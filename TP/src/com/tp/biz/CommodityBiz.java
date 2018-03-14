package com.tp.biz;
import java.util.List;
import java.util.Map;

import com.tp.entity.Users;
import com.tp.vo.Goods;
public interface CommodityBiz {
	public List<Map<String,Object>>queryCommodity(int pageNumber,int pageSize);
	public List<Map<String,Object>> queryCommoditys();
	public int queryCommodity();
	public List<Map<String,Object>>queryCommodity(int id);
	public List<Map<String,Object>>queryCommodity(String name);
	public List<Map<String,Object>>queryCommodity(String name,int pageNumber,int pageSize);
	public List<Map<String,Object>>queryCommodity(String schoolName,String className,int pageNumber,int pageSize);
	public List<Map<String,Object>>queryCommodity(String keyName,String schoolName,String className,int pageNumber,int pageSize);
	public int queryCommodityAccout(String schoolName,String className);
	public int queryCommodityAccout(String keyName,String schoolName,String className);
	int updateCommodity(String id,int state);
	int postCommodity(Goods goods,Users user);
	void updateCommodity(String[] ids,int state);
}
