package org.chronotics.db.mybatis;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

public abstract class Mapper implements IMapper {	
	/**
	 * 
	 * @param _resultSet
	 * @param count_from
	 * if(count_from <= i && i < count_to)
	 * @param count_to
	 * @return
	 * @throws JSONException
	 */
}
