package org.chronotics.db.mybatis;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository("mapperMap")
public class MapperMap extends Mapper {
	
	private Map<String, SqlSession> sqlSessionMap = new ConcurrentHashMap<String, SqlSession>();
	
	public void setSqlSessionMap(Map<String, SqlSession> object) {
		sqlSessionMap.putAll(object);
	}
	
	/**
	 * getSqlSession
	 * get SqlSession with a key 
	 * from Map<String, SqlSession> sqlSessionMap
	 */
	@Override
	public SqlSession getSqlSession(String key) {
		return sqlSessionMap.get(key);
	}
	
	@Override
	public SqlSession getSqlSession() {
		assert(false);
		return null;
	}
	
	public int size() {
		return sqlSessionMap.size();
	}
	
	@Override
	public Map<String, Object> selectOne(String key, String statement, Map<Object, Object> parameter) {
		return getSqlSession(key).selectOne(statement, parameter);
	}

	@Override
	public List<Map<String, Object>> select(String key, String statement, Map<Object, Object> parameter) {
		return getSqlSession(key).selectList(statement,parameter);
	}
	@Override
	public List<Map<String,Object>> selectWithStatement(String key, String statement) {
		return getSqlSession(key).selectOne(statement);
	}

	@Override
	public int insert(String key, String statement, Map<Object, Object> parameter) {
		return getSqlSession(key).insert(statement,parameter);
	}
	
	@Override
	public int insertWithStatement(String key, String statement) {
		return getSqlSession(key).insert(statement);
	}

	@Override
	public int update(String key, String statement, Map<Object, Object> parameter) {
		return getSqlSession(key).update(statement, parameter);
	}
	
	@Override
	public int updateWithStatement(String key, String statement) {
		return getSqlSession(key).update(statement);
	}

	@Override
	public int delete(String key, String statement, Map<Object, Object> parameter) {
		return getSqlSession(key).delete(statement, parameter);
	}
	
	@Override
	public int deleteWithStatement(String key, String statement) {
		return getSqlSession(key).delete(statement);
	}

	@Override
	public int insertMultipleItems(String key, String statement, Map<Object,Object> parameters) {
		return getSqlSession(key).insert(statement, parameters);
	}

//	@Override
//	public int updateMultipleItems(String key, String statement, Map<Object,Object> parameters) {
//		return getSqlSession(key).update(statement, parameters);
//	}
	
	@Override
	public int doStatement(String key, String statement, Map<Object,Object> parameters) {
		return getSqlSession(key).update(statement, parameters);
	}
}
