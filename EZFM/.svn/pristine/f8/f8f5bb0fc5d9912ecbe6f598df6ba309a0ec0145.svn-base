package com.shareworx.ezfm.app.util;

import java.text.MessageFormat;

import javax.servlet.http.HttpServletRequest;
/**
 * logger 日志打印 util
 * @author lingwei.li
 *
 */
public class AppMessageFormatUtil {

	/**
	 * logger.info 打印日志 封装
	 * @param reqParam 请求参数
	 * @param ret 响应
	 * @return
	 */
	public static String loggerInfo(HttpServletRequest reqParam, Object ret, long start){
		
		String mobilePlatform = reqParam.getParameter("mobilePlatform");//设备类型
		
		String msgFm = MessageFormat.format("耗时:{0},请求:{1},响应:{2},设备类型:{3}",
				System.currentTimeMillis()-start, reqParam, ret, mobilePlatform);
		
		return msgFm;
		
	}
	
	/**
	 * logger.error 错误日志 封装
	 * @param reqParam 请求参数
	 * @param ret 响应
	 * @return
	 */
	public static String loggerError(HttpServletRequest reqParam, Exception e){
		
		String mobilePlatform = reqParam.getParameter("mobilePlatform");//设备类型
		
		String msgFm = MessageFormat.format("请求:{1},error:{2},设备类型:{3}",
				reqParam, e, mobilePlatform);
		
		return msgFm;
		
	}
}
