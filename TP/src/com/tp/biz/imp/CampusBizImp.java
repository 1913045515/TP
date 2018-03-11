package com.tp.biz.imp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tp.biz.CampusBiz;
import com.tp.dao.CampusDao;
import com.tp.entity.Campus;
public class CampusBizImp implements CampusBiz {
	private CampusDao campusDao;

	public void setCampusDao(CampusDao campusDao) {
		this.campusDao = campusDao;
	}
	
	public List<Map<String, Object>> toMap(List<Campus>list){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
		for(int i=0;i<list.size();i++){
			Map<String,Object>map=new HashMap<String, Object>();
			map.put("id",list.get(i).getId());
			map.put("province",list.get(i).getProvince());
			map.put("university",list.get(i).getUniversity());
			if(list.get(i).getState()==1){
				map.put("state","已经开通");
			}else{
				map.put("state","未开通");
			}
			
			map.put("btId",list.get(i).getId());
			result.add(map);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryCampus() {
		return toMap(campusDao.queryCampus());
	}

	@Override
	public List<Map<String, Object>> queryCampus(int pageNumber, int pageSize) {
		return toMap(campusDao.queryCampus(pageNumber,pageSize));
	}

	@Override
	public List<Map<String, Object>> queryCampus(int id) {
		return toMap(campusDao.queryCampus(id));
	}

	@Override
	public List<Map<String, Object>> queryCampus(String name) {
		return toMap(campusDao.queryCampus(name));
	}

	@Override
	public Campus campus(int id) {
		return campusDao.queryCampus(id).get(0);
	}

	@Override
	public int saveCampus(Campus campus) {
		return campusDao.saveCampus(campus);
	}

	@Override
	public void deleteCampus(String[] check) {
		for(int i=0;i<check.length;i++){
			deleteCampus(Integer.valueOf(check[i]));
		}
	}

	@Override
	public int updateCampus(Campus campus) {
		return campusDao.updateCampus(campus);
	}
	
	public int deleteCampus(int id){
		return campusDao.deleteCampus(campusDao.queryCampus(id).get(0));
	}

	@Override
	public List<Map<String, Object>> index_queryCampus() {
		List<Campus>list=campusDao.queryCampus();
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<String>proList=new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			if(proList.size()==0){
				proList.add(list.get(i).getProvince());
			}else{
				int flag=0;
				for(int ii=0;ii<proList.size();ii++){
					if(list.get(i).getProvince().equals(proList.get(ii))){
						flag=1;
					}
				}
				if(flag==0){
					proList.add(list.get(i).getProvince());
				}
			}
		}
		Map<String,Object>map=null;
		for(int i=0;i<proList.size();i++){
			map=new HashMap<String,Object>();
			String proNmae=proList.get(i);
			map.put("province", proNmae);
			List<String>schoolList=new ArrayList<String>();
			for(int j=0;j<list.size();j++){
				if(proNmae.equals(list.get(j).getProvince())){
					schoolList.add(list.get(j).getUniversity());
				}
			}
			map.put("school", schoolList);
			result.add(map);
		}
		return result;
	}

	@Override
	public List<Map<String,Object>> index_queryNameCampus(String keyword) {
		return queryCampus(keyword);
	}
}
