package com.shareworx.ezfm.worktask.sonclass.action;

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
import com.shareworx.ezfm.worktask.sonclass.dao.YJWYWorkTaskSonClassRecordDao;
import com.shareworx.ezfm.worktask.sonclass.model.YJWYWorkTaskSonClassRecordModel;
import com.shareworx.ezfm.worktask.sonclass.service.YJWYWorkTaskSonClassRecordBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 工单维修种类记录表操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("worktask/sonclass")
public class YJWYWorkTaskSonClassRecordAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskSonClassRecordAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "worktask/sonclass";
	
	@Autowired
	@Qualifier(YJWYWorkTaskSonClassRecordBusinessService.ID)
	private YJWYWorkTaskSonClassRecordBusinessService service;
	
	public void setService(YJWYWorkTaskSonClassRecordBusinessService service) {
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
		YJWYWorkTaskSonClassRecordModel[] models = service.query(query);
		YJWYWorkTaskSonClassRecordDao domainDao = SpringUtils.getBean(YJWYWorkTaskSonClassRecordDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody YJWYWorkTaskSonClassRecordModel model) {
		YJWYWorkTaskSonClassRecordModel[] rs = service.save(new YJWYWorkTaskSonClassRecordModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYWorkTaskSonClassRecordModel model) {
		YJWYWorkTaskSonClassRecordModel[] rs = service.update(new YJWYWorkTaskSonClassRecordModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYWorkTaskSonClassRecordModel[] models) {
		YJWYWorkTaskSonClassRecordModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
