package com.tp.biz.imp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tp.biz.OrderBiz;
import com.tp.dao.OrderDao;
import com.tp.entity.Order;
public class OrderBizImp implements OrderBiz {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	@Override
	public List<Map<String, Object>> queryOrder() {
		return toMap(orderDao.queryOrder());
	}

	@Override
	public List<Map<String, Object>> queryOrder(int pageNumber, int pageSize) {	
		return toMap(orderDao.queryOrder(pageNumber,pageSize));
	}

	@Override
	public List<Map<String, Object>> queryOrder(int id) {
		
		return toMap(orderDao.queryOrder());
	}

	@Override
	public List<Map<String, Object>> queryOOrder(int oId) {
		return toMap(orderDao.queryOOrder(oId));
	}

	@Override
	public List<Map<String, Object>> queryCOrder(int cId) {	
		return toMap(orderDao.queryCOrder(cId));
	}

	@Override
	public List<Map<String, Object>> queryUOrder(int uId) {		
		return toMap(orderDao.queryUOrder(uId));
	}

	@Override
	public Order order(int oId) {
		return orderDao.queryOOrder(oId).get(0);
	}

	@Override
	public void deleteOrder(String[] check) {	
		for(int i=0;i<check.length;i++){
			System.out.println(check[i]);
			deleteOrder(Integer.valueOf(check[i]));
		}
	}

	public int deleteOrder(int id){
		return orderDao.deleteOrder(orderDao.queryOrder(id).get(0));
	}
	@Override
	public int updateOrder(Order order) {
		return orderDao.updateOrder(order);
	}
	
	public List<Map<String, Object>> toMap(List<Order>list){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
		for(int i=0;i<list.size();i++){
			Map<String,Object>map=new HashMap<String, Object>();
			map.put("id",list.get(i).getId());
			map.put("oId",list.get(i).getOrderNo());
			map.put("uId",list.get(i).getUsers().getId());
			map.put("cId",list.get(i).getCommodity().getId());		
			map.put("name",list.get(i).getName());
			map.put("price",list.get(i).getPrice());		
			map.put("number",list.get(i).getNumber());
			map.put("state",list.get(i).getState());	
			map.put("time",list.get(i).getTime());
			map.put("remarks",list.get(i).getRemarks());			
			map.put("btId",list.get(i).getId());		
			result.add(map);
		}
		return result;
	}
}
