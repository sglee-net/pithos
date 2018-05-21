package org.chronotics.db.mybatis.app.mapper;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.chronotics.db.mybatis.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author sglee
 * You are recommended to use MapperMap 
 * instead of using MapperMySQL or MapperOracle
 * You inject multiple Mapper with context-*.xml
 * First of all, you have to define dataSource,
 * and then, sqlSessionFactoryBean, sqlSession.
 * You finally have to inject sqlSession to MapperMap.
 */
@Repository("mapperOracle")
public class MapperOracle extends Mapper {
	@Resource(name = "sqlSessionSimpleOracle")
	private SqlSession sqlSession;
	
	@Override
	public SqlSession getSqlSession() {
		return sqlSession;
	}

	/**
	 * setSqlSession
	 * for DI injection
	 * @param _sqlSession
	 */
	@Override
	public void setSqlSession(SqlSession _sqlSession) {
		sqlSession = _sqlSession;
	}
	
	@Override
	public Map<String, Object> selectOne(String statement, Map<Object, Object> parameter) {
		return getSqlSession().selectOne(statement, parameter);
	}

	@Override
	public List<Map<String, Object>> select(String statement, Map<Object, Object> parameter) {
		return getSqlSession().selectList(statement,parameter);
	}
	
	@Override
	public Map<String,Object> selectWithStatement(String statement) {
		return getSqlSession().selectOne(statement);
	}

	@Override
	public int insert(String statement, Map<Object, Object> parameter) {
		return getSqlSession().insert(statement,parameter);
	}
	
	@Override
	public int insertWithStatement(String statement) {
		return getSqlSession().insert(statement);
	}

	@Override
	public int update(String statement, Map<Object, Object> parameter) {
		return getSqlSession().update(statement, parameter);
	}
	
	@Override
	public int updateWithStatement(String statement) {
		return getSqlSession().update(statement);
	}

	@Override
	public int delete(String statement, Map<Object, Object> parameter) {
		return getSqlSession().delete(statement, parameter);
	}
	
	@Override
	public int deleteWithStatement(String statement) {
		return getSqlSession().delete(statement);
	}

	@Override
	public int insertMultipleItems(String statement, Map<Object,Object> parameters) {
		return getSqlSession().insert(statement, parameters);
	}

	@Override
	public int updateMultipleItems(String statement, Map<Object,Object> parameters) {
		return getSqlSession().update(statement, parameters);
	}
}
