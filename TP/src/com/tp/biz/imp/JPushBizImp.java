package com.tp.biz.imp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tp.biz.JPushBiz;
import com.tp.entity.JpushInfo;
import com.tp.jpush.api.ErrorCodeEnum;
import com.tp.jpush.api.IOSExtra;
import com.tp.jpush.api.JPushClient;
import com.tp.jpush.api.MessageResult;
import com.tp.tools.Constant;
import com.tp.tools.JsonUtil;
public class JPushBizImp implements JPushBiz {
	private static JPushClient jpush = null;
	public static final int MAX = Integer.MAX_VALUE;
	public static final int MIN = (int) MAX / 2;
	public JPushBizImp() {
		
	}
	private static void testSend(JpushInfo jpushInfo, List<Map<String, Object>> list) {
		Integer num= getRandomSendNo();
		String sendNo = num+"";
		Map<String, Object> extra = new HashMap<String, Object>();
		for(int i=0;i<list.size();i++){
			extra.put(list.get(i).get("key").toString(),list.get(i).get("value"));
		}
		 jpush.sendNotificationWithAppKey(sendNo,jpushInfo.getThemes(), jpushInfo.getContent(), 0, extra);
	}

	public static int getRandomSendNo() {
		return (int) (MIN + Math.random() * (MAX - MIN));
	}

	@Override
	public String sendJPush(JpushInfo jpushInfo, List<Map<String, Object>> list) {
 		jpush = new JPushClient(Constant.masterSecret, Constant.appKey, jpushInfo.getTimeToLive());
		jpush.setEnableSSL(true);
		testSend(jpushInfo,list);
		return null;
	}
}
