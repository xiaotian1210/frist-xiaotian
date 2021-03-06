package com.shareworx.ezfm.baseinfo.pub.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.pub.service.IBaseInfoQueryService;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * @see此类供前台下拉框查询使用
 * @author zhenwei.shi
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/pub/query")
public class BaseInfoQuesyAction {
	@Autowired
	@Qualifier(IBaseInfoQueryService.ID)
	private IBaseInfoQueryService service;

	public void setAreaService(IBaseInfoQueryService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
	}

	/**
	 * @author zhenwei.shi
	 * 区域字典查询操作(查询所有区域)
	 * 供前台下拉框使用
	 * @return
	 */
	@RequestMapping(value="dictionary/all/area/query", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult queryForAllArea(HttpServletRequest req) {
		YJWYAreaModel[] models = service.queryForArea();
		return new ModelAndResult(models);
	}
	/**
	 * @author zhenwei.shi
	 * 区域字典查询操作(查询自己所属区域)
	 * 供前台下拉框使用
	 * @return
	 */
	@RequestMapping(value = "dictionary/area/query", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryForAreaBySelf(HttpServletRequest req) {
		List<YJWYAreaModel> areaList = new ArrayList<>();
		// 获取用户所属项目集合
//		Set<String> projectIds = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentUserPk());
//		if (projectIds.size() != 0) {
//			areaList.addAll(Arrays.asList(deviceService.getAreaModelsByPorjectIds(projectIds)));
//		}
		YJWYAreaModel[] areas = UserUtil.getUserArea();
		if(areas!=null) {
			for(int i=0;i<areas.length;i++) {
				areaList.add(areas[i]);
			}
		}
		return new ModelAndResult(areaList);
	}

	/**
	 * 不建议使用 请在前台使用选中方法
	 * @author zhangjing.cheng
	 * 区域字典查询操作
	 * 供前台下拉框使用
	 * @return
	 */
	@RequestMapping(value = "dictionary/area/id/query", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryForArea(@RequestParam(required = false) String areaId) {
		YJWYAreaModel[] models = service.queryForArea();
		for (YJWYAreaModel model : models) {
			if (!StringUtils.isEmpty(areaId)) {
				if (model.getPk_area().equals(areaId)) {
					model.put("selected", true);
				}
			}
		}
		return new ModelAndResult(models);
	}

	/**
	 * @author zhenwei.shi
	 * 项目字典查询操作(查询所有项目)
	 * 供前台下拉框使用
	 * @return
	 */
	@RequestMapping(value = "dictionary/all/project/query", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryForAllProject(@RequestParam(required = false) String areaId) {
		return new ModelAndResult(service.queryForProject(areaId));
	}

	/**
	 * @author zhenwei.shi
	 * 项目字典查询操作(查询自己所属项目)
	 * 供前台下拉框使用
	 * @return
	 */
	@RequestMapping(value = "dictionary/project/query", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryForProject(@RequestParam(required = false) String areaId) {
		List<YJWYProjectModel> projectList = new ArrayList<>();
//		if (!StringUtils.isEmpty(areaId)) {
//			projectList.addAll(deviceService.getProjectModelsByPkArea(areaId));
//		}
	   if(!StringUtils.isEmpty(areaId)){
		   YJWYProjectModel[] projects = UserUtil.getUserProject(areaId);
			if(projects != null){
				for(int i=0;i<projects.length;i++){
					projectList.add(projects[i]);
				}
			} 
	   }		
		return new ModelAndResult(projectList);
	}

	/**
	 * @author zhenwei.shi
	 * 部门字典查询操作(组织结构) 注：所属部门从字典表取
	 * 供前台下拉框使用
	 * @return
	 */
	@RequestMapping(value = "dictionary/orgdept/query", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryForOrgDept(@RequestParam(required = false) String areaId, @RequestParam(required = false) String projectId) {
		return new ModelAndResult(service.queryForOrgDept(areaId, projectId));
	}

	/**
	 * @author zhenwei.shi
	 * 岗位字典查询操作 只能使用项目ID查询 后期可能会扩展使用部门查询(现岗位管理功能未明确)
	 * 供前台下拉框使用
	 * @return
	 */
	@RequestMapping(value = "dictionary/station/query", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryForStation(@RequestParam(required = false) String projectId) {
		return new ModelAndResult(service.queryForStation(projectId));
	}

	/**
	 * @author zhenwei.shi
	 * 人员查询操作
	 * 供前台下拉框使用
	 * @return
	 */
	@RequestMapping(value = "dictionary/user/query", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryForUser(@RequestParam(required = false) String areaId, @RequestParam(required = false) String projectId, @RequestParam(required = false) String stationId) {
		return new ModelAndResult(service.queryForUser(areaId, projectId, stationId));
	}

	/**
	 * @author zhenwei.shi
	 * 数据字典查询
	 * 供前台下拉框使用
	 * @param code 父子CODE都可以，返还是子信息或者兄弟信息数组
	 * @param state 1表示父CODE 0表示兄弟CODE
	 * @return 返回信息一致自信息数组
	 */
	@RequestMapping(value = "dictionary/dict/query", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryForDictionary(@RequestParam String code, @RequestParam int state) {
		YJWYDictModel[] models = service.queryDictionaryForArray(code, state);
		return new ModelAndResult(models);
	}

	/**
	 * @author zhenwei.shi
	 * 城市字典查询操作
	 * 供前台下拉框使用
	 * @return
	 */
	@RequestMapping(value = "dictionary/city/query", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryForCity(HttpServletRequest req, @RequestParam String pid) {
		return new ModelAndResult(service.queryCityForList(Integer.parseInt(pid)));
	}
}
