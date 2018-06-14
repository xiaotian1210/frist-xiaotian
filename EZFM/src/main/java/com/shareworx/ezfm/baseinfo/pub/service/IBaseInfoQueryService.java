package com.shareworx.ezfm.baseinfo.pub.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.org.model.DefaultOrgModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;

public interface IBaseInfoQueryService {
	public static final String ID = "baseInfoQueryService";
	/**
	 * 查询字典MAP
	 * @param code 字典编码
	 * @param sate 1代表父CODE 0代表CODE
	 * @return
	 */
	public Map<String,String> queryDictionaryForMap(String code,int state);
	/**
	 * 查询字典MAP
	 * @param code 字典编码
	 * @param sate 1代表父CODE 0代表CODE
	 * @return
	 */
	public Map<String,String> queryDictionaryNameToCodeForMap(String code,int state);
	
	/**
	 * 查询字典List
	 * @param code 字典编码
	 * @param sate 1代表父CODE 0代表CODE
	 * @return
	 */
	public YJWYDictModel[] queryDictionaryForArray(String code,int state);
	
	/**
	 * @author zhenwei.shi 区域字典查询操作 供前台下拉框使用
	 * @return
	 */
	YJWYAreaModel[] queryForArea();
	
	/**
	 * @author zhenwei.shi 通过区域ID查询项目字典查询操作 供前台下拉框使用
	 * @return
	 */
	YJWYProjectModel[] queryForProject(String areaId);
	
	/**
	 * 组织结构暂未彻底明确，不建议使用
	 * @author zhenwei.shi 部门字典查询操作(组织结构) 注：所属部门从字典表取 供前台下拉框使用 
	 * @return
	 */
	DefaultOrgModel[] queryForOrgDept(String areaId, String projectId);
	
	/**
	 * @author zhenwei.shi 岗位字典查询操作 依据项目ID查询
	 * @return
	 */
	YJWYStationModel[] queryForStation(String projectId);
	
	/**
	 * @author zhenwei.shi 人员查询操作 供前台下拉框使用
	 * @return
	 */
	YJWYUserModel[] queryForUser(String areaId, String projectId, String stationId);
	
	/**
	 * 根据人员ID查询其所属岗位
	 * @param userId
	 * @return
	 */
	List<YJWYStationModel> queryStationsByUserId(String userId);
	
	/**
	 * 根据人员ID查询其所属项目
	 * @param userId
	 * @return
	 */
	List<YJWYProjectModel> queryProjectsByUserId(String userId);
	/**
	 * 查询城市
	 * @param pid
	 * @return
	 */
	List<Map<String, Object>> queryCityForList(int pid);
	
	/**
	 * 查询城市
	 * @param id
	 * @return
	 */
	Map<String, Object> queryCityForMap(int id);
	
	/**
	 * 通过用户Id 查询用户所属组织旗下的所有项目IDS
	 * @param userId
	 * @return
	 */
	Set<String> queryProjectIdsByUserId(String userId);
}
