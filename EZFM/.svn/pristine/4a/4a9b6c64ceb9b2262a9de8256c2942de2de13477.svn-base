package com.shareworx.ezfm.pub.map.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.ezfm.pub.commons.PubCommonsForward;
/**
 * 地图查询类
 * 适用于显示，不适用于修改
 * @author zhenwei.shi
 *
 */
@Controller
@RequestMapping("ezfm/pub/map/bd")
public class BDMapAction {
	
	/**
	 * 定位地图页面
	 * @return
	 */
	@RequestMapping(value="point/forward", method=RequestMethod.GET)
	public ModelAndView pointForward(@RequestParam(value="lon") String lon,@RequestParam(value="lat") String lat){
		ModelAndView mar = new ModelAndView(PubCommonsForward.BD_POINT_MAP_FORWARD);
		mar.addObject("lon", lon);
		mar.addObject("lat",lat);
		return mar;
	}
	
	/**
	 * 城市、围栏、坐标页面
	 * @return
	 */
	@RequestMapping(value="city/rail/point/forward", method=RequestMethod.GET)
	public ModelAndView cityRailPointForward(@RequestParam(value="city") String city,@RequestParam(value="rail") String rail,@RequestParam(value="lon") String lon,@RequestParam(value="lat") String lat){
		ModelAndView mar = new ModelAndView(PubCommonsForward.BD_POINT_MAP_FORWARD);
		mar.addObject("city",city);
		mar.addObject("rail",rail);
		mar.addObject("lon", lon);
		mar.addObject("lat",lat);
		return mar;
	}
	
	/**
	 * 城市页面
	 * @return
	 */
	@RequestMapping(value="city/forward", method=RequestMethod.GET)
	public ModelAndView cityForward(@RequestParam(value="city") String city){
		ModelAndView mar = new ModelAndView(PubCommonsForward.BD_POINT_MAP_FORWARD);
		mar.addObject("city",city);
		return mar;
	}
	
	/**
	 * 围栏页面
	 * @return
	 */
	@RequestMapping(value="rail/forward", method=RequestMethod.GET)
	public ModelAndView railForward(@RequestParam(value="rail") String rail){
		ModelAndView mar = new ModelAndView(PubCommonsForward.BD_POINT_MAP_FORWARD);
		mar.addObject("rail",rail);
		return mar;
	}
}
