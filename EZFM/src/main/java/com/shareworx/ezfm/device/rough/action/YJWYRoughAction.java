package com.shareworx.ezfm.device.rough.action;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.ezfm.device.rough.service.YJWYRoughService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;

/**
 * 设备概况操作控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/device/rough")
public class YJWYRoughAction {

	final static Logger log = Logger.getLogger(YJWYRoughAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/device/rough/rough_list";

	@Autowired
	@Qualifier(YJWYRoughService.ID)
	private YJWYRoughService yjwyRoughService;

	public void setYjwyRoughService(YJWYRoughService yjwyRoughService) {
		this.yjwyRoughService = yjwyRoughService;
	}

	/**
	 * 转向设备概况页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView checkListForward() {
		return new ModelAndView(PAGE_FORWARD);
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(ParamEntity params) {
		Map<String, Object> map = yjwyRoughService.queryRatio(params);
		ModelAndResult mar = new ModelAndResult(map);
		return mar;
	}

}
