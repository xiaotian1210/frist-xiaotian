package com.shareworx.ezfm.baseinfo.resources.action;

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
import com.shareworx.ezfm.baseinfo.resources.dao.YJWYBaseAttributeDao;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYBaseAttributeModel;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYBaseAttributeBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 基础属性表操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/resources/baseattribute")
public class YJWYBaseAttributeAction {

	Logger log = Logger.getLogger(YJWYBaseAttributeAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/resources";
	
	@Autowired
	@Qualifier(YJWYBaseAttributeBusinessService.ID)
	private YJWYBaseAttributeBusinessService service;
	
	public void setService(YJWYBaseAttributeBusinessService service) {
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
		YJWYBaseAttributeModel[] models = service.query(query);
		YJWYBaseAttributeDao domainDao = SpringUtils.getBean(YJWYBaseAttributeDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody YJWYBaseAttributeModel model) {
		YJWYBaseAttributeModel[] rs = service.save(new YJWYBaseAttributeModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYBaseAttributeModel model) {
		YJWYBaseAttributeModel[] rs = service.update(new YJWYBaseAttributeModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYBaseAttributeModel[] models) {
		YJWYBaseAttributeModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
