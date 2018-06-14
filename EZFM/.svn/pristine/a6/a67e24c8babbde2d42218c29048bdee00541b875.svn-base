package com.shareworx.ezfm.performance.sign.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.ezfm.performance.sign.dao.YJWYSignDao;
import com.shareworx.ezfm.performance.sign.model.YJWYSignModel;
import com.shareworx.ezfm.performance.sign.vo.YJWYSignModelVo;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * YJWYSignModel领域操作实现
 * @author lingwei.li
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYSignDomainService.ID)
public class YJWYSignDomainServiceImpl implements YJWYSignDomainService {

	public final static String ID = "yJWYSignDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(YJWYSignDao.ID)
	private YJWYSignDao signDao;

	public void setYJWYSignDao(YJWYSignDao signDao) {
		this.signDao = signDao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignDomainService#save(com.shareworx.ezfm.performance.sign.model.YJWYSignModel[])
	 */
	@Override
	public List<YJWYSignModel> save(YJWYSignModel... models) throws ShareworxServiceException {
		return service.save(YJWYSignModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignDomainService#update(com.shareworx.ezfm.performance.sign.model.YJWYSignModel[])
	 */
	@Override
	public List<YJWYSignModel> update(YJWYSignModel... models) throws ShareworxServiceException {
		return service.update(YJWYSignModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignDomainService#update(java.util.List, com.shareworx.ezfm.performance.sign.model.YJWYSignModel[])
	 */
	@Override
	public List<YJWYSignModel> update(List<String> editFields, YJWYSignModel... models) throws ShareworxServiceException {
		return service.update(YJWYSignModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignDomainService#delete(com.shareworx.ezfm.performance.sign.model.YJWYSignModel[])
	 */
	@Override
	public int delete(YJWYSignModel... models) throws ShareworxServiceException {
		return service.delete(YJWYSignModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYSignModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignDomainService#queryById(java.lang.String)
	 */
	@Override
	public YJWYSignModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYSignModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYSignModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYSignModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.performance.sign.service.YJWYSignDomainService#queryByExample(com.shareworx.ezfm.performance.sign.model.YJWYSignModel)
	 */
	@Override
	public List<YJWYSignModel> queryByExample(YJWYSignModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYSignModel.META_ID, model);
	}

	/**
	 * sql 联合查询
	 */
	@Override
	public List<Map<String, Object>> queryMap(YJWYSignModelVo signModelVo) {
		return signDao.queryMap(getListSql(signModelVo));
	}

	/**
	 * 获取签到条数
	 */
	@Override
	public Long queryTaskCount(YJWYSignModelVo signModelVo) {
		return signDao.queryTaskCount(getCountSql(signModelVo));
	}
	
	/**
	 * 
	 * @param leaveModelVo
	 * @return
	 */
	public static String getListSql(YJWYSignModelVo signModelVo) {
		String whr = "WHERE 1 = 1 ";
		//区域
		if (!StringUtils.isEmpty(signModelVo.getArea_id())) {
			whr = whr + "and yps.fk_region_ = '" + signModelVo.getArea_id() + "' ";
		} 
		//项目id
		if (!StringUtils.isEmpty(signModelVo.getProject_id())) {
			whr = whr + "and yps.fk_project_ = '" + signModelVo.getProject_id() + "' ";
		}
		//提交人
		if (!StringUtils.isEmpty(signModelVo.getUser_id())) {
			whr = whr + "and yps.sign_person_ = '" + signModelVo.getUser_id() + "' ";
		}
		//状态
		if (!StringUtils.isEmpty(signModelVo.getState())) {
			whr = whr + "and yps.sign_action_ = '" + signModelVo.getState() + "' ";
		}
		//岗位
		if (!StringUtils.isEmpty(signModelVo.getStation_id())) {
			whr = whr + "and yps.fk_job_ = '" + signModelVo.getStation_id() + "' ";
		}
		//部门
		if (!StringUtils.isEmpty(signModelVo.getFk_department())) {
			whr = whr + "and yps.fk_department_ = '" + signModelVo.getFk_department() + "' ";
		}
		
		//提交时间
		if (!StringUtils.isEmpty(signModelVo.getStart_date())
				&& !StringUtils.isEmpty(signModelVo.getEnd_date())) {
			if (!StringUtils.isEmpty(signModelVo.getStart_time())
					&& !StringUtils.isEmpty(signModelVo.getEnd_time())) {
				whr = whr + "and yps.sign_commitTime_ >= '" + signModelVo.getStart_date()+" "+signModelVo.getStart_time()+ "' "
						+ "and yps.sign_commitTime_ <= '" + signModelVo.getEnd_date()+" "+signModelVo.getEnd_time()+ "' ";
			}else{
				whr = whr + "and yps.sign_commitTime_ >= '" + signModelVo.getStart_date()+" 00:00:00' "
						+ "and yps.sign_commitTime_ <= '" + signModelVo.getEnd_date()+" 00:00:00' ";
			}
			
		}
		whr = whr + "and yps.pk_crop_ = '" + UserUtil.getCurrentUser().getPk_crop()+"' ";
		
		String sql = "select "
				+ "ypp.project_name_ AS project_name,"
				+ "ypu.user_name_ AS user_name,"
				+ "yps.sign_person_ AS sign_person,"
				+ "yps.sign_commitTime_ AS sign_commitTime,"
				+ "yps.sign_action_ AS sign_action,"
				+ "yps.fk_project_ AS fk_project,"
				+ "yps.fk_job_ AS fk_job,"
				+ "yps.fk_department_ AS fk_department,"
				+ "yps.sign_location_ AS sign_location,"
				+ "yps.pk_sign_ AS pk_sign,"
				+ "yps.fk_region_ AS fk_region,"
				+ "yps.sign_person_ AS sign_person,"
				+ "yps.sign_project_ AS sign_project "
				+ "FROM yjwy_performance_sign yps "
				+ "LEFT JOIN yjwy_pub_project ypp "
				+ "ON yps.fk_project_ = ypp.pk_project_ "
				+ "LEFT JOIN yjwy_pub_user ypu "
				+ "ON yps.sign_person_ = ypu.pk_user_ "
				+ whr
				+ "limit "+signModelVo.getStart()+","+signModelVo.getLimit();
		return sql;
		
	}
	
	/**
	 * 
	 * @param leaveModelVo
	 * @return
	 */
	public static String getCountSql(YJWYSignModelVo signModelVo) {
		String whr = "WHERE 1 = 1 ";
		//区域
		if (!StringUtils.isEmpty(signModelVo.getArea_id())) {
			whr = whr + "and yps.fk_region_ = '" + signModelVo.getArea_id() + "' ";
		} 
		//项目id
		if (!StringUtils.isEmpty(signModelVo.getProject_id())) {
			whr = whr + "and yps.fk_project_ = '" + signModelVo.getProject_id() + "' ";
		}
		//提交人
		if (!StringUtils.isEmpty(signModelVo.getUser_id())) {
			whr = whr + "and yps.sign_person_ = '" + signModelVo.getUser_id() + "' ";
		}
		//状态
		if (!StringUtils.isEmpty(signModelVo.getState())) {
			whr = whr + "and yps.sign_action_ = '" + signModelVo.getState() + "' ";
		}
		//岗位
		if (!StringUtils.isEmpty(signModelVo.getStation_id())) {
			whr = whr + "and yps.fk_job_ = '" + signModelVo.getStation_id() + "' ";
		}
		//部门
		if (!StringUtils.isEmpty(signModelVo.getFk_department())) {
			whr = whr + "and yps.fk_department_ = '" + signModelVo.getFk_department() + "' ";
		}
		
		//部门
		if (!StringUtils.isEmpty(signModelVo.getStart_date())
				&& !StringUtils.isEmpty(signModelVo.getEnd_date())) {
			if (!StringUtils.isEmpty(signModelVo.getStart_time())
					&& !StringUtils.isEmpty(signModelVo.getEnd_time())) {
				whr = whr + "and yps.sign_commitTime_ >= '" + signModelVo.getStart_date()+" "+signModelVo.getStart_time()+ "' "
						+ "and yps.sign_commitTime_ <= '" + signModelVo.getEnd_date()+" "+signModelVo.getEnd_time()+ "' ";
			}else{
				whr = whr + "and yps.sign_commitTime_ >= '" + signModelVo.getStart_date()+" 00:00:00' "
						+ "and yps.sign_commitTime_ <= '" + signModelVo.getEnd_date()+" 00:00:00' ";
			}
			
		}
		
		whr = whr + "and yps.pk_crop_ = '" + UserUtil.getCurrentUser().getPk_crop()+"' ";
		
		String sql = "select count(*) FROM yjwy_performance_sign yps "+ whr;
		
		return sql;
		
	}

}
