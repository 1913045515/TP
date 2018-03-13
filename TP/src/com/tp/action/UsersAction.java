package com.tp.action;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.tp.jpush.api.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.tp.biz.CampusBiz;
import com.tp.biz.UsersBiz;
import com.tp.entity.Users;
import com.tp.tools.CodeUtil;
import com.tp.tools.JsonUtil;
public class UsersAction {
	private UsersBiz usersBiz;
	private HttpServletRequest request;
	private HttpServletResponse response;
	public void setUsersBiz(UsersBiz usersBiz) {
		this.usersBiz = usersBiz;
	}
	public UsersAction(){
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
	//后台的用户登入
	public void login(String usersName,String password){
		Map<String,Object> map=usersBiz.loginMange(usersName, password);
		int result=(int)map.get("result");
		Users users=(Users)map.get("resultValue");
		request.getSession().setAttribute("result",result);
		if(result==3){
			request.getSession().setAttribute("users",users);	
		}
	}
	
	//前台的用户登录
	public void loginUsers(String usersName,String password){
		Map<String,Object> map=usersBiz.loginUser(usersName, password);
		int result=(int)map.get("result");
		Users users=(Users)map.get("resultValue");
		request.getSession().setAttribute("result",result);
		if(result==3){
			request.getSession().setAttribute("indexUsers",users);	
		}
	}
	
	public void modify(int id,String longPwd,String newPwd){
		request.setAttribute("flag",usersBiz.modify(id, longPwd, newPwd));
	}
	public String execute() throws Exception{
		String flag=request.getParameter("method");
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		PrintWriter witer = response.getWriter();
		if("login".equals(flag)){
			Map<String,Object>map=new HashMap<String, Object>();
			String usersName=request.getParameter("usersName");
			String password=request.getParameter("password");
			String validcode=request.getParameter("validcode");
			String rand=(String)request.getSession().getAttribute("rand");	
			System.out.println("rand="+rand+"   validcode="+validcode);
			if(validcode.equals(rand)){
				login(usersName,password);
				map.put("result",request.getSession().getAttribute("result"));	
			}else{
				map.put("error","error");
			}	
			witer.write(JsonUtil.toJson(map));
		}else if("loginUser".equals(flag)){
			Map<String,Object>map=new HashMap<String, Object>();
			String usersName=request.getParameter("usersName");
			String password=request.getParameter("password");
			String validcode=request.getParameter("validcode");
			String rand=(String)request.getSession().getAttribute("rand");	
			System.out.println("rand="+rand+"   validcode="+validcode);
			if(validcode.equals(rand)){
				loginUsers(usersName,password);
				map.put("result",request.getSession().getAttribute("result"));
				map.put("error","noError");
			}else{
				map.put("error","error");
			}
			System.out.println(JsonUtil.toJson(map));
			witer.write(JsonUtil.toJson(map));
		}else if("modify".equals(flag)){
			int id=Integer.valueOf(request.getParameter("id"));
			String longPwd=request.getParameter("longPwd");
			String newPwd=request.getParameter("newPwd");
			modify(id,longPwd,newPwd);
			witer.write(JsonUtil.toJson("true"));
		}else if("all".equals(flag)){
			String pageSize=request.getParameter("pageSize");
			String pageNumber=request.getParameter("pageNumber");
			witer.write(JsonUtil.toJson(usersBiz.queryUsers(Integer.valueOf(pageNumber),Integer.valueOf(pageSize))));
		}else if("select".equals(flag)){
			String typeName=request.getParameter("typeName");
			String typeValue=request.getParameter("typeValue");
			if("id".equals(typeName) && !"".equals(typeValue)){			
				result=usersBiz.queryUsers(Integer.valueOf(typeValue));
			}else if("name".equals(typeName) && !"".equals(typeValue)){
				result=usersBiz.queryUsers(typeValue);
			}else{
				result=usersBiz.queryUsers();
			}
			Map<String,Object>map=new HashMap<String,Object>();
			map.put("total", result.size());
			map.put("rows",result);
			witer.write(JsonUtil.toJson(map));	
		}else if("detail".equals(flag)){			
			int id=Integer.valueOf(request.getParameter("id"));
			request.setAttribute("list",usersBiz.queryUsers(id));
			return "detail";			
		}else if("userdetail".equals(flag)){
			if(request.getParameter("userID")!=null && !"".equals(request.getParameter("userID"))){
				int userID=Integer.valueOf(request.getParameter("userID"));
				String json=JsonUtil.toJson(usersBiz.queryUsers(userID));
				witer.write(json);
			}else{
				List<Users> list=new ArrayList<>();
				witer.write(JsonUtil.toJson(list));
			}
		}else if("update".equals(flag)){	
			int id=Integer.valueOf(request.getParameter("id"));
			String name=request.getParameter("name");
			String password=request.getParameter("password");
			String phone=request.getParameter("phone");
			String acount=request.getParameter("acount");
			String address=request.getParameter("address");
			Users users=usersBiz.users(id).get(0);
			users.setNickName(name);
			users.setPassword(password);
			users.setPhone(phone);
			users.setAcount(acount);
			users.setAddress(address);		
			witer.write(JsonUtil.toJson(usersBiz.update(users)));	
		}else if("getOut".equals(flag)){	
			request.getSession().setAttribute("users",null);
			return "login";
		}else if("register".equals(flag)){
			Map<String,Object>map=new HashMap<String, Object>();
			String usersName=request.getParameter("usersName");
			String password=request.getParameter("password");
			String email=request.getParameter("email");
			String school=request.getParameter("school");
			String validcode=request.getParameter("validcode");
			String rand=(String)request.getSession().getAttribute("rand");	
			System.out.println("rand="+rand+"   validcode="+validcode);
			if(validcode.equals(rand)){
				witer.write(JsonUtil.toJson(usersBiz.register(usersName,password,email,school)));
			}else{
				map.put("error","error");//验证码错误
				witer.write(JsonUtil.toJson(map));
			}		
		}else if("userInfo".equals(flag)){	
			Users uers=(Users)request.getSession().getAttribute("indexUsers");
			Map<String,Object>map=new HashMap<String, Object>();
			if(uers!=null){
				map.put("name", uers.getNickName());
				map.put("id", uers.getId());
				map.put("accout", uers.getAcount());
			}
			witer.write(JsonUtil.toJson(map));
		}else if("getIndexOut".equals(flag)){	
			request.getSession().setAttribute("indexUsers",null);
			return "login";
		}
		witer.flush();
		witer.close();
		return null;
	}
}
