package com.tp.dao.imp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tp.dao.UsersamountDao;
import com.tp.entity.Usersamount;
public class UsersamountDaoImp implements UsersamountDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public UsersamountDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Usersamount> queryUsersamount() {
		String hql="From Usersamount";
		return getSession().createQuery(hql).list();
	}
	@Override
	public Usersamount queryUsersamount(int id) {
		Usersamount usersamount=getSession().get(Usersamount.class, id);
		return usersamount;
	}
	@Override
	public int saveUsersamount(Usersamount usersamount) {
		try {
			getSession().save(usersamount);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deleteUsersamount(Usersamount usersamount) {
		try {
			getSession().delete(usersamount);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateUsersamount(Usersamount usersamount) {
		try {
			getSession().update(usersamount);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
