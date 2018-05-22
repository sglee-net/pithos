package org.chronotics.db.mybatis;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

@Repository("mapperMap")
public class MapperMap implements IMapper {
	
	private Map<String, IMapper> mapperMap = 
			new ConcurrentHashMap<String, IMapper>();
	
	public void setMapperMap(Map<String, IMapper> object) {
		mapperMap.putAll(object);
	}
	
	public Map<String, IMapper> getMapeerMap() {
		return mapperMap;
	}
	
	/**
	 * getSqlSession
	 * get SqlSession with a key 
	 * from Map<String, SqlSession> mapperMap
	 */
//	@Override
//	public SqlSession getSqlSession(String key) {
//		return getSqlSession();
//	}
//	
//	@Override
//	public SqlSession getSqlSession() {
//		assert(false);
//		// this function should not be called
//		return null;
//	}
	
	public int size() {
		return mapperMap.size();
	}
	
	public Set<Entry<String, IMapper>> entrySet() {
		return mapperMap.entrySet();
	}
	
	public IMapper get(String key) {
		return mapperMap.get(key);
	}
	
	public Map<String, Object> selectOne(
			String key, Map<Object, Object> parameter) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).selectOne(parameter);
	}
	
	public Map<String, Object> selectOne(
			String key, SqlStatement sqlStatement) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).selectOne(sqlStatement);
	}
	
	public List<Map<String,Object>> selectList(
			String key, Map<Object, Object> parameter) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).selectList(parameter);
	}
	
	public List<Map<String,Object>> selectList(
			String key, SqlStatement sqlStatement) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).selectList(sqlStatement);
	}
	
	public List<Map<String,Object>> selectWithStatement(
			String key, String statement) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).selectWithStatement(statement);
	}

	public int insert(
			String key, Map<Object, Object> parameter) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).insert(parameter);
	}
	
	public int insert(
			String key, SqlStatement sqlStatement) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).insert(sqlStatement);
	}
	
	public int insertWithStatement(
			String key, String statement) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).insertWithStatement(statement);
	}

	public int update(
			String key, Map<Object, Object> parameter) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).update(parameter);
	}
	
	public int update(
			String key, SqlStatement sqlStatement) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).update(sqlStatement);
	}
	
	public int updateWithStatement(
			String key, String statement) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).updateWithStatement(statement);
	}

	public int delete(
			String key, Map<Object, Object> parameter) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).delete(parameter);
	}
	
	public int delete(
			String key, SqlStatement sqlStatement) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).delete(sqlStatement);
	}
	
	public int deleteWithStatement(
			String key, String statement) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).deleteWithStatement(statement);
	}

	public int insertMultipleItems(
			String key, Map<Object,Object> parameters) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).insertMultipleItems(parameters);
	}
	
	public int insertMultipleItems(
			String key, SqlStatement sqlStatement) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).insertMultipleItems(sqlStatement);
	}
	
	public int doStatement(
			String key, Map<Object,Object> parameters) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).doStatement(parameters);
	}
	
	public int doStatement(
			String key, SqlStatement sqlStatement) {
		if(get(key) == null) throw new NullPointerException();
		return get(key).doStatement(sqlStatement);
	}

	@Override
	public Map<String, Object> selectOne(Map<Object, Object> parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectOne(SqlStatement sqlStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectList(Map<Object, Object> parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectList(SqlStatement sqlStatement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> selectWithStatement(String statement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Map<Object, Object> parameter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(SqlStatement sqlStatement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertWithStatement(String statement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Map<Object, Object> parameter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(SqlStatement sqlStatement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateWithStatement(String statement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Map<Object, Object> parameter) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(SqlStatement sqlStatement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteWithStatement(String statement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertMultipleItems(Map<Object, Object> parameters) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertMultipleItems(SqlStatement sqlStatement) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doStatement(Map<Object, Object> parameters) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doStatement(SqlStatement sqlStatement) {
		// TODO Auto-generated method stub
		return 0;
	}

}
