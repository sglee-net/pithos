package org.chronotics.db.mybatis.app.mapper;

import java.util.List;
import java.util.Map;

public interface IMapperUser {
//	
//	public Map<String,Object> select(String statement);
	
	public Map<String,Object> selectOne(Map<Object,Object> parameter);
	
	public List<Map<String,Object>> select(Map<Object,Object> parameter);
	
	public int insert(Map<Object,Object> parameter);
	
	public int update(Map<Object,Object> parameter);
	
	public int delete(Map<Object,Object> parameter);
}
