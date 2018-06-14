package com.shareworx.platform.metadata;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.shareworx.platform.cache.IDmsCacheManager;
import com.shareworx.platform.exception.ShareworxRuntimeException;
import com.shareworx.platform.metadata.service.MetadataDefineService;
import com.shareworx.platform.metadata.service.MetadataPersistService;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 元数据操作工厂
 * @author zhentong.jia
 *
 */
@Component("metadataFactory")
@ConfigurationProperties(prefix="system.metadata")
public class MetadataFactory {

	public final static String ID = "metadataFactory";
	public final static String SPACE_META_CLASS = "space_meta_class";
	public final static String AUTO_NONE = "none";
	public final static String AUTO_CREATE = "create";
	public final static String AUTO_UPDATE = "update";
	
	Logger log = Logger.getLogger(MetadataFactory.class);
	
	/**
	 * 初始化方式
	 * {@link #AUTO_NONE}
	 * {@link #AUTO_CREATE}
	 * {@link #AUTO_UPDATE}
	 */
	private String autoPersist = "none";
	
	/**
	 * 重新发布元数据
	 */
	private boolean rePublish = false;

	private MetadataResolver metadataResolver;
	
	@Autowired(required=false)
	@Qualifier("cacheManager")
	private IDmsCacheManager cacheManager;
	
	@Autowired(required=false)
	private MetadataPersistService metadataPersistService;

	@Autowired(required=false)
	private MetadataDefineService metadataDefineService;
	
	private String[] packagesToScan;
	
	public MetadataFactory() {
		metadataResolver = new MetadataResolver();
	}

	public String[] getPackagesToScan() {
		return packagesToScan;
	}

	public void setPackagesToScan(String[] packagesToScan) {
		this.packagesToScan = packagesToScan;
	}
	
	public void setMetadataResolver(MetadataResolver metadataResolver) {
		this.metadataResolver = metadataResolver;
	}

	public void setCacheManager(IDmsCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public void setMetadataPersistService(MetadataPersistService metadataPersistService) {
		this.metadataPersistService = metadataPersistService;
	}
	
	public void setMetadataDefineService(MetadataDefineService metadataDefineService) {
		this.metadataDefineService = metadataDefineService;
	}

	public String getAutoPersist() {
		return autoPersist;
	}

	public void setAutoPersist(String autoPersist) {
		this.autoPersist = autoPersist;
	}

	public boolean isRePublish() {
		return rePublish;
	}

	public void setRePublish(boolean rePublish) {
		this.rePublish = rePublish;
	}

	/**
	 * 初始化元数据类，将元数据放入缓存
	 */
	@PostConstruct
	public void init(){
		List<MetaClass> metaClassList = null;
		if(StringUtils.isEmpty(getAutoPersist()) || getAutoPersist().equalsIgnoreCase(AUTO_NONE)){
			metaClassList = metadataPersistService.findAll();
		}else{
			try{
				metaClassList = reLoadMetadatas();
				doAutoPersiste(metaClassList);
				doRePublishMetadata(metaClassList);
			}catch(Exception e){
				log.error(e.getMessage());
				throw e;
			}
		}
		reloadMetadataCache(metaClassList);
		log.info("Metadata perload success and the total number is: " + metaClassList.size());
	}
	
	/**
	 * 从元数据配置文件加载元数据
	 * @return
	 */
	public List<MetaClass> reLoadMetadatas() {
		List<MetaClass> metaClassList = new ArrayList<>();
		List<Resource> resList = metadataResolver.doScanMetadatas(packagesToScan);
		if(!ArrayUtils.isEmpty(resList)){
			for(Resource res: resList) {
				log.info(res.getFilename());
				List<MetaClass> list = metadataResolver.converMetaClass(res);
				if(!ArrayUtils.isEmpty(list)){
					metaClassList.addAll(list);
				}
			}
		}
		return metaClassList;
	}
	
	/**
	 * 初始化元数据缓存
	 */
	public void reloadMetadataCache(List<MetaClass> list) {
		if(ArrayUtils.isEmpty(list)){
			list = reLoadMetadatas();
		}
		if(!ArrayUtils.isEmpty(list)){
			for(MetaClass metaClass: list){
				cacheManager.put(SPACE_META_CLASS, metaClass.getId(), metaClass);
			}
		}
	}
	
	private boolean isDefaultMetaClass(String metaName) {
		if(!StringUtils.isEmpty(metaName))
			switch (metaName) {
			case MetadataContents.METADATA_CLASS:
			case MetadataContents.METADATA_FIELD:
			case MetadataContents.METADATA_CLASSRELATION:
			case MetadataContents.METADATA_CODEDETAIL:
			case MetadataContents.METADATA_CODE:
			case MetadataContents.METADATA_CODESERIAL:
			case MetadataContents.METADATA_INDEX:
				return true;
			}
		return false;
	}
	
	private void doRePublishMetadata(List<MetaClass> metaClassList) {
		if(!isRePublish()) {
			return;
		}
		if(metadataDefineService == null || metadataPersistService == null){
			log.warn("Beans have to be initialized：com.shareworx.platform.metadata.service.MetadataDefineService and com.shareworx.platform.metadata.service.MetadataPersistService");
			return;
		}
		if(ArrayUtils.isEmpty(metaClassList)){
			log.warn("There is not any metaclass has been fetched.");
			return;
		}
		for(MetaClass metaClass: metaClassList){
//			if(isDefaultMetaClass(metaClass.getId())){
//				continue;
//			}
			log.info(String.format("Publishing Metadata：[ %s ]", metaClass.getId()));
			metadataPersistService.deleteClass(metaClass);
			metadataPersistService.saveClass(metaClass);
		}
	}
	
	/**
	 * 初始化表结构
	 */
	private void doAutoPersiste(List<MetaClass> metaClassList) {
		if(StringUtils.isEmpty(getAutoPersist()) || getAutoPersist().equalsIgnoreCase("none")){
			return;
		}
		if(!getAutoPersist().equalsIgnoreCase("update") && !getAutoPersist().equalsIgnoreCase("create")){
			throw new ShareworxRuntimeException("Unsupport autoPersist argument：" + getAutoPersist() + ". system only support [none, create, update]");
		}
		if(metadataDefineService == null || metadataPersistService == null){
			log.warn("Beans have to be initialized：com.shareworx.platform.metadata.service.MetadataDefineService and com.shareworx.platform.metadata.service.MetadataPersistService");
			return;
		}
		if(ArrayUtils.isEmpty(metaClassList)){
			log.warn("There is not any metaclass has been fetched.");
			return;
		}
		for(MetaClass metaClass: metaClassList){
			if(metaClass.getType() != MetadataContents.META_TYPE_ENTITY) {
				continue;
			}
			if(isDefaultMetaClass(metaClass.getId())){
				if(getAutoPersist().equalsIgnoreCase("create")){
					log.info(String.format("Creating Metadata: %s", metaClass.getId()));
					metadataDefineService.createTableStructByMetaClass(metaClass);
				}
			}else{
if("pub_version".equals(metaClass.getId())) {
	System.out.println("===");
}
				log.info(String.format("Updating Metadata: %s", metaClass.getId()));
				metadataDefineService.updateTableStructByMetaClass(metaClass);
			}
		}
		
		if(getAutoPersist().equalsIgnoreCase("create")){
			metadataPersistService.initCodeRule();
		}
	}

	/**
	 * 创建元数据类
	 * @param metaClass
	 * @return
	 */
	public boolean createMetaClass(MetaClass metaClass){
		MetaClass existClass = getMetaClassByName(metaClass.getName());
		if(existClass != null){
			throw new ShareworxRuntimeException("Metadata existed：" + metaClass.getId());
		}
		metadataDefineService.createMetaClass(metaClass);
		metadataPersistService.saveClass(metaClass);
		cacheManager.put(SPACE_META_CLASS, metaClass.getId(), metaClass);
		return true;
	}
	
	/**
	 * 根据名称获取元数据类
	 * @param metaName
	 * @return
	 */
	public MetaClass getMetaClassByName(String metaName) {
		MetaClass metaClass = cacheManager.get(SPACE_META_CLASS, metaName);
		if(metaClass == null){
			metaClass = metadataPersistService.findClassById(metaName);
			if(metaClass != null){
				cacheManager.put(SPACE_META_CLASS, metaName, metaClass);
				return metaClass;
			}
		}
		return metaClass;
	}
}
