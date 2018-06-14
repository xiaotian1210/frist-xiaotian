
package com.shareworx.ezfm.app.basedata.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.basedata.service.AppBaseDataDownService;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;

@Controller
@RequestMapping("app/basedata")
public class AppBaseDataDownAction {

	final static Logger logger = Logger.getLogger(AppBaseDataDownAction.class);
	
	@Autowired
	@Qualifier(AppBaseDataDownService.ID)
	private AppBaseDataDownService baseDataDownService;
	
	/**
	 * 下载基础数据
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "down", method = RequestMethod.GET)
	public @ResponseBody JSONObject doBaseDataDownAction(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();
		try {
			JSONObject ret = baseDataDownService.doDownData(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, ret, start));
			return ret;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	/**
	 * 下载基础数据
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "forUser", method = RequestMethod.GET)
	public @ResponseBody JSONObject doBaseDataForUser(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();
		try {
			JSONObject ret = baseDataDownService.doForUserData(reqParam);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, ret, start));
			return ret;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
}
