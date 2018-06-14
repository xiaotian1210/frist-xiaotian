package com.shareworx.ezfm.app.push;

import java.util.HashMap;
import java.util.Map;

import com.shareworx.ezfm.app.push.modelVo.AndroidModelVo;
import com.shareworx.ezfm.app.push.modelVo.IOSModelVo;
import com.shareworx.ezfm.app.util.AppEmptyUtils;

public class PushUtils {

	/**
	 * 单发
	 * @param mobile_id 设备号
	 * @param type 类型  IOS Android 
	 * @param reserve_str 预留字段
	 */
	@SuppressWarnings("unchecked")
	public static void pushUnicast(String mobile_id, String type, String reserve_str, Map<String,Object> pmMap){
		if ("IOS".equals(type)) {
			//单推  开始发送推送
			IOSModelVo iModel = new IOSModelVo();
			iModel.setAlert(reserve_str);
			iModel.setDevice_token(mobile_id);
			iModel.setBadge("0");
			iModel.setSound("default");
			iModel.setCustomized_field_key("test");
			iModel.setCustomized_field_value("helloUmeng");
			iModel.setPm_module(String.valueOf(pmMap.get("pm_module")));
			iModel.setPm_function(String.valueOf(pmMap.get("pm_function")));
			iModel.setPm_ext((Map<String, Object>) pmMap.get("pm_ext"));
			
			try {
				PushMethod.sendIOSUnicast(iModel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if ("Android".equals(type)){
			AndroidModelVo aModelVo = new AndroidModelVo();
			
			aModelVo.setDevice_token(mobile_id);
			aModelVo.setTicker("达美盛资产云");
			aModelVo.setTitle("新消息");
			aModelVo.setText(reserve_str);
			Map<String,Object> map = new HashMap<>();
			map.put("pm_function", pmMap.get("pm_function"));
			map.put("pm_module", pmMap.get("pm_module"));
			aModelVo.setExtra_field_map(map);
			try {
				PushMethod.sendAndroidUnicast(aModelVo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 单发
	 * @param mobile_id 设备号
	 * @param type 类型  IOS Android 
	 * @param reserve_str 预留字段
	 * @param reserve_str 预留字段
	 */
	@SuppressWarnings("unchecked")
	public static void pushListcast(String ios_mobile_ids, String android_mobile_ids, String reserve_str, Map<String,Object> pmMap){
		
		if (!AppEmptyUtils.isEmpty(ios_mobile_ids)) {
			//单推  开始发送推送
			IOSModelVo iModel = new IOSModelVo();
			iModel.setAlert(reserve_str);
			iModel.setDevice_token(ios_mobile_ids);
			iModel.setBadge("0");
			iModel.setSound("default");
			iModel.setCustomized_field_key("test");
			iModel.setCustomized_field_value("helloUmeng");
			iModel.setPm_module(String.valueOf(pmMap.get("pm_module")));
			iModel.setPm_function(String.valueOf(pmMap.get("pm_function")));
			iModel.setPm_ext((Map<String, Object>) pmMap.get("pm_ext"));
			try {
				PushMethod.sendIOSListcast(iModel);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!AppEmptyUtils.isEmpty(android_mobile_ids)){
			AndroidModelVo aModelVo = new AndroidModelVo();
			
			aModelVo.setDevice_token(android_mobile_ids);
			aModelVo.setTicker("达美盛资产云");
			aModelVo.setTitle("新消息");
			aModelVo.setText(reserve_str);
			Map<String,Object> map = new HashMap<>();
			map.put("pm_function", pmMap.get("pm_function"));
			map.put("pm_module", pmMap.get("pm_module"));
			aModelVo.setExtra_field_map(map);
			
			try {
				PushMethod.sendAndroidListcast(aModelVo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
