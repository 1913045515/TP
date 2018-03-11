package com.tp.action;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.tp.biz.LuceneBiz;
import com.tp.tools.IKAnalyzerSearch;
import com.tp.tools.JsonUtil;
public class LuceneAction {
	private LuceneBiz luceneBiz;
	public void setLuceneBiz(LuceneBiz luceneBiz) {
		this.luceneBiz = luceneBiz;
	}
	public LuceneAction(){
		
	}
	public void execute() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter printWrite=response.getWriter();
		String action=request.getParameter("action");
		if("save".equals(action)){
			luceneBiz.saveInfo();
		}else{
			String str=request.getParameter("query");
			List<String>listStr=IKAnalyzerSearch.iKAnalyzerSearch(str);
			List<Object> list=luceneBiz.queryCommodityDao(listStr);
			printWrite.write(JsonUtil.toJson(list));
		}
		//printWrite.write(JsonUtil.toJson(list));
		printWrite.flush();
		printWrite.close();
	}
}
