package com.tp.action;
import java.util.Map;
import com.tp.biz.StatisticsBiz;
import com.tp.tools.JsonUtil;
public class StatisticsAction extends ObjectAction{
	private StatisticsBiz statisticsBiz;
	public void setStatisticsBiz(StatisticsBiz statisticsBiz) {
		this.statisticsBiz = statisticsBiz;
	}
	public String execute(){
		String year=getRequest().getParameter("year");
		String month=getRequest().getParameter("month");
		if(year!=null && year!="" && month!=null && month!=""){
			typeStatics(year,month);
			return null;
		}else{
			typeStatics();
			return null;
		}
	}
	
	public void typeStatics(){
		Map<String,Object> map=statisticsBiz.typeStatics();
		getPrintWriter().write(JsonUtil.toJson(map));
		writerClose();
	}
	
	public void typeStatics(String year,String month){
		Map<String,Object> map=statisticsBiz.typeStatics(year,month);
		getPrintWriter().write(JsonUtil.toJson(map));
		writerClose();
	}
}
