package com.shareworx.ezfm.saas.payment.action;

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
import com.shareworx.ezfm.saas.payment.dao.PaymentDao;
import com.shareworx.ezfm.saas.payment.model.PaymentModel;
import com.shareworx.ezfm.saas.payment.service.PaymentBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 缴费操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("saas/payment")
public class PaymentAction {

	Logger log = Logger.getLogger(PaymentAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "saas/payment";
	
	@Autowired
	@Qualifier(PaymentBusinessService.ID)
	private PaymentBusinessService service;
	
	public void setService(PaymentBusinessService service) {
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
		PaymentModel[] models = service.query(query);
		PaymentDao domainDao = SpringUtils.getBean(PaymentDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody PaymentModel model) {
		PaymentModel[] rs = service.save(new PaymentModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody PaymentModel model) {
		PaymentModel[] rs = service.update(new PaymentModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody PaymentModel[] models) {
		PaymentModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
