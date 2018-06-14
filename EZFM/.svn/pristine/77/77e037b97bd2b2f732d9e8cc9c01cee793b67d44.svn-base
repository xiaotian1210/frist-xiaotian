package com.shareworx.ezfm.energyloss.reading.action;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("ezfm/energyloss/reading")
public class YJWYReadElectricAction {
	Logger log = Logger.getLogger(YJWYReadElectricAction.class);
	
	
	/** 地点页面*/
	public final static String PAGE_FORWORD = "ezfm/energyloss/reading_electric";
	
	/**
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView listForward(){
		return new ModelAndView(PAGE_FORWORD);
	}
}
