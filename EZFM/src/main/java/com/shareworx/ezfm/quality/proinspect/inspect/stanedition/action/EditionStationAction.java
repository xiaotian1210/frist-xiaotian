package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.action;

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
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.dao.EditionStationDao;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationBusinessService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 版本与岗位中间表操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("inspect/stanedition")
public class EditionStationAction {

	final static Logger log = Logger.getLogger(EditionStationAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "inspect/stanedition";
	
	@Autowired
	@Qualifier(EditionStationBusinessService.ID)
	private EditionStationBusinessService service;
	
	public void setService(EditionStationBusinessService service) {
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
		query.and(Condition.create("pk_crop",UserUtil.getCurrentUser().getPk_crop()));
		EditionStationModel[] models = service.query(query);
		EditionStationDao domainDao = SpringUtils.getBean(EditionStationDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody EditionStationModel model) {
		EditionStationModel[] rs = service.save(new EditionStationModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody EditionStationModel model) {
		EditionStationModel[] rs = service.update(new EditionStationModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody EditionStationModel[] models) {
		EditionStationModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
