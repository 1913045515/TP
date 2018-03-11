package com.tp.dao;
import java.util.List;
import com.tp.entity.Comment;
public interface CommentDao {
	//评论接口
	List<Comment>queryComment();
	Comment queeryComment(int id);
	int saveComment(Comment comment);
	int deleteComment(Comment comment);
	int updateComment(Comment comment);
}
