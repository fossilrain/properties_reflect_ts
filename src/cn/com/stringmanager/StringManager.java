package cn.com.stringmanager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class StringManager {
	//返回任意字符串中每个字符在该字符串中出现次数的Map视图
	public Map<Object, Integer> census(String target){
		List<Character> li=new ArrayList<Character>();
		for(int i=0;i<target.length();i++){
			li.add(target.charAt(i));
		}
		Object[]x=li.toArray();
		Set<Character> se=new HashSet<Character>();
		for(int i=0;i<target.length();i++){
			se.add(target.charAt(i));
		}
		Object[]y=se.toArray();
		int[]z=new int[y.length];
		for(int i=0;i<y.length;i++){
			for(int j=0;j<x.length;j++){
				if(x[j].equals(y[i])){
					z[i]++;
				}
			}
		}
		Map<Object,Integer> cs=new HashMap<Object,Integer>();
		for(int i=0;i<z.length;i++){
			cs.put(y[i],z[i]);
		}
		return cs;
	}
	//返回任意字符串中字母个数
	public Integer letterNumber(String target){
		int k=0;
		for(int i=0;i<target.length();i++){
			if(Character.isLetter(target.charAt(i))){
				if(Character.isUpperCase(target.charAt(i))||Character.isLowerCase(target.charAt(i))){
					k++;
				}
			}else{}
		}
		return k;
	}
	//返回任意字符串中字母个数
	public Integer allLetterNumber(String target){
		int k=0;
		for(int i=0;i<target.length();i++){
			if(Character.isLetter(target.charAt(i))){
					k++;
			}else{}
		}
		return k;
	}
	
	//返回任意字符串中数字个数
	public Integer digitNumber(String target){
		int k=0;
		for(int i=0;i<target.length();i++){
			if(Character.isDigit(target.charAt(i))){
				k++;
			}else{}
		}
		return k;
	}
	//邮箱验证
	public boolean emailVerify(String email){
		boolean flag=false;
		if(email!=null){
			int ema=email.indexOf("@");
			int emp=email.indexOf(".");
			Map<?, ?> memail=census(email);
			if(ema!=-1&&emp!=-1){
				int za=(Integer)memail.get('@');
				int zp=(Integer)memail.get('.');
				if(ema>0&&emp>ema+1&&!email.contains(" ")&&za==1&&zp<3){
					email=email.replace('.', 'x');
					email=email.replace('@', 'x');
					int x=letterNumber(email);
					int y=digitNumber(email);
					if(x+y==email.length()){
						flag=true;
					}
				}else{
					flag=false;
				}
			}
		}
		return flag;
	}
	//随机生成固定位数ID(6位，首位为3)
	public int generateID(){
		Random rd=new Random();
		int id=rd.nextInt(65535);
		String str=String.valueOf(id);
		StringBuffer sb=new StringBuffer(str);
		for(int i=0;i<(5-str.length());i++){
			sb.insert(0,"0");
		}
		sb.insert(0, 3);
		int x=Integer.parseInt(sb.toString());
		return x;
	}
	//通用随机id生成器
	public Object generateID(int digitCapacity,boolean isString,int scope,String prefix){
		Random rd=new Random();
		int id=rd.nextInt(scope);
		String str=String.valueOf(id);
		StringBuffer sb=new StringBuffer(str);
		int count=String.valueOf(scope).length();
		if(prefix==null||"".equals(prefix)){
			if(digitCapacity<count){
				return "参数取值逻辑有误";
			}
		}else{
			if(digitCapacity-prefix.length()<count){
				return "参数取值逻辑有误";
			}
		}
		if(prefix==null||"".equals(prefix)){
			if(isString){
				for(int i=0;i<(digitCapacity-str.length());i++){
					sb.insert(0,"0");
				}
				return sb.toString();
			}else{
				return id;
			}
		}else{
			for(int i=0;i<(digitCapacity-prefix.length()-str.length());i++){
				sb.insert(0, "0");
			}
			sb.insert(0, prefix);
			return sb.toString();
		}
	}
	public static void main(String[]args){
		StringManager sm=new StringManager(); 
//		System.out.println(sm.generateID(5, false, 655353, "05"));
		System.out.println(sm.emailVerify("测试@qq.com")+"!!!!!!!!!!!!!!!!!!!!!!!!!");
		String ce="1024254883@qq.com";
		ce=ce.replace('.', '*');
		ce=ce.replace('@', '*');
		System.out.println(ce);
		System.out.println(sm.letterNumber("测试@qq.com"+"!!!!!!!!!!!!!!!!!!!!!!!!!"));
		System.out.println(Character.isUpperCase('测'));
		System.out.println(Character.isLowerCase('测'));
		System.out.println(sm.allLetterNumber("*   "));
	}
}
