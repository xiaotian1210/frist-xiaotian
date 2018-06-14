package com.shareworx.ezfm.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.dms365.crypto.DESEncrypt;
import com.dms365.crypto.util.Constants;
import com.dms365.ezfm.limitcheck.interceptor.LimitCheckInterceptor;
import com.shareworx.ezfm.limitcheck.PrivateLimitCheckFunction;
import com.shareworx.ezfm.limitcheck.PublicLimitCheckFunction;
import com.shareworx.ezfm.thread.RegisterThread;
import com.shareworx.ezfm.timer.DVDataStatisticsTimer;
import com.shareworx.ezfm.utils.EnvUtil;
import com.shareworx.ezfm.utils.VersionUtil;
import com.shareworx.platform.util.SpringUtils;

@Component
public class ApplicationRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private Environment env;
	
	private static final Logger logger = LoggerFactory.getLogger(ApplicationRefreshedEventListener.class);
	
	public void onApplicationEvent(ContextRefreshedEvent event) {
		/*
		ApplicationContext applicationContext = event.getApplicationContext();  
        WebApplicationContext webApplicationContext = (WebApplicationContext)applicationContext;  
        ServletContext servletContext = webApplicationContext.getServletContext();  
        String contextPath = servletContext.getContextPath();
        FreeMarkerViewResolver freeMarkerViewResolver = (FreeMarkerViewResolver) SpringUtils.getBean(FreeMarkerViewResolver.class);
		freeMarkerViewResolver.getAttributesMap().put("getContextPath", new GetContextPathTemplateMethodModel(contextPath));
        */
		
		EnvUtil.setEnv(env);
		String license = env.getProperty("license", "");
		if(license==null||"".equals(license)) {
			String errorMsg = "请在配置文件application.properties中配置license。";
			logger.error(errorMsg);
			throw new RuntimeException(errorMsg);
		}
		if(license.equals(com.shareworx.ezfm.constant.Constants.PUBLIC_LICENSE)) {
			VersionUtil.setPrivate(false);
		} else {
			VersionUtil.setPrivate(true);
		}
		if(VersionUtil.isPrivate()) {
			String licenseServer = env.getProperty("database-license-server", "");
			if(licenseServer==null||"".equals(licenseServer)) {
				String errorMsg = "请在配置文件application.properties中配置database-license-server。";
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			String code = null;
			try {
				code = DESEncrypt.decrypt(license, Constants.LICENSE_CRYPTO_KEY);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if(code==null) {
				String errorMsg = "授权文件无效。";
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			String[] codes = code.split(";", -1);
			VersionUtil.setCodes(codes);
			String isWR = codes[3];
			String readURL = env.getProperty("jdbc.read.url", "");
			String writeURL = env.getProperty("jdbc.write.url", "");
			if("1".equals(isWR)) {
				if(readURL.equals(writeURL)) {
					String errorMsg = "您必须配置数据库读写分离。";
					logger.error(errorMsg);
					throw new RuntimeException(errorMsg);
				}
			} else if("0".equals(isWR)) {
				if(!readURL.equals(writeURL)) {
					String errorMsg = "您不能配置数据库读写分离，如需要，请申请更高的授权。";
					logger.error(errorMsg);
					throw new RuntimeException(errorMsg);
				}
			} else {
				String errorMsg = "授权文件无效。";
				logger.error(errorMsg);
				throw new RuntimeException(errorMsg);
			}
			RegisterThread thread = new RegisterThread(licenseServer, license);
			new Thread(thread).start();
		}
		LimitCheckInterceptor limitCheckInterceptor = SpringUtils.getBean(LimitCheckInterceptor.class);
		if(VersionUtil.isPrivate()) {
			PrivateLimitCheckFunction privateLimitCheckFunction = SpringUtils.getBean(PrivateLimitCheckFunction.class);
			limitCheckInterceptor.setLimitCheckFunction(privateLimitCheckFunction);
		} else {
			PublicLimitCheckFunction publicLimitCheckFunction = SpringUtils.getBean(PublicLimitCheckFunction.class);
			limitCheckInterceptor.setLimitCheckFunction(publicLimitCheckFunction);
		}
		
		if("1".equals(env.getProperty("dv.enable", "0"))) {
			DVDataStatisticsTimer timer = SpringUtils.getBean(DVDataStatisticsTimer.class);
			timer.last7WorkTask();
			timer.lastYearWorkTaskComplete();
		}
	}
	
	
	
}
