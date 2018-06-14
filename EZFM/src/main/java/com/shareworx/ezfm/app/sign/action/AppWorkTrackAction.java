package com.shareworx.ezfm.app.sign.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.sign.service.AppWorkTrackService;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;

/**
 * 工作轨迹  action
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/worktrack")
public class AppWorkTrackAction {

	final static Logger logger = Logger.getLogger(AppWorkTrackAction.class);
	
	@Autowired
	@Qualifier(AppWorkTrackService.ID)
	private AppWorkTrackService service;
	
	/**
	 * 获取工作轨迹
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "getWorkTrack", method = RequestMethod.GET)
	public @ResponseBody JSONObject getWorkTrack(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		try {
			JSONObject json = service.getWorkTrack(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}

}
