package com.shareworx.ezfm.performance.leave.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.performance.leave.dao.YJWYLeaveDao;
import com.shareworx.ezfm.performance.leave.model.YJWYLeaveModel;
import com.shareworx.ezfm.performance.leave.vo.YJWYLeaveModelVo;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * YJWYLeaveModel领域操作实现
 * 
 * @author ying.chen
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYLeaveDomainService.ID)
public class YJWYLeaveDomainServiceImpl implements YJWYLeaveDomainService {

	public final static String ID = "yJWYLeaveDomainService";

	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	@Autowired
	@Qualifier(YJWYLeaveBusinessService.ID)
	private YJWYLeaveBusinessService leaveService;

	public void setLeaveService(YJWYLeaveBusinessService leaveService) {
		this.leaveService = leaveService;
	}

	public void setService(BaseDomainService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(YJWYLeaveDao.ID)
	private YJWYLeaveDao leaveDao;

	public void setYJWYLeaveDao(YJWYLeaveDao leaveDao) {
		this.leaveDao = leaveDao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.Result.leave.service.YJWYLeaveDomainService#save(com.
	 * shareworx.yjwy.Result.leave.model.YJWYLeaveModel[])
	 */
	@Override
	public List<YJWYLeaveModel> save(YJWYLeaveModel... models) throws ShareworxServiceException {
		return service.save(YJWYLeaveModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.Result.leave.service.YJWYLeaveDomainService#update(com
	 * .shareworx.yjwy.Result.leave.model.YJWYLeaveModel[])
	 */
	@Override
	public List<YJWYLeaveModel> update(YJWYLeaveModel... models) throws ShareworxServiceException {
		return service.update(YJWYLeaveModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.Result.leave.service.YJWYLeaveDomainService#update(
	 * java.util.List, com.shareworx.ezfm.Result.leave.model.YJWYLeaveModel[])
	 */
	@Override
	public List<YJWYLeaveModel> update(List<String> editFields, YJWYLeaveModel... models)
			throws ShareworxServiceException {
		return service.update(YJWYLeaveModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.Result.leave.service.YJWYLeaveDomainService#delete(com
	 * .shareworx.yjwy.Result.leave.model.YJWYLeaveModel[])
	 */
	@Override
	public int delete(YJWYLeaveModel... models) throws ShareworxServiceException {
		return service.delete(YJWYLeaveModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.Result.leave.service.YJWYLeaveDomainService#
	 * deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(YJWYLeaveModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.Result.leave.service.YJWYLeaveDomainService#
	 * deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.ezfm.Result.leave.service.YJWYLeaveDomainService#queryById(
	 * java.lang.String)
	 */
	@Override
	public YJWYLeaveModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(YJWYLeaveModel.META_ID, id);
	}

	/**
	 * sql 联合查询
	 */
	@Override
	public List<Map<String, Object>> queryMap(YJWYLeaveModelVo leaveModelVo) {
		return leaveDao.queryMap(getListSql(leaveModelVo,true));
	}

	/**
	 * 获取 休假备案的条数
	 */
	@Override
	public Long queryTaskCount(YJWYLeaveModelVo leaveModelVo) {
		return leaveDao.queryTaskCount("select count(*) from ( "+getListSql(leaveModelVo,false)+" ) ypl");
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.Result.leave.service.YJWYLeaveDomainService#
	 * queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public YJWYLeaveModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.Result.leave.service.YJWYLeaveDomainService#
	 * queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<YJWYLeaveModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.shareworx.ezfm.Result.leave.service.YJWYLeaveDomainService#
	 * queryByExample(com.shareworx.ezfm.Result.leave.model.YJWYLeaveModel)
	 */
	@Override
	public List<YJWYLeaveModel> queryByExample(YJWYLeaveModel model) throws ShareworxServiceException {
		return service.queryByExample(YJWYLeaveModel.META_ID, model);
	}

	@Override
	// 判断用户是否在休假 返回false不在休假 返回true在休假
	public boolean queryLeave(String id) throws ShareworxServiceException {
		Query query = Query.from(YJWYLeaveModel.META_ID);
		query.and(Condition.create("lv_submitter_", id), Condition.create("lv_operation_", "1"));
		YJWYLeaveModel[] models = leaveService.query(query);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		Date dt = null;
		Date dts = null;
		Date dte = null;
		try {
			for (YJWYLeaveModel l : models) {
				dt = df.parse(df.format(new Date()));
				if (!StringUtils.isEmpty(l.getLv_startTime())) {
					dts = df.parse(l.getLv_startTime());
				}
				if (!StringUtils.isEmpty(l.getLv_endTime())) {
					dte = df.parse(l.getLv_endTime());
				}
				if (dt.getTime() >= dts.getTime() && dt.getTime() <= dte.getTime()) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取listSql
	 * 
	 * @param leaveModelVo
	 * @return
	 */
	public String getListSql(YJWYLeaveModelVo leaveModelVo,boolean flag) {
		String whr = " where 1 = 1 ";
		//通过当前登录用户获取项目ids
		YJWYUserModel userModel = UserUtil.getCurrentUser();
		Set<String> projectIdsSet = deviceService.queryProjectIdsByPkUser(userModel.getPk_user());
		if (projectIdsSet.size() > 0 ) {
			
			String projectIdsStr = "";
			Iterator<String> it = projectIdsSet.iterator();
			while(it.hasNext()){
				projectIdsStr = projectIdsStr + "'"+it.next()+"',";
			}
			projectIdsStr = projectIdsStr.substring(0, projectIdsStr.length()-1);
			
			whr = whr + " and ypl.fk_project_ in ("+projectIdsStr+") ";
		}
		
		if (!StringUtils.isEmpty(leaveModelVo.getArea_id())) {
			whr = whr + "and ypl.fk_region_ = '" + leaveModelVo.getArea_id() + "' ";
		} 
		if (!StringUtils.isEmpty(leaveModelVo.getProject_id())) {
			whr = whr + "and ypl.fk_project_ = '" + leaveModelVo.getProject_id() + "' ";
		} 
		if (!StringUtils.isEmpty(leaveModelVo.getUser_id())) {
			whr = whr + "and ypl.lv_submitter_ = '" + leaveModelVo.getUser_id() + "' ";
		}
		if (!StringUtils.isEmpty(leaveModelVo.getState())) {
			whr = whr + "and ypl.lv_operation_ = '" + leaveModelVo.getState() + "' ";
		}
		whr = whr + "and yps.pk_crop_ = '" + UserUtil.getCurrentUser().getPk_crop()+"' ";
		
		String sql = "select "
				+ "ypl.pk_leave_ as pk_leave, " 
				+ "ypu.user_name_ AS lv_submitter_name," 
				+ "ypp.project_name_ AS fk_project_name,"
				+ "ypd.dict_name_ AS fk_type_name," 
				+ "ypl.fk_project_ as fk_project," 
				+ "ypl.fk_region_ as fk_region," 
				+ "ypl.lv_submitter_ as lv_submitter,"
				+ "ypl.lv_commitTime_ as lv_commitTime," 
				+ "ypl.lv_confirmTime_ as lv_confirmTime," 
				+ "ypl.lv_startTime_ as lv_startTime," 
				+ "ypl.lv_endTime_ as lv_endTime,"
				+ "ypl.lv_days_ as lv_days," 
				+ "ypl.fk_type_ as fk_type," 
				+ "ypl.lv_details_ as lv_details," 
				+ "ypl.lv_operation_ as lv_operation, "
				+ "ypl.fk_job_ AS fk_job, "
				+ "ypl.lv_approval_ as lv_approval,"
				+ "ypl.lv_approvalDesc_ as lv_approvalDesc,"
				+ "ypl.create_user_ as create_user,"
				+ "ypl.create_time_ as create_time,"
				+ "ypl.update_user_ as update_user,"
				+ "ypl.update_time_ as update_time,"
				+ "ypl.fk_people_ as fk_people,"
				+ "ypl.pk_crop_ as pk_crop,"
				+ "yps.station_name_ AS fk_job_name,"
				+ "ysf.file_id_ as file_id " 
				+ "from "
				+ "yjwy_performance_leave ypl " 
				+ "LEFT JOIN yjwy_pub_user ypu " 
				+ "ON ypl.lv_submitter_ = ypu.pk_user_ "
				+ "LEFT JOIN yjwy_pub_project ypp " 
				+ "ON ypl.fk_project_ = ypp.pk_project_ "
				+ "LEFT JOIN yjwy_pub_dict ypd " 
				+ "ON ypl.fk_type_ = ypd.dict_code_ " 
				+ "LEFT JOIN yjwy_pub_station yps " 
				+ "ON ypl.fk_job_ = yps.pk_station_ "
				+ "left join yjwy_system_file ysf "
				+ "on ysf.record_id_ = ypl.pk_leave_ "
				+ "and ysf.table_name_ ='yjwy_performance_leave'" 
				+ whr ;
		if (flag) {
			sql += "limit "+leaveModelVo.getStart() + "," + leaveModelVo.getLimit();
		}
		return sql;

	}
	
	/**
	 * 获取countSql
	 * 
	 * @param leaveModelVo
	 * @return
	 */
	public static String getCountSql(YJWYLeaveModelVo leaveModelVo) {
		String whr = "WHERE 1 = 1 ";
		if (!StringUtils.isEmpty(leaveModelVo.getArea_id())) {
			whr = whr + "and ypl.fk_region_ = '" + leaveModelVo.getArea_id() + "' ";
		} else if (!StringUtils.isEmpty(leaveModelVo.getProject_id())) {
			whr = whr + "and ypl.fk_project_ = '" + leaveModelVo.getProject_id() + "' ";
		} else if (!StringUtils.isEmpty(leaveModelVo.getUser_id())) {
			whr = whr + "and ypl.lv_submitter_ = '" + leaveModelVo.getUser_id() + "' ";
		} else if (!StringUtils.isEmpty(leaveModelVo.getState())) {
			whr = whr + "and ypl.lv_operation_ = '" + leaveModelVo.getState() + "' ";
		}

		String sql = "select count(*) from yjwy_performance_leave ypl "+ whr;
		
		return sql;

	}

}
