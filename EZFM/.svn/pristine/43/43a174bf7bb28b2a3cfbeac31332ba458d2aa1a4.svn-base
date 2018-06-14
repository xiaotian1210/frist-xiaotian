package com.shareworx.ezfm.system.crop.pre.action;

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
import com.shareworx.ezfm.system.crop.pre.dao.PreCropDao;
import com.shareworx.ezfm.system.crop.pre.model.PreCropModel;
import com.shareworx.ezfm.system.crop.pre.service.PreCropBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 企业信息预采集实体操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/system/crop/pre")
public class PreCropAction {

	Logger log = Logger.getLogger(PreCropAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "crop/pre";
	
	@Autowired
	@Qualifier(PreCropBusinessService.ID)
	private PreCropBusinessService service;
	
	public void setService(PreCropBusinessService service) {
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
		PreCropModel[] models = service.query(query);
		PreCropDao domainDao = SpringUtils.getBean(PreCropDao.ID);
		long count = domainDao.countListByCondition(JSON.parseObject(param, Query.class));
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
		
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody PreCropModel model) {
		PreCropModel[] rs = service.save(new PreCropModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody PreCropModel model) {
		PreCropModel[] rs = service.update(new PreCropModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody PreCropModel[] models) {
		PreCropModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
