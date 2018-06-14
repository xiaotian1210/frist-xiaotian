package com.shareworx.ezfm.system.crop.file.action;

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
import com.shareworx.ezfm.system.crop.file.dao.CropFileDao;
import com.shareworx.ezfm.system.crop.file.model.CropFileModel;
import com.shareworx.ezfm.system.crop.file.service.CropFileBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 企业文件存储操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("crop/file")
public class CropFileAction {

	Logger log = Logger.getLogger(CropFileAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "crop/file";
	
	@Autowired
	@Qualifier(CropFileBusinessService.ID)
	private CropFileBusinessService service;
	
	public void setService(CropFileBusinessService service) {
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
		CropFileModel[] models = service.query(query);
		CropFileDao domainDao = SpringUtils.getBean(CropFileDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody CropFileModel model) {
		CropFileModel[] rs = service.save(new CropFileModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody CropFileModel model) {
		CropFileModel[] rs = service.update(new CropFileModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody CropFileModel[] models) {
		CropFileModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
