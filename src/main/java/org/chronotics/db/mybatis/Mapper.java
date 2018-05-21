package org.chronotics.db.mybatis;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Mapper implemented from IMapper
 * @author sglee
 * @since 2015
 * @description
 * SqlSessionTemplate of Mybatis-Spring will be injected
 * according to Beans that are defined in context_mapperBean.xml
 * SqlSessionTemplate has two types.
 * One is simple, the other is batch
 */
@Repository("mapper")
public class Mapper implements IMapper {

	private String className = this.getClass().getName();
	public String getClassName() {
		return className;
	}
	
	@Resource(name = "sqlSessionSimple")
	private SqlSession sqlSession;
	
	/**
	 * This is dummy
	 * getSqlSession(String key) is for MapperMap
	 */
	@Override
	public SqlSession getSqlSession(String key) {
		return sqlSession;
	}
	
	/**
	 * getSqlSession
	 * for polymorphism
	 * @return
	 */
	@Override
	public SqlSession getSqlSession() {
		return getSqlSession("");
	}
	
	/**
	 * setSqlSession
	 * for DI injection
	 * @param _sqlSession
	 */
	public void setSqlSession(SqlSession _sqlSession) {
		sqlSession = _sqlSession;
	}
		
	@Override
	public Map<String, Object> selectOne(String key, String statement, Map<Object, Object> parameter) {
		return this.selectOne(statement, parameter);
	}
	
	public Map<String, Object> selectOne(String statement, Map<Object, Object> parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}

	@Override
	public List<Map<String, Object>> select(String key, String statement, Map<Object, Object> parameter) {
		return this.select(statement,parameter);
	}
	
	public List<Map<String, Object>> select(String statement, Map<Object, Object> parameter) {
		return getSqlSession().selectList(statement,parameter);
	}
	
	@Override
	public List<Map<String,Object>> selectWithStatement(String key, String statement) {
		return this.selectWithStatement(statement);
	}
	
	public List<Map<String,Object>> selectWithStatement(String statement) {
		return getSqlSession().selectList(statement);
	}
	
	public List<Map<String,Object>> select(SqlStatement sqlstatement) {
		return getSqlSession().selectList(
				this.getClassName() + sqlstatement.getMapperStatement(),
				sqlstatement.getSqlParameter());
	}

	@Override
	public int insert(String key, String statement, Map<Object, Object> parameter) {
		return this.insert(statement,parameter);
	}
	
	public int insert(String statement, Map<Object, Object> parameter) {
		return getSqlSession().insert(statement,parameter);
	}
	
	@Override
	public int insertWithStatement(String key, String statement) {
		return this.insertWithStatement(statement);
	}
	
	public int insertWithStatement(String statement) {
		return getSqlSession().insert(statement);
	}

	public int insert(SqlStatement sqlstatement) {
		System.out.println(this.getClassName());
		System.out.println(sqlstatement.getMapperStatement());
		return getSqlSession().insert(
				this.getClassName() + sqlstatement.getMapperStatement(),
				sqlstatement.getSqlParameter());
	}
	
	@Override
	public int update(String key, String statement, Map<Object, Object> parameter) {
		return this.update(statement, parameter);
	}
	
	public int update(String statement, Map<Object, Object> parameter) {
		return getSqlSession().update(statement, parameter);
	}
	
	@Override
	public int updateWithStatement(String key, String statement) {
		return this.updateWithStatement(statement);
	}
	
	public int updateWithStatement(String statement) {
		return getSqlSession().update(statement);
	}
	
	public int update(SqlStatement sqlstatement) {
		return getSqlSession().update(
				this.getClassName() + sqlstatement.getMapperStatement(),
				sqlstatement.getSqlParameter());
	}

	@Override
	public int delete(String key, String statement, Map<Object, Object> parameter) {
		return this.delete(statement, parameter);
	}
	
	public int delete(String statement, Map<Object, Object> parameter) {
		return getSqlSession().delete(statement, parameter);
	}
	
	@Override
	public int deleteWithStatement(String key, String statement) {
		return this.deleteWithStatement(statement);
	}
	
	public int deleteWithStatement(String statement) {
		return getSqlSession().delete(statement);
	}
	
	public int delete(SqlStatement sqlstatement) {
		return getSqlSession().delete(
				this.getClassName() + sqlstatement.getMapperStatement(),
				sqlstatement.getSqlParameter());
	}
	
	@Override
	public int insertMultipleItems(String key, String statement, Map<Object,Object> parameters) {
		return this.insertMultipleItems(statement, parameters);
	}
	
	public int insertMultipleItems(String statement, Map<Object,Object> parameters) {
		return getSqlSession().insert(statement, parameters);
	}

//	@Override
//	public int updateMultipleItems(String key, String statement, Map<Object,Object> parameters) {
//		return this.updateMultipleItems(statement, parameters);
//	}
//	
//	public int updateMultipleItems(String statement, Map<Object,Object> parameters) {
//		return getSqlSession().update(statement, parameters);
//	}
	
	@Override
	public int doStatement(String key, String statement, Map<Object,Object> parameters) {
		return this.doStatement(statement, parameters);
	}
	
	public int doStatement(String statement, Map<Object,Object> parameters) {
		return getSqlSession().update(statement, parameters);
	}
	
	/**
	 * 
	 * @param _resultSet
	 * @param count_from
	 * if(count_from <= i && i < count_to)
	 * @param count_to
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject getJSonObject(
			List<Map<String,Object>> _resultSet, 
			int count_from, 
			int count_to) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		int i = 0;
		for(Map<String,Object> element: _resultSet) {
			if(count_from <= i && i < count_to) {
				for(Entry<String,Object> entry: element.entrySet()) {
					Object object = entry.getValue();
					jsonObject.put(entry.getKey(), object);
				}
			}
			i++;
		}

		return jsonObject;
	}
}
