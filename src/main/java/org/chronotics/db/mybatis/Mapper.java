package org.chronotics.db.mybatis;

import java.util.List;
import java.util.Map;

import org.chronotics.db.mybatis.SqlStatement.COMMAND;


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

public abstract class Mapper implements IMapper {
	
	@Override
	public Map<String,Object> selectOne(SqlStatement sqlStatement) throws Exception {
		Map<Object, Object> parameter = sqlStatement.getParameter();
		if(!parameter.containsKey(COMMAND.SELECT)) {
			throw new Exception("SELECT is not defined");
		}
		if(!parameter.containsKey(COMMAND.FROM)) {
			throw new Exception("SELECT is not defined");
		}
		return null;
	}
	
	@Override
	public List<Map<String,Object>> selectList(SqlStatement sqlStatement) throws Exception {
		return null;
	}
	
	@Override
	public int insert(SqlStatement sqlStatement) throws Exception {
		return 0;
	}
	
	@Override
	public int update(SqlStatement sqlStatement) throws Exception {
		return 0;
	}
	
	@Override
	public int delete(SqlStatement sqlStatement) throws Exception {
		return 0;
	}
	
	@Override
	public int insertMultipleItems(SqlStatement sqlStatement) throws Exception {
		return 0;
	}
	
	@Override
	public int doStatement(SqlStatement sqlStatement) throws Exception {
		return 0;
	}
}
