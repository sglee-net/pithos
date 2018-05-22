package org.chronotics.db.mybatis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MabatisSqlSessionTest {
	
	@Rule
	public ExpectedException exceptions = ExpectedException.none();
	
	private static SqlSessionFactory sqlSessionFactory = null;
	
	
	@BeforeClass
	public static void setup() {
		
		String resource = "mybatis/config/mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	
	
	@Test
	public void testFactory() {
		assertNotNull(sqlSessionFactory);
	}
	
	@Test
	public void testSession() throws Exception {
		try {
			SqlSession session = sqlSessionFactory.openSession();
			assertNotNull(session);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testMapperSelect() {		
		SqlSession session = sqlSessionFactory.openSession();
//		try{
//			Map<Object,Object> parameterObject = new HashMap<Object,Object>();
//			parameterObject.put("no","1");
//			IMapper mapper = session.getMapper(IMapper.class);
//			Map<String,Object> rtMap = (Map<String, Object>) mapper.select(parameterObject);
//			for(Map.Entry<String, Object> entry : rtMap.entrySet()) {
//				System.out.println(entry.getKey() + ", " + entry.getValue());
//			}
//			assertEquals(4,rtMap.size());
//
//		} catch (PersistenceException e) {
//			e.printStackTrace();
//		} finally {
//			session.close();
//		}
	}
}
