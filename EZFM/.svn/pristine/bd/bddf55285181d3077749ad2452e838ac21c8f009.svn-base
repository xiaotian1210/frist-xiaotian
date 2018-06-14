package com.shareworx.ezfm.webservice.eba.inner;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 设备预警信息推送接收业务接口
 * 
 * @author jin.li
 *公司测试地址http://219.141.190.18:9090/yjwy/yjwy/webServices/eba?wsdl
 *本机地址：http://localhost:8080/yjwy/webServices/eba?wsdl
 *阿里云测试地址：http://123.57.30.243:8080/yjwy/webServices/eba?wsdl
 *阿里云正式环境：http://123.57.30.221:8080/yjwy/webServices/eba?wsdl
 */
//@WebService(targetNamespace = "http://localhost:8080/yjwy/webServices/eba?wsdl")
@WebService(targetNamespace = "http://123.57.30.221:8080/yjwy/webServices/eba?wsdl")
public interface EbaWarningDataService {
	/**
	 * 测试方法
	 * 
	 * @param test
	 * @return
	 */
	@WebMethod
	@WebResult
	String getTest(String test);

	/**
	 * eba设备预警推送接收方法
	 * 
	 * @param reportId
	 *            预警信息id（主键）
	 * @param deviceCode
	 *            预警设备编号
	 * @param warningContent
	 *            预警信息
	 * @param warningValue
	 *            预警值
	 * @param warningTime
	 *            预警产生时间
	 * @param crop_code
	 *            企业编码
	 * @param eliminate_url
	 *            告警消除地址
	 * @return
	 */
	@WebMethod
	@WebResult
	String pushWarningData(String reportId, String deviceCode, String warningContent, String warningValue, String warningTime, String crop_code, String eliminate_url);

}
