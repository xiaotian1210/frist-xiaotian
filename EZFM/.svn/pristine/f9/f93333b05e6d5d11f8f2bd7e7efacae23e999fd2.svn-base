package com.shareworx.ezfm.baseinfo.rolefunc.action;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shareworx.ezfm.baseinfo.rolefunc.dao.YJWYAPPRoleFuncDao;
import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYAPPRoleFuncModel;
import com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYAPPRoleFuncBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * APP手机权限操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/approlefunc")
public class YJWYAPPRoleFuncAction {

	final static Logger log = Logger.getLogger(YJWYAPPRoleFuncAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/rolefunc";
	
	@Autowired
	@Qualifier(YJWYAPPRoleFuncBusinessService.ID)
	private YJWYAPPRoleFuncBusinessService service;
	
	public void setService(YJWYAPPRoleFuncBusinessService service) {
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
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYAPPRoleFuncModel[] models = service.query(query);
		YJWYAPPRoleFuncDao domainDao = SpringUtils.getBean(YJWYAPPRoleFuncDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYAPPRoleFuncModel model) {
		YJWYAPPRoleFuncModel[] rs = service.save(new YJWYAPPRoleFuncModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYAPPRoleFuncModel model) {
		YJWYAPPRoleFuncModel[] rs = service.update(new YJWYAPPRoleFuncModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYAPPRoleFuncModel[] models) {
		YJWYAPPRoleFuncModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
