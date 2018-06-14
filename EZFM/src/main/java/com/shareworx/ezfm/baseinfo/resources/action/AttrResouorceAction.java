package com.shareworx.ezfm.baseinfo.resources.action;

import java.util.List;
import java.util.Map;

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
import com.shareworx.ezfm.baseinfo.resources.dao.AttrResouorceDao;
import com.shareworx.ezfm.baseinfo.resources.model.AttrResouorceModel;
import com.shareworx.ezfm.baseinfo.resources.service.AttrResouorceBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;

/**
 * 资源_属性操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/resources/attr")
public class AttrResouorceAction {

	Logger log = Logger.getLogger(AttrResouorceAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/resources";
	
	@Autowired
	@Qualifier(AttrResouorceBusinessService.ID)
	private AttrResouorceBusinessService service;
	
	public void setService(AttrResouorceBusinessService service) {
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
		AttrResouorceModel[] models = service.query(query);
		AttrResouorceDao domainDao = SpringUtils.getBean(AttrResouorceDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="queryReource", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryReource(@RequestParam(value="pk_resources",required=false)String pk_resources) {
		String sql="select t1.attribute_name,t2.attr_value from yjwy_attribute_name t1 "
				+ "left join yjwy_attr_resource t2 on t1.attribute_code=t2.attr_name "
				+ "where t2.attr_value <>'null' and t2.pk_resources='"
				+ pk_resources
				+ "' ";
		JdbcTemplate read = dao.getReadTemplate();
		List<Map<String, Object>> list = read.queryForList(sql);
		ModelAndResult mar = new ModelAndResult(list);
		return mar;
		
		
	}
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody AttrResouorceModel model) {
		AttrResouorceModel[] rs = service.save(new AttrResouorceModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody AttrResouorceModel model) {
		AttrResouorceModel[] rs = service.update(new AttrResouorceModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody AttrResouorceModel[] models) {
		AttrResouorceModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
