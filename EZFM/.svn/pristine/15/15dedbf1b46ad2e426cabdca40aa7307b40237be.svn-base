package com.shareworx.ezfm.webservice.eba.inner;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

/**
 * webService客户端业务操作
 * 
 * @author jin.li
 *
 */
public class EbaClient {
	public static void main(String[] args) {
		final JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
//		final String address = "http://123.57.30.221:8080/yjwy/webServices/eba?wsdl";
		final String address = "http://localhost:8080/ezfm/webServices/eba?wsdl";
		factory.setAddress(address);
		factory.setServiceClass(EbaWarningDataService.class);
		EbaWarningDataService hw = (EbaWarningDataService) factory.create();
		String str = hw.getTest("见证奇迹的时刻！");
		System.out.println(str);
		System.out.println("=====测试通过======");
		String reportId = "8ce73321-8f9e-46a8-941c-3cc3485d1965";
		String deviceCode = "QSS0-00-00000-01-000=SB-NY-NS-FB00011";
		String warningContent = "【重要告警】湿度-低,当前值 25 告警值 25;";
		String warningValue = "25";
		String warningTime = "2016-11-15 12:18:12";
		String crop_code = "YYJT";
		String eliminate_url = "http://222.180.250.162:9755/AlarmService.asmx";
		System.out.println("接口地址：" + address);
		System.out.println("预警id：" + reportId);
		System.out.println("设备编号：" + deviceCode);
		System.out.println("报警信息：" + warningContent);
		System.out.println("报警值：" + warningValue);
		System.out.println("报警时间：" + warningTime);
		System.out.println("企业编号：" + crop_code);
		System.out.println("告警消除地址：" + eliminate_url);
		String result = hw.pushWarningData(reportId, deviceCode, warningContent, warningValue, warningTime, crop_code, eliminate_url);
		System.out.println(result);
	}
}
