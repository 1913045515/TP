package com.tp.dao.imp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tp.dao.CommoditypathDao;
import com.tp.entity.Commoditypath;
public class CommoditypathDaoImp implements CommoditypathDao{
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public CommoditypathDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Commoditypath> queryCommoditypath() {
		String hql="From Commoditypath";
		return getSession().createQuery(hql).list();
	}
	@Override
	public Commoditypath queryCommoditypath(int id) {
		Commoditypath commoditypath=getSession().get(Commoditypath.class, id);
		return commoditypath;
	}
	@Override
	public int saveCommoditypath(Commoditypath commoditypath) {
		try {
			getSession().save(commoditypath);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateCommoditypath(Commoditypath commoditypath) {
		try {
			getSession().update(commoditypath);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deleteCommoditypath(Commoditypath commoditypath) {
		try {
			getSession().delete(commoditypath);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Commoditypath> queryCommoditypaths(int id) {
		String hql="From Commoditypath c where c.commodity.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,id);
		return query.list();
	}
	@Override
	public Commoditypath queryCommoditypathCommodity(int id) {
		String hql="From Commoditypath c where c.commodity.id=? and c.indexNumber=0";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,id);
		return (Commoditypath) query.list().get(0);
	}
	
}
