package com.tp.dao.imp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tp.dao.DeliverDao;
import com.tp.entity.Deliver;
public class DeliverDaoImp implements DeliverDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public DeliverDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Deliver> queryDeliver() {
		String hql="From Deliver";
		return getSession().createQuery(hql).list();
	}
	@Override
	public Deliver queryDeliver(int id) {
		Deliver deliver=getSession().get(Deliver.class, id);
		return deliver;
	}
	@Override
	public int saveDeliver(Deliver deliver) {
		try {
			getSession().save(deliver);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deleteDeliver(Deliver deliver) {
		try {
			getSession().delete(deliver);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateDeliver(Deliver deliver) {
		try {
			getSession().update(deliver);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
