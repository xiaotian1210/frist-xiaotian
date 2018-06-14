package com.shareworx.ezfm.device.fmdata.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.ezfm.device.fmdata.service.YJWYFmDataService;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;

/**
 * FM数据同步操作控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/device/fmdata")
public class YJWYFmdataAction {

	final static Logger log = Logger.getLogger(YJWYFmdataAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/device/fmdata/synchro_list";

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	@Autowired
	@Qualifier(YJWYFmDataService.ID)
	private YJWYFmDataService fmDataService;

	public void setFmDataService(YJWYFmDataService fmDataService) {
		this.fmDataService = fmDataService;
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

	@RequestMapping(value = "synchro", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult synchro(HttpServletRequest req, HttpServletResponse resp, @RequestParam(value = "address", required = false) String address, @RequestParam(value = "crop_code", required = false) String crop_code) throws Exception {
		deviceService.configSessionUser(req, resp, 3600);
		ModelAndResult mar = null;
		try {
			System.out.println("=================FM数据同步【开始】！==================");
			mar = fmDataService.synchro("http://101.201.196.210:8081/archibus/cxf/BasisService", "YYJT");
			System.out.println("=================FM数据同步【完成】！==================");
			deviceService.configSessionUser(req, resp, 3600);
			return mar;
		} catch (Exception e) {
			System.out.println("=================FM数据同步【失败】！==================");
			mar = new ModelAndResult(false, e.getMessage());
			e.printStackTrace();
			return mar;
		}
	}

}
