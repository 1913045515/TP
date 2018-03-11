package com.tp.dao.imp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tp.dao.OrderDao;
import com.tp.entity.Order;
public class OrderDaoImp implements OrderDao{
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public OrderDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> queryOrder() {
		String hql="From Order";
		return getSession().createQuery(hql).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> queryOrder(int id) {
		String hql="From Order o where o.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,id);
		return query.list();
	}

	@Override
	public int saveOrder(Order order) {
		try {
			getSession().save(order);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}	
	}

	@Override
	public int deleteOrder(Order order) {
		try {
			getSession().delete(order);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}	
	}

	@Override
	public int updateOrder(Order order) {
		try {
			getSession().update(order);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}	
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> queryOrder(int pageNumber, int pageSize) {
		String hql="From Order";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> queryUOrder(int uId) {
		String hql="From Order o where o.users.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,uId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> queryCOrder(int cId) {
		String hql="From Order o where o.commodity.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,cId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Order> queryOOrder(int oId) {
		String hql="From Order o where o.orderNo=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,oId);
		return query.list();
	}
	
}
