package com.shareworx.ezfm.app.push.android;
import com.shareworx.ezfm.app.push.AndroidNotification;
/**
 * Android 单播(unicast): 向指定的设备发送消息，包括向单个device_token或者单个alias发消息。
 * @author lingwei.li
 *
 */
public class AndroidListcast extends AndroidNotification {
	public AndroidListcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "listcast");	
	}
	
	public void setDeviceToken(String token) throws Exception {
    	setPredefinedKeyValue("device_tokens", token);
    }

}