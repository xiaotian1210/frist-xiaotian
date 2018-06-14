package com.shareworx.ezfm.app.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AppCookieUtil {

	public static void clearCookie(HttpServletRequest request, HttpServletResponse response, String path) {
		Cookie[] cookies = request.getCookies();
		try {
			for (int i = 0; i < cookies.length; i++) {
				// System.out.println(cookies[i].getName() + ":" +
				// cookies[i].getValue());
				Cookie cookie = new Cookie(cookies[i].getName(), null);
				cookie.setMaxAge(0);
				cookie.setPath(path);// 根据你创建cookie的路径进行填写
				response.addCookie(cookie);
			}
		} catch (Exception ex) {
			System.out.println("清空Cookies发生异常！");
		}

	}
	
	
}
