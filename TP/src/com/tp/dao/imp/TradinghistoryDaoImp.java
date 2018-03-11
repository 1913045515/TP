package com.tp.dao.imp;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tp.dao.TradinghistoryDao;
import com.tp.entity.Tradinghistory;
public class TradinghistoryDaoImp implements TradinghistoryDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public TradinghistoryDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Tradinghistory> queryTradinghistory() {
		String hql="From Tradinghistory";
		return getSession().createQuery(hql).list();
	}
	@Override
	public Tradinghistory queryTradinghistory(int id) {
		Tradinghistory tradinghistory=getSession().get(Tradinghistory.class, id);
		return tradinghistory;
	}
	@Override
	public int saveTradinghistory(Tradinghistory tradinghistory) {
		try {
			getSession().save(tradinghistory);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deleteTradinghistory(Tradinghistory tradinghistory) {
		try {
			getSession().delete(tradinghistory);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateTradinghistory(Tradinghistory tradinghistory) {
		try {
			getSession().update(tradinghistory);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
}
