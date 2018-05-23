package org.chronotics.pithos;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.chronotics.db.mybatis.MapperMySql;
import org.chronotics.db.mybatis.SqlStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PithoCommandLineRunner implements CommandLineRunner {
	private static final Logger logger = 
			LoggerFactory.getLogger(PithoCommandLineRunner.class);
	
	@Override
	public void run(String... arg0) throws Exception {
		
		// test code
		if(mapper == null) {
			logger.error("mapper is null");
			return;
		}
		
//		SqlStatement sqlStatement = null;
//		try {
//			sqlStatement = new SqlStatement.Builder()
//			.select("*")
//			.from("user")
//			.build();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		if(sqlStatement != null) {
//			Map<String, Object> result = 
//					mapper.selectOne(sqlStatement);
//			if(result != null) {
//				System.out.println(result.size());
//			}
//		}
		
		createTables();
		
		int resultCount = 0;
		resultCount = insertMultipleItems();
		logger.info("{} items are inserted",resultCount);
		
		resultCount = 0;
		for(int i=0; i<itemCount; i++) {
			String str1 = (String) itemSet1.get(i).get(CSTR1);
			SqlStatement sqlStatement = 
					new SqlStatement.Builder()
					.select("*")
					.from(TABLENAME)
					.where(CSTR1, SqlStatement.OPERATOR.EQ, SqlStatement.toVV(str1))
					.build();
			
			List<Map<String,Object>> result = 
					mapper.selectList(sqlStatement);
			if(result != null) {
				resultCount += result.size();
//				logger.info("result.size() {}",result.size());
			}
		}
		logger.info("the number of select result is {}, which expected value is {}",resultCount,itemCount);
		
		dropTables();
	}
	
	@Resource(name = "mapperSimpleMySql")
	private MapperMySql mapper;

	public static String TABLENAME = "table1";
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
	
	public static List<Map<String,Object>> itemSet1 = 
			new ArrayList<Map<String,Object>>();
	public static int itemCount = 100;
	
	private void createTables() {
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
			Map<Object,Object> sqlStatement = new LinkedHashMap<Object,Object>();
			sqlStatement.put(SqlStatement.statement,statement);
			mapper.doStatement(sqlStatement);
		}
	}
	
	private void dropTables() {
		{
			String statement=
			"DROP TABLE IF EXISTS " + TABLENAME;
			Map<Object,Object> sqlStatement = new LinkedHashMap<Object,Object>();
			sqlStatement.put(SqlStatement.statement,statement);
			mapper.doStatement(sqlStatement);
		}
	}
	
	private int insertMultipleItems() throws Exception {
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
		
		SqlStatement sqlStatement = 
				new SqlStatement.Builder()
				.insert(TABLENAME)
				.records(colNames, records)
				.build();
		return mapper.insertMultipleItems(sqlStatement);
	}
}

