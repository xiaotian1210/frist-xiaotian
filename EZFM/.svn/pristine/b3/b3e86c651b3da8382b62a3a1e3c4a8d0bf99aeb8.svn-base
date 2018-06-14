package com.shareworx.ezfm.performance.leave.action;



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
import com.shareworx.ezfm.performance.leave.model.YJWYLeaveModel;
import com.shareworx.ezfm.performance.leave.service.YJWYLeaveBusinessService;
import com.shareworx.ezfm.performance.leave.service.YJWYLeaveDomainService;
import com.shareworx.ezfm.performance.leave.vo.YJWYLeaveModelVo;
import com.shareworx.ezfm.utils.ImpAndExpExcel;

/**
 * 休假备案操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/performance/leave")
public class YJWYLeaveAction {

	final static Logger log = Logger.getLogger(YJWYLeaveAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/performance/leave/leave_list";
	
	@Autowired
	@Qualifier(YJWYLeaveBusinessService.ID)
	private YJWYLeaveBusinessService service;
	
	public void setService(YJWYLeaveBusinessService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(YJWYLeaveDomainService.ID)
	private YJWYLeaveDomainService leaveService;
	
	public void setLeaveService(YJWYLeaveDomainService leaveService) {
		this.leaveService = leaveService;
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
	public @ResponseBody ModelAndResult query(@RequestBody YJWYLeaveModelVo leaveModelVo) {
		List<Map<String, Object>> list = leaveService.queryMap(leaveModelVo);
		ModelAndResult mar = new ModelAndResult(list);
		mar.setTotal(leaveService.queryTaskCount(leaveModelVo));
		return mar;
	}
	
	/**
	 * 导出excel
	 * @param leaveModelVo
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult doExpDataStructureByTable(@RequestParam(value="param", required=false) String param) {
		YJWYLeaveModelVo leaveModelVo = JSONObject.parseObject(param,YJWYLeaveModelVo.class);
		long countNum = leaveService.queryTaskCount(leaveModelVo);
		leaveModelVo.setLimit((int)countNum);
		List<Map<String, Object>> list = leaveService.queryMap(leaveModelVo);
		
		return ImpAndExpExcel.doExpExcel(list, new String[]{"lv_submitter_name","fk_project_name",
				"fk_job_name","lv_commitTime","lv_confirmTime","lv_startTime","lv_endTime",
				"lv_days","fk_type_name","lv_details"}, 
				"templates/templates/performance/绩效管理休假备案.xls", 2);
		
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYLeaveModel model) {
		YJWYLeaveModel[] rs = service.save(new YJWYLeaveModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYLeaveModel model) {
		YJWYLeaveModel[] rs = service.update(new YJWYLeaveModel[]{model});
		return new ModelAndResult(rs);
	}
	@RequestMapping(value="updateModels", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult updateModels(@RequestBody YJWYLeaveModel[] models) {
		YJWYLeaveModel[] rs = service.update(models);
		return new ModelAndResult(rs);
	}
	@RequestMapping(value="operation/update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult updateOperation(@RequestBody YJWYLeaveModel model) {
		YJWYLeaveModel[] rs = service.updateOperation(new YJWYLeaveModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYLeaveModel[] models) {
		YJWYLeaveModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
