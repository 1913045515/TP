package com.tp.dao;
import java.util.Date;
import java.util.List;
import java.util.Map;
public interface StatisticsDao {
	List<Map<String,Object>>typeStatics(Date date[]);
	int numberOrder();
}
