package com.shareworx;

import javax.servlet.Filter;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.filter.CharacterEncodingFilter;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages={"com.shareworx","com.dms365"})
public class Application extends SpringBootServletInitializer {

	private static Class<Application> applicationClass = Application.class;
	
	private static ApplicationContext ctx;
	
	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication app = new SpringApplication(Application.class);
		app.setWebEnvironment(true);
		app.setBannerMode(Mode.OFF);
		ctx = app.run(args);
		System.out.println("=============system started===============");
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) {
		return (T)ctx.getBean(name);
	}
	
	public static <T, E> T getBean(Class<? extends T> className) {
		return (T)ctx.getBean(className);
	}
	
	@Bean
	public Filter characterEncodingFilter() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(applicationClass);
	}
}
