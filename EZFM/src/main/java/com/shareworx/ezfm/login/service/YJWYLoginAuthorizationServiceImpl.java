package com.shareworx.ezfm.login.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.interceptor.YJWYWebAuthSessionListener;
import com.shareworx.platform.cache.CacheContents;
import com.shareworx.platform.cache.IDmsCacheManager;
import com.shareworx.platform.exception.BusinessException;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.mvc.Session;
import com.shareworx.platform.mvc.SessionContext;
import com.shareworx.platform.mvc.SessionFactory;
import com.shareworx.platform.mvc.ShareworxAuthencatinException;
import com.shareworx.platform.mvc.ThreadLocalContextHolder;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.dao.YJWYUserDao;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.utils.UserUtil;

@Service(IYJWYLoginAuthorizationService.ID)
public class YJWYLoginAuthorizationServiceImpl implements IYJWYLoginAuthorizationService {
	
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
	@Qualifier("cacheManager")
	private IDmsCacheManager cacheManager;
	
	public void setCacheManager(IDmsCacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	public YJWYUserModel loginValid(String username, String password, String domain) throws BusinessException{
		return loginValid(username, password, domain, true);
	}
	/*
	 * (non-Javadoc)
	 * @see com.dms.ezplatform.login.service.IAuthorizationService#loginValid(java.lang.String, java.lang.String, java.lang.String)
	 * changed by Jiazht 2015-4-8
	 * 首先验证是否为系统用户，如果是直接返回，如果不是则验证普通用户
	 */
	public YJWYUserModel loginValid(String username, String password, String domain, boolean validatePassword) throws BusinessException{
		if(username.equals(LoginCommons.QYUSERCODE) && password.equals(LoginCommons.QYUSERPASSWORD)){
			YJWYUserModel adminModel = new YJWYUserModel();
			adminModel.setPk_user(LoginCommons.QYUSERPK);
			adminModel.setUser_code(LoginCommons.QYUSERCODE);
			adminModel.setUser_name(LoginCommons.QYUSERNAME);
			adminModel.setPk_crop(LoginCommons.QY_CROP_PK);
			return adminModel;
		}
		if(username.equals(LoginCommons.DMSUSERCODE) && password.equals(LoginCommons.DMSUSERPASSWORD)){
			YJWYUserModel adminModel = new YJWYUserModel();
			adminModel.setPk_user(LoginCommons.DMSUSERPK);
			adminModel.setUser_code(LoginCommons.DMSUSERCODE);
			adminModel.setUser_name(LoginCommons.DMSUSERNAME);
			adminModel.setPk_crop(LoginCommons.DMS_CROP_PK);
			return adminModel;
		}
		Query query = Query.from(YJWYUserModel.META_ID).where(Condition.create("user_code_").eq(username)).or(Condition.create("phone_").eq(username));
		YJWYUserModel userModel = userDao.queryOneByCondition(query);
		if(userModel == null){
			throw new ShareworxAuthencatinException("用户不存在");
		}
		if(!userModel.getIs_able()){
			throw new ShareworxAuthencatinException("用户不可用");
		}
		if (validatePassword) {
			//使用MD5加密时放开
			if(!userModel.getPassword().equals(StringUtils.md5(password))){
				throw new ShareworxAuthencatinException("密码错误");
			}
		}
		return userModel;
	}

	/**
	 * 登录service操作
	 */
	@Override
	public ModelAndResult doLogin(YJWYUserModel model, HttpServletRequest request, HttpServletResponse response, boolean validatePassword) {
		//validate success
		YJWYUserModel userModel = loginValid(model.getUser_code(), model.getPassword(), null, validatePassword);
		if(sessionFactory.alreadyLogin(userModel.getUser_code(), LoginCommons.DOMAINSYS)){
			sessionFactory.rejectLogin(userModel.getUser_code(), LoginCommons.DOMAINSYS);
		}
		String sessionId = sessionFactory.createSession(userModel.getUser_code(), LoginCommons.DOMAINSYS);
		model.setAttribute("token", sessionId);
		if(StringUtils.isEmpty(sessionId)){
			throw new BusinessException("无法获取sessionId");
		}
		
		request.getSession().setMaxInactiveInterval(3600);
		request.getSession().setAttribute(YJWYWebAuthSessionListener.SYSSESSION_KEY, sessionId);
		
		Session session = sessionFactory.getSession(sessionId);
		userModel.setAttribute("logintime", DateTimeUtil.getTimestampStr());
		session.setPk_user(userModel.getPk_user());
		session.setUserObject(userModel);
		session.setRemoteHost(request.getRemoteHost());
		sessionFactory.updateSession(sessionId, session);
		/*免登录时使用Cookie cookie = new Cookie(YJWYWebAuthSessionListener.SYSSESSION_KEY, sessionId);
		cookie.setMaxAge(24*60*60);
		cookie.setPath("/yjwy");
		response.addCookie(cookie);*/
		SessionContext context = new SessionContext(sessionId);
		ThreadLocalContextHolder.setContext(context);
		ModelAndResult result = new ModelAndResult(model);
		return result;
	}
	/**
	 * 用户退出或注销
	 */
	@Override
	public void exitLogin(HttpSession httpSession){
		SessionFactory sessionFactory = SpringUtils.getBean(SessionFactory.ID);
		String userKey = UserUtil.getCurrentUser().getUser_code();
		sessionFactory.rejectLogin(userKey, LoginCommons.DOMAINSYS);
		httpSession.removeAttribute(YJWYWebAuthSessionListener.SYSSESSION_KEY);
	}
	
	//判断用户是否已经登录
	@Override
	public boolean isAlreadyLogin(YJWYUserModel user) {
		YJWYUserModel userModel = loginValid(user.getUser_code(), user.getPassword(),null,true);
		return sessionFactory.alreadyLogin(userModel.getUser_code(), LoginCommons.DOMAINSYS);
	}
	
	//判断本人是否已经登录
	@Override
	public boolean isSelfAlreadyLoginForSessionId(String sessionId) {
		if(StringUtils.isEmpty(sessionId)){
			return false;
		}
		Session session = sessionFactory.getSession(sessionId);
		if(null!=session && sessionId.equals(session.getToken())){
			return true;
		}else{
			return false;
		}
	}
	
	//判断本人是否已经登录
	@Override
	public boolean isSelfAlreadyLoginForCookie(HttpServletRequest request) {
		String sessionId = "";
		Cookie[] cookies = request.getCookies();
		if(null==cookies||cookies.length<1){
			return false;
		}
		for(Cookie cookie:cookies){
			if(YJWYWebAuthSessionListener.SYSSESSION_KEY.equals(cookie.getName())){
				sessionId = cookie.getValue();
			}
		}
		return isSelfAlreadyLoginForSessionId(sessionId);
	}
	//判断是否本人登录
	@Override
	public boolean isSelfAlreadyLoginForSession(HttpServletRequest request) {
		String sessionId = (String) (request.getSession()).getAttribute(YJWYWebAuthSessionListener.SYSSESSION_KEY);
		return isSelfAlreadyLoginForSessionId(sessionId);
	}
	
	//查询登录总数
	@Override
	public int queryLoginCount(){
		List<String> userKeys = cacheManager.getKeys(CacheContents.CACHE_USER_CONTEXT);
		if(null!=userKeys){
			return userKeys.size();
		}else{
			return 0;
		}
	}
	
	//查询aPP和系统的登录人数
	@Override
	public String queryLoginCountByClassify(){
		List<String> userKeys = cacheManager.getKeys(CacheContents.CACHE_USER_CONTEXT);
		
		if(null!=userKeys){
			int syscount = 0;
			int appcount = 0;
			for(String key : userKeys){
				if(key.contains(LoginCommons.DOMAINSYS)){
					syscount+=1;
				}else{
					appcount+=1;
				}
			}
			return "{syscount:"+syscount+",appcount:"+appcount+",amount:"+userKeys.size()+"}";
		}else{
			return "{syscount:0,appcount:0,amount:0}";
		}
	}
}
