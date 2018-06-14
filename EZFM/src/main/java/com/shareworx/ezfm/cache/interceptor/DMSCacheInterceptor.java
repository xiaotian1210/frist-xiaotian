package com.shareworx.ezfm.cache.interceptor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.cache.annotation.DMSCacheEvict;
import com.shareworx.ezfm.cache.annotation.DMSCacheable;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.cache.IDmsCacheManager;

@Component
@Aspect
@Order(value=1)
public class DMSCacheInterceptor {
	
	private static final String DMSCACHE_NAMESPACE = "__dmscache_namespace__";
	
	@Autowired
	@Qualifier("cacheManager")
	private IDmsCacheManager cacheManager;

	@SuppressWarnings("unchecked")
	@Around("@annotation(com.shareworx.ezfm.cache.annotation.DMSCacheable )")
    public Object getCache(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		try {
			MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
			Method method = methodSignature.getMethod();
			EvaluationContext context = getEvaluationContext(thisJoinPoint);
			DMSCacheable dmsCache = method.getAnnotation(DMSCacheable.class);
			//获取缓存命名空间
			String namespace = dmsCache.namespace();
			String[] namespaces = null;
			if(namespace==null||"".equals(namespace)) {
				namespace = DMSCACHE_NAMESPACE;
				namespaces = new String[]{namespace};
			} else {
				ExpressionParser parser = new SpelExpressionParser();
				Expression expression = parser.parseExpression(namespace);
				Object namespaceValue = expression.getValue(context);
				if(namespaceValue instanceof String[]) {
					namespaces = (String[]) namespaceValue;
				} else if (namespaceValue instanceof List){
					List<String> namespaceList = new ArrayList<String>();
					List<Object> namespaceValueList = (List<Object>) namespaceValue;
					for(int i=0;i<namespaceValueList.size();i++) {
						Object o = namespaceValueList.get(i);
						if(o instanceof String[]) {
							String[] strs = (String[]) o;
							for(int j=0;j<strs.length;j++) {
								namespaceList.add(strs[j]);
							}
						} else if(o instanceof String) {
							namespaceList.add(o+"");
						}
					}
					namespaces = namespaceList.toArray(new String[]{});
				} else {
					namespaces = new String[]{namespaceValue+""};
				}
			}
			//获取缓存key
			String key = dmsCache.key();
			String[] cacheKey = null;
			if(key==null||"".equals(key)) {
				key = "#_methodSignature";
			}
			if(key!=null) {
				ExpressionParser parser = new SpelExpressionParser();
				Expression expression = parser.parseExpression(key);
				Object cacheKeyObject = expression.getValue(context);
				if(cacheKeyObject instanceof String[]) {
					cacheKey = (String[]) cacheKeyObject;
				} else if (cacheKeyObject instanceof List){
					List<String> cacheKeyList = new ArrayList<String>();
					List<Object> cacheKeyValueList = (List<Object>) cacheKeyObject;
					for(int i=0;i<cacheKeyValueList.size();i++) {
						Object o = cacheKeyValueList.get(i);
						if(o instanceof String[]) {
							String[] strs = (String[]) o;
							for(int j=0;j<strs.length;j++) {
								cacheKeyList.add(strs[j]);
							}
						} else if(o instanceof String) {
							cacheKeyList.add(o+"");
						}
					}
					cacheKey = cacheKeyList.toArray(new String[]{});
				} else {
					cacheKey = new String[]{cacheKeyObject+""};
				}
			}
			//获取缓存过期时间
			long timeout = dmsCache.timeout();
			TimeUnit timeUnit = dmsCache.timeUnit();
			long now = new Date().getTime();
			boolean cacheable = false;
			long create = getCacheTimeout(namespaces[0], cacheKey[0]);
			if(timeout<0&&create>0) {
				cacheable = true;
			} else {
				//毫秒
				long expire = timeUnit.toMillis(timeout);
				if(create+expire>=now) {
					cacheable = true;
				}
			}
			if(cacheable) {
				return cacheManager.get(namespaces[0], cacheKey[0]);
			} else {
				Object value = thisJoinPoint.proceed();
				for(int i=0;i<namespaces.length;i++) {
					for(int j=0;j<namespaces.length;j++) {
						cacheManager.put(namespaces[i], cacheKey[j], value);
						cacheManager.put(namespaces[i], "_"+cacheKey[j], now);
					}
				}
				return value;
			}
		} catch (Throwable e) {
			throw e;
		}
    }
	
	@SuppressWarnings("unchecked")
	@Around("@annotation(com.shareworx.ezfm.cache.annotation.DMSCacheEvict )")
    public Object evictCache(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		try {
			Object value = thisJoinPoint.proceed();
			MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
			Method method = methodSignature.getMethod();
			EvaluationContext context = getEvaluationContext(thisJoinPoint);
			DMSCacheEvict dmsCache = method.getAnnotation(DMSCacheEvict.class);
			//获取缓存命名空间
			String namespace = dmsCache.namespace();
			String[] namespaces = null;
			if(namespace==null||"".equals(namespace)) {
				namespace = DMSCACHE_NAMESPACE;
				namespaces = new String[]{namespace};
			} else {
				ExpressionParser parser = new SpelExpressionParser();
				Expression expression = parser.parseExpression(namespace);
				Object namespaceValue = expression.getValue(context);
				if(namespaceValue instanceof String[]) {
					namespaces = (String[]) namespaceValue;
				} else if (namespaceValue instanceof List){
					List<String> namespaceList = new ArrayList<String>();
					List<Object> namespaceValueList = (List<Object>) namespaceValue;
					for(int i=0;i<namespaceValueList.size();i++) {
						Object o = namespaceValueList.get(i);
						if(o instanceof String[]) {
							String[] strs = (String[]) o;
							for(int j=0;j<strs.length;j++) {
								namespaceList.add(strs[j]);
							}
						} else if(o instanceof String) {
							namespaceList.add(o+"");
						}
					}
					namespaces = namespaceList.toArray(new String[]{});
				} else {
					namespaces = new String[]{namespaceValue+""};
				}
			}
			String allEntries = dmsCache.allEntries();
			boolean allEntriesBoolean = false;
			if("false".equals(allEntries)||"true".equals(allEntries)) {
				allEntriesBoolean = Boolean.parseBoolean(allEntries);
			} else {
				ExpressionParser parser = new SpelExpressionParser();
				Expression expression = parser.parseExpression(allEntries);
				allEntriesBoolean = Boolean.parseBoolean(expression.getValue(context)+"");
			}
			if(allEntriesBoolean) {
				for(int i=0;i<namespaces.length;i++) {
					cacheManager.clearNamespace(namespaces[i]);
				}
				return value;
			}
			//获取缓存key
			String key = dmsCache.key();
			String[] cacheKey = null;
			if(key==null||"".equals(key)) {
				key = "#_methodSignature";
			}
			if(key!=null) {
				ExpressionParser parser = new SpelExpressionParser();
				Expression expression = parser.parseExpression(key);
				Object cacheKeyObject = expression.getValue(context);
				if(cacheKeyObject instanceof String[]) {
					cacheKey = (String[]) cacheKeyObject;
				} else if (cacheKeyObject instanceof List){
					List<String> cacheKeyList = new ArrayList<String>();
					List<Object> cacheKeyValueList = (List<Object>) cacheKeyObject;
					for(int i=0;i<cacheKeyValueList.size();i++) {
						Object o = cacheKeyValueList.get(i);
						if(o instanceof String[]) {
							String[] strs = (String[]) o;
							for(int j=0;j<strs.length;j++) {
								cacheKeyList.add(strs[j]);
							}
						} else if(o instanceof String) {
							cacheKeyList.add(o+"");
						}
					}
					cacheKey = cacheKeyList.toArray(new String[]{});
				} else {
					cacheKey = new String[]{cacheKeyObject+""};
				}
			}
			for(int i=0;i<namespaces.length;i++) {
				for(int j=0;j<cacheKey.length;j++) {
					cacheManager.remove(namespaces[i], "_"+cacheKey[j]);
					cacheManager.remove(namespaces[i], cacheKey[j]);
				}
			}
			return value;
		} catch (Throwable e) {
			throw e;
		}
    }

	private long getCacheTimeout(String namespace, String key) {
		Object value = cacheManager.get(namespace, "_"+key);
		if(value!=null) {
			return Long.parseLong(value+"");
		}
		return -1;
	}
	
	private EvaluationContext getEvaluationContext(ProceedingJoinPoint thisJoinPoint) {
		MethodSignature methodSignature = (MethodSignature) thisJoinPoint.getSignature();
		Method method = methodSignature.getMethod();
		EvaluationContext context = new StandardEvaluationContext();
		String[] strings = methodSignature.getParameterNames(); 
		Object[] objects = thisJoinPoint.getArgs();
		if(strings!=null&&strings.length>0) {
			for(int i=0;i<strings.length;i++) {
				String name = strings[i];
				context.setVariable(name, objects[i]);
			}
		}
		YJWYUserModel currentUser = null;
		try {
			currentUser = UserUtil.getCurrentUser();
		} catch(Exception e) {
//			e.printStackTrace();
		}
		if(currentUser!=null) {
			context.setVariable("_currentUser", currentUser);
		}
		Class<?>[] params =  method.getParameterTypes();
		String paramSign = "";
		if(params!=null&&params.length>0) {
			for(int i=0;i<params.length;i++) {
				paramSign += params[i].getName();
				if(i!=params.length-1) {
					paramSign+=";";
				}
			}
		}
		String methodSignatureKey = thisJoinPoint.getTarget().getClass().getName()+"|"+method.getName()+"|"+method.getModifiers()+"|"+method.getReturnType().getName()+"|"+paramSign;
		context.setVariable("_methodSignature", methodSignatureKey);
		return context;
	}
	
}
