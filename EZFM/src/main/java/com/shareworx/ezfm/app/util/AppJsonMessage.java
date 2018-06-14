package com.shareworx.ezfm.app.util;

import com.alibaba.fastjson.JSONObject;
/**
 * 返回json 样式 封装
 * @author lingwei.li
 *
 */
public class AppJsonMessage {
	
	/**
	 * 封装jsonMap 返回正确的json样式 
	 * 
	 * st: 1468899101，
	 * code:0,
	 * msg:＂success＂，
	 * data:{}
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject toJsonMsgTrue(Object obj){
		
		JSONObject jsonMap = new JSONObject();
		//获取当前时间戳
		jsonMap.put("st", System.currentTimeMillis());
		jsonMap.put("data", obj);
		jsonMap.put("code", 0);
		jsonMap.put("msg", "success");
		
		return jsonMap;
		
	}
	
	/**
	 * 封装jsonMap 返回错误的json样式 
	 * 
	 * code:1, code值是变量
	 * msg:＂msg＂，
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject toJsonMsgFalse(int code,String msg){
		
		JSONObject jsonMap = new JSONObject();
		jsonMap.put("code", code);
		jsonMap.put("msg", msg);
		
		return jsonMap;
		
	}
	
	/**
	 * 封装jsonMap 返回json样式 
	 * 
	 * code:1, code值是变量
	 * msg:＂success＂，
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject toJsonMsg(int code,String msg){
		
		JSONObject jsonMap = new JSONObject();
		jsonMap.put("code", code);
		jsonMap.put("msg", msg);
		
		return jsonMap;
		
	}
	
	/**
	 * 封装jsonMap 返回json样式 
	 * 
	 * code:1, code值是变量
	 * msg:＂success＂，
	 * data:{}
	 * 
	 * @param json
	 * @return
	 */
	public static JSONObject toJsonMsgNoSt(int code,String msg,Object obj){
		
		JSONObject jsonMap = new JSONObject();
		jsonMap.put("code", 0);
		jsonMap.put("msg", msg);
		jsonMap.put("data", obj);
		
		return jsonMap;
		
	}
}
