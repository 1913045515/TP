package com.tp.dao.imp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tp.dao.ChangeDao;
import com.tp.entity.Change;
public class ChangeDaoImp implements ChangeDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public ChangeDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Change> queryChange() {
		String hql="From Change";
		return getSession().createQuery(hql).list();
	}

	@Override
	public Change queryChange(int id) {
		Change change=getSession().get(Change.class, id);
		return change;
	}

	@Override
	public int saveChange(Change change) {
		try {
			getSession().save(change);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int deleteChange(Change change) {
		try {
			getSession().delete(change);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateChange(Change change) {
		try {
			getSession().update(change);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
