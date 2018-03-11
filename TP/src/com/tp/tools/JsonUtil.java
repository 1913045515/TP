package com.tp.tools;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javassist.bytecode.stackmap.TypeData.ClassName;

import org.apache.lucene.analysis.core.TypeTokenFilter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class JsonUtil {
	private static Gson gson;
	public static String toJson(Object objec) {
		gson = new Gson();
		String strJson = gson.toJson(objec);
		return strJson;
	}
	public static Map<String, Object> toObject(String jsonString)
			throws JSONException {
		JSONObject json = new JSONObject(jsonString);
		Map<String, Object> map = new HashMap<String, Object>();
		for (Iterator iter = json.keys(); iter.hasNext();) {
			String key = (String) iter.next();
			map.put(key, json.get(key));
		}
		return map;
	}
	
	public static Object toEntity(String str,Class className){
		 return gson.fromJson(str,className);	
	}
	
	public static List<Map<String,Object>> toList(String str){
		Type listType = new TypeToken<List<Map<String,Object>>>() {}.getType();
		List<Map<String,Object>> list=gson.fromJson(str,listType);
		return list;
	}
}
