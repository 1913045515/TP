package com.tp.biz;
import java.util.List;
import java.util.Map;
public interface MailBiz {
	boolean sendMail(String usersId,String themes,String content);
	boolean sendMail(String[] usersId,String themes,String content);
	List<Map<String,Object>>getMail() throws Exception;
}
