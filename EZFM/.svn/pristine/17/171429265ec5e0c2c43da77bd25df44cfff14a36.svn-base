package com.shareworx.ezfm.webservice.eba.out;

/**
 * eba设备预警消除接口
 * 
 * 接口地址：http://222.180.250.162:9755/AlarmService.asmx
 * 响应信息格式：{"Result":2,"Content":"告警单元不存在!"}
 * {"Result":0,"Content":"提交成功"}
 * @author jin.li
 * 
 */
public interface EbaWarningEliminateService {
	String ID = "ebaWarningEliminateService";

	/**
	 * eba设备预警消除方法
	 * @param reportId 预警消息id
	 * @return true 消除成功
	 */
	boolean EbaWarningEliminate(String reportId);
}
