package com.shareworx.ezfm.device.siteproject.action;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel;
import com.shareworx.ezfm.device.fmdata.service.YJWYSiteBusinessService;
import com.shareworx.ezfm.device.patrol.plan.model.YJWYPlanModel;
import com.shareworx.ezfm.device.patrol.plan.service.YJWYPlanBusinessService;
import com.shareworx.ezfm.device.siteproject.dao.YJWYSiteProjectDao;
import com.shareworx.ezfm.device.siteproject.model.YJWYSiteProjectModel;
import com.shareworx.ezfm.device.siteproject.service.YJWYSiteProjectBusinessService;
import com.shareworx.ezfm.device.siteproject.service.YJWYSiteProjectService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.vo.ParamEntity;

/**
 * FM与YJWY项目关联表操作控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/device/project")
public class YJWYSiteProjectAction {

	final static Logger log = Logger.getLogger(YJWYSiteProjectAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/device/fmdata/cognate_listcard";

	@Autowired
	@Qualifier(YJWYSiteProjectBusinessService.ID)
	private YJWYSiteProjectBusinessService siteProjectService;

	public void setSiteProjectService(YJWYSiteProjectBusinessService siteProjectService) {
		this.siteProjectService = siteProjectService;
	}

	@Autowired
	@Qualifier(YJWYSiteProjectService.ID)
	private YJWYSiteProjectService siteProService;

	public void setSiteProService(YJWYSiteProjectService siteProService) {
		this.siteProService = siteProService;
	}

	@Autowired
	@Qualifier(YJWYSiteBusinessService.ID)
	private YJWYSiteBusinessService siteService;

	public void setSiteService(YJWYSiteBusinessService siteService) {
		this.siteService = siteService;
	}

	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectService;

	public void setProjectService(YJWYProjectBusinessService projectService) {
		this.projectService = projectService;
	}

	@Autowired
	@Qualifier(YJWYPlanBusinessService.ID)
	private YJWYPlanBusinessService planBusinessService;

	public void setPlanBusinessService(YJWYPlanBusinessService planBusinessService) {
		this.planBusinessService = planBusinessService;
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
	 * 查询FM项目列表
	 */
	@RequestMapping(value = "querySite", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult querySite() {
		// 查询所有项目关联数据
		List<Map<String, Object>> list = siteProService.queryMap(null);
		// 存放已存在关联关系的FM项目id
		Set<Object> siteIds = new HashSet<>();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				siteIds.add(list.get(i).get("site_id"));
			}
		}
		Query siteQuery = Query.from(YJWYSiteModel.META_ID);
		siteQuery.where(new Condition("flag", QueryContents.TYPE_EQ, 1));
		siteQuery.and(new Condition("pk_crop", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
		if (siteIds.size() > 0) {
			siteQuery.and(new Condition("site_id", QueryContents.TYPE_NOT_IN, siteIds));
		}
		YJWYSiteModel[] models = siteService.query(siteQuery);
		YJWYSiteModel[] models2 = new YJWYSiteModel[models.length + 1];
		YJWYSiteModel model = new YJWYSiteModel();
		model.setSite_id("default");
		model.setName("FM选择");
		model.setAttribute("selected", true);
		models2[0] = model;
		for (int i = 0; i < models.length; i++) {
			models2[i + 1] = models[i];
		}
		ModelAndResult mar = new ModelAndResult(models2);
		return mar;
	}
	/**
	 * 查询FM项目列表
	 */
	@RequestMapping(value = "queryProject", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryProject(ParamEntity params) {
		String pk_area = params.getPk_area();
		List<Map<String, Object>> list = siteProService.queryMap(null);
		// 存放已存在关联关系的YJWY项目id
		Set<Object> projectIds = new HashSet<>();
		if (null != list && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				projectIds.add(list.get(i).get("pk_project"));
			}
		}
		Query query = Query.from(YJWYProjectModel.META_ID);
		query.where(new Condition("pk_crop_", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
		query.and(new Condition("pk_area_",QueryContents.TYPE_EQ,pk_area));
		if (projectIds.size() > 0) {
			query.and(new Condition("pk_project_", QueryContents.TYPE_NOT_IN, projectIds));
		}
		YJWYProjectModel[] models= projectService.query(query);
		YJWYProjectModel[] models2 = new YJWYProjectModel[models.length + 1];
		YJWYProjectModel model = new YJWYProjectModel();
		model.setPk_project("default");
		model.setProject_name("项目选择");
		model.setSelected(true);
		models2[0] = model;
		for (int i = 0; i < models.length; i++) {
			models2[i + 1] = models[i];
		}
		ModelAndResult mar = new ModelAndResult(models2);
		return mar;
	}
	/**
	 * 查询含有FM项目信息和YJWY项目信息的关联数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(ParamEntity params) {
		Query query = Query.from(YJWYSiteProjectModel.META_ID);
		query.where(new Condition("pk_crop", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
		List<Map<String, Object>> list = siteProService.queryMap(params);
		YJWYSiteProjectDao domainDao = SpringUtils.getBean(YJWYSiteProjectDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", list);
		return mar;
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYSiteProjectModel model) {
		DeviceUtil.setPkCrop(model);
		YJWYSiteProjectModel[] rs = siteProjectService.save(new YJWYSiteProjectModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYSiteProjectModel model) {
		YJWYSiteProjectModel[] rs = siteProjectService.update(new YJWYSiteProjectModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYSiteProjectModel[] models) {
		ModelAndResult mar = new ModelAndResult();
		String[] pk_projects = null;
		YJWYPlanModel[] planModels = null;
		// 获取项目id
		if (DeviceUtil.arrayIsNotEmpty(models)) {
			pk_projects = new String[models.length];
			for (int i = 0; i < models.length; i++) {
				pk_projects[i] = models[i].getPk_project();
			}
		}
		// 判断是否被关联使用
		if (DeviceUtil.arrayIsNotEmpty(pk_projects)) {
			planModels = planBusinessService.query(Query.from(YJWYPlanModel.META_ID)
					.where(new Condition("pk_project", QueryContents.TYPE_IN, pk_projects)));
		}
		if (DeviceUtil.arrayIsNotEmpty(planModels)) {
			mar.setAttribute("usedFlag", 1);
			mar.setData(planModels);
			return mar;
		}
		// 执行删除
		YJWYSiteProjectModel[] rs = siteProService.delete(models);
		mar.setData(rs);
		return mar;
	}

}
