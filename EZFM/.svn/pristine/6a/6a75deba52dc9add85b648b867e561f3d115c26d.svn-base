package com.shareworx.ezfm.worktask.areauser.action;

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
import com.shareworx.ezfm.baseinfo.user.dao.YJWYUserDao;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel;
import com.shareworx.ezfm.worktask.areauser.dao.YJWYWorkTaskAreaUserNexusDao;
import com.shareworx.ezfm.worktask.areauser.model.YJWYWorkTaskAreaUserNexusModel;
import com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusBusinessService;
import com.shareworx.ezfm.worktask.areauser.service.YJWYWorkTaskAreaUserNexusToolService;
import com.shareworx.ezfm.worktask.areauser.vo.AreaUserQueryVo;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 片区与人员关系表操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/worktask/areauser")
public class YJWYWorkTaskAreaUserNexusAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskAreaUserNexusAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "worktask/areauser";
	
	/** 跳转关联用户界面 */
	public final static String SUN_ADDUSER = "ezfm/workTask/areaDetails/area_add_user";
	@Autowired
	@Qualifier(YJWYWorkTaskAreaUserNexusBusinessService.ID)
	private YJWYWorkTaskAreaUserNexusBusinessService service;
	
	public void setService(YJWYWorkTaskAreaUserNexusBusinessService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(YJWYWorkTaskAreaUserNexusToolService.ID)
	private YJWYWorkTaskAreaUserNexusToolService toolService;
	
	public void setToolService(YJWYWorkTaskAreaUserNexusToolService toolService) {
		this.toolService = toolService;
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
	@RequestMapping(value="sonAddUser/{id}/{user_type}/{gridId}", method=RequestMethod.GET)
	public ModelAndView sonAddUser(HttpServletRequest request,@PathVariable String id,
			@PathVariable String user_type,@PathVariable String gridId){
		ModelAndView mv = new ModelAndView(SUN_ADDUSER);
		mv.addObject("pk_area_id",id);
		mv.addObject("user_type",user_type);
		mv.addObject("grid_id",gridId);
		return mv;
	}
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYWorkTaskAreaUserNexusModel[] models = service.query(query);
		YJWYWorkTaskAreaUserNexusDao domainDao = SpringUtils.getBean(YJWYWorkTaskAreaUserNexusDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 查询项目人员列表
	 * @return
	 */
	@RequestMapping(value="queryAreaByUser", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryAreaByUser(@RequestBody AreaUserQueryVo vo) {
		JdbcTemplate read = dao.getReadTemplate();
		String whrSql = " where 1=1 ";
		//拼接查询条件
		YJWYUserModel user = UserUtil.getCurrentUser();
		//用户姓名模糊匹配
		if (!StringUtils.isEmpty(vo.getUser_name())) {
			whrSql += "and user_name_ like '%"+vo.getUser_name()+"%' ";
		}
		whrSql += " and pk_crop_='"+user.getPk_crop()+"' and delete_flag_=0";
		String countSql = "select count(pk_user_) from yjwy_pub_user "+ whrSql;
		int count = read.queryForObject(countSql, Integer.class);
		YJWYUserModel[] models = service.queryUser(vo,whrSql);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 查询关联维修人列表
	 * @return
	 */
	@RequestMapping(value="queryAreaByUserRepair", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryAreaByUserRepair(@RequestBody AreaUserQueryVo vo) {
		JdbcTemplate read = dao.getReadTemplate();
		String whrSql = " where 1=1 ";
		if (!StringUtils.isEmpty(vo.getUser_name())) {
			whrSql += "and tab2.user_name_ like '%"+vo.getUser_name()+"%' ";
		}
		
		whrSql += " and pk_crop_='"+UserUtil.getCurrentUser().getPk_crop()+"' and tab2.delete_flag_=0";
		String countSql = "select count(tab1.pk_personnel_id)"
				+" from yjwy_worktask_area_personnel tab1 left join yjwy_pub_user tab2 on tab1.user_id=tab2.pk_user_ "
				+ whrSql;
		
		int count = read.queryForObject(countSql, Integer.class);
		YJWYWorkTaskAreaPersonnelModel[] models = service.queryUserRepair(vo,whrSql);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="queryAreaByUserRepairAll", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryAreaByUserRepairAll(@RequestParam(value="areaId", required=false) String areaId) {
		YJWYUserModel[] models = service.queryUserRepairAll(areaId);
		return new ModelAndResult(models);
	}
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestParam(value="area_id", required=false) String area_id,
		@RequestParam(value="user_id", required=false) String user_id,
		@RequestParam(value="user_type", required=false) String user_type) {
		YJWYWorkTaskAreaUserNexusModel model = new YJWYWorkTaskAreaUserNexusModel();
		model.setArea_id(area_id);
		if (user_type.equals("2")) {
			model.setPersonnel_id(user_id);
		}else{
			model.setUser_id(user_id);
		}
		model.setUser_type(user_type);
		model.setCreate_time(this.obtainTime());
		model.setCreate_user_id(UserUtil.getCurrentUserPk());
		YJWYWorkTaskAreaUserNexusModel[] rs = service.save(new YJWYWorkTaskAreaUserNexusModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYWorkTaskAreaUserNexusModel model) {
		YJWYWorkTaskAreaUserNexusModel[] rs = service.update(new YJWYWorkTaskAreaUserNexusModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYWorkTaskAreaUserNexusModel[] models) {
		YJWYWorkTaskAreaUserNexusModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
	
	
	/**
	 * 查询当前用户是否是调度人员
	 * @return
	 */
	@RequestMapping(value="whether/dispatch", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult whetherDispatch() {
		ModelAndResult model = new ModelAndResult();
		model.setSuccess(toolService.whetherDispatch(UserUtil.getCurrentUserPk()));
		return model;
	}
	
	//公共方法，获取当前时间
	public String obtainTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
}
