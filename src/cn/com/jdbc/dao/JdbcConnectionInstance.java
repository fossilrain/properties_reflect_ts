package cn.com.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cn.com.properties.PropertiesManager;

public class JdbcConnectionInstance {
	private PropertiesManager pm=PropertiesManager.getInstance();
	private static JdbcConnectionInstance jci;
	private Connection connection=null;	
	private String url=pm.getProperty("jdbc.url");
	private String driver=pm.getProperty("jdbc.driver");
	private String un=pm.getProperty("jdbc.user");
	private String pw=pm.getProperty("jdbc.pass");
	
	private JdbcConnectionInstance(){
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static JdbcConnectionInstance getInstance(){
		if(jci==null){
			jci=new JdbcConnectionInstance();
		}
		return jci;
	}
	public Connection getConnection(){
		try {
			connection=DriverManager.getConnection(url,un,pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;	
	}
}
