package com.tp.action;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.tp.biz.PlatformBiz;
import com.tp.entity.Campus;
import com.tp.entity.Platform;
import com.tp.tools.FormatTools;
import com.tp.tools.JsonUtil;
public class PlatformAction {
	private PlatformBiz platformBiz;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public void setPlatformBiz(PlatformBiz platformBiz) {
		this.platformBiz = platformBiz;
	}
	public PlatformAction(){
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
			result=platformBiz.queryPlatform(Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
			map.put("total", platformBiz.queryPlatform().size());
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("selete".equals(type)){
			String typeName=request.getParameter("typeName");
			String typeValue=request.getParameter("typeValue");
			System.out.println("typeName="+typeName);
			System.out.println("typeValue="+typeValue);
			if("id".equals(typeName) && !"".equals(typeValue)){			
				result=platformBiz.queryPlatform(Integer.valueOf(typeValue));
			}else if("oId".equals(typeName) && !"".equals(typeValue)){
				result=platformBiz.queryOPlatform(Integer.valueOf(typeValue));
			}else if("sUId".equals(typeName) && !"".equals(typeValue)){
				result=platformBiz.querySPlatform(Integer.valueOf(typeValue));
			}else if("gUId".equals(typeName) && !"".equals(typeValue)){
				result=platformBiz.queryGPlatform(Integer.valueOf(typeValue));
			}else{
				result=platformBiz.queryPlatform();
			}
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("total", result.size());
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("delete".equals(type)){
			if(!"".equals(request.getParameter("check"))){
				String[] check=request.getParameter("check").split(",");
				platformBiz.deletePlatform(check);
			}		
			witer.write(JsonUtil.toJson(null));
		}else if("detail".equals(type)){
			String showId=request.getParameter("showId");
			result=platformBiz.queryPlatform(Integer.valueOf(showId));
			request.setAttribute("list",result);
			witer.write(JsonUtil.toJson(null));
			return "detail";
		}else if("update".equals(type)){
			int id=Integer.valueOf(request.getParameter("id"));
			String price=request.getParameter("price");
			String invalidTime=request.getParameter("invalidTime");
			Platform platform=platformBiz.platform(id);
			platform.setPrice(Double.valueOf(price));
			platform.setInvalidTime(FormatTools.FormateTime(invalidTime));
			witer.write(JsonUtil.toJson(platformBiz.updatePlatform(platform)));
		}	
		witer.flush();
		witer.close();	
		return null;
	}
}
