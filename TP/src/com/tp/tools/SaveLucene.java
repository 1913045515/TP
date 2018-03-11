package com.tp.tools;
import java.io.IOException;
import java.util.List;

import com.tp.entity.Commodity;
public interface SaveLucene {
	int saveInfo(List<Commodity> list) throws IOException;
	List<Object> queryCommodityDao(List<String> queryString) throws Exception;
	int deleteInfo(int id);
	int updateInfo(Commodity commodity,int id);
}
