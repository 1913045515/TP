package com.tp.biz;
import java.util.List;
import java.util.Map;
import com.tp.entity.Order;
public interface OrderBiz {
	List<Map<String,Object>>queryOrder();
	List<Map<String,Object>>queryOrder(int pageNumber,int pageSize);
	List<Map<String,Object>>queryOrder(int id);
	List<Map<String,Object>>queryOOrder(int oId);
	List<Map<String,Object>>queryCOrder(int cId);
	List<Map<String,Object>>queryUOrder(int uId);
	Order order(int oId);
	void deleteOrder(String[] check);
	int updateOrder(Order order);
}
