package com.tp.dao;
import java.util.List;
import com.tp.entity.Usersamount;
public interface UsersamountDao {
	//用户金额接口
	List<Usersamount>queryUsersamount();
	Usersamount queryUsersamount(int id);
	int saveUsersamount(Usersamount usersamount);
	int deleteUsersamount(Usersamount usersamount);
	int updateUsersamount(Usersamount usersamount);
}
