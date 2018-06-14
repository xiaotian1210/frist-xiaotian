package com.shareworx.ezfm.worktask.projectuser.action;

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
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.projectuser.dao.YJWYWorkTaskProjectUserDao;
import com.shareworx.ezfm.worktask.projectuser.model.YJWYWorkTaskProjectUserModel;
import com.shareworx.ezfm.worktask.projectuser.service.YJWYWorkTaskProjectUserBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;

/**
 * 项目接口人员操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/worktask/projectuser")
public class YJWYWorkTaskProjectUserAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskProjectUserAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/workTask/areaProjectUser/project_user_listcard";
	
	/** 跳转关联项目界面 */
	public final static String SUN_ADDUSER = "ezfm/workTask/areaProjectUser/add_project_user";
	
	@Autowired
	@Qualifier(YJWYWorkTaskProjectUserBusinessService.ID)
	private YJWYWorkTaskProjectUserBusinessService service;
	
	public void setService(YJWYWorkTaskProjectUserBusinessService service) {
		this.service = service;
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
	 *  转向详情
	 * @return
	 */
	@RequestMapping(value="sonAddProjectUser/{id}", method=RequestMethod.GET)
	public ModelAndView sonAddProjectUser(HttpServletRequest request,@PathVariable String id){
		ModelAndView mv = new ModelAndView(SUN_ADDUSER);
		mv.addObject("projectId",id);
		return mv;
	}
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYWorkTaskProjectUserModel[] models = service.query(query);
		YJWYWorkTaskProjectUserDao domainDao = SpringUtils.getBean(YJWYWorkTaskProjectUserDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setAttribute("rows", models);;
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
	
	
	/**
	 * 通过项目查询所有用户
	 * @return
	 */
	@RequestMapping(value="queryProjectByUserAll", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryProjectByUserAll(@RequestParam(value="projectId", required=false) String projectId) {
		YJWYUserModel[] userModel = service.findUserAllByProjectId(projectId);
		return new ModelAndResult(userModel);
	}
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestParam(value="projectId", required=false) String projectId,
			@RequestParam(value="user_id", required=false) String user_id) {
		YJWYWorkTaskProjectUserModel model = new YJWYWorkTaskProjectUserModel();
		model.setProject_id(projectId);
		model.setUser_id(user_id);
		model.setNexus_type("1");
		model.setCreate_time(this.obtainTime());
		model.setCreate_user_id(UserUtil.getCurrentUserPk());
		YJWYWorkTaskProjectUserModel[] rs = service.save(new YJWYWorkTaskProjectUserModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYWorkTaskProjectUserModel model) {
		YJWYWorkTaskProjectUserModel[] rs = service.update(new YJWYWorkTaskProjectUserModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYWorkTaskProjectUserModel[] models) {
		YJWYWorkTaskProjectUserModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
	
	//通过项目ID查询人员
	public int countUserByProjectId(String projectId){
		JdbcTemplate read = dao.getReadTemplate();
		String sql = " select count(pk_user_) from yjwy_pub_user where pk_user_ in(select pk_user_ from view_yjwy_project_user where pk_project_ = '"+projectId+"') ";
		return read.queryForObject(sql, Integer.class);
		}
		//公共方法，获取当前时间
	public String obtainTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
}
