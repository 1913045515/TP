package com.tp.dao.imp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tp.dao.StatisticsDao;
import com.tp.tools.FormatTools;
public class StatisticsDaoImp implements StatisticsDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public StatisticsDaoImp(){
		
	}
	public List<Map<String, Object>> typeStatics(Date[] date) {
		String hql="select cf.name,count(cf.name) from Order o "
				+ "left join Commodity c on c.id=o.commodity.id "
				+ "left join Classification cf on cf.id=c.classification.id "
				+ "where o.time>=? and o.time<=? group by cf.name";
		System.out.println("first"+FormatTools.FormateTime(date[0]));
		System.out.println("last"+FormatTools.FormateTime(date[1]));
		Query query=getSession().createQuery(hql);
		query.setParameter(0,date[0]);
		query.setParameter(1,date[1]);
		Iterator<Object>it=query.list().iterator();
		List<Map<String, Object>>list=new ArrayList<Map<String,Object>>();
		while(it.hasNext()){
			Map<String, Object> map=new HashMap<String, Object>();
			Object[] o=(Object[])it.next();
			map.put("name", o[0]);
			map.put("value", o[1]);
			list.add(map);
		}
		return list;
	}
	
	@Override
	public int numberOrder(){
		String hql="select count(*) from Order";
		Query query=getSession().createQuery(hql);
		String str=query.list().get(0).toString();
		return Integer.valueOf(str);
	}
}
