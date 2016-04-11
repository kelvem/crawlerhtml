package com.kelvem.crawler.model;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.kelvem.common.StringUtil;
import com.kelvem.common.database.base.DataBaseSession;
import com.kelvem.common.database.sqlite.SqliteSession;

public class SqliteBaseModel {


	private static DataBaseSession session = new SqliteSession();
	static {
		session.open();
	}

	public SqliteBaseModel() {

	}
	
	public static <T> int count(T t) {

		try {
//			System.out.println("根据对象查询个数： " + t);
			String sql = " select count(1) ";
			sql += getFromPartSql(t);
			sql += getWherePartSql(t);
			
			int count = session.count(sql);
			
			return count;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public static <T> int maxId(T t) {

		try {
//			System.out.println("根据对象查询个数： " + t);
			String primaryKey = getPrimaryKeyName(t);
			String sql = " select max(" + primaryKey + ") ";
			sql += getFromPartSql(t);
			sql += getWherePartSql(t);
			
			int count = session.count(sql);
			
			return count;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static <T> List<T> queryModel(T t) {

		try {
			System.out.println("根据对象查询列表： " + t);
			String sql = " select * ";
			sql += getFromPartSql(t);
			sql += getWherePartSql(t);
			
			ResultSet rs = session.query(sql);

			// ResultSet -> List<T>
			List<T> result = new ArrayList<T>();
			while(rs.next()) {
				Class<?> clazz = t.getClass();
				T m = (T)clazz.newInstance(); 
				Field[] fields = clazz.getDeclaredFields();
				for (Field field : fields) {
					String fieldName = field.getName();
					String columnName = StringUtil.aaaAaaToaaa_aaa(fieldName);
					Object value = rs.getObject(columnName);
					if (value == null) {
						continue;
					}
					field.setAccessible(true);
					field.set(m, value);
				}
				result.add(m);
			}
			
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static <T> Integer addRecord(T t) {

//		System.out.println("增加表记录： " + t);
		String sql = addRecordSql(t);

		try {
			session.execute(sql);
			
			Integer maxId = maxId(t);
			
			return maxId;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
	
	


	
	public static String sqliteEscape(String keyWord){
//	    keyWord = keyWord.replace("/", "//");
	    keyWord = keyWord.replace("'", "''");
//	    keyWord = keyWord.replace("[", "/[");
//	    keyWord = keyWord.replace("]", "/]");
//	    keyWord = keyWord.replace("%", "/%");
//	    keyWord = keyWord.replace("&","/&");
//	    keyWord = keyWord.replace("_", "/_");
//	    keyWord = keyWord.replace("(", "/(");
//	    keyWord = keyWord.replace(")", "/)");
	    return keyWord;
	}
	
	protected static <T> String getPrimaryKeyName(T t) {
		String className = t.getClass().getSimpleName();
		String tableName = StringUtil.aaaAaaToaaa_aaa(className);
		if (tableName.endsWith("_model")) {
			tableName = tableName.substring(0, tableName.length() - "_model".length());
		}
		
		String primaryKey = tableName + "_id";
		return primaryKey;
	}
	
	protected static <T> String getFromPartSql(T t) {
		
		String className = t.getClass().getSimpleName();
		String tableName = StringUtil.aaaAaaToaaa_aaa(className);
		if (tableName.endsWith("_model")) {
			tableName = tableName.substring(0, tableName.length() - "_model".length());
		}
		
		String from = " from " + tableName + " \r\n";
		return from;
	}
	
	protected static <T> String getWherePartSql(T t) {
		
		try {
			Map<String, String> columnMap = new LinkedHashMap<String, String>();
			
			Field[] fields = t.getClass().getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				String columnName = StringUtil.aaaAaaToaaa_aaa(fieldName);
				
				field.setAccessible(true);
				Object fieldValue = field.get(t);
				String columnValue = "";
				if (fieldValue == null) {
					continue;
				}
				
				columnValue = "'" + sqliteEscape(fieldValue.toString()) + "'";
				
				columnMap.put(columnName, columnValue);
			}
			
			StringBuilder sb = new StringBuilder();
			
			boolean isFirst = true;
			for (String key : columnMap.keySet()) {
				if (isFirst == true) {
					sb.append(" where ");
					isFirst = false;
				} else {
					sb.append(" and ");
				}
				sb.append(key).append("=").append(columnMap.get(key));
			}
			
			String where = sb.toString();

			return where;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected static <T> String addRecordSql(T t) {
		
		try {
			Map<String, String> columnMap = new LinkedHashMap<String, String>();
			
			String className = t.getClass().getSimpleName();
			String tableName = StringUtil.aaaAaaToaaa_aaa(className);
			if (tableName.endsWith("_model")) {
				tableName = tableName.substring(0, tableName.length() - "_model".length());
			}
			String primaryKey = tableName + "_id";
			
			Field[] fields = t.getClass().getDeclaredFields();
			for (Field field : fields) {
				String fieldName = field.getName();
				String columnName = StringUtil.aaaAaaToaaa_aaa(fieldName);
				if (columnName.equalsIgnoreCase(primaryKey)) {
					continue;
				}
				
				field.setAccessible(true);
				Object fieldValue = field.get(t);
				String columnValue = "";
				if (fieldValue == null) {
					columnValue = "null";
				} else {
					columnValue = "'" + sqliteEscape(fieldValue.toString()) + "'";
				}
				
				columnMap.put(columnName, columnValue);
			}
			
			StringBuilder sb = new StringBuilder();
			sb.append("insert into ").append(tableName).append("\r\n");
			sb.append("(");
			
			boolean isFirst = true;
			for (String key : columnMap.keySet()) {
				if (isFirst == true) {
					isFirst = false;
				} else {
					sb.append(",");
				}
				sb.append(key);
			}
			sb.append(")\r\n");
			
			sb.append("values (");
			isFirst = true;
			for (String key : columnMap.keySet()) {
				if (isFirst == true) {
					isFirst = false;
				} else {
					sb.append(",");
				}
				sb.append(columnMap.get(key));
			}
			sb.append(")");
			
			String sql = sb.toString();
//			System.out.println(sql);

			return sql;
			
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
}
