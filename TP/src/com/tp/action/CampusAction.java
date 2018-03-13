package com.tp.action;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.jboss.logging.Cause;

import com.tp.biz.CampusBiz;
import com.tp.entity.Campus;
import com.tp.entity.Classification;
import com.tp.tools.JsonUtil;
public class CampusAction {
	private CampusBiz campusBiz;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public void setCampusBiz(CampusBiz campusBiz) {
		this.campusBiz = campusBiz;
	}
	public CampusAction(){
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
			result=campusBiz.queryCampus(Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
			map.put("total", campusBiz.queryCampus().size());
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("selete".equals(type)){
			String typeName=request.getParameter("typeName");
			String typeValue=request.getParameter("typeValue");
			if("id".equals(typeName) && !"".equals(typeValue)){			
				result=campusBiz.queryCampus(Integer.valueOf(typeValue));
			}else if("name".equals(typeName) && !"".equals(typeValue)){
				result=campusBiz.queryCampus(typeValue);
			}else{
				result=campusBiz.queryCampus();
			}
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("total", result.size());
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("delete".equals(type)){
			if(!"".equals(request.getParameter("check"))){
				String[] check=request.getParameter("check").split(",");
				campusBiz.deleteCampus(check);
			}		
			witer.write(JsonUtil.toJson(null));
		}else if("detail".equals(type)){
			String showId=request.getParameter("showId");
			result=campusBiz.queryCampus(Integer.valueOf(showId));
			request.setAttribute("list",result);
			witer.write(JsonUtil.toJson(null));
			return "detail";
		}else if("update".equals(type)){
			int id=Integer.valueOf(request.getParameter("id"));
			String province=request.getParameter("province");
			String university=request.getParameter("university");
			int state=Integer.valueOf(request.getParameter("state"));
			Campus campus=campusBiz.campus(id);
			campus.setUniversity(university);
			campus.setProvince(province);
			campus.setState(state);
			witer.write(JsonUtil.toJson(campusBiz.updateCampus(campus)));
		}else if("save".equals(type)){
			String province=request.getParameter("province");
			String university=request.getParameter("university");
			int state=Integer.valueOf(request.getParameter("state"));
			Campus campus=new Campus();
			campus.setUniversity(university);
			campus.setProvince(province);
			campus.setState(state);
			witer.write(JsonUtil.toJson(campusBiz.saveCampus(campus)));
		}else if("index_query".equals(type)){
			String jsonStr=JsonUtil.toJson(campusBiz.index_queryCampus());
			witer.write(jsonStr);
		}else if("index_queryName".equals(type)){
			String keyword=request.getParameter("keyword");
			String jsonStr=JsonUtil.toJson(campusBiz.index_queryNameCampus(keyword));
			witer.write(jsonStr);
		}			
		witer.flush();
		witer.close();	
		return null;
	}
}
