package com.shareworx.ezfm.problem.classadmin.action;

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
import com.shareworx.ezfm.problem.classadmin.dao.yjwyjwtest_csDao;
import com.shareworx.ezfm.problem.classadmin.model.yjwyjwtest_csModel;
import com.shareworx.ezfm.problem.classadmin.service.yjwyjwtest_csBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 测试用的操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("problem/classadmin")
public class yjwyjwtest_csAction {

	Logger log = Logger.getLogger(yjwyjwtest_csAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "problem/classadmin";
	
	@Autowired
	@Qualifier(yjwyjwtest_csBusinessService.ID)
	private yjwyjwtest_csBusinessService service;
	
	public void setService(yjwyjwtest_csBusinessService service) {
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
		yjwyjwtest_csModel[] models = service.query(query);
		yjwyjwtest_csDao domainDao = SpringUtils.getBean(yjwyjwtest_csDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody yjwyjwtest_csModel model) {
		yjwyjwtest_csModel[] rs = service.save(new yjwyjwtest_csModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody yjwyjwtest_csModel model) {
		yjwyjwtest_csModel[] rs = service.update(new yjwyjwtest_csModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody yjwyjwtest_csModel[] models) {
		yjwyjwtest_csModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
