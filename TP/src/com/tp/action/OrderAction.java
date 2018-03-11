package com.tp.action;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import com.tp.biz.OrderBiz;
import com.tp.entity.Order;
import com.tp.tools.FormatTools;
import com.tp.tools.JsonUtil;
public class OrderAction {
	private OrderBiz orderBiz;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public void setOrderBiz(OrderBiz orderBiz) {
		this.orderBiz = orderBiz;
	}
	
	public OrderAction(){
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
			result=orderBiz.queryOrder(Integer.valueOf(pageNumber),Integer.valueOf(pageSize));
			map.put("total", orderBiz.queryOrder().size());
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("selete".equals(type)){
			String typeName=request.getParameter("typeName");
			String typeValue=request.getParameter("typeValue");
			if("oId".equals(typeName) && !"".equals(typeValue)){			
				result=orderBiz.queryOOrder(Integer.valueOf(typeValue));
			}else if("cId".equals(typeName) && !"".equals(typeValue)){
				result=orderBiz.queryCOrder(Integer.valueOf(typeValue));
			}else if("uId".equals(typeName) && !"".equals(typeValue)){
				result=orderBiz.queryUOrder(Integer.valueOf(typeValue));
			}else{
				result=orderBiz.queryOrder();
			}
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("total", result.size());
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));
		}else if("delete".equals(type)){
			if(!"".equals(request.getParameter("check"))){
				String[] check=request.getParameter("check").split(",");
				orderBiz.deleteOrder(check);
			}		
			witer.write(JsonUtil.toJson(null));
		}else if("detail".equals(type)){
			String showId=request.getParameter("showId");
			result=orderBiz.queryOrder(Integer.valueOf(showId));
			request.setAttribute("list",result);
			witer.write(JsonUtil.toJson(null));
			return "detail";
		}else if("update".equals(type)){
			int oId=Integer.valueOf(request.getParameter("oId"));
			String name=request.getParameter("name");
			Double price=Double.valueOf(request.getParameter("price"));
			String state=request.getParameter("state");
			String remarks=request.getParameter("remarks");
			Date time=FormatTools.FormateTime(request.getParameter("time"));
			int number=Integer.valueOf(request.getParameter("number"));
			Order order=orderBiz.order(oId);
			order.setName(name);
			order.setPrice(price);
			order.setNumber(number);
			order.setRemarks(remarks);
			order.setState(state);
			order.setTime(time);
			witer.write(JsonUtil.toJson(orderBiz.updateOrder(order)));
		}		
		witer.flush();
		witer.close();	
		return null;
	}
}
