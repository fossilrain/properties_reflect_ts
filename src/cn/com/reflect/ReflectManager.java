package cn.com.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cn.com.bean.Small;


public class ReflectManager {
	//通过列名返回相应的set方法
	public String getSetMethod(String column){
		String last=column.substring(1);
		String first=column.substring(0, 1);
		return "set"+first.toUpperCase()+last;
	}
	//通过列名返回相应的get方法
	public String getGetMethod(String column){
		String last=column.substring(1);
		String first=column.substring(0, 1);
		return "get"+first.toUpperCase()+last;
	}

	//通过bean类名获取对应bean类的方法集合
	public Map<String,Method> getMethods(String beanName) throws SecurityException, ClassNotFoundException{
		Method[]me=Class.forName(beanName).getMethods();
		Map<String,Method> map=new HashMap<String,Method>();
		for(Method m:me){
			map.put(m.getName(), m);
		}
		return map;
	}
	//通过bean类名获取对应bean类的字段及类型集合
	public Map<String,String> getFields(String beanName) throws SecurityException, ClassNotFoundException{
		Field[]fi=Class.forName(beanName).getDeclaredFields();
		Map<String,String> map=new HashMap<String,String>();
		for(Field f:fi){
			map.put(f.getName(), f.getType().getSimpleName());
		}
		return map;
	}
	//返回bean类的一个实例
	public Object getT(String className) throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		return Class.forName(className).newInstance();
	}
	/**
	 * 通过bean类字段名自动调取相应set方法
	 * 给予需设置的值实参
	 * @throws ClassNotFoundException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 */
	public void set(String column,Object value,String beanName,Object bean) throws SecurityException, ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		String s=getSetMethod(column);
		Map<String,Method> map=getMethods(beanName);
		Method me=map.get(s);
		me.invoke(bean, new Object[]{value});
	}
	public static void main(String[]args) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException, InvocationTargetException, NoSuchMethodException, InstantiationException, ClassNotFoundException{
		ReflectManager rm=new ReflectManager();
		Map<String,String> map=rm.getFields("cn.com.bean.Users");
		System.out.println(map.entrySet());
//		Object o=rm.getT("cn.com.bean.Small");
//		rm.set("nums", 53, "cn.com.bean.Small", o);
//		System.out.println(((Small)o).getNums()+"~~~~~~~~~~~");
//		Base b=new Base();
//		rm.set("name", "xxx", "cn.com.bean.Base", b);
//		System.out.println(b.getName());
//		
		
		
	
//		System.out.println(rm.getSetMethod("com"));
//		System.out.println(Class.forName("java.lang.String"));
//		Base ba=new Base();
//		ba.getClass().getMethod("setAge", new Class[]{int.class}).invoke(ba, new Object[]{33});
//		System.out.println(ba.getAge()+"!!!!!!!!!!");
		
		/*
		ba.setAge(23);
		Field[] fi=ba.getClass().getDeclaredFields();
//		对private字段无法set
//		Field fii=Base.class.getDeclaredField("age");
//		fii.set(ba, 33);
		
		ba.getClass().getMethod("setAge", new Class[]{int.class}).invoke(ba, new Object[]{new Integer(33)});
		for(Field f:fi){
			System.out.println(f.getType());
		}
		System.out.println(ba.getAge()+"@@@@@@@@@@");
		*/
	}
}
