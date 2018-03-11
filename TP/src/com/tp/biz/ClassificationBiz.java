package com.tp.biz;
import java.util.List;
import java.util.Map;

import com.tp.entity.Classification;
public interface ClassificationBiz {
	List<Map<String,Object>>queryClassification(int pageNumber,int pageSize);
	List<Map<String,Object>> queryClassification();
	List<Map<String,Object>>queryClassification(int id);
	Classification classification(int id);
	List<Map<String,Object>>queryClassification(String name);
	void deleteClassifications(String[] check);
	int updateClassifications(Classification classification);
	int saveClassifications(Classification classification);
}
