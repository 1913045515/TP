package com.tp.biz.imp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tp.biz.PlatformBiz;
import com.tp.dao.PlatformDao;
import com.tp.entity.Platform;
public class PlatformBizImp implements PlatformBiz {
	private PlatformDao platformDao;
	public void setPlatformDao(PlatformDao platformDao) {
		this.platformDao = platformDao;
	}
	@Override
	public List<Map<String, Object>> queryPlatform() {
		return toMap(platformDao.queryPlatform());
	}

	@Override
	public List<Map<String, Object>> queryPlatform(int pageNumber, int pageSize) {
		return toMap(platformDao.queryPlatform(pageNumber,pageSize));
	}

	@Override
	public List<Map<String, Object>> queryPlatform(int id) {
		return toMap(platformDao.queryPlatform(id));
	}

	@Override
	public Platform platform(int id) {
		return platformDao.queryPlatform(id).get(0);
	}

	@Override
	public int savePlatform(Platform platform) {
		return platformDao.savePlatform(platform);
	}

	@Override
	public void deletePlatform(String[] check) {
		for(int i=0;i<check.length;i++){
			deletePlatform(Integer.valueOf(check[i]));
		}
	}

	public int deletePlatform(int id){
		return platformDao.deletePlatform(platformDao.queryPlatform(id).get(0));
	}
	@Override
	public int updatePlatform(Platform platform) {
		return platformDao.updatePlatform(platform);
	}
	
	public List<Map<String, Object>> toMap(List<Platform>list){
		List<Map<String,Object>>result=new ArrayList<Map<String,Object>>();	
		for(int i=0;i<list.size();i++){
			Map<String,Object>map=new HashMap<String, Object>();
			map.put("id",list.get(i).getId());
			map.put("gUId",list.get(i).getUsersByReceivablesId().getId());
			map.put("sUId",list.get(i).getUsersByPaymentId().getId());
			map.put("oId",list.get(i).getOrder().getId());
			map.put("price",list.get(i).getPrice());
			map.put("completeTime",list.get(i).getCompleteTime());
			map.put("invalidTime",list.get(i).getInvalidTime());
			map.put("btId",list.get(i).getId());
			result.add(map);
		}
		return result;
	}
	@Override
	public List<Map<String, Object>> queryOPlatform(int oId) {
		return toMap(platformDao.queryOPlatform(oId));
	}
	@Override
	public List<Map<String, Object>> querySPlatform(int sUId) {
		return toMap(platformDao.querySPlatform(sUId));
	}
	@Override
	public List<Map<String, Object>> queryGPlatform(int gUId) {
		return toMap(platformDao.queryGPlatform(gUId));
	}
}
