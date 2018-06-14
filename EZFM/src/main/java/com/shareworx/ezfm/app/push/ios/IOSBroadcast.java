package com.shareworx.ezfm.app.push.ios;
import com.shareworx.ezfm.app.push.IOSNotification;
/**
 * IOS 广播(broadcast): 向安装该App的所有设备发送消息
 * @author lingwei.li
 *
 */
public class IOSBroadcast extends IOSNotification {
	public IOSBroadcast(String appkey,String appMasterSecret) throws Exception {
			setAppMasterSecret(appMasterSecret);
			setPredefinedKeyValue("appkey", appkey);
			this.setPredefinedKeyValue("type", "broadcast");	
		
	}
}
