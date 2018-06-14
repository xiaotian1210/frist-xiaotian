package com.shareworx.ezfm.login.commons;

public class LoginCommons {
	//系统和企业初始化登录账号信息
	public final static String QYUSERNAME = "超级系统管理员";
	public final static String QYUSERPASSWORD = "admin123";
	public final static String QYUSERCODE = "admin";
	public final static String QYUSERPK = "qyadmin";
	public final static String QY_CROP_PK = "qy";
	public final static String QY_CROP_NAME = "达美盛资产云企业";
	
	//DMS系统功能管理账号信息
	public final static String DMSUSERNAME = "DMS超级系统管理员";
	public final static String DMSUSERPASSWORD = "dms@888";
	public final static String DMSUSERCODE = "dmsadmin";
	public final static String DMSUSERPK = "dmsadmin";
	public final static String DMS_CROP_PK = "dms";
	public final static String DMS_CROP_NAME = "达美盛系统";
	
	//登录用户DOMAIN区分
	public final static String DOMAINSYS = "YJWYSYS";
	public final static String DOMAINAPP = "YJWYAPP";
	
	//SESSION失效
	public final static String HTTPSESSIONINVALID = "http_session_invalid";
	//用户被顶掉
	public final static String DMSSESSIONINVALID = "dms_session_invalid";
	
	//允许访问
	public final static String ACCESSALLOW = "200";
	
	//login登录URL
	public final static String LOGINURL = "/ezfm/login/index";
	//login主页URL
	public final static String HOMEURL = "/ezfm/home/index";
	//session失效URL
	public final static String SESSIONINVALIDURL = "/ezfm/help/session/invalid/index";

	//APP状态
	public final static Integer SUCCESSCODE = 200;//成功
	public final static Integer PASSWORDERROR = 201;//密码错误
	public final static Integer USERNAMEERROR = 202;//用户不存在
	public final static Integer USERUSABLEERROR= 405;//用户不可用
	public final static Integer LOGINERROR = 500;//登录失败
	public final static Integer USERSELFALREADYLOGIN= 100;//用户已在本机登录
	public final static Integer USEROTHORALREADYLOGIN=101;//用户已经在其他地点登录
	public final static Integer USERCOOKIEERROR=102;//获取用户信息(cookie)失败，请重新登录
}
