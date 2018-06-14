package com.shareworx.ezfm.app.versionmanage;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;
import com.shareworx.ezfm.app.util.SysConfigurationReadUtil;
/**
 * 版本管理
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/versionManage")
public class AppVersionManageAction {

	final static Logger logger = Logger.getLogger(AppVersionManageAction.class);
	
	static String ApkPath = "";
	static String ApkVersion = "";
	static String ApkName = "";
	static String ApkVersionDescription = "";
	
	static {
		try {
			ApkPath = SysConfigurationReadUtil.getInstance().getConfigItem(
					"ApkPath");
			ApkVersion = SysConfigurationReadUtil.getInstance().getConfigItem(
					"ApkVersion");
			ApkName = SysConfigurationReadUtil.getInstance().getConfigItem(
					"ApkName");
			ApkVersionDescription = SysConfigurationReadUtil.getInstance().getConfigItem(
					"ApkVersionDescription");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取最新版本
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "checkVersion", method = RequestMethod.GET)
	public @ResponseBody JSONObject workTaskPool(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis(); //当前时间戳
		
		try {
			JSONObject json = new JSONObject();
			json.put("version", ApkVersion);
			json.put("path", ApkPath+"/"+ApkName+".apk");
			json.put("content", ApkVersionDescription);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return AppJsonMessage.toJsonMsgTrue(json);
		} catch (Exception e) {
			logger.error(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
	}
}
