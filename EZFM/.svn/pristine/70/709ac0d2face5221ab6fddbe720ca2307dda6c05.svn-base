package com.shareworx.ezfm.templatemethodmodel;

import java.util.List;


import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class GetContextPathTemplateMethodModel implements TemplateMethodModelEx {
	
	private String contextPath;
	
	public GetContextPathTemplateMethodModel(String contextPath) {
		this.contextPath = contextPath;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object exec(List arguments) throws TemplateModelException {
		return contextPath;
	}
	


}
