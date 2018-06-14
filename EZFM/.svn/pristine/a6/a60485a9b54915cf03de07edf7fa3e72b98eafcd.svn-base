package com.shareworx.ezfm.quality.proinspect.inspect.standard.action;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.shareworx.ezfm.quality.proinspect.inspect.standard.vo.AnotherVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shareworx.ezfm.baseinfo.pub.service.IBaseInfoQueryService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.model.InspectTaskModel;
import com.shareworx.ezfm.quality.proinspect.inspect.insptask.service.InspectTaskBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.StandardStationModel;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardDomainService;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.service.StandardStationBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.vo.StationStanQueryVo;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.exception.FieldValidRuntimeException;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryOrder;
import com.shareworx.platform.persist.dao.ObjectPersistDao;

/**
 * 核查标准操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/quality/proinspect/inspect/standard")
public class InspectStandardAction {

	final static Logger log = Logger.getLogger(InspectStandardAction.class);

	/** LIST跳转页面 */
	public final static String List_FORWARD = "ezfm/quality/proinspect/inspect/standard/standard_list";
	/** FORM跳转页面 */
	public final static String Form_FORWARD = "ezfm/quality/proinspect/inspect/standard/standard_form";
	/** SONPAGE跳转页面 */
	public final static String SONPAGE_FORWARD = "ezfm/quality/proinspect/inspect/standard/standard_sonpage";
	@Autowired
	@Qualifier(InspectStandardBusinessService.ID)
	private InspectStandardBusinessService service;

	public void setService(InspectStandardBusinessService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(InspectStandardDomainService.ID)
	private InspectStandardDomainService domainservice;

	public void setDomainservice(InspectStandardDomainService domainservice) {
		this.domainservice = domainservice;
	}

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	@Autowired
	@Qualifier(IBaseInfoQueryService.ID)
	IBaseInfoQueryService baseinfoQueryService;

	public void setBaseinfoQueryService(IBaseInfoQueryService baseinfoQueryService) {
		this.baseinfoQueryService = baseinfoQueryService;
	}

	@Autowired
	@Qualifier(InspectTaskBusinessService.ID)
	private InspectTaskBusinessService taskService;

	public void setTaskService(InspectTaskBusinessService taskService) {
		this.taskService = taskService;
	}

	@Autowired
	@Qualifier(StandardStationBusinessService.ID)
	private StandardStationBusinessService standardStationService;

	public void setStandardStationService(StandardStationBusinessService standardStationService) {
		this.standardStationService = standardStationService;
	}

	/**
	 * 转向列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView listForward() {
		return new ModelAndView(List_FORWARD);
	}

	/**
	 * 转向表单页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "mainformpage/{id}", method = RequestMethod.GET)
	public ModelAndView formForward(HttpServletRequest request, @PathVariable String id) {
		if (StringUtils.isEmpty(id) || "null".equalsIgnoreCase(id.trim())) {
			return new ModelAndView(Form_FORWARD);
		} else {
			ModelAndView mv = new ModelAndView(Form_FORWARD);
			InspectStandardModel model = domainservice.queryById(id);
			mv.addObject("mainmodel", model);
			return mv;
		}
	}

	/**
	 * 转向SONPAGE
	 * 
	 * @return
	 */
	@RequestMapping(value = "sonpage/{id}", method = RequestMethod.GET)
	public ModelAndView sonpageForward(HttpServletRequest request, @PathVariable String id) {
		if (StringUtils.isEmpty(id) || "null".equalsIgnoreCase(id.trim())) {
			return new ModelAndView(SONPAGE_FORWARD);
		} else {
			ModelAndView mv = new ModelAndView(SONPAGE_FORWARD);
			InspectStandardModel model = domainservice.queryById(id);
			Map<String, String> pcMap = baseinfoQueryService.queryDictionaryForMap("taskRate", 1);
			Map<String, String> projectCategoryMap = baseinfoQueryService.queryDictionaryForMap("projectCatagory", 1);
			Map<String, String> specialtyMap = baseinfoQueryService.queryDictionaryForMap("propessional", 1);
			model.setProject_category(projectCategoryMap.get(model.getProject_category()));
			model.setSpecialty(specialtyMap.get(model.getSpecialty()));
			model.setInspstan_dkzg_pc(pcMap.get(model.getInspstan_dkzg_pc()));
			model.setInspstan_xmjl_pc(pcMap.get(model.getInspstan_xmjl_pc()));
			model.setInspstan_bmjl_pc(pcMap.get(model.getInspstan_bmjl_pc()));
			model.setInspstan_qyzz_pc(pcMap.get(model.getInspstan_qyzz_pc()));
			model.setInspstan_qyz_pc(pcMap.get(model.getInspstan_qyz_pc()));
			model.setInspstan_zj_pc(pcMap.get(model.getInspstan_zj_pc()));
			mv.addObject("mainmodel", model);
			return mv;
		}
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value = "param", required = false) String param) {
		List<Map<String, Object>> models = domainservice.queryMap(param);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(domainservice.queryCount(param));
		return mar;
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "edition/standard/query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryForStandard(
			@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		JdbcTemplate read = dao.getReadTemplate();
		String baseSql = "select * from yjwy_quality_inspectstandard where pk_crop = '"
				+ UserUtil.getCurrentUser().getPk_crop()
				+ "' and (fk_standardedition = '' or fk_standardedition is null) ";
		// String conditions = query.getConditions();
		List<Condition> conditions = query.getAndList();
		for (Condition c : conditions) {
			if ("inspstan_code".equalsIgnoreCase(c.getKey())) {
				baseSql = baseSql + " and inspstan_code like '%" + c.getValue() + "%' ";
			}
			if ("specialty".equalsIgnoreCase(c.getKey())) {
				baseSql = baseSql + " and specialty = '" + c.getValue() + "' ";
			}
		}
		String sql = baseSql + " order by create_time desc limit " + query.getStart() + "," + query.getLimit();
		List<InspectStandardModel> list = read.query(sql, new RowMapper<InspectStandardModel>() {
			@Override
			public InspectStandardModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				InspectStandardModel model = new InspectStandardModel();
				model.setPk_inspstan(rs.getString("pk_inspstan"));
				model.setInspstan_code(rs.getString("inspstan_code"));
				model.setSpecialty(rs.getString("specialty"));
				model.setInspstan_category(rs.getString("inspstan_category"));
				model.setInspstan_category_description(rs.getString("inspstan_category_description"));
				model.setInspstan_scorevalue(rs.getBigDecimal("inspstan_scorevalue"));
				return model;
			}
		});

		InspectStandardModel[] models = list.toArray(new InspectStandardModel[] {});
		Map<String, String> specialtyMap = baseinfoQueryService.queryDictionaryForMap("qualitySpecialty", 1);
		for (InspectStandardModel model : models) {
			model.setSpecialty(specialtyMap.get(model.getSpecialty()));
		}
		ModelAndResult mar = new ModelAndResult(models);
		String countSql = "select count(*) from (" + baseSql + ")t";
		int count = read.queryForObject(countSql, Integer.class);
		mar.setTotal(count);
		return mar;
	}

	
	@RequestMapping(value = "edition/query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryForEdition(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		JdbcTemplate read = dao.getReadTemplate();
		String baseSql = "select * from yjwy_quality_inspectstandard where pk_crop = '"
				+ UserUtil.getCurrentUser().getPk_crop()
				+ "' and fk_standardedition = '"+query.getAndList().get(0).getValue()+"'";
		String sql = baseSql + " order by create_time desc limit " + query.getStart() + "," + query.getLimit();
		List<InspectStandardModel> list = read.query(sql, new RowMapper<InspectStandardModel>() {
			@Override
			public InspectStandardModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				InspectStandardModel model = new InspectStandardModel();
				model.setPk_inspstan(rs.getString("pk_inspstan"));
				model.setInspstan_code(rs.getString("inspstan_code"));
				model.setSpecialty(rs.getString("specialty"));
				model.setInspstan_category(rs.getString("inspstan_category"));
				model.setInspstan_category_description(rs.getString("inspstan_category_description"));
				model.setInspstan_scorevalue(rs.getBigDecimal("inspstan_scorevalue"));
				return model;
			}
		});

		InspectStandardModel[] models = list.toArray(new InspectStandardModel[] {});
		Map<String, String> specialtyMap = baseinfoQueryService.queryDictionaryForMap("qualitySpecialty", 1);
		for (InspectStandardModel model : models) {
			model.setSpecialty(specialtyMap.get(model.getSpecialty()));
		}
		ModelAndResult mar = new ModelAndResult(models);
		String countSql = "select count(*) from (" + baseSql + ")t";
		int count = read.queryForObject(countSql, Integer.class);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody InspectStandardModel model) {
		YJWYUserModel currentUser = UserUtil.getCurrentUser();
		model.setPk_crop(currentUser.getPk_crop());
		String code = model.getInspstan_code();
		Boolean isPass = false;
		Query query = Query.from(InspectStandardModel.META_ID);
		query.and(Condition.eq("inspstan_code", code).and(Condition.eq(InspectStandardModel.PK_CROP, currentUser.getPk_crop())));
		InspectStandardModel[] rCode = service.query(query);
		if (rCode.length == 0) {
			InspectStandardModel[] rs = service.save(new InspectStandardModel[] { model });
			return new ModelAndResult(rs);
		}
		return new ModelAndResult(isPass, "编码重复！");
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody InspectStandardModel model) {
		String pk_inspstan = model.getPk_inspstan();
		Query query = Query.from(InspectStandardModel.META_ID).where(Condition.eq(InspectStandardModel.PK_INSPSTAN, pk_inspstan));
		InspectStandardModel[] models = service.query(query);
		if(models==null||models.length==0) {
			return new ModelAndResult(false, "标准不存在！");
		}
		InspectStandardModel old = models[0];
		old.setInspstan_code(model.getInspstan_code());
		old.setProject_category(model.getProject_category());
		old.setSpecialty(model.getSpecialty());
		old.setInspstan_scorevalue(model.getDecimalAttribute(InspectStandardModel.INSPSTAN_SCOREVALUE));
		old.setInspstan_category(model.getInspstan_category());
		old.setInspstan_category_description(model.getInspstan_category_description());
		old.setInspstan_performance_norm(model.getInspstan_performance_norm());
		old.setInspstan_usingscope(model.getInspstan_usingscope());
		old.setInspstan_inpectmethod(model.getInspstan_inpectmethod());
		old.setInspstan_secretinquiries(model.getInspstan_secretinquiries());
		old.setInspstan_dkzg_pc(model.getInspstan_dkzg_pc());
		old.setInspstan_bmjl_pc(model.getInspstan_bmjl_pc());
		old.setInspstan_xmjl_pc(model.getInspstan_xmjl_pc());
		old.setInspstan_qyzz_pc(model.getInspstan_qyzz_pc());
		old.setInspstan_qyz_pc(model.getInspstan_qyz_pc());
		old.setInspstan_zj_pc(model.getInspstan_zj_pc());
		if(old.getPk_crop()==null||"".equals(old.getPk_crop())) {
			YJWYUserModel currentUser = UserUtil.getCurrentUser();
			old.setPk_crop(currentUser.getPk_crop());
		}
		
		String code = model.getInspstan_code();
		if(code==null||"".equals(code)) {
			return new ModelAndResult(false, String.format("标准编码不能为空！"));
		}
		query = Query.from(InspectStandardModel.META_ID);
		query.and(Condition.eq("inspstan_code", code).and(Condition.eq(InspectStandardModel.PK_CROP, old.getPk_crop())));
		InspectStandardModel[] rCode = service.query(query);
		if (rCode!=null&&rCode.length>0) {
			if(!rCode[0].getPk_inspstan().equals(model.getPk_inspstan())) {
				return new ModelAndResult(false, String.format("标准编码重复！"));
			}
		}
		InspectStandardModel[] rs = service.update(new InspectStandardModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody InspectStandardModel[] models) {
		String tempIn = "";
		for (InspectStandardModel model : models) {
			tempIn = tempIn + "," + model.getPk_inspstan();
		}
		tempIn = tempIn.substring(1);
		Query query = Query.from(InspectTaskModel.META_ID);
		query.and(Condition.in("fk_standard", tempIn.split(",")));
		InspectTaskModel[] tasks = taskService.query(query);
		if (tasks.length > 0) {
			return new ModelAndResult(false, "标准下已有任务生成，不允许删除。");
		}
		StandardStationModel[] ssms = standardStationService
				.query(Query.from(StandardStationModel.META_ID).and(Condition.in("pk_standard", tempIn.split(","))));
		if (ssms.length > 0) {
			return new ModelAndResult(false, "标准已被岗位关联，请先移除岗位关联。");
		}
		InspectStandardModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}

	/**
	 * 将标准列表加入标准版本
	 * 
	 * @return
	 */
	@RequestMapping(value = "edition/add/{id}/{fk_id}", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult addStandardForEdition(@PathVariable String id, @PathVariable String fk_id) {
		InspectStandardModel model = domainservice.queryById(id);
		model.setFk_standardedition(fk_id);
		InspectStandardModel[] rs = service.update(new InspectStandardModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 将标准列表移出标准版本
	 * 
	 * @return
	 */
	@RequestMapping(value = "edition/remove", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult deleteStandardForEdition(@RequestBody InspectStandardModel[] models) {
		JdbcTemplate write = dao.getWriteTemplate();
		for (InspectStandardModel model : models) {
			final String pk_inspstan = model.getPk_inspstan();
			write.update("update yjwy_quality_inspectstandard set fk_standardedition=null where pk_inspstan = ?",   
	                new PreparedStatementSetter(){  
	                    @Override  
	                    public void setValues(PreparedStatement ps) throws SQLException {  
	                        ps.setString(1, pk_inspstan);  
	                    }  
	        });
		}
		return new ModelAndResult(true);
	}

	/**
	 * 下载导入模板操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "imptemplete/download", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult downloadImpTemplate() {
		return ImpAndExpExcel.download("templates/templates/quality/品质核查标准导入模板.xls");
	}

	/**
	 * 导入Excel
	 * 
	 * @return
	 */
	@RequestMapping(value = "import/excel", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult impExcel(HttpServletRequest request,
			@RequestParam("excleFile") MultipartFile file) {
		String[] files = new String[] { "inspstan_code", "project_category", "specialty", "inspstan_scorevalue",
				"inspstan_category", "inspstan_category_description", "inspstan_performance_norm",
				"inspstan_usingscope", "inspstan_inpectmethod", "inspstan_secretinquiries", "inspstan_dkzg_pc",
				"inspstan_bmjl_pc", "inspstan_xmjl_pc", "inspstan_qyzz_pc", "inspstan_qyz_pc", "inspstan_zj_pc" };
		JSONArray jsonArray;
		try {
			jsonArray = ImpAndExpExcel.doImpExcel(file, files, 2,"核查标准导入模板");
		} catch (Exception e) {
			return new ModelAndResult(false, "请导入正确模板文件");
		}
		List<InspectStandardModel> list = JSONArray.parseArray(jsonArray.toJSONString(), InspectStandardModel.class);
		
		InspectStandardModel model = null;
		int len = 0;
		for (int i = 0; i < list.size(); i++) {
			model = list.get(i);
			// 防止空白行
			if (model == null) {
				continue;
			}
			if (StringUtils.isEmpty(model.getInspstan_code()) 
					&& StringUtils.isEmpty(model.getInspstan_category()) 
					&& StringUtils.isEmpty(model.getInspstan_category_description())
					&& StringUtils.isEmpty(model.getInspstan_performance_norm())
					&& StringUtils.isEmpty(model.getInspstan_usingscope())
					&& StringUtils.isEmpty(model.getInspstan_inpectmethod())) {
				continue;
			}
			len++;
		}
		String pk_crop = UserUtil.getCurrentUser().getPk_crop();
		InspectStandardModel[] models = new InspectStandardModel[len];
		Map<String, String> projectCatagoryMap = baseinfoQueryService.queryDictionaryNameToCodeForMap("projectCatagory", 1);
		Map<String, String> propessionalMap = baseinfoQueryService.queryDictionaryNameToCodeForMap("propessional", 1);
		Map<String, String> taskRateMap = baseinfoQueryService.queryDictionaryNameToCodeForMap("taskRate", 1);
		for (int i = 0; i < list.size(); i++) {
			model = list.get(i);
			if (model == null) {
				continue;
			}
			if (StringUtils.isEmpty(model.getInspstan_code()) 
					&& StringUtils.isEmpty(model.getInspstan_category()) 
					&& StringUtils.isEmpty(model.getInspstan_category_description())
					&& StringUtils.isEmpty(model.getInspstan_performance_norm())
					&& StringUtils.isEmpty(model.getInspstan_usingscope())
					&& StringUtils.isEmpty(model.getInspstan_inpectmethod())) {
				continue;
			}
			if (StringUtils.isEmpty(model.getInspstan_code())) {
				return new ModelAndResult(false, String.format("第%s行数据%s字段存在问题：%s", i+1, "标准编码", "不能为空，请检查"));
			}
			if (StringUtils.isEmpty(model.getProject_category())) {
				return new ModelAndResult(false, String.format("第%s行数据%s字段存在问题：%s", i+1, "项目类别", "不能为空，请检查"));
			}
			if (StringUtils.isEmpty(model.getSpecialty())) {
				return new ModelAndResult(false, String.format("第%s行数据%s字段存在问题：%s", i+1, "标准专业", "不能为空"));
			}
			
			String code = model.getInspstan_code();
			Query query = Query.from(InspectStandardModel.META_ID);
			query.and(Condition.eq("inspstan_code", code).and(Condition.eq(InspectStandardModel.PK_CROP, pk_crop)));
			InspectStandardModel[] rCode = service.query(query);
			if (rCode!=null&&rCode.length>0) {
				return new ModelAndResult(false, String.format("第%s行数据%s字段存在问题：%s", i+1, "标准编码", "数据重复"));
			}
			
			models[i] = model;
			models[i].setProject_category(projectCatagoryMap.get(models[i].getProject_category()));
			models[i].setSpecialty(propessionalMap.get(models[i].getSpecialty()));
			models[i].setInspstan_dkzg_pc(taskRateMap.get(models[i].getInspstan_dkzg_pc()));
			models[i].setInspstan_bmjl_pc(taskRateMap.get(models[i].getInspstan_bmjl_pc()));
			models[i].setInspstan_xmjl_pc(taskRateMap.get(models[i].getInspstan_xmjl_pc()));
			models[i].setInspstan_qyzz_pc(taskRateMap.get(models[i].getInspstan_qyzz_pc()));
			models[i].setInspstan_qyz_pc(taskRateMap.get(models[i].getInspstan_qyz_pc()));
			models[i].setInspstan_zj_pc(taskRateMap.get(models[i].getInspstan_zj_pc()));
			models[i].setPk_crop(pk_crop);
			
		}
		try {
			service.save(models);
		} catch (FieldValidRuntimeException e) {
			return new ModelAndResult(false, String.format("第%s行数据%s字段存在问题：%s", e.getRow()+1, e.getFieldName(), e.getMessage()));
		}
		
		return new ModelAndResult(true, "数据导入成功");
	}

	/**
	 * 导出EXCEL
	 * 
	 * @return
	 */
	@RequestMapping(value = "export/excel", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult expExcel(@RequestParam(value = "param", required = false) String param,String another) {

		AnotherVo vo  = JSON.parseObject(another, AnotherVo.class);
		Query query = JSON.parseObject(param, Query.class);
		query.and(Condition.create("pk_crop", UserUtil.getCurrentUser().getPk_crop()));
		query.addOrder(QueryOrder.desc(InspectStandardModel.CREATE_TIME));
		if(vo ==null || vo.getPage() == null ||vo.getPage().equals("1")){//查询所有
			query.start(0);
			query.limit(0);
		}

		InspectStandardModel[] models = service.query(query);
		Map<String, String> specialtyMap = baseinfoQueryService.queryDictionaryForMap("propessional", 1);
		Map<String, String> projectCategoryMap = baseinfoQueryService.queryDictionaryForMap("projectCatagory", 1);
		Map<String, String> pcMap = baseinfoQueryService.queryDictionaryForMap("taskRate", 1);
		for (InspectStandardModel model : models) {
			model.setProject_category(projectCategoryMap.get(model.getProject_category()));
			model.setSpecialty(specialtyMap.get(model.getSpecialty()));
			model.setInspstan_dkzg_pc(pcMap.get(model.getInspstan_dkzg_pc()));
			model.setInspstan_xmjl_pc(pcMap.get(model.getInspstan_xmjl_pc()));
			model.setInspstan_bmjl_pc(pcMap.get(model.getInspstan_bmjl_pc()));
			model.setInspstan_qyzz_pc(pcMap.get(model.getInspstan_qyzz_pc()));
			model.setInspstan_qyz_pc(pcMap.get(model.getInspstan_qyz_pc()));
			model.setInspstan_zj_pc(pcMap.get(model.getInspstan_zj_pc()));
		}
		String[] files ;
		if(vo == null ||vo.getCk() == null){
			files = new String[] { "inspstan_code", "project_category", "specialty", "inspstan_scorevalue",
					"inspstan_category", "inspstan_category_description", "inspstan_performance_norm",
					"inspstan_usingscope", "inspstan_inpectmethod", "inspstan_secretinquiries", "inspstan_dkzg_pc",
					"inspstan_bmjl_pc", "inspstan_xmjl_pc", "inspstan_qyzz_pc", "inspstan_qyz_pc", "inspstan_zj_pc" };
		}else {
			files = vo.getCk();
		}

		List<InspectStandardModel> list = new ArrayList<>();
		InspectStandardModel model = new InspectStandardModel();
		model.put("inspstan_zj_pc","总监频次");
		model.put("inspstan_qyz_pc","区域总频次");
		model.put("inspstan_qyzz_pc","区域总助频次");
		model.put("inspstan_xmjl_pc","项目经理频次");
		model.put("inspstan_bmjl_pc","部门经理频次");
		model.put("inspstan_dkzg_pc","对口主管频次");
		model.put("inspstan_secretinquiries","是否强制拍照");

		model.put("inspstan_inpectmethod","核查方法");
		model.put("inspstan_usingscope","使用范围");
		model.put("inspstan_performance_norm","质量绩效指标");
		model.put("inspstan_category_description","标准类别描述");
		model.put("inspstan_category","标准类别");
		model.put("inspstan_scorevalue","分值");
		model.setSpecialty("专业");
		model.setProject_category("项目类别");
		model.setInspstan_code("标准编码");
		list.add(model);
		list.addAll(Arrays.asList(models));
//		return ImpAndExpExcel.doExpExcel(models,
//				new String[] { "inspstan_code", "project_category", "specialty", "inspstan_category",
//						"inspstan_category_description", "inspstan_scorevalue" },
//				"templates/templates/quality/品质核查标准.xls", 2);
		return ImpAndExpExcel.doExpExcel(list,
				files,
				"templates/templates/quality/品质核查标准导入模板_2.xls", 1);
	}

	/**
	 * 通过StationId查询其关联的标准 通过StationId查询其没有关联的标准
	 * 
	 * @return
	 */
	@RequestMapping(value = "station/standard/query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryEditionByStationId(@RequestBody StationStanQueryVo query) {
		JdbcTemplate read = dao.getReadTemplate();
		String notInSql = " select * from yjwy_quality_inspectstandard where fk_standardedition in"
				+ " (select pk_edition from yjwy_quality_edition_station where pk_crop = '"
				+ UserUtil.getCurrentUser().getPk_crop() + "' and pk_station = '" + query.getPk_station() + "')"
				+ " and pk_inspstan not in"
				+ " (select pk_standard from yjwy_quality_standard_station where pk_crop = '"
				+ UserUtil.getCurrentUser().getPk_crop() + "' and pk_station = '" + query.getPk_station() + "')";

		String inSql = " select * from yjwy_quality_inspectstandard where fk_standardedition in"
				+ " (select pk_edition from yjwy_quality_edition_station where pk_crop = '"
				+ UserUtil.getCurrentUser().getPk_crop() + "' and pk_station = '" + query.getPk_station() + "')"
				+ " and pk_inspstan in" + " (select pk_standard from yjwy_quality_standard_station where pk_crop = '"
				+ UserUtil.getCurrentUser().getPk_crop() + "' and pk_station = '" + query.getPk_station() + "')";

		String sql;
		if ("in".equalsIgnoreCase(query.getFlag())) {
			sql = inSql;
		} else {
			sql = notInSql;
		}
		sql = " select s.*,e.edition_name from (" + sql
				+ ")s left join yjwy_quality_standardedition e on s.fk_standardedition = e.pk_edition where 1=1";
		if (!StringUtils.isEmpty(query.getEdition_name())) {
			sql = sql + " and e.edition_name like '%" + query.getEdition_name() + "%' ";
		}
		if (!StringUtils.isEmpty(query.getStan_code())) {
			sql = sql + " and s.inspstan_code like '%" + query.getStan_code() + "%' ";
		}
		String countSql = "select count(*) from (" + sql + ")c";
		int count = read.queryForObject(countSql, Integer.class);
		sql = sql + " order by e.edition_name limit " + query.getStart() + "," + query.getLimit();
		List<InspectStandardModel> list = read.query(sql, new RowMapper<InspectStandardModel>() {
			Map<String, String> specialtyMap = baseinfoQueryService.queryDictionaryForMap("propessional", 1);

			@Override
			public InspectStandardModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				InspectStandardModel model = new InspectStandardModel();
				model.setPk_inspstan(rs.getString("pk_inspstan"));
				model.setInspstan_code(rs.getString("inspstan_code"));
				model.setFk_standardedition(rs.getString("edition_name"));// 此位置用来显示所以放NAME
				model.setSpecialty(specialtyMap.get(rs.getString("specialty")));
				model.setInspstan_category(rs.getString("inspstan_category"));
				model.setInspstan_category_description(rs.getString("inspstan_category_description"));
				model.setInspstan_scorevalue(rs.getBigDecimal("inspstan_scorevalue"));
				return model;
			}
		});
		ModelAndResult mar = new ModelAndResult(list.toArray(new InspectStandardModel[] {}));
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 判断是否是 数字或者浮点数
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("^[0-9]+(.[0-9]+)?$");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
}
