package org.chronotics.db.mybatis;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

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
@Repository("mapperOracle")
public class MapperOracle implements IMapper {

	private String className = this.getClass().getName();
	public String getClassName() {
		return className;
	}
	
	@Resource(name = "sqlSessionSimpleOracle")
	private SqlSession sqlSession;
	
	public SqlSession getSqlSession() {
		return sqlSession;
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
	public Map<String, Object> selectOne(Map<Object, Object> parameter) {
		return getSqlSession().selectOne(
				getClassName() + ".selectOne", 
				parameter);
	}
	
	@Override
	public Map<String, Object> selectOne(SqlStatement sqlStatement) {
		return getSqlSession().selectOne(
				getClassName() + ".selectOne",
				sqlStatement.getParameter());
	}

	@Override
	public List<Map<String, Object>> selectList(Map<Object, Object> parameter) {
		return getSqlSession().selectList(
				getClassName() + ".selectList",
				parameter);
	}
	
	@Override
	public List<Map<String,Object>> selectList(SqlStatement sqlStatement) {
		return getSqlSession().selectList(
				getClassName() + ".selectList",
				sqlStatement.getParameter());
	}
	
	@Override
	public List<Map<String,Object>> selectWithStatement(String statement) {
		return getSqlSession().selectList(statement);
	}

	@Override
	public int insert(Map<Object, Object> parameter) {
		return getSqlSession().insert(
				getClassName() + ".insert",
				parameter);
	}

	@Override
	public int insert(SqlStatement sqlStatement) {
		return getSqlSession().insert(
				getClassName() + ".insert",
				sqlStatement.getParameter());
	}
	
	@Override
	public int insertWithStatement(String statement) {
		return getSqlSession().insert(statement);
	}
	
	@Override
	public int update(Map<Object, Object> parameter) {
		return getSqlSession().update(
				getClassName() + ".update", 
				parameter);
	}
	
	@Override
	public int update(SqlStatement sqlStatement) {
		return getSqlSession().update(
				getClassName() + ".update",
				sqlStatement.getParameter());
	}
	
	@Override
	public int updateWithStatement(String statement) {
		return getSqlSession().update(statement);
	}

	@Override
	public int delete(Map<Object, Object> parameter) {
		return getSqlSession().delete(
				getClassName() + ".delete", 
				parameter);
	}
	
	@Override
	public int delete(SqlStatement sqlStatement) {
		return getSqlSession().delete(
				getClassName() + ".delete", 
				sqlStatement.getParameter());
	}
		
	@Override
	public int deleteWithStatement(String statement) {
		return this.deleteWithStatement(statement);
	}

	@Override
	public int insertMultipleItems(Map<Object,Object> parameters) {
		return getSqlSession().insert(
				getClassName() + ".insertMultipleItems", 
				parameters);
	}
	
	@Override
	public int insertMultipleItems(SqlStatement sqlStatement) {
		return getSqlSession().insert(
				getClassName() + ".insertMultipleItems", //sqlstatement.getMapperStatement(),
				sqlStatement.getParameter());
	}
	
	@Override
	public int doStatement(Map<Object,Object> parameters) {
		return getSqlSession().update(
				getClassName() + ".doStatement", 
				parameters);
	}

	@Override
	public int doStatement(SqlStatement sqlStatement) {
		return getSqlSession().update(
				getClassName() + ".doStatement", 
				sqlStatement.getParameter());
	}
	
}
