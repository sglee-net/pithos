package org.chronotics.db.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {org.chronotics.pithos.Application.class})
public class MapperMapTest {
	public static String SESSION_ORACLE = "mapperSimpleOracle";
	public static String SESSION_MYSQL = "mapperSimpleMySql";
	
	// table name
	public static String TABLE1 = "table1";
			
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
	
	@Rule
	public ExpectedException exceptions = ExpectedException.none();
	
	@Resource(name = "mapperMap")
	private MapperMap mapper;

	// for Table1
	public static List<Map<String,Object>> itemSet1 = 
			new ArrayList<Map<String,Object>>();
	public static int itemCount = 100;
	
	private void createTables() {
		dropTables();
		
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
//			queryParameter.put(SqlStatement.statement,statement);
//			mapper.doStatement(key, queryParameter);
//		}
		
		// MySQL
		{
			String key = SESSION_MYSQL;
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
			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
			queryParameter.put(SqlStatement.STATEMENT,statement);
			mapper.doStatement(key, queryParameter);
		}
	}
	
	private void dropTables() {
//		{
//			String key = SESSION_ORACLE;
//			String statement=
//			"DROP TABLE IF EXISTS " + TABLE1;
//			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
//			queryParameter.put(SqlStatement.statement,statement);
//			mapper.doStatement(key, queryParameter);
//		}
		
		{
			String key = SESSION_MYSQL;
			String statement=
			"DROP TABLE IF EXISTS " + TABLE1;
			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
			queryParameter.put(SqlStatement.STATEMENT,statement);
			mapper.doStatement(key, queryParameter);
		}
	}

	private int insertItemsOneByOne(IMapper mapper) throws Exception {
		
		int totalInsertion = 0;
		// insert
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
			
			// old method
			Map<Object,Object> sqlStatement = new LinkedHashMap<Object,Object>();
			
			List<Object> insert = new ArrayList<Object>();
			insert.add(TABLE1);
			
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
			
			sqlStatement.put(SqlStatement.INSERT, insert);
			sqlStatement.put(SqlStatement.COLNAMES, colNames);
			sqlStatement.put(SqlStatement.COLVALUES, colValues);
			
			int count = mapper.insert(sqlStatement);
			totalInsertion += count;
		}
		
		return totalInsertion; 
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
	public void insertSelectTest() throws Exception {
	
		assertTrue(mapper != null);
		
		MapperMap mapperMap = (MapperMap)mapper;
		if(mapperMap.size() == 0) {
			System.out.println("map is empty");
			assertTrue(mapperMap.size() != 0 );
			return;
		}
		
		createTables();
		
		this.insertItemsOneByOne(mapper.get(SESSION_MYSQL));
		this.insertItemsOneByOne(mapper.get(SESSION_ORACLE));
			
		// select
		{
			String key = SESSION_MYSQL;
			Map<Object,Object> queryParameterSelect = new LinkedHashMap<Object,Object>();
				
			List<Object> select = new ArrayList<Object>();
			select.add("*");
			
			List<Object> from = new ArrayList<Object>();
			from.add(TABLE1);
			
			List<Object> where = new ArrayList<Object>();
			where.add(CSTR1);
			where.add(SqlStatement.OPERATOR.EQ);
			where.add(SqlStatement.toVV("0"));
			Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
			whereCondition.put(SqlStatement.WHERE, where);
			
			queryParameterSelect.put(SqlStatement.SELECT, select);
			queryParameterSelect.put(SqlStatement.FROM, from);
			queryParameterSelect.put(SqlStatement.WHERECONDITION, whereCondition);
	
			List<Map<String,Object>> result = 
					mapperMap.selectList(key, queryParameterSelect);
			assertEquals(1, result.size());
			
			java.sql.Timestamp timestampResult = (java.sql.Timestamp)(result.get(0).get(CTIMESTAMP));
			java.sql.Timestamp timestampOrg = (java.sql.Timestamp)(itemSet1.get(0).get(CTIMESTAMP));
			assertEquals(timestampOrg.getTime(), timestampResult.getTime());
		}
		
		
		// select
		{
			String key = SESSION_ORACLE;
			Map<Object,Object> queryParameterSelect = new LinkedHashMap<Object,Object>();
				
			List<Object> select = new ArrayList<Object>();
			select.add("*");
			
			List<Object> from = new ArrayList<Object>();
			from.add(TABLE1);
			
			List<Object> where = new ArrayList<Object>();
			where.add(CSTR1);
			where.add(SqlStatement.OPERATOR.EQ);
			where.add(SqlStatement.toVV("0"));
			Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
			whereCondition.put(SqlStatement.WHERE, where);
			
			queryParameterSelect.put(SqlStatement.SELECT, select);
			queryParameterSelect.put(SqlStatement.FROM, from);
			queryParameterSelect.put(SqlStatement.WHERECONDITION, whereCondition);
	
			List<Map<String,Object>> result = 
					mapperMap.selectList(key, queryParameterSelect);
			assertTrue(result.size() >=1 );
//			for(Map<String,Object> e : result) {
//				for(Object obj : e.entrySet()) {
//					System.out.println(obj);
//				}
//			}
			
			oracle.sql.TIMESTAMP timestampResult = (oracle.sql.TIMESTAMP)(result.get(0).get(CTIMESTAMP.toUpperCase()));
			assertTrue(timestampResult != null);
			java.sql.Timestamp timestampOrg = (java.sql.Timestamp)(itemSet1.get(0).get(CTIMESTAMP));
			assertTrue(timestampResult != null);
//			assertEquals(timestampOrg,timestampResult);
		}
		
		dropTables();
	}
}
