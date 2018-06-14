package com.shareworx.ezfm.problem.nexus.proanduser.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectByUserService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.problem.nexus.proanduser.dao.YJWYProjectInfoUserNexusDao;
import com.shareworx.ezfm.problem.nexus.proanduser.model.YJWYProjectInfoUserNexusModel;
import com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusBusinessService;
import com.shareworx.ezfm.problem.nexus.proanduser.service.YJWYProjectInfoUserNexusDomainService;
import com.shareworx.ezfm.utils.UserUtil;
import com.alibaba.fastjson.JSON;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 接口人员关系表操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/nexus/proanduser")
public class YJWYProjectInfoUserNexusAction {

	final static Logger log = Logger.getLogger(YJWYProjectInfoUserNexusAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "nexus/proanduser";
	
	@Autowired
	@Qualifier(YJWYProjectInfoUserNexusBusinessService.ID)
	private YJWYProjectInfoUserNexusBusinessService service;
	
	public void setService(YJWYProjectInfoUserNexusBusinessService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(YJWYProjectInfoUserNexusDomainService.ID)
	private YJWYProjectInfoUserNexusDomainService domainService;
	
	public void setDomainService(YJWYProjectInfoUserNexusDomainService domainService) {
		this.domainService = domainService;
	}
	@Autowired
	@Qualifier(YJWYProjectByUserService.ID)
	private YJWYProjectByUserService proUserService;
	
	
	public void setProUserService(YJWYProjectByUserService proUserService) {
		this.proUserService = proUserService;
	}
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
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
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="project_id", required=false) String project_id) {
		
		YJWYUserModel[] userModel= proUserService.queryUserByPro(project_id);
		Query query = Query.from(YJWYProjectInfoUserNexusModel.META_ID);
		YJWYProjectInfoUserNexusModel[] models = service.query(query);
		YJWYProjectInfoUserNexusDao domainDao = SpringUtils.getBean(YJWYProjectInfoUserNexusDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 通过项目查询用户
	 * @return
	 */
	@RequestMapping(value="queryProjectByUser", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryProjectByUser(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		List<Condition> andList = query.getAndList();
		String projectId = "";
		String userName = "";
		for (Condition con:andList) {
			if (con.getKey().equals("projectId")) {
				projectId = con.getValue().toString();
			}
			if (con.getKey().equals("user_name_")) {
				userName = con.getValue().toString();
			}
		}
		int limit = query.getLimit();
		int start = query.getStart();
		YJWYUserModel[] userModel = service.findUserByProjectId(projectId,userName,start,limit);
		long count = countUserByProjectId(projectId);
		ModelAndResult mar = new ModelAndResult(userModel);
		mar.setTotal(count);
		return mar;
	}
	//通过项目ID查询人员
		public int countUserByProjectId(String projectId){
			JdbcTemplate read = dao.getReadTemplate();
			String sql = " select count(pk_user_) from yjwy_pub_user where pk_user_ in(select pk_user_ from view_yjwy_project_user where pk_project_ = '"+projectId+"') ";
			return read.queryForObject(sql, Integer.class);
			}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestParam(value="projectId", required=false) String projectId,
			@RequestParam(value="user_id", required=false) String user_id) {
		YJWYProjectInfoUserNexusModel model = new YJWYProjectInfoUserNexusModel();
		model.setProject_id(projectId);
		model.setUser_id(user_id);
		model.setCreate_time(this.obtainTime());
		model.setCreate_user_id(UserUtil.getCurrentUserPk());
		YJWYProjectInfoUserNexusModel[] rs = service.save(new YJWYProjectInfoUserNexusModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYProjectInfoUserNexusModel model) {
		YJWYProjectInfoUserNexusModel[] rs = service.update(new YJWYProjectInfoUserNexusModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYProjectInfoUserNexusModel[] models) {
		YJWYProjectInfoUserNexusModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
	
	
	/**
	 * 根据id删除相关数据
	 * @param pk_nexus_id
	 * @return
	 */
	@RequestMapping(value="delete/model", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult deleteModel(@RequestParam(value="pk_nexus_id", required=false) String pk_nexus_id) {
		YJWYProjectInfoUserNexusModel model = new YJWYProjectInfoUserNexusModel();
		if (!StringUtils.isEmpty(pk_nexus_id)) {
			model = domainService.queryById(pk_nexus_id);
		}
		YJWYProjectInfoUserNexusModel[] rs = service.delete(new YJWYProjectInfoUserNexusModel[]{model});
		return new ModelAndResult(rs);
	}
	
	
	//公共方法，获取当前时间
	public String obtainTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
}
