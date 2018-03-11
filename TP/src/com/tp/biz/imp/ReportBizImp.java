package com.tp.biz.imp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tp.biz.ReportBiz;
import com.tp.dao.ReportDao;
import com.tp.entity.Report;
public class ReportBizImp implements ReportBiz{
	private ReportDao reportDao;

	public void setReportDao(ReportDao reportDao) {
		this.reportDao = reportDao;
	}
	
	@Override
	public List<Map<String, Object>> queryReport(int pageNumber, int pageSize) {		
		return toMap(reportDao.queryReport(pageNumber, pageSize));
	}

	@Override
	public List<Map<String, Object>> queryReport() {
		return toMap(reportDao.queryReport());
	}

	@Override
	public List<Map<String, Object>> queryReport(int id) {
		return toMap(reportDao.queryReport(id));
	}

	@Override
	public List<Map<String, Object>> queryCReport(int cId) {
		return toMap(reportDao.queryCReport(cId));
	}

	@Override
	public List<Map<String, Object>> queryUReport(int uId) {
		return toMap(reportDao.queryUReport(uId));
	}

	@Override
	public Report report(int id) {
		return reportDao.queryReport(id).get(0);
	}
	
	public List<Map<String, Object>> toMap(List<Report>list){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
		for(int i=0;i<list.size();i++){
			Map<String,Object>map=new HashMap<String, Object>();
			map.put("id",list.get(i).getId());
			map.put("cId",list.get(i).getCommodity().getId());
			map.put("content",list.get(i).getContent());
			map.put("uId",list.get(i).getUsers().getId());
			map.put("time",list.get(i).getTime());
			result.add(map);
		}
		return result;
	}

	@Override
	public void deleteReport(String[] check) {
		for(int i=0;i<check.length;i++){
			deleteReport(Integer.valueOf(check[i]));
		}
	}
	
	public int deleteReport(int id){
		return reportDao.deleteReport(reportDao.queryReport(id).get(0));
	}
}
