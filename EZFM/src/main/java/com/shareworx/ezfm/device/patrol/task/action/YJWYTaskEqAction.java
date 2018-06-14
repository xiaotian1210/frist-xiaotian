package com.shareworx.ezfm.device.patrol.task.action;

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
import com.shareworx.ezfm.device.patrol.task.dao.YJWYTaskEqDao;
import com.shareworx.ezfm.device.patrol.task.model.YJWYTaskEqModel;
import com.shareworx.ezfm.device.patrol.task.service.YJWYTaskEqBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 巡检维保任务表操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("patrol/taskkkkkkkk")
public class YJWYTaskEqAction {

	final static Logger log = Logger.getLogger(YJWYTaskEqAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "patrol/task";
	
	@Autowired
	@Qualifier(YJWYTaskEqBusinessService.ID)
	private YJWYTaskEqBusinessService service;
	
	public void setService(YJWYTaskEqBusinessService service) {
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
		YJWYTaskEqModel[] models = service.query(query);
		YJWYTaskEqDao domainDao = SpringUtils.getBean(YJWYTaskEqDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody YJWYTaskEqModel model) {
		YJWYTaskEqModel[] rs = service.save(new YJWYTaskEqModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYTaskEqModel model) {
		YJWYTaskEqModel[] rs = service.update(new YJWYTaskEqModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYTaskEqModel[] models) {
		YJWYTaskEqModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
