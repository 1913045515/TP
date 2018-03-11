package com.tp.action;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.tp.biz.ReportBiz;
import com.tp.tools.JsonUtil;
public class ReportAction {
	private ReportBiz reportBiz;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public void setReportBiz(ReportBiz reportBiz) {
		this.reportBiz = reportBiz;
	}
	public ReportAction(){
		try {
			response = ServletActionContext.getResponse();
			request = ServletActionContext.getRequest();
			response.setCharacterEncoding("UTF-8");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	public String execute() throws Exception{
		PrintWriter witer=response.getWriter();
		String type=request.getParameter("type");
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		if("all".equals(type)){
			Map<String,Object>map=new HashMap<String,Object>();
			String pageSize=request.getParameter("pageSize");
			String pageNumber=request.getParameter("pageNumber");
			result=reportBiz.queryReport(Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("selete".equals(type)){
			String typeName=request.getParameter("typeName");
			String typeValue=request.getParameter("typeValue");
			if("id".equals(typeName) && !"".equals(typeValue)){			
				result=reportBiz.queryReport(Integer.valueOf(typeValue));
			}else if("cId".equals(typeName) && !"".equals(typeValue)){
				result=reportBiz.queryReport(Integer.valueOf(typeValue));
			}else if("uId".equals(typeName) && !"".equals(typeValue)){
				result=reportBiz.queryReport(Integer.valueOf(typeValue));
			}else{
				result=reportBiz.queryReport();
			}
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("delete".equals(type)){
			if(!"".equals(request.getParameter("check"))){
				String[] check=request.getParameter("check").split(",");
				reportBiz.deleteReport(check);
			}		
			witer.write(JsonUtil.toJson(null));
		}
		witer.flush();
		witer.close();	
		return null;
	}
}
