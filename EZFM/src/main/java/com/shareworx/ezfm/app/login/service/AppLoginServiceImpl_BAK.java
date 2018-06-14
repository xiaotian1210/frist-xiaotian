package com.shareworx.ezfm.app.login.service;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.mvc.SessionFactory;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.ezfm.app.quality.service.AppInspectTaskServiceImpl;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.baseinfo.usermobile.dao.YJWYUserMobileDao;
import com.shareworx.ezfm.login.service.IAPPLoginAuthorizationService;
import com.shareworx.ezfm.login.service.IYJWYLoginAuthorizationService;
/**
 * app端 登录认证实现
 * @author lingwei.li
 *
 */
@Service(AppLoginService_BAK.ID)
public class AppLoginServiceImpl_BAK implements AppLoginService_BAK{

	final static Logger logger = Logger.getLogger(AppLoginServiceImpl_BAK.class);
	private JdbcTemplate readJdbcTemplate = DatabaseConnections.getReadTemplate();
	String msg = "参数错误";
	@Autowired
	@Qualifier(YJWYUserMobileDao.ID)
	private YJWYUserMobileDao userMobileDao;
	
	@Autowired
	@Qualifier(IAPPLoginAuthorizationService.ID)
	private IAPPLoginAuthorizationService appLoginAut;
	
	@Autowired
	@Qualifier(SessionFactory.ID)
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	
	@Autowired
	@Qualifier(IYJWYLoginAuthorizationService.ID)
	private IYJWYLoginAuthorizationService service;
	
	/**
	 * 获取用户登录信息
	 */
	@Override
	public JSONObject getLoginInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String username = request.getParameter("username");
		String sessionId = request.getParameter("sessionId");
		String isForceLogin = request.getParameter("isForceLogin"); //是否强行登录 1：是；0：否
		
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		
		//判断是否强行登录
		if ("1".equals(isForceLogin)) {
			jsonMap = getLoginInfoStatic(request, response);
		}else{
			//判断是否登录
			Boolean isLogining = appLoginAut.isAlreadyLogin(username);
			if (isLogining) {
				//是否是本人登录
				Boolean isLoginForSelf = appLoginAut.isAlreadyLoginForSelf(sessionId);
				if (isLoginForSelf) {
					msg = "已登录！";
					return AppJsonMessage.toJsonMsgFalse(2,msg);
				}else{
					msg = "已有设备登录该账号！";
					return AppJsonMessage.toJsonMsgFalse(2,msg);
				}
			}else{
				jsonMap = getLoginInfoStatic(request, response);
			}
		}
		
		if ((boolean) jsonMap.get("tOrf")) {
			return AppJsonMessage.toJsonMsgTrue(jsonMap.get("msg"));
		}else{
			return AppJsonMessage.toJsonMsgFalse(2, String.valueOf(jsonMap.get("msg")));
		}
		
	}

	/**
	 * 退出登录
	 */
	@Override
	public JSONObject logout(String sessionId) throws Exception {
		
		SessionFactory sessionFactory = SpringUtils.getBean(SessionFactory.ID);
		sessionFactory.removeSession(sessionId);
		
		return AppJsonMessage.toJsonMsg(0, "登出成功");
	}
	
	/**
	 * 登录操作
	 * @return
	 */
	public Map<String,Object> getLoginInfoStatic(HttpServletRequest request,HttpServletResponse response){
		return null;
		//页面传过来的参数
		/*String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		String mobileId = request.getParameter("mobileId");
		String mobilePlatform = request.getParameter("mobilePlatform");
		
		Map<String, Object> baseJsonMap = new HashMap<String,Object>();
		
		Map<String, Object> jsonMap = new HashMap<String,Object>();
		
		//判断必填参数
		if (AppEmptyUtils.isEmpty(username) 
				|| AppEmptyUtils.isEmpty(pwd)
				|| AppEmptyUtils.isEmpty(mobileId) 
				|| AppEmptyUtils.isEmpty(mobilePlatform)) {
			logger.info("result:error parameter");
			return AppJsonMessage.toJsonMsgFalse(1,msg);
		}
		
		//创建登录用户信息 并进行登录验证
		YJWYUserModel loginModel = new YJWYUserModel();	
		loginModel.setUser_code(username);
		loginModel.setPassword(pwd);
		String result = appLoginAut.doLogin(loginModel, request, response);

		switch (result) {
		case "201":
			msg = "密码错误";
			logger.info("result:error pwd is not right");
			jsonMap.put("tOrf", false);
			jsonMap.put("msg", msg);
			return jsonMap;
		case "404":
			msg = "用户不存在";
			logger.info("result:error user is not exist");
			jsonMap.put("tOrf", false);
			jsonMap.put("msg", msg);
			return jsonMap;
		case "405":
			msg = "用户不可用";
			logger.info("result:error user is not use");
			jsonMap.put("tOrf", false);
			jsonMap.put("msg", msg);
			return jsonMap;
		case "500":
			msg = "登录失败";
			logger.info("result:error login");
			jsonMap.put("tOrf", false);
			jsonMap.put("msg", msg);
			return jsonMap;
		}
		//通过session 获取 用户信息
		Session session = sessionFactory.getSession(result);
		System.out.println(result);
		YJWYUserModel dbModel = (YJWYUserModel) session.getUserObject();
		
		//清除cookie
		AppCookieUtil.clearCookie(request, response, "/app");
		
		//封装 cookie 信息
		//用户访问过之后重新设置用户的访问时间，存储到cookie中，然后发送到客户端浏览器
		//创建一个cookie，cookie的名字是lastAccessTime
		JSONObject json = new JSONObject();
		json.put("token", result);
		json.put("usercode", dbModel.getUser_code());
        Cookie cookie = new Cookie("appCookie", json.toJSONString());
        //将cookie对象添加到response对象中，这样服务器在输出response对象中的内容时就会把cookie也输出到客户端浏览器
        cookie.setPath("/app");
        response.addCookie(cookie);
		
		//根据条件返回 json
		String userId =dbModel.getPk_user(); //id
		String crop = dbModel.getPk_crop();//所属公司
		String backSql = "select ypu.pk_user_ as pk_user, "
				+ "ypu.user_name_ as user_name, "
				+ "ypu.user_code_ as user_code, "
				+ "ypu.header_img_ as header_img, "
				+ "ypu.pk_crop_ as pk_crop "
				+ "FROM yjwy_pub_user ypu "
				+ "where ypu.pk_user_ = '"+userId+"' and ypu.pk_crop_ ='"+crop+"'";
		baseJsonMap = readJdbcTemplate.queryForMap(backSql);
		
		//获取用户app权限 
		String appPer = "";
		String appRoleSql = "select "
				+ "IFNULL(GROUP_CONCAT(m.dict_code_ SEPARATOR ','),'') AS dict_code "
				+ "from "
					+ "(select dict_code_, '"+userId+"' as pk_user "
					+ "from yjwy_pub_dict "
					+ "where dict_parent_ = 'AppPermission' "
					+ "and pk_crop_ = '"+crop+"' "
					+ "and pk_dict_ in "
						+ "(select pk_func_ from yjwy_app_role_func "
							+ "where pk_crop_ = '"+crop+"' "
							+ "and pk_role_ in "
								+ "(select pk_role_ from yjwy_pub_role_user "
								+ " where pk_crop_ = '"+crop+"' "
								+ "and pk_user_ = '"+userId+"')"
						+ ")"
					+ ") m GROUP BY m.pk_user";
		
		Map<String, Object> appPerMap = readJdbcTemplate.queryForMap(appRoleSql);
		if (!AppEmptyUtils.isEmpty(appPerMap)) {
			appPer = appPer + appPerMap.get("dict_code");
		}
		
		baseJsonMap.put("AppPermission", appPer);
		
		//将用户的设备id 以及 用户id 存入 yjwy_pub_user_mobile 表中
		Query query = Query.from(YJWYUserMobileModel.META_ID);
		query.and(Condition.create("fk_user", userId));
        YJWYUserMobileModel userMobile = userMobileDao.queryOneByCondition(query);
        
        //判断userMobile 是否为空；如果是空，新增一条
        if (AppEmptyUtils.isEmpty(userMobile)) {
        	YJWYUserMobileModel newUserMobile = new YJWYUserMobileModel();
        	newUserMobile.setFk_user(userId);
        	newUserMobile.setMobile_id(mobileId);
        	newUserMobile.setPk_crop(crop);
        	newUserMobile.setMobile_platform(mobilePlatform);
        	int[] flag = userMobileDao.saveModels(new YJWYUserMobileModel[]{newUserMobile});
        	if (AppEmptyUtils.isEmpty(flag)) {
        		msg = "设备号存储失败";
    			logger.info("result:error mobileId save");
    			jsonMap.put("tOrf", false);
    			jsonMap.put("msg", msg);
    			return jsonMap;
			}
		}else{
			 //判断userMobile 是否为空；如果不是空，判断设备号以及设备类型是否匹配，如果不匹配，删除之前的，新增一条
			if (!mobileId.equals(userMobile.getMobile_id())
					|| !mobilePlatform.equals(userMobile.getMobile_platform())) {
				userMobileDao.deleteModels(new YJWYUserMobileModel[]{userMobile});
				
				YJWYUserMobileModel newUserMobile = new YJWYUserMobileModel();
	        	newUserMobile.setFk_user(userId);
	        	newUserMobile.setMobile_id(mobileId);
	        	newUserMobile.setPk_crop(crop);
	        	newUserMobile.setMobile_platform(mobilePlatform);
	        	int[] flag = userMobileDao.saveModels(new YJWYUserMobileModel[]{newUserMobile});
	        	if (AppEmptyUtils.isEmpty(flag)) {
	        		msg = "设备号存储失败";
	    			logger.info("result:error mobileId save");
	    			jsonMap.put("tOrf", false);
	    			jsonMap.put("msg", msg);
	    			return jsonMap;
				}
	        	
			}
		}
        logger.info("result:success");
        jsonMap.put("tOrf", true);
		jsonMap.put("msg", baseJsonMap);
		return jsonMap;*/
		
	}
	
}
