package com.tp.biz;
import java.util.List;
import java.util.Map;
import com.tp.entity.Report;
public interface ReportBiz {
	List<Map<String,Object>>queryReport(int pageNumber,int pageSize);
	List<Map<String,Object>> queryReport();
	List<Map<String,Object>>queryReport(int id);
	List<Map<String,Object>>queryCReport(int cId);
	List<Map<String,Object>>queryUReport(int uId);
	void deleteReport(String[] check);
	Report report(int id);	
}
