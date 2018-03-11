package com.tp.action;
import java.util.Map;

import com.tp.biz.MailBiz;
import com.tp.biz.UsersBiz;
import com.tp.tools.JsonUtil;
public class EmailAction extends ObjectAction{
	private UsersBiz usersBiz;
	private MailBiz mailBiz;
	public void setUsersBiz(UsersBiz usersBiz) {
		this.usersBiz = usersBiz;
	}
	
	public void setMailBiz(MailBiz mailBiz) {
		this.mailBiz = mailBiz;
	}

	public EmailAction(){
		
	}
	
	public String execute() throws Exception{
		String type=getRequest().getParameter("type");
		if("tree".equals(type)){
			getPrintWriter().write(JsonUtil.toJson(usersBiz.queryEmail()));
			writerClose();
		}else if("eamil".equals(type)){
			String usersId=getRequest().getParameter("usersId");
			String themes=getRequest().getParameter("themes");
			String content=getRequest().getParameter("content");
			String[] usersIds= usersId.split(";");
			boolean flag=mailBiz.sendMail(usersIds, themes, content);
			getPrintWriter().write(JsonUtil.toJson(flag));
			writerClose();
		}else if("getEmail".equals(type)){		
			getPrintWriter().write(JsonUtil.toJson(mailBiz.getMail()));
			writerClose();
		}else if("detail".equals(type)){	
			String result=getRequest().getParameter("result");
			Map<String,Object> map=JsonUtil.toObject(result);
			getRequest().setAttribute("resultValue",map);
			return "reply";
		}
		return null;
	}
}
