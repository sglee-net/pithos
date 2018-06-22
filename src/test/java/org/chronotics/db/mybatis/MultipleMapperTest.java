package org.chronotics.db.mybatis;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
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
public class MultipleMapperTest {
	
	@Rule
	public ExpectedException exceptions = ExpectedException.none();
	
	@Resource(name = "mapperSimpleMySql")
	private MapperMySql mapperMySql;
	
	@Resource(name = "mapperSimpleOracle")
	private MapperOracle mapperOracle;
	
//	@Resource(name = "mapperMap")
//	private MapperMap mapperMap;

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
	
	public static List<Map<String,Object>> itemSetMySql = 
			new ArrayList<Map<String,Object>>();
	public static List<Map<String,Object>> itemSetOracle = 
			new ArrayList<Map<String,Object>>();

	public static int itemCount = 100;
	private void createOracleTables() {
//		IMapper mapperOracle = mapperMap.get("mapperSimpleOracle");
		
		dropOracleTables();
		{
			String statement = 
					"DROP SEQUENCE TABLE1_SEQUENCE ";
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			try {
				mapperOracle.doStatement(queryParameter);
			} catch (Exception e) {
				
			}
		}	
		{
			String statement = 
					"CREATE SEQUENCE TABLE1_SEQUENCE START WITH 1 ";
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			mapperOracle.doStatement(queryParameter);
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
			mapperOracle.doStatement(queryParameter);
		}
	}
	
	private void dropOracleTables() {
//		IMapper mapperOracle = mapperMap.get("mapperSimpleOracle");
		{
			String statement=
			"DROP TABLE " + TABLE1;
			Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			try {
				mapperOracle.doStatement(queryParameter);
			} catch (Exception e) {
				
			}
		}
	}
	
	private void createMySqlTables() {
//		IMapper mapperMySql = mapperMap.get("mapperSimpleMySql");
		dropMySqlTables();
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
			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			mapperMySql.doStatement(queryParameter);
		}

	}
	
	private void dropMySqlTables() {
//		IMapper mapperMySql = mapperMap.get("mapperSimpleMySql");
		{
			String statement=
			"DROP TABLE IF EXISTS " + TABLE1;
			Map<Object,Object> queryParameter = new HashMap<Object,Object>();
			queryParameter.put(KEYWORD.STATEMENT,statement);
			mapperMySql.doStatement(queryParameter);
		}
	}
	
	private int insertMultipleOracleItems() {
//		IMapper mapperOracle = mapperMap.get("mapperSimpleOracle");
		List<Map<String,Object>> itemSet = itemSetOracle;
		
		Map<Object,Object> queryParameter = new LinkedHashMap<Object,Object>();
		
		List<Object> insert = new ArrayList<Object>();
		insert.add(TABLE1);
		
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
		
		return mapperOracle.insertMultipleItems(queryParameter);
	}
	
	private int insertOracleItemsOneByOne() {
//		IMapper mapperOracle = mapperMap.get("mapperSimpleOracle");
		List<Map<String,Object>> itemSet = itemSetOracle;
		
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
			insert.add(TABLE1);
			
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

			int count = mapperOracle.insert(queryParameter);
			totalInsertion += count;
		}
		
		return totalInsertion; 
	}

	private int insertMultipleMySqlItems() {
//		IMapper mapperMySql = mapperMap.get("mapperSimpleMySql");
		
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
		for(Map<String,Object> entry : itemSetMySql) {
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
		
		Map<Object,Object> sqlStatement = new LinkedHashMap<Object,Object>();
		
		List<Object> insert = new ArrayList<Object>();
		insert.add(TABLE1);
		
		sqlStatement.put(COMMAND.INSERT, insert);
		sqlStatement.put(KEYWORD.COLNAMES, colNames);
		sqlStatement.put(KEYWORD.RECORDS, records);
		
		return mapperMySql.insertMultipleItems(sqlStatement);
	}
	
	private int insertMySqlItemsOneByOne() {
//		IMapper mapperMySql = mapperMap.get("mapperSimpleMySql");
		
		int totalInsertion = 0;
		// insert
		for(Map<String,Object> entry : itemSetMySql) {
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
			
			sqlStatement.put(COMMAND.INSERT, insert);
			sqlStatement.put(KEYWORD.COLNAMES, colNames);
			sqlStatement.put(KEYWORD.COLVALUES, colValues);
			
			int count = mapperMySql.insert(sqlStatement);
			totalInsertion += count;
		}
		
		return totalInsertion; 
	}
	
	@BeforeClass
	public static void setup() {
		for(int i=0; i<itemCount; i++) {
			Map<String,Object> item = 
					new HashMap<String,Object>();
			item.put(CSTR1, Integer.toString(i));
			item.put(CSTR2, Integer.toString(i));
			item.put(CNUMBER, (double)i);
			// BINARY
			// BLOB
			// CLOB
			item.put(CDATE, new java.sql.Date(new java.util.Date().getTime()));
			item.put(CTIME, new java.sql.Time(new java.util.Date().getTime()));
			item.put(CTIMESTAMP, new java.sql.Timestamp(new java.util.Date().getTime()));
			itemSetMySql.add(item);
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
			itemSetOracle.add(item);
		}
	}
	
	@Test
	public void testInsertSelectOracle() {
//		IMapper mapperOracle = mapperMap.get("mapperSimpleOracle");
		int resultCount = 0;
		createOracleTables();		
		
		resultCount = this.insertOracleItemsOneByOne();
		assertEquals(itemCount, resultCount);
		
		dropOracleTables();
		createOracleTables();
		
		resultCount = this.insertMultipleOracleItems();
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
			where.add(OPERATOR.EQ);
			where.add(SqlStatement.toVV(Integer.toString(i)));
			Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
			whereCondition.put(COMMAND.WHERE, where);
			
			queryParameter.put(COMMAND.SELECT, select);
			queryParameter.put(COMMAND.FROM, from);
			queryParameter.put(COMMAND.WHERECONDITION, whereCondition);
	
			List<Map<String,Object>> result = 
					mapperOracle.selectList(queryParameter);
			assertEquals(1, result.size());
		}
		dropOracleTables();
	}
	@Test
	public void testInsertSelectMySql() {
//		IMapper mapperMySql = mapperMap.get("mapperSimpleMySql");
		
		int resultCount = 0;
		
		createMySqlTables();
		
		resultCount = this.insertMySqlItemsOneByOne();
		assertEquals(itemCount, resultCount);
		
		dropMySqlTables();
		
		createMySqlTables();
		
		resultCount = this.insertMultipleMySqlItems();
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
			where.add(OPERATOR.EQ);
			where.add(SqlStatement.toVV(Integer.toString(i)));
			Map<String,Object> whereCondition = new LinkedHashMap<String,Object>();
			whereCondition.put(COMMAND.WHERE, where);
			
			queryParameter.put(COMMAND.SELECT, select);
			queryParameter.put(COMMAND.FROM, from);
			queryParameter.put(COMMAND.WHERECONDITION, whereCondition);
	
			List<Map<String,Object>> result = 
					mapperMySql.selectList(queryParameter);
			assertEquals(1, result.size());
		}
		
		dropMySqlTables();
	}	
}
