package com.shareworx.ezfm.worktask.statistics.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.statistics.service.YJWYWorkTaskStatisticsService;

/**
 * 维修单量工时统计表控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/worktask/manhours")
public class YJWYWorkTaskManhoursAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskManhoursAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/workTask/statistics/worktask_manhours_list";

	@Autowired
	@Qualifier(YJWYWorkTaskStatisticsService.ID)
	private YJWYWorkTaskStatisticsService workTaskStatisticsService;

	public void setWorkTaskStatisticsService(YJWYWorkTaskStatisticsService workTaskStatisticsService) {
		this.workTaskStatisticsService = workTaskStatisticsService;
	}

	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	public void setDeviceService(YJWYDeviceService deviceService) {
		this.deviceService = deviceService;
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
		String pk_project = params.getPk_project();
		String start_time = params.getStart_time();
		String end_time = params.getEnd_time();
		StringBuilder projectSql = new StringBuilder("");
		StringBuilder areaSql = new StringBuilder("");
		StringBuilder dateSql = new StringBuilder("");
		if (!DeviceUtil.stringIsEmpty(pk_project) && !"default".equals(pk_project)) {
			projectSql.append(" where tt.pk_project = '" + pk_project + "' ");
		} else if (!DeviceUtil.stringIsEmpty(pk_area) && !"default".equals(pk_area)) {
			areaSql.append(" where tt.pk_area = '" + pk_area + "' ");
		} else {
			Set<String> set = deviceService.queryProjectIdsByPkUser(UserUtil.getCurrentPk());
			if (!ArrayUtils.isEmpty(set)) {
				projectSql.append(" where " + DeviceUtil.getInNotInSql("tt.pk_project", QueryContents.TYPE_IN, set.toArray(new String[] {})) + " ");
			}else {
				projectSql.append(" where 1=2 ");
			}
		}
		if (DeviceUtil.stringIsEmpty(start_time) && DeviceUtil.stringIsEmpty(end_time)) {
			start_time = DeviceUtil.CurrentMonthFirstDay(new Date(), DeviceUtil.YMDHMS);
			end_time = DeviceUtil.date2String(new Date(), DeviceUtil.YMDHMS);
		}
		int days = DeviceUtil.daysBetween(start_time, end_time, DeviceUtil.YMD);
		if (days < 0) {
			days = 0;
		}
		dateSql.append(" create_time >= '" + start_time + "' and create_time <= '" + end_time + "' ");
		// 维修分类下工单总数查询
		StringBuilder classtask_total_sql = new StringBuilder();
		classtask_total_sql.append("select repair_class_id,count(pk_details_id) classtask_total from yjwy_worktask_details ");
		classtask_total_sql.append(" where " + dateSql);
		classtask_total_sql.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		classtask_total_sql.append("group by repair_class_id ");

		// 24小时内完成工单数
		StringBuilder twentyfour_comptotal_sql = new StringBuilder();
		twentyfour_comptotal_sql.append("select t.fk_project_id,t.repair_class_id,count(pk_details_id) twentyfour_comptotal from ");
		twentyfour_comptotal_sql.append("( ");
		twentyfour_comptotal_sql.append("select pk_details_id,fk_project_id,repair_class_id,(case when minute(timediff(finish_time,orders_time))>0 then hour(timediff(finish_time,orders_time))+1 ");
		twentyfour_comptotal_sql.append("when second(timediff(finish_time,orders_time))>0 then hour(timediff(finish_time,orders_time))+1 ");
		twentyfour_comptotal_sql.append("else hour(timediff(finish_time,orders_time)) END ) as time_interval ");
		twentyfour_comptotal_sql.append("from yjwy_worktask_details where task_state=3 ");
		twentyfour_comptotal_sql.append(" and " + dateSql);
		twentyfour_comptotal_sql.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		twentyfour_comptotal_sql.append(") t where t.time_interval<=24 GROUP BY t.fk_project_id ");
		// 完成状态的工单数量
		StringBuilder completion_total_sql = new StringBuilder();
		completion_total_sql.append("select fk_project_id,repair_class_id,count(pk_details_id) completion_total from yjwy_worktask_details  ");
		completion_total_sql.append("where task_state = 3 ");
		completion_total_sql.append(" and " + dateSql);
		completion_total_sql.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		completion_total_sql.append("GROUP BY fk_project_id,repair_class_id ");
		// 接单总数 在总单数中查状态为2和3
		StringBuilder order_total_sql = new StringBuilder();
		order_total_sql.append("select fk_project_id,repair_class_id,count(pk_details_id) order_total from yjwy_worktask_details ");
		order_total_sql.append("where task_state in (2,3) ");
		order_total_sql.append(" and " + dateSql);
		order_total_sql.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		order_total_sql.append("GROUP BY fk_project_id,repair_class_id ");
		// 总工时(分钟)
		StringBuilder all_workmins_sql = new StringBuilder();
		all_workmins_sql.append("select fk_project_id,repair_class_id,SUM(finished_duration) all_workmins ");
		all_workmins_sql.append("from yjwy_worktask_details ");
		all_workmins_sql.append(" where " + dateSql);
		all_workmins_sql.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		all_workmins_sql.append("group by fk_project_id,repair_class_id  ");
		// 涉及到维修人+协助人的总数
		StringBuilder user_total_sql = new StringBuilder();
		user_total_sql.append("select t1.fk_project_id,t1.repair_class_id,(t2.help_user_total+t1.repair_user_total) user_total from ( ");
		user_total_sql.append("select fk_project_id,repair_class_id,count(repair_user_id) repair_user_total from yjwy_worktask_details ");
		user_total_sql.append(" where " + dateSql);
		user_total_sql.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		user_total_sql.append("GROUP BY fk_project_id,repair_class_id ");
		user_total_sql.append(") t1 LEFT JOIN ( ");
		user_total_sql.append("select t1.fk_project_id,repair_class_id,count(t2.fk_user_id) help_user_total from yjwy_worktask_details t1 ");
		user_total_sql.append("LEFT JOIN yjwy_worktask_assist_user_record t2 ON t2.fk_details_id = t1.pk_details_id  ");
		user_total_sql.append(" where " + dateSql);
		user_total_sql.append("and t1.pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		user_total_sql.append("GROUP BY t1.fk_project_id,t1.repair_class_id ");
		user_total_sql.append(") t2 ON t1.fk_project_id = t2.fk_project_id AND t1.repair_class_id = t2.repair_class_id ");
		// 维修分类+项目下工单总数查询
		StringBuilder task_total_sql = new StringBuilder();
		task_total_sql.append("select fk_project_id,repair_class_id,count(pk_details_id) task_total from yjwy_worktask_details ");
		task_total_sql.append(" where " + dateSql);
		task_total_sql.append("and pk_crop = '" + DeviceUtil.getPkCrop() + "' ");
		task_total_sql.append("group by fk_project_id,repair_class_id ");

		// 查询语句
		StringBuilder sql = new StringBuilder();
		sql.append("select * from ( ");
		sql.append("select " + days + " days,t1.fk_project_id pk_project,t8.project_name_ project_name,t9.pk_area_ pk_area,t9.area_name_ area_name,t1.repair_class_id pk_class_id,t10.class_name class_name,");
		sql.append("(case when t1.task_total is null then 0 else t1.task_total end ) task_total,");
		sql.append("(case when t2.user_total is null then 0 else t2.user_total end ) user_total,");
		sql.append("(case when t3.all_workmins is null then 0 else t3.all_workmins end ) all_workmins,");
		sql.append("(case when t4.order_total is null then 0 else t4.order_total end ) order_total,");
		sql.append("(case when t5.completion_total is null then 0 else t5.completion_total end ) completion_total,");
		sql.append("(case when t6.twentyfour_comptotal is null then 0 else t6.twentyfour_comptotal end ) twentyfour_comptotal,");
		sql.append("(case when t7.classtask_total is null then 0 else t7.classtask_total end ) classtask_total ");
		sql.append("from ");
		sql.append("( ");
		sql.append(task_total_sql);
		sql.append(") t1 LEFT JOIN ");
		sql.append("( ");
		sql.append(user_total_sql);
		sql.append(") t2 ON t2.fk_project_id = t1.fk_project_id AND t2.repair_class_id = t1.repair_class_id LEFT JOIN ");
		sql.append("( ");
		sql.append(all_workmins_sql);
		sql.append(") t3 ON t3.fk_project_id = t1.fk_project_id AND t3.repair_class_id = t1.repair_class_id LEFT JOIN ");
		sql.append("( ");
		sql.append(order_total_sql);
		sql.append(") t4 ON t4.fk_project_id = t1.fk_project_id AND t4.repair_class_id = t1.repair_class_id LEFT JOIN ");
		sql.append("( ");
		sql.append(completion_total_sql);
		sql.append(") t5 ON t5.fk_project_id = t1.fk_project_id AND t5.repair_class_id = t1.repair_class_id LEFT JOIN ");
		sql.append("( ");
		sql.append(twentyfour_comptotal_sql);
		sql.append(") t6 ON t6.fk_project_id = t1.fk_project_id AND t6.repair_class_id = t1.repair_class_id LEFT JOIN ");
		sql.append("( ");
		sql.append(classtask_total_sql);
		sql.append(") t7 ON t7.repair_class_id = t1.repair_class_id LEFT JOIN ");
		sql.append("yjwy_pub_project t8 ON t8.pk_project_ = t1.fk_project_id LEFT JOIN ");
		sql.append("yjwy_pub_area t9 ON t8.pk_area_ = t9.pk_area_   LEFT JOIN  ");
		sql.append("yjwy_worktask_repair_class t10 ON t10.pk_class_id = t1.repair_class_id ");
		sql.append(" ) tt ");
		sql.append(projectSql).append(areaSql);
		sql.append(" order by tt.pk_class_id ");
		// 执行查询
		List<Map<String, Object>> models = workTaskStatisticsService.queryList(sql.toString());
		// 计算
		Set<String> classIds = new TreeSet<>();
		Map<String, Object> classMap = null;
		Map<String, Object> ratioMap = null;
		// 用于重新排序
		List<Map<String, Object>> tempList = new ArrayList<>();
		int all_task = 0;
		long all_minute = 0;
		// 1.循环数据结果集
		for (Map<String, Object> map : models) {
			setParams(map);
			// 获取维修类型id
			classIds.add(map.get("pk_class_id").toString());
			// 叠加工单总数
			all_task += Integer.parseInt(map.get("task_total").toString());
			// 叠加工时总数
			all_minute += Double.parseDouble(map.get("all_workmins").toString());
		}
		// 2.循环类型set集合
		for (String string : classIds) {
			int task_total = 0;
			int user_total = 0;
			long all_workmins = 0;
			int order_total = 0;
			int completion_total = 0;
			int classtask_total = 0;
			int twentyfour_comptotal = 0;
			String class_name = null;
			classMap = new HashMap<>();
			classMap.put("pk_class_id", string);
			classMap.put("days", days);
			classMap.put("project_name", "合计");
			classMap.put("maint_rate", "100%");
			// 循环数据结果集
			for (Map<String, Object> map : models) {
				// 判断数据类型id与类型id
				if (map.get("pk_class_id").equals(string)) {
					task_total += Integer.parseInt(map.get("task_total").toString());
					user_total += Integer.parseInt(map.get("user_total").toString());
					all_workmins += Double.parseDouble(map.get("all_workmins").toString());
					order_total += Integer.parseInt(map.get("order_total").toString());
					completion_total += Integer.parseInt(map.get("completion_total").toString());
					twentyfour_comptotal += Integer.parseInt(map.get("twentyfour_comptotal").toString());
					if (classtask_total == 0) {
						classtask_total = Integer.parseInt(map.get("classtask_total").toString());
					}
					if (class_name == null) {
						class_name = map.get("class_name").toString();
					}
					tempList.add(map);
				}
			}
			// 合计map赋值
			classMap.put("class_name", class_name);
			classMap.put("classtask_total", classtask_total);
			classMap.put("task_total", task_total);
			classMap.put("user_total", user_total);
			classMap.put("all_workmins", all_workmins);
			classMap.put("order_total", order_total);
			classMap.put("completion_total", completion_total);
			classMap.put("twentyfour_comptotal", twentyfour_comptotal);
			setParams(classMap);
			// 合计map存入models
			tempList.add(classMap);
			// 占比map
			ratioMap = new HashMap<>();
			// 占比map赋值
			ratioMap.put("all_task", all_task);
			ratioMap.put("all_minute", all_minute);
			ratioMap.put("pk_class_id", string);
			ratioMap.put("project_name", "占比");
			ratioMap.put("class_name", class_name);
			ratioMap.put("task_total", DeviceUtil.getPercent(classtask_total + "", all_task + ""));
			ratioMap.put("all_workhours", DeviceUtil.getPercent(all_workmins + "", all_minute + ""));
			// 占比map存入models
			tempList.add(ratioMap);
		}
		long count = workTaskStatisticsService.queryCount(sql.toString());
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", tempList);
		return mar;
	}

	/**
	 * 计算额外参数
	 * 
	 * @param map
	 */
	private void setParams(Map<String, Object> map) {
		Object obj1 = null;
		Object obj2 = null;
		int days = Integer.parseInt(map.get("days").toString());
		// 人均日单数
		obj1 = map.get("completion_total");
		obj2 = map.get("user_total");
		if (obj1 == null || obj2 == null || Integer.valueOf(obj1.toString()) == 0 || days == 0 || Integer.valueOf(obj2.toString()) == 0) {
			map.put("apiece_day_avg", "0.0");
		} else {
			map.put("apiece_day_avg", DeviceUtil.doubleForDivision(DeviceUtil.forDivision(obj1.toString(), days + ""), obj2.toString()));
		}
		// 总工时(小时)
		obj1 = map.get("all_workmins");
		if (obj1 == null) {
			map.put("all_workhours", "0.0");
		} else {
			map.put("all_workhours", DeviceUtil.doubleForDivision(obj1.toString(), "60"));
		}
		// 接单完成率
		obj1 = map.get("completion_total");
		obj2 = map.get("order_total");
		if (obj1 == null || obj2 == null) {
			map.put("order_rate", "0.0%");
		} else {
			map.put("order_rate", DeviceUtil.getPercent(obj1.toString(), obj2.toString()));
		}
		// 每单平均工时
		obj1 = map.get("all_workmins");
		obj2 = map.get("completion_total");
		if (obj1 == null || obj2 == null) {
			map.put("workhours_avg", "0.0");
		} else {
			map.put("workhours_avg", DeviceUtil.doubleForDivision(obj1.toString(), obj2.toString()));
		}
		// 人均工时
		obj2 = map.get("user_total");
		if (obj2 == null) {
			map.put("apiece_workhours_avg", "0.0");
		} else {
			map.put("apiece_workhours_avg", DeviceUtil.doubleForDivision(obj1.toString(), obj2.toString()));
		}
		// 人均日工时
		if (obj1 == null || obj2 == null || Double.valueOf(obj1.toString()) == 0 || days == 0 || Double.valueOf(obj2.toString()) == 0) {
			map.put("apiece_day_workhours_avg", "0.0");
		} else {
			map.put("apiece_day_workhours_avg", DeviceUtil.doubleForDivision(DeviceUtil.doubleForDivision(obj1.toString(), obj2.toString()), days + ""));
		}
		// 维修量占比
		obj1 = map.get("task_total");
		obj2 = map.get("classtask_total");
		if (obj1 == null || obj2 == null) {
			map.put("maint_rate", "0.0%");
		} else {
			map.put("maint_rate", DeviceUtil.getPercent(obj1.toString(), obj2.toString()));
		}
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
		ModelAndResult mr = this.query(params);
		return this.doExpExcel(mr.get("rows"), new String[] { "class_name", "project_name", "task_total", "apiece_day_avg", "all_workmins", "all_workhours", "order_total", "completion_total", "twentyfour_comptotal", "order_rate", "workhours_avg", "apiece_workhours_avg", "apiece_day_workhours_avg", "maint_rate" }, "templates/templates/worktask/工单管理维修单量工时统计表.xls", 2);
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
	private ModelAndResult doExpExcel(Object object, String[] fields, String docTemplatePath, Integer docWriteStartRowIndex) {
		docTemplatePath = docTemplatePath.replaceAll("\\\\", "/");
		String projectPath = ImpAndExpExcel.class.getResource("/").getPath().replaceAll("\\\\", "/");
		if (!docTemplatePath.contains(projectPath)) {
			docTemplatePath = projectPath + "/" + docTemplatePath;
		}
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(docTemplatePath));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return new ModelAndResult(false, e1.getLocalizedMessage());
		}
		HSSFWorkbook workbook = null;
		try {
			// 把一张xls的数据表读到workbook里
			workbook = new HSSFWorkbook(new POIFSFileSystem(inputStream));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return new ModelAndResult(false, e1.getLocalizedMessage());
		}
		// 读取第一页,一般一个excel文件会有三个工作表，这里获取第一个工作表来进行操作
		HSSFSheet sheet = workbook.getSheetAt(0);

		// 开始行序号
		int startRowIndex = (null == docWriteStartRowIndex ? 2 : docWriteStartRowIndex);

		JSONArray jsonArr = (JSONArray) JSONArray.toJSON(object);
		String class_name = null;
		// 合并的行数
		int rowSpan = -1;
		Integer start = null;
		CellRangeAddress cra = null;
		HSSFCell tempCell = null;
		JSONObject jsonObj = null;
		// 单元格样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		// 居中
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 循环数据
		for (int i = 0; i <= jsonArr.size(); i++, startRowIndex++) {
			if (i == jsonArr.size()) {
				class_name = "";
			} else {
				jsonObj = jsonArr.getJSONObject(i);
				if (null == class_name) {
					class_name = jsonObj.getString("class_name");
				}
			}
			// 列数
			for (int colIndex = 0; colIndex < fields.length; colIndex++) {
				if (colIndex == 0) {
					if (jsonObj.getString("class_name").equals(class_name)) {
						if (start == null) {
							start = startRowIndex;
						}
						rowSpan++;
					} else {
						// 创建合并单元格
						cra = new CellRangeAddress(start, start + rowSpan, 0, 0);
						sheet.addMergedRegion(cra);
						tempCell = ImpAndExpExcel.getCell(sheet, start, 0, true);
						if (i == jsonArr.size()) {
							tempCell.setCellValue(jsonObj.getString("class_name"));
						} else {
							tempCell.setCellValue(class_name);
						}
						tempCell.setCellStyle(cellStyle);
						// 标识变量初始化
						rowSpan = 0;
						startRowIndex++;
						start = startRowIndex;
						class_name = null;
					}
				} else {
					if (i != jsonArr.size()) {
						// 获取列对象
						tempCell = ImpAndExpExcel.getCell(sheet, startRowIndex, colIndex, true);
						// 单元格赋值
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
