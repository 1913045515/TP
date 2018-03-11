package com.tp.biz;
import java.util.Map;
public interface StatisticsBiz {
	//商品信息统计
	Map<String,Object> typeStatics(String year,String month);
	Map<String,Object> typeStatics();
}
