package com.tp.dao;
import java.util.List;
import com.tp.entity.Report;
public interface ReportDao {
	//举报接口
	List<Report>queryReport(int pageNumber,int pageSize);
	List<Report>queryReport();
	List<Report>queryReport(int id);
	List<Report>queryCReport(int cId);
	List<Report>queryUReport(int uId);
	int saveReport(Report report);
	int deleteReport(Report report);
	int updateReport(Report report);
}
