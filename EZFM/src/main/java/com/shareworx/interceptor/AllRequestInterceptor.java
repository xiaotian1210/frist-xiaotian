package com.shareworx.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.system.crop.model.CropModel;
import com.shareworx.ezfm.system.crop.service.CropDomainService;
import com.shareworx.ezfm.utils.ChargePaymentUtil;
import com.shareworx.ezfm.utils.UserUtil;

@Controller
public class AllRequestInterceptor implements HandlerInterceptor {
	
	final static Logger log = Logger.getLogger(AllRequestInterceptor.class);
	
	//将不走监听的Action的url放进FILTERURIS
	private static final String[] FILTERURIS = new String[]{"/ezfm/mobile","/ezfm/home/password/alter","/ezfm/home/exit","/ezfm/worktask/areauser/whether/dispatch","/ezfm/home/dbtask/count","/ezfm/system/function/loginuser/func/query","/ezfm/home/index","/ezfm/login/home/index","/ezfm/system/crop","/ezfm/apply/apply","/ezfm/login","/error","/ezfm/help"};
	//将不走postHandle监听的Action的url放进postFILTERURIS
	private static final String[] postFILTERURIS = new String[]{"/ezfm/home/exit"};
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUri = request.getRequestURI().substring(request.getContextPath().length());
		for(String uri : FILTERURIS){
			if(requestUri.startsWith(uri)){
				return true;
			}
		}
	
		log.info(requestUri);
		YJWYUserModel user = UserUtil.getCurrentUser();
		
		if(user.getUser_code().equals("admin")||user.getUser_code().equals("dmsadmin"))
			return true;
		 
		CropDomainService cropDomainService = (CropDomainService) WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext()).getBean("cropDomainService"); 

		CropModel crop = cropDomainService.queryById(user.getPk_crop());
		
		String deadline = crop.getCrop_deadline();
		
		if(ChargePaymentUtil.getSurplusTime(deadline)>0)
			return true;
		else{
			response.sendRedirect("/ezfm/help/chargepayment/deadline/index");
			return false;
		}
		
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
	}
	

}
