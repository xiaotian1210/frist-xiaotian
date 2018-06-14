package com.shareworx.platform.cache.support;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.util.SerializeUtil;
import com.shareworx.platform.cache.CacheContents;
import com.shareworx.platform.cache.IDmsCacheManager;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.ShareworxAssert;
import com.shareworx.platform.util.StringUtils;

@Component("dmsRedisCachemanager")
@AutoConfigureAfter(name={"redisCacheManager", "redisTempalte"})
public class DmsRedisCacheManager3 implements IDmsCacheManager {

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	public final static String CACHE_NAMESPACE = CacheContents.CACHE_DEFAULT;
	public final static String KEY_LIST_NAMESPACE = "list_namespace";
	private ListOperations<String, String> listOps;
	private HashOperations<String, String, String> mapOps;

	@PostConstruct
	public void init() {
		this.listOps = redisTemplate.opsForList();
		this.mapOps = redisTemplate.opsForHash();
	}
	
	public RedisTemplate<String, String> getRedisTemplate() {
		return redisTemplate;
	}

	@Override
	public List<String> getNamespaces() {
		long size = listOps.size(KEY_LIST_NAMESPACE);
		if(size <= 0) {
			return null;
		}
		return listOps.range(KEY_LIST_NAMESPACE, 0, size);
	}

	@Override
	public List<String> getKeys(String namespace) {
		Set<String> set = mapOps.keys(namespace);
		if(!ArrayUtils.isEmpty(set)) {
			return new ArrayList<>(set);
		}
		return null;
	}

	@Override
	public void createCache(String namespace) {
		long size = listOps.size(namespace);
		if(size > 0) {
			List<String> list = listOps.range(KEY_LIST_NAMESPACE, 0, size);
			if(!ArrayUtils.isEmpty(list) && list.contains(namespace)) {
				return;
			}
		}
		listOps.rightPush(KEY_LIST_NAMESPACE, namespace);
	}

	@Override
	public void removeCache(String namespace) {
		listOps.remove(KEY_LIST_NAMESPACE, 0, namespace);
	}

	@Override
	public void put(String namespace, String key, Object value) {
		//ShareworxAssert.doAssert(value == null, "cache put value is null.");
		if(StringUtils.isEmpty(namespace)){
			namespace = CacheContents.CACHE_DEFAULT;
		}
		List<String> namespaceList = getNamespaces();
		if(ArrayUtils.isEmpty(namespaceList) || !namespaceList.contains(namespace)){
			createCache(namespace);
		}
		if(value == null) {
			remove(namespace, key);
			return;
		}
		mapOps.put(namespace, key, SerializeUtil.serialize(value));
	}
	
	@Override
	public void put(String namespace, String key, Object value, long timeout, TimeUnit timeUnit) {
		//ShareworxAssert.doAssert(value == null, "cache put value is null.");
		if(StringUtils.isEmpty(namespace)){
			namespace = CacheContents.CACHE_DEFAULT;
		}
		List<String> namespaceList = getNamespaces();
		if(ArrayUtils.isEmpty(namespaceList) || !namespaceList.contains(namespace)){
			createCache(namespace);
		}
		if(value == null) {
			remove(namespace, key);
			return;
		}
		mapOps.put(namespace, key, SerializeUtil.serialize(value));
		redisTemplate.expire(namespace, timeout, timeUnit);
	}
	
	@Override
	public void put(String key, Object value) {
		put(CACHE_NAMESPACE, key, value);
	}

	public <T> T get(String namespace, String key) {
		if(StringUtils.isEmpty(namespace)) {
			namespace = CACHE_NAMESPACE;
		}
		Object o = mapOps.get(namespace, key);
		if(o==null) {
			return null;
		}
		String value = mapOps.get(namespace, key)+"";
		return (T) SerializeUtil.unserialize(value);
	}

	@Override
	public <T> T get(String key) {
		return get(CACHE_NAMESPACE, key);
	}

	@Override
	public void remove(String namespace, String key) {
		mapOps.delete(namespace, key);
	}

	@Override
	public void remove(String key) {
		remove(CACHE_NAMESPACE, key);
	}

	@Override
	public void clearNamespace(String namespace) {
		removeCache(namespace);
		redisTemplate.delete(namespace);
	}

	@Override
	public void clear() {
		redisTemplate.getConnectionFactory().getConnection().flushAll();
	}

	

}
