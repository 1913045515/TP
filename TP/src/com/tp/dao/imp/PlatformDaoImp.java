package com.tp.dao.imp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tp.dao.PlatformDao;
import com.tp.entity.Platform;
public class PlatformDaoImp implements PlatformDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public PlatformDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Platform> queryPlatform() {
		String hql="From Platform";
		return getSession().createQuery(hql).list();
	}
	@Override
	public int savePlatform(Platform platform) {
		try {
			getSession().save(platform);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deletePlatform(Platform platform) {
		try {
			getSession().delete(platform);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updatePlatform(Platform platform) {
		try {
			getSession().update(platform);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Platform> queryPlatform(int pageNumber, int pageSize) {
		String hql="From Platform";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Platform> queryPlatform(int id) {
		String hql="From Platform p where p.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, id);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Platform> queryGPlatform(int gUId) {
		String hql="From Platform p where p.usersByReceivablesId.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, gUId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Platform> querySPlatform(int gSId) {
		String hql="From Platform p where p.usersByPaymentId.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, gSId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Platform> queryOPlatform(int oId) {
		String hql="From Platform p where p.order.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, oId);
		return query.list();
	}

	
}
