package com.shareworx.ezfm.app.sqlite.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.sqlite.service.AppCreateDBSqliteService;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;

/**
 * 通过 sqlite 下载需要的 fmdata数据
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/datadown/sqlite")
public class AppCreateDBSqliteAction {

	final static Logger logger = Logger.getLogger(AppCreateDBSqliteAction.class);
	
	@Autowired
	@Qualifier(AppCreateDBSqliteService.ID)
	private AppCreateDBSqliteService service;
	
	/**
	 * 下载基础数据 打包成 sqlite 巡检保养得
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "fmdata", method = RequestMethod.GET)
	public @ResponseBody JSONObject doUpdateBaseDataDown(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();
		try {
			JSONObject ret = service.updateDataDB();
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, ret, start));
			return ret;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
	
	
	/**
	 * 下载基础数据 打包成 sqlite 巡检保养得
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "manualdown", method = RequestMethod.GET)
	public @ResponseBody JSONObject downdata(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();
		try {
			JSONObject ret = new JSONObject();
			service.createSqliteDB();
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, ret, start));
			ret.put("result", "手动下载成功！");
			return ret;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
}
