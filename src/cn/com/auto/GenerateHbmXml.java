package cn.com.auto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import cn.com.properties.PropertiesManager;
import cn.com.reflect.ReflectManager;
import cn.com.theroy.HbTheoryManager;

public class GenerateHbmXml {
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
		File fi=new File("src/"+packagePath+"/"+getBeanName(tableName)+"."+pm.getProperty("hbm.suffix"));
		fi.createNewFile();
		return new FileOutputStream(fi);
	}
	//根据表名返回相应xml映射文件内容
	public byte[] getBeanContext(String[]table_primaryKey) throws SQLException, SecurityException, ClassNotFoundException{
		String tableName=getBeanName(table_primaryKey[0]);
		StringBuffer sb=new StringBuffer();
		sb.append(pm.getProperty("hbm.header")+"\n");
		sb.append("<hibernate-mapping>\n");
		sb.append("\t<class name=");
		sb.append('"'+packageName+"."+tableName+'"');
		sb.append(" table=");
		sb.append('"'+table_primaryKey[0]+'"');
		sb.append(" catalog=");
		sb.append('"'+pm.getProperty("database.name")+'"'+">\n");
		
		Map<String,String> fields=rm.getFields(packageName+"."+tableName);
		Object[]obj=fields.keySet().toArray();
		String[]fieldsNames=new String[obj.length];
		for(int i=0;i<obj.length;i++){
			fieldsNames[i]=(String)obj[i];
		}
		sb.append("\t\t<id name=");
		sb.append('"'+table_primaryKey[1]+'"');
		sb.append(" column=");
		sb.append('"'+table_primaryKey[1]+'"');
		sb.append(" type=");
		sb.append('"'+pm.getProperty(fields.get(table_primaryKey[1]))+'"');
		sb.append(">\n");
		sb.append("\t\t\t<generator class=");
		sb.append('"'+pm.getProperty("hbm.id.generator")+'"');
		sb.append("/>\n");
		sb.append("\t\t</id>\n");
		for(String fn:fieldsNames){
			if("serialVersionUID".equals(fn)||fn.equals(table_primaryKey[1])){
				continue;
			}else{
				sb.append("\t\t<property name=");
				sb.append('"'+fn+'"');
				sb.append(" column=");
				sb.append('"'+fn+'"');
				sb.append(" type=");
				sb.append('"'+pm.getProperty(fields.get(fn))+'"');
				sb.append("/>\n");
			}	
		}
		sb.append("\t</class>\n");
		sb.append("</hibernate-mapping>");
		return sb.toString().getBytes();
	}
	//根据表名及主键自动生成bean类
	public void agxml(String[]table_primaryKey) throws SQLException, IOException, SecurityException, ClassNotFoundException{
		byte[] b=getBeanContext(table_primaryKey);
		FileOutputStream fos=getFos(table_primaryKey[0]);
		fos.write(b);
		fos.close();
	}
	//根据表名及主键数组自动生成bean类
	public void agxml(String[][]dArray) throws SQLException, IOException, SecurityException, ClassNotFoundException{
		for(String[]s:dArray){
			agxml(s);
		}
	}
	
	public static void main(String[] args) throws SQLException, IOException, SecurityException, ClassNotFoundException {
		GenerateHbmXml ghx=new GenerateHbmXml();
//		System.out.println(ghx.getBeanName("user"));
//		ghx.agxml(new String[]{"users","id"});
		ghx.agxml(new String[][]{new String[]{"users","id"},new String[]{"ware","number"}});
	}
}
