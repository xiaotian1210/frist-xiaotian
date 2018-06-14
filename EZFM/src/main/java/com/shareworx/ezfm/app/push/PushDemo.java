package com.shareworx.ezfm.app.push;

import java.util.HashMap;
import java.util.Map;

import com.shareworx.ezfm.app.push.modelVo.AndroidModelVo;
import com.shareworx.ezfm.app.push.modelVo.IOSModelVo;
import com.shareworx.ezfm.app.push.modelVo.PmModelVo;
/**
 * 推送调用 demo 
 * @author lingwei.li
 *
 */
public class PushDemo {

	public static void main(String[] args) {
		sendAndroidUnicast();
	}

	/**
	 * ios 推送测试
	 */
	public static void sendIOSUnicast() {
		IOSModelVo iModel = new IOSModelVo();

		iModel.setAlert(
				"您有一摆个待办任务，请及时处理，否则后果自负；您有一摆个待办任务，请及时处理，否则后果自负；您有一摆个待办任务，请及时处理，否则后果自负；您有一摆个待办任务，请及时处理，否则后果自负；您有一摆个待办任务，请及时处理，否则后果自负；您有一摆个待办任务，请及时处理，否则后果自负；您有一摆个待办任务，请及时处理，否则后果自负；您有一摆个待办任务，请及时处理，否则后果自负；您有一摆个待办任务，请及时处理，否则后果自负；您有一摆个待办任务，请及时处理，否则后果自负；");
		;
		// my phone
		iModel.setDevice_token("f5f28b2472e4f20dc8bc97adfaddbd4fd8e9116cd4f43c5e8e45d0f450e88dac");
		// pushModel.setDevice_token("353bcc9f71881d561719e354669cb8a321aaa57262b606c5ade15273bbfb86de");
		iModel.setBadge("0");
		iModel.setSound("default");
		iModel.setCustomized_field_key("test");
		iModel.setCustomized_field_value("helloUmeng");
		((PmModelVo) iModel).setPm_module("1");
		((PmModelVo) iModel).setPm_function("2");
		Map<String, Object> map = new HashMap<>();
		map.put("task_id", 123);
		map.put("stats", 1);
		iModel.setPm_ext(map);

		try {
			PushMethod.sendIOSUnicast(iModel);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendAndroidUnicast(){
		AndroidModelVo aModelVo = new AndroidModelVo();
		
		aModelVo.setDevice_token("91f4f87cbff58884144c730339d19945");
		aModelVo.setTicker("工单信息");
		aModelVo.setTitle("工单信息");
		aModelVo.setText("测试工单信息");
		aModelVo.setExtra_field_key("test");
		aModelVo.setExtra_field_value("helloUmeng");
		try {
			PushMethod.sendAndroidUnicast(aModelVo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
