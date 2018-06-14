package com.shareworx.ezfm.performance.sign.action;

import java.util.List;
import java.util.Map;

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

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.ezfm.performance.sign.model.YJWYSignModel;
import com.shareworx.ezfm.performance.sign.service.YJWYSignBusinessService;
import com.shareworx.ezfm.performance.sign.service.YJWYSignDomainService;
import com.shareworx.ezfm.performance.sign.vo.YJWYSignModelVo;
import com.shareworx.ezfm.utils.ImpAndExpExcel;

/**
 * 签到管理操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/performance/sign")
public class YJWYSignAction {

	final static Logger log = Logger.getLogger(YJWYSignAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/performance/sign/sign_list";
	
	@Autowired
	@Qualifier(YJWYSignBusinessService.ID)
	private YJWYSignBusinessService service;
	
	public void setService(YJWYSignBusinessService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(YJWYSignDomainService.ID)
	private YJWYSignDomainService signService;
	
	public void setSignService(YJWYSignDomainService signService) {
		this.signService = signService;
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
	public @ResponseBody ModelAndResult query(@RequestBody YJWYSignModelVo signModelVo) {
		List<Map<String, Object>> models = signService.queryMap(signModelVo);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(signService.queryTaskCount(signModelVo));
		return mar;
	}
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="export", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult doExport(@RequestParam(value="param", required=false) String param) {
		YJWYSignModelVo signModelVo = JSONObject.parseObject(param,YJWYSignModelVo.class);
		long countNum = signService.queryTaskCount(signModelVo);
		signModelVo.setLimit((int)countNum);
		List<Map<String, Object>> modelList = signService.queryMap(signModelVo);
		for (Map<String, Object> map : modelList) {
			if("1".equals(map.get("sign_action"))){
				map.put("sign_action_name", "签到");
			}else{
				map.put("sign_action_name", "签退");
			}
		}
		return ImpAndExpExcel.doExpExcel(modelList, new String[]{"user_name","sign_commitTime",
				"sign_action_name","project_name"}, 
				"templates/templates/performance/绩效管理签到管理.xls", 2);
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYSignModel model) {
		YJWYSignModel[] rs = service.save(new YJWYSignModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYSignModel model) {
		YJWYSignModel[] rs = service.update(new YJWYSignModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYSignModel[] models) {
		YJWYSignModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
