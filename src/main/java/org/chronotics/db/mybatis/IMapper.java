package org.chronotics.db.mybatis;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

/**
 * @author SG Lee
 * @since 2013
 * @description
 * Thanks Ladybug members, Jinho and Nate.
 * This Class is mybatis mapper class.
 * Values of VO are defined as prepared statement variables (key,value) and 
 * the set of them is used as an argument of below functions.
 * Additionally, qeury condition can be represented as simple string.
 * Hash tag # is used for prepared statement variable.
 * Dollar sign $ is used for query condition that is represented as simple string.
 * ex)
 * SELECT * FROM ${table} WHERE id = #{id} ORDER BY ${orderBy}
 * SELECT ${statement}
 */

public interface IMapper {
	
	/**
	 * getSqlSession
	 * this function is for polymorphism of different DB connections
	 * @return
	 */
	public SqlSession getSqlSession(String key);
	
	public SqlSession getSqlSession();
	
	/**
	 * select
	 * this function is mapped to the query statement in a mapper file.
	 * @param key
	 * key for MapperMap<MapperId, Mapper>
	 * @param statement
	 * query statement defined in a mapper file.
	 * you should make two paths defined in .java and *mapper*.xml be equal.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return 
	 * single object with the type of key, value 
	 * key = property of a returned object, value = value of a returned object
	 */
	public Map<String,Object> selectOne(String key, String statement, Map<Object,Object> parameter);
	
	/**
	 * selectList
	 * this function is mapped to the query statement in a mapper file.
	 * @param key
	 * key for MapperMap<MapperId, Mapper>
	 * @param statement
	 * query statement defined in a mapper file.
	 * you should make two paths defined in .java and *mapper*.xml be equal.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * multiple objects with the type of key, value 
	 * key = property of a returned object, value = value of a returned object
	 */
	public List<Map<String,Object>> select(String key, String statement, Map<Object,Object> parameter);

	/**
	 * selectWithStatement
	 * this function is mapped to the query statement in a mapper file.
	 * @param key
	 * key for MapperMap<MapperId, Mapper>
	 * @param statement
	 * query statement
	 * ex) SELECT ${statement}
	 * @return
	 * multiple objects with the type of key, value 
	 * key = property of a returned object, value = value of a returned object
	 */
	public List<Map<String, Object>> selectWithStatement(String key, String statement);
	
	
	/**
	 * insert
	 * this function is mapped to the query statement in a mapper file.
	 * @param key
	 * key for MapperMap<MapperId, Mapper>
	 * @param statement
	 * query statement defined in a mapper file.
	 * you should make two paths defined in .java and *mapper*.xml be equal.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * the number of inserted elements
	 */
	public int insert(String key, String statement, Map<Object,Object> parameter);
	
	/**
	 * insertWithStatement
	 * this function is mapped to the query definition in mapper.xml 
	 * @param statement
	 * query statement
	 * ex) insert ${statement}
	 * @return
	 * the number of inserted elements
	 */
	public int insertWithStatement(String key, String statement);
	
	/**
	 * update
	 * this function is mapped to the query statement in a mapper file.
	 * @param key
	 * key for MapperMap<MapperId, Mapper>
	 * @param statement
	 * query statement defined in a mapper file.
	 * you should make two paths defined in .java and *mapper*.xml be equal.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * the number of updated elements
	 */
	public int update(String key, String statement, Map<Object,Object> parameter);
	
	/**
	 * insertWithStatement
	 * this function is mapped to the query statement in a mapper file.
	 * @param statement
	 * query statement
	 * ex) update ${statement}
	 * @return
	 * the number of updateded elements
	 */
	public int updateWithStatement(String key, String statement);
	
	/**
	 * delete
	 * this function is mapped to the query statement in a mapper file.
	 * @param key
	 * key for MapperMap<MapperId, Mapper>
	 * @param statement
	 * query statement defined in a mapper file.
	 * you should make two paths defined in .java and *mapper*.xml be equal.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * the number of deleted elements
	 */
	public int delete(String key, String statement, Map<Object,Object> parameter);
	
	/**
	 * deletedWithStatement
	 * this function is mapped to the query statement in a mapper file.
	 * @param key
	 * key for MapperMap<MapperId, Mapper>
	 * @param statement
	 * query statement
	 * ex) delete ${statement}
	 * @return
	 * the number of deleted elements
	 */
	public int deleteWithStatement(String key, String statement);
	
	/**
	 * insertMultipleItems
	 * this function is mapped to the query statement in a mapper file.
	 * @param key
	 * key for MapperMap<MapperId, Mapper>
	 * @param statement
	 * query statement defined in a mapper file.
	 * you should make two paths defined in .java and *mapper*.xml be equal.
	 * @param parameters
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 */
	public int insertMultipleItems(String key, String statement, Map<Object,Object> parameters);
	
//	/**
//	 * updateMultipleItems
//	 * this function is mapped to the query statement in a mapper file.
//	 * @param key
//	 * key for MapperMap<MapperId, Mapper>
//	 * @param statement
//	 * query statement defined in a mapper file.
//	 * you should make two paths defined in .java and *mapper*.xml be equal.
//	 * @param parameters
//	 * parameter to complete query.
//	 * parameter can be used for statement or simple string variable.
//	 * key = property of a variable, value = value of a variable
//	 * @return
//	 */
//	public int updateMultipleItems(String key, String statement, Map<Object,Object> parameters);
	
	/**
	 * doStatement
	 * @param key
	 * key for MapperMap<MapperId, Mapper>
	 * @param statement
	 * query statement defined in a mapper file.
	 * you should make two paths defined in .java and *mapper*.xml be equal.
	 * @param parameters
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 */
	public int doStatement(String key, String statement, Map<Object,Object> parameters);

}
