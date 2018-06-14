package com.shareworx.ezfm.worktask.statistics.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.shareworx.ezfm.worktask.repairclass.service.YJWYWorkTaskRepairClassBusinessService;
import com.shareworx.ezfm.worktask.statistics.service.YJWYWorkTaskStatisticsService;

/**
 * 片区维修种类统计表控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/worktask/maintclass")
public class YJWYWorkTaskMaintclassAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskMaintclassAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/workTask/statistics/worktask_maintclass_list";

	@Autowired
	@Qualifier(YJWYWorkTaskStatisticsService.ID)
	private YJWYWorkTaskStatisticsService workTaskStatisticsService;

	public void setWorkTaskStatisticsService(YJWYWorkTaskStatisticsService workTaskStatisticsService) {
		this.workTaskStatisticsService = workTaskStatisticsService;
	}

	@Autowired
	@Qualifier(YJWYWorkTaskRepairClassBusinessService.ID)
	private YJWYWorkTaskRepairClassBusinessService repairClassBusinessService;

	public void setRepairClassBusinessService(YJWYWorkTaskRepairClassBusinessService repairClassBusinessService) {
		this.repairClassBusinessService = repairClassBusinessService;
	}

	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectBusinessService;

	public void setProjectBusinessService(YJWYProjectBusinessService projectBusinessService) {
		this.projectBusinessService = projectBusinessService;
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
	 * 查询数据集合
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(ParamEntity params) {
		// 获取参数
		String pk_area = params.getPk_area();
		String start_time = params.getStart_time();
		String end_time = params.getEnd_time();
		StringBuilder areaSql = new StringBuilder("");
		StringBuilder dateSql = new StringBuilder("");
		// 基础数据集合
		List<Map<String, Object>> models = new ArrayList<>();
		// 维修类型集合
		YJWYWorkTaskRepairClassModel[] repairClassModels = new YJWYWorkTaskRepairClassModel[0];
		// 项目编号集合
		Set<String> projects = new HashSet<>();
		// 项目集合
		YJWYProjectModel[] projectModels = new YJWYProjectModel[0];
		// 数据集合
		YJWYWorkTaskRepairClassModel[] rows = new YJWYWorkTaskRepairClassModel[0];
		// 表头第二行对象
		List<Map<String, Object>> secondColumns = new ArrayList<>();
		if (!DeviceUtil.stringIsEmpty(pk_area) && !"default".equals(pk_area)) {
			areaSql.append(" tt.pk_area = '" + pk_area + "' ");
			if (DeviceUtil.stringIsEmpty(start_time) && DeviceUtil.stringIsEmpty(end_time)) {
				start_time = DeviceUtil.date2String(new Date(), DeviceUtil.YMDHMS);
				end_time = DeviceUtil.date2String(new Date(), DeviceUtil.YMDHMS);
			}
			dateSql.append(" d.create_time >= '" + start_time + "' and d.create_time <= '" + end_time + "' ");
			// 项目下的维修单量
			StringBuilder all_total_sql = new StringBuilder();
			all_total_sql.append("select fk_project_id,count(pk_details_id) all_total from yjwy_worktask_details d ");
			all_total_sql.append("where " + dateSql);
			all_total_sql.append("and d.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
			all_total_sql.append("group by fk_project_id ");
			// 类型下工单总耗时(分钟)
			StringBuilder repair_duraction_sql = new StringBuilder();
			repair_duraction_sql.append("select repair_class_id,fk_project_id,sum(finished_duration) repair_duraction from yjwy_worktask_details d ");
			repair_duraction_sql.append("where " + dateSql);
			repair_duraction_sql.append("and d.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
			repair_duraction_sql.append("group by repair_class_id,fk_project_id ");
			// 类型下维修完成单量
			StringBuilder complete_total_sql = new StringBuilder();
			complete_total_sql.append("select repair_class_id,fk_project_id,count(pk_details_id) complete_total from yjwy_worktask_details d ");
			complete_total_sql.append("where task_state = 3 ");
			complete_total_sql.append("and " + dateSql);
			complete_total_sql.append("and d.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
			complete_total_sql.append("group by repair_class_id,fk_project_id ");
			// 类型下满意状态的单量
			StringBuilder satis_total_sql = new StringBuilder();
			satis_total_sql.append("select d.repair_class_id,d.fk_project_id,count(d.pk_details_id) satis_total from yjwy_worktask_details d  ");
			satis_total_sql.append("left join yjwy_problem_details t2 on d.pk_details_id = t2.fk_details_id ");
			satis_total_sql.append("left join yjwy_problem_record t3 on t2.pk_details_id = t3.fk_details_id ");
			satis_total_sql.append("where t3.evaluate_type in (1,2) ");
			satis_total_sql.append("and" + dateSql);
			satis_total_sql.append("and d.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
			satis_total_sql.append("group by d.repair_class_id,d.fk_project_id ");

			// 查询sql
			StringBuilder sql = new StringBuilder();
			sql.append("select * ");
			sql.append(" from (");
			sql.append("select d.repair_class_id repair_class_id,t3.class_name repair_class_name,");
			sql.append("d.fk_project_id pk_project,t1.project_name_ project_name,t2.pk_area_ pk_area,t2.area_name_ area_name,");
			sql.append("(case when count(d.pk_details_id) is null then 0 else count(d.pk_details_id) end ) repair_total,");
			sql.append("(case when t4.all_total is null then 0 else t4.all_total end ) all_total,");
			sql.append("(case when t5.repair_duraction is null then 0 else t5.repair_duraction end ) repair_duraction,");
			sql.append("(case when t6.complete_total is null then 0 else t6.complete_total end ) complete_total,");
			sql.append("(case when t7.satis_total is null then 0 else t7.satis_total end ) satis_total  ");
			sql.append("from yjwy_worktask_details d ");
			sql.append("LEFT JOIN yjwy_pub_project t1 ON d.fk_project_id = t1.pk_project_ ");
			sql.append("LEFT JOIN yjwy_pub_area t2 ON t1.pk_area_ = t2.pk_area_ ");
			sql.append("LEFT JOIN yjwy_worktask_repair_class t3 ON d.repair_class_id = t3.pk_class_id ");
			sql.append("LEFT JOIN (");
			sql.append(all_total_sql);
			sql.append(") t4 ON d.fk_project_id = t4.fk_project_id ");
			sql.append("LEFT JOIN (");
			sql.append(repair_duraction_sql);
			sql.append(") t5 ON d.fk_project_id = t5.fk_project_id AND d.repair_class_id = t5.repair_class_id ");
			sql.append("LEFT JOIN (");
			sql.append(complete_total_sql);
			sql.append(") t6 ON d.fk_project_id = t6.fk_project_id AND d.repair_class_id = t6.repair_class_id ");
			sql.append("LEFT JOIN (");
			sql.append(satis_total_sql);
			sql.append(") t7 ON d.fk_project_id = t7.fk_project_id AND d.repair_class_id = t7.repair_class_id ");
			sql.append(" where " + dateSql);
			sql.append("and d.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
			sql.append("group by d.repair_class_id,d.fk_project_id ");
			sql.append(") tt ");
			// 区域项目条件
			if (!"".equals(areaSql.toString())) {
				sql.append("where " + areaSql);
			}
			// 排序
			sql.append(" order by tt.repair_class_id ");
			// 查询数据
			models = workTaskStatisticsService.queryList(sql.toString());
			// 查询一级分类
			Query classQuery = Query.from(YJWYWorkTaskRepairClassModel.META_ID);
			classQuery.where(new Condition("parent_id", QueryContents.TYPE_EQ, 0));
			classQuery.and(new Condition("pk_crop", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
			repairClassModels = repairClassBusinessService.query(classQuery);
			// 获取项目集合
			for (Map<String, Object> map : models) {
				projects.add((String) map.get("pk_project"));
			}
			if (projects.size() > 0) {
				projectModels = projectBusinessService.query(Query.from(YJWYProjectModel.META_ID).where(new Condition("pk_project_", QueryContents.TYPE_IN, projects)));
				// 项目
				YJWYProjectModel projectModel = null;
				String pk_project = null;
				// 维修类型
				YJWYWorkTaskRepairClassModel classModel = null;
				String pk_class_id = null;
				// 基础数据
				Map<String, Object> modelMap = null;
				String model_class_id = null;
				String model_pk_project = null;
				// 小计
				Map<String, Object> cozenMap = new HashMap<>();
				cozenMap.put("class_name", "小计");
				// 循环项目集合
				for (int i = 0; i < projectModels.length; i++) {
					projectModel = projectModels[i];
					pk_project = projectModel.getPk_project();
					// 小计初始化
					Integer repair_total = 0;
					Double repair_duraction = 0.0;
					Integer complete_total = 0;
					Integer satis_total = 0;
					// 循环维修类型
					for (int j = 0; j < repairClassModels.length; j++) {
						classModel = repairClassModels[j];
						pk_class_id = classModel.getPk_class_id();
						// 循环基础数据
						for (int k = 0; k < models.size(); k++) {
							modelMap = models.get(k);
							model_class_id = modelMap.get("repair_class_id").toString();
							model_pk_project = modelMap.get("pk_project").toString();
							// 判断属于当前项目+维修类型的数据model
							if (model_class_id.equals(pk_class_id) && model_pk_project.equals(pk_project)) {
								// 当前项目下的参数设置给类型
								this.getDataRow(classModel, i + "", modelMap.get("repair_total"), modelMap.get("repair_duraction"), modelMap.get("all_total"), modelMap.get("complete_total"), modelMap.get("satis_total"));
								// 小计叠加
								repair_total += Integer.parseInt(modelMap.get("repair_total").toString());
								repair_duraction += Double.parseDouble(modelMap.get("repair_duraction").toString());
								complete_total += Integer.parseInt(modelMap.get("complete_total").toString());
								satis_total += Integer.parseInt(modelMap.get("satis_total").toString());
								break;
							}
							// 如果没有符合的则赋默认值
							this.getDataRow(classModel, i + "", 0, 0, 0, 0, 0);
						}
					}
					// 小计
					this.getDataRow(cozenMap, i + "", repair_total, repair_duraction, repair_total, complete_total, satis_total);
					// 项目第一行表头---省略
					// 项目第二行表头
					secondColumns.addAll(this.getSecondColumns(i + "", "repair_total", "repair_duraction", "repair_avgduraction", "repair_rate", "complete_total", "complete_rate", "satis_rate"));
				}
				// 总计小计初始化
				Integer repair_total = 0;
				Double repair_duraction = 0.0;
				Integer complete_total = 0;
				Integer satis_total = 0;
				// 循环维修类型
				for (int j = 0; j < repairClassModels.length; j++) {
					classModel = repairClassModels[j];
					// 初始化总计
					Integer repair_total2 = 0;
					Double repair_duraction2 = 0.0;
					Integer complete_total2 = 0;
					Integer satis_total2 = 0;
					Integer all_total2 = 0;
					for (int i = 0; i < projectModels.length; i++) {
						// 总计累加
						repair_total2 += Integer.parseInt(classModel.getAttribute("repair_total" + i).toString());
						repair_duraction2 += Double.parseDouble(classModel.getAttribute("repair_duraction" + i).toString());
						complete_total2 += Integer.parseInt(classModel.getAttribute("complete_total" + i).toString());
						satis_total2 += Integer.parseInt(classModel.getAttribute("satis_total" + i).toString());
						all_total2 += Integer.parseInt(classModel.getAttribute("all_total" + i).toString());
					}
					// 总计赋值
					this.getDataRow(classModel, "", repair_total2, repair_duraction2 * 60, all_total2, complete_total2, satis_total2);
					// 总计小计累加
					repair_total += Integer.parseInt(classModel.getAttribute("repair_total").toString());
					repair_duraction += Double.parseDouble(classModel.getAttribute("repair_duraction").toString());
					complete_total += Integer.parseInt(classModel.getAttribute("complete_total").toString());
					satis_total += Integer.parseInt(classModel.getAttribute("satis_total").toString());
				}
				// 总计小计赋值
				this.getDataRow(cozenMap, "", repair_total, repair_duraction * 60, repair_total, complete_total, satis_total);
				// 小计插入行
				rows = new YJWYWorkTaskRepairClassModel[repairClassModels.length + 1];
				for (int j = 0; j < repairClassModels.length; j++) {
					rows[j] = repairClassModels[j];
				}
				rows[rows.length - 1] = (YJWYWorkTaskRepairClassModel) DeviceUtil.map2Model(cozenMap, YJWYWorkTaskRepairClassModel.class);

			}
		}
		// 添加合计的第二行表头
		secondColumns.addAll(this.getSecondColumns("", "repair_total", "repair_duraction", "repair_avgduraction", "repair_rate", "complete_total", "complete_rate", "satis_rate"));
		ModelAndResult mar = new ModelAndResult();
		mar.setAttribute("rows", rows);
		// 生成列
		mar.setAttribute("firstColumns", this.getFirstColumns(projectModels));
		mar.setAttribute("secondColumns", secondColumns);
		return mar;
	}

	/**
	 * 对数据行进行赋值设置
	 * 
	 * @param map
	 *            数据行map对象
	 * @param len
	 *            项目序号
	 * @param repair_total
	 *            维修单量
	 * @param repair_duraction
	 *            维修工时(分钟)
	 * @param all_total
	 *            项目下维修总量
	 * @param complete_total
	 *            完成单量
	 * @param satis_total
	 *            满意单量
	 * @return
	 */
	private Map<String, Object> getDataRow(Map<String, Object> map, String len, Object repair_total, Object repair_duraction, Object all_total, Object complete_total, Object satis_total) {
		map.put("repair_total" + len, repair_total);
		map.put("repair_duraction" + len, DeviceUtil.doubleForDivision(repair_duraction.toString(), "60"));
		map.put("repair_avgduraction" + len, DeviceUtil.doubleForDivision(DeviceUtil.doubleForDivision(repair_duraction.toString(), "60"), repair_total.toString()));
		map.put("repair_rate" + len, DeviceUtil.getPercent(repair_total.toString(), all_total.toString()));
		map.put("complete_total" + len, complete_total);
		map.put("complete_rate" + len, DeviceUtil.getPercent(complete_total.toString(), repair_total.toString()));
		map.put("satis_rate" + len, DeviceUtil.getPercent(satis_total.toString(), complete_total.toString()));
		map.put("all_total" + len, all_total);
		map.put("satis_total" + len, satis_total);
		return map;
	}

	/**
	 * 获取表头第一行对象
	 * 
	 * @param projectModels
	 *            项目数组
	 * @return
	 */
	private List<Map<String, Object>> getFirstColumns(YJWYProjectModel[] projectModels) {
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(this.getColumnMap("class_name", "类型", 120, 2, null));
		int len = 0;
		YJWYProjectModel model = null;
		for (int i = 0; i < projectModels.length; i++) {
			model = projectModels[i];
			list.add(this.getColumnMap("project_name" + len, model.getProject_name(), 300, null, 7));
			len++;
		}
		list.add(this.getColumnMap("total", "合计", 300, null, 7));
		return list;
	}

	/**
	 * 获取表头第二行对象
	 * 
	 * @param len
	 *            项目序号
	 * @param repair_total
	 *            维修单量（宗）
	 * @param repair_duraction
	 *            维修工时（小时）
	 * @param repair_avgduraction
	 *            平均时长
	 * @param repair_rate
	 *            占维修总量比率
	 * @param complete_total
	 *            维修完成量
	 * @param complete_rate
	 *            完成率
	 * @param satis_rate
	 *            满意率
	 * @return
	 */
	private List<Map<String, Object>> getSecondColumns(String len, String repair_total, String repair_duraction, String repair_avgduraction, String repair_rate, String complete_total, String complete_rate, String satis_rate) {
		List<Map<String, Object>> list = new ArrayList<>();
		list.add(this.getColumnMap(repair_total + len, "维修单量（宗）", null, null, null));
		list.add(this.getColumnMap(repair_duraction + len, "维修工时（小时）", null, null, null));
		list.add(this.getColumnMap(repair_avgduraction + len, "平均时长", null, null, null));
		list.add(this.getColumnMap(repair_rate + len, "占维修总量比率", null, null, null));
		list.add(this.getColumnMap(complete_total + len, "维修完成量", null, null, null));
		list.add(this.getColumnMap(complete_rate + len, "完成率", null, null, null));
		list.add(this.getColumnMap(satis_rate + len, "满意率", null, null, null));
		return list;
	}

	/**
	 * 获取列对象
	 * 
	 * @param field
	 *            属性名
	 * @param title
	 *            标题名
	 * @param width
	 *            宽度
	 * @param rowspan
	 *            合并行数
	 * @param colspan
	 *            合并列数
	 * @return
	 */
	private Map<String, Object> getColumnMap(String field, String title, Integer width, Integer rowspan, Integer colspan) {
		Map<String, Object> map = new HashMap<>();
		map.put("field", field);
		map.put("title", title);
		map.put("width", width);
		map.put("rowspan", rowspan);
		map.put("colspan", colspan);
		map.put("hidden", false);
		map.put("align", "center");
		return map;
	}

	/**
	 * 导出报表
	 * 
	 * @param params
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult doExpDataStructureByTable(@RequestParam(value = "param", required = false) String param) {
		ParamEntity params = JSONObject.parseObject(param, ParamEntity.class);
		return this.doExpExcel(this.query(params), new String[] { "repair_total", "repair_duraction", "repair_avgduraction", "repair_rate", "complete_total", "complete_rate", "satis_rate" }, "templates/templates/worktask/工单管理片区维修种类统计表.xls");
	}

	/**
	 * 设置报表
	 * 
	 * @param object
	 * @param fields
	 * @param docTemplatePath
	 * @param docWriteStartRowIndex
	 * @return
	 */
	private ModelAndResult doExpExcel(ModelAndResult mr, String[] fields, String docTemplatePath) {
		docTemplatePath = docTemplatePath.replaceAll("\\\\", "/");
		String projectPath = ImpAndExpExcel.class.getResource("/").getPath().replaceAll("\\\\", "/");
		if (!docTemplatePath.contains(projectPath)) {
			docTemplatePath = projectPath + "/" + docTemplatePath;
		}
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(docTemplatePath));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			return new ModelAndResult(false, e1.getLocalizedMessage());
		}
		HSSFWorkbook workbook = null;
		try {
			// 把一张xls的数据表读到workbook里
			workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
		} catch (IOException e1) {
			e1.printStackTrace();
			return new ModelAndResult(false, e1.getLocalizedMessage());
		}
		// 读取第一页,一般一个excel文件会有三个工作表，这里获取第一个工作表来进行操作
		HSSFSheet sheet = workbook.getSheetAt(0);

		// 分解数据
		JSONArray rows = (JSONArray) JSONArray.toJSON(mr.get("rows"));
		JSONArray firstColumns = (JSONArray) JSONArray.toJSON(mr.get("firstColumns"));
		JSONArray secondColumns = (JSONArray) JSONArray.toJSON(mr.get("secondColumns"));

		// 合并的行列数
		int rowSpan = 2 - 1;
		int firstRow = 0;
		int lastRow = 0;
		int firstCol = 0;
		int lastCol = 0;
		// 个数
		int len = 0;
		// 单元格对象
		CellRangeAddress cra = null;
		HSSFCell tempCell = null;
		// 标题单元格字体
		HSSFFont titleFont = workbook.createFont();
		titleFont.setFontName("黑体");
		titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体显示
		titleFont.setFontHeightInPoints((short) 18);
		// 标题单元格样式
		HSSFCellStyle titleStyle = workbook.createCellStyle();
		// 居中
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 背景色
		titleStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titleStyle.setFillForegroundColor(HSSFColor.ROYAL_BLUE.index);
		// 字体
		titleStyle.setFont(titleFont);

		// 小标题单元格样式
		HSSFCellStyle titleStyle2 = workbook.createCellStyle();
		// 居中
		titleStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 背景色
		titleStyle2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		titleStyle2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);

		// 数据对象
		JSONObject jsonObj = null;
		// 表头标题
		String title = null;
		// 循环数据设置表头第一行
		for (int i = 0; i < firstColumns.size(); i++) {
			jsonObj = firstColumns.getJSONObject(i);
			title = jsonObj.getString("title");
			// 类型
			if (title.equals("类型")) {
				firstRow = 1;
				lastRow = 1 + rowSpan;
				firstCol = 0;
				lastCol = 0;
				// 项目+合计
			} else {
				len++;
				firstRow = 1;
				lastRow = 1;
				firstCol = 1 + (len - 1) * 7;
				lastCol = len * 7;
			}
			// 创建合并单元格
			cra = new CellRangeAddress(firstRow, lastRow, firstCol, lastCol);
			// sheet添加合并单元格
			sheet.addMergedRegion(cra);
			// 获取单元格
			tempCell = ImpAndExpExcel.getCell(sheet, firstRow, firstCol, true);
			// 赋值
			tempCell.setCellValue(title);
			// 样式设置
			tempCell.setCellStyle(titleStyle2);
		}
		// 设置标题单元格
		// 创建合并单元格
		cra = new CellRangeAddress(0, 0, 0, len * 7);
		// sheet添加合并单元格
		sheet.addMergedRegion(cra);
		// 获取单元格
		tempCell = ImpAndExpExcel.getCell(sheet, 0, 0, true);
		// 设置行高
		sheet.getRow(0).setHeightInPoints(34);
		// 赋值
		tempCell.setCellValue("片区维修种类统计表");
		// 样式设置
		tempCell.setCellStyle(titleStyle);
		// 循环数据设置表头第二行
		for (int i = 0; i < secondColumns.size(); i++) {
			jsonObj = secondColumns.getJSONObject(i);
			tempCell = ImpAndExpExcel.getCell(sheet, 2, i + 1, true);
			// 设置列宽
			sheet.setColumnWidth(i + 1, 16 * 256);
			tempCell.setCellStyle(titleStyle2);
			// 单元格赋值
			tempCell.setCellValue(jsonObj.getString("title"));
		}

		// 循环数据设置数据
		for (int i = 0; i < rows.size(); i++) {
			jsonObj = rows.getJSONObject(i);
			// 类型
			tempCell = ImpAndExpExcel.getCell(sheet, i + 3, 0, true);
			tempCell.setCellValue(jsonObj.getString("class_name"));
			// len-1 项目个数
			for (int j = 0; j < len; j++) {
				for (int colIndex = 0; colIndex < fields.length; colIndex++) {
					// 数据
					tempCell = ImpAndExpExcel.getCell(sheet, i + 3, j * 7 + 1 + colIndex, true);
					tempCell.setCellValue(jsonObj.getString(fields[colIndex] + j));
					if ((len - 1) == j) {
						// 数据
						tempCell = ImpAndExpExcel.getCell(sheet, i + 3, (len - 1) * 7 + 1 + colIndex, true);
						tempCell.setCellValue(jsonObj.getString(fields[colIndex]));
					}
				}
			}
		}
		ServletOutputStream out = null;
		String excName = docTemplatePath.substring(docTemplatePath.lastIndexOf("/") + 1);
		try {
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + new String(excName.getBytes("GB2312"), "ISO8859-1") + "\";");//
			out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			return new ModelAndResult();
		} catch (IOException e) {
			e.printStackTrace();
			return new ModelAndResult(false, e.getLocalizedMessage());
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
