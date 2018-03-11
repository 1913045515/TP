package com.tp.dao.imp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tp.dao.ClassificationDao;
import com.tp.entity.Classification;
public class ClassificationDaoImp implements ClassificationDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public ClassificationDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Classification> queryClassification() {
		String hql="From Classification";
		return getSession().createQuery(hql).list();
	}
	
	@Override
	public int saveClassification(Classification classification) {
		try {
			getSession().save(classification);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}		
	}
	@Override
	public int deleteClassification(Classification classification) {
		try {
			getSession().delete(classification);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateClassification(Classification classification) {
		try {
			getSession().update(classification);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Classification> queryClassification(int pageNumber, int pageSize) {
		String hql="From Classification";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Classification> queryClassification(int id) {
		String hql="From Classification c where c.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,id);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Classification> queryClassification(String name) {
		String hql="From Classification c where c.name=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,name);
		return query.list();
	}
}
