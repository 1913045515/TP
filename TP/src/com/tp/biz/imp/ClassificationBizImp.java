package com.tp.biz.imp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tp.biz.ClassificationBiz;
import com.tp.dao.ClassificationDao;
import com.tp.entity.Classification;
public class ClassificationBizImp implements ClassificationBiz {
	private ClassificationDao classificationDao;

	public void setClassificationDao(ClassificationDao classificationDao) {
		this.classificationDao = classificationDao;
	}

	@Override
	public List<Map<String, Object>> queryClassification(int pageNumber,
			int pageSize) {		
		return toMap(classificationDao.queryClassification(pageNumber, pageSize));
	}

	@Override
	public List<Map<String, Object>> queryClassification() {
		return toMap(classificationDao.queryClassification());
	}

	@Override
	public List<Map<String, Object>> queryClassification(int id) {		
		return toMap(classificationDao.queryClassification(id));
	}

	@Override
	public List<Map<String, Object>> queryClassification(String name) {
		return toMap(classificationDao.queryClassification(name));
	}
	
	public List<Map<String, Object>> toMap(List<Classification>list){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
		for(int i=0;i<list.size();i++){
			Map<String,Object>map=new HashMap<String, Object>();
			map.put("id",list.get(i).getId());
			map.put("name",list.get(i).getName());
			map.put("describle",list.get(i).getDescrible());
			map.put("path",list.get(i).getPath());
			map.put("btId",list.get(i).getId());
			result.add(map);
		}
		return result;
	}

	public int deleteClassification(int id) {
		return classificationDao.deleteClassification(
				classificationDao.queryClassification(id).get(0));
	}

	@Override
	public void deleteClassifications(String[] check) {
		for(int i=0;i<check.length;i++){
			deleteClassification(Integer.valueOf(check[i]));
		}
	}

	@Override
	public int updateClassifications(Classification classification) {	
		return classificationDao.updateClassification(classification);
	}

	@Override
	public int saveClassifications(Classification classification) {
		return classificationDao.saveClassification(classification);
	}

	@Override
	public Classification classification(int id) {
		return classificationDao.queryClassification(id).get(0);
	}
}
