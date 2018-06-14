package com.shareworx.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class YJWYWebConfiguration extends WebMvcConfigurerAdapter {
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addViewController("/").setViewName("view/main/index.html");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		super.addViewControllers(registry);
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/*WebAuthInterceptor interceptor = new WebAuthInterceptor();
		interceptor.addExcludeMapping("/view/login/");
		interceptor.addExcludeMapping("/view/main/");
		interceptor.addExcludeMapping("/pageerror");
		interceptor.addExcludeMapping("/jsonerror");
		interceptor.addExcludeMapping("/error");
		registry.addInterceptor(interceptor);*/
		
		YJWYHttpRequestInterceptor requestInterceptor = new YJWYHttpRequestInterceptor();
		registry.addInterceptor(requestInterceptor).addPathPatterns("/ezfm/**");
		APPHttpRequestInterceptor appRequestInterceptor = new APPHttpRequestInterceptor();
		registry.addInterceptor(appRequestInterceptor).addPathPatterns("/app/**");
		//AllRequestInterceptor allRequestInterceptor = new AllRequestInterceptor();
		//registry.addInterceptor(allRequestInterceptor).addPathPatterns("/**");
	}
}
