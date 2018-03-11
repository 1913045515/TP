package com.tp.dao.imp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tp.dao.JurisdictionDao;
import com.tp.entity.Jurisdiction;
public class JurisdictionDaoImp implements JurisdictionDao{
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public JurisdictionDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Jurisdiction> queryJurisdiction() {
		String hql="From Jurisdiction";
		return getSession().createQuery(hql).list();
	}
	@Override
	public Jurisdiction queryJurisdiction(int id) {
		Jurisdiction jurisdiction=getSession().get(Jurisdiction.class, id);
		return jurisdiction;
	}
	@Override
	public int saveJurisdiction(Jurisdiction jurisdiction) {
		try {
			getSession().save(jurisdiction);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deleteJurisdiction(Jurisdiction jurisdiction) {
		try {
			getSession().delete(jurisdiction);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateJurisdiction(Jurisdiction jurisdiction) {
		try {
			getSession().update(jurisdiction);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
