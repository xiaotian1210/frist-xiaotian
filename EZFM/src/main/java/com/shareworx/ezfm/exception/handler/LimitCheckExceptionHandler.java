package com.shareworx.ezfm.exception.handler;

import javax.servlet.http.HttpServletRequest;

//import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dms365.ezfm.limitcheck.exception.LimitCheckException;

//@ControllerAdvice
public class LimitCheckExceptionHandler {

	@ExceptionHandler(value = LimitCheckException.class)
    @ResponseBody
    public Object defaultErrorHandler(HttpServletRequest req, LimitCheckException e) throws Exception {
		System.out.println(e);
        return null;
    }
	
}
