package com.shareworx.ezfm.pub.test.plms.action;

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
import com.shareworx.ezfm.pub.test.plms.dao.PlmsDao;
import com.shareworx.ezfm.pub.test.plms.model.PlmsModel;
import com.shareworx.ezfm.pub.test.plms.service.PlmsBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 测试plms操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("test/plms")
public class PlmsAction {

	Logger log = Logger.getLogger(PlmsAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "test/plms";
	
	@Autowired
	@Qualifier(PlmsBusinessService.ID)
	private PlmsBusinessService service;
	
	public void setService(PlmsBusinessService service) {
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
		PlmsModel[] models = service.query(query);
		PlmsDao domainDao = SpringUtils.getBean(PlmsDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody PlmsModel model) {
		PlmsModel[] rs = service.save(new PlmsModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody PlmsModel model) {
		PlmsModel[] rs = service.update(new PlmsModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody PlmsModel[] models) {
		PlmsModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
