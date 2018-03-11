package com.tp.dao.imp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tp.dao.CampusDao;
import com.tp.entity.Campus;
import com.tp.entity.Classification;
public class CampusDaoImp implements CampusDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public CampusDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Campus> queryCampus() {
		String hql="From Campus";
		return getSession().createQuery(hql).list();
	}
	
	@Override
	public int saveCampus(Campus campus) {
		try {
			getSession().save(campus);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deleteCampus(Campus campus) {
		try {
			getSession().delete(campus);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateCampus(Campus campus) {
		try {
			getSession().update(campus);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<com.tp.entity.Campus> queryCampus(int pageNumber, int pageSize) {
		String hql="From Campus";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Campus> queryCampus(int id) {
		String hql="From Campus c where c.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,id);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Campus> queryCampus(String name) {
		String hql="From Campus c where c.university like ?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,"%"+name+"%");
		return query.list();
	}
}
