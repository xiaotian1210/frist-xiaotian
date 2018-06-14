package com.shareworx.ezfm.webservice.eba.inner;

import com.shareworx.platform.util.SpringUtils;
import com.shareworx.ezfm.device.warn.action.YJWYEbaWarningAction;

/**
 * 设备预警信息推送接收业务实现
 * 
 * @author jin.li
 *
 */
public class EbaWarningDataServiceImpl implements EbaWarningDataService {

	@Override
	public String getTest(String test) {
		return "接口测试" + test;
	}

	@Override
	public String pushWarningData(String reportId, String deviceCode, String warningContent, String warningValue, String warningTime, String crop_code, String eliminate_url) {
		YJWYEbaWarningAction action = SpringUtils.getBean(YJWYEbaWarningAction.class);
		return action.pushWarningData(reportId, deviceCode, warningContent, warningValue, warningTime, crop_code, eliminate_url);
	}

}
