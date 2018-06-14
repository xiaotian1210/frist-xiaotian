package com.shareworx.ezfm.login.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.pub.service.IBaseInfoQueryService;
import com.shareworx.ezfm.baseinfo.station.model.YJWYStationModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.login.service.IYJWYLoginAuthorizationService;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.action.InspectStandardAction;
import com.shareworx.ezfm.system.crop.model.CropModel;
import com.shareworx.ezfm.system.crop.service.ICropService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.MD5Utils;

@Controller
@RequestMapping("ezfm/home")
public class YJWYHomeAction {
	
	@Autowired
	private Environment evn;
	
	final static Logger log = Logger.getLogger(InspectStandardAction.class);
	/**home跳转页面 */
	public final static String HOME_FORWARD = "ezfm/login/home";
	public final static String FIRST_PAGE_FORWARD = "ezfm/login/first_page";
	
	public final static String METRO_HOME_FORWARD = "ezfm/login/metro";
	
	@Autowired
	@Qualifier(IYJWYLoginAuthorizationService.ID)
	private IYJWYLoginAuthorizationService service;

	public void setService(IYJWYLoginAuthorizationService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userservice;
	
	public void setService(YJWYUserDomainService userservice) {
		this.userservice = userservice;
	}
	
	@Autowired
	@Qualifier(IBaseInfoQueryService.ID)
	IBaseInfoQueryService baseInfoQueryService;
	
	public void setBaseInfoQueryService(IBaseInfoQueryService baseInfoQueryService) {
		this.baseInfoQueryService = baseInfoQueryService;
	}
	
	@Autowired
	@Qualifier(ICropService.ID)
	ICropService cropService;
	
	public void setCropService(ICropService cropService) {
		this.cropService = cropService;
	}
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	/**
	 * 转向HOME页面
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView homeForward(HttpServletRequest req, HttpServletResponse resp){
		String homeForward = HOME_FORWARD;
		if("metro".equals(evn.getProperty("homepage.style", "default"))) {
			homeForward = METRO_HOME_FORWARD;
		}
		ModelAndView mav = new ModelAndView(homeForward);
		mav.addObject("contextPath", req.getContextPath());
		mav.addObject("currentUser", UserUtil.getCurrentUser());
		return mav;
	}
	
	@RequestMapping(value="dashboard", method=RequestMethod.GET)
	public ModelAndView dashboard(HttpServletRequest req, HttpServletResponse resp){
		if(!"metro".equals(evn.getProperty("homepage.style", "default"))) {
			return null;
		}
		ModelAndView mav = new ModelAndView("ezfm/login/metro-func");
		Map<String, String> map = new HashMap<String, String>();
		map.put("id_", "-1");
		map.put("url_", "ezfm/home/first/page/index");
		map.put("name_", "我的面板");
		mav.addObject("contextPath", req.getContextPath());
		mav.addObject("requestFun", map);
		return mav;
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="metrofunc", method=RequestMethod.GET)
	public ModelAndView metroFunc(HttpServletRequest req, HttpServletResponse resp){
		if(!"metro".equals(evn.getProperty("homepage.style", "default"))) {
			return null;
		}
		YJWYUserModel loginUser = UserUtil.getCurrentUser();
		String funcId = req.getParameter("funcId");
		if(funcId==null||"".equals(funcId)) {
			return null;
		}
		if(loginUser.getUser_code().equals(LoginCommons.QYUSERCODE)) {
			String sql = "select * from yjwy_pub_function t3 where t3.id_=?" ;
			List<Map<String, Object>> list = dao.getReadTemplate().queryForList(sql, funcId);
			if(list==null||list.size()==0) {
				return null;
			}
			String url = list.get(0).get("url_")==null?"":list.get(0).get("url_").toString();
			if(url==null||"".equals(url)) {
				return null;
			}
			sql = "select t.* from yjwy_pub_function t where t.type_ = 'corp_function' order by order_";
			List<Map<String, Object>> siblingMenu = dao.getReadTemplate().queryForList(sql);
			ModelAndView mav = new ModelAndView("ezfm/login/metro-func");
			mav.addObject("contextPath", req.getContextPath());
			mav.addObject("requestFun", list.get(0));
			mav.addObject("siblingMenu", siblingMenu);
			return mav;
		} else if(loginUser.getUser_code().equals(LoginCommons.DMSUSERCODE)) {
			String sql = "select * from yjwy_pub_function t3 where t3.id_=?" ;
			List<Map<String, Object>> list = dao.getReadTemplate().queryForList(sql, funcId);
			if(list==null||list.size()==0) {
				return null;
			}
			String url = list.get(0).get("url_")==null?"":list.get(0).get("url_").toString();
			if(url==null||"".equals(url)) {
				return null;
			}
			sql = "select t.* from yjwy_pub_function t where t.type_ = 'dms_function' order by order_";
			List<Map<String, Object>> siblingMenu = dao.getReadTemplate().queryForList(sql);
			ModelAndView mav = new ModelAndView("ezfm/login/metro-func");
			mav.addObject("contextPath", req.getContextPath());
			mav.addObject("requestFun", list.get(0));
			mav.addObject("siblingMenu", siblingMenu);
			return mav;
		} else {
			String sql = "select * from yjwy_pub_function t3 where t3.type_='function' and t3.id_=? and t3.id_ in( "
					 +"	select t2.pk_func_ from yjwy_pub_role_func t2 where t2.pk_role_ in( "
					 +" select t1.pk_role_ from yjwy_pub_role_user t1 where t1.pk_user_='"+loginUser.getPk_user()+"' "
					 +" ) "
					 +" ) " ;
			List<Map<String, Object>> list = dao.getReadTemplate().queryForList(sql, funcId);
			if(list==null||list.size()==0) {
				return null;
			}
			String url = list.get(0).get("url_")==null?"":list.get(0).get("url_").toString();
			if(url==null||"".equals(url)) {
				return null;
			}
			sql = "select * from yjwy_pub_function t3 where t3.type_='function' and t3.pid_=? and t3.id_ in( "
					+" select t2.pk_func_ from yjwy_pub_role_func t2 where t2.pk_role_ in( "
					+" select t1.pk_role_ from yjwy_pub_role_user t1 where t1.pk_user_='"+loginUser.getPk_user()+"' "
					+" ) "
					+" ) order by order_ ";
			List<Map<String, Object>> siblingMenu = dao.getReadTemplate().queryForList(sql, list.get(0).get("pid_"));
			ModelAndView mav = new ModelAndView("ezfm/login/metro-func");
			mav.addObject("contextPath", req.getContextPath());
			mav.addObject("requestFun", list.get(0));
			mav.addObject("siblingMenu", siblingMenu);
			return mav;
		}
	}
	
	@RequestMapping(value="first/page/index", method=RequestMethod.GET)
	public ModelAndView firstPageForward(){
		ModelAndView mav = new ModelAndView(FIRST_PAGE_FORWARD);
		String userId = UserUtil.getCurrentUserPk();
		List<YJWYProjectModel> projectList = baseInfoQueryService.queryProjectsByUserId(userId);
		List<YJWYStationModel> stationList = baseInfoQueryService.queryStationsByUserId(userId);
		String projects = "";
		String stations = ""; 
		for(YJWYProjectModel model : projectList){
			projects = projects+model.getProject_name()+"；";
		}
		for(YJWYStationModel model : stationList){
			stations = stations+model.getStation_name()+"；";
		}
		CropModel crop = cropService.queryForObjectById(UserUtil.getCurrentUser().getPk_crop());
		mav.addObject("projects", projects);
		mav.addObject("stations", stations);
		if(null!=crop){
			mav.addObject("cropname",crop.getCrop_name());
		}
		mav.addObject("logincount", service.queryLoginCount());
		return mav;
	}
	
	/**
	 * 退出操作
	 * @param pk_user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="exit",method=RequestMethod.POST)
	public @ResponseBody ModelAndResult exitLogin(HttpServletRequest request){
		service.exitLogin(request.getSession());
		return new ModelAndResult(true,"账号已退出");
	}
	
	@RequestMapping(value="password/alter",method=RequestMethod.POST)
	public @ResponseBody ModelAndResult alterPassword(HttpServletRequest req, @RequestParam String id, @RequestParam String p1,@RequestParam String p2){
		YJWYUserModel userModel = UserUtil.getCurrentUser();
		YJWYUserModel dataModel = userservice.queryById(id);
		if(dataModel==null || !id.equals(userModel.getPk_user())){
			return new ModelAndResult(false, "密码修改失败,请稍后尝试修改");
		}
		String password = dataModel.getPassword();
		if(!MD5Utils.checkPassword(p1, password)){
			return new ModelAndResult(false, "原密码不对,请重新输入");
		}
		String md5p2 = MD5Utils.getMD5String(p2);
		dataModel.setPassword(md5p2);
		List<YJWYUserModel> list = userservice.update(dataModel);
		if(null==list||list.size()<1){
			return new ModelAndResult(false, "密码修改失败,请稍后尝试修改");
		}
		return new ModelAndResult(list.get(0));
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="dbtask/count")
	public @ResponseBody ModelAndResult queryDbTaskData(){
		YJWYUserModel user = UserUtil.getCurrentUser();
		String sql = " select count(*) 'value','核查' as label from yjwy_quality_inspecttask where pk_crop='"+user.getPk_crop()+"' and fk_taskuser = '"+user.getPk_user()+"' and task_state = '10'"+
				" union all"+
				" select count(*) 'value','报事' as label from yjwy_problem_details where pk_crop='"+user.getPk_crop()+"' and fk_duty_user_id='"+user.getPk_user()+"' and (state='1' or state='2')"+
				" union all"+
				" select count(*) 'value','工单' as label from yjwy_worktask_details where pk_crop='"+user.getPk_crop()+"' and duty_user_id='"+user.getPk_user()+"' and (task_state=1 or task_state=2)"+
				" union all"+
				" select count(*) 'value','巡检' as label from yjwy_patrol_task where pk_crop='"+user.getPk_crop()+"' and task_state='0' and task_type='0' and executor_id='"+user.getPk_user()+"'"+
				" union all"+
				" select count(*) 'value','维保' as label from yjwy_patrol_task where pk_crop='"+user.getPk_crop()+"' and task_state='0' and task_type='1' and executor_id='"+user.getPk_user()+"'";
		JdbcTemplate read = dao.getReadTemplate();
		List<Map<String, Object>> list = read.queryForList(sql);
		return new ModelAndResult(list);
	}
}
