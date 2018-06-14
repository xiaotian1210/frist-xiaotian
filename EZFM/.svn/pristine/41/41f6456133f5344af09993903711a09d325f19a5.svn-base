package com.shareworx.ezfm.app.worktask.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.app.util.AppMessageFormatUtil;
import com.shareworx.ezfm.app.worktask.service.AppWorkTaskPushService;

/**
 * 工单超时推送 action
 * @author lingwei.li
 *
 */
@Controller
@RequestMapping("app/workTaskPush")
public class AppWorkTaskPushAction {

	final static Logger logger = Logger.getLogger(AppWorkTaskPushAction.class);
	@Autowired
	@Qualifier( AppWorkTaskPushService.ID)
	private AppWorkTaskPushService wtPushService;
	
	/**
	 * 保存文件上传信息
	 * @param reqParam
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "push", method = RequestMethod.POST)
	public @ResponseBody JSONObject push(HttpServletRequest reqParam) throws Exception{
		long start = System.currentTimeMillis();//当前时间戳
		try {
			wtPushService.notOrdersOverTimePush();
			wtPushService.repairOverTimePush();
			JSONObject json = new JSONObject();
			json.put("code", 0);
			logger.info(AppMessageFormatUtil.loggerInfo(reqParam, json, start));
			return json;
		} catch (Exception e) {
			logger.info(AppMessageFormatUtil.loggerError(reqParam, e));
			throw(e);
		}
		
	}
}
