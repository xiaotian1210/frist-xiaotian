package com.shareworx.ezfm.worktask.areaproject.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel;
import com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsDomainService;
import com.shareworx.ezfm.worktask.areaproject.dao.YJWYWorkTaskAreaProjectNexusDao;
import com.shareworx.ezfm.worktask.areaproject.model.YJWYWorkTaskAreaProjectNexusModel;
import com.shareworx.ezfm.worktask.areaproject.service.YJWYWorkTaskAreaProjectNexusBusinessService;
import com.shareworx.ezfm.worktask.areaproject.vo.WorkTaskAreaProVo;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;

/**
 * 片区项目关联操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/worktask/areaproject")
public class YJWYWorkTaskAreaProjectNexusAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskAreaProjectNexusAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "worktask/areaproject";
	
	/** 跳转关联项目界面 */
	public final static String SUN_ADDPROJECT = "ezfm/workTask/areaDetails/area_add_project";
	
	@Autowired
	@Qualifier(YJWYWorkTaskAreaProjectNexusBusinessService.ID)
	private YJWYWorkTaskAreaProjectNexusBusinessService service;
	
	@Autowired
	@Qualifier(YJWYWorkTaskAreaDetailsDomainService.ID)
	private YJWYWorkTaskAreaDetailsDomainService detailsService;
	
	@Autowired
	@Qualifier(YJWYAreaDomainService.ID)
	private YJWYAreaDomainService areaService;
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	public void setAreaService(YJWYAreaDomainService areaService) {
		this.areaService = areaService;
	}
	
	public void setDetailsService(YJWYWorkTaskAreaDetailsDomainService detailsService) {
		this.detailsService = detailsService;
	}
	
	public void setService(YJWYWorkTaskAreaProjectNexusBusinessService service) {
		this.service = service;
	}

	/**
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView listForward(){
		return new ModelAndView(PAGE_FORWARD);
	}
	
	/**
	 *  转向详情
	 * @return
	 */
	@RequestMapping(value="sonAddProject/{id}", method=RequestMethod.GET)
	public ModelAndView sonAddProject(HttpServletRequest request,@PathVariable String id){
		ModelAndView mv = new ModelAndView(SUN_ADDPROJECT);
		//根据id查询区域信息
		YJWYWorkTaskAreaDetailsModel areaDetailsModel = detailsService.queryById(id);
		mv.addObject("pk_area_id",id);
		if (areaDetailsModel!=null) {
			YJWYAreaModel areaModel = areaService.queryById(areaDetailsModel.getFk_region_id());
			if (areaModel!=null) {
				mv.addObject("pk_area",areaModel.getPk_area());
				mv.addObject("area_name",areaModel.getArea_name());
			}
		}
		return mv;
	}
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYWorkTaskAreaProjectNexusModel[] models = service.query(query);
		YJWYWorkTaskAreaProjectNexusDao domainDao = SpringUtils.getBean(YJWYWorkTaskAreaProjectNexusDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	
	/**
	 * 根据区域项目查询项目
	 * @return
	 */
	@RequestMapping(value="queryAreaByProject", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryAreaByProject(@RequestBody WorkTaskAreaProVo queryvo) {
		JdbcTemplate read = dao.getReadTemplate();
		String wheSql = "from yjwy_pub_project where pk_area_ in ("
				+ " select fk_region_id from yjwy_worktask_area_details where pk_area_id='"+queryvo.getArea_id()+"'"
				+ " ) and pk_project_ not in ("
				+ " select project_id from yjwy_worktask_area_project_nexus where project_id in ("
				+ "select pk_project_ from yjwy_pub_project where pk_area_ in" 
				+ " (select fk_region_id from yjwy_worktask_area_details where pk_area_id='"+queryvo.getArea_id()+"')))"
				+ " and delete_flag_=0";
		String countSql = "select count(pk_project_) "+wheSql;
		YJWYProjectModel[] models = service.queryProject(wheSql,queryvo);
		int count = read.queryForObject(countSql, Integer.class);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestParam(value="area_id", required=false) String area_id,@RequestParam(value="project_id", required=false) String project_id) {
		YJWYWorkTaskAreaProjectNexusModel model = new YJWYWorkTaskAreaProjectNexusModel();
		model.setArea_id(area_id);
		model.setProject_id(project_id);
		model.setCreate_time(this.obtainTime());
		model.setCreate_user_id(UserUtil.getCurrentUserPk());
		YJWYWorkTaskAreaProjectNexusModel[] rs = service.save(new YJWYWorkTaskAreaProjectNexusModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYWorkTaskAreaProjectNexusModel model) {
		YJWYWorkTaskAreaProjectNexusModel[] rs = service.update(new YJWYWorkTaskAreaProjectNexusModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYWorkTaskAreaProjectNexusModel[] models) {
		YJWYWorkTaskAreaProjectNexusModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
	
	
	public Query getQuery(Query query) throws ShareworxServiceException {
		//拼接查询Query
		Query projectQuery = Query.from(YJWYProjectModel.META_ID);
		Query areaDetails = Query.from(YJWYWorkTaskAreaDetailsModel.META_ID);
		projectQuery.setPage(query.getPage());
		projectQuery.setLimit(query.getLimit());
		projectQuery.setStart(query.getStart());
		areaDetails.setAndList(query.getAndList());
		List<YJWYWorkTaskAreaDetailsModel> areaDetailsModels = detailsService.queryListByCondition(areaDetails);
		for (YJWYWorkTaskAreaDetailsModel areaDetailsModel:areaDetailsModels) {
			YJWYAreaModel areaModel = areaService.queryById(areaDetailsModel.getFk_region_id());
			projectQuery.and(new Condition("pk_area_", QueryContents.TYPE_EQ, areaModel.getPk_area()));
		}
	 	
		return projectQuery;
	}
	//公共方法，获取当前时间
		public String obtainTime(){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			return df.format(new Date());
		}
}
