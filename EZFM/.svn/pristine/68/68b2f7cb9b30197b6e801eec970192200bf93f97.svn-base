package com.shareworx.ezfm.worktask.repairclass.action;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shareworx.ezfm.device.util.ObectUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.system.dict.service.YJWYDictBusinessService;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassBusinessService;
import com.alibaba.fastjson.JSONArray;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.StringUtils;

/**
 * 维修种类操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/orktask/repairclass")
public class YJWYWorkTaskRepairClassAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskRepairClassAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/workTask/repairClass/repair_class_listcard";

	/** 打开子页面 */
	public final static String SONPAGE_FORWARD = "ezfm/workTask/repairClass/repair_class_relation_project";

	/** 打开子页面 */
	public final static String ADD_PROJECT = "ezfm/workTask/repairClass/add_project";

	@Autowired
	@Qualifier(YJWYWorkTaskRepairClassBusinessService.ID)
	private YJWYWorkTaskRepairClassBusinessService service;

	@Autowired
	@Qualifier(YJWYDictBusinessService.ID)
	private YJWYDictBusinessService dictService;

	public void setService(YJWYWorkTaskRepairClassBusinessService service) {
		this.service = service;
	}

	public void setDictService(YJWYDictBusinessService dictService) {
		this.dictService = dictService;
	}

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	/**
	 * 转向列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView listForward() {
		return new ModelAndView(PAGE_FORWARD);
	}

	/**
	 * 转向详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "sonpage/{id}", method = RequestMethod.GET)
	public ModelAndView sonpageForward(HttpServletRequest request, @PathVariable String id) {
		ModelAndView mv = new ModelAndView(SONPAGE_FORWARD);
		mv.addObject("pk_class_id", id);
		return mv;
	}

	/**
	 * 转向详情
	 * 
	 * @return
	 */
	@RequestMapping(value = "addproject/{id}", method = RequestMethod.GET)
	public ModelAndView addProjectForward(HttpServletRequest request, @PathVariable String id) {
		ModelAndView mv = new ModelAndView(ADD_PROJECT);
		mv.addObject("pk_class_id", id);
		return mv;
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value = "class_id", required = false) String class_id) {
		ModelAndResult mar = new ModelAndResult();
		// 判断是否为根目录
		YJWYWorkTaskRepairClassModel[] models = service.queryTree(class_id);
		mar.setAttribute("rows", models);
		return mar;
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryClassByProject", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryClassByProject(@RequestParam String projectId,
			@RequestParam(value = "pk_details_id", required = false) String pk_details_id) {
		YJWYWorkTaskRepairClassModel[] models = service.queryClassByProject(projectId, pk_details_id);
		return new ModelAndResult(models);
	}

	/**
	 * 查询树结构展示
	 * 
	 * @return
	 */
	@RequestMapping(value = "queryClassByTree", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryClassByTree(@RequestParam String repair_class_id) {
		YJWYWorkTaskRepairClassModel[] models = service.queryClassByTree(repair_class_id);
		return new ModelAndResult(models);
	}

	/**
	 * 查询服务专业字典
	 * 
	 * @return
	 */

	@RequestMapping(value = "queryDict", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryDict(@RequestParam(value = "dictCode", required = false) String dictCode,
			@RequestParam(value = "code", required = false) String code) {
		YJWYDictModel[] models = dictService.getDictByCode(dictCode);
		int len = models.length;
		Object[] modelsRes = new Object[len];
		for (int i = 0; i < len; i++) {
			if (models[i].getDict_code().equals(code)) {
				models[i].put("selected", true);
			}
			modelsRes[i] = models[i];
		}
		ModelAndResult mr = new ModelAndResult(modelsRes);
		return mr;
	}

	/**
	 * 下载导入模板操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "imptemplete/download", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult downloadImpTemplate() {
		return ImpAndExpExcel.download("templates/templates/worktask/维修类型导入模板.xls");
	}

	/**
	 * 导入Excel
	 * 
	 * @return
	 */
	@RequestMapping(value = "import/excel", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult impExcel(HttpServletRequest request,
			@RequestParam("excleFile") MultipartFile file) {
		String[] files = new String[] { "service_major", "class_name", "class_code", "parent_id", "repair_class",
				"project_class", "specifications", "company", "import_rated_worktime", "import_labor_cost",
				"import_material_cost", "import_mechanics_cost", "import_sort" };
		JSONArray jsonArray;
		try {
			jsonArray = ImpAndExpExcel.doImpExcel(file, files, 2);
		} catch (IOException e) {
			e.printStackTrace();
			return new ModelAndResult(false, "导入失败");
		}
		List<YJWYWorkTaskRepairClassModel> list = JSONArray.parseArray(jsonArray.toJSONString(),
				YJWYWorkTaskRepairClassModel.class);
		// 服务专业
		String serviceMajor = "";
		// 名称问题
		String name = "";
		// 编码问题
		String code = "";
		// 父类编码问题
		String parentCode = "";
		// 额定工时
		String ratedWorktime = "";
		// 人工费
		String laborCost = "";
		// 材料费
		String materialCost = "";
		// 机械费
		String mechanicsCost = "";
		// 排序
		String sort = "";
		int connt = list.size();
		int row = 0;
		// 循环判断数据准确性，问题数据跳过不提交；
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 判断服务专业非空
				if (StringUtils.isEmpty(list.get(i).getService_major())) {
					serviceMajor += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断名称非空
				if (StringUtils.isEmpty(list.get(i).getClass_name())) {
					name += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断编码非空
				if (StringUtils.isEmpty(list.get(i).getClass_code())) {
					code += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断父级非空
				if (StringUtils.isEmpty(list.get(i).getParent_id())) {
					parentCode += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断排序
				if (!StringUtils.isEmpty(list.get(i).getImport_sort())
						&& !judgeStringToInt(list.get(i).getImport_sort())) {
					sort += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				} else {
					if (!StringUtils.isEmpty(list.get(i).getImport_sort())) {
						list.get(i).setSort(Integer.parseInt(list.get(i).getImport_sort()));
					}
				}
				// 判断数据编码唯一性
				JdbcTemplate read = dao.getReadTemplate();
				String countSql = "select count(pk_class_id) from yjwy_worktask_repair_class where class_code='"
						+ list.get(i).getClass_code() + "'";
				int countCode = read.queryForObject(countSql, Integer.class);
				if (countCode > 0) {
					code += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 根据父类code，查询父类ID。先判断父类类型，1：项目，2：资源
				if (!list.get(i).getParent_id().equals("root")) {
					String projectSql = "select pk_class_id from yjwy_worktask_repair_class where class_code='"
							+ list.get(i).getParent_id() + "'";
					try {
						Map<String, Object> projectMap = read.queryForMap(projectSql);
						// 无法获取该code项目，跳过
						if (projectMap != null) {
							list.get(i).setParent_id(projectMap.get("pk_class_id").toString());
						} else {
							list.get(i).setParent_id("0");
						}
					} catch (Exception e) {
						// TODO: handle exception
						// 未查到异常错误，不做处理。跳过
					}
				} else {
					list.get(i).setParent_id("0");
				}
				// 所属公司
				list.get(i).setPk_crop(UserUtil.getCurrentUser().getPk_crop());
				service.save(new YJWYWorkTaskRepairClassModel[] { list.get(i) });
				row++;
			}
		}
		/*
		 * if (list.size()>0) { service.save(list.toArray(new
		 * YJWYWorkTaskRepairClassModel[]{})); }
		 */
		int fail = connt - list.size();
		String output = "导入成功【" + list.size() + "】条，失败【" + fail + "】条。";
		if (!StringUtils.isEmpty(name)) {
			output += "名称问题【" + name + "】行。";
		}
		if (!StringUtils.isEmpty(code)) {
			output += "编码问题【" + code + "】行。";
		}
		if (!StringUtils.isEmpty(parentCode)) {
			output += "父编码问题【" + parentCode + "】行。";
		}
		if (!StringUtils.isEmpty(serviceMajor)) {
			output += "服务专业问题【" + serviceMajor + "】行。";
		}
		if (!StringUtils.isEmpty(ratedWorktime)) {
			output += "额定工时问题【" + ratedWorktime + "】行。";
		}
		if (!StringUtils.isEmpty(laborCost)) {
			output += "人工费问题【" + laborCost + "】行。";
		}
		if (!StringUtils.isEmpty(materialCost)) {
			output += "材料费问题【" + materialCost + "】行。";
		}
		if (!StringUtils.isEmpty(mechanicsCost)) {
			output += "机械费问题【" + mechanicsCost + "】行。";
		}
		if (!StringUtils.isEmpty(sort)) {
			output += "排序费问题【" + sort + "】行。";
		}
		return new ModelAndResult(true, output);
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYWorkTaskRepairClassModel model) {
		ObectUtils.filter(model,"pk_class_id");
		YJWYWorkTaskRepairClassModel[] rs = service.save(new YJWYWorkTaskRepairClassModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYWorkTaskRepairClassModel model) {
		YJWYWorkTaskRepairClassModel[] rs = service.update(new YJWYWorkTaskRepairClassModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYWorkTaskRepairClassModel[] models) {
		JdbcTemplate read = dao.getReadTemplate();
		String sql;
		int count;
		for (YJWYWorkTaskRepairClassModel model : models) {
			sql = "select count(pk_nexus_id) from yjwy_worktask_repair_class_project where class_id ='"
					+ model.getPk_class_id() + "'";
			count = read.queryForObject(sql, Integer.class);
			sql = "select count(pk_class_id) from yjwy_worktask_repair_class where parent_id ='"
					+ model.getPk_class_id() + "'";
			count += read.queryForObject(sql, Integer.class);
			if (count > 0) {
				ModelAndResult result = new ModelAndResult();
				result.setSuccess(false);
				result.put("prompt", "此种类有数据关联，请先删除！");
				return result;
			}
		}
		YJWYWorkTaskRepairClassModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}

	/**
	 * 判断字符串是否为数字，是返回true,否返回false
	 * 
	 * @param character
	 * @return
	 */
	public boolean judgeStringToInt(String character) {
		try {
			Integer.valueOf(character);// 把字符串强制转换为数字
			return true;// 如果是数字，返回True
		} catch (Exception e) {
			return false;// 如果抛出异常，返回False
		}

	}
}
