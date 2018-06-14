package com.shareworx.ezfm.problem.nexus.proandclass.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService;
import com.shareworx.ezfm.baseinfo.project.dao.YJWYProjectDao;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.problem.classadmin.service.ProblemClassBusinessService;
import com.shareworx.ezfm.problem.nexus.proandclass.model.YJWYProjectInfoClassNexusModel;
import com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.SpringUtils;

/**
 * 项目与报事类型关系表操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/nexus/proandclass")
public class YJWYProjectInfoClassNexusAction {

	final static Logger log = Logger.getLogger(YJWYProjectInfoClassNexusAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/problem/projectInfo/projectInfo_listcard";
	/** 跳转关联项目界面 */
	public final static String SUN_ADDUSER = "ezfm/problem/projectInfo/addProjectUser";
	@Autowired
	@Qualifier(YJWYProjectInfoClassNexusBusinessService.ID)
	private YJWYProjectInfoClassNexusBusinessService service;

	@Autowired
	@Qualifier(YJWYAreaBusinessService.ID)
	private YJWYAreaBusinessService areaService;


	@Autowired
	@Qualifier(ProblemClassBusinessService.ID)
	private ProblemClassBusinessService classService;

	public void setService(YJWYProjectInfoClassNexusBusinessService service) {
		this.service = service;
	}

	public void setAreaService(YJWYAreaBusinessService areaService) {
		this.areaService = areaService;
	}
	public void setClassService(ProblemClassBusinessService classService) {
		this.classService = classService;
	}
	
	
	/**
	 *  转向详情
	 * @return
	 */
	@RequestMapping(value="sonAddProjectUser/{id}", method=RequestMethod.GET)
	public ModelAndView sonAddProjectUser(HttpServletRequest request,@PathVariable String id){
		ModelAndView mv = new ModelAndView(SUN_ADDUSER);
		mv.addObject("projectId",id);
		return mv;
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
	public @ResponseBody ModelAndResult query(@RequestParam(value="class_id", required=false) String class_id) {
		ModelAndResult mar = new ModelAndResult();
		YJWYProjectInfoClassNexusModel[]  models = service.queryAreaAndProject(class_id);
		mar.setAttribute("rows", models);
		return mar;
	}

	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYProjectInfoClassNexusModel model) {
		String project_id = model.getProject_id();
		List<Object> classids = model.getClassids();
		YJWYProjectInfoClassNexusModel[] rs = null;
		if (classids != null&&classids.size() > 0) {
			//新增前，删除已保存关系；
			this.deleteProjectClassModels(project_id);
			YJWYProjectInfoClassNexusModel[] nexusModels = new YJWYProjectInfoClassNexusModel[classids.size()];
			Map<String, String> map;
			YJWYProjectInfoClassNexusModel nexusModel;
			for (int i = 0; i < classids.size(); i++) {
				nexusModel = new YJWYProjectInfoClassNexusModel();
				map = (Map<String, String>) classids.get(i);
				nexusModel.setClass_id(map.get("id"));
				nexusModel.setProject_id(project_id);
				nexusModel.setCreate_time(this.obtainTime());
				nexusModel.setUpdate_time(this.obtainTime());
				nexusModels[i] = nexusModel;
			}
			rs = service.save(nexusModels);
		}
		return new ModelAndResult(rs);
	}
	

	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYProjectInfoClassNexusModel model) {
		YJWYProjectInfoClassNexusModel[] rs = service.update(new YJWYProjectInfoClassNexusModel[]{model});
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYProjectInfoClassNexusModel[] models) {
		YJWYProjectInfoClassNexusModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
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

	/**
	 * 根据分组主键id对关系表进行删除
	 * 
	 * @param pk_group
	 * @return
	 */
	public void deleteProjectClassModels(String project_id) {
		// 生成query
		Query query = Query.from(YJWYProjectInfoClassNexusModel.META_ID);
		query = query.where(new Condition("project_id", QueryContents.TYPE_EQ, project_id));
		// 查询数据
		YJWYProjectInfoClassNexusModel[] models = service.query(query);
		// 判断是否有数据
		if (models.length > 0) {
			service.delete(models);
		}
	}

	/**
	 * 查询项
	 * @return
	 */
	public List<String> selects(){
		List<String> selects = new ArrayList<String>();
		selects.add("pk_area_");
		selects.add("area_name_");
		selects.add("area_code_");
		selects.add("last_modify_time");
		return selects;
	}


	//公共方法，获取当前时间
	public String obtainTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
}
