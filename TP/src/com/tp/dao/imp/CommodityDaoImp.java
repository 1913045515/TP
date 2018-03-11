package com.tp.dao.imp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.tp.dao.CommodityDao;
import com.tp.entity.Commodity;
public class CommodityDaoImp implements CommodityDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public CommodityDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Commodity> queryCommodity(int pageNumber,int pageSize) {
		String hql="From Commodity";
		Query query=getSession().createQuery(hql);
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	
	@Override
	public int saveCommodity(Commodity commodity) {
		try {
			getSession().save(commodity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}	
	}

	@Override
	public int deleteCommodity(Commodity commodity) {
		try {
			getSession().delete(commodity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int updateCommodity(Commodity commodity) {
		try {
			getSession().update(commodity);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Commodity> queryCommodity(int id) {
		String hql="From Commodity c where c.id=?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,id);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Commodity> queryCommodity(String name) {
		String hql="From Commodity c where c.name like ?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,"%"+name+"%");
		return query.list();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Commodity> queryCommodity() {
		String hql="From Commodity";
		Query query=getSession().createQuery(hql);
		return query.list();
	}
	@Override
	public List<Commodity> queryCommodity(String name, int pageNumber,
			int pageSize) {
		String hql="From Commodity c where c.name like ?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,"%"+name+"%");
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	@Override
	public List<Commodity> queryCommodity(String keyname, String schoolName,
			String className, int pageNumber, int pageSize) {
		String hql="From Commodity c where c.name like ? ";
		int index=0;
		if(!"".equals(schoolName) && !"undefined".equals(schoolName)){
			hql+=" and c.campus.university=? ";
			index+=1;
		}
		if(!"".equals(className) && !"undefined".equals(className) && !"所有分类".equals(className)){
			hql+=" and c.classification.name=? ";
			index+=3;
		}
		Query query=getSession().createQuery(hql);
		query.setParameter(0,"%"+keyname+"%");
		if(index==1){
			query.setParameter(1,schoolName);
		}else if(index==3){
			query.setParameter(1,className);
		}else if(index==4){
			query.setParameter(1,schoolName);
			query.setParameter(2,className);
		}
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	@Override
	public List<Commodity> queryCommodity(String schoolName, String className,
			int pageNumber, int pageSize) {
		String hql="From Commodity c where 1=1 ";
		int index=0;
		if(!"".equals(schoolName) && !"undefined".equals(schoolName)){
			hql+=" and c.campus.university=? ";
			index+=1;
		}
		if(!"".equals(className) && !"undefined".equals(className) && !"所有分类".equals(className)){
			hql+=" and c.classification.name=? ";
			index+=3;
		}
		Query query=getSession().createQuery(hql);
		if(index==1){
			query.setParameter(0,schoolName);
		}else if(index==3){
			query.setParameter(0,className);
		}else if(index==4){
			query.setParameter(0,schoolName);
			query.setParameter(1,className);
		}
		query.setFirstResult((pageNumber-1)*pageSize);
		query.setMaxResults(pageSize);
		return query.list();
	}
	@Override
	public List<Commodity> queryCommodityAccout(String schoolName,
			String className) {
		String hql="From Commodity c where 1=1 ";
		int index=0;
		if(!"".equals(schoolName) && !"undefined".equals(schoolName)){
			hql+=" and c.campus.university=? ";
			index+=1;
		}
		if(!"".equals(className) && !"undefined".equals(className) && !"所有分类".equals(className)){
			hql+=" and c.classification.name=? ";
			index+=3;
		}
		Query query=getSession().createQuery(hql);
		if(index==1){
			query.setParameter(0,schoolName);
		}else if(index==3){
			query.setParameter(0,className);
		}else if(index==4){
			query.setParameter(0,schoolName);
			query.setParameter(1,className);
		}
		return query.list();
	}
	@Override
	public List<Commodity> queryCommodityAccout(String keyName,
			String schoolName, String className) {
		String hql="From Commodity c where c.name like ? ";
		int index=0;
		if(!"".equals(schoolName) && !"undefined".equals(schoolName)){
			hql+=" and c.campus.university=? ";
			index+=1;
		}
		if(!"".equals(className) && !"undefined".equals(className) && !"所有分类".equals(className)){
			hql+=" and c.classification.name=? ";
			index+=3;
		}
		Query query=getSession().createQuery(hql);
		query.setParameter(0,"%"+keyName+"%");
		if(index==1){
			query.setParameter(1,schoolName);
		}else if(index==3){
			query.setParameter(1,className);
		}else if(index==4){
			query.setParameter(1,schoolName);
			query.setParameter(2,className);
		}
		return query.list();
	}
}
