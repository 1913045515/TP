package com.tp.tools;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
public class FormatTools {
	public static List<Object> reduceValue(List<Object> list, int num) {
		List<Object> listResult = new ArrayList<Object>();
		if (list.size() > num) {
			for (int i = 0; i < num; i++) {
				listResult.add(list.get(i));
			}
		} else {
			listResult = list;
		}
		return listResult;
	}

	public static List<Object> formateAdd(List<Object> list,
			String[] strSelect, String[] addName, String[][] addValue) {
		List<Object> listResult = new ArrayList<Object>();
		Iterator<Object> iterator = list.iterator();
		int j=0;
		while (iterator.hasNext()) {
			Map<String, Object> map = (Map<String, Object>) iterator.next();
			Map<String, Object> mapResult = new HashMap<String, Object>();
			for (int i = 0; i < map.size(); i++) {
				mapResult.put(strSelect[i], map.get(strSelect[i]));
			}
			for(int i=0;i<addName.length;i++){
				mapResult.put(addName[i], addValue[i][j]);
			}
			listResult.add(mapResult);
			j++;
		}
		return listResult;
	}
	public static Date FormateTime(String strTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			date = sdf.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static Date FormateTimes(String strTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=null;
		try {
			date = sdf.parse(strTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public static String FormateTime(Date dateTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strDate=null;
		try {
			strDate = sdf.format(dateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}
	
	public static String FormateTimes(Date dateTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String strDate=null;
		try {
			strDate = sdf.format(dateTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}
}
