package cn.com.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class JdbcBaseData {
	private String sql;
	private Object[] obja;
	private Connection connection;
	private PreparedStatement ps;
	
	public void setSql(String sql) {
		this.sql = sql;
	}
	public void setObja(Object[] obja) {
		this.obja = obja;
	}
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	public PreparedStatement getPs() {
		return ps;
	}
	public void setPs(PreparedStatement ps) {
		this.ps = ps;
	}
	//设置预编译sql语句实参
	public void setSqlParamList(Object[] obja,PreparedStatement ps) throws SQLException{
			for(int i=0;i<obja.length;i++){
				ps.setObject(i+1, obja[i]);
			}			
	}
	//返回预编译PreparedStatement对象
	public PreparedStatement getPreparedStatement() throws SQLException{
		ps=connection.prepareStatement(sql);		
		if(obja==null||obja.length==0){
			obja=null;
			return ps;
		}else{
			setSqlParamList(obja,ps);
			obja=null;
			return ps;
		}
	}
	//查询数据
	public ResultSet dataQuery() throws SQLException{	
		return getPreparedStatement().executeQuery();
	}
	//更新数据
	public int dataUpdate() throws SQLException{		
		return getPreparedStatement().executeUpdate();
	}
	//通用sql语句执行方法
	public boolean sqlExecute() throws SQLException{
		return getPreparedStatement().execute();
	}
}