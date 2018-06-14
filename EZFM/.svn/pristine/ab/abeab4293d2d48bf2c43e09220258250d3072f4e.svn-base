package com.shareworx.ezfm.webservice.callcenter.inner;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 * 呼叫中心业务接口
 * 
 * @author jin.li
 *公司测试地址http://219.141.190.18:9090/yjwy/yjwy/webServices/callcenter?wsdl
 *本机地址：http://localhost:8080/yjwy/webServices/callcenter?wsdl
 *阿里云测试地址：http://123.57.30.243:8080/yjwy/webServices/callcenter?wsdl
 *阿里云正式环境：http://123.57.30.221:8080/yjwy/webServices/callcenter?wsdl
 */
//@WebService(targetNamespace = "http://localhost:8080/yjwy/webServices/callcenter?wsdl")
@WebService(targetNamespace = "http://123.57.30.221:8080/yjwy/webServices/callcenter?wsdl")
//@WebService(targetNamespace = "http://192.168.0.92:8080/yjwy/webServices/callcenter?wsdl")
public interface CallCenterDataService {
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
	 * 推送呼叫用户用户数据
	 * 
	 * @param json
	 * @return
	 */
	@WebMethod
	@WebResult
	String pushCallCenterUsersInfo(String json);
}
