package com.shareworx.ezfm.worktask.areauser.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.platform.persist.dao.ObjectPersistDao;

@Service(YJWYWorkTaskAreaUserNexusToolService.ID)
public class YJWYWorkTaskAreaUserNexusToolServiceImpl implements YJWYWorkTaskAreaUserNexusToolService {
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	@Override
	public boolean whetherDispatch(String user_id) {
		try {
			JdbcTemplate read = dao.getReadTemplate();
			String sql = "select count(pk_nexus_id) from yjwy_worktask_area_user_nexus where user_type = 3 and user_id='"+user_id+"'";
			int count = read.queryForObject(sql, Integer.class);
			if(count>0){
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}

