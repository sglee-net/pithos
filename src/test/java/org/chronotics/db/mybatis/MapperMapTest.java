package org.chronotics.db.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = {org.chronotics.pithos.Application.class})
//public class MapperMapTest {
//	public static String SESSION_ORACLE = "sqlSessionSimpleOracle";
//	public static String SESSION_MYSQL = "sqlSessionSimpleMySQL";
//	
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
//	@Rule
//	public ExpectedException exceptions = ExpectedException.none();
//	
//	@Resource(name = "mapperMap")
//	private Mapper mapper;
//
//	private void createTables() {
//		dropTables();
//		
//		// Oracle
//		{
//			String key = SESSION_ORACLE;
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
//			"	c9 TIMESTAMP NULL," + 
//			"	PRIMARY KEY (c0)" + 
//			");";
//			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
//			queryParameter.put(Mapper.statement,statement);
//			mapper.doStatement(key, mapper.getClassName()+".doStatement",queryParameter);
//		}
//		
//		// MySQL
//		{
//			String key = SESSION_MYSQL;
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
//			mapper.doStatement(key, mapper.getClassName()+".doStatement",queryParameter);
//		}
//	}
//	
//	private void dropTables() {
//		{
//			String key = SESSION_ORACLE;
//			String statement=
//			"DROP TABLE IF EXISTS " + TABLE1;
//			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
//			queryParameter.put(Mapper.statement,statement);
//			mapper.doStatement(key,mapper.getClassName()+".doStatement",queryParameter);
//		}
//		
//		{
//			String key = SESSION_MYSQL;
//			String statement=
//			"DROP TABLE IF EXISTS " + TABLE1;
//			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
//			queryParameter.put(Mapper.statement,statement);
//			mapper.doStatement(key,mapper.getClassName()+".doStatement",queryParameter);
//		}
//	}
//	
//	@Test
//	public void testConnection() {
//		MapperMap mapperMap = (MapperMap)mapper;
//		if(mapperMap.size() == 0) {
//			System.out.println("map is empty");
//			assertTrue(mapperMap.size() != 0 );
//			return;
//		}
//		
//		createTables();
//		
//		// select
//		{
//			String key = SESSION_ORACLE;
//			String str = "0";
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
//			whereCondition.add(Mapper.toVV(str)); 
//			
//			queryParameter.put(Mapper.selectColumn, selectColumn);
//			queryParameter.put(Mapper.tableName, tableName);
//			queryParameter.put(Mapper.whereCondition, whereCondition);
//	
//			List<Map<String,Object>> result = 
//					mapper.select(key, mapper.getClassName()+".select", queryParameter);
//			assertEquals(1, result.size());
////			assertTrue(result.size() == 0 | result.size() >= 1);
//		}
//		
//		
//		{			
//			String key = SESSION_MYSQL;
//			String str = "0";
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
//			whereCondition.add(Mapper.toVV(str)); 
//			
//			queryParameter.put(Mapper.selectColumn, selectColumn);
//			queryParameter.put(Mapper.tableName, tableName);
//			queryParameter.put(Mapper.whereCondition, whereCondition);
//	
//			List<Map<String,Object>> result = 
//					mapper.select(key, mapper.getClassName()+".select", queryParameter);
//			assertEquals(1, result.size());
////			assertTrue(result.size() == 0 | result.size() >= 1);
//		}
//		
//		dropTables();
//	}
//}
