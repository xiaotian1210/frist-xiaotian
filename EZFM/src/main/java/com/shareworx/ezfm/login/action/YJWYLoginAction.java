package com.shareworx.ezfm.login.action;

import java.io.IOException;

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
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.login.service.IYJWYLoginAuthorizationService;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.action.InspectStandardAction;
@Controller
@RequestMapping("ezfm/login")
public class YJWYLoginAction {
	final static Logger log = Logger.getLogger(InspectStandardAction.class);
	/**home跳转页面 */
	public final static String LOGIN_FORWARD = "ezfm/login/login";
	public final static String CALLCENTER_LOGIN_FORWARD = "ezfm/login/callcenterLogin";
	public final static String HOME_FORWARD = "ezfm/login/home";
	@Autowired
	@Qualifier(IYJWYLoginAuthorizationService.ID)
	private IYJWYLoginAuthorizationService service;

	public void setService(IYJWYLoginAuthorizationService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(value=YJWYUserDomainService.ID)
	private YJWYUserDomainService userDomainService;
	
	public void setUserDomainService(YJWYUserDomainService userDomainService) {
		this.userDomainService = userDomainService;
	}
	/**
	 * 转向Login页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView loginForward(HttpServletRequest req, HttpServletResponse resp){
		if(service.isSelfAlreadyLoginForSession(req)){
			try {
				resp.sendRedirect(LoginCommons.HOMEURL);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		ModelAndView mv = new ModelAndView(LOGIN_FORWARD);
		mv.addObject("contextPath", req.getContextPath());
		return mv;
	}
	
	
	/**
	 * 呼叫中心转向Login页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="callcenter", method=RequestMethod.GET)
	public ModelAndView callcenterLoginForward(HttpServletRequest req, HttpServletResponse resp
			,String username,String password){
		ModelAndView mv = new ModelAndView(CALLCENTER_LOGIN_FORWARD);;
		//呼叫中心调用登陆界面
		//已有登陆状态
		if(service.isSelfAlreadyLoginForSession(req)){
			mv.addObject("service",1);
		}else{
			//未有登陆状态
			mv.addObject("service",0);
		}
		mv.addObject("username",username);
		mv.addObject("password",password);
		return mv;
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
	public @ResponseBody ModelAndResult login(HttpServletRequest req, HttpServletResponse resp,@RequestParam String username, @RequestParam String password){
		YJWYUserModel user = new YJWYUserModel();
		user.setUser_code(username);
		user.setPassword(password);
		service.doLogin(user, req, resp,true);
		return new ModelAndResult(user);
	}
	/**
	 * 判断用户是否已经登录
	 * @param req
	 * @param resp
	 * @param username
	 * @param password
	 * @return
	 */
	@RequestMapping(value="isalready/login",method=RequestMethod.POST)
	public @ResponseBody ModelAndResult isAlreadyLogin(HttpServletRequest req, HttpServletResponse resp,@RequestParam String username, @RequestParam String password){
		YJWYUserModel user = new YJWYUserModel();
		user.setUser_code(username);
		user.setPassword(password);
		boolean isAready = service.isAlreadyLogin(user);
		if(isAready){
			return new ModelAndResult(true,"账号已经被登录，是否强制登录？");
		}else{
			return new ModelAndResult(false,"账号未被登录，可以登录");
		}
	}
}
