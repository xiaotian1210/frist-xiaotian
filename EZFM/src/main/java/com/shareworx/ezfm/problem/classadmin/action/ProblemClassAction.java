package com.shareworx.ezfm.problem.classadmin.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.ezfm.problem.classadmin.model.ProblemClassModel;
import com.shareworx.ezfm.problem.classadmin.service.ProblemClassBusinessService;
import com.shareworx.ezfm.problem.nexus.proandclass.service.YJWYProjectInfoClassNexusBusinessService;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.alibaba.fastjson.JSONArray;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.mvc.ShareworxAuthencatinException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.StringUtils;

/**
 * 报事分类管理操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/problem/classadmin")
public class ProblemClassAction {

	final static Logger log = Logger.getLogger(ProblemClassAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/problem/classadmin/classAdmin";

	@Autowired
	@Qualifier(ProblemClassBusinessService.ID)
	private ProblemClassBusinessService service;

	@Autowired
	@Qualifier(YJWYProjectInfoClassNexusBusinessService.ID)
	private YJWYProjectInfoClassNexusBusinessService projectService;

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	public void setService(ProblemClassBusinessService service) {
		this.service = service;
	}

	public void setProjectService(YJWYProjectInfoClassNexusBusinessService projectService) {
		this.projectService = projectService;
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
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value = "class_id", required = false) String class_id) {
		ModelAndResult mar = new ModelAndResult();
		ProblemClassModel[] models = this.mosaicTree(service.queryTree(class_id));
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
	 * 项目定义查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "projectClassquery", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult projectClassquery(
			@RequestParam(value = "project_id", required = false) String project_id,
			@RequestParam(value = "class_id", required = false) String class_id) {
		ModelAndResult mar = new ModelAndResult();
		if (!StringUtils.isEmpty(project_id)) {
			ProblemClassModel[] models = service.projectClassquery(class_id, project_id);
			mar.setAttribute("rows", models);
		}
		return mar;
	}

	/**
	 * 拼接树数据
	 * 
	 * @param models
	 * @return
	 */
	public ProblemClassModel[] mosaicTree(ProblemClassModel[] models) {
		for (ProblemClassModel model : models) {
			model.put("id", model.get("pk_class_id"));
			model.put("name", model.get("class_name"));
			model.put("pId", model.get("parent_id"));
			model.put("create_user_name", model.get("create_user_name"));
			// 判断是否为叶子节点。
			// if (!StringUtils.isEmpty(model.getParent_id())) {
			// model.put("isParent", true);
			// model.put("open", true);
			// model.put("nocheck",false);
			// }
		}
		return models;
	}

	/**
	 * 下载导入模板操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "imptemplete/download", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult downloadImpTemplate() {
		return ImpAndExpExcel.download("templates/templates/problem/报事分类导入模板.xls");
	}

	/**
	 * 导入Excel
	 * 
	 * @return
	 */
	@RequestMapping(value = "import/excel", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult impExcel(HttpServletRequest request,
			@RequestParam("excleFile") MultipartFile file) {
		String[] files = new String[] { "class_name", "class_code", "parent_id", "import_whether_repair",
				"import_whether_visit", "project_attribute", "import_time_limit", "import_sort" };
		JSONArray jsonArray;
		try {
			jsonArray = ImpAndExpExcel.doImpExcel(file, files, 2);
		} catch (IOException e) {
			e.printStackTrace();
			return new ModelAndResult(false, "导入失败");
		}
		List<ProblemClassModel> list = JSONArray.parseArray(jsonArray.toJSONString(), ProblemClassModel.class);
		// 名称问题
		String name = "";
		// 编码问题
		String code = "";
		// 父类编码问题
		String parentCode = "";
		// 维修问题
		String whetherRepair = "";
		// 回访问题
		String whetherVisit = "";
		// 项目属性问题
		String projectAttribute = "";
		// 处理时限问题
		String timeLimit = "";
		int connt = list.size();
		// 循环判断数据准确性，问题数据跳过不提交；
		int row = 0;
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
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
				// 判断是否维修非空
				if (StringUtils.isEmpty(list.get(i).getImportWhetherRepair())
						&& (!list.get(i).getImportWhetherRepair().equals("1")
								|| !list.get(i).getImportWhetherRepair().equals("2"))) {
					whetherRepair += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				} else {
					list.get(i).setWhether_repair(Integer.parseInt(list.get(i).getImportWhetherRepair()));
				}
				// 判断是否回访非空
				if (StringUtils.isEmpty(list.get(i).getImportWhetherVisit())
						&& (!list.get(i).getImportWhetherVisit().equals("1")
								|| !list.get(i).getImportWhetherVisit().equals("2"))) {
					whetherVisit += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				} else {
					list.get(i).setWhether_visit(Integer.parseInt(list.get(i).getImportWhetherVisit()));
				}
				// 判断项目属性非空
				if (StringUtils.isEmpty(list.get(i).getProject_attribute())) {
					projectAttribute += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				}
				// 判断时效
				if (!StringUtils.isEmpty(list.get(i).getImportTimeLimit())
						&& (!list.get(i).getImportTimeLimit().equals("1")
								|| !list.get(i).getImportTimeLimit().equals("2")
								|| !list.get(i).getImportTimeLimit().equals("3"))) {
					timeLimit += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				} else {
					if (!StringUtils.isEmpty(list.get(i).getImportTimeLimit())) {
						list.get(i).setTime_limit(Integer.parseInt(list.get(i).getImportTimeLimit()));
					}
				}
				// 判断排序
				if (!StringUtils.isEmpty(list.get(i).getImportSort())
						&& !judgeStringToInt(list.get(i).getImportSort())) {
					timeLimit += row + 3 + ",";
					list.remove(i);
					i--;
					row++;
					continue;
				} else {
					list.get(i).setSort(Integer.parseInt(list.get(i).getImportSort()));
				}
				// 判断数据编码唯一性
				JdbcTemplate read = dao.getReadTemplate();
				String countSql = "select count(pk_class_id) from yjwy_problem_class where class_code='"
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
					String projectSql = "select pk_class_id from yjwy_problem_class where class_code='"
							+ list.get(i).getParent_id() + "'";
					try {
						Map<String, Object> projectMap = read.queryForMap(projectSql);
						// 无法获取该code项目，跳过
						if (projectMap != null) {
							list.get(i).setParent_id(projectMap.get("pk_class_id").toString());
						}
					} catch (Exception e) {
						// TODO: handle exception
						// 未查到异常错误，不做处理。跳过
					}
				} else {
					list.get(i).setParent_id(null);
				}
				// 所属公司
				list.get(i).setPk_crop(UserUtil.getCurrentUser().getPk_crop());
				row++;
			}
		}
		if (list.size() > 0) {
			service.save(list.toArray(new ProblemClassModel[] {}));
		}
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
		if (!StringUtils.isEmpty(whetherRepair)) {
			output += "是否维修问题【" + whetherRepair + "】行。";
		}
		if (!StringUtils.isEmpty(whetherVisit)) {
			output += "是否回访问题【" + whetherVisit + "】行。";
		}
		if (!StringUtils.isEmpty(projectAttribute)) {
			output += "否项目属性问题【" + projectAttribute + "】行。";
		}
		if (!StringUtils.isEmpty(timeLimit)) {
			output += "处理时限问题【" + timeLimit + "】行。";
		}
		return new ModelAndResult(true, output);
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

	/**
	 * 拼接树数据
	 * 
	 * @param models
	 * @return
	 */
	public ProblemClassModel[] mosaicTreeCombotree(ProblemClassModel[] models) {
		for (ProblemClassModel model : models) {
			model.put("id", model.get("pk_class_id"));
			model.put("text", model.get("class_name"));
			model.setArrayAttribute("children", getSonTree(model.get("pk_class_id").toString()));
		}
		return models;
	}

	/**
	 * 递归拼接tree
	 * 
	 * @param class_id
	 * @return
	 */
	public List<ProblemClassModel> getSonTree(String class_id) {
		List<ProblemClassModel> classList = new ArrayList<ProblemClassModel>();
		Query query = Query.from(ProblemClassModel.META_ID);
		query.and(Condition.create("parent_id", class_id));
		ProblemClassModel[] sonModels = service.query(query);
		if (sonModels.length > 0) {
			for (ProblemClassModel sonModel : sonModels) {
				sonModel.put("id", sonModel.get("pk_class_id"));
				sonModel.put("text", sonModel.get("class_name"));
				sonModel.setArrayAttribute("children", this.getSonTree(sonModel.get("pk_class_id").toString()));

				classList.add(sonModel);
			}
		}
		return classList;
	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody ProblemClassModel model) {
		// 判断数据编码唯一性
		JdbcTemplate read = dao.getReadTemplate();
		String countSql = "select count(pk_class_id) from yjwy_problem_class where class_code='" + model.getClass_code()
				+ "'";
		int countCode = read.queryForObject(countSql, Integer.class);
		if (countCode > 0) {
			throw new ShareworxAuthencatinException("分类编码不允许重复，请查证!");
		}
		ProblemClassModel[] rs = service.save(new ProblemClassModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody ProblemClassModel model) {
		ProblemClassModel[] rs = service.update(new ProblemClassModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody ProblemClassModel[] models) {
		JdbcTemplate read = dao.getReadTemplate();
		int count;
		String sql;
		// 判断是否有关联，有关联的提示无法删除
		for (ProblemClassModel model : models) {
			sql = "select count(nexus_id) from yjwy_proinfo_class_nexus where class_id ='" + model.getPk_class_id()
					+ "'";
			count = read.queryForObject(sql, Integer.class);
			sql = "select count(pk_details_id) from yjwy_problem_details where fk_class_id ='" + model.getPk_class_id()
					+ "'";
			count += read.queryForObject(sql, Integer.class);
			sql = "select count(pk_class_id) from yjwy_problem_class where parent_id ='" + model.getPk_class_id() + "'";
			count = read.queryForObject(sql, Integer.class);
			if (count > 0) {
				ModelAndResult result = new ModelAndResult();
				result.setSuccess(false);
				result.put("prompt", "此分类关联有项目或报事工单，请先删除相关数据！");
				return result;
			}
		}
		ProblemClassModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}

	/**
	 * 查询项
	 * 
	 * @return
	 */
	public List<String> selects() {
		List<String> selects = new ArrayList<String>();
		selects.add("pk_class_id");
		selects.add("class_name");
		selects.add("parent_id");
		selects.add("whether_repair");
		selects.add("whether_visit");
		selects.add("project_attribute");
		selects.add("time_limit");
		selects.add("sort");
		selects.add("create_time");
		return selects;
	}
}
