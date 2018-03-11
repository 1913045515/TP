package com.tp.dao;
import java.util.List;
import com.tp.entity.Users;
public interface UsersDao {
	//用户接口
	List<Users>queryUsers();
	List<Users>queryPUsers(int pageNumber,int pageSize);
	List<Users>queryPUsers();
	List<Users>queryPUsers(int id);
	List<Users>queryPUsers(String name);
	List<Object>queryEmail();
	Users queryUsers(int id);
	Users queryUsers(String acount);
	int saveUsers(Users users);
	int deleteUsers(Users users);
	int updateUsers(Users users);
}
