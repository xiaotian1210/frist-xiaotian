package com.shareworx.ezfm.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DMSCacheable {
	
	/**
	 * 缓存命名空间
	 * @return
	 */
	String namespace() default "";

	/**
	 * 缓存key
	 * @return
	 */
	String key() default "";
	
	/**
	 * 过期时间，默认永不过期
	 * @return
	 */
	long timeout() default -1;
	
	/**
	 * 时间单位，默认为秒
	 * @return
	 */
	TimeUnit timeUnit() default TimeUnit.SECONDS;

}
