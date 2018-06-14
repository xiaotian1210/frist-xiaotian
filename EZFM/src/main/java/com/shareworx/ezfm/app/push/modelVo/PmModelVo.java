package com.shareworx.ezfm.app.push.modelVo;

import java.util.Map;

public class PmModelVo {

	private String pm_module; //那个模块的（app 有七大模块 1,2,3,4,5,6,7）
	
	private String pm_function; //每个模块的代表的功能
	
	private Map<String, Object> pm_ext;

	public String getPm_module() {
		return pm_module;
	}

	public void setPm_module(String pm_module) {
		this.pm_module = pm_module;
	}

	public String getPm_function() {
		return pm_function;
	}

	public void setPm_function(String pm_function) {
		this.pm_function = pm_function;
	}

	public Map<String, Object> getPm_ext() {
		return pm_ext;
	}

	public void setPm_ext(Map<String, Object> pm_ext) {
		this.pm_ext = pm_ext;
	}

}
