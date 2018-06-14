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
import com.shareworx.ezfm.baseinfo.resources.dao.AttributeNameDao;
import com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel;
import com.shareworx.ezfm.baseinfo.resources.service.AttributeNameBusinessService;
import com.shareworx.ezfm.baseinfo.resources.service.AttributeNameDomainService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;

/**
 * 属性名称表操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/resources/attributename")
public class AttributeNameAction {

	Logger log = Logger.getLogger(AttributeNameAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/resources";
	
	@Autowired
	@Qualifier(AttributeNameBusinessService.ID)
	private AttributeNameBusinessService service;
	
	public void setService(AttributeNameBusinessService service) {
		this.service = service;
	}
	@Autowired
	@Qualifier(AttributeNameDomainService.ID)
	private AttributeNameDomainService domainservice;



	public void setDomainservice(AttributeNameDomainService domainservice) {
		this.domainservice = domainservice;
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
		AttributeNameModel[] models = service.query(query);
		AttributeNameDao domainDao = SpringUtils.getBean(AttributeNameDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		
		mar.setAttribute("models", models);
		return mar;
	}
	@RequestMapping(value="queryName",method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryName(@RequestParam(value="type_id",required=false)String type_id){
		String sql="select t1.attribute_name,t1.attribute_code from yjwy_attribute_name t1 left join yjwy_resource_attribute_pk_resource t2 "
				+ "on t1.attribute_code=t2.pk_attribute  where t2.pk_resource='"
				+ type_id
				+ "'";
		JdbcTemplate read =dao.getReadTemplate();
		List<Map<String, Object>> list = read.queryForList(sql);	
		
		ModelAndResult mar =new ModelAndResult(list);
		return mar;
		
	}
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody AttributeNameModel model) {
		AttributeNameModel[] rs = service.save(new AttributeNameModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody AttributeNameModel model) {
		AttributeNameModel[] rs = service.update(new AttributeNameModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody AttributeNameModel[] models) {
		AttributeNameModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
