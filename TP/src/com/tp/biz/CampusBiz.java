package com.tp.biz;
import java.util.List;
import java.util.Map;
import com.tp.entity.Campus;
public interface CampusBiz {
	List<Map<String,Object>>queryCampus();
	List<Map<String,Object>>index_queryNameCampus(String keyword);
	List<Map<String,Object>>index_queryCampus();
	List<Map<String,Object>>queryCampus(int pageNumber,int pageSize);
	List<Map<String,Object>>queryCampus(int id);
	List<Map<String,Object>>queryCampus(String name);
	Campus campus(int id);
	int saveCampus(Campus campus);
	void deleteCampus(String[] check);
	int updateCampus(Campus campus);
}
