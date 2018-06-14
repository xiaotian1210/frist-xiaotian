package com.shareworx.ezfm.app.quality.service;
import java.util.List;
import java.util.Map;

/**
 * 获取基础数据 service
 */

public interface AppProblemTypeService {
	String ID ="appProblemTypeService";
	List<Map<String,Object>> getAllProblemType(String crop,String lt) throws Exception;
}
