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

import com.google.gson.Gson;
import com.tp.biz.CommodityBiz;
import com.tp.biz.LuceneBiz;
import com.tp.entity.Users;
import com.tp.tools.IKAnalyzerSearch;
import com.tp.tools.JsonUtil;
import com.tp.vo.Goods;
public class CommodityAction {
	private CommodityBiz commodityBiz;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private LuceneBiz luceneBiz;
	public void setLuceneBiz(LuceneBiz luceneBiz) {
		this.luceneBiz = luceneBiz;
	}
	public void setCommodityBiz(CommodityBiz commodityBiz) {
		this.commodityBiz = commodityBiz;
	}
	public CommodityAction(){
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
			String schoolId=request.getParameter("schoolId");
			String classId=request.getParameter("classId");
			result=commodityBiz.queryCommodity(schoolId,classId,Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
			map.put("total", commodityBiz.queryCommodityAccout(schoolId,classId));
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("backgroundAll".equals(type)){
			Map<String,Object>map=new HashMap<String,Object>();
			String pageSize=request.getParameter("pageSize");
			String pageNumber=request.getParameter("pageNumber");
			result=commodityBiz.queryCommodity(Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
			map.put("total", commodityBiz.queryCommodity());
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("index_selete".equals(type)){
			String typeName=request.getParameter("typeName");
			String pageSize=request.getParameter("pageSize");
			String pageNumber=request.getParameter("pageNumber");
			String schoolId=request.getParameter("schoolId");
			String classId=request.getParameter("classId");
			result=commodityBiz.queryCommodity(typeName,schoolId,classId,Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("total", commodityBiz.queryCommodityAccout(typeName,schoolId,classId));
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("selete".equals(type)){
			String typeName=request.getParameter("typeName");
			String typeValue=request.getParameter("typeValue");
			if("id".equals(typeName) && !"".equals(typeValue)){			
				result=commodityBiz.queryCommodity(Integer.valueOf(typeValue));
				Map<String,Object>map=new HashMap<String,Object>();
				map.put("total", result.size());
				map.put("rows",result);
				witer.write(JsonUtil.toJson(map));
			}else if("name".equals(typeName) && !"".equals(typeValue)){
				result=commodityBiz.queryCommodity(typeValue);
				Map<String,Object>map=new HashMap<String,Object>();
				map.put("total", result.size());
				map.put("rows",result);
				witer.write(JsonUtil.toJson(map));
			}else if("lucene".equals(typeName) && !"".equals(typeValue)){			
				List<String>listStr=IKAnalyzerSearch.iKAnalyzerSearch(typeValue);
				List<Object> list=luceneBiz.queryCommodityDao(listStr);
				Map<String,Object>map=new HashMap<String,Object>();
				map.put("total", result.size());
				map.put("rows",list);
				witer.write(JsonUtil.toJson(map));
			}else{
				result=commodityBiz.queryCommoditys();
				Map<String,Object>map=new HashMap<String,Object>();
				map.put("total", result.size());
				map.put("rows",result);
				witer.write(JsonUtil.toJson(map));
			}
		}else if("delete".equals(type)){
			if(!"".equals(request.getParameter("check"))){
				String[] check=request.getParameter("check").split(",");
				commodityBiz.updateCommodity(check);
			}		
			witer.write(JsonUtil.toJson(null));
		}else if("goodsdetail".equals(type)){
			String goodsID=request.getParameter("goodsID");
			result=commodityBiz.queryCommodity(Integer.valueOf(goodsID));
			if(result==null){
				result=new ArrayList<Map<String, Object>>();
			}
			witer.write(JsonUtil.toJson(result));
		}else if("detail".equals(type)){
			String showId=request.getParameter("showId");
			result=commodityBiz.queryCommodity(Integer.valueOf(showId));
			request.setAttribute("list",result);
			witer.write(JsonUtil.toJson(null));
			return "detail";
		}else if("postGoods".equals(type)){
			String json=request.getParameter("goods");
		     Gson gson = new Gson();  
		     Goods goods = gson.fromJson(json, Goods.class); 
		     int flag=commodityBiz.postCommodity(goods,(Users)request.getSession().getAttribute("indexUsers"));
		     witer.write(flag==1?"true":"false");
		}	
		witer.flush();
		witer.close();	
		return null;
	}
}
