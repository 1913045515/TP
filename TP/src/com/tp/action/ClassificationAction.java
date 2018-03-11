package com.tp.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.tp.biz.ClassificationBiz;
import com.tp.entity.Classification;
import com.tp.tools.JsonUtil;
public class ClassificationAction {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ClassificationBiz classificationBiz;
	public void setClassificationBiz(ClassificationBiz classificationBiz) {
		this.classificationBiz = classificationBiz;
	}

	public ClassificationAction(){
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
			result=classificationBiz.queryClassification(Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
			map.put("total", classificationBiz.queryClassification().size());
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("index_all".equals(type)){
			result=classificationBiz.queryClassification();
			witer.write(JsonUtil.toJson(result));
		}else if("selete".equals(type)){
			String typeName=request.getParameter("typeName");
			String typeValue=request.getParameter("typeValue");
			if("id".equals(typeName) && !"".equals(typeValue)){			
				result=classificationBiz.queryClassification(Integer.valueOf(typeValue));
			}else if("name".equals(typeName) && !"".equals(typeValue)){
				result=classificationBiz.queryClassification(typeValue);
			}else{
				result=classificationBiz.queryClassification();
			}
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("total", result.size());
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("delete".equals(type)){
			if(!"".equals(request.getParameter("check"))){
				String[] check=request.getParameter("check").split(",");
				//classificationBiz.deleteClassifications(check);
			}		
			witer.write(JsonUtil.toJson(null));
		}else if("detail".equals(type)){
			String showId=request.getParameter("showId");
			result=classificationBiz.queryClassification(Integer.valueOf(showId));
			request.setAttribute("list",result);
			witer.write(JsonUtil.toJson(null));
			return "detail";
		}else if("update".equals(type)){
			int id=Integer.valueOf(request.getParameter("id"));
			String name=request.getParameter("name");
			String describle=request.getParameter("describle");
			String path=request.getParameter("path");
			Classification classification=classificationBiz.classification(id);
			classification.setName(name);
			classification.setDescrible(describle);
			classification.setPath(path);
			witer.write(JsonUtil.toJson(classificationBiz.updateClassifications(classification)));
		}else if("save".equals(type)){
			String name=request.getParameter("name");
			String describle=request.getParameter("describle");
			String path=request.getParameter("path");
			Classification classification=new Classification();
			classification.setName(name);
			classification.setDescrible(describle);
			classification.setPath(path);
			witer.write(JsonUtil.toJson(classificationBiz.saveClassifications(classification)));
		}		
		witer.flush();
		witer.close();	
		return null;
	}
}
