package org.chronotics.db.mybatis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SqlStatement {
	
	public static String statement = "statement";
	public static String select = "select";
	public static String insert = "insert";
	public static String update = "update";
	public static String delete = "delete";
	public static String from = "from";
	public static String whereCondition = "whereCondition";
	public static String where = "where";
	public static String whereNot = "whereNot";
	public static String and = "and";
	public static String andNot = "andNot";
	public static String or = "or";
	public static String orNot = "orNot";
	public static String orderBy = "orderBy";
	public static String orderByAscOrDec = "orderByAscOrDec";
	public static String ASC = "ASC";
	public static String DESC= "DESC";
	public static String colNames = "colNames";
	public static String colValues = "colValues";
	public static String records = "records";
	public static String set = "set";
	public static String innerJoin = "innerJoin";
	public static String leftJoin = "leftJoin";
	public static String rightJoin = "rightJoin";
	public static String fullOuterJoin = "fullOuterJoin";
	public static String on = "on";
	
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
		}
		public String toString() {
			return str;
		}
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
		
		public SqlStatement build() throws Exception {
			SqlStatement sqlStatement = new SqlStatement();
			if(whereMap != null) {
				if(!whereMap.isEmpty()) {
					sqlParameter.put(SqlStatement.whereCondition, whereMap);
				}
			}
			
			if(colNames != null && colValues !=null) {
				if(!colNames.isEmpty() && !colValues.isEmpty()) {
					assert(colNames.size() == colValues.size());
					sqlParameter.put(SqlStatement.colNames, colNames);
					sqlParameter.put(SqlStatement.colValues, colValues);
				}
			}
			
			if(colNames != null && records != null) {
				if(!colNames.isEmpty() && !records.isEmpty()) {
					sqlParameter.put(SqlStatement.colNames, colNames);
					sqlParameter.put(SqlStatement.records, records);
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
			sqlParameter.put(select, selectList);
			return this;
		}
		
		public Builder from(Object object) {
			if(fromList == null) {
				fromList = new ArrayList<Object>();
			}
			fromList.clear();
			fromList.add(object);
			sqlParameter.put(from, fromList);
			return this;
		}
		
		public Builder insert(Object object) {
			if(insertList == null) {
				insertList = new ArrayList<Object>();
			}
			insertList.clear();
			insertList.add(object);
			sqlParameter.put(SqlStatement.insert, insertList);
			return this;
		}
		
		public Builder update(Object object) {
			if(updateList == null) {
				updateList = new ArrayList<Object>();
			}
			updateList.clear();
			updateList.add(object);
			sqlParameter.put(update, updateList);
			return this;
		}
		
		public Builder delete(Object object) {
			if(deleteList == null) {
				deleteList = new ArrayList<Object>();
			}
			deleteList.clear();
			deleteList.add(object);
			sqlParameter.put(delete, deleteList);
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
			assert(whereMap.get(where.toString()) == null);
			assert(whereMap.get(whereNot.toString()) == null);
			
			whereMap.put(where.toString(), whereList);
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
			assert(whereMap.get(where.toString()) == null);
			assert(whereMap.get(whereNot.toString()) == null);
			
			whereMap.put(whereNot.toString(), whereList);
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
			
			whereMap.put(and, whereList);
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
			
			whereMap.put(andNot, whereList);
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
			
			whereMap.put(or, whereList);
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
			
			whereMap.put(orNot, whereList);
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
}
