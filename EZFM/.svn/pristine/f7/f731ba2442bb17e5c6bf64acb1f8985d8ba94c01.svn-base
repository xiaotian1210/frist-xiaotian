package com.shareworx.ezfm.login.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.action.InspectStandardAction;
@Controller
@RequestMapping("ezfm/help")
public class YJWYHelpAction {
	final static Logger log = Logger.getLogger(InspectStandardAction.class);
	/**home跳转页面 */
	public final static String SESSION_INVALID_FORWARD = "ezfm/login/session_invalid";
	
	/**过期跳转页面*/
	public final static String DEADLINE_FORWARD = "ezfm/chargepayment/deadline";
	/**
	 * 转向Session失效提示页面
	 * @return
	 */
	@RequestMapping(value="session/invalid/index/{state}", method=RequestMethod.GET)
	public ModelAndView homeForward(@PathVariable(value="state") String state, HttpServletRequest request){
		if(request.getSession().getAttribute("invalid_first")!=null&&request.getSession().getAttribute("invalid_first").toString() == Boolean.TRUE.toString()) {
			request.getSession().removeAttribute("invalid_first");
			return new ModelAndView("redirect:"+request.getContextPath()+"/ezfm/login/index");
		}
		ModelAndView mav = new ModelAndView(SESSION_INVALID_FORWARD);
		if(LoginCommons.HTTPSESSIONINVALID.equals(state)){
			mav.addObject("state", LoginCommons.HTTPSESSIONINVALID);
		}else{
			mav.addObject("state", LoginCommons.DMSSESSIONINVALID);
		}
		request.getSession().setAttribute("invalid_first", true);
		return mav;
	}
	
	/**
	 * 转向过期提示页面
	 * @return
	 */
	@RequestMapping(value="chargepayment/deadline/index", method=RequestMethod.GET)
	public ModelAndView deadlineForward(){
		ModelAndView mav = new ModelAndView(DEADLINE_FORWARD);
		return mav;
	}
}
