package com.shareworx.ezfm.webservice;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shareworx.ezfm.webservice.callcenter.inner.CallCenterDataService;
import com.shareworx.ezfm.webservice.callcenter.inner.CallCenterDataServiceImpl;
import com.shareworx.ezfm.webservice.eba.inner.EbaWarningDataService;
import com.shareworx.ezfm.webservice.eba.inner.EbaWarningDataServiceImpl;
import com.shareworx.ezfm.webservice.yjq.inner.YjqDataService;
import com.shareworx.ezfm.webservice.yjq.inner.YjqDataServiceImpl;

/**
 * 发布webService服务,通过配置整合cxf
 * 
 * @author jin.li
 *
 */
@Configuration
public class CxfConfig {

	@Bean(name = "cxfServlet")
	public ServletRegistrationBean dispatcherServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/ezfm/webServices/*");
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@Bean
	public EbaWarningDataService ebaWarningDataService() {
		return new EbaWarningDataServiceImpl();
	}

	@Bean(name = "ebaEndpoint")
	public Endpoint ebaEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), ebaWarningDataService());
		endpoint.publish("/eba");
		return endpoint;
	}

	@Bean
	public YjqDataService yjqDataService() {
		return new YjqDataServiceImpl();
	}

	@Bean(name = "yjqEndpoint")
	public Endpoint yjqEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), yjqDataService());
		endpoint.publish("/yjq");
		return endpoint;
	}

	@Bean
	public CallCenterDataService callCenterDataService() {
		return new CallCenterDataServiceImpl();
	}

	@Bean(name = "callCenterEndpoint")
	public Endpoint callCenterEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), callCenterDataService());
		endpoint.publish("/callcenter");
		return endpoint;
	}

}
