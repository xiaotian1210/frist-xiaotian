package com.shareworx.ezfm.exception.handler;

import java.io.Writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class FreemarkerExceptionHandler implements TemplateExceptionHandler {
	
	private static Logger logger = LoggerFactory.getLogger(FreemarkerExceptionHandler.class);


	@Override
	public void handleTemplateException(TemplateException te, Environment env, Writer out) throws TemplateException {
		logger.error("[Freemarker Exception:"+te.getMessage()+"]");
	}

}
