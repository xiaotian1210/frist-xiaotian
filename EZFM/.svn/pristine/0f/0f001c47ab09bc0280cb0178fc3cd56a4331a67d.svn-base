package com.shareworx.ezfm.timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.baseinfo.pub.service.IBaseInfoQueryService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.device.basic.executor.model.YJWYGroupModel;
import com.shareworx.ezfm.device.basic.executor.service.YJWYGroupBusinessService;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.timer.service.QualityTaskService;
import com.shareworx.ezfm.utils.UserUtil;

@Controller
@RequestMapping("ezfm/quality")
public class YJWYQualityTimerAction {
	final static Logger log = Logger.getLogger(YJWYQualityTimerAction.class);

	@Autowired
	@Qualifier(QualityTaskService.ID)
	public QualityTaskService qualityService;

	public void setQualityService(QualityTaskService qualityService) {
		this.qualityService = qualityService;
	}

	/**
	 * 当前用户所属区域下拉框备选项查询
	 * 
	 * @return
	 */
	@RequestMapping(value = "task", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryArea() {
		qualityService.executeTimerUpdateTask();
		qualityService.executeTimerGeneratorTask();
		ModelAndResult mr = new ModelAndResult();
		return mr;
	}

}
