package com.tp.dao;
import java.util.List;
import com.tp.entity.Commoditypath;
public interface CommoditypathDao{
	//商品图片路径表
	List<Commoditypath>queryCommoditypath();
	List<Commoditypath>queryCommoditypaths(int id);
	Commoditypath queryCommoditypath(int id);
	Commoditypath queryCommoditypathCommodity(int id);
	int saveCommoditypath(Commoditypath commoditypath);
	int updateCommoditypath(Commoditypath commoditypath);
	int deleteCommoditypath(Commoditypath commoditypath);
}
