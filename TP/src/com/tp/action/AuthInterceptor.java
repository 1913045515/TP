package com.tp.action;
import javax.servlet.http.HttpSession;  
import org.apache.struts2.ServletActionContext;  
import com.opensymphony.xwork2.Action;  
import com.opensymphony.xwork2.ActionInvocation;  
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;  
import com.tp.entity.Users;
@SuppressWarnings("serial")
public class AuthInterceptor extends AbstractInterceptor{  
  @Override  
  public String intercept(ActionInvocation invocation) throws Exception {  
      HttpSession session = ServletActionContext.getRequest().getSession();  
      Users userName = (Users)session.getAttribute("users");  
      if(userName == null || userName.getAcount()==""){//错误,回到登录界面  
          return Action.LOGIN;  
      }else{  
          return invocation.invoke();  
      }  
  }  
}  