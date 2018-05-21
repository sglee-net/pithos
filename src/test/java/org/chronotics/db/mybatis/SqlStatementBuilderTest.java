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
public class SqlStatementBuilderTest {

//	// Strings that is defined as below 
//	// could be better than this kind of enum
//	public static enum TABLE {
//		TABLENAME("table1"),
//		CID("c0"),
//		CSTR1("c1"),
//		CSTR2("c2"),
//		CNUMBER("c3"),
//		CVARBINARY("c4"),
//		CBLOB("c5"),
//		CCLOB("c6"),
//		CDATE("c7"),
//		CTIME("c8"),
//		CTIMESTAMP("c9");
//		
//		private String str;
//		TABLE(String _arg) {
//			str = _arg;
//		}
//		public String toString() {
//			return str;
//		}
//	}
	
	@Rule
	public ExpectedException exceptions = ExpectedException.none();
	
	@Resource(name = "mapperSimpleMySQL")
	private Mapper mapper;
	
	// table name
	public static String TABLENAME = "table1";
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
	
	private void createTables() {
		dropTables();
		{
			String statement=
			"CREATE TABLE "+ TABLENAME +" (" + 
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
			"DROP TABLE IF EXISTS " + TABLENAME;
			Map<Object,Object> sqlParameter = new LinkedHashMap<Object,Object>();
			sqlParameter.put(SqlStatement.statement,statement);
			mapper.doStatement(mapper.getClassName()+".doStatement",sqlParameter);
		}
	}
	
	private int insertMultipleItems(String _tableName) throws Exception {
		List<Map<String,Object>> itemSet;
		if(_tableName == TABLENAME) {
			itemSet = itemSet1;
		}else {
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
		
		SqlStatement sqlStatement = 
				new SqlStatement.Builder()
				.insert(TABLENAME)
				.records(colNames, records)
				.build();
		return mapper.insert(sqlStatement);
	}
	
	private int insertItemsOneByOne(String _tableName) throws Exception {
		List<Map<String,Object>> itemSet;
		if(_tableName == TABLENAME) {
			itemSet = itemSet1;
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

			SqlStatement sqlStatement = 
					new SqlStatement.Builder()
					.insert(TABLENAME)
					.colValue(CSTR1, str1)
					.colValue(CSTR2, str2)
					.colValue(CNUMBER, number)
					.colValue(CDATE, date)
					.colValue(CTIME, time)
					.colValue(CTIMESTAMP, timestamp)
					.build();
			int count = mapper.insert(sqlStatement);
			totalInsertion += count;
		}
		
		return totalInsertion; 
	}
	
	private int deleteLikeName(String _tableName) throws Exception {	
		SqlStatement sqlStatement = 
				new SqlStatement.Builder()
				.delete(TABLENAME)
				.where(CSTR1, SqlStatement.OPERATOR.EQ, SqlStatement.toVV("%"))
				.build();
		int result = mapper.delete(sqlStatement);
		
		return result;
	}

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
	}
	
	@Test
	public void testSingleInsert() throws Exception {
		createTables();
		
		int resultCount = 0;
		resultCount = insertItemsOneByOne(TABLENAME);
		assertEquals(itemCount, resultCount);

		this.deleteLikeName(TABLENAME);
		
		dropTables();
	}
	
	@Test
	public void testSelect() throws Exception {
		createTables();
		
		int resultCount = 0;
		resultCount = insertMultipleItems(TABLENAME);
		assertEquals(itemCount, resultCount);
		
		for(int i=0; i<itemCount; i++) {
			String str1 = (String) itemSet1.get(i).get(CSTR1);
			SqlStatement sqlStatement = 
					new SqlStatement.Builder()
					.select("*")
					.from(TABLENAME)
					.where(CSTR1, SqlStatement.OPERATOR.EQ, SqlStatement.toVV(str1))
					.build();
			
			List<Map<String,Object>> result = 
					mapper.select(sqlStatement);
			assertEquals(1, result.size());
		}
		
		dropTables();
	}

	@Test
	public void testInsert() throws Exception {
		createTables();
		
		for(Map<String,Object> entry : itemSet1) {
			String str1 = (String) entry.get(CSTR1);
			String str2 = (String) entry.get(CSTR2);
			Object number = entry.get(CNUMBER);
			// binary
			// blob
			// clob
			Object date = entry.get(CDATE);
			Object time = entry.get(CTIME);
			Object timestamp = entry.get(CTIMESTAMP);
			
			SqlStatement sqlStatement = 
					new SqlStatement.Builder()
					.insert(TABLENAME)
					.colValue(CSTR1,str1)
					.colValue(CSTR2, str2)
					.colValue(CNUMBER, number)
					.colValue(CDATE, date)
					.colValue(CTIME, time)
					.colValue(CTIMESTAMP, timestamp)
					.build();
			
			int count = mapper.insert(sqlStatement);
			assertEquals(1, count);
		}
		
		{
			String str1 = (String) itemSet1.get(0).get(CSTR1);
			SqlStatement sqlStatement = 
					new SqlStatement.Builder()
					.select("*")
					.from(TABLENAME)
					.where(CSTR1, SqlStatement.OPERATOR.EQ, SqlStatement.toVV(str1))
					.build();
			
			List<Map<String,Object>> result = 
					mapper.select(sqlStatement);
			assertEquals(1, result.size());
			
			java.sql.Timestamp timestampResult = (java.sql.Timestamp)(result.get(0).get(CTIMESTAMP));
			java.sql.Timestamp timestampOrg = (java.sql.Timestamp)(itemSet1.get(0).get(CTIMESTAMP));
			assertEquals(timestampOrg.getTime(), timestampResult.getTime());
		}
		
		dropTables();
	}
	
	@Test
	public void testInsertMulti() throws Exception {
		createTables();
		List<Object> colNames = new ArrayList<Object>();
		List<List<Object>> records = new ArrayList<List<Object>>();
		
		int i=0;
		for(Map<String,Object> entry : itemSet1) {
			List<Object> record = new ArrayList<Object>();
			String str1 = (String) entry.get(CSTR1);
			String str2 = (String) entry.get(CSTR2);
			Object number = entry.get(CNUMBER);
			// binary
			// blob
			// clob
			Object date = entry.get(CDATE);
			Object time = entry.get(CTIME);
			Object timestamp = entry.get(CTIMESTAMP);
			if(i==0) {
				colNames.add(CSTR1);
				colNames.add(CSTR2);
				colNames.add(CNUMBER);
				// binary
				// blob
				// clob
				colNames.add(CDATE);
				colNames.add(CTIME);
				colNames.add(CTIMESTAMP);
			}
			i++;
			
			record.add(str1);
			record.add(str2);
			record.add(number);
			// binary
			// blob
			// clob
			record.add(date);
			record.add(time);
			record.add(timestamp);
			
			records.add(record);
		}
		{
			SqlStatement sqlParameter = 
					new SqlStatement.Builder()
					.insert(TABLENAME)
					.records(colNames, records)
					.build();
			
			int count = mapper.insertMultipleItems(mapper.getClassName()+".insertMultipleItems", sqlParameter.clone());
			assertEquals(itemCount, count);
		}
		
		{
			String str1 = (String) itemSet1.get(0).get(CSTR1);
			SqlStatement sqlParameter = 
					new SqlStatement.Builder()
					.select("*")
					.from(TABLENAME)
					.where(CSTR1, SqlStatement.OPERATOR.EQ, SqlStatement.toVV(str1))
					.build();
			
			List<Map<String,Object>> result = 
					mapper.select(mapper.getClassName()+".select", sqlParameter.clone());
			assertEquals(1, result.size());
			
			java.sql.Timestamp timestampResult = (java.sql.Timestamp)(result.get(0).get(CTIMESTAMP));
			java.sql.Timestamp timestampOrg = (java.sql.Timestamp)(itemSet1.get(0).get(CTIMESTAMP));
			assertEquals(timestampOrg.getTime(), timestampResult.getTime());
		}
		
		dropTables();
	}
}
