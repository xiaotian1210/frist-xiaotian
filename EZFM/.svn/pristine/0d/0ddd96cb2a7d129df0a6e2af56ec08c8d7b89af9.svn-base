package com.shareworx.ezfm.webservice.callcenter.inner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.alibaba.fastjson.JSONArray;

/**
 * webService客户端业务操作
 * 
 * @author jin.li
 *
 */
public class CallCenterClient {
	public static void main(String[] args) {
		// System.out.println(CallCenterClient.getJson());
		final JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//		 final String address = "http://123.57.30.221:8080/yjwy/webServices/callcenter?wsdl";
		final String address = "http://localhost:8080/ezfm/webServices/callcenter?wsdl";
//		final String address = "http://192.168.0.92:8080/yjwy/webServices/callcenter?wsdl";
		factory.setAddress(address);
		factory.setServiceClass(CallCenterDataService.class);
		CallCenterDataService hw = (CallCenterDataService) factory.create();
		String str = hw.getTest("见证奇迹的时刻！");
		System.out.println(str);
//		String result = hw.pushCallCenterUsersInfo(CallCenterClient.getJson());
		String result = hw.pushCallCenterUsersInfo(CallCenterClient.getJson1());
		System.out.println(result);
	}

	private static String getJson() {
		List<Map<String, Object>> returnList = new ArrayList<>();
		List<Map<String, Object>> userList = new ArrayList<>();
		Map<String, Object> map = null;
		map = new HashMap<>();
		map.put("crop_code", "YYDC");
		returnList.add(map);
		for (int i = 1; i < 4; i++) {
			map = new HashMap<>();
			map.put("em_code", "callcenter0" + i);
			map.put("user_name", "呼叫中心" + i);
			map.put("user_code", "callcenter0" + i);
			map.put("email", "callcenter" + i + "@cc.com");
			map.put("phone", "186" + i + i + i + i + i + i + i + i);
			userList.add(map);
		}
		map = new HashMap<>();
		map.put("users", userList);
		returnList.add(map);
		return JSONArray.toJSONString(returnList);
	}

	private static String getJson1() {
		List<Map<String, Object>> returnList = new ArrayList<>();
		List<Map<String, Object>> userList = new ArrayList<>();
		Map<String, Object> map = null;
		map = new HashMap<>();
		map.put("crop_code", "YYJT");
		returnList.add(map);

		map = new HashMap<>();
		map.put("em_code", "1001");
		map.put("user_code", "4028889742b714a00142b75d08620018");
		map.put("phone", "18610788317");
		map.put("email", "");
		map.put("user_name", "1001");
		userList.add(map);

		map = new HashMap<>();
		map.put("em_code", "bean");
		map.put("user_code", "2c908157559f0af101559f24aabb0009");
		map.put("phone", "");
		map.put("email", "");
		map.put("user_name", "4200");
		userList.add(map);

		map = new HashMap<>();
		map.put("em_code", "4201");
		map.put("user_code", "2c908157559f0af101559f24e26d000b");
		map.put("phone", "");
		map.put("email", "");
		map.put("user_name", "4201");
		userList.add(map);

		map = new HashMap<>();
		map.put("users", userList);
		returnList.add(map);

		return JSONArray.toJSONString(returnList);
	}
}
