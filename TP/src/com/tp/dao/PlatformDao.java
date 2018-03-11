package com.tp.dao;
import java.util.List;

import com.tp.entity.Platform;
public interface PlatformDao {
	//平台金额接口
	List<Platform>queryPlatform();
	List<Platform>queryPlatform(int pageNumber,int pageSize);
	List<Platform>queryPlatform(int id);
	List<Platform>queryGPlatform(int gUId);
	List<Platform>querySPlatform(int gSId);
	List<Platform>queryOPlatform(int oId);
	int savePlatform(Platform platform);
	int deletePlatform(Platform platform);
	int updatePlatform(Platform platform);
}
