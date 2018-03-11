package com.tp.dao.imp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tp.dao.PunishmentDao;
import com.tp.entity.Punishment;
public class PunishmentDaoImp implements PunishmentDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public PunishmentDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Punishment> queryPunishment() {
		String hql="From Punishment";
		return getSession().createQuery(hql).list();
	}
	@Override
	public Punishment queryPunishment(int id) {
		Punishment punishment=getSession().get(Punishment.class, id);
		return punishment;
	}
	@Override
	public int savePunishment(Punishment punishment) {
		try {
			getSession().save(punishment);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deletePunishment(Punishment punishment) {
		try {
			getSession().delete(punishment);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updatePunishment(Punishment punishment) {
		try {
			getSession().update(punishment);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
