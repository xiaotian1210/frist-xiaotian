package com.shareworx.ezfm.worktask.details.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.StringUtils;

/**
 * YJWYWorkTaskDetailsModel领域操作实现
 * @author zhangjing.cheng
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskDetailsDomainService.ID)
public class YJWYWorkTaskDetailsDomainServiceImpl implements YJWYWorkTaskDetailsDomainService {

	public final static String ID = "yJWYWorkTaskDetailsDomainService";
	
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
	 * @see com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService#save(com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public List<YJWYWorkTaskDetailsModel> save(YJWYWorkTaskDetailsModel... models) throws ShareworxServiceException {
		return service.save(YJWYWorkTaskDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService#update(com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public List<YJWYWorkTaskDetailsModel> update(YJWYWorkTaskDetailsModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService#update(java.util.List, com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public List<YJWYWorkTaskDetailsModel> update(List<String> editFields, YJWYWorkTaskDetailsModel... models) throws ShareworxServiceException {
		return service.update(YJWYWorkTaskDetailsModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService#delete(com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel[])
	 */
	@Override
	public int delete(YJWYWorkTaskDetailsModel... models) throws ShareworxServiceException {
		return service.delete(YJWYWorkTaskDetailsModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYWorkTaskDetailsModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYWorkTaskDetailsModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYWorkTaskDetailsModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYWorkTaskDetailsModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYWorkTaskDetailsModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsDomainService#queryByExample(com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel)
	 */
	@Override
	public List<YJWYWorkTaskDetailsModel> queryByExample(YJWYWorkTaskDetailsModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYWorkTaskDetailsModel.META_ID, model);
	}

	/**
	 * 根据项目编号生成工单单号
	 * @param projectId 项目ID
	 * @return 编号：BX-+项目编号前四位+年月日+四位流水号
	 */
	public String getTaskCode(String projectId){
		String code;
		//最大流水号
		String maxSerial;
		//项目编码前四位
		String projectNumber;
		//年月日字符串
		String dateNumber =this.obtainTime("yyyyMMdd");
		//查询最大流水号
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select max(right(datails_code, 4)) from yjwy_worktask_details";
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
		String projectCode = projectService.queryById(projectId).getProject_code();
		projectNumber = projectCode.substring(0, 4);
		//编码拼接BX-项目编号前四位+年月日+流水号
		code ="BX-"+projectNumber+dateNumber+maxSerial;
		return code;
	}
	//公共方法，获取当前时间
	public String obtainTime(String format){
		SimpleDateFormat df = new SimpleDateFormat(format);//设置日期格式
		return df.format(new Date());
	}
}
