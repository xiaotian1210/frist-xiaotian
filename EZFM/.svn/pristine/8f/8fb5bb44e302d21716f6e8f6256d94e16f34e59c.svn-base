package com.shareworx.ezfm.app.push.android;
import com.shareworx.ezfm.app.push.AndroidNotification;
/**
 * Android 广播(broadcast): 向安装该App的所有设备发送消息
 * @author lingwei.li
 *
 */
public class AndroidBroadcast extends AndroidNotification {
	public AndroidBroadcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
	}
}
