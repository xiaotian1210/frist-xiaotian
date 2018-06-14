package com.shareworx.ezfm.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DMSCacheEvict {
	
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
	 * 是否清除所有key
	 * @return
	 */
	String allEntries() default "false";

}
