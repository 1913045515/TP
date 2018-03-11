package com.tp.biz;
import java.util.List;
import java.util.Map;

import com.tp.entity.Users;
public interface UsersBiz {
   Map<String,Object> loginMange(String userName,String password);
   Map<String,Object> loginUser(String userName,String password);
   Map<String,Object> register(String usersName,String password,String email,String school);
   boolean modify(int id,String longPwd,String newPwd);
   List<Map<String,Object>>queryUsers(int pageNumber,int pageSize);
   List<Map<String,Object>>queryUsers(int id);
   List<Users>users(int id);
   List<Map<String,Object>>queryUsers();
   List<Map<String,Object>>queryUsers(String name);
   int update(Users users);
   List<Map<String,Object>>queryEmail();
}
