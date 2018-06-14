package com.shareworx.ezfm.device.list.dao;

import java.util.List;

import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.list.model.YJWYListModel;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;

/**
 * 设备列表持久化操作接口
 * @author jin.li
 *
 */
public interface YJWYListDao {
	String ID = "yJWYListDao";
	
	/**
	 * 查询数据集合
	 * @param params
	 * @return
	 */
	List<YJWYListModel> queryList(String sql);
	
	/**
	 * 查询总数
	 * @param sql
	 * @return
	 */
	Long queryCount(String sql);

	/**
	 * 删除设备
	 * @return
	 */
	int deleteByCondition(String sql);
	int deleteByCondition(Delete delete);
	/**
	 * 查询区域ID
	 * @return
	 */
	String queryPk_area(Query query);
	/**
	 * 查询FM项目
	 * @return
	 */
	String querySite_id(String sql);
	String querySite_id(Query query);
	/**
	 * 查询项目ID
	 * @return
	 */
	String queryPk_project(String sql);
	String queryPk_project(Query query);
	/**
	 * 查询房间
	 * @return
	 */
	YJWYRoomModel queryRoom(String sql);
	YJWYRoomModel queryRoom(Query query);
	
}
