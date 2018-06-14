package com.shareworx.ezfm.worktask.areadetails.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.areadetails.dao.YJWYWorkTaskAreaDetailsDao;
import com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel;
import com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 片区详情操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/worktask/areadetails")
public class YJWYWorkTaskAreaDetailsAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskAreaDetailsAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/workTask/areaDetails/area_details_list";
	
	/** 打开子页面 */
	public final static String SONPAGE_FORWARD = "ezfm/workTask/areaDetails/area_sun_page";
	@Autowired
	@Qualifier(YJWYWorkTaskAreaDetailsBusinessService.ID)
	private YJWYWorkTaskAreaDetailsBusinessService service;
	
	public void setService(YJWYWorkTaskAreaDetailsBusinessService service) {
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
	 *  转向详情
	 * @return
	 */
	@RequestMapping(value="sonpage/{id}", method=RequestMethod.GET)
	public ModelAndView sonpageForward(HttpServletRequest request,@PathVariable String id){
		ModelAndView mv = new ModelAndView(SONPAGE_FORWARD);
		mv.addObject("pk_area_id",id);
		return mv;
	}
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYWorkTaskAreaDetailsModel[] models = service.query(query);
		YJWYWorkTaskAreaDetailsDao domainDao = SpringUtils.getBean(YJWYWorkTaskAreaDetailsDao.ID);
		query.and(Condition.create("pk_crop", UserUtil.getCurrentUser().getPk_crop()));
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		mar.setAttribute("rows", models);
		return mar;
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYWorkTaskAreaDetailsModel model) {
		YJWYWorkTaskAreaDetailsModel[] rs = service.save(new YJWYWorkTaskAreaDetailsModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYWorkTaskAreaDetailsModel model) {
		YJWYWorkTaskAreaDetailsModel[] rs = service.update(new YJWYWorkTaskAreaDetailsModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYWorkTaskAreaDetailsModel[] models) {
		YJWYWorkTaskAreaDetailsModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
