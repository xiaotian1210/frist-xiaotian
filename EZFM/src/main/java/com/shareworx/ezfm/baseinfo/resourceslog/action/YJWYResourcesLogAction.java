package com.shareworx.ezfm.baseinfo.resourceslog.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.shareworx.ezfm.baseinfo.resourceslog.dao.YJWYResourcesLogDao;
import com.shareworx.ezfm.baseinfo.resourceslog.model.YJWYResourcesLogModel;
import com.shareworx.ezfm.baseinfo.resourceslog.service.YJWYResourcesLogBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 记录资源空间日志表操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("baseinfo/resourceslog")
public class YJWYResourcesLogAction {

	Logger log = Logger.getLogger(YJWYResourcesLogAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/resourceslog";

	@Autowired
	@Qualifier(YJWYResourcesLogBusinessService.ID)
	private YJWYResourcesLogBusinessService service;

	public void setService(YJWYResourcesLogBusinessService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(YJWYResourcesLogDao.ID)
	private YJWYResourcesLogDao sourceLogDao;

	public void setSourceLogDao(YJWYResourcesLogDao sourceLogDao) {
		this.sourceLogDao = sourceLogDao;
	}

	/**
	 * 转向列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView listForward() {
		return new ModelAndView(PAGE_FORWARD);
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYResourcesLogModel[] models = service.query(query);
		YJWYResourcesLogDao domainDao = SpringUtils.getBean(YJWYResourcesLogDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYResourcesLogModel model) {
		YJWYResourcesLogModel[] rs = service.save(new YJWYResourcesLogModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYResourcesLogModel model) {
		YJWYResourcesLogModel[] rs = service.update(new YJWYResourcesLogModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYResourcesLogModel[] models) {
		YJWYResourcesLogModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}

	/**
	 * 返回“新增”或者“更新的记录”资源数据的id给易彩区。
	 * 
	 * @return
	 */
	@RequestMapping(value = "getSaUpRecords", method = RequestMethod.POST)
	public @ResponseBody String getSaUpRecords(HttpServletRequest reqParam, HttpServletResponse response) {
		String date = reqParam.getParameter("nozzleDate");
		return this.service.SaUpRecords(date);
	}


}
