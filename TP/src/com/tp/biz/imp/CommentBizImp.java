package com.tp.biz.imp;

import com.tp.biz.CommentBiz;
import com.tp.dao.CommentDao;
import com.tp.dao.CommodityDao;
import com.tp.dao.UsersDao;
import com.tp.entity.Comment;
import com.tp.entity.Commodity;
import com.tp.entity.Users;
import com.tp.tools.DateUtils;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Date;

/**
 * Created by 19130 on 2018/3/12.
 */
public class CommentBizImp implements CommentBiz {
    private CommentDao commentDao;
    private UsersDao usersDao;
    private CommodityDao commodityDao;

    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void setCommodityDao(CommodityDao commodityDao) {
        this.commodityDao = commodityDao;
    }

    @Override
    public boolean saveComment(String goodsID, String userID, String content) {
        Users users=usersDao.queryUsers(Integer.valueOf(userID));
        Commodity commodity=commodityDao.queryCommodity(Integer.valueOf(goodsID)).get(0);
        Comment comment = new Comment();
        comment.setCommodity(commodity);
        comment.setContent(content);
        comment.setUsersByUsersId(users);
        comment.setTime(new Date());
        comment.setUsersByObjectId(commodity.getUsers());
        int flag=commentDao.saveComment(comment);
        if(flag==1){
            return true;
        }else{
            return false;
        }
    }
}
