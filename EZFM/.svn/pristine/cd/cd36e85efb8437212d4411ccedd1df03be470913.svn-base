package com.shareworx.ezfm.webservice.yjq.inner;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * webService客户端业务操作
 * 
 * @author jin.li
 *
 */
public class YjqClient {
	public static void main(String[] args) {
		final JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		final String address = "http://219.141.190.18:9090/yjwy/webServices/yjq?wsdl";
		factory.setAddress(address);
		factory.setServiceClass(YjqDataService.class);
		YjqDataService hw = (YjqDataService) factory.create();
		String str = hw.getTest("见证奇迹的时刻！");
		System.out.println(str);
//		System.out.println(hw.eventList("","","","",4,3));
		System.out.println(hw.eventDetails("000000201609070004OE"));
		
//		System.out.println(hw.ownerResources("000000201609060004KH"));
//		System.out.println(hw.eventClassHistoryAddressInfo("0000002016090200048J","000000201609010003RX"));
	}
}
