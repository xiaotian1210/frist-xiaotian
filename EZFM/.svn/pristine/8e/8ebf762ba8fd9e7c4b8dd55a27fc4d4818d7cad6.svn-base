package com.shareworx.platform.cache;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 缓存管理器
 * @author zhentong.jia
 *
 */
public interface IDmsCacheManager {

	/**
	 * 获取全部缓存空间
	 * @return
	 */
	List<String> getNamespaces();
	
	/**
	 * 获取空间内缓存键值
	 * @param namespace
	 * @return
	 */
	List<String> getKeys(String namespace);
	
	/**
	 * 创建缓存空间
	 * @param namespace
	 */
	void createCache(String namespace);
	
	/**
	 * 删除缓存空间
	 * @param namespace
	 */
	void removeCache(String namespace);
	
	/**
	 * 在指定缓存空间内加入或更新缓存信息
	 * @param namespace
	 * @param key
	 * @param value
	 */
	void put(String namespace, String key, Object value);
	
	/**
	 * 在指定缓存空间内加入或更新缓存信息
	 * 增加缓存过期时间，但是过期时间只能针对namespace设置
	 * @param namespace
	 * @param key
	 * @param value
	 */
	void put(String namespace, String key, Object value, long timeout, TimeUnit timeUnit);
	
	/**
	 * 在默认缓存空间内加入和更新缓存信息
	 * @param key
	 * @param value
	 * @deprecated {@link IDmsCacheManager#put(String, String, Object)}
	 */
	void put(String key, Object value);
	
	/**
	 * 获取缓存信息
	 * @param namespace
	 * @param key
	 */
	<T> T get(String namespace, String key);
	
	/**
	 * 在默认缓存空间内获取缓存信息
	 * 如果信息不存在这遍历其他缓存空间
	 * 如果其他缓存空间存在多个相同key的缓存信息，将返回默认第一个找到的信息，这种情况建议使用 {@link IDmsCacheManager#get(String, String)}
	 * @param key
	 * @deprecated {@link IDmsCacheManager#get(String, String)}
	 * @return
	 */
	<T> T get(String key);
	
	/**
	 * 在指定空间内删除缓存信息
	 * @param namespace
	 * @param key
	 */
	void remove(String namespace, String key);
	
	/**
	 * 在所有缓存空间删除缓存信息
	 * @deprecated {@link IDmsCacheManager#remove(String, String)}
	 * @param key
	 */
	void remove(String key);
	
	/**
	 * 清空缓存空间内的所有信息
	 * @param namespace
	 */
	void clearNamespace(String namespace);
	
	/**
	 * 清空所有缓存空间内全部信息
	 */
	void clear();
}
