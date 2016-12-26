package cn.com.pattern;

import java.util.regex.Pattern;

public class PatternManager {
	public String getTableName(String sql){
		Pattern pattern = Pattern.compile("\\s+");
		String[]s=pattern.split(sql);
		int i=0;
		for(String str:s){	
			i++;
			if("from".equals(str)){
				break;
			}			
		}
		return s[i].trim().toLowerCase();
	}
	public static void main(String[]args){
		PatternManager pm=new PatternManager();
		System.out.println(pm.getTableName("select * from user"));
		/*
//		Pattern p=Pattern.compile("\\Qfrom user\\E");
		System.out.println(Pattern.quote("select * from user"));
		System.out.println(Pattern.matches("select","select * from user"));
		Pattern pattern = Pattern.compile("^.{0,}from");
		Matcher m = pattern.matcher("select* * from user from small");
		StringBuffer sbr = new StringBuffer();
//		while(m.find()){
//		    m.appendReplacement(sbr,"");
		 
//		}
//		m.appendTail(sbr);
		m.replaceFirst("");
		m.appendTail(sbr);
		System.out.println(sbr.toString());
		*/
//		Pattern pattern = Pattern.compile("[,\\s|]+");
//		String[] strs = pattern.split("Java Hello World  Java,Hello,,World|Sun,");
//		for (int i=0;i<strs.length;i++) {
//		    System.out.println(strs[i]+"!!!");
//		}
	}
}
