package com.shareworx.ezfm.webservice;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

/**
 * cxf配置工具类
 * 
 * @author jin.li
 *
 */
public class CxfClientUtil {
	/* 获取服务端连接的超时时间，单位为毫秒 */
	public static final int CXF_CLIENT_CONNECT_TIMEOUT = 5 * 1000;
	/* 获取连接后接收数据的超时时间，单位为毫秒 */
	public static final int CXF_CLIENT_RECEIVE_TIMEOUT = 10 * 1000;

	/**
	 * 使用代理方式设置接口请求超时时间
	 * 
	 * @param service
	 */
	public static void configTimeout(Object service) {
		Client proxy = ClientProxy.getClient(service);
		HTTPConduit conduit = (HTTPConduit) proxy.getConduit();
		HTTPClientPolicy policy = new HTTPClientPolicy();
		policy.setConnectionTimeout(CXF_CLIENT_CONNECT_TIMEOUT);
		policy.setReceiveTimeout(CXF_CLIENT_RECEIVE_TIMEOUT);
		conduit.setClient(policy);
	}
}
