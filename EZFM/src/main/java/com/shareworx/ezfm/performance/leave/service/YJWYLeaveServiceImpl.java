package com.shareworx.ezfm.performance.leave.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.platform.persist.dao.ObjectPersistDao;

@Service(YJWYLeaveService.ID)
public class YJWYLeaveServiceImpl implements YJWYLeaveService {

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean isLeaveByUserId(String pk_user) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		String date=sdf.format(new Date());
		String sql=" select * from yjwy_performance_leave "
				+ " WHERE lv_submitter_='"+pk_user+"'"
				+ " and lv_operation_='1' "
				+ " and lv_startTime_<='"+date+"'"
				+ " and lv_endTime_ > '"+date+"' ";
		
		JdbcTemplate read = dao.getReadTemplate();
		List<Map<String,Object>> objList = read.queryForList(sql);
		if(objList.size()==0){
			return false;
		}
		return true;
	}

}
