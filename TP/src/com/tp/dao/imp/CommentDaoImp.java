package com.tp.dao.imp;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.tp.dao.CommentDao;
import com.tp.entity.Comment;
public class CommentDaoImp implements CommentDao {
	private SessionFactory sessionFactory;
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public CommentDaoImp(){
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Comment> queryComment() {
		String hql="From Comment";
		return getSession().createQuery(hql).list();
	}

	@Override
	public List<Comment> queryComment(int goodsId) {
		String hql="From Comment c where c.commodity.id = ?";
		Query query=getSession().createQuery(hql);
		query.setParameter(0,goodsId);
		return query.list();
	}

	@Override
	public Comment queeryComment(int id) {
		Comment comment=getSession().get(Comment.class,id);
		return comment;
	}
	@Override
	public int saveComment(Comment comment) {
		try {
			getSession().save(comment);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int deleteComment(Comment comment) {
		try {
			getSession().delete(comment);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public int updateComment(Comment comment) {
		try {
			getSession().update(comment);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}
