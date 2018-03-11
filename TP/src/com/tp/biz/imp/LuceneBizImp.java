package com.tp.biz.imp;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.tp.biz.LuceneBiz;
import com.tp.dao.CommodityDao;
import com.tp.entity.Commodity;
import com.tp.tools.SaveLucene;
public class LuceneBizImp implements LuceneBiz {
	private CommodityDao commodityDao;
	private SaveLucene saveLucene;
	public void setCommodityDao(CommodityDao commodityDao) {
		this.commodityDao = commodityDao;
	}
	
	public void setSaveLucene(SaveLucene saveLucene) {
		this.saveLucene = saveLucene;
	}

	public LuceneBizImp(){
		
	}

	@Override
	public int saveInfo() throws IOException {
		List<Commodity>list=commodityDao.queryCommodity();
		return saveLucene.saveInfo(list);
	}

	@Override
	public List<Object> queryCommodityDao(List<String> queryString) throws Exception {
		return saveLucene.queryCommodityDao(queryString);
	}
}
