package com.shareworx.ezfm.thread;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import com.dms365.crypto.util.Constants;
import com.dms365.crypto.util.HttpParamsCryptoUtil;
import com.shareworx.ezfm.utils.JsonUtil;
import com.shareworx.ezfm.utils.ResultBean;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class RegisterThread implements Runnable {
	
	public static final Logger logger = LoggerFactory.getLogger(RegisterThread.class);
	
	private String licenseServer;
	
	private String license;

	//是否注册成功
	private static boolean register = false;
	
	//轮询间隔，单位秒
	private static int interval = 24*60*60;
	
	//重试次数
	private static int retry = 3;
	
	public RegisterThread(String licenseServer, String license) {
		this.licenseServer = licenseServer;
		this.license = license;
	}
	
	public void run() {
		while(register==false) {
			try {
				register = register(licenseServer, license);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			if(register) {
				retry = 3;
				register = false;
			} else {
				retry--;
			}
			if(retry<0) {
				System.exit(0);
			}
			try {
				Thread.sleep(interval*1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private boolean register(String licenseServer, String license) throws Exception {
		ClientConfig cc = new DefaultClientConfig();  
        cc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);  
		Client client = Client.create(cc);
		URI uri = new URI(licenseServer+"/webclient/register");
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("license", license);
		String time = new Date().getTime()+"";
		formData.add("time", time);
		Map<String, String> pmap = new HashMap<String, String>();
		pmap.put("license", license);
		pmap.put("time", time);
		String sign = HttpParamsCryptoUtil.getSign(Constants.HTTP_SIGN_CRYPTO_KEY, pmap);
		formData.add("sign", sign);
		WebResource webResource = client.resource(uri);
		ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON_UTF8_VALUE).type(MediaType.APPLICATION_FORM_URLENCODED_VALUE) .post(ClientResponse.class, formData);
		String result = response.getEntity(String.class);
		Map<String, Object> resultMap = JsonUtil.toObject(result, HashMap.class);
		String status = resultMap.get("status")==null?null:resultMap.get("status")+"";
		Long now = new Date().getTime();
		if(ResultBean.SUCCESS.equals(status)) {
			Map map = (Map) resultMap.get("data");
			String newTime = map.get("time")+"";
			Long t = Long.parseLong(newTime);
			if(Math.abs((int)(now-t))>60000) {
				logger.warn("请同步服务器时间！");
				return false;
			}
			Map<String, String> params = new HashMap<String, String>();
			params.put("time", newTime);
			boolean flag = HttpParamsCryptoUtil.validateSign(map.get("sign")+"", Constants.HTTP_SIGN_CRYPTO_KEY, params);
			if(flag==false) {
				logger.warn("签名验证失败！");
				return false;
			}
			return true;
		}
		return false;
	}
	
}
