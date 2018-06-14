package com.huiy.ssm.frame.persist.generate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JDBC {
	private Connection conn;
	private Statement stmt;
	
	public JDBC(){
		try {
			Class.forName(PoGenConf.driverClass);
			conn = DriverManager.getConnection(PoGenConf.connectionURL, PoGenConf.userId, PoGenConf.password);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JDBC j = new JDBC();
		System.out.println(j.queryAllTables());
	}
	
	protected String getTableComments(String tableName){
		ResultSet rst;
		try {
			rst = stmt.executeQuery(" select remarks from syscat.tables where tabname = '"+tableName.toUpperCase()+"' and owner = '"+PoGenConf.userId.toUpperCase()+"'");
			while(rst.next()){
				String remarks = rst.getString("remarks");
				//System.out.println( rst.getString("table_schema"));
				return remarks;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	protected List<String> queryAllTables(){
		List<String> tables = new ArrayList<String>();
		ResultSet rst;
		try {
			rst = stmt.executeQuery("select table_name,table_schema from user_tables");
			while(rst.next()){
				String tableName = rst.getString("table_name");
				//System.out.println( rst.getString("table_schema"));
				tables.add(tableName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return tables;
	}
	
	protected List<TableInfo> queryAllColumnsAboutTable(String tableName){
		List<TableInfo> columns = new ArrayList<TableInfo>();
		ResultSet rst;
		try {			
			rst = stmt.executeQuery("select a.column_name,a.data_type,a.data_precision,a.data_scale,b.comments  from user_tab_columns a,USER_COL_COMMENTS b where a.TABLE_NAME = b.TABLE_NAME and a.COLUMN_NAME = b.column_name and a.TABLE_NAME = '"+tableName.toUpperCase()+"'");
			while(rst.next()){
				TableInfo info =  new TableInfo();
				String columnName = rst.getString("column_name");
				String dataType = rst.getString("data_type");
				int precision = rst.getInt("data_precision");
				int scale = rst.getInt("data_scale");
				String comment = rst.getString("comments");
				info.setColumn(columnName);
				info.setType(dataType);
				info.setDataPrecision(precision);
				info.setDataScale(scale);
				info.setComment(comment);				
				
				columns.add(info);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return columns;
	}
	
	
	
	
	protected List<String> queryPkForTable(String tableName){
		List<String> columns = new ArrayList<String>();
		ResultSet rst;
		try {			
			rst = stmt.executeQuery("select cu.* from user_cons_columns cu, " +
					"user_constraints au where cu.constraint_name = au.constraint_name " +
					"and au.constraint_type = 'P' and au.table_name ='"+tableName.toUpperCase()+"'");
			while(rst.next()){
				String pk = rst.getString("column_name");
				
				columns.add(pk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return columns;
	}
}
