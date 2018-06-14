package com.shareworx.interceptor;


import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.stereotype.Component;

import com.shareworx.platform.cache.IDmsCacheManager;
import com.shareworx.platform.mvc.SessionFactory;
import com.shareworx.platform.util.SpringUtils;
@Component
@WebListener
public class YJWYWebAuthSessionListener implements HttpSessionListener, HttpSessionAttributeListener {

	public final static String SYSSESSION_KEY = "syssession_key";
	public final static String SYSSESSION_NUM = "syssession_num";
	public final static String APPSESSION_KEY = "appsession_key";
	public final static String APPSESSION_NUM = "appsession_num";
	
	protected IDmsCacheManager getCacheManage() {
		return SpringUtils.getBean("cacheManage");
	}
	
	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		ServletContext ctx = se.getSession().getServletContext();
		Integer numSessions = (Integer) ctx.getAttribute(SYSSESSION_NUM);
		if (numSessions == null) {
			numSessions = new Integer(1);
		} else {
			int count = numSessions.intValue();
			numSessions = new Integer(count + 1);
		}
		ctx.setAttribute(SYSSESSION_NUM, numSessions);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		SessionFactory sessionFactory = SpringUtils.getBean(SessionFactory.ID);
		String sessionKey = (String) se.getSession().getAttribute(SYSSESSION_KEY);
		sessionFactory.removeSession(sessionKey);
		ServletContext ctx = se.getSession().getServletContext();
		Integer numSessions = (Integer) ctx.getAttribute(SYSSESSION_NUM);
		if (numSessions == null) {
			numSessions = new Integer(0);
		} else {
			int count = numSessions.intValue();
			numSessions = new Integer(count - 1);
		}
		ctx.setAttribute(SYSSESSION_NUM, numSessions);
	}
}
