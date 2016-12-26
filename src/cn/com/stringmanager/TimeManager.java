package cn.com.stringmanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TimeManager {
	
	//解析日期
	public Date parseTime(String pattern, Locale locale,String source) throws ParseException{
		
		SimpleDateFormat sdf=new SimpleDateFormat(pattern,locale);
		Date sdate=sdf.parse(source);
		return sdate;
	}	
	//返回抽象时间
	public String getWant(String pattern, Locale locale,String source) throws ParseException{
		
		Date sdate=parseTime(pattern, locale, source);
		long msdate=sdate.getTime();
		GregorianCalendar ndate=new GregorianCalendar();
		long mndate=ndate.getTimeInMillis();
		SimpleDateFormat sdf_time=new SimpleDateFormat("HH:mm:ss",locale);
		SimpleDateFormat sdf_date=new SimpleDateFormat("yyyy-MM-dd",locale);
		if(msdate>mndate){
			return "未来&nbsp;"+sdf_date.format(sdate) ;
		}else{
			ndate.set(Calendar.HOUR_OF_DAY, 0);
			ndate.set(Calendar.MINUTE, 0);
			ndate.set(Calendar.SECOND, 0);
			mndate=ndate.getTimeInMillis();
			long day=24*60*60*1000;
			long subtract=Math.abs(msdate-mndate);
			if(msdate>mndate){
				return "今天&nbsp;"+sdf_time.format(sdate);
			}else if(subtract<=day){
				return "昨天&nbsp;"+sdf_time.format(sdate);
			}else{
				return sdf_date.format(sdate);
			}
		}
	}

	
	public static void main(String[] args) throws ParseException {
		TimeManager tm=new TimeManager();
		
		String xxx=tm.getWant("yyyy-MM-dd HH:mm:ss", Locale.CHINA, " 2014-09-29 0:37:45 ");
		System.out.println(xxx);
		
		
//		GregorianCalendar gc=new GregorianCalendar();
//		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yy KK:mm" ,Locale.ENGLISH);
//		//GretorianCalendar类
//		System.out.println(new Date());
//		System.out.println(gc);
//		System.out.println(gc.getGregorianChange());//儒略历 
//		System.out.println(gc.getTime());			//Date
//		System.out.println(gc.getTimeInMillis());	//long millis
//		System.out.println(gc.getTimeZone());		//返回默认时区
//		System.out.println(gc.get(Calendar.YEAR));	//得到指定日历字段值
//		gc.set(Calendar.DATE, 3);					//设置指定字段的值
//		long millis=1411800460000l;	
//		gc.setTimeInMillis(millis);
//		System.out.println(gc.getTime()+"@@@@@@@@@@@@@@@@@@@@@");
//		System.out.println(DateFormat.getInstance().format(new Date())+"**************");
//		//Locale类
//		System.out.println(gc.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH));
//		System.out.println(Locale.getDefault().getCountry());
//		System.out.println(Locale.getDefault().toString());
//
//		//SimpleDateFormat类
//		//Date date=sdf.parse("07/10/96 4:5 PM, PDT");
//		ParsePosition pos = new ParsePosition(1);
//		//Date date=sdf.parse("07/10/96 4:5 PM");
//		//System.out.println(date);
//		SimpleDateFormat sdfs=new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss",Locale.ENGLISH);
//	    String dateString="2014年09月09日 23:23:23 PM";  
//	    Date sdate=sdfs.parse(dateString);
//	    System.out.println(sdate);
//	    Date date=sdf.parse("07/10/96 4:5 PM, PDT");
//	    //Date date=sdf.parse("Sun Sep 28 07:55:30 CST 2014");
//	    System.out.println(date+"oooooooooooooooooooooooooooooooo");
//	    
//	    System.out.println(sdfs.format(new Date())); 
//	    String str=new Date().toString();
//	    System.out.println(str);
//	    System.out.println(new GregorianCalendar().toString());
//	    System.out.println(sdfs.toPattern());
//	    
//	    SimpleDateFormat ssdf=new SimpleDateFormat();
//	    System.out.println(ssdf.toPattern());
////	    System.out.println(ssdf.parse("13-5-3"));
	}

}
