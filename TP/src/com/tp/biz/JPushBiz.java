package com.tp.biz;
import java.util.List;
import java.util.Map;
import com.tp.entity.JpushInfo;
public interface JPushBiz {
	public String sendJPush(JpushInfo jpushInfo,List<Map<String,Object>> list);
}
