package com.shareworx.ezfm.app.basedata.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.platform.persist.dao.ObjectPersistDao;

@Service(AppProjectService.ID)
public class AppProjectServiceImpl implements AppProjectService{

	final static Logger logger = Logger.getLogger(AppProjectServiceImpl.class);
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<Map<String, Object>> getAllProject(String crop) throws Exception {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		
		logger.info("AppProjectServiceImpl/getAllProject");
		
		String SQL = " select pro.pk_project_ as pk_project, "
				+ "pro.project_name_ as project_name, "
				+ "pro.project_code_ as project_code, "
				+ "pro.pk_area_ as pk_area "
				+ "from yjwy_pub_project pro "
				+ "where pk_crop_='"+crop+"'";
		
		List<Map<String,Object>> projectList = readJdbcTemplate.queryForList(SQL);
		
		logger.info("result:success");
		
		return projectList;
	}

}
