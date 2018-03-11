package com.tp.dao;
import java.util.List;
import com.tp.entity.Order;
public interface OrderDao {
	//订单接口
	List<Order>queryOrder();
	List<Order>queryOrder(int id);
	List<Order>queryOrder(int pageNumber, int pageSize);
	List<Order>queryUOrder(int uId);
	List<Order>queryCOrder(int cId);
	List<Order>queryOOrder(int oId);
	int saveOrder(Order order);
	int deleteOrder(Order order);
	int updateOrder(Order order);
}
