package com.shareworx.ezfm.app.push;

import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

import com.shareworx.ezfm.app.push.android.AndroidBroadcast;
import com.shareworx.ezfm.app.push.android.AndroidCustomizedcast;
import com.shareworx.ezfm.app.push.android.AndroidFilecast;
import com.shareworx.ezfm.app.push.android.AndroidGroupcast;
import com.shareworx.ezfm.app.push.android.AndroidListcast;
import com.shareworx.ezfm.app.push.android.AndroidUnicast;
import com.shareworx.ezfm.app.push.ios.IOSBroadcast;
import com.shareworx.ezfm.app.push.ios.IOSCustomizedcast;
import com.shareworx.ezfm.app.push.ios.IOSFilecast;
import com.shareworx.ezfm.app.push.ios.IOSGroupcast;
import com.shareworx.ezfm.app.push.ios.IOSListcast;
import com.shareworx.ezfm.app.push.ios.IOSUnicast;
import com.shareworx.ezfm.app.push.modelVo.AndroidModelVo;
import com.shareworx.ezfm.app.push.modelVo.IOSModelVo;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.SysConfigurationReadUtil;

/**
 * 友盟推送主要的 方法 类
 * @author lingwei.li
 *
 */
public class PushMethod {

	//安卓和苹果推送的key和
	private static String ios_appkey = "";
	private static String ios_appMasterSecret = "";
	private static String android_appkey = "";
	private static String android_appMasterSecret = "";
	private static String isTestStr = "";
	
	static {
		try {
			ios_appkey = SysConfigurationReadUtil.getInstance().getConfigItem(
					"ios_appkey");
			ios_appMasterSecret = SysConfigurationReadUtil.getInstance().getConfigItem(
					"ios_appMasterSecret");
			android_appkey = SysConfigurationReadUtil.getInstance().getConfigItem(
					"android_appkey");
			android_appMasterSecret = SysConfigurationReadUtil.getInstance().getConfigItem(
					"android_appMasterSecret");
			isTestStr = SysConfigurationReadUtil.getInstance().getConfigItem(
					"isTestStr");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	//是否是测试 
	static Boolean isTest = true;
	static{
		if ("false".equals(isTestStr)) {
			isTest =false;
		}
	}
	
	@SuppressWarnings("unused")
	private String timestamp = null;
	
	//ios 会用到的两个 int 值
	private static Integer default_badge = 0;
	//private Integer default_content_available = 0;
	
	//友盟的 主要 发送 类
	private static PushClient client = new PushClient();
	
	//==============================Android start======================================
	
	/**
	 * Android 广播(broadcast): 向安装该App的所有设备发送消息
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendAndroidBroadcast(AndroidModelVo aModelVo) throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast(android_appkey,android_appMasterSecret);
		broadcast.setTicker(aModelVo.getTicker());
		broadcast.setTitle(aModelVo.getTitle());
		broadcast.setText(aModelVo.getText());
		broadcast.goAppAfterOpen();
		broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		if (isTest) {
			broadcast.setTestMode();
		}else{
			broadcast.setProductionMode();
		}
		broadcast.setExtraField(aModelVo.getExtra_field_key(),aModelVo.getExtra_field_value());
		client.send(broadcast);
	}
	
	/**
	 * Android 单播(unicast): 向指定的设备发送消息，包括向单个device_token或者单个alias发消息。
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendAndroidUnicast(AndroidModelVo aModelVo) throws Exception {
		Long timestamp = new Date().getTime();
		AndroidUnicast unicast = new AndroidUnicast(android_appkey,android_appMasterSecret);
		//设置自己的 device token  如果是多个 用 ，隔开  
		unicast.setDeviceToken(aModelVo.getDevice_token());
		unicast.setTicker(aModelVo.getTicker());
		unicast.setTitle(aModelVo.getTitle());
		unicast.setText(aModelVo.getText());
		unicast.setTimestamp(timestamp);
		unicast.setSound("");
		unicast.goAppAfterOpen();
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		unicast.setExtraField(aModelVo.getExtra_field_map());
		//正式模式 用  setProductionMode 如果测试 用 setTestMode 
		if (isTest) {
			unicast.setTestMode();
		}else{
			unicast.setProductionMode();
		}
		client.send(unicast);
	}
	
	/**
	 * Android 单播(unicast): 向指定的设备发送消息，包括向单个device_token或者单个alias发消息。
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendAndroidListcast(AndroidModelVo aModelVo) throws Exception {
		AndroidListcast listcast = new AndroidListcast(android_appkey,android_appMasterSecret);
		//设置自己的 device token  如果是多个 用 ，隔开  
		listcast.setDeviceToken(aModelVo.getDevice_token());
		listcast.setTicker(aModelVo.getTicker());
		listcast.setTitle(aModelVo.getTitle());
		listcast.setText(aModelVo.getText());
		listcast.goAppAfterOpen();
		listcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		listcast.setExtraField(aModelVo.getExtra_field_map());
		//正式模式 用  setProductionMode 如果测试 用 setTestMode 
		if(isTest){
			listcast.setTestMode();
		}else{
			listcast.setProductionMode();
		}
		client.send(listcast);
	}
	
	/**
	 * Android 组播(groupcast): 向满足特定条件的设备集合发送消息， 
	 * 例如: "特定版本"、"特定地域"等.
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendAndroidGroupcast(AndroidModelVo aModelVo) throws Exception {
		AndroidGroupcast groupcast = new AndroidGroupcast(android_appkey,android_appMasterSecret);
		/*  TODO 待完善
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
    	 *		"and": 
    	 *		[
      	 *			{"tag":"test"},
      	 *			{"tag":"Test"}
    	 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		JSONObject TestTag = new JSONObject();
		testTag.put("tag", "test");
		TestTag.put("tag", "Test");
		tagArray.put(testTag);
		tagArray.put(TestTag);
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		
		groupcast.setFilter(filterJson);
		groupcast.setTicker(aModelVo.getTicker());
		groupcast.setTitle(aModelVo.getTitle());
		groupcast.setText(aModelVo.getText());
		groupcast.goAppAfterOpen();
		groupcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		//正式模式 用  setProductionMode 如果测试 用 setTestMode 
		if(isTest){
			groupcast.setTestMode();
		}else{
			groupcast.setProductionMode();
		}
		client.send(groupcast);
	}
	
	/**
	 * Android 自定义播(customizedcast): 
	 * 开发者通过自有的alias进行推送, 
	 * 可以针对单个或者一批alias进行推送，
	 * 也可以将alias存放到文件进行发送
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendAndroidCustomizedcast(AndroidModelVo aModelVo) throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(android_appkey,android_appMasterSecret);
		/*
		 * 待办事项设置您的别名，并用逗号分开他们,
		 * 如果有很多别名，也可以上传一个包含这些别名的文件,
		 * 然后使用file_id发送定制的通知.
		 */
		customizedcast.setAlias(aModelVo.getAlias(), aModelVo.getAlias_type());
		customizedcast.setTicker(aModelVo.getTicker());
		customizedcast.setTitle(aModelVo.getTitle());
		customizedcast.setText(aModelVo.getText());
		customizedcast.goAppAfterOpen();
		customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		//正式模式 用  setProductionMode 如果测试 用 setTestMode 
		if(isTest){
			customizedcast.setTestMode();
		}else{
			customizedcast.setProductionMode();
		}
		client.send(customizedcast);
	}
	
	/**
	 * Android 自定义播(customizedcast): 
	 * 将alias存放到文件进行发送
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendAndroidCustomizedcastFile(AndroidModelVo aModelVo) throws Exception {
		AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(android_appkey,android_appMasterSecret);
		//TODO 先通过文件上传接口获取文件id
		String fileId = client.uploadContents(android_appkey,android_appMasterSecret,"aa"+"\n"+"bb"+"\n"+"alias");
		//再通过文件方式发送消息
		customizedcast.setFileId(fileId, aModelVo.getAlias_type());
		customizedcast.setTicker(aModelVo.getTicker());
		customizedcast.setTitle(aModelVo.getTitle());
		customizedcast.setText(aModelVo.getText());
		customizedcast.goAppAfterOpen();
		customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		//正式模式 用  setProductionMode 如果测试 用 setTestMode 
		if (isTest) {
			customizedcast.setTestMode();
		}else{
			customizedcast.setProductionMode();
		}
		
		client.send(customizedcast);
	}
	
	/**
	 * Android 文件播(filecast): 开发者将批量的device_token或者alias存放到文件, 通过文件ID进行消息发送。
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendAndroidFilecast(AndroidModelVo aModelVo) throws Exception {
		AndroidFilecast filecast = new AndroidFilecast(android_appkey,android_appMasterSecret);
		// TODO upload your device tokens, and use '\n' to split them if there are multiple tokens 
		String fileId = client.uploadContents(android_appkey,android_appMasterSecret,"aa"+"\n"+"bb");
		filecast.setFileId( fileId);
		filecast.setTicker(aModelVo.getTicker());
		filecast.setTitle(aModelVo.getTitle());
		filecast.setText(aModelVo.getText());
		filecast.goAppAfterOpen();
		filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		client.send(filecast);
	}
	
	//==============================Android end================================
	
	//==============================IOS start================================
	
	/**
	 * IOS 广播(broadcast): 向安装该App的所有设备发送消息
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendIOSBroadcast(IOSModelVo iModel) throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast(ios_appkey,ios_appMasterSecret);
		broadcast.setAlert(iModel.getAlert());
		if (!AppEmptyUtils.isEmpty(iModel.getBadge())) {
			default_badge = Integer.valueOf(iModel.getBadge());
		}
		broadcast.setBadge(default_badge);
		broadcast.setSound(iModel.getSound());
		//正式模式 用  setProductionMode 如果测试 用 setTestMode 
		if (isTest) {
			broadcast.setTestMode();
		}else{
			broadcast.setProductionMode();
		}
		
		broadcast.setCustomizedField(iModel.getCustomized_field_key(), 
				iModel.getCustomized_field_value());
		
		//ios自定义需要添加的参数
		broadcast.setPmModule(iModel.getPm_module());
		broadcast.setPmFunction(iModel.getPm_function());
		broadcast.setPmExt(iModel.getPm_ext());
		
		client.send(broadcast);
	}
	
	/**
	 * IOS 单播(unicast): 向指定的设备发送消息，包括向单个device_token或者单个alias发消息。
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendIOSUnicast(IOSModelVo iModel) throws Exception {
		IOSUnicast unicast = new IOSUnicast(ios_appkey,ios_appMasterSecret);
		unicast.setDeviceToken(iModel.getDevice_token());
		unicast.setAlert(iModel.getAlert());
		if (!AppEmptyUtils.isEmpty(iModel.getBadge())) {
			default_badge = Integer.valueOf(iModel.getBadge());
		}
		unicast.setBadge(default_badge);
		unicast.setSound(iModel.getSound());
		//正式模式 用  setProductionMode 如果测试 用 setTestMode 
		if (isTest) {
			unicast.setTestMode();
		}else{
			unicast.setProductionMode();
		}
		
		// Set customized fields
		unicast.setCustomizedField(iModel.getCustomized_field_key(), 
				iModel.getCustomized_field_value());
		
		//ios自定义需要添加的参数
		unicast.setPmModule(iModel.getPm_module());
		unicast.setPmFunction(iModel.getPm_function());
		unicast.setPmExt(iModel.getPm_ext());
		
		client.send(unicast);
	}
	
	/**
	 * IOS 单播(unicast): 向指定的设备发送消息，包括向单个device_token或者单个alias发消息。
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendIOSListcast(IOSModelVo iModel) throws Exception {
		IOSListcast listcast = new IOSListcast(ios_appkey,ios_appMasterSecret);
		listcast.setDeviceToken(iModel.getDevice_token());
		listcast.setAlert(iModel.getAlert());
		if (!AppEmptyUtils.isEmpty(iModel.getBadge())) {
			default_badge = Integer.valueOf(iModel.getBadge());
		}
		listcast.setBadge(default_badge);
		listcast.setSound(iModel.getSound());
		//正式模式 用  setProductionMode 如果测试 用 setTestMode 
		if (isTest) {
			listcast.setTestMode();
		}else{
			listcast.setProductionMode();
		}
		// Set customized fields
		listcast.setCustomizedField(iModel.getCustomized_field_key(), 
				iModel.getCustomized_field_value());
		
		//ios自定义需要添加的参数
		listcast.setPmModule(iModel.getPm_module());
		listcast.setPmFunction(iModel.getPm_function());
		listcast.setPmExt(iModel.getPm_ext());
		
		client.send(listcast);
	}
	
	/**
	 * IOS 组播(groupcast): 向满足特定条件的设备集合发送消息， 例如: "特定版本"、"特定地域"等。
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendIOSGroupcast(IOSModelVo iModel) throws Exception {
		IOSGroupcast groupcast = new IOSGroupcast(ios_appkey,ios_appMasterSecret);
		/*  TODO
		 *  Construct the filter condition:
		 *  "where": 
		 *	{
    	 *		"and": 
    	 *		[
      	 *			{"tag":"iostest"}
    	 *		]
		 *	}
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		JSONObject testTag = new JSONObject();
		testTag.put("tag", "iostest");
		tagArray.put(testTag);
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());
		
		// Set filter condition into rootJson
		groupcast.setFilter(filterJson);
		groupcast.setAlert(iModel.getAlert());
		if (!AppEmptyUtils.isEmpty(iModel.getBadge())) {
			default_badge = Integer.valueOf(iModel.getBadge());
		}
		groupcast.setBadge(default_badge);
		groupcast.setSound(iModel.getSound());
		//正式模式 用  setProductionMode 如果测试 用 setTestMode 
		if (isTest) {
			groupcast.setTestMode();
		}else{
			groupcast.setProductionMode();
		}
		
		
		//ios自定义需要添加的参数
		groupcast.setPmModule(iModel.getPm_module());
		groupcast.setPmFunction(iModel.getPm_function());
		groupcast.setPmExt(iModel.getPm_ext());
		
		client.send(groupcast);
	}
	
	/**
	 * IOS 自定义播(customizedcast): 
	 * 开发者通过自有的alias进行推送, 
	 * 可以针对单个或者一批alias进行推送，
	 * 也可以将alias存放到文件进行发送。
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendIOSCustomizedcast(IOSModelVo iModel) throws Exception {
		IOSCustomizedcast customizedcast = new IOSCustomizedcast(ios_appkey,ios_appMasterSecret);
		/*
		 * 待办事项设置您的别名，并用逗号分开他们,
		 * 如果有很多别名，也可以上传一个包含这些别名的文件,
		 * 然后使用file_id发送定制的通知.
		 */
		customizedcast.setAlias(iModel.getAlias(), iModel.getAlias_type());
		customizedcast.setAlert(iModel.getAlert());
		if (!AppEmptyUtils.isEmpty(iModel.getBadge())) {
			default_badge = Integer.valueOf(iModel.getBadge());
		}
		customizedcast.setBadge(default_badge);
		customizedcast.setSound(iModel.getSound());
		//正式模式 用  setProductionMode 如果测试 用 setTestMode 
		if (isTest) {
			customizedcast.setTestMode();
		}else{
			customizedcast.setProductionMode();
		}
		
		
		//ios自定义需要添加的参数
		customizedcast.setPmModule(iModel.getPm_module());
		customizedcast.setPmFunction(iModel.getPm_function());
		customizedcast.setPmExt(iModel.getPm_ext());
		
		client.send(customizedcast);
	}
	
	/**
	 * IOS 文件播(filecast): 开发者将批量的device_token或者alias存放到文件, 通过文件ID进行消息发送。
	 * @param pushModel
	 * @throws Exception
	 */
	public static void sendIOSFilecast(IOSModelVo iModel) throws Exception {
		IOSFilecast filecast = new IOSFilecast(ios_appkey,ios_appMasterSecret);
		// TODO upload your device tokens, and use '\n' to split them if there are multiple tokens 
		String fileId = client.uploadContents(ios_appkey,ios_appMasterSecret,"aa"+"\n"+"bb");
		filecast.setFileId( fileId);
		filecast.setAlert(iModel.getAlert());
		if (!AppEmptyUtils.isEmpty(iModel.getBadge())) {
			default_badge = Integer.valueOf(iModel.getBadge());
		}
		filecast.setBadge(default_badge);
		filecast.setSound(iModel.getSound());
		//正式模式 用  setProductionMode 如果测试 用 setTestMode 
		if (isTest) {
			filecast.setTestMode();
		}else{
			filecast.setProductionMode();
		}
		//ios自定义需要添加的参数
		filecast.setPmModule(iModel.getPm_module());
		filecast.setPmFunction(iModel.getPm_function());
		filecast.setPmExt(iModel.getPm_ext());
		
		client.send(filecast);
	}
	
}
