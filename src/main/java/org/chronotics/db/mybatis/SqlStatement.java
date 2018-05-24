package org.chronotics.db.mybatis;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SqlStatement {
	public static class KEYWORD {
		public static String RESULTSET = "resultSet";
		public static String COLNAMES = "colNames";
		public static String COLVALUES = "colValues";
		public static String RECORDS = "records";
	}
	
//	public static class COMMAND {
		public static String STATEMENT = "statement";
		public static String SELECT = "select";
		public static String INSERT = "insert";
		public static String UPDATE = "update";
		public static String DELETE = "delete";
		public static String FROM = "from";
		public static String WHERECONDITION = "whereCondition";
		public static String WHERE = "where";
		public static String WHERENOT = "whereNot";
		public static String AND = "and";
		public static String ANDNOT = "andNot";
		public static String OR = "or";
		public static String ORNOT = "orNot";
		public static String ORDERBY = "orderBy";
		public static String ORDERBYASCORDEC = "orderByAscOrDec";
		public static String ASC = "ASC";
		public static String DESC= "DESC";
		public static String SET = "set";
		public static String INNERJOIN = "innerJoin";
		public static String LEFTJOIN = "leftJoin";
		public static String RIGHTJOIN = "rightJoin";
		public static String FULLOUTERJOIN = "fullOuterJoin";
		public static String ON = "on";
//	}
	
	protected static Map<String, OPERATOR> operatorMap = new HashMap<String, OPERATOR>();
	public static enum OPERATOR {
		PARENTHESIS_LEFT("("),
		PARENTHESIS_RIGHT(")"),
		COMMA(","),
		AND("AND"),
		OR("OR"),
		NOT("NOT"),
		EQ("="),
		LT("<"),
		LE("<="),
		NE("<>"),
		GE(">="),
		GT(">"),
		BETWEEN("BETWEEN"),
		LIKE("LIKE"),
		IN("IN");
		private String str;
		OPERATOR(String _arg) {
			str = _arg;
			operatorMap.put(_arg,this);
		}
		public String toString() {
			return str;
		}
	}
	
	public static OPERATOR getOperator(String _name) {
		return operatorMap.get(_name);
	}
	
	/**
	 * toVV
	 * @param _object
	 * @return
	 * convert input Object to variable value Object
	 */
	public static Object toVV(Object _object) {
		if(_object instanceof String) {
			String temp = (String)_object;
			return new String("\'" + temp + "\'");
		} else if(_object instanceof Number) {
			return _object;
		} else {
			return _object;
		}
	}
	private Map<Object,Object> sqlParameter = new HashMap<Object,Object>();
	
	public Map<Object,Object> getParameter() {
		return sqlParameter;
	}
	
	public static class Builder {
		// for final return value
		private Map<Object,Object> sqlParameter = new HashMap<Object,Object>();
		// for temporary
		private List<Object> selectList = null;
		private List<Object> fromList = null;
		private List<Object> insertList = null;
		private List<Object> updateList = null;
		private List<Object> deleteList = null;
		private List<Object> whereList = null;
		private Map<String,Object> whereMap = null;
		private List<Object> colNames = null;
		private List<Object> colValues = null;
		private List<Object> records = null;
		
		public Builder() {}
		
		public SqlStatement build() {
			SqlStatement sqlStatement = new SqlStatement();
			if(whereMap != null) {
				if(!whereMap.isEmpty()) {
					sqlParameter.put(WHERECONDITION, whereMap);
				}
			}
			
			if(colNames != null && colValues !=null) {
				if(!colNames.isEmpty() && !colValues.isEmpty()) {
					assert(colNames.size() == colValues.size());
					sqlParameter.put(KEYWORD.COLNAMES, colNames);
					sqlParameter.put(KEYWORD.COLVALUES, colValues);
				}
			}
			
			if(colNames != null && records != null) {
				if(!colNames.isEmpty() && !records.isEmpty()) {
					sqlParameter.put(KEYWORD.COLNAMES, colNames);
					sqlParameter.put(KEYWORD.RECORDS, records);
				}
			}

			sqlStatement.sqlParameter =	
					this.sqlParameter
					.entrySet()
					.stream()
					.collect(Collectors.toMap(
							Map.Entry::getKey, 
							Map.Entry::getValue));
			
			return sqlStatement;
		}
		
		public Builder select(Object ...object) {
			if(selectList == null) {
				selectList = new ArrayList<Object>();
			}
			selectList.clear();
			for(Object e: object) {
				assert(e instanceof String);
				selectList.add(e);
			}
			sqlParameter.put(SELECT, selectList);
			return this;
		}
		
		public Builder from(Object object) {
			if(fromList == null) {
				fromList = new ArrayList<Object>();
			}
			fromList.clear();
			fromList.add(object);
			sqlParameter.put(FROM, fromList);
			return this;
		}
		
		public Builder insert(Object object) {
			if(insertList == null) {
				insertList = new ArrayList<Object>();
			}
			insertList.clear();
			insertList.add(object);
			sqlParameter.put(INSERT, insertList);
			return this;
		}
		
		public Builder update(Object object) {
			if(updateList == null) {
				updateList = new ArrayList<Object>();
			}
			updateList.clear();
			updateList.add(object);
			sqlParameter.put(UPDATE, updateList);
			return this;
		}
		
		public Builder delete(Object object) {
			if(deleteList == null) {
				deleteList = new ArrayList<Object>();
			}
			deleteList.clear();
			deleteList.add(object);
			sqlParameter.put(DELETE, deleteList);
			return this;
		}
		
		public void addObjectToWhereList(Object object) {
			if(object instanceof List) {
				if(object instanceof Builder) {
					this.addObjectToWhereList("(");
				}
				for(Object e: (List<?>)object) {
					this.addObjectToWhereList(e);
				}
				if(object instanceof Builder) {
					this.addObjectToWhereList(")");
				}
			} else {
				whereList.add(object);
			}
		}
		
		public void clearWhereList() {
			whereList.clear();
		}
		
		public Builder where(
				Object leftOperand, 
				OPERATOR operator, 
				Object rightOperand) {
			if(whereList == null) {
				whereList = new ArrayList<Object>();
			}
			if(whereMap == null) {
				whereMap = new LinkedHashMap<String,Object>();
			}
			
			this.clearWhereList();
			this.addObjectToWhereList(leftOperand);
			this.addObjectToWhereList(operator);
			this.addObjectToWhereList(rightOperand);
			assert(whereMap.get(WHERE) == null);
			assert(whereMap.get(WHERENOT) == null);
			
			whereMap.put(WHERE, whereList);
			// don't clear
			//this.clearWhereList();
			return this;
		}
		
		public Builder whereNot(
				Object leftOperand, 
				OPERATOR operator, 
				Object rightOperand) {
			this.clearWhereList();
			this.addObjectToWhereList(leftOperand);
			this.addObjectToWhereList(operator);
			this.addObjectToWhereList(rightOperand);
			assert(whereMap.get(WHERE) == null);
			assert(whereMap.get(WHERENOT) == null);
			
			whereMap.put(WHERENOT, whereList);
			// don't clear
			//this.clearWhereList();
			return this;
		}
		
		public Builder and(
				Object leftOperand, 
				OPERATOR operator, 
				Object rightOperand) {
			this.clearWhereList();
			this.addObjectToWhereList(leftOperand);
			this.addObjectToWhereList(operator);
			this.addObjectToWhereList(rightOperand);
			
			whereMap.put(AND, whereList);
			// don't clear
			//this.clearWhereList();
			return this;
		}
		
		public Builder andNot(
				Object leftOperand, 
				OPERATOR operator, 
				Object rightOperand) {
			this.clearWhereList();
			this.addObjectToWhereList(leftOperand);
			this.addObjectToWhereList(operator);
			this.addObjectToWhereList(rightOperand);
			
			whereMap.put(ANDNOT, whereList);
			// don't clear
			//this.clearWhereList();
			return this;
		}
		
		public Builder or(
				Object leftOperand, 
				OPERATOR operator, 
				Object rightOperand) {
			this.clearWhereList();
			this.addObjectToWhereList(leftOperand);
			this.addObjectToWhereList(operator);
			this.addObjectToWhereList(rightOperand);
			
			whereMap.put(OR, whereList);
			// don't clear
			//this.clearWhereList();
			return this;
		}
		
		public Builder orNot(
				Object leftOperand, 
				OPERATOR operator, 
				Object rightOperand) {
			this.clearWhereList();
			this.addObjectToWhereList(leftOperand);
			this.addObjectToWhereList(operator);
			this.addObjectToWhereList(rightOperand);
			
			whereMap.put(ORNOT, whereList);
			// don't clear
			//this.clearWhereList();
			return this;
		}
				
		public Builder colValue(
				String name,
				Object value) {
			if(colNames == null) {
				colNames = new ArrayList<Object>();
			}
			if(colValues == null) {
				colValues = new ArrayList<Object>();
			}
			
			this.colNames.add(name);
			this.colValues.add(value);
			return this;
		}
		
		public Builder colValues(
				List<Object> _colNames,
				List<Object> _colValues) {
			if(colNames == null) {
				colNames = new ArrayList<Object>();
			}
			if(colValues == null) {
				colValues = new ArrayList<Object>();
			}
			
			colNames.addAll(_colNames);
			colValues.addAll(_colValues);
			return this;
		}
		
		public Builder records(
				List<Object> _colNames,
				List<List<Object>> _records) {
			if(colNames == null) {
				colNames = new ArrayList<Object>();
			}
			colNames.addAll(_colNames);
			
			if(records == null) {
				records = new ArrayList<Object>();
			}

			for(List<Object> record: _records) {
				List<Object> element = new ArrayList<Object>(record);
				records.add(element);
			}
			
			return this;
		}
	}
	
	static Object createObject(String _className) 
			throws ClassNotFoundException, 
			NoSuchMethodException, 
			SecurityException, 
			InstantiationException, 
			IllegalAccessException, 
			IllegalArgumentException, 
			InvocationTargetException {
		Class<?> c = Class.forName(_className);
		Constructor<?> cons = c.getConstructor(String.class);
		Object object = cons.newInstance();
		return object;
	}
	
	public static List<Object> getColNames(String _json) throws JSONException {
		List<Object> rtObject = null;
		JSONObject jsonObject = new JSONObject(_json);
		Object obj = jsonObject.get(SqlStatement.KEYWORD.COLNAMES);
		if(obj instanceof JSONArray == false) {
			throw new JSONException("There is no SqlStatement.COLNAMES");
		}
		rtObject = new ArrayList<Object>();
		JSONArray array = (JSONArray)obj;
		for(int i=0; i<array.length(); i++) {
			rtObject.add(array.get(i));
		}
		return rtObject;
	}
	
	public static List<Object> getColValues(String _json) throws JSONException {
		List<Object> rtObject = null;
		JSONObject jsonObject = new JSONObject(_json);
		Object obj = jsonObject.get(SqlStatement.KEYWORD.COLVALUES);
		if(obj instanceof JSONArray == false) {
			throw new JSONException("There is no SqlStatement.COLVALUES");
		}
		rtObject = new ArrayList<Object>();
		JSONArray array = (JSONArray)obj;
		for(int i=0; i<array.length(); i++) {
			rtObject.add(array.get(i));
		}
		return rtObject;
	}
	
	public static List<List<Object>> getRecords(String _json) throws JSONException {
		List<List<Object>> rtObject = null;
		JSONObject jsonObject = new JSONObject(_json);
		Object obj = jsonObject.get(SqlStatement.KEYWORD.RECORDS);
		if(obj instanceof JSONArray == false) {
			throw new JSONException("There is no SqlStatement.RECORDS");
		}
		JSONArray jsonArrayRecords = (JSONArray)obj;
		rtObject = new ArrayList<List<Object>>();
		for(int i=0; i<jsonArrayRecords.length(); i++) {
			JSONArray jsonArrayRecord = jsonArrayRecords.getJSONArray(i);
			if(jsonArrayRecord == null) {
				throw new JSONException("There is no array in records");
			}
			List<Object> record = new ArrayList<Object>();
			for(int j=0; j<jsonArrayRecord.length(); j++) {
				record.add(jsonArrayRecord.get(j));
			}
			rtObject.add(record);
		}
		return rtObject;
	}
	
	public static JSONObject getJSonObject(
			List<Map<String,Object>> _resultSet, 
			int count_from, 
			int count_to) {
		JSONObject object = new JSONObject();
		JSONArray jsonArray = new JSONArray();
		int i = 0;
		for(Map<String,Object> element: _resultSet) {
			if(count_from <= i && i < count_to) {
				JSONObject jsonChild = new JSONObject();
				for(Entry<String,Object> entry: element.entrySet()) {
					Object entryObj = entry.getValue();
					try {
						jsonChild.put(entry.getKey(), entryObj);
					} catch (JSONException e) {
						e.printStackTrace();
						return null;
					}
				}
				jsonArray.put(jsonChild);
			}
			i++;
		}
		try {
			object.put("resultSet", jsonArray);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}

}
