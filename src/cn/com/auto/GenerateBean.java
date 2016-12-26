package cn.com.auto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import cn.com.properties.PropertiesManager;
import cn.com.reflect.ReflectManager;
import cn.com.theroy.HbTheoryManager;

public class GenerateBean {
	private PropertiesManager pm=PropertiesManager.getInstance();
	private HbTheoryManager htm=new HbTheoryManager();
	private ReflectManager rm=new ReflectManager();
	private String packageName=pm.getProperty("bean.package");
	private String packagePath=pm.getProperty("bean.package").replace('.', '/');
	//生成文件夹（packageName）
	public void makePackage(){
		File fi=new File("src/"+packagePath);
		if(!fi.exists()){
			fi.mkdirs();
		}
	}
	//通过表名返回对应bean类(不需要包名)
	public String getBeanName(String tableName) throws SQLException{
		String last=tableName.substring(1);
		String first=tableName.substring(0, 1);
		return first.toUpperCase()+last;
	}
	//根据表名返回写入流
	public FileOutputStream  getFos(String tableName) throws SQLException, IOException{
		makePackage();
		File fi=new File("src/"+packagePath+"/"+getBeanName(tableName)+".java");
		fi.createNewFile();
		return new FileOutputStream(fi);
	}
	//根据表名返回相应bean类内容
	public byte[] getBeanContext(String tableName) throws SQLException{
		StringBuffer sb=new StringBuffer();
		sb.append("package ");
		sb.append(packageName+";\n");
		sb.append("import java.io.Serializable;\n\n");
		sb.append("public class "+getBeanName(tableName)+" implements Serializable {\n\n");
		sb.append("\t"+"private static final long serialVersionUID = 1L;\n\t");
		
		htm.getRs("select * from "+tableName, null);
		int count=htm.getColumnNo();
		ResultSetMetaData rsmd=htm.getRsmd();
		String cn="";
		String ct="";
		for(int i=1;i<=count;i++){
			cn=rsmd.getColumnName(i).toLowerCase();
			ct=rsmd.getColumnTypeName(i);
			sb.append("private "+pm.getProperty(ct)+" "+cn+";\n\t");
		}
		sb.append("\n\t");
		for(int i=1;i<=count;i++){
			cn=rsmd.getColumnName(i).toLowerCase();
			ct=rsmd.getColumnTypeName(i);
			sb.append("public "+pm.getProperty(ct)+" "+rm.getGetMethod(cn)+" (){\n\t\t");
			sb.append("return "+cn+";\n\t");
			sb.append("}\n\t");
			sb.append("public void "+rm.getSetMethod(cn)+"("+pm.getProperty(ct)+" "+cn+"){\n\t\t");
			sb.append("this."+cn+"="+cn+";\n\t");
			sb.append("}\n\t");
		}
		sb.append("\n");
		sb.append("}");
		return sb.toString().getBytes();
	}
	//根据表名自动生成bean类
	public void agb(String tableName) throws SQLException, IOException{
		byte[] b=getBeanContext(tableName);
		FileOutputStream fos=getFos(tableName);
		fos.write(b);
		fos.close();
	}
	//根据表名数组自动生成bean类
	public void agb(String[]tableName) throws SQLException, IOException{
		for(int i=0;i<tableName.length;i++){
			agb(tableName[i]);
		}
	}
	public static void main(String[]args) throws SQLException, IOException{
//		PropertiesManager pm=PropertiesManager.getInstance();
//		File fi=new File(GenerateBean.class.getResource("/").getPath()+pm.getProperty("bean.package"));
//		System.out.println(fi.getParent());
//		System.out.println(fi.getAbsolutePath());
//		System.out.println("ceshi.......");
		GenerateBean gb=new GenerateBean();
//		System.out.println(gb.getBeanName("user"));
//		gb.getFos("user");
//		gb.agb(new String[]{"user","base"});
		gb.agb("ware");
//		gb.getBeanContext("user");
//		File fi=new File("E:\\xxx\\properties_reflect_ts\\WebRoot\\WEB-INF\\classes\\cn\\com\\bean\\User.java");
//		File fii=new File("E:\\xxx\\properties_reflect_ts\\WebRoot\\WEB-INF\\classes\\cn\\com\\bean\\a.txt");
//		fii.createNewFile();
//		File fi=new File("ceshi.txt");
//		fi.createNewFile();
//		System.out.println(fi.getAbsolutePath());
	}
}
