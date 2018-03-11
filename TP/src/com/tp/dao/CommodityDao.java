package com.tp.dao;
import java.util.List;

import com.tp.entity.Commodity;
public interface CommodityDao {
	//商品接口
	List<Commodity>queryCommodity(int pageNumber,int pageSize);
	List<Commodity>queryCommodity(String name,int pageNumber,int pageSize);
	List<Commodity>queryCommodity(String keyname,String schoolName,String className,int pageNumber,int pageSize);
	List<Commodity>queryCommodity(String schoolName,String className,int pageNumber,int pageSize);
	List<Commodity> queryCommodityAccout(String schoolName,String className);
	List<Commodity> queryCommodityAccout(String keyName,String schoolName,String className);
	List<Commodity>queryCommodity();
	List<Commodity>queryCommodity(int id);
	List<Commodity>queryCommodity(String name);
	int saveCommodity(Commodity commodity);
	int deleteCommodity(Commodity commodity);
	int updateCommodity(Commodity commodity);
}
