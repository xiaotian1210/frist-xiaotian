package com.shareworx.ezfm.baseinfo.resources.action;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shareworx.ezfm.baseinfo.resources.dao.YJWYResourceAttributePkResourceDao;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourceAttributePkResourceModel;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourceAttributePkResourceBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;

/**
 * 资源属性与资源关联操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/resources/attributepkresource")
public class YJWYResourceAttributePkResourceAction {

	Logger log = Logger.getLogger(YJWYResourceAttributePkResourceAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/resources";
	
	@Autowired
	@Qualifier(YJWYResourceAttributePkResourceBusinessService.ID)
	private YJWYResourceAttributePkResourceBusinessService service;
	
	public void setService(YJWYResourceAttributePkResourceBusinessService service) {
		this.service = service;
	}
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
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
		YJWYResourceAttributePkResourceModel[] models = service.query(query);
		YJWYResourceAttributePkResourceDao domainDao = SpringUtils.getBean(YJWYResourceAttributePkResourceDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody YJWYResourceAttributePkResourceModel model) {
		YJWYResourceAttributePkResourceModel[] rs = service.save(new YJWYResourceAttributePkResourceModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYResourceAttributePkResourceModel model) {
		YJWYResourceAttributePkResourceModel[] rs = service.update(new YJWYResourceAttributePkResourceModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYResourceAttributePkResourceModel[] models) {
		YJWYResourceAttributePkResourceModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
