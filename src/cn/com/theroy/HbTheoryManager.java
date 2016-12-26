package cn.com.theroy;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.bean.Small;
import cn.com.jdbc.dao.PublicDao;
import cn.com.pattern.PatternManager;
import cn.com.properties.PropertiesManager;
import cn.com.reflect.ReflectManager;

public class HbTheoryManager {
	private PropertiesManager pm=PropertiesManager.getInstance();
	private PublicDao pd=new PublicDao();
	private ResultSet rs;
	private ReflectManager rm=new ReflectManager();
	//得到结果集
	public ResultSet getRs(String sql,Object[] obja) throws SQLException{
		rs=(ResultSet) pd.publicMethod(sql, obja, true);
		return rs;
	}
	//根据rs返回ResultSetMetaData对象
	public ResultSetMetaData getRsmd() throws SQLException{
		return rs.getMetaData();
	}
	//根据rsmd返回表名
	private String getTableName(String sql) throws SQLException{
		ResultSetMetaData rsmd=getRsmd();
		String tn=rsmd.getTableName(1);
		if("".equals(tn)||tn==null){
			tn=new PatternManager().getTableName(sql);
		}
		return tn;
	}
	//根据rsmd返回rs列数
	public int getColumnNo() throws SQLException{
		ResultSetMetaData rsmd=getRsmd();
		return rsmd.getColumnCount() ;
	}
	//通过表名返回对应bean类(完整类名，需要包名)
	public String getBeanName(String packageName,String sql) throws SQLException{
		String tableName=getTableName(sql);
		String last=tableName.substring(1);
		String first=tableName.substring(0, 1);
		return packageName+"."+first.toUpperCase()+last;
	}
	//返回sql语句的查询集合
	public List<Object> list(String sql,Object[] obja) throws SQLException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		getRs(sql,obja);
		List<Object> li=new ArrayList<Object>();
		int count=getColumnNo();
		String column="";
		Object columnValue=null;
		String beanName=getBeanName(pm.getProperty("bean.package"),sql);
		ResultSetMetaData rsmd=getRsmd();
		Object o=null;
		while(rs.next()){
			o=rm.getT(beanName);
			for(int i=1;i<=count;i++){
				column=rsmd.getColumnName(i).toLowerCase();	
				columnValue=rs.getObject(column);
				if(columnValue==null){
					continue;
				}else{
					System.out.println(rsmd.getColumnTypeName(i).toLowerCase()+"!!!");
					System.out.println(columnValue+"@@@");
					System.out.println(column+"&&&");
					if("number".equals(rsmd.getColumnTypeName(i).toLowerCase())){
						columnValue=Integer.valueOf(columnValue.toString());			
					}
				}
				rm.set(column, columnValue, beanName, o);
			}
			li.add(o);
		}	
		rs.close();
		rs=null;
		return li;
	}
	//返回sql语句的查询集合(解析long值)
		public List<Object> listL(String sql,Object[] obja) throws SQLException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
			getRs(sql,obja);
			List<Object> li=new ArrayList<Object>();
			int count=getColumnNo();
			String column="";
			Object columnValue=null;
			String beanName=getBeanName(pm.getProperty("bean.package"),sql);
			ResultSetMetaData rsmd=getRsmd();
			Object o=null;
			while(rs.next()){
				o=rm.getT(beanName);
				for(int i=1;i<=count;i++){
					column=rsmd.getColumnName(i).toLowerCase();	
					columnValue=rs.getObject(column);
					if(columnValue==null){
						continue;
					}else{
						System.out.println(rsmd.getColumnTypeName(i).toLowerCase()+"!!!");
						System.out.println(columnValue+"@@@");
						System.out.println(column+"&&&");
						if("number".equals(rsmd.getColumnTypeName(i).toLowerCase())){
							columnValue=Long.valueOf(columnValue.toString());			
						}
					}
					rm.set(column, columnValue, beanName, o);
				}
				li.add(o);
			}	
			rs.close();
			rs=null;
			return li;
		}
	//释放资源
	public void close() throws SQLException{
		pd.free();
	}
	public static void main(String[]args) throws SQLException, SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException, IOException{
	
		HbTheoryManager htm=new HbTheoryManager();
//		GenerateBean gb=new GenerateBean();
//		gb.agb("user");
//		htm.getRs("select * from small", null);
//		System.out.println(htm.getTableName("select * from small")+"#########");
//		ResultSet rs=htm.getRs("select * from small", null);
//		while(rs.next()){
//			System.out.println(rs.getObject(3));
//		}
		/**
		 * mysql:date类型需转换
		 * 非列名需指定别名
		 * 改进！！！
		 */
//		List<Object> li=htm.list("select u.name,u.id,to_char(registetime,'YYYY-MM-DD HH24:MI:SS') as registetime from users u", null);
		List<Object> li=htm.list("select * from ware", null);
		System.out.println(li.size()+"!!!!!@@@@@@@@@@@");
			System.out.println(li.size());		
//		ResultSetMetaData rsmd=htm.getRsmd();
//		System.out.println(rsmd.getCatalogName(1)+"@@@@@@@");
//		System.out.println(rsmd.getColumnClassName(1)+"@@@@@@@");
//		List<Object> li=htm.list("select * from user", null);
//		for(Object o:li){
//			System.out.println(((User)o).getEmail());
//		}
//		System.out.println(htm.getBeanName("cn.com.bean"));
//		while(rs.next()){
//			System.out.println(rs.getObject(1));
//		}
		//		long i1=new Date().getTime();
//		HbTheoryManager htm=new HbTheoryManager();
//		List<Object> li=htm.list("select * from base",null);
//		List<Object> lii=htm.list("select * from base where name=?", new Object[]{"xxx1"});
//		for(Object o:li){
//			System.out.println(((Base)o).getName());
//		}
//		for(Object o:lii){
//			System.out.println(((Base)o).getName()+"@@@@@@@@@@@");
//		}
//		long i2=new Date().getTime();
//		System.out.println("ceshi....");
//		long i3=new Date().getTime();
//		System.out.println(i2-i1);
//		System.out.println(i3-i2);
		//		PublicDao pd=new PublicDao();
//		ResultSet rss=(ResultSet) pd.publicMethod("select * from user", null, true);
//		ResultSetMetaData rsmd=rss.getMetaData();
//		System.out.println(rsmd.getTableName(2));
		
//		HbTheoryManager htm=new HbTheoryManager();
//		htm.getRs("select * from base",null);
//		System.out.println(htm.getBeanName("cn.com.bean"));
//		System.out.println(htm.getColumnNo());
//		List<Object> li=htm.list("");
//		System.out.println(li.size());
//		Base b=null;
//		for(Object o:li){
//			b=(Base)o;
//			System.out.println(b.getName());
//			System.out.println(b.getAge());
//		}
	}
}
