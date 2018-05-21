package org.chronotics.db.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
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
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {org.chronotics.pithos.Application.class})
//public class MultipleMapperTest {
//	// table name
//	public static String TABLE1 = "table1";
////	public static String TABLE2 = "table2";
//			
//	// public static variable matches with COLUMN in DB
//	public static String CID = "c0";
//	public static String CSTR1 = "c1";
//	public static String CSTR2 = "c2";
//	public static String CNUMBER = "c3";
//	public static String CVARBINARY = "c4";
//	public static String CBLOB = "c5";
//	public static String CCLOB = "c6";
//	public static String CDATE = "c7";
//	public static String CTIME = "c8";
//	public static String CTIMESTAMP = "c9";
//	
//	// for Table1
//	public static List<Map<String,Object>> itemSet1 = 
//			new ArrayList<Map<String,Object>>();
//	public static int itemCount = 100;
//	
//	// for Table2
//	public static List<Map<String,Object>> itemSet2 = 
//			new ArrayList<Map<String,Object>>();
//	private void createTables() {
//		dropTables();
//		
////		{
////			String statement=
////			"CREATE TABLE \"SYSTEM\".\"TABLE1\" \n" + 
////			"   (	\"C0\" NUMBER(*,0) NOT NULL ENABLE, \n" + 
////			"	\"C1\" VARCHAR2(20 BYTE), \n" + 
////			"	\"C2\" VARCHAR2(20 BYTE), \n" + 
////			"	\"COLUMN1\" NUMBER, \n" + 
////			"	\"C4\" BFILE, \n" + 
////			"	\"C5\" BLOB, \n" + 
////			"	\"C6\" CLOB, \n" + 
////			"	\"C7\" DATE, \n" + 
////			"	\"C8\" DATE, \n" + 
////			"	\"C9\" TIMESTAMP (6), \n" + 
////			"	 CONSTRAINT \"TABLE1_PK\" PRIMARY KEY (\"C0\")\n" + 
////			"  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 \n" + 
////			"  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645\n" + 
////			"  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)\n" + 
////			"  TABLESPACE \"SYSTEM\"  ENABLE\n" + 
////			"   ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING\n" + 
////			"  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645\n" + 
////			"  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)\n" + 
////			"  TABLESPACE \"SYSTEM\" \n" + 
////			" LOB (\"C5\") STORE AS BASICFILE (\n" + 
////			"  TABLESPACE \"SYSTEM\" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION \n" + 
////			"  NOCACHE LOGGING \n" + 
////			"  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645\n" + 
////			"  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) \n" + 
////			" LOB (\"C6\") STORE AS BASICFILE (\n" + 
////			"  TABLESPACE \"SYSTEM\" ENABLE STORAGE IN ROW CHUNK 8192 RETENTION \n" + 
////			"  NOCACHE LOGGING \n" + 
////			"  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645\n" + 
////			"  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)) ;";
////
//////			"CREATE TABLE "+ TABLE1 +" (" + 
//////			"	c0 BIGINT(20) unsigned NOT NULL AUTO_INCREMENT," + 
//////			"	c1 VARCHAR(255) NULL," + 
//////			"	c2 VARCHAR(255) NULL," + 
//////			"	c3 FLOAT NULL default '0'," + 
//////			"	c4 VARBINARY(255) NULL," + 
//////			"	c5 BLOB NULL," + 
//////			"	c6 TEXT NULL," + 
//////			"	c7 DATE NULL," + 
//////			"	c8 TIME NULL," + 
//////			"	c9 TIMESTAMP NULL," + 
//////			"	PRIMARY KEY (c0)" + 
//////			");";
////			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
////			queryParameter.put(Mapper.statement,statement);
////			mapperOracle.doStatement(mapperOracle.getClassName()+".doStatement",queryParameter);
////		}
//		
//		{
//			String statement=
//			"CREATE TABLE "+ TABLE1 +" (" + 
//			"	c0 BIGINT(20) unsigned NOT NULL AUTO_INCREMENT," + 
//			"	c1 VARCHAR(255) NULL," + 
//			"	c2 VARCHAR(255) NULL," + 
//			"	c3 FLOAT NULL default '0'," + 
//			"	c4 VARBINARY(255) NULL," + 
//			"	c5 BLOB NULL," + 
//			"	c6 TEXT NULL," + 
//			"	c7 DATE NULL," + 
//			"	c8 TIME NULL," + 
//			"	c9 TIMESTAMP(6) NULL," + 
//			"	PRIMARY KEY (c0)" + 
//			");";
//			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
//			queryParameter.put(Mapper.statement,statement);
//			mapperMySQL.doStatement(mapperMySQL.getClassName()+".doStatement",queryParameter);
//		}
//
//	}
//	
//	private void dropTables() {
////		{
////			String statement=
////			"drop table \"SYSTEM\"." + "\"" + TABLE1 + "\"" + " PURGE";
//////			"DROP TABLE " + TABLE1;
////			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
////			queryParameter.put(Mapper.statement,statement);
////			mapperOracle.doStatement(mapperOracle.getClassName()+".doStatement",queryParameter);
////		}
//		
//		{
//			String statement=
//			"DROP TABLE IF EXISTS " + TABLE1;
//			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
//			queryParameter.put(Mapper.statement,statement);
//			mapperMySQL.doStatement(mapperMySQL.getClassName()+".doStatement",queryParameter);
//		}
//	}
//
//	private int insertMultipleItems(Mapper mapper) {
//		
//		Map<Object,Object> queryParameter = new HashMap<Object,Object>();
//		
//		List<Object> tableName = new ArrayList<Object>();
//		tableName.add(TABLE1);
//		
//		List<Object> variableNameForInsert = new ArrayList<Object>();
//		variableNameForInsert.add(CSTR1);
//		variableNameForInsert.add(CSTR2);
//		variableNameForInsert.add(CNUMBER);
//		// binary
//		// blob
//		// clob
//		variableNameForInsert.add(CDATE);
//		variableNameForInsert.add(CTIME);
//		variableNameForInsert.add(CTIMESTAMP);
//		
//		List<Object> variableValueForInsert = new ArrayList<Object>();
//		for(Map<String,Object> entry : itemSet1) {
//			List<Object> variables = new ArrayList<Object>();
//			
//			String str1 = (String) entry.get(CSTR1);
//			String str2 = (String) entry.get(CSTR2);
//			Object number = entry.get(CNUMBER);
//			// binary
//			// blob
//			// clob
//			Object date = entry.get(CDATE);
//			Object time = entry.get(CTIME);
//			Object timestamp = entry.get(CTIMESTAMP);
//						
//			variables.add(str1);
//			variables.add(str2);
//			variables.add(number);
//			// binary
//			// blob
//			// clob
//			variables.add(date);
//			variables.add(time);
//			variables.add(timestamp);
//			
//			variableValueForInsert.add(variables);
//		}
//		
//		queryParameter.put(Mapper.tableName, tableName);
//		queryParameter.put(Mapper.variableNameForInsert, variableNameForInsert);
//		queryParameter.put(Mapper.variableValueForInsert, variableValueForInsert);
//		
//		return mapper.insertMultipleItems(mapper.getClassName()+".insertMultipleItems", queryParameter);
//	}
//	
//	private int insertItemsOneByOne(Mapper mapper) {
//		
//		int totalInsertion = 0;
//		// insert
//		for(Map<String,Object> entry : itemSet1) {
//			String str1 = (String) entry.get(CSTR1);
//			String str2 = (String) entry.get(CSTR2);
//			Object number = entry.get(CNUMBER);
//			// binary
//			// blob
//			// clob
//			Object date = entry.get(CDATE);
//			Object time = entry.get(CTIME);
//			Object timestamp = entry.get(CTIMESTAMP);
//			
//			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
//			
//			List<Object> tableName = new ArrayList<Object>();
//			tableName.add(TABLE1);
//			
//			List<Object> variableNameForInsert = new ArrayList<Object>();
//			variableNameForInsert.add(CSTR1);
//			variableNameForInsert.add(CSTR2);
//			variableNameForInsert.add(CNUMBER);
//			// binary
//			// blob
//			// clob
//			variableNameForInsert.add(CDATE);
//			variableNameForInsert.add(CTIME);
//			variableNameForInsert.add(CTIMESTAMP);
//			
//			List<Object> variableValueForInsert = new ArrayList<Object>();
//			variableValueForInsert.add(str1);
//			variableValueForInsert.add(str2);
//			variableValueForInsert.add(number);
//			// binary
//			// blob
//			// clob
//			variableValueForInsert.add(date);
//			variableValueForInsert.add(time);
//			variableValueForInsert.add(timestamp);
//			
//			queryParameter.put(Mapper.tableName, tableName);
//			queryParameter.put(Mapper.variableNameForInsert, variableNameForInsert);
//			queryParameter.put(Mapper.variableValueForInsert, variableValueForInsert);
//
//			int count = mapper.insert(mapper.getClassName()+".insert", queryParameter);
//			totalInsertion += count;
//		}
//		
//		return totalInsertion; 
//	}
//	
//	@Rule
//	public ExpectedException exceptions = ExpectedException.none();
//	
//	@Resource(name = "mapperSimpleMySQL")
//	private Mapper mapperMySQL;
//	
//	@Resource(name = "mapperSimpleOracle")
//	private Mapper mapperOracle;
//	
//	@BeforeClass
//	public static void setup() {
//		for(int i=0; i<itemCount; i++) {
//			Map<String,Object> item = 
//					new HashMap<String,Object>();
//			item.put(CSTR1, Integer.toString(i));
//			item.put(CSTR2, Integer.toString(i));
//			item.put(CNUMBER, (double)i);
//			// BINARY
//			// BLOB
//			// CLOB
//			item.put(CDATE, new java.sql.Date(new java.util.Date().getTime()));
//			item.put(CTIME, new java.sql.Time(new java.util.Date().getTime()));
//			item.put(CTIMESTAMP, new java.sql.Timestamp(new java.util.Date().getTime()));
//			itemSet1.add(item);
//		}
//		
//		for(int i=0; i<itemCount; i++) {
//			Map<String,Object> item = 
//					new HashMap<String,Object>();
//			item.put(CSTR1, Integer.toString(i));
//			item.put(CSTR2, Integer.toString(i));
//			item.put(CNUMBER, (double)i);
//			// BINARY
//			// BLOB
//			// CLOB
//			item.put(CDATE, new java.sql.Date(new java.util.Date().getTime()));
//			item.put(CTIME, new java.sql.Time(new java.util.Date().getTime()));
//			item.put(CTIMESTAMP, new java.sql.Timestamp(new java.util.Date().getTime()));
//			itemSet2.add(item);
//		}
//	}
//	
//	@Test
//	public void testConnection() {
//		createTables();
//		
//		// insert 
//		//this.insertMultipleItems(mapperOracle);
//		this.insertItemsOneByOne(mapperOracle);
//		this.insertItemsOneByOne(mapperMySQL);
//		
//		// select
//		{
//			Mapper mapper = mapperOracle;			
//			String queryStr = "0";
//			
//			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
//			
//			List<Object> tableName = new ArrayList<Object>();
//			tableName.add(TABLE1);
//			
//			List<Object> selectColumn = new ArrayList<Object>();
//			selectColumn.add("*");
//			
//			List<Object> whereCondition = new ArrayList<Object>();
//			whereCondition.add(CSTR1); 
//			whereCondition.add(Mapper.OPERATOR.EQ); 
//			whereCondition.add(Mapper.toVV(queryStr)); 
//			
//			queryParameter.put(Mapper.selectColumn, selectColumn);
//			queryParameter.put(Mapper.tableName, tableName);
//			queryParameter.put(Mapper.whereCondition, whereCondition);
//	
//			List<Map<String,Object>> result = 
//					mapper.select(mapper.getClassName()+".select", queryParameter);
////			assertEquals(1, result.size());
//			assertTrue(result.size() >= 1);
//		}		
//		
//		{
//			Mapper mapper = mapperMySQL;
//			String queryStr = "0";
//			
//			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
//			
//			List<Object> tableName = new ArrayList<Object>();
//			tableName.add(TABLE1);
//			
//			List<Object> selectColumn = new ArrayList<Object>();
//			selectColumn.add("*");
//			
//			List<Object> whereCondition = new ArrayList<Object>();
//			whereCondition.add(CSTR1); 
//			whereCondition.add(Mapper.OPERATOR.EQ); 
//			whereCondition.add(Mapper.toVV(queryStr)); 
//			
//			queryParameter.put(Mapper.selectColumn, selectColumn);
//			queryParameter.put(Mapper.tableName, tableName);
//			queryParameter.put(Mapper.whereCondition, whereCondition);
//	
//			List<Map<String,Object>> result = 
//					mapper.select(mapper.getClassName()+".select", queryParameter);
//			assertEquals(1, result.size());
//		}
//	
//		dropTables();
//	}	
//}
