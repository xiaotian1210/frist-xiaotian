package com.shareworx.ezfm.login.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.platform.mvc.Session;
import com.shareworx.platform.mvc.SessionContext;
import com.shareworx.platform.mvc.SessionFactory;
import com.shareworx.platform.mvc.ThreadLocalContextHolder;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.dao.YJWYUserDao;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.usermobile.model.YJWYUserMobileModel;
import com.shareworx.ezfm.baseinfo.usermobile.service.YJWYUserMobileDomainService;
import com.shareworx.ezfm.login.commons.APPResultMsgCommons;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.utils.UserUtil;

@Service(IAPPLoginAuthorizationService.ID)
public class APPLoginAuthorizationServiceImp implements IAPPLoginAuthorizationService {
	@Autowired
	@Qualifier(YJWYUserDao.ID)
	private YJWYUserDao userDao;
	
	@Autowired
	@Qualifier(SessionFactory.ID)
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	@Autowired
	@Qualifier(YJWYUserMobileDomainService.ID)
	private YJWYUserMobileDomainService mobileDomainService;
	
	public void setMobileDomainService(YJWYUserMobileDomainService mobileDomainService) {
		this.mobileDomainService = mobileDomainService;
	}
	/**
	 * 手机用户登录验证
	 */
	@Override
	public Integer loginValid(YJWYUserModel loginModel,YJWYUserModel dbModel){
		if(dbModel == null){
			return LoginCommons.USERNAMEERROR;
		}else if(!dbModel.getIs_able()){
			return LoginCommons.USERUSABLEERROR;
		}else if(!dbModel.getPassword().equals(StringUtils.md5(loginModel.getPassword()))){
			return LoginCommons.PASSWORDERROR;
		}else{
			return LoginCommons.SUCCESSCODE;
		}
	}

	/**
	 * 登录service操作
	 */
	@Override
	public Integer doLogin(YJWYUserModel loginModel, HttpServletRequest request, HttpServletResponse response) {
		Query query = Query.from(YJWYUserModel.META_ID).and(Condition.create("user_code_").eq(loginModel.getUser_code()));
		YJWYUserModel dbModel = userDao.queryOneByCondition(query);
		int validCode = loginValid(loginModel, dbModel);
		if(!LoginCommons.SUCCESSCODE.equals(validCode)){
			return validCode;
		}
		if(sessionFactory.alreadyLogin(dbModel.getUser_code(), LoginCommons.DOMAINAPP)){
			sessionFactory.rejectLogin(dbModel.getUser_code(), LoginCommons.DOMAINAPP);
		}
		//清空前面的cookie
		Cookie[] cookies = request.getCookies();
		if(cookies!=null && cookies.length>0){
			for(Cookie cookie : cookies){
				cookie.setMaxAge(0);
			}
		}
		String sessionId = sessionFactory.createSession(dbModel.getUser_code(), LoginCommons.DOMAINAPP);
		dbModel.setAttribute("token", sessionId);
		if(StringUtils.isEmpty(sessionId)){
			return LoginCommons.LOGINERROR;
		}
		//记录缓存
		Session session = sessionFactory.getSession(sessionId);
		session.setPk_user(dbModel.getPk_user());
		session.setUserObject(dbModel);
		session.setRemoteHost(request.getRemoteHost());
		sessionFactory.updateSession(sessionId, session);
		SessionContext context = new SessionContext(sessionId);
		ThreadLocalContextHolder.setContext(context);
		//记录cookie
		Cookie cookie = new Cookie(APPResultMsgCommons.LOGINCOOKIENAME, sessionId);
		cookie.setMaxAge(2*7*24*60*60);
		response.addCookie(cookie);
		Cookie cookie2 = new Cookie(APPResultMsgCommons.LOGINUSERNAME, dbModel.getUser_code());
		cookie2.setMaxAge(2*7*24*60*60);
		response.addCookie(cookie2);
		return LoginCommons.SUCCESSCODE;
	}
	/**
	 * 用户退出或注销
	 */
	@Override
	public void exitLogin(HttpServletRequest request){
		String sessionId="";
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies){
			if(APPResultMsgCommons.LOGINCOOKIENAME.equals(cookie.getName())){
				sessionId = cookie.getValue();
				break;
			}
		}
		//清空缓存
		SessionFactory sessionFactory = SpringUtils.getBean(SessionFactory.ID);
		sessionFactory.removeSession(sessionId);
		//清空cookie
		for(Cookie cookie : cookies){
			cookie.setMaxAge(0);
		}
	}
	
	/**
	 * 判断该账号是否已经登录
	 * @param user
	 * @return
	 */
	@Override
	public boolean isAlreadyLogin(String userCode) {
		return sessionFactory.alreadyLogin(userCode, LoginCommons.DOMAINAPP);
	}
	
	/**
	 * 判断是否本人登录
	 * @param user
	 * @return
	 */
	@Override
	public boolean isAlreadyLoginForSelf(String sessionId) {
		Session session = sessionFactory.getSession(sessionId);
		if(null!=session && sessionId.equals(session.getToken())){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 判断是否本人登录
	 * @param user
	 * @return
	 */
	@Override
	public boolean isAlreadyLoginForSelf(HttpServletRequest request) {
		String sessionId = "";
		Cookie[] cookies = request.getCookies();
		if(null==cookies||cookies.length<1){
			return false;
		}
		for(Cookie cookie:cookies){
			if(APPResultMsgCommons.LOGINCOOKIENAME.equals(cookie.getName())){
				sessionId = cookie.getValue();
			}
		}
		return isAlreadyLoginForSelf(sessionId);
	}
	//登录成功 返回信息
	@Override
	public Map<String,Object> loginResult(String username){
		//Query query = Query.from(YJWYUserModel.META_ID).and(Condition.create("user_code_").eq(username));
		//YJWYUserModel dbModel = userDao.queryOneByCondition(query);
		YJWYUserModel user = UserUtil.getCurrentUser();
		Map<String,Object> map = new HashMap<>();
		map.put("pk_user",user.getPk_user());
		map.put("user_name", user.getUser_name());
		map.put("user_code", user.getUser_code());
		map.put("header_img", user.getHeader_img());
		map.put("pk_crop", user.getPk_crop());
		map.put("is_sign", user.getIs_sign());
		
		String sql = "select IFNULL(GROUP_CONCAT(dict_code_ SEPARATOR ','),'') 'dict_code' from yjwy_pub_dict where dict_parent_ = 'AppPermission' and pk_dict_ in (select pk_func_ from yjwy_app_role_func where pk_crop_='"+user.getPk_crop()+"' and  pk_role_ in (select pk_role_ from yjwy_pub_role_user where  pk_crop_='"+user.getPk_crop()+"' and pk_user_  = '"+user.getPk_user()+"') ) ;";
		JdbcTemplate read = dao.getReadTemplate();
		List<Map<String,Object>> objList = read.queryForList(sql);
		if(objList.size()>0){
			map.put("AppPermission",objList.get(0).get("dict_code"));
		}
		return map;
	}
	//验证更新用户设备信息
	@Override
	public void validMobile(String mobileId,String mobilePlatform){
		YJWYUserModel user = UserUtil.getCurrentUser();
		YJWYUserMobileModel model = mobileDomainService.queryOneByCondition(Query.from(YJWYUserMobileModel.META_ID).and(Condition.create("fk_user", user.getPk_user())));
		YJWYUserMobileModel model1 = mobileDomainService.queryOneByCondition(Query.from(YJWYUserMobileModel.META_ID).and(Condition.create("mobile_id", mobileId)));
		
		//先判断是否存在该设备号
		if(null==model1){
			//用户和设备都不存在，保存一条
			if(null==model){
				YJWYUserMobileModel userMobile = new YJWYUserMobileModel();
				userMobile.setFk_user(user.getPk_user());
				userMobile.setMobile_id(mobileId);
				userMobile.setPk_crop(user.getPk_crop());
				userMobile.setMobile_platform(mobilePlatform);
				mobileDomainService.save(new YJWYUserMobileModel[]{userMobile});
			/**
			 * 已经存在用户和设备关联model，
			 * 判断当前设备是否与model的设备相同，
			 * 不相同则更新model，并且model1必须为空，如果不为空，删除model1；
			 * 相同则忽略model，并且model1必须为空，如果不为空，删除model1；
			 */
			}else{
				//判断model的设备是否存在
				if(!mobileId.equals(model.getPk_usermobile())){
					model.setMobile_id(mobileId);
					model.setMobile_platform(mobilePlatform);
					mobileDomainService.update(new YJWYUserMobileModel[]{model});
				}
			}
		}else{
			if(null==model){
				//删除model1
				mobileDomainService.delete(new YJWYUserMobileModel[]{model1});
				//TODO   model=null时有问题；
				YJWYUserMobileModel userMobile = new YJWYUserMobileModel();
				userMobile.setFk_user(user.getPk_user());
				userMobile.setMobile_id(mobileId);
				userMobile.setPk_crop(user.getPk_crop());
				userMobile.setMobile_platform(mobilePlatform);
				mobileDomainService.save(new YJWYUserMobileModel[]{userMobile});
			}else if(!model.getFk_user().equals(model1.getFk_user())){
				if(!mobileId.equals(model.getPk_usermobile())){
					model.setMobile_id(mobileId);
					model.setMobile_platform(mobilePlatform);
					mobileDomainService.update(new YJWYUserMobileModel[]{model});
				}
			}
		}
		
		
	}
}
