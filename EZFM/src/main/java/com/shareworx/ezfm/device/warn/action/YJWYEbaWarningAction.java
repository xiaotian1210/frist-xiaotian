package com.shareworx.ezfm.device.warn.action;

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
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.DomainModel;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqBusinessService;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService;
import com.shareworx.ezfm.device.siteproject.model.YJWYSiteProjectModel;
import com.shareworx.ezfm.device.siteproject.service.YJWYSiteProjectBusinessService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.device.warn.model.YJWYEbaWarningModel;
import com.shareworx.ezfm.device.warn.service.YJWYEbaWarningBusinessService;
import com.shareworx.ezfm.device.warn.service.YJWYEbaWarningDomainService;
import com.shareworx.ezfm.system.crop.model.CropModel;
import com.shareworx.ezfm.system.crop.service.ICropService;
import com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel;
import com.shareworx.ezfm.worktask.details.service.YJWYWorkTaskDetailsBusinessService;

/**
 * eba设备报警信息操作控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/device/warn")
public class YJWYEbaWarningAction {

	final static Logger log = Logger.getLogger(YJWYEbaWarningAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/device/warn/warn_listcard";

	@Autowired
	@Qualifier(YJWYEbaWarningBusinessService.ID)
	private YJWYEbaWarningBusinessService ebaWarningBusinessService;

	public void setEbaWarningBusinessService(YJWYEbaWarningBusinessService ebaWarningBusinessService) {
		this.ebaWarningBusinessService = ebaWarningBusinessService;
	}

	@Autowired
	@Qualifier(YJWYEbaWarningDomainService.ID)
	private YJWYEbaWarningDomainService ebaWarningDomainService;

	public void setEbaWarningDomainService(YJWYEbaWarningDomainService ebaWarningDomainService) {
		this.ebaWarningDomainService = ebaWarningDomainService;
	}

	@Autowired
	@Qualifier(ICropService.ID)
	private ICropService cropService;

	public void setCropService(ICropService cropService) {
		this.cropService = cropService;
	}

	@Autowired
	@Qualifier(YJWYEqBusinessService.ID)
	private YJWYEqBusinessService eqBusinessService;

	public void setEqBusinessService(YJWYEqBusinessService eqBusinessService) {
		this.eqBusinessService = eqBusinessService;
	}

	@Autowired
	@Qualifier(YJWYWorkTaskDetailsBusinessService.ID)
	private YJWYWorkTaskDetailsBusinessService taskDetailsBusinessService;

	public void setTaskDetailsBusinessService(YJWYWorkTaskDetailsBusinessService taskDetailsBusinessService) {
		this.taskDetailsBusinessService = taskDetailsBusinessService;
	}

	@Autowired
	@Qualifier(YJWYSiteProjectBusinessService.ID)
	private YJWYSiteProjectBusinessService siteProjectBusinessService;

	public void setSiteProjectBusinessService(YJWYSiteProjectBusinessService siteProjectBusinessService) {
		this.siteProjectBusinessService = siteProjectBusinessService;
	}

	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;

	public void setService(BaseDomainService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(YJWYRoomBusinessService.ID)
	private YJWYRoomBusinessService roomBusinessService;

	public void setRoomBusinessService(YJWYRoomBusinessService roomBusinessService) {
		this.roomBusinessService = roomBusinessService;
	}

	@Autowired
	@Qualifier(YJWYRoomDomainService.ID)
	private YJWYRoomDomainService roomDomainService;

	public void setRoomDomainService(YJWYRoomDomainService roomDomainService) {
		this.roomDomainService = roomDomainService;
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
	public @ResponseBody ModelAndResult query(ParamEntity params) {
		List<Map<String, Object>> models = ebaWarningDomainService.queryList(params);
		long count = ebaWarningDomainService.queryCount(params);
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", models);
		return mar;
	}

	/**
	 * EBA预警信息上传接口
	 * 
	 * @param userName
	 *            用户名
	 * @param userPass
	 *            密码
	 * @param report_id
	 *            预警id
	 * @param device_code
	 *            设备编号
	 * @param warning_content
	 *            预警内容
	 * @param warning_value
	 *            预警值
	 * @param warning_time
	 *            预警时间
	 * @return
	 */
	@RequestMapping(value = "pushWarningData", method = RequestMethod.POST)
	public @ResponseBody String pushWarningData(@RequestParam(value = "report_id") String report_id, @RequestParam(value = "device_code") String device_code, @RequestParam(value = "warning_content") String warning_content, @RequestParam(value = "warning_value") String warning_value, @RequestParam(value = "warning_time") String warning_time, @RequestParam(value = "crop_code") String crop_code, @RequestParam(value = "eliminate_url") String eliminate_url) {
		log.info("report_id=" + report_id + ",device_code=" + device_code + ",warning_content=" + warning_content + ",warning_value=" + warning_value + ",warning_time=" + warning_time + ",crop_code=" + crop_code + ",eliminate_url=" + eliminate_url);
		String content = "服务端出错";
		int result = 3;
		try {
			// 判断参数
			if (StringUtils.isEmpty(report_id) || StringUtils.isEmpty(device_code) || StringUtils.isEmpty(warning_content) || StringUtils.isEmpty(warning_value) || StringUtils.isEmpty(warning_time) || StringUtils.isEmpty(crop_code) | StringUtils.isEmpty(eliminate_url)) {
				result = 2;
				content = "参数列表不正确：参数不能为空，请检查后重试！";
			} else {
				// 根据crop_code获取pk_crop
				CropModel cropModel = cropService.queryForObject(crop_code);
				if (null != cropModel) {
					String pk_crop = cropModel.getPk_crop();
					// 根据设备编号获取eq_id
					YJWYEqModel[] eqModels = eqBusinessService.query(Query.from(YJWYEqModel.META_ID).where(new Condition("flag", QueryContents.TYPE_EQ, 1)).and(new Condition("fm_code", QueryContents.TYPE_EQ, device_code)).and(new Condition("pk_crop", QueryContents.TYPE_EQ, pk_crop)));
					if (DeviceUtil.arrayIsNotEmpty(eqModels)) {
						YJWYEqModel eqModel = eqModels[0];
						// 判断当前设备所属项目是否关联
						String site_id = eqModel.getSite_id();
						DomainModel siteProjectModel = null;
						if (!StringUtils.isEmpty(site_id)) {
							List<YJWYSiteProjectModel> siteProjectModels = service.queryListByCondition(Query.from(YJWYSiteProjectModel.META_ID).where(new Condition("site_id", QueryContents.TYPE_EQ, site_id)));
							if (!ArrayUtils.isEmpty(siteProjectModels)) {
								siteProjectModel = siteProjectModels.get(0);
							} else {
								result = 2;
								content = "参数列表不正确：设备编号所属项目未关联达美盛资产云项目，请联系达美盛资产云！";
							}
						} else {
							result = 2;
							content = "参数列表不正确：设备编号所属项目不存在，请检查后重试！";
						}
						if (null != siteProjectModel) {
							// 判断是否有设备相应未完成的工单。
							Query query = Query.from(YJWYWorkTaskDetailsModel.META_ID);
							query.where(new Condition("task_state", QueryContents.TYPE_NOT_IN, new String[] { "3", "6" }));
							query.and(new Condition("eba_report_id", QueryContents.TYPE_EQ, report_id));
							List<YJWYWorkTaskDetailsModel> taskDetailsModels = service.queryListByCondition(query);
							if (ArrayUtils.isEmpty(taskDetailsModels)) {
								// 判断预警id
								YJWYEbaWarningModel[] ebaWarningModels = ebaWarningBusinessService.query(Query.from(YJWYEbaWarningModel.META_ID).where(new Condition("report_id", QueryContents.TYPE_EQ, report_id)));
								YJWYEbaWarningModel ebaWarningModel = null;
								boolean flag = true;// 保存操作标识
								if (!DeviceUtil.arrayIsNotEmpty(ebaWarningModels)) {
									ebaWarningModel = new YJWYEbaWarningModel();
									ebaWarningModel.setReport_id(report_id);
								} else {
									ebaWarningModel = ebaWarningModels[0];
									flag = false;// 修改操作标识
								}
								ebaWarningModel.setDevice_code(device_code);
								ebaWarningModel.setWarning_content(warning_content);
								ebaWarningModel.setWarning_value(warning_value);
								ebaWarningModel.setWarning_time(warning_time);
								ebaWarningModel.setWarn_state("1");
								ebaWarningModel.setEq_id(eqModel.getEq_id());
								ebaWarningModel.setPk_crop(pk_crop);
								ebaWarningModel.setEliminate_url(eliminate_url);
								// 持久化记录
								if (flag) {
									ebaWarningBusinessService.save(new YJWYEbaWarningModel[] { ebaWarningModel });
								} else {
									ebaWarningBusinessService.update(new YJWYEbaWarningModel[] { ebaWarningModel });
								}
								// 报修工单保存操作
								YJWYWorkTaskDetailsModel workTaskDetailsModel = getWorkTaskDetailsModel(ebaWarningModel, eqModel, siteProjectModel);
								taskDetailsBusinessService.saveCallPolice(new YJWYWorkTaskDetailsModel[] { workTaskDetailsModel });
							}
							result = 1;
							content = "提交成功！";
						}
					} else {
						result = 2;
						content = "参数列表不正确：设备编号对应的设备记录不存在，请检查后重试！";
					}
				} else {
					result = 2;
					content = "参数列表不正确：企业编码不存在，请检查后重试！";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			content = content + ":" + e.getMessage();
			log.error("预警信息上传出错", e);
		}
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		jsonObject.put("content", content);
		return jsonObject.toJSONString();
	}

	/**
	 * 根据报警信息生成工单
	 * 
	 * @param ebaWarningModel
	 * @param eqModel
	 * @return
	 * @throws Exception
	 */
	private YJWYWorkTaskDetailsModel getWorkTaskDetailsModel(YJWYEbaWarningModel ebaWarningModel, YJWYEqModel eqModel, DomainModel siteProjectModel) throws Exception {
		YJWYWorkTaskDetailsModel workTaskDetailsModel = new YJWYWorkTaskDetailsModel();
		// 设备id
		workTaskDetailsModel.setFk_repair_equipment(eqModel.getEq_id());
		// 机房相关
		String rm_id = eqModel.getRm_id();
		if (!StringUtils.isEmpty(rm_id)) {
			// 详细地址
			workTaskDetailsModel.setRepair_details(roomDomainService.queryById(rm_id).getName());
			// 机房id
			workTaskDetailsModel.setFk_repair_equipment_room(rm_id);
		}
		// 项目相关
		workTaskDetailsModel.setFk_project_id((String) siteProjectModel.getAttribute("pk_project"));
		// 所属crop
		workTaskDetailsModel.setPk_crop(ebaWarningModel.getPk_crop());
		// 维修内容
		workTaskDetailsModel.setRepair_content(ebaWarningModel.getWarning_content());
		// 维修类型
		workTaskDetailsModel.setService_type("serviceCateTwo");
		// 维修种类id
		workTaskDetailsModel.setRepair_class_id("EBA_OverrunCallPolice");
		// 报修人
		workTaskDetailsModel.setRepair_user("EBA设备设施自动报警");
		// 预警id
		workTaskDetailsModel.setEba_report_id(ebaWarningModel.getReport_id());
		return workTaskDetailsModel;
	}

	/**
	 * 忽略操作
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "setIgnore", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult setIgnore(@RequestBody YJWYEbaWarningModel model) {
		YJWYEbaWarningModel[] rs = ebaWarningBusinessService.update(new YJWYEbaWarningModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYEbaWarningModel model) {
		YJWYEbaWarningModel[] rs = ebaWarningBusinessService.update(new YJWYEbaWarningModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYEbaWarningModel[] models) {
		YJWYEbaWarningModel[] rs = ebaWarningBusinessService.delete(models);
		return new ModelAndResult(rs);
	}
}
