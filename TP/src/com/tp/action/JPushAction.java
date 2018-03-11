package com.tp.action;
import java.util.List;
import java.util.Map;
import com.tp.biz.JPushBiz;
import com.tp.entity.JpushInfo;
import com.tp.tools.JsonUtil;
public class JPushAction extends ObjectAction{
	private JPushBiz jPushBiz;
	private JpushInfo jpushInfo;
	public JpushInfo getJpushInfo() {
		return jpushInfo;
	}
	public void setJpushInfo(JpushInfo jpushInfo) {
		this.jpushInfo = jpushInfo;
	}
	public void setjPushBiz(JPushBiz jPushBiz) {
		this.jPushBiz = jPushBiz;
	}
	public JPushAction(){
		
	}
	public String execute(){
		String jsonResult=getRequest().getParameter("jsonResult");
		String jsonKeyResult=getRequest().getParameter("jsonKeyResult");
		setJpushInfo((JpushInfo)JsonUtil.toEntity(jsonKeyResult, JpushInfo.class));
		List<Map<String,Object>> list=JsonUtil.toList(jsonResult);
		getPrintWriter().write(JsonUtil.toJson(jPushBiz.sendJPush(jpushInfo, list)));
		writerClose();
		return null;
	}
}
