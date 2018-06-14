package com.shareworx.ezfm.app.quality.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.platform.persist.dao.ObjectPersistDao;

@Service(AppProblemTypeService.ID)
public class AppProblemTypeServiceImpl implements AppProblemTypeService{

	final static Logger logger = Logger.getLogger(AppProblemTypeServiceImpl.class);
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	
	@Override
	public List<Map<String,Object>> getAllProblemType(String crop,String lt){
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		logger.info("AppProblemTypeService/getAllProblemType");
		
		String SQL = " select pk_problem, "
				+ "problem_name, problem_rectify_days "
				+ "from yjwy_quality_problemtype "
				+ "where pk_crop = '"+crop+"'";
		List<Map<String,Object>> proTypeList = new ArrayList<>();
		
		if("0".equals(lt)){
			proTypeList = readJdbcTemplate.queryForList(SQL);
		}else{
			String updateSql =SQL+ " and update_time > '"+lt+"'";
			proTypeList = readJdbcTemplate.queryForList(updateSql);
			if(proTypeList.size()>0){
				proTypeList = readJdbcTemplate.queryForList(SQL);
			}
		}
		return proTypeList;
	}

}
