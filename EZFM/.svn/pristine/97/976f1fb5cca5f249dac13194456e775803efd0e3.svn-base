package com.shareworx.ezfm.energyloss.tabledefinition.action;

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
import com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataBusinessService;
import com.shareworx.ezfm.energyloss.tabledefinition.dao.YJWJEnergyUpdateDao;
import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWJEnergyUpdateModel;
import com.shareworx.ezfm.energyloss.tabledefinition.service.YJWJEnergyUpdateBusinessService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;

/**
 * 能耗数据修改表操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("energyloss/tabledefinition/up")
public class YJWJEnergyUpdateAction {

	Logger log = Logger.getLogger(YJWJEnergyUpdateAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "energyloss/tabledefinition";
	
	@Autowired
	@Qualifier(YJWJEnergyUpdateBusinessService.ID)
	private YJWJEnergyUpdateBusinessService service;
	
	public void setService(YJWJEnergyUpdateBusinessService service) {
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
	@Autowired
	@Qualifier(YjwyEnergyDataBusinessService.ID)
	private YjwyEnergyDataBusinessService dateservice;
	
	public void setDateservice(YjwyEnergyDataBusinessService dateservice) {
		this.dateservice = dateservice;
	}
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWJEnergyUpdateModel[] models = service.query(query);
		YJWJEnergyUpdateDao domainDao = SpringUtils.getBean(YJWJEnergyUpdateDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody YJWJEnergyUpdateModel model) {
		YJWJEnergyUpdateModel[] rs = service.save(new YJWJEnergyUpdateModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWJEnergyUpdateModel model) {
		
		JdbcTemplate read = dao.getReadTemplate();
		JdbcTemplate write=dao.getWriteTemplate();
		String sql ="SELECT Count(yjwy_energy_update.id) FROM yjwy_energy_update WHERE yjwy_energy_update.eq_id =  '"
				+ model.getEq_id()
				+ "'";
		int count = read.queryForObject(sql, Integer.class);
		
//		YJWJEnergyUpdateModel[] rs = null;
		if(count==1){
			String sql1="UPDATE yjwy_energy_update SET energy_update = '"
					+ model.getEnergy_update()
					+ "'  WHERE eq_id = '"
					+ model.getEq_id()
					+ "'";
			 write.update(sql1);
			
			 
		}else{
			this.save(model);
		}
		 dateservice.updateLastEneryData(model.getEq_id(), model.getEnergy_update());
//		return new ModelAndResult(rs);
		return new ModelAndResult(model);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWJEnergyUpdateModel[] models) {
		YJWJEnergyUpdateModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
