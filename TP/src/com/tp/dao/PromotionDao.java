package com.tp.dao;
import java.util.List;
import com.tp.entity.Promotion;
public interface PromotionDao {
	//促销活动接口
	List<Promotion>queryPromotion();
	Promotion queryPromotion(int id);
	int savePromotion(Promotion promotion);
	int deletePromotion(Promotion promotion);
	int updatePromotion(Promotion promotion);
}
