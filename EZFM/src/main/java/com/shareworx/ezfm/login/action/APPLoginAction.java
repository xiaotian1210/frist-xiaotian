package com.shareworx.ezfm.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.login.commons.APPResultMsgCommons;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.login.service.IAPPLoginAuthorizationService;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.action.InspectStandardAction;

@Controller
@RequestMapping(value="app/")
public class APPLoginAction {
	final static Logger log = Logger.getLogger(InspectStandardAction.class);
	
	@Autowired
	@Qualifier(IAPPLoginAuthorizationService.ID)
	private IAPPLoginAuthorizationService service;

	public void setService(IAPPLoginAuthorizationService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(value=YJWYUserDomainService.ID)
	private YJWYUserDomainService userDomainService;
		
	
	public void setUserDomainService(YJWYUserDomainService userDomainService) {
		this.userDomainService = userDomainService;
	}


	/**
	 * 登录操作
	 * @param req
	 * @param resp
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="login",method=RequestMethod.POST)
	public @ResponseBody JSONObject login(HttpServletRequest req,HttpServletResponse resp,@RequestParam String username,@RequestParam String pwd, @RequestParam String mobileId,@RequestParam String mobilePlatform ){
		YJWYUserModel user = new YJWYUserModel();
		user.setUser_code(username);
		user.setPassword(pwd);
		int code = service.doLogin(user, req, resp);
		String msg = "";
		JSONObject json = new JSONObject();
		json.put(APPResultMsgCommons.CODE, code);
		if(LoginCommons.SUCCESSCODE.equals(code)){
			msg = "用户登录成功";
			service.validMobile(mobileId, mobilePlatform);
			json.put("data", service.loginResult(username));
		}else if(LoginCommons.USERUSABLEERROR.equals(code)){
			msg = "用户不可用";
		}else{
			msg = "用户名或密码有误";
		}
		json.put(APPResultMsgCommons.MSG, msg);
		return json;
	}
	/**
	 * 判断用户是否已经登录或其他地点登录
	 * @param req
	 * @param resp
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="isalready/login",method=RequestMethod.POST)
	public @ResponseBody JSONObject isAlreadyLogin(HttpServletRequest req, HttpServletResponse resp,@RequestParam String username, @RequestParam String pwd){
		YJWYUserModel user = new YJWYUserModel();
		user.setUser_code(username);
		user.setPassword(pwd);
		boolean isAready = service.isAlreadyLogin(username);
		JSONObject json = new JSONObject();
		req.getCookies();
		if(service.isAlreadyLoginForSelf(req)){
			json.put(APPResultMsgCommons.CODE, LoginCommons.USERSELFALREADYLOGIN);
			json.put(APPResultMsgCommons.MSG, "用户已经在本机登录");
		}else if(isAready){
			json.put(APPResultMsgCommons.CODE, LoginCommons.USEROTHORALREADYLOGIN);
			json.put(APPResultMsgCommons.MSG, "用户已经在其他地点登录");
		}else{
			json.put(APPResultMsgCommons.CODE, LoginCommons.SUCCESSCODE);
			json.put(APPResultMsgCommons.MSG, "用户可以继续登录");
		}
		return json;
	}
	
	/**
	 * 退出操作
	 * @param pk_user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="exit",method=RequestMethod.GET)
	public @ResponseBody JSONObject exitLogin(HttpServletRequest request){
		JSONObject json = new JSONObject();
		service.exitLogin(request);
		json.put(APPResultMsgCommons.CODE, LoginCommons.SUCCESSCODE);
		json.put(APPResultMsgCommons.MSG, "账号已退出");
		return json;
	}
}
