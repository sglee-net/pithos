package org.chronotics.db.mybatis;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.chronotics.db.mybatis.SqlStatement.COMMAND;
import org.chronotics.db.mybatis.SqlStatement.KEYWORD;
import org.chronotics.db.mybatis.SqlStatement.OPERATOR;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.chronotics.pithos.Application.class})
public class MapperOracleTest {
	
	@Rule
	public ExpectedException exceptions = ExpectedException.none();
	
	@Resource(name = "mapperSimpleOracle")
	private MapperOracle mapper;

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
			String statement = 
					"DROP SEQUENCE TABLE1_SEQUENCE ";
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			try {
				mapper.doStatement(queryParameter);
			} catch (Exception e) {
				
			}
		}	
		{
			String statement = 
					"CREATE SEQUENCE TABLE1_SEQUENCE START WITH 1 ";
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			mapper.doStatement(queryParameter);
		}	
		{
			String statement = 
					"CREATE TABLE " + TABLE1 
					+ " ( c0 NUMBER(10) NOT NULL, "
					+ "c1 VARCHAR2(255) NULL, "
					+ "c2 VARCHAR2(255) NULL, "
					+ "c3 NUMBER(10,3) NULL, "
					+ "c4 BFILE NULL, "
					+ "c5 BLOB NULL, "
					+ "c6 CLOB NULL, "
					+ "c7 DATE NULL, "
					+ "c8 DATE NULL, "
					+ "c9 TIMESTAMP(6) NULL,"
					+ "CONSTRAINT TABLE1_PK PRIMARY KEY (c0) ENABLE ) ";
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			mapper.doStatement(queryParameter);
		}
//		{
//			String statement = 
//					"CREATE OR REPLACE TRIGGER TR1 "
//					+ "BEFORE INSERT ON " + TABLE1 + " "
//					+ "FOR EACH ROW "
//					+ "WHEN (new.c0 IS NULL) "
//					+ "BEGIN "
//					+ "SELECT TABLE1_SEQUENCE.NEXTVAL "
//					+ "INTO :new.c0 "
//					+ "FROM dual; END ";
////					+ "END ";
//			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
//			queryParameter.put(KEYWORD.STATEMENT,statement);
//			mapper.doStatement(queryParameter);
//		}		
	
		{
			String statement = 
					"DROP SEQUENCE TABLE2_SEQUENCE ";
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			try {
				mapper.doStatement(queryParameter);
			} catch (Exception e) {
				
			}
		}	
		{
			String statement = 
					"CREATE SEQUENCE TABLE2_SEQUENCE START WITH 1 ";
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			mapper.doStatement(queryParameter);
		}	
		{
			String statement= 
					"CREATE TABLE " + TABLE2 
					+ " ( c0 NUMBER(10) NOT NULL, "
					+ "c1 VARCHAR2(255) NULL, "
					+ "c2 VARCHAR2(255) NULL, "
					+ "c3 NUMBER(10,3) NULL, "
					+ "c4 BFILE NULL, "
					+ "c5 BLOB NULL, "
					+ "c6 CLOB NULL, "
					+ "c7 DATE NULL, "
					+ "c8 DATE NULL, "
					+ "c9 TIMESTAMP(6) NULL,"
					+ "CONSTRAINT TABLE2_PK PRIMARY KEY (c0) ENABLE ) ";
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			mapper.doStatement(queryParameter);
		}
	}
	
	private void dropTables() {
		{
			String statement=
			"DROP TABLE " + TABLE1;
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			try {
				mapper.doStatement(queryParameter);
			} catch (Exception e) {
				
			}
		}
		{
			String statement=
			"DROP TABLE " + TABLE2;
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			try {
				mapper.doStatement(queryParameter);
			} catch (Exception e) {
				
			}
		}
	}
	
	private int insertMultipleItems(String _tableName) {
		List<Map<String,Object>> itemSet;
		if(_tableName == TABLE1) {
			itemSet = itemSet1;
		} else if (_tableName == TABLE2) {
			itemSet = itemSet2;
		} else {
			assert(false);
			return 0;
		}
		
		Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
		
		List<Object> insert = new ArrayList<Object>();
		insert.add(_tableName);
		
		List<Object> colNames = new ArrayList<Object>();
		colNames.add(CID);
		colNames.add(CSTR1);
		colNames.add(CSTR2);
		colNames.add(CNUMBER);
		// binary
		// blob
		// clob
		colNames.add(CDATE);
		colNames.add(CTIME);
		colNames.add(CTIMESTAMP);
		
		List<Object> records = new ArrayList<Object>();
		for(Map<String,Object> entry : itemSet) {
			List<Object> variables = new ArrayList<Object>();
			Object id = entry.get(CID);
			String str1 = (String) entry.get(CSTR1);
			String str2 = (String) entry.get(CSTR2);
			Object number = entry.get(CNUMBER);
			// binary
			// blob
			// clob
			Object date = entry.get(CDATE);
			Object time = entry.get(CTIME);
			Object timestamp = entry.get(CTIMESTAMP);
			
			variables.add(id);
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
		
		queryParameter.put(COMMAND.INSERT, insert);
		queryParameter.put(KEYWORD.COLNAMES, colNames);
		queryParameter.put(KEYWORD.RECORDS, records);
		
		return mapper.insertMultipleItems(queryParameter);
	}
	
	private int insertItemsOneByOne(String _tableName) {
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
			Object id = entry.get(CID);
			String str1 = (String) entry.get(CSTR1);
			String str2 = (String) entry.get(CSTR2);
			Object number = entry.get(CNUMBER);
			// binary
			// blob
			// clob
			Object date = entry.get(CDATE);
			Object time = entry.get(CTIME);
			Object timestamp = entry.get(CTIMESTAMP);
			
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			
			List<Object> insert = new ArrayList<Object>();
			insert.add(_tableName);
			
			List<Object> colNames = new ArrayList<Object>();
			colNames.add(CID);
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
			colValues.add(id);
			colValues.add(str1);
			colValues.add(str2);
			colValues.add(number);
			// binary
			// blob
			// clob
			colValues.add(date);
			colValues.add(time);
			colValues.add(timestamp);
			
			queryParameter.put(COMMAND.INSERT, insert);
			queryParameter.put(KEYWORD.COLNAMES, colNames);
			queryParameter.put(KEYWORD.COLVALUES, colValues);

			int count = mapper.insert(queryParameter);
			totalInsertion += count;
		}
		
		return totalInsertion; 
	}
	
	private int deleteLikeName(String _tableName) {
		Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
		
		List<Object> delete = new ArrayList<Object>();
//		delete.add(Mapper.delete);
		delete.add(_tableName);
		
		List<Object> where = new ArrayList<Object>();
		where.add(CSTR1);
		where.add(OPERATOR.LIKE);
		where.add(SqlStatement.toVV("%"));
		
		Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
		whereCondition.put(COMMAND.WHERE, where);
		
		queryParameter.put(COMMAND.DELETE, delete);
		queryParameter.put(COMMAND.WHERECONDITION, whereCondition);

		int result = mapper.delete(queryParameter);
		
		return result;
	}

	@BeforeClass
	public static void setup() {
		for(int i=0; i<itemCount; i++) {
			Map<String,Object> item = 
					new LinkedHashMap<String,Object>();
			item.put(CID,i);
			item.put(CSTR1, Integer.toString(i));
			item.put(CSTR2, Integer.toString(i));
			item.put(CNUMBER, (double)i);
			// BINARY
			// BLOB
			// CLOB
			item.put(CDATE, new java.sql.Date(new java.util.Date().getTime()));
			item.put(CTIME, new java.sql.Date(new java.util.Date().getTime()));
			item.put(CTIMESTAMP, new java.sql.Timestamp(new java.util.Date().getTime()));
			itemSet1.add(item);
		}
		
		for(int i=0; i<itemCount; i++) {
			Map<String,Object> item = 
					new LinkedHashMap<String,Object>();
			item.put(CID,i);
			item.put(CSTR1, Integer.toString(i));
			item.put(CSTR2, Integer.toString(i));
			item.put(CNUMBER, (double)i);
			// BINARY
			// BLOB
			// CLOB
			item.put(CDATE, new java.sql.Date(new java.util.Date().getTime()));
			item.put(CTIME, new java.sql.Date(new java.util.Date().getTime()));
			item.put(CTIMESTAMP, new java.sql.Timestamp(new java.util.Date().getTime()));
			itemSet2.add(item);
		}
	}
	
	@Test
	public void createTable() {
		createTables();
		dropTables();
	}
	
	@Test
	public void testSingleInsert() {
		createTables();
		
		int resultCount = 0;
		resultCount = insertItemsOneByOne(TABLE1);
		assertEquals(itemCount, resultCount);
		
		resultCount = insertItemsOneByOne(TABLE2);
		assertEquals(itemCount, resultCount);
		
		dropTables();
	}
	
	@Test
	public void testMultipleInsert() {
		createTables();
		
		int resultCount = 0;
		resultCount = insertMultipleItems(TABLE1);
		assertEquals(itemCount, resultCount);
		
		resultCount = insertMultipleItems(TABLE2);
		assertEquals(itemCount, resultCount);

		dropTables();
	}
	
//	@Test
//	public void testDelete() {
//		createTables();
//		
//		int resultCount = 0;
//		resultCount = insertMultipleItems(TABLE1);
//		assertEquals(itemCount, resultCount);
//		
//		resultCount = insertMultipleItems(TABLE2);
//		assertEquals(itemCount, resultCount);
//		
//		// delete
//		resultCount = deleteLikeName(TABLE1);
//		assertEquals(itemCount, resultCount);
//		
//		// delete
//		resultCount = deleteLikeName(TABLE2);
//		assertEquals(itemCount, resultCount);
//			
//		dropTables();
//	}
//	
//	@Test
//	public void testComplexSelect() {
//		String _tableName = TABLE1;
//		
//		createTables();
//		
//		int resultCount = 0;
//		resultCount = insertMultipleItems(_tableName);
//		assertEquals(itemCount, resultCount);
//		
//		// select
//		{
////			double comparisonNumber = 0;
//			
//			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
//			
//			List<Object> select = new ArrayList<Object>();
////			select.add(CSTR1);
////			select.add(CSTR2);
//			select.add("*");
//
//			List<Object> from = new ArrayList<Object>();
//			from.add(_tableName);
//			
//			List<Object> where = new ArrayList<Object>();
//			where.add(CNUMBER);
//			where.add(OPERATOR.GE); 
//			where.add(0);
//			
//			List<Object> and = new ArrayList<Object>();
////			and.add(OPERATOR.AND);
////			and.add(OPERATOR.PARENTHESIS_LEFT); 
//			and.add(CSTR1);
//			and.add(OPERATOR.EQ);
//			and.add("0");
//			List<Object> or = new ArrayList<Object>();
////			or.add(OPERATOR.OR);
//			or.add(CSTR1);
//			or.add(OPERATOR.EQ);
//			or.add("1");
////			or.add(OPERATOR.PARENTHESIS_RIGHT); 
//
////			Map<String,Object> andOr = new LinkedHashMap<String,Object>();
////			andOr.put(SqlStatement.and, and);
////			andOr.put(SqlStatement.or, or);
//			
//			Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
//			whereCondition.put(COMMAND.WHERE, where);
//			whereCondition.put(COMMAND.AND, and);
//			whereCondition.put(COMMAND.OR, or);
//			
//			List<Object> orderBy = new ArrayList<Object>();
//			orderBy.add(CNUMBER);
//			orderBy.add(CSTR2);
//			
//			List<Object> orderByAscOrDec = new ArrayList<Object>();
//			orderByAscOrDec.add(COMMAND.ASC);
//			
//			queryParameter.put(COMMAND.SELECT, select);
//			queryParameter.put(COMMAND.FROM, from);
//			queryParameter.put(COMMAND.SELECT, select);
//			queryParameter.put(COMMAND.WHERECONDITION, whereCondition);
//			queryParameter.put(COMMAND.ORDERBY, orderBy);
//			queryParameter.put(COMMAND.ORDERBYASCORDEC, orderByAscOrDec);
//	
//			List<Map<String,Object>> result = 
//					mapper.selectList(queryParameter);
//			assertEquals(2, result.size());
//			
//			java.sql.Timestamp timestampResult = (java.sql.Timestamp)(result.get(0).get(CTIMESTAMP));
//			java.sql.Timestamp timestampOrg = (java.sql.Timestamp)(itemSet1.get(0).get(CTIMESTAMP));
//			assertEquals(timestampOrg.getTime(), timestampResult.getTime());
//		}
//		
//		dropTables();
//	}
		
}
