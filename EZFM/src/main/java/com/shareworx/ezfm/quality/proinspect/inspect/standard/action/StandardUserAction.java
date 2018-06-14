package com.shareworx.ezfm.quality.proinspect.inspect.standard.action;

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
import com.shareworx.ezfm.quality.proinspect.inspect.standard.dao.StandardUserDao;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardUserModel;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardUserBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 核查标准人员中间表操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/quality/proinspect/inspect/standard/user")
public class StandardUserAction {

	Logger log = Logger.getLogger(StandardUserAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "inspect/standard";
	
	@Autowired
	@Qualifier(StandardUserBusinessService.ID)
	private StandardUserBusinessService service;
	
	public void setService(StandardUserBusinessService service) {
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
		StandardUserModel[] models = service.query(query);
		StandardUserDao domainDao = SpringUtils.getBean(StandardUserDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody StandardUserModel model) {
		StandardUserModel[] rs = service.save(new StandardUserModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody StandardUserModel model) {
		StandardUserModel[] rs = service.update(new StandardUserModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody StandardUserModel[] models) {
		StandardUserModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
