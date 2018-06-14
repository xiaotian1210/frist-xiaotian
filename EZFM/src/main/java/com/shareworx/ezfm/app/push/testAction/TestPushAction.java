package com.shareworx.ezfm.app.push.testAction;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.push.PushMethod;
import com.shareworx.ezfm.app.push.modelVo.AndroidModelVo;
import com.shareworx.ezfm.app.push.modelVo.IOSModelVo;
import com.shareworx.ezfm.app.push.modelVo.PmModelVo;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;

@Controller
@RequestMapping("app/push")
public class TestPushAction {

	
	@RequestMapping(value = "testpush", method = RequestMethod.POST)
	public @ResponseBody JSONObject patrolList(HttpServletRequest reqParam) throws Exception{
		
		String mobilePlatform = reqParam.getParameter("mobilePlatform");
		String mobileID = reqParam.getParameter("mobileID");
		String module = reqParam.getParameter("module");
		String func = reqParam.getParameter("func");
		String taskId = reqParam.getParameter("taskId");
		String content =  reqParam.getParameter("content");
		
		if ("IOS".equals(mobilePlatform)) {
			
			IOSModelVo iModel = new IOSModelVo();

			iModel.setAlert(content);
			// my phone
			iModel.setDevice_token(mobileID);
			iModel.setCustomized_field_key("test");
			iModel.setCustomized_field_value("helloUmeng");
			((PmModelVo) iModel).setPm_module(module);
			((PmModelVo) iModel).setPm_function(func);
			Map<String, Object> map = new HashMap<>();
			map.put("task_id", taskId);
			iModel.setPm_ext(map);

			try {
				PushMethod.sendIOSUnicast(iModel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else{
			
			AndroidModelVo aModelVo = new AndroidModelVo();
			
			aModelVo.setDevice_token(mobileID);
			aModelVo.setTicker("工单信息");
			aModelVo.setTitle("工单信息");
			aModelVo.setText("测试工单信息");
			Map<String, Object> map = new HashMap<>();
			map.put("pm_function", 201);
			map.put("pm_module", 2);
			aModelVo.setExtra_field_map(map);
			try {
				PushMethod.sendAndroidUnicast(aModelVo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return AppJsonMessage.toJsonMsg(0, "success");
	}
}
