package com.tp.dao;
import java.util.List;
import com.tp.entity.Promotioncommodity;
public interface PromotioncommodityDao {
	//促销活动商品接口
	List<Promotioncommodity>queryPromotioncommodity();
	Promotioncommodity queryPromotioncommodity(int id);
	int savePromotioncommodity(Promotioncommodity promotioncommodity);
	int deletePromotioncommodity(Promotioncommodity promotioncommodity);
	int updatePromotioncommodity(Promotioncommodity promotioncommodity);
}
