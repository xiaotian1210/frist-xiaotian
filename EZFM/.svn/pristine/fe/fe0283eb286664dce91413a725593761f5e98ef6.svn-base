package com.shareworx.ezfm.problem.details.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.StringUtils;

/**
 * YJWYProblemDetailsModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYProblemDetailsDomainService.ID)
public class YJWYProblemDetailsDomainServiceImpl implements YJWYProblemDetailsDomainService {

	public final static String ID = "yJWYProblemDetailsDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(YJWYProjectDomainService.ID)
	private YJWYProjectDomainService projectService;
	
	public void setProjectService(YJWYProjectDomainService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService#save(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public List<YJWYProblemDetailsModel> save(YJWYProblemDetailsModel... models) throws ShareworxServiceException {
		return service.save(YJWYProblemDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService#update(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public List<YJWYProblemDetailsModel> update(YJWYProblemDetailsModel... models) throws ShareworxServiceException {
		return service.update(YJWYProblemDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService#update(java.util.List, com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public List<YJWYProblemDetailsModel> update(List<String> editFields, YJWYProblemDetailsModel... models) throws ShareworxServiceException {
		return service.update(YJWYProblemDetailsModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService#delete(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel[])
	 */
	@Override
	public int delete(YJWYProblemDetailsModel... models) throws ShareworxServiceException {
		return service.delete(YJWYProblemDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYProblemDetailsModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYProblemDetailsModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYProblemDetailsModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYProblemDetailsModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYProblemDetailsModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsDomainService#queryByExample(com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel)
	 */
	@Override
	public List<YJWYProblemDetailsModel> queryByExample(YJWYProblemDetailsModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYProblemDetailsModel.META_ID, model);
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public String getCodeByProjectId(String projecId) throws ShareworxServiceException {
		String code;
		//最大流水号
		String maxSerial;
		//项目编码前四位
		String projectNumber;
		//年月日字符串
		String dateNumber =this.obtainTime();
		//通过岗位ID查询人员
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select max(right(details_number, 4)) from yjwy_problem_details";
		//获取最大流水号
		String serial = read.queryForObject(sql, java.lang.String.class);
		if (StringUtils.isEmpty(serial)||Integer.parseInt(serial)+1>9999) {
			maxSerial = "0000";
		}else{
			maxSerial = Integer.parseInt(serial)+1+"";
		}
		int len = maxSerial.length();
		String supplement = "";
		if (len<4) {
			for (int i = 0; i < 4-len; i++) {
				supplement +="0";
			}
			maxSerial =supplement+maxSerial;
		}
		YJWYProjectModel project = projectService.queryById(projecId);
		if (project==null) {
			return null;
		}
		String projectCode = projectService.queryById(projecId).getProject_code();
		projectNumber = projectCode.substring(0, 4);
		code ="BS-"+projectNumber+dateNumber+maxSerial;
		return code;
	}
	
	//公共方法，获取当前时间
	public String obtainTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		return df.format(new Date());
	}
}
