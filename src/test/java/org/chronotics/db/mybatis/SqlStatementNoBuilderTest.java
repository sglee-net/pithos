package org.chronotics.db.mybatis;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.chronotics.pithos.Application.class})
public class SqlStatementNoBuilderTest {
	
	// table name
	public static String TABLE1 = "table1";
	public static String TABLE2 = "table2";
	// public static variable matches with COLUMN in DB
	public static String CID = "c0";
	public static String CSTR1 = "c1";
	public static String CSTR2 = "c2";
	public static String CNUMBER = "c3";
	public static String CVARBINARY = "c4";
	public static String CBLOB = "c5";
	public static String CCLOB = "c6";
	public static String CDATE = "c7";
	public static String CTIME = "c8";
	public static String CTIMESTAMP = "c9";
	
	// for Table1
	public static List<Map<String,Object>> itemSet1 = 
			new ArrayList<Map<String,Object>>();
	public static int itemCount = 100;
	
	// for Table2
	public static List<Map<String,Object>> itemSet2 = 
			new ArrayList<Map<String,Object>>();
	
	private void createTables() {
		dropTables();
		{
			String statement=
			"CREATE TABLE "+ TABLE1 +" (" + 
			"	c0 BIGINT(20) unsigned NOT NULL AUTO_INCREMENT," + 
			"	c1 VARCHAR(255) NULL," + 
			"	c2 VARCHAR(255) NULL," + 
			"	c3 FLOAT NULL default '0'," + 
			"	c4 VARBINARY(255) NULL," + 
			"	c5 BLOB NULL," + 
			"	c6 TEXT NULL," + 
			"	c7 DATE NULL," + 
			"	c8 TIME NULL," + 
			"	c9 TIMESTAMP(6) NULL," + 
			"	PRIMARY KEY (c0)" + 
			");";
			Map<Object,Object> sqlParameter = new LinkedHashMap<Object,Object>();
			sqlParameter.put(SqlStatement.statement,statement);
			mapper.doStatement(mapper.getClassName()+".doStatement",sqlParameter);
		}
		{
			String statement=
			"CREATE TABLE "+ TABLE2 +" (" + 
			"	c0 BIGINT(20) unsigned NOT NULL AUTO_INCREMENT," + 
			"	c1 VARCHAR(255) NULL," + 
			"	c2 VARCHAR(255) NULL," + 
			"	c3 FLOAT NULL default '0'," + 
			"	c4 VARBINARY(255) NULL," + 
			"	c5 BLOB NULL," + 
			"	c6 TEXT NULL," + 
			"	c7 DATE NULL," + 
			"	c8 TIME NULL," + 
			"	c9 TIMESTAMP(6) NULL," + 
			"	PRIMARY KEY (c0)" + 
			");";
			Map<Object,Object> sqlParameter = new LinkedHashMap<Object,Object>();
			sqlParameter.put(SqlStatement.statement,statement);
			mapper.doStatement(mapper.getClassName()+".doStatement",sqlParameter);
		}
	}
	
	private void dropTables() {
		{
			String statement=
			"DROP TABLE IF EXISTS " + TABLE1;
			Map<Object,Object> sqlParameter = new LinkedHashMap<Object,Object>();
			sqlParameter.put(SqlStatement.statement,statement);
			mapper.doStatement(mapper.getClassName()+".doStatement",sqlParameter);
		}
		{
			String statement=
			"DROP TABLE IF EXISTS " + TABLE2;
			Map<Object,Object> sqlParameter = new LinkedHashMap<Object,Object>();
			sqlParameter.put(SqlStatement.statement,statement);
			mapper.doStatement(mapper.getClassName()+".doStatement",sqlParameter);
		}
	}
	
	private int insertMultipleItems(String _tableName) throws Exception {
		List<Map<String,Object>> itemSet;
		if(_tableName == TABLE1) {
			itemSet = itemSet1;
		} else if (_tableName == TABLE2) {
			itemSet = itemSet2;
		} else {
			assert(false);
			return 0;
		}
		
		List<Object> colNames = new ArrayList<Object>();
		colNames.add(CSTR1);
		colNames.add(CSTR2);
		colNames.add(CNUMBER);
		// binary
		// blob
		// clob
		colNames.add(CDATE);
		colNames.add(CTIME);
		colNames.add(CTIMESTAMP);
		
		List<List<Object>> records = new ArrayList<List<Object>>();
		for(Map<String,Object> entry : itemSet) {
			List<Object> variables = new ArrayList<Object>();
			
			String str1 = (String) entry.get(CSTR1);
			String str2 = (String) entry.get(CSTR2);
			Object number = entry.get(CNUMBER);
			// binary
			// blob
			// clob
			Object date = entry.get(CDATE);
			Object time = entry.get(CTIME);
			Object timestamp = entry.get(CTIMESTAMP);
						
			variables.add(str1);
			variables.add(str2);
			variables.add(number);
			// binary
			// blob
			// clob
			variables.add(date);
			variables.add(time);
			variables.add(timestamp);
			
			records.add(variables);
		}
		
		Map<Object,Object> sqlParameter = new LinkedHashMap<Object,Object>();
		
		List<Object> insert = new ArrayList<Object>();
		insert.add(_tableName);
		
		sqlParameter.put(SqlStatement.insert, insert);
		sqlParameter.put(SqlStatement.colNames, colNames);
		sqlParameter.put(SqlStatement.records, records);
		
		return mapper.insert(mapper.getClassName()+".insertMultipleItems",sqlParameter);
	}
	
	private int insertItemsOneByOne(String _tableName) throws Exception {
		List<Map<String,Object>> itemSet;
		if(_tableName == TABLE1) {
			itemSet = itemSet1;
		} else if (_tableName == TABLE2) {
			itemSet = itemSet2;
		} else {
			return 0;
		}
		
		int totalInsertion = 0;
		// insert
		for(Map<String,Object> entry : itemSet) {
			String str1 = (String) entry.get(CSTR1);
			String str2 = (String) entry.get(CSTR2);
			Object number = entry.get(CNUMBER);
			// binary
			// blob
			// clob
			Object date = entry.get(CDATE);
			Object time = entry.get(CTIME);
			Object timestamp = entry.get(CTIMESTAMP);
			
			// old method
			Map<Object,Object> sqlParameter = new LinkedHashMap<Object,Object>();
			
			List<Object> insert = new ArrayList<Object>();
			insert.add(_tableName);
			
			List<Object> colNames = new ArrayList<Object>();
			colNames.add(CSTR1);
			colNames.add(CSTR2);
			colNames.add(CNUMBER);
			// binary
			// blob
			// clob
			colNames.add(CDATE);
			colNames.add(CTIME);
			colNames.add(CTIMESTAMP);
			
			List<Object> colValues = new ArrayList<Object>();
			colValues.add(str1);
			colValues.add(str2);
			colValues.add(number);
			// binary
			// blob
			// clob
			colValues.add(date);
			colValues.add(time);
			colValues.add(timestamp);
			
			sqlParameter.put(SqlStatement.insert, insert);
			sqlParameter.put(SqlStatement.colNames, colNames);
			sqlParameter.put(SqlStatement.colValues, colValues);
			
			int count = mapper.insert(mapper.getClassName()+".insert",sqlParameter);
			totalInsertion += count;
		}
		
		return totalInsertion; 
	}
	
	private int deleteLikeName(String _tableName) throws Exception {
		Map<Object,Object> sqlParameter = new LinkedHashMap<Object,Object>();
		
		List<Object> delete = new ArrayList<Object>();
		delete.add(_tableName);
		
		List<Object> where = new ArrayList<Object>();
		where.add(CSTR1);
		where.add(SqlStatement.OPERATOR.LIKE);
		where.add(SqlStatement.toVV("%"));
		
		Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
		whereCondition.put(SqlStatement.where.toString(), where);
		
		sqlParameter.put(SqlStatement.delete.toString(), delete);
		sqlParameter.put(SqlStatement.whereCondition.toString(), whereCondition);

		int result = mapper.delete(mapper.getClassName()+".delete", sqlParameter);
		
		return result;
	}
	
	@Rule
	public ExpectedException exceptions = ExpectedException.none();
	
	@Resource(name = "mapperSimpleMySQL")
	private Mapper mapper;

	@BeforeClass
	public static void setup() {
		for(int i=0; i<itemCount; i++) {
			Map<String,Object> item = 
					new LinkedHashMap<String,Object>();
			item.put(CSTR1, Integer.toString(i));
			item.put(CSTR2, Integer.toString(i));
			item.put(CNUMBER, (double)i);
			// BINARY
			// BLOB
			// CLOB
			item.put(CDATE, new java.sql.Date(new java.util.Date().getTime()));
			item.put(CTIME, new java.sql.Time(new java.util.Date().getTime()));
			item.put(CTIMESTAMP, new java.sql.Timestamp(new java.util.Date().getTime()));
			itemSet1.add(item);
		}
		
		for(int i=0; i<itemCount; i++) {
			Map<String,Object> item = 
					new LinkedHashMap<String,Object>();
			item.put(CSTR1, Integer.toString(i));
			item.put(CSTR2, Integer.toString(i));
			item.put(CNUMBER, (double)i);
			// BINARY
			// BLOB
			// CLOB
			item.put(CDATE, new java.sql.Date(new java.util.Date().getTime()));
			item.put(CTIME, new java.sql.Time(new java.util.Date().getTime()));
			item.put(CTIMESTAMP, new java.sql.Timestamp(new java.util.Date().getTime()));
			itemSet2.add(item);
		}
	}
	
	@Test
	public void testSingleInsert() throws Exception {
		createTables();
		
		int resultCount = 0;
		resultCount = insertItemsOneByOne(TABLE1);
		assertEquals(itemCount, resultCount);
		
		resultCount = insertItemsOneByOne(TABLE2);
		assertEquals(itemCount, resultCount);
		
		this.deleteLikeName(TABLE1);
		this.deleteLikeName(TABLE2);
		
		dropTables();
	}
	
	@Test
	public void testMultipleInsert() throws Exception {
		createTables();
		
		int resultCount = 0;
		resultCount = insertMultipleItems(TABLE1);
		assertEquals(itemCount, resultCount);
		
		resultCount = insertMultipleItems(TABLE2);
		assertEquals(itemCount, resultCount);

		dropTables();
	}
	
	@Test
	public void testDelete() throws Exception {
		createTables();
		
		int resultCount = 0;
		resultCount = insertMultipleItems(TABLE1);
		assertEquals(itemCount, resultCount);
		
		resultCount = insertMultipleItems(TABLE2);
		assertEquals(itemCount, resultCount);
		
		// delete
		resultCount = deleteLikeName(TABLE1);
		assertEquals(itemCount, resultCount);
		
		// delete
		resultCount = deleteLikeName(TABLE2);
		assertEquals(itemCount, resultCount);
			
		dropTables();
	}
	
	@Test
	public void testComplexSelect() throws Exception {
		String _tableName = TABLE1;
		
		createTables();
		
		int resultCount = 0;
		resultCount = insertMultipleItems(_tableName);
		assertEquals(itemCount, resultCount);
		
		// select
		{
//			double comparisonNumber = 0;
			
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			
			List<Object> select = new ArrayList<Object>();
//			select.add(CSTR1);
//			select.add(CSTR2);
			select.add("*");

			List<Object> from = new ArrayList<Object>();
			from.add(_tableName);
			
			List<Object> where = new ArrayList<Object>();
			where.add(CNUMBER);
			where.add(SqlStatement.OPERATOR.GE); 
			where.add(0);
			
			List<Object> and = new ArrayList<Object>();
//			and.add(SqlStatement.OPERATOR.AND);
//			and.add(SqlStatement.OPERATOR.PARENTHESIS_LEFT); 
			and.add(CSTR1);
			and.add(SqlStatement.OPERATOR.EQ);
			and.add("0");
			List<Object> or = new ArrayList<Object>();
//			or.add(SqlStatement.OPERATOR.OR);
			or.add(CSTR1);
			or.add(SqlStatement.OPERATOR.EQ);
			or.add("1");
//			or.add(SqlStatement.OPERATOR.PARENTHESIS_RIGHT); 

//			Map<String,Object> andOr = new LinkedHashMap<String,Object>();
//			andOr.put(SqlStatement.and, and);
//			andOr.put(SqlStatement.or, or);
			
			Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
			whereCondition.put(SqlStatement.where.toString(), where);
			whereCondition.put(SqlStatement.and.toString(), and);
			whereCondition.put(SqlStatement.or.toString(), or);
			
			List<Object> orderBy = new ArrayList<Object>();
			orderBy.add(CNUMBER);
			orderBy.add(CSTR2);
			
			List<Object> orderByAscOrDec = new ArrayList<Object>();
			orderByAscOrDec.add(SqlStatement.ASC);
			
			queryParameter.put(SqlStatement.select, select);
			queryParameter.put(SqlStatement.from, from);
			queryParameter.put(SqlStatement.select, select);
			queryParameter.put(SqlStatement.whereCondition, whereCondition);
			queryParameter.put(SqlStatement.orderBy, orderBy);
			queryParameter.put(SqlStatement.orderByAscOrDec, orderByAscOrDec);
	
			List<Map<String,Object>> result = 
					mapper.select(mapper.getClassName()+".select", queryParameter);
			assertEquals(2, result.size());
			
			java.sql.Timestamp timestampResult = (java.sql.Timestamp)(result.get(0).get(CTIMESTAMP));
			java.sql.Timestamp timestampOrg = (java.sql.Timestamp)(itemSet1.get(0).get(CTIMESTAMP));
			assertEquals(timestampOrg.getTime(), timestampResult.getTime());
		}
		
		dropTables();
	}
	
	@Test
	public void testSelectOneDeleteOne() throws Exception {
		String _tableName = TABLE1;
		
		createTables();
		
		int resultCount = 0;
		resultCount = insertMultipleItems(_tableName);
		assertEquals(itemCount, resultCount);
		
		// select
		for(int i=0; i<itemCount; i++){
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			
			List<Object> select = new ArrayList<Object>();
			select.add("*");

			List<Object> from = new ArrayList<Object>();
			from.add(TABLE1);
						
			List<Object> where = new ArrayList<Object>();
			where.add(CSTR1);
			where.add(SqlStatement.OPERATOR.EQ);
			where.add(SqlStatement.toVV(Integer.toString(i)));
			Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
			whereCondition.put(SqlStatement.where, where);
			
			queryParameter.put(SqlStatement.select, select);
			queryParameter.put(SqlStatement.from, from);
			queryParameter.put(SqlStatement.whereCondition, whereCondition);
	
			List<Map<String,Object>> result = 
					mapper.select(mapper.getClassName()+".select", queryParameter);
			assertEquals(1, result.size());
		}

		// delete one item "0"
		{
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			
			List<Object> delete = new ArrayList<Object>();
			delete.add(TABLE1);
			
			List<Object> where = new ArrayList<Object>();
			where.add(CSTR1);
			where.add(SqlStatement.OPERATOR.EQ);
			where.add(SqlStatement.toVV("0"));
			Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
			whereCondition.put(SqlStatement.where, where);
			
			queryParameter.put(SqlStatement.delete, delete);
			queryParameter.put(SqlStatement.whereCondition, whereCondition);
	
			int result = mapper.delete(mapper.getClassName()+".delete", queryParameter);
			assertEquals(1, result);
		}
		
		// select * => return userCount-1
		{
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			
			List<Object> select = new ArrayList<Object>();
			select.add("*");
			
			List<Object> from = new ArrayList<Object>();
			from.add(TABLE1);
			
			queryParameter.put(SqlStatement.select, select);
			queryParameter.put(SqlStatement.from, from);
	
			List<Map<String,Object>> result = 
					mapper.select(mapper.getClassName()+".select", queryParameter);
			assertEquals(itemCount-1, result.size());
		}
		
		dropTables();
	}
	
	@Test
	public void testJoinInner() throws Exception {		
		createTables();

		int resultCount = 0;
		resultCount = insertMultipleItems(TABLE1);
		assertEquals(itemCount, resultCount);
		
		resultCount = 0;
		resultCount = insertMultipleItems(TABLE2);
		assertEquals(itemCount, resultCount); 
		
		// select
		for(int i=0; i<itemCount; i++){
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();

			List<Object> select = new ArrayList<Object>();
			select.add("*");
	
			List<Object> from = new ArrayList<Object>();
			from.add(TABLE1);
			
			List<Object> innerJoinCondition = new ArrayList<Object>();
			innerJoinCondition.add(SqlStatement.on.toString());
			innerJoinCondition.add("table1.c1 = table2.c1");
			
			queryParameter.put(SqlStatement.select, select);
			queryParameter.put(SqlStatement.from, from);
			queryParameter.put(SqlStatement.innerJoin, innerJoinCondition);
	
			List<Map<String,Object>> result = 
					mapper.select(mapper.getClassName()+".select", queryParameter);
			assertEquals(itemCount, result.size());
		}

		dropTables();
	}
	
	@Test
	public void testJoinSelf() throws Exception {
		createTables();

		int resultCount = 0;
		resultCount = insertMultipleItems(TABLE1);
		assertEquals(itemCount, resultCount);
		
		resultCount = 0;
		resultCount = insertMultipleItems(TABLE2);
		assertEquals(itemCount, resultCount); 
		
		// select : Self Join
		{
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			
			List<Object> select = new ArrayList<Object>();
			select.add("*");
			
			List<Object> from = new ArrayList<Object>();
			from.add(TABLE1);
			from.add(TABLE2);
			
			List<Object> where = new ArrayList<Object>();
			where.add(TABLE1+"." + CSTR1);
			where.add(SqlStatement.OPERATOR.EQ);
			where.add(TABLE2+"." + CSTR1);
			Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
			whereCondition.put(SqlStatement.where.toString(), where);
			
			queryParameter.put(SqlStatement.select, select);
			queryParameter.put(SqlStatement.from, from);
			queryParameter.put(SqlStatement.whereCondition, whereCondition);
	
			List<Map<String,Object>> result = 
					mapper.select(mapper.getClassName()+".select", queryParameter);
			assertEquals(itemCount, result.size());
		}

		dropTables();
	}
	
	@Test
	public void testUpdate() throws Exception {
		createTables();

		int resultCount = 0;
		resultCount = insertMultipleItems(TABLE1);
		assertEquals(itemCount, resultCount);
		
		// update one
		for(int i=0; i<itemCount; i++){
//			String strWillbeUpdated = "U" + Integer.toString(i);
			
			Map<Object,Object> queryParameterUpdate = new LinkedHashMap<Object,Object>();
			{
				List<Object> update = new ArrayList<Object>();
				update.add(TABLE1);
				
				Map<Object,Object> set = new LinkedHashMap<Object,Object>();
				set.put(CNUMBER, 999);
				
				List<Object> where = new ArrayList<Object>();
				where.add(CSTR1);
				where.add(SqlStatement.OPERATOR.EQ);
				where.add(SqlStatement.toVV(Integer.toString(i)));
				Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
				whereCondition.put(SqlStatement.where, where);
				
				queryParameterUpdate.put(SqlStatement.update, update);
				queryParameterUpdate.put(SqlStatement.set, set);
				queryParameterUpdate.put(SqlStatement.whereCondition, whereCondition);
			}
	
			int count = mapper.update(mapper.getClassName()+".update", queryParameterUpdate);
			assertEquals(1, count);
		
			// select
			Map<Object,Object> queryParameterSelect = new LinkedHashMap<Object,Object>();
			{
				
				List<Object> select = new ArrayList<Object>();
				select.add("*");
				
				List<Object> from = new ArrayList<Object>();
				from.add(TABLE1);
				
				List<Object> where = new ArrayList<Object>();
				where.add(CNUMBER);
				where.add(SqlStatement.OPERATOR.EQ);
				where.add(999);
				where.add(SqlStatement.OPERATOR.AND);
				where.add(CSTR1);
				where.add(SqlStatement.OPERATOR.EQ);
				where.add(SqlStatement.toVV(Integer.toString(i)));
				Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
				whereCondition.put(SqlStatement.where, where);
				
				queryParameterSelect.put(SqlStatement.select, select);
				queryParameterSelect.put(SqlStatement.from, from);
				queryParameterSelect.put(SqlStatement.whereCondition, whereCondition);
			}
	
			List<Map<String,Object>> result = 
					mapper.select(mapper.getClassName()+".select", queryParameterSelect);
			assertEquals(1, result.size());
		}
		
		dropTables();
	}
	
	@Test
	public void testComplexUpdate() throws Exception {
		createTables();

		int resultCount = 0;
		resultCount = insertMultipleItems(TABLE1);
		assertEquals(itemCount, resultCount);
		
		resultCount = insertMultipleItems(TABLE2);
		assertEquals(itemCount, resultCount);
		
		// update one
		for(int i=0; i<itemCount; i++){
			float numberUpdate = 999;
			
			Map<Object,Object> queryParameterUpdate = new LinkedHashMap<Object,Object>();
			{
				List<Object> update = new ArrayList<Object>();
				update.add(TABLE1);
				
				List<Object> innerJoin = new ArrayList<Object>();
				innerJoin.add(queryParameterUpdate);
				innerJoin.add(TABLE2);
				innerJoin.add("table1.c1 = table2.c1");
				
				Map<Object,Object> set = new LinkedHashMap<Object,Object>();
				set.put(CNUMBER, numberUpdate);
				
				List<Object> where = new ArrayList<Object>();
				where.add(CSTR1);
				where.add(SqlStatement.OPERATOR.EQ);
				where.add(SqlStatement.toVV(Integer.toString(i)));
				where.add(SqlStatement.OPERATOR.AND);
				where.add(CNUMBER);
				where.add(SqlStatement.OPERATOR.EQ);
				where.add(i);
				Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
				whereCondition.put(SqlStatement.where.toString(), where);
				
				queryParameterUpdate.put(SqlStatement.update, update);
				queryParameterUpdate.put(SqlStatement.innerJoin, innerJoin);
				queryParameterUpdate.put(SqlStatement.set, set);
				queryParameterUpdate.put(SqlStatement.whereCondition, whereCondition);
			}
	
			int count = mapper.update(mapper.getClassName()+".update", queryParameterUpdate);
			assertEquals(1, count);
		
			// select
			Map<Object,Object> queryParameterSelect = new LinkedHashMap<Object,Object>();
			{
				
				List<Object> select = new ArrayList<Object>();
				select.add("*");
				
				List<Object> from = new ArrayList<Object>();
				from.add(TABLE1);
				
				List<Object> where = new ArrayList<Object>();
				where.add(CNUMBER);
				where.add(SqlStatement.OPERATOR.EQ);
				where.add(numberUpdate);
				Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
				whereCondition.put(SqlStatement.where, where);
				
				queryParameterSelect.put(SqlStatement.select, select);
				queryParameterSelect.put(SqlStatement.from, from);
				queryParameterSelect.put(SqlStatement.whereCondition, whereCondition);
			}
	
			List<Map<String,Object>> result = 
					mapper.select(mapper.getClassName()+".select", queryParameterSelect);
			assertEquals(i+1, result.size());
			
			java.sql.Timestamp timestampResult = (java.sql.Timestamp)(result.get(0).get(CTIMESTAMP));
			java.sql.Timestamp timestampOrg = (java.sql.Timestamp)(itemSet1.get(0).get(CTIMESTAMP));
			assertEquals(timestampOrg.getTime(), timestampResult.getTime());
		}
		
		dropTables();
	}
}
