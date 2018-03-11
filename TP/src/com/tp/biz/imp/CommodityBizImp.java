package com.tp.biz.imp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tp.biz.CommodityBiz;
import com.tp.dao.CampusDao;
import com.tp.dao.ClassificationDao;
import com.tp.dao.CommodityDao;
import com.tp.dao.CommoditypathDao;
import com.tp.dao.imp.CampusDaoImp;
import com.tp.dao.imp.ClassificationDaoImp;
import com.tp.dao.imp.CommoditypathDaoImp;
import com.tp.entity.Campus;
import com.tp.entity.Classification;
import com.tp.entity.Commodity;
import com.tp.entity.Commoditypath;
import com.tp.entity.Users;
import com.tp.vo.Goods;
public class CommodityBizImp implements CommodityBiz{
	private CommodityDao commodityDao;
	private CommoditypathDao commoditypathDao;
	public void setCommoditypathDao(CommoditypathDao commoditypathDao) {
		this.commoditypathDao = commoditypathDao;
	}

	public void setCampusDao(CampusDao campusDao) {
		this.campusDao = campusDao;
	}

	public void setClassificationDao(ClassificationDao classificationDao) {
		this.classificationDao = classificationDao;
	}

	private CampusDao campusDao;
	private ClassificationDao classificationDao;
	public void setCommodityDao(CommodityDao commodityDao) {
		this.commodityDao = commodityDao;
	}

	@Override
	public List<Map<String,Object>> queryCommodity(int pageNumber,int pageSize) {
		List<Commodity>list=commodityDao.queryCommodity(pageNumber,pageSize);
		return toMap(list);
	}

	@Override
	public List<Map<String, Object>> queryCommodity(int id) {
		List<Commodity>list=commodityDao.queryCommodity(id);
		return toMap(list);
	}

	@Override
	public List<Map<String, Object>> queryCommodity(String name) {
		List<Commodity>list=commodityDao.queryCommodity(name);
		return toMap(list);
	}
	public List<Map<String, Object>> toMap(List<Commodity>list){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
		for(int i=0;i<list.size();i++){
			Map<String,Object>map=new HashMap<String, Object>();
			map.put("id",list.get(i).getId());
			map.put("name",list.get(i).getName());
			map.put("originalPrice",list.get(i).getOriginalPrice());
			map.put("price",list.get(i).getPrice());
			map.put("number",list.get(i).getNumber());
			map.put("degree",list.get(i).getDegree());
			map.put("describe",list.get(i).getDescribes());
			map.put("type",list.get(i).getClassification().getName());
			map.put("editTime",list.get(i).getEditTimes());
			map.put("releaseTime",list.get(i).getReleaseTimes()); 
			if(list.get(i).getUsers()!=null){
				map.put("usersId",list.get(i).getUsers().getId()); 
			}else{
				map.put("usersId",""); 
			}
			Set<Commoditypath>set=list.get(i).getCommoditypaths();
			Iterator<Commoditypath>it=set.iterator();
			List<String>path=new ArrayList<String>();
			String indexPath="";
			while(it.hasNext()){
				Commoditypath commoditypath=it.next();
				if(commoditypath!=null){
					if(commoditypath.getIndexNumber()==0){
						indexPath=commoditypath.getPath();
					}
					path.add(commoditypath.getPath());
				}
			}	
			map.put("path",path); 
			map.put("indexPath",indexPath); 
			map.put("btId",list.get(i).getId()); 
			if(list.get(i).getShelfState()==1){
				map.put("shelfState","已下架");
			}else{
				map.put("shelfState","已上架");
			}	
			result.add(map);
		}
		return result;
	}

	@Override
	public int updateCommodity(String id) {
		Commodity commodity=commodityDao.queryCommodity(Integer.valueOf(id)).get(0);
		commodity.setShelfState(Integer.valueOf(1));
		return commodityDao.updateCommodity(commodity);
	}

	@Override
	public void updateCommodity(String[] ids) {
		for(int i=0;i<ids.length;i++){
			updateCommodity(ids[i]);
		}
	}

	@Override
	public int queryCommodity() {		
		return commodityDao.queryCommodity().size();
	}

	@Override
	public List<Map<String, Object>> queryCommoditys() {	
		List<Commodity>list=commodityDao.queryCommodity();
		return toMap(list);
	}

	@Override
	public List<Map<String, Object>> queryCommodity(String name,
			int pageNumber, int pageSize) {
		List<Commodity>list=commodityDao.queryCommodity(name,pageNumber,pageSize);
		return toMap(list);
	}

	@Override
	public int postCommodity(Goods goods,Users users) {
		try {
			Campus campus=campusDao.queryCampus(Integer.parseInt(goods.getSchool())).get(0);
			Classification classification=classificationDao.queryClassification(Integer.parseInt(goods.getItem())).get(0);
			Commodity commodity=new Commodity();
			commodity.setClassification(classification);
			commodity.setCampus(campus);
			commodity.setDegree(goods.getDegree());
			commodity.setDescribes(goods.getDescribes());
			commodity.setName(goods.getName());
			commodity.setOriginalPrice(Double.parseDouble(goods.getOriginalPrice()));
			commodity.setNumber(Integer.parseInt(goods.getNumber()));
			commodity.setPrice(Double.parseDouble(goods.getPrice()));
			commodity.setShelfState(Integer.parseInt(goods.getShelfState()));
			commodity.setEditTimes(new Date(System.currentTimeMillis()));
			commodity.setUsers(users);
			commodityDao.saveCommodity(commodity);
			savePathPic(goods.getUrlPic(),getPath(goods.getUrlInfo()),commodity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	private String[] getPath(String urlPath){
		String[] urlPaths=urlPath.split(",");
		return urlPaths;
	}
	
	private void savePathPic(String urlPic,String[] urlPath,Commodity commodity){
		Commoditypath commoditypath0=new Commoditypath();
		commoditypath0.setCommodity(commodity);
		commoditypath0.setPath(urlPic);
		commoditypath0.setIndexNumber(0);
		commoditypathDao.saveCommoditypath(commoditypath0);
		for(String url:urlPath){
			Commoditypath commoditypath1=new Commoditypath();
			commoditypath1.setCommodity(commodity);
			commoditypath1.setPath(url);
			commoditypath1.setIndexNumber(1);
			commoditypathDao.saveCommoditypath(commoditypath1);
		}
	}

	@Override
	public List<Map<String, Object>> queryCommodity(String schoolName,
			String className, int pageNumber, int pageSize) {
		List<Commodity>list=commodityDao.queryCommodity(schoolName,className,pageNumber,pageSize);
		return toMap(list);
	}

	@Override
	public List<Map<String, Object>> queryCommodity(String keyName,
			String schoolName, String className, int pageNumber, int pageSize) {
		List<Commodity>list=commodityDao.queryCommodity(keyName,schoolName,className,pageNumber,pageSize);
		return toMap(list);
	}

	@Override
	public int queryCommodityAccout(String schoolName, String className) {
		List<Commodity>list=commodityDao.queryCommodityAccout(schoolName,className);
		if(list!=null){
			return list.size();
		}
		return 0;
	}

	@Override
	public int queryCommodityAccout(String keyName, String schoolName,
			String className) {
		List<Commodity>list=commodityDao.queryCommodityAccout(keyName,schoolName,className);
		if(list!=null){
			return list.size();
		}
		return 0;
	}
}