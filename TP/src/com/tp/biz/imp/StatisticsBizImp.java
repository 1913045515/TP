package com.tp.biz.imp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tp.biz.StatisticsBiz;
import com.tp.dao.StatisticsDao;
import com.tp.tools.DateUtils;
public class StatisticsBizImp implements StatisticsBiz {
	private StatisticsDao statisticsDao;
	
	public void setStatisticsDao(StatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}

	@Override
	public Map<String, Object> typeStatics(String year,String month) {
		List<Map<String,Object>>list=statisticsDao.typeStatics(DateUtils.setDate(year, month));
		Map<String,Object>map=new HashMap<String, Object>();
		for(int i=0;i<list.size();i++){
			map.put(list.get(i).get("name").toString(),list.get(i).get("value"));
		}
		return map;
	}

	@Override
	public Map<String, Object> typeStatics() {
		List<Map<String,Object>>list=statisticsDao.typeStatics(DateUtils.getDate());
		Map<String,Object>map=new HashMap<String, Object>();
		for(int i=0;i<list.size();i++){
			map.put(list.get(i).get("name").toString(),list.get(i).get("value"));
		}
		return map;
	}
}
