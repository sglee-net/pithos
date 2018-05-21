package org.chronotics.db.mybatis.app.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.chronotics.db.mybatis.app.mapper.MapperUser;
import org.springframework.stereotype.Repository;

@Repository("serviceUser")
public class ServiceUser implements IServiceUser {
	@Resource(name = "mapperUser")
	private MapperUser mapper;
	
	public void setMapperUser(MapperUser _mapper) {
		mapper = _mapper;
	}
	
	@Override
	public void selectListByName(String _name) {
		Map<Object,Object> parameterObject = new HashMap<Object,Object>();
		parameterObject.put("name",_name);
			
		List<Map<String,Object>> result = mapper.select(parameterObject);
		
		for(Map<String,Object> map : result) {
			for(Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey() + ", " + entry.getValue());
			}
		}
	}
}
