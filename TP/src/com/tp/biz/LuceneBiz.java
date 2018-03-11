package com.tp.biz;
import java.io.IOException;
import java.util.List;
public interface LuceneBiz{
	int saveInfo() throws IOException;
	List<Object> queryCommodityDao(List<String> queryString) throws Exception;
}
