package org.chronotics.db.mybatis;

import java.util.List;
import java.util.Map;

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
	
	/**
 	 * @param statement
	 * query statement defined in a mapper file.
	 * you should make two paths defined in .java and *mapper*.xml be equal.
	 */

	/**
	 * select
	 * this function is mapped to the query statement in a mapper file.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return 
	 * single object with the type of key, value 
	 * key = property of a returned object, value = value of a returned object
	 */
	public Map<String,Object> selectOne(Map<Object,Object> parameter);
	
	public Map<String,Object> selectOne(SqlStatement sqlStatement);
	
	/**
	 * selectList
	 * this function is mapped to the query statement in a mapper file.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * multiple objects with the type of key, value 
	 * key = property of a returned object, value = value of a returned object
	 */
	public List<Map<String,Object>> selectList(Map<Object,Object> parameter);
	
	public List<Map<String,Object>> selectList(SqlStatement sqlStatement);

	/**
	 * selectWithStatement
	 * this function is mapped to the query statement in a mapper file.
	 * @param statement
	 * query statement
	 * ex) SELECT ${statement}
	 * @return
	 * multiple objects with the type of key, value 
	 * key = property of a returned object, value = value of a returned object
	 */
	public List<Map<String,Object>> selectWithStatement(String statement);
	
	
	/**
	 * insert
	 * this function is mapped to the query statement in a mapper file.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * the number of inserted elements
	 */
	public int insert(Map<Object,Object> parameter);
	
	public int insert(SqlStatement sqlStatement);
	
	/**
	 * insertWithStatement
	 * this function is mapped to the query definition in mapper.xml 
	 * @param statement
	 * query statement
	 * ex) insert ${statement}
	 * @return
	 * the number of inserted elements
	 */
	public int insertWithStatement(String statement);
	
	/**
	 * update
	 * this function is mapped to the query statement in a mapper file.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * the number of updated elements
	 */
	public int update(Map<Object,Object> parameter);
	
	public int update(SqlStatement sqlStatement);
	
	/**
	 * insertWithStatement
	 * this function is mapped to the query statement in a mapper file.
	 * @param statement
	 * query statement
	 * ex) update ${statement}
	 * @return
	 * the number of updateded elements
	 */
	public int updateWithStatement(String statement);
	
	/**
	 * delete
	 * this function is mapped to the query statement in a mapper file.
	 * @param parameter
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * the number of deleted elements
	 */
	public int delete(Map<Object,Object> parameter);
	
	public int delete(SqlStatement sqlStatement);
	
	/**
	 * deletedWithStatement
	 * this function is mapped to the query statement in a mapper file.
	 * @param statement
	 * query statement
	 * ex) delete ${statement}
	 * @return
	 * the number of deleted elements
	 */
	public int deleteWithStatement(String statement);
	
	/**
	 * insertMultipleItems
	 * this function is mapped to the query statement in a mapper file.
	 * @param parameters
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 * the number of inserted elements
	 */
	public int insertMultipleItems(Map<Object,Object> parameters);
		
	/**
	 * insertMultipleItems
	 * @param sqlstatement
	 * SqlStatement generated from SqlStatement.Builder()
	 * @return
	 * the number of inserted elements
	 */
	public int insertMultipleItems(SqlStatement sqlStatement);
	
	/**
	 * doStatement
	 * @param parameters
	 * parameter to complete query.
	 * parameter can be used for statement or simple string variable.
	 * key = property of a variable, value = value of a variable
	 * @return
	 */
	public int doStatement(Map<Object,Object> parameters);
	
	public int doStatement(SqlStatement sqlStatement);

}
