package com.tp.dao.imp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tp.dao.UsersDao;
import com.tp.entity.Users;
public class UsersDaoImp implements UsersDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public UsersDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> queryUsers() {
		String hql="From Users";
		return getSession().createQuery(hql).list();
	}
	@Override
	public Users queryUsers(int id) {
		Users users=getSession().get(Users.class, id);
		return users;
	}
	@Override
	public int saveUsers(Users users) {
		try {
			getSession().save(users);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deleteUsers(Users users) {
		try {
			getSession().delete(users);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateUsers(Users users) {
		try {
			getSession().update(users);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public Users queryUsers(String acount) {
		String hql="From Users u where u.acount=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, acount);
		if(query.list().size()==0){
			return null;
		}else{
			return (Users)query.list().get(0);
		}		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> queryPUsers(int id) {
		String hql="From Users u where u.id=? and u.jurisdiction.id=1";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, id);
		return 	query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> queryPUsers(int pageNumber,int pageSize) {
		String hql="From Users u where u.jurisdiction.id=1";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> queryPUsers(String name) {
		String hql="From Users u where u.acount=? and u.jurisdiction.id=1";
		Query query=getSession().createQuery(hql);
		query.setParameter(0, name);
		return 	query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Users> queryPUsers() {
		String hql="From Users u where u.jurisdiction.id=1";
		Query query=getSession().createQuery(hql);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> queryEmail() {
		String hql="Select c.province, u.acount From Users u "+
				"left join Campus c on u.campus.id=c.id";
		Query query=getSession().createQuery(hql);
		return query.list();
	}
}
