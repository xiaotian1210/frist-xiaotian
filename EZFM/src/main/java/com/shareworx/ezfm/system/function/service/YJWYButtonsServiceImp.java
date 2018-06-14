package com.shareworx.ezfm.system.function.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.utils.UserUtil;

@Service(YJWYButtonsService.ID)
public class YJWYButtonsServiceImp implements YJWYButtonsService{
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	/**
	 * 查询登录用户有权限的按钮
	 * @param funcId
	 * @return
	 */
	@Override
	public String queryRoleButtonsForString(String funcId) {
		YJWYUserModel loginUser = UserUtil.getCurrentUser();
		String sql = "select IFNULL(GROUP_CONCAT(code_ SEPARATOR ','),'') 'buttons' from yjwy_pub_function where id_ in (select pk_func_ from yjwy_pub_role_func where pk_crop_='"+loginUser.getPk_crop()+"' and  pk_role_ in (select pk_role_ from yjwy_pub_role_user where  pk_crop_='"+loginUser.getPk_crop()+"' and pk_user_  = '"+loginUser.getPk_user()+"') ) and pid_ = '"+funcId+"' and type_ = 'button' order by order_ ";
		JdbcTemplate read = dao.getReadTemplate();
		Map<String,Object> map = read.queryForMap(sql);
		return map.get("buttons").toString();
	}
	/**
	 * 查询登录用户没有权限的按钮
	 * @param funcId
	 * @return
	 */
	@Override
	public String queryNotRoleButtonsForString(String funcId) {
		YJWYUserModel loginUser = UserUtil.getCurrentUser();
		String sql = "select IFNULL(GROUP_CONCAT(code_ SEPARATOR ','),'') 'buttons' from yjwy_pub_function where id_ not in (select pk_func_ from yjwy_pub_role_func where pk_crop_='"+loginUser.getPk_crop()+"' and  pk_role_ in (select pk_role_ from yjwy_pub_role_user where  pk_crop_='"+loginUser.getPk_crop()+"' and pk_user_  = '"+loginUser.getPk_user()+"') ) and pid_ = '"+funcId+"' and type_ = 'button' order by order_ ";
		JdbcTemplate read = dao.getReadTemplate();
		Map<String,Object> map = read.queryForMap(sql);
		return map.get("buttons").toString();
	}
	/**
	 * 查询登录用户所有的功能按钮
	 * @param funcId
	 * @return
	 */
	@Override
	public String queryAllButtonsForString(String funcId) {
		String sql = "select IFNULL(GROUP_CONCAT(code_ SEPARATOR ','),'') 'buttons' from yjwy_pub_function where pid_ = '"+funcId+"' and type_ = 'button' order by order_ ";
		JdbcTemplate read = dao.getReadTemplate();
		Map<String,Object> map = read.queryForMap(sql);
		return map.get("buttons").toString();
	}
}
