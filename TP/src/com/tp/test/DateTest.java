package com.tp.test;
import java.util.Calendar;
import java.util.Date;
import com.tp.tools.FormatTools;
public class DateTest {
	public static void main(String[] args) {
//		Date date=new Date();
//		System.out.println("m"+date.getMonth());
//		String str=FormatTools.FormateTime(date);
//		System.out.println("date="+str);
		Calendar a=Calendar.getInstance();
//		System.out.println(a.get(Calendar.YEAR));//得到年
//		System.out.println(a.get(Calendar.MONTH));//由于月份是从0开始的所以加1
//		System.out.println(a.get(Calendar.DATE));
//		a.set(Calendar.MONTH, a.get(Calendar.MONTH) + 1);  
		// 设置Calendar日期为下一个月一号  
		a.set(Calendar.DATE, 1);  
//		System.out.println(a.get(Calendar.DATE));//由于月份是从0开始的所以加1
		// 设置Calendar日期减一,为本月末  
		a.add(Calendar.DATE, -1);  
		System.out.println(a.get(Calendar.DATE));//由于月份是从0开始的所以加1
		
	}
}
