package com.shareworx.ezfm.worktask.classpro.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shareworx.ezfm.baseinfo.area.dao.YJWYAreaDao;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService;
import com.shareworx.ezfm.baseinfo.project.dao.YJWYProjectDao;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.classpro.dao.YJWYWorkTaskRepairClassProjectDao;
import com.shareworx.ezfm.worktask.classpro.model.YJWYWorkTaskRepairClassProjectModel;
import com.shareworx.ezfm.worktask.classpro.service.YJWYWorkTaskRepairClassProjectBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 种类项目关联操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/worktask/classpro")
public class YJWYWorkTaskRepairClassProjectAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskRepairClassProjectAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "worktask/classpro";
	
	@Autowired
	@Qualifier(YJWYWorkTaskRepairClassProjectBusinessService.ID)
	private YJWYWorkTaskRepairClassProjectBusinessService service;
	
	@Autowired
	@Qualifier(YJWYAreaBusinessService.ID)
	private YJWYAreaBusinessService areaService;

	
	public void setService(YJWYWorkTaskRepairClassProjectBusinessService service) {
		this.service = service;
	}

	
	public void setAreaService(YJWYAreaBusinessService areaService) {
		this.areaService = areaService;
	}
	/**
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView listForward(){
		return new ModelAndView(PAGE_FORWARD);
	}
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYWorkTaskRepairClassProjectModel[] models = service.query(query);
		YJWYWorkTaskRepairClassProjectDao domainDao = SpringUtils.getBean(YJWYWorkTaskRepairClassProjectDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="queryProject", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryProject(@RequestParam(value="class_id", required=false) String class_id
			,@RequestParam(value="area_id", required=false) String area_id) {
		Query query = Query.from(YJWYAreaModel.META_ID);
		ModelAndResult mar = new ModelAndResult();
		//添加公司查询条件。
		YJWYWorkTaskRepairClassProjectModel[]  models = this.mosaicTree(class_id,area_id);
		mar.setAttribute("rows", models);
		return mar;
	}
	
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYWorkTaskRepairClassProjectModel model) {
		YJWYWorkTaskRepairClassProjectModel[] rs = service.save(new YJWYWorkTaskRepairClassProjectModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYWorkTaskRepairClassProjectModel model) {
		YJWYWorkTaskRepairClassProjectModel[] rs = service.update(new YJWYWorkTaskRepairClassProjectModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYWorkTaskRepairClassProjectModel[] models) {
		YJWYWorkTaskRepairClassProjectModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
	
	
	/**
	 * 拼接树数据
	 * @param models
	 * @return
	 */
	public YJWYWorkTaskRepairClassProjectModel[] mosaicTree(String class_id,String area_id){
		List<YJWYWorkTaskRepairClassProjectModel> list = new ArrayList<YJWYWorkTaskRepairClassProjectModel>();
		if (StringUtils.isEmpty(class_id)) {
			//拿到区域
			YJWYAreaModel[] areaModels =this.queryArea();
			YJWYWorkTaskRepairClassProjectModel probjectInfoModel;
			for (int i = 0; i < areaModels.length; i++) {
				probjectInfoModel = new YJWYWorkTaskRepairClassProjectModel();
				probjectInfoModel.put("id",  areaModels[i].get("pk_area"));
				probjectInfoModel.put("name", areaModels[i].get("area_name"));
				probjectInfoModel.put("pk_area", areaModels[i].get("pk_area"));
				probjectInfoModel.put("type", 1);
				probjectInfoModel.put("isParent", true);
				list.add(probjectInfoModel);
			}
		}else{
			//拿到所有项目
			YJWYProjectModel[] projectModels = queryProjectByArea(area_id);
			YJWYWorkTaskRepairClassProjectModel probjectInfoModel;
			for (YJWYProjectModel model:projectModels) {
					probjectInfoModel = new YJWYWorkTaskRepairClassProjectModel();
					probjectInfoModel.put("id", model.get("pk_project"));
					probjectInfoModel.put("name", model.get("project_name"));
					probjectInfoModel.put("pId",  class_id);
					probjectInfoModel.put("type", 2);
					list.add(probjectInfoModel);
			}
		}
		Query query = Query.from(YJWYWorkTaskRepairClassProjectModel.META_ID);
		query.and(new Condition("class_id", QueryContents.TYPE_EQ, class_id));
		YJWYWorkTaskRepairClassProjectModel [] repairClassModels = service.query(query);
		YJWYWorkTaskRepairClassProjectModel[] projectInfoModels = new YJWYWorkTaskRepairClassProjectModel[list.size()];
		for (int i =0;i<list.size();i++) {
			for (YJWYWorkTaskRepairClassProjectModel model:repairClassModels) {
				if (list.get(i).get("id").equals(model.getProject_id())) {
					list.get(i).put("selected",true);
				}
			}
			projectInfoModels[i]=list.get(i);
		}
		return projectInfoModels;
	}
	
	/**
	 * 查找区域
	 * @param areaModels
	 * @return
	 */
	public YJWYAreaModel[] queryArea(){
		Query query = Query.from(YJWYAreaModel.META_ID);
		//添加公司查询条件。
		query.and(Condition.create("pk_crop_", UserUtil.getCurrentUser().getPk_crop()));
		query.and(Condition.create("delete_flag_",0));
		YJWYAreaDao domainDao = SpringUtils.getBean(YJWYAreaDao.ID);
		List<YJWYAreaModel> list = domainDao.queryListByCondition(query);;
		if(ArrayUtils.isEmpty(list)){
			return new YJWYAreaModel[0];
		}
		return list.toArray(new YJWYAreaModel[0]);
	}
	
	/**
	 * 根据区域查找项目
	 * @param areaModels
	 * @return
	 */
	public YJWYProjectModel[] queryProjectByArea(String pk_area){
		Query query = Query.from(YJWYProjectModel.META_ID);
		YJWYProjectDao domainDao = SpringUtils.getBean(YJWYProjectDao.ID);
		query.and(Condition.create("pk_area_", pk_area));
		List<YJWYProjectModel> list = domainDao.queryListByCondition(query);;
		if(ArrayUtils.isEmpty(list)){
			return new YJWYProjectModel[0];
		}
		return list.toArray(new YJWYProjectModel[0]);
	}
}
