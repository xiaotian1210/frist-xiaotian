package com.shareworx.ezfm.problem.record.action;

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
import com.shareworx.ezfm.problem.record.dao.YJWYProblemRecordDao;
import com.shareworx.ezfm.problem.record.model.YJWYProblemRecordModel;
import com.shareworx.ezfm.problem.record.service.YJWYProblemRecordBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 报事记录操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/problem/record")
public class YJWYProblemRecordAction {

	final static Logger log = Logger.getLogger(YJWYProblemRecordAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "problem/record";
	
	@Autowired
	@Qualifier(YJWYProblemRecordBusinessService.ID)
	private YJWYProblemRecordBusinessService service;
	
	public void setService(YJWYProblemRecordBusinessService service) {
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
		YJWYProblemRecordModel[] models = service.query(query);
		YJWYProblemRecordDao domainDao = SpringUtils.getBean(YJWYProblemRecordDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody YJWYProblemRecordModel model) {
		YJWYProblemRecordModel[] rs = service.save(new YJWYProblemRecordModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYProblemRecordModel model) {
		YJWYProblemRecordModel[] rs = service.update(new YJWYProblemRecordModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYProblemRecordModel[] models) {
		YJWYProblemRecordModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
