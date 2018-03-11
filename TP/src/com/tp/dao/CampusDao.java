package com.tp.dao;
import java.util.List;

import com.tp.entity.Campus;
import com.tp.entity.Classification;
public interface CampusDao {
	//校区接口
	List<Campus>queryCampus();
	List<Campus>queryCampus(int pageNumber,int pageSize);
	List<Campus>queryCampus(int id);
	List<Campus>queryCampus(String name);
	int saveCampus(Campus campus);
	int deleteCampus(Campus campus);
	int updateCampus(Campus campus);
}
