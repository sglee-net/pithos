package org.chronotics.db.mybatis.app.mapper;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.chronotics.db.mybatis.Mapper;
import org.springframework.stereotype.Repository;

@Repository("mapperUser")
public class MapperUser implements IMapperUser {
	final String className = this.getClass().getName();//"org.chronotics.db.mybatis.imp.mapper.MapperUser.";

	@Resource(name = "mapperSimple")
	private Mapper mapper;

	@Override
	public Map<String,Object> selectOne(Map<Object,Object> parameter) {
		return mapper.selectOne(className + ".select", parameter);
	}
	
	@Override
	public List<Map<String,Object>> select(Map<Object,Object> parameter) {
		return mapper.select(className + ".selectList", parameter);
	}

	@Override
	public int insert(Map<Object, Object> parameter) {
		return mapper.insert(className + ".insert", parameter);
	}

	@Override
	public int update(Map<Object, Object> parameter) {
		return mapper.update(className + ".update", parameter);
	}

	@Override
	public int delete(Map<Object, Object> parameter) {
		return mapper.delete(className + ".delete", parameter);
	}
}
