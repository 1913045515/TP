package com.tp.tools;
import java.util.Calendar;
import java.util.Date;
public class DateUtils {
	public static Date[] getDate(){
		Calendar a=Calendar.getInstance();
		String year=a.get(Calendar.YEAR)+"";
		String month=a.get(Calendar.MONTH)+1+"";
		a.set(Calendar.MONTH, a.get(Calendar.MONTH) + 1);  
		a.set(Calendar.DATE, 1);  
		a.add(Calendar.DATE, -1);  
		String date=a.get(Calendar.DATE)+"";
		String first=year+"-"+month+"-1";
		String last=year+"-"+month+"-"+date;
		Date firstDate=FormatTools.FormateTime(first);
		Date lastDate=FormatTools.FormateTime(last);
		Date[] result={firstDate,lastDate};
		return result;
	}
	
	public static Date[] setMonth(Date[] date,String month){
		Calendar first=Calendar.getInstance();
		first.setTime(date[0]);
		first.set(Calendar.MONTH,Integer.valueOf(month)-1);
		Calendar last=Calendar.getInstance();
		last.setTime(date[1]);
		last.set(Calendar.MONTH,Integer.valueOf(month));
		last.set(Calendar.DATE, 1);        //设置为该月第一天
		last.add(Calendar.DATE,-1);  
		Date[] result={first.getTime(),last.getTime()};
		return result;
	}
	
	public static Date[] setYear(Date[] date,String year){
		Calendar first=Calendar.getInstance();
		first.setTime(date[0]);
		first.set(Calendar.YEAR, first.get(Calendar.YEAR)+Integer.valueOf(year));
		Calendar last=Calendar.getInstance();
		last.setTime(date[1]);
		last.set(Calendar.YEAR, last.get(Calendar.YEAR)+Integer.valueOf(year));
		Date[] result={first.getTime(),last.getTime()};
		return result;
	}
	
	public static Date[] setDate(String year,String month){
		Calendar first=Calendar.getInstance();
		first.set(Calendar.YEAR, first.get(Calendar.YEAR)+Integer.valueOf(year));
		first.set(Calendar.MONTH,Integer.valueOf(month)-1);
		first.set(Calendar.DATE, 1); 
		Calendar last=Calendar.getInstance();
		last.set(Calendar.YEAR, last.get(Calendar.YEAR)+Integer.valueOf(year));
		last.set(Calendar.MONTH,Integer.valueOf(month));
		last.set(Calendar.DATE, 1);        //设置为该月第一天
		last.add(Calendar.DATE,-1);  
		Date[] result={first.getTime(),last.getTime()};
		return result;
	}
	
	public static void main(String[] args) {
//		String month="1月";
//		month=month.substring(0, month.length()-1);
//		System.out.println("month="+month);
//		String str="1月2222222";
//		System.out.println(str.substring(1));
		
//		System.out.println(FormatTools.FormateTime(DateUtils.setMonth(DateUtils.getDate(), "2")[0]));
//		System.out.println(FormatTools.FormateTime(DateUtils.setMonth(DateUtils.getDate(), "2")[1]));
	
//		System.out.println(FormatTools.FormateTime(DateUtils.setYear(DateUtils.getDate(), "-2")[0]));
//		System.out.println(FormatTools.FormateTime(DateUtils.setYear(DateUtils.getDate(), "-2")[1]));
	
		System.out.println(FormatTools.FormateTime(DateUtils.setDate("1", "1")[0]));
		System.out.println(FormatTools.FormateTime(DateUtils.setDate("2","2")[1]));
		System.out.println(FormatTools.FormateTime(DateUtils.setDate("3", "3")[0]));
		System.out.println(FormatTools.FormateTime(DateUtils.setDate("4","4")[1]));
	
	}
}
