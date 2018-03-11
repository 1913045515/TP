package com.tp.dao.imp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tp.dao.PromotionDao;
import com.tp.entity.Promotion;
public class PromotionDaoImp implements PromotionDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public PromotionDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Promotion> queryPromotion() {
		String hql="From Promotion";
		return getSession().createQuery(hql).list();
	}
	@Override
	public Promotion queryPromotion(int id) {
		Promotion Promotion=getSession().get(Promotion.class, id);
		return Promotion;
	}
	@Override
	public int savePromotion(Promotion promotion) {
		try {
			getSession().save(promotion);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deletePromotion(Promotion promotion) {
		try {
			getSession().delete(promotion);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updatePromotion(Promotion promotion) {
		try {
			getSession().update(promotion);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
