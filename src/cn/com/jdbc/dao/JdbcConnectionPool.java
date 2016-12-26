package cn.com.jdbc.dao;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class JdbcConnectionPool {
	private static JdbcConnectionPool jci;
	private Connection connection=null;	
//	private String url="jdbc:mysql://localhost:3306/synthetical?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&failOverReadOnly=false";
//	private String driver="com.mysql.jdbc.Driver";
//	private String un="root";
//	private String pw="loop";
	
	private JdbcConnectionPool(){
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	public static JdbcConnectionPool getInstance(){
		if(jci==null){
			jci=new JdbcConnectionPool();
		}
		return jci;
	}
	//连接池
	public Connection getConnection(){
//		try {
//			connection=DriverManager.getConnection(url,un,pw);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
     
		try {
			Context con = new InitialContext();
			DataSource ds=(DataSource)con.lookup("java:comp/env/jdbc/jx");
			connection=ds.getConnection();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return connection;	
	}
//	public static void main(String[]args){
//		JdbcConnectionPool jci=new JdbcConnectionPool();
//		if(jci.getConnection()==null){
//			System.out.println("fail");
//		}else{
//			System.out.println("success");
//		}
//	}
}
