package com.tp.biz;
import java.util.List;
import java.util.Map;
import com.tp.entity.Platform;
public interface PlatformBiz {
	List<Map<String,Object>>queryPlatform();
	List<Map<String,Object>>queryPlatform(int pageNumber,int pageSize);
	List<Map<String,Object>>queryPlatform(int id);
	List<Map<String,Object>>queryOPlatform(int oId);
	List<Map<String,Object>>querySPlatform(int sUId);
	List<Map<String,Object>>queryGPlatform(int gUId);
	Platform platform(int id);
	int savePlatform(Platform platform);
	void deletePlatform(String[] check);
	int updatePlatform(Platform platform);
}
