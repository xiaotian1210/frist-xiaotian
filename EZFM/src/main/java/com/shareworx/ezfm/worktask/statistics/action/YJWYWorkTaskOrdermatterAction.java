package com.shareworx.ezfm.worktask.statistics.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel;
import com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusDomainService;
import com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel;
import com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusBusinessService;
import com.shareworx.ezfm.worktask.statistics.service.YJWYWorkTaskStatisticsService;

/**
 * 抢派单情况对比表控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/worktask/ordermatter")
public class YJWYWorkTaskOrdermatterAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskOrdermatterAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/workTask/statistics/worktask_ordermatter_list";

	@Autowired
	@Qualifier(YJWYWorkTaskStatisticsService.ID)
	private YJWYWorkTaskStatisticsService workTaskStatisticsService;

	public void setWorkTaskStatisticsService(YJWYWorkTaskStatisticsService workTaskStatisticsService) {
		this.workTaskStatisticsService = workTaskStatisticsService;
	}

	@Autowired
	@Qualifier(YJWYWorkTaskAreaProjectNexusDomainService.ID)
	private YJWYWorkTaskAreaProjectNexusDomainService areaProjectNexusDomainService;

	public void setAreaProjectNexusDomainService(YJWYWorkTaskAreaProjectNexusDomainService areaProjectNexusDomainService) {
		this.areaProjectNexusDomainService = areaProjectNexusDomainService;
	}

	@Autowired
	@Qualifier(YJWYWorkTaskAreaUserNexusBusinessService.ID)
	private YJWYWorkTaskAreaUserNexusBusinessService areaUserNexusBusinessService;

	public void setAreaUserNexusBusinessService(YJWYWorkTaskAreaUserNexusBusinessService areaUserNexusBusinessService) {
		this.areaUserNexusBusinessService = areaUserNexusBusinessService;
	}

	@Autowired
	@Qualifier(YJWYUserBusinessService.ID)
	private YJWYUserBusinessService userBusinessService;

	public void setUserBusinessService(YJWYUserBusinessService userBusinessService) {
		this.userBusinessService = userBusinessService;
	}

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	/**
	 * 转向列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView listForward() {
		return new ModelAndView(PAGE_FORWARD);
	}

	/**
	 * 查询数据集合
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(ParamEntity params) {
		// 获取参数
		Integer page = params.getPage();
		Integer rows = params.getRows();
		String pk_area = params.getPk_area();
		String pk_project = params.getPk_project();
		String pk_user = params.getPk_user();
		String start_time = params.getStart_time();
		String end_time = params.getEnd_time();
		StringBuilder projectSql = new StringBuilder("");
		StringBuilder areaSql = new StringBuilder("");
		StringBuilder userSql = new StringBuilder("");
		StringBuilder dateSql = new StringBuilder("");
		if (!DeviceUtil.stringIsEmpty(pk_user) && !"default".equals(pk_user)) {
			userSql.append(" tt.repair_user_id = '" + pk_user + "' ");
		} else if (!DeviceUtil.stringIsEmpty(pk_project) && !"default".equals(pk_project)) {
			projectSql.append(" tt.pk_project = '" + pk_project + "' ");
		} else if (!DeviceUtil.stringIsEmpty(pk_area) && !"default".equals(pk_area)) {
			areaSql.append(" tt.pk_area = '" + pk_area + "' ");
		} else {
			Set<String> set = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentPk());
			if (!ArrayUtils.isEmpty(set)) {
				projectSql.append(" " + DeviceUtil.getInNotInSql("tt.pk_project", QueryContents.TYPE_IN, set.toArray(new String[] {})) + " ");
			} else {
				projectSql.append(" 1=2 ");
			}
		}
		if (DeviceUtil.stringIsEmpty(start_time) && DeviceUtil.stringIsEmpty(end_time)) {
			start_time = DeviceUtil.CurrentMonthFirstDay(new Date(), DeviceUtil.YMDHMS);
			end_time = DeviceUtil.date2String(new Date(), DeviceUtil.YMDHMS);
		}
		dateSql.append(" create_time >= '" + start_time + "' and create_time <= '" + end_time + "' ");

		// 查询总单量
		StringBuilder all_total = new StringBuilder();
		all_total.append("select fk_project_id,repair_user_id,count(pk_details_id) all_total from yjwy_worktask_details ");
		all_total.append("where repair_user_id is not null ");
		all_total.append("and " + dateSql);
		all_total.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		all_total.append("group by repair_user_id,fk_project_id ");
		// 查询完成总单量
		StringBuilder complete_total = new StringBuilder();
		complete_total.append("select fk_project_id,repair_user_id,count(pk_details_id) complete_total from yjwy_worktask_details ");
		complete_total.append("where task_state = 3 ");
		complete_total.append("and " + dateSql);
		complete_total.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		complete_total.append("group by repair_user_id,fk_project_id ");
		// 查询公共区域抢单数量
		StringBuilder public_grab = new StringBuilder();
		public_grab.append("select fk_project_id,repair_user_id,count(pk_details_id) public_grab from yjwy_worktask_details ");
		public_grab.append("where task_state = 3 and dispatch_type = 1 and service_type = 'serviceCateTwo' ");
		public_grab.append("and " + dateSql);
		public_grab.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		public_grab.append("group by repair_user_id,fk_project_id ");
		// 查询公共区域派单数量
		StringBuilder public_dispatch = new StringBuilder();
		public_dispatch.append("select fk_project_id,repair_user_id,count(pk_details_id) public_dispatch from yjwy_worktask_details ");
		public_dispatch.append("where dispatch_type = 2 and service_type = 'serviceCateTwo' ");
		public_dispatch.append("and " + dateSql);
		public_dispatch.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		public_dispatch.append("group by repair_user_id,fk_project_id ");
		// 查询户内区域抢单数量
		StringBuilder indoors_grab = new StringBuilder();
		indoors_grab.append("select fk_project_id,repair_user_id,count(pk_details_id) indoors_grab from yjwy_worktask_details ");
		indoors_grab.append("where task_state = 3 and dispatch_type = 1 and service_type = 'serviceCateOne' ");
		indoors_grab.append("and " + dateSql);
		indoors_grab.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		indoors_grab.append("group by repair_user_id,fk_project_id ");
		// 查询户内区域派单数量
		StringBuilder indoors_dispatch = new StringBuilder();
		indoors_dispatch.append("select fk_project_id,repair_user_id,count(pk_details_id) indoors_dispatch from yjwy_worktask_details ");
		indoors_dispatch.append("where dispatch_type = 2 and service_type = 'serviceCateOne' ");
		indoors_dispatch.append("and " + dateSql);
		indoors_dispatch.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		indoors_dispatch.append("group by repair_user_id,fk_project_id ");

		// 查询sql
		StringBuilder sql = new StringBuilder();
		sql.append("select * from (");
		sql.append("select t1.fk_project_id pk_project,t1.repair_user_id,t9.user_name_ repair_user_name,");
		sql.append("(case when t1.all_total is null then 0 else t1.all_total end ) all_total,");
		sql.append("(case when t2.complete_total is null then 0 else t2.complete_total end ) complete_total,");
		sql.append("(case when t3.public_grab is null then 0 else t3.public_grab end ) public_grab,");
		sql.append("(case when t4.public_dispatch is null then 0 else t4.public_dispatch end ) public_dispatch,");
		sql.append("(case when t5.indoors_grab is null then 0 else t5.indoors_grab end ) indoors_grab,");
		sql.append("(case when t6.indoors_dispatch is null then 0 else t6.indoors_dispatch end ) indoors_dispatch,");
		sql.append("t7.project_name_ project_name,t7.pk_area_ pk_area,t8.area_name_ area_name ");
		sql.append("from (");
		sql.append(all_total);
		sql.append(") t1 left join (");
		sql.append(complete_total);
		sql.append(") t2 on t2.fk_project_id = t1.fk_project_id and t2.repair_user_id = t1.repair_user_id left join (");
		sql.append(public_grab);
		sql.append(") t3 on t3.fk_project_id = t1.fk_project_id and t3.repair_user_id = t1.repair_user_id left join (");
		sql.append(public_dispatch);
		sql.append(") t4 on t4.fk_project_id = t1.fk_project_id and t4.repair_user_id = t1.repair_user_id left join (");
		sql.append(indoors_grab);
		sql.append(") t5 on t5.fk_project_id = t1.fk_project_id and t5.repair_user_id = t1.repair_user_id left join (");
		sql.append(indoors_dispatch);
		sql.append(") t6 on t6.fk_project_id = t1.fk_project_id and t6.repair_user_id = t1.repair_user_id ");
		sql.append("left join yjwy_pub_project t7 on t7.pk_project_ = t1.fk_project_id ");
		sql.append("left join yjwy_pub_area t8 on t8.pk_area_ = t7.pk_area_ ");
		sql.append("left join yjwy_pub_user t9 on t9.pk_user_ = t1.repair_user_id ");
		sql.append(") tt ");
		// 条件拼接
		if (!StringUtils.isEmpty(projectSql.toString()) || !StringUtils.isEmpty(areaSql.toString()) || !StringUtils.isEmpty(userSql.toString())) {
			sql.append("where " + userSql + projectSql + areaSql);
		}
		// 排序
		sql.append(" order by tt.repair_user_id ");
		long count = workTaskStatisticsService.queryCount(sql.toString());
		// 分页
		if (null != page && null != rows) {
			sql.append(" limit " + (page - 1) * rows + "," + rows);
		}
		List<Map<String, Object>> models = workTaskStatisticsService.queryList(sql.toString());
		// 计算
		for (Map<String, Object> map : models) {
			// 抢单与派单比率
			int grab = Integer.valueOf(map.get("public_grab").toString()) + Integer.valueOf(map.get("indoors_grab").toString());
			int dispatch = Integer.valueOf(map.get("public_dispatch").toString()) + Integer.valueOf(map.get("indoors_dispatch").toString());
			if (grab == 0 || dispatch == 0) {
				map.put("grab_dispatch", "0.0%");
			} else {
				map.put("grab_dispatch", DeviceUtil.getPercent(grab + "", dispatch + ""));
			}
		}
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", models);
		return mar;
	}

	/**
	 * 根据项目查询维修人员列表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "queryUser", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryProject(ParamEntity params) {
		// 获取区域主键
		String pk_project = params.getPk_project();
		ModelAndResult mr = null;
		// 设置默认选中项
		ParamEntity paramEntity = new ParamEntity();
		paramEntity.setPk_user("default");
		paramEntity.setUser_name("人员选择");
		paramEntity.setSelected(true);
		// 声明返回data数组
		Object[] modelsRes = new Object[] { paramEntity };
		if (null != pk_project) {
			// 项目查片区
			YJWYWorkTaskAreaProjectNexusModel model = areaProjectNexusDomainService.queryOneByCondition(Query.from(YJWYWorkTaskAreaProjectNexusModel.META_ID).where(new Condition("project_id", QueryContents.TYPE_EQ, pk_project)));
			if (null != model) {
				Query areaUserQuery = Query.from(YJWYWorkTaskAreaUserNexusModel.META_ID);
				areaUserQuery.where(new Condition("user_type", QueryContents.TYPE_EQ, "2"));
				areaUserQuery.and(new Condition("area_id", QueryContents.TYPE_EQ, model.getArea_id()));
				// 片区查人员
				YJWYWorkTaskAreaUserNexusModel[] areaUserNexusModels = areaUserNexusBusinessService.query(areaUserQuery);
				if (DeviceUtil.arrayIsNotEmpty(areaUserNexusModels)) {
					String[] userIds = new String[areaUserNexusModels.length];
					for (int i = 0; i < areaUserNexusModels.length; i++) {
						userIds[i] = areaUserNexusModels[i].getUser_id();
					}
					Query userQuery = Query.from(YJWYUserModel.META_ID);
					userQuery.where(new Condition("pk_user_", QueryContents.TYPE_IN, userIds));
					// 人员信息
					YJWYUserModel[] userModels = userBusinessService.query(userQuery);
					// 数据总数
					int len = userModels.length;
					// 实例化data数组在数据总数基础上长度加1
					modelsRes = new Object[len + 1];
					// 将默认选中的项目添加进去
					modelsRes[0] = paramEntity;
					// 将数据遍历转存到data数组中
					for (int i = 0; i < len; i++) {
						modelsRes[i + 1] = userModels[i];
					}
				}
			}
		}
		mr = new ModelAndResult(modelsRes);
		return mr;
	}

	/**
	 * 导出报表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult doExpDataStructureByTable(@RequestParam(value = "param", required = false) String param) {
		ParamEntity params = JSONObject.parseObject(param, ParamEntity.class);
		params.setPage(null);
		params.setRows(null);
		ModelAndResult mr = this.query(params);
		return ImpAndExpExcel.doExpExcel(mr.get("rows"), new String[] { "project_name", "repair_user_name", "all_total", "complete_total", "public_grab", "indoors_grab", "public_dispatch", "indoors_dispatch", "grab_dispatch" }, "templates/templates/worktask/工单管理抢派单情况对比表.xls", 2);
	}

}
