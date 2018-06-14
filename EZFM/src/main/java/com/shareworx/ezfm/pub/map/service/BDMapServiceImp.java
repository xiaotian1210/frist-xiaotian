package com.shareworx.ezfm.pub.map.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.ezfm.pub.commons.PubCommonsForward;

@Service(IBDMapService.ID)
public class BDMapServiceImp implements IBDMapService{
	@Override
	public ModelAndView forwordMap(String lon,String lat){
		ModelAndView mar = new ModelAndView(PubCommonsForward.BD_POINT_MAP_FORWARD);
		mar.addObject("lon", lon);
		mar.addObject("lat",lat);
		return mar;
	}
}
