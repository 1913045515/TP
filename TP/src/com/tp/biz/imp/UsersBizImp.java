package com.tp.biz.imp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tp.biz.UsersBiz;
import com.tp.dao.CampusDao;
import com.tp.dao.JurisdictionDao;
import com.tp.dao.UsersDao;
import com.tp.entity.Campus;
import com.tp.entity.Commodity;
import com.tp.entity.Commoditypath;
import com.tp.entity.Jurisdiction;
import com.tp.entity.Users;
public class UsersBizImp implements UsersBiz {
	private UsersDao usersDao;
	private CampusDao campusDao;
	private JurisdictionDao jurisdictionDao;
	
	public void setCampusDao(CampusDao campusDao) {
		this.campusDao = campusDao;
	}

	public void setJurisdictionDao(JurisdictionDao jurisdictionDao) {
		this.jurisdictionDao = jurisdictionDao;
	}

	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	@Override
	public Map<String,Object> loginMange(String userName, String password) {
		Map<String,Object>map=new HashMap<String, Object>();
		Users users=usersDao.queryUsers(userName);
		if(users!=null){
			String getPassword=users.getPassword();
			if(password.equals(getPassword)){
				int flag=judgeJurisdiction(users);
				if(flag==1){
					map.put("result",2);//密码正确，权限不够，不可以登录
				}else{
					map.put("result",3);//密码正确，权限满足，可以登录
					map.put("resultValue",users);
				}				
			}else{
				map.put("result",1);//密码错误
			}
		}else{		
			map.put("result",0);//用户名不存在
		}
		return map;
	}
	public int judgeJurisdiction(Users users){
		if(users.getJurisdiction().getFlag()==2){
			return 2;//2为管理员权限
		}else if(users.getJurisdiction().getFlag()==1){
			return 1;//1为普通用户权限
		}
		return 1;
	}

	@Override
	public boolean modify(int id,String longPwd, String newPwd) {
		Users users=usersDao.queryUsers(id);
		if(users.getPassword().equals(longPwd)){//验证旧密码是否正确，正确则进行修改
			users.setPassword(newPwd);
			usersDao.updateUsers(users);
			return true;
		}else{
			return false;
		}	
	}

	@Override
	public List<Map<String, Object>> queryUsers(int pageNumber,int pageSize) {
		return toMap(usersDao.queryPUsers(pageNumber,pageSize));
	}

	@Override
	 public List<Map<String,Object>>queryUsers(int id) {
		 return toMap(usersDao.queryPUsers(id));
	}
	
	public List<Map<String, Object>> toMap(List<Users>list){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
		for(int i=0;i<list.size();i++){
			Map<String,Object>map=new HashMap<String, Object>();
			map.put("id",list.get(i).getId());
			map.put("name",list.get(i).getNickName());
			map.put("password",list.get(i).getPassword());
			map.put("phone",list.get(i).getPhone());
			map.put("acount",list.get(i).getAcount());
			map.put("address",list.get(i).getAddress());
			map.put("school",list.get(i).getCampus().getUniversity());
			map.put("type",list.get(i).getJurisdiction().getType());
			map.put("btId",list.get(i).getId());
			result.add(map);
		}
		return result;
	}

	@Override
	public List<Map<String, Object>> queryUsers(String name) {
		return toMap(usersDao.queryPUsers(name));
	}

	@Override
	public List<Map<String, Object>> queryUsers() {
		return toMap(usersDao.queryPUsers());
	}

	@Override
	public List<Users> users(int id) {
		return usersDao.queryPUsers(id);
	}

	@Override
	public int update(Users users) {		
		return usersDao.updateUsers(users);
	}

	@SuppressWarnings("null")
	@Override
	public List<Map<String,Object>> queryEmail() {
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		List<Object> list=usersDao.queryEmail();
		Iterator<Object>it=list.iterator();
		List<String> strName=new ArrayList<String>();
		List<String> strValue=new ArrayList<String>();
		while(it.hasNext()){	
			Object[] o=(Object[]) it.next(); 
			strName.add((String)o[0]);
			strValue.add((String)o[1]);
		}
		return toFormat(strName,strValue);
	}
	
	public List<Map<String,Object>> toFormat(List<String> strName,List<String> strValue){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();
		for(int i=0;i<strName.size();i++){
			Map<String,Object>map=new HashMap<String, Object>();
			map.put("name",strName.get(i));
			List<Map<String,Object>>valueList=new ArrayList<Map<String,Object>>();
			List<Object> num=new ArrayList<Object>();
			for(int j=0;j<strName.size();j++){
				if(strName.get(i).equals(strName.get(j))){
					Map<String,Object> mapValue=new HashMap<String, Object>();				
					mapValue.put("name",strValue.get(j));
					valueList.add(mapValue);
					num.add(strName.get(j));
				}
			}	
			for(int ii=0;ii<num.size();ii++){
				strName.remove(num.get(ii));
			}
			i=0;
			map.put("children",valueList);
			result.add(map);
		}
		return result;
	}

	public Users getUsers(String usersName,String password,String email,String school){
		Users users=new Users();
		users.setPassword(password);
		users.setAcount(email);
		Campus campus=campusDao.queryCampus(Integer.valueOf(school)).get(0);
		Jurisdiction jurisdiction=jurisdictionDao.queryJurisdiction(1);
		users.setJurisdiction(jurisdiction);
		users.setCampus(campus);
		users.setNickName(usersName);
		return users;
	}

	@Override
	public Map<String, Object> register(String usersName, String password,
			String email, String school) {
		Map<String,Object>map=new HashMap<String, Object>();
		Users usersIf=usersDao.queryUsers(email);
		if(usersIf==null){
			usersDao.saveUsers(getUsers(usersName,password,email,school));//注册成功
			map.put("result",1);//注册成功
		}else{		
			map.put("result",0);//用户已经存在
		}
		return map;
	}

	@Override
	public Map<String, Object> loginUser(String userName, String password) {
		Map<String,Object>map=new HashMap<String, Object>();
		Users users=usersDao.queryUsers(userName);
		if(users!=null){
			String getPassword=users.getPassword();
			if(password.equals(getPassword)){
				map.put("result",3);//密码正确，权限满足，可以登录
				map.put("resultValue",users);			
			}else{
				map.put("result",1);//密码错误
			}
		}else{		
			map.put("result",0);//用户名不存在
		}
		return map;
	}
}
