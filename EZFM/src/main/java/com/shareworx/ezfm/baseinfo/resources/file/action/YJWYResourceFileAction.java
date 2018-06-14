package com.shareworx.ezfm.baseinfo.resources.file.action;

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
import com.shareworx.ezfm.baseinfo.resources.file.dao.YJWYResourceFileDao;
import com.shareworx.ezfm.baseinfo.resources.file.model.YJWYResourceFileModel;
import com.shareworx.ezfm.baseinfo.resources.file.service.YJWYResourceFileBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 资源附件操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("resources/file")
public class YJWYResourceFileAction {

	Logger log = Logger.getLogger(YJWYResourceFileAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "resources/file";
	
	@Autowired
	@Qualifier(YJWYResourceFileBusinessService.ID)
	private YJWYResourceFileBusinessService service;
	
	public void setService(YJWYResourceFileBusinessService service) {
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
		YJWYResourceFileModel[] models = service.query(query);
		YJWYResourceFileDao domainDao = SpringUtils.getBean(YJWYResourceFileDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody YJWYResourceFileModel model) {
		YJWYResourceFileModel[] rs = service.save(new YJWYResourceFileModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYResourceFileModel model) {
		YJWYResourceFileModel[] rs = service.update(new YJWYResourceFileModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYResourceFileModel[] models) {
		YJWYResourceFileModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
