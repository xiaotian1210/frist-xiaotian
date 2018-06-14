package com.shareworx.ezfm.device.timer.action;

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
import com.shareworx.ezfm.device.timer.dao.YJWYTimerDao;
import com.shareworx.ezfm.device.timer.model.YJWYTimerModel;
import com.shareworx.ezfm.device.timer.service.YJWYTimerBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 定时任务执行记录操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("device/timer")
public class YJWYTimerAction {

	final static Logger log = Logger.getLogger(YJWYTimerAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "device/timer_list";
	
	@Autowired
	@Qualifier(YJWYTimerBusinessService.ID)
	private YJWYTimerBusinessService service;
	
	public void setService(YJWYTimerBusinessService service) {
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
		YJWYTimerModel[] models = service.query(query);
		YJWYTimerDao domainDao = SpringUtils.getBean(YJWYTimerDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody YJWYTimerModel model) {
		YJWYTimerModel[] rs = service.save(new YJWYTimerModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYTimerModel model) {
		YJWYTimerModel[] rs = service.update(new YJWYTimerModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYTimerModel[] models) {
		YJWYTimerModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
