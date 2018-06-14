package com.shareworx.ezfm.utils;

import com.shareworx.ezfm.app.util.SysConfigurationReadUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

public class ShortMessageUtil {

	private static String alidayv_url = "";
	private static String alidayv_appkey = "";
	private static String alidayv_secret = "";
    private static String alidayv_signature = "";
    private static String alidayv_templet1_id = "";
	
	static {
		try {
			alidayv_url = SysConfigurationReadUtil.getInstance().getConfigItem(
					"alidayv_url");
			alidayv_appkey = SysConfigurationReadUtil.getInstance().getConfigItem(
					"alidayv_appkey");
			alidayv_secret = SysConfigurationReadUtil.getInstance().getConfigItem(
					"alidayv_secret");
			alidayv_signature = SysConfigurationReadUtil.getInstance().getConfigItem(
					"alidayv_signature");
			alidayv_templet1_id = SysConfigurationReadUtil.getInstance().getConfigItem(
					"alidayv_templet1_id");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void SimpleSend(String number){
		TaobaoClient client = new DefaultTaobaoClient(alidayv_url, alidayv_appkey, alidayv_secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType("normal");
		req.setSmsFreeSignName(alidayv_signature);
		req.setSmsParamString("{code:'test',password:'test'}");
		req.setRecNum(number);
		req.setSmsTemplateCode(alidayv_templet1_id);
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rsp.getBody());
	}
	
	public static void SendAccount(String number,String code,String password){
		TaobaoClient client = new DefaultTaobaoClient(alidayv_url, alidayv_appkey, alidayv_secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("");
		req.setSmsType("normal");
		req.setSmsFreeSignName(alidayv_signature);
		req.setSmsParamString("{code:'"+code+"',password:'"+password+"'}");
		req.setRecNum(number);
		req.setSmsTemplateCode(alidayv_templet1_id);
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(rsp.getBody());
	}
	
}
