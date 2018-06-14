package com.shareworx.ezfm.app.push.service;

import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;
import com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel;

public interface AppPushService {

	String ID = "appPushService";
	
	
	/**
	 * 工单推送
	 * @param obj (YJWYWorkTaskDetailsModel)
	 */
	void sendWorkTaskPush(YJWYWorkTaskDetailsModel obj);
	
	/**
	 * 任务推送
	 * @param inspectTaskModel
	 * @param type (1:待整改；2:整改确认)
	 */
	void sendInspectTaskPush(InspectTaskModel inspectTaskModel,String type);
	
	/**
	 * 报事推送
	 * @param probelmModel
	 */
	void sendProbelmPush(YJWYProblemDetailsModel probelmModel);
	
}
