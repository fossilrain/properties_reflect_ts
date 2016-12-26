package cn.com.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager extends Properties{
	/**
	 * 单例获取PropertiesManager对象
	 * 缩短new时间
	 */
	private static final long serialVersionUID = 1L;
	private static PropertiesManager pm=null;
	//构造器私有化
	private PropertiesManager(){}
	//获得一配置文件输入流
	public static InputStream getInputStream(String propertiesFile) throws FileNotFoundException{
		File fi=new File(propertiesFile);
		return new FileInputStream(fi);
	}
	//获得PropertiesManger实例
	public static PropertiesManager getInstance(){
		if(pm==null){
			pm=new PropertiesManager();
			try {
				pm.load(getInputStream(PropertiesManager.class.getResource("/").getPath()+"3.properties"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return pm;
	}
	public static void main(String[]args) throws FileNotFoundException, IOException{
		/*
		Properties pp=new Properties();
		pp.load(new FileInputStream(new File("E:/xxx/properties_reflect_ts/src/cn/com/properties/1.properties")));
		pp.load(new FileInputStream(new File("E:/xxx/properties_reflect_ts/src/cn/com/properties/2.properties")));
		System.out.println(pp.get("cn.com.1"));
		System.out.println(pp.get("cn.com.2"));
		System.out.println(pp.getProperty("cn.com.1"));
		System.out.println(pp.getProperty("cn.com.2"));
		*/
//		File fi=new File("xxx.txt");
//		fi.createNewFile();
//		System.out.println(fi.getAbsolutePath());
		String url=PropertiesManager.class.getResource("/").getPath();
		System.out.println(url);
		PropertiesManager pm=PropertiesManager.getInstance();
		System.out.println(pm.getProperty("cn.com.3"));
	}
}
