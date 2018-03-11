package com.tp.dao.imp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tp.dao.PromotioncommodityDao;
import com.tp.entity.Promotioncommodity;
public class PromotioncommodityDaoImp implements PromotioncommodityDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public PromotioncommodityDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Promotioncommodity> queryPromotioncommodity() {
		String hql="From Promotioncommodity";
		return getSession().createQuery(hql).list();
	}
	@Override
	public Promotioncommodity queryPromotioncommodity(int id) {
		Promotioncommodity promotioncommodity=getSession().get(Promotioncommodity.class, id);
		return promotioncommodity;
	}
	@Override
	public int savePromotioncommodity(Promotioncommodity promotioncommodity) {
		try {
			getSession().save(promotioncommodity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deletePromotioncommodity(Promotioncommodity promotioncommodity) {
		try {
			getSession().delete(promotioncommodity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updatePromotioncommodity(Promotioncommodity promotioncommodity) {
		try {
			getSession().update(promotioncommodity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
