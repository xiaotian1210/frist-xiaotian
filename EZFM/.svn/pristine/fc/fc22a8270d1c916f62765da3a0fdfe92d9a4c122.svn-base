package com.shareworx.ezfm.worktask.record.action;

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
import com.shareworx.ezfm.worktask.record.dao.YJWYWorkTaskDetailsRecordDao;
import com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel;
import com.shareworx.ezfm.worktask.record.service.YJWYWorkTaskDetailsRecordBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 工单详情表操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/worktask/record")
public class YJWYWorkTaskDetailsRecordAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskDetailsRecordAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "worktask/record";
	
	@Autowired
	@Qualifier(YJWYWorkTaskDetailsRecordBusinessService.ID)
	private YJWYWorkTaskDetailsRecordBusinessService service;
	
	public void setService(YJWYWorkTaskDetailsRecordBusinessService service) {
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
		YJWYWorkTaskDetailsRecordModel[] models = service.query(query);
		YJWYWorkTaskDetailsRecordDao domainDao = SpringUtils.getBean(YJWYWorkTaskDetailsRecordDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody YJWYWorkTaskDetailsRecordModel model) {
		YJWYWorkTaskDetailsRecordModel[] rs = service.save(new YJWYWorkTaskDetailsRecordModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYWorkTaskDetailsRecordModel model) {
		YJWYWorkTaskDetailsRecordModel[] rs = service.update(new YJWYWorkTaskDetailsRecordModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYWorkTaskDetailsRecordModel[] models) {
		YJWYWorkTaskDetailsRecordModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
