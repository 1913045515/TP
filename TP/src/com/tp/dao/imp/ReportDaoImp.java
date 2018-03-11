package com.tp.dao.imp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tp.dao.ReportDao;
import com.tp.entity.Report;
public class ReportDaoImp implements ReportDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public ReportDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Report> queryReport() {
		String hql="From Report";
		return getSession().createQuery(hql).list();
	}

	@Override
	public int saveReport(Report report) {
		try {
			getSession().save(report);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deleteReport(Report report) {
		try {
			getSession().delete(report);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateReport(Report report) {
		try {
			getSession().update(report);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Report> queryReport(int pageNumber, int pageSize) {
		String hql="From Report";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Report> queryReport(int id) {
		String hql="From Report r where r.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, id);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Report> queryCReport(int cId) {
		String hql="From Report r where r.commodity.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, cId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Report> queryUReport(int uId) {
		String hql="From Report r where r.users.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, uId);
		return query.list();
	}
	
}
