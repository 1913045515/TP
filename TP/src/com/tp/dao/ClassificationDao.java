package com.tp.dao;
import java.util.List;

import com.tp.entity.Classification;
public interface ClassificationDao {
	//分类接口
	List<Classification>queryClassification();
	List<Classification>queryClassification(int pageNumber,int pageSize);
	List<Classification>queryClassification(int id);
	List<Classification>queryClassification(String name);
	int saveClassification(Classification classification);
	int deleteClassification(Classification classification);
	int updateClassification(Classification classification);
}
