package com.tp.dao;
import java.util.List;
import com.tp.entity.Deliver;
public interface DeliverDao {
	//发货信息接口
	List<Deliver>queryDeliver();
	Deliver queryDeliver(int id);
	int saveDeliver(Deliver deliver);
	int deleteDeliver(Deliver deliver);
	int updateDeliver(Deliver deliver);
}
