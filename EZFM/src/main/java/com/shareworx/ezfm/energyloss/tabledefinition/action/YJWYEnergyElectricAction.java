package com.shareworx.ezfm.energyloss.tabledefinition.action;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shareworx.ezfm.baseinfo.resources.dao.AttrResouorceDao;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService;
import com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel;
import com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataBusinessService;
import com.shareworx.ezfm.energyloss.tabledefinition.dao.YJWYEnergyElectricDao;
import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel;
import com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricBusinessService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 电表操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/energyloss/tabledefinition")
public class YJWYEnergyElectricAction {

	Logger log = Logger.getLogger(YJWYEnergyElectricAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/energyloss/electric_list";
	/** 地点页面 */
	public final static String ENERGY_LIST = "ezfm/energyloss/energy_statistics";
	@Autowired
	@Qualifier(YJWYEnergyElectricBusinessService.ID)
	private YJWYEnergyElectricBusinessService service;

	public void setService(YJWYEnergyElectricBusinessService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(YJWYRoomBusinessService.ID)
	private YJWYRoomBusinessService areaservice;

	public void setAreaservice(YJWYRoomBusinessService areaservice) {
		this.areaservice = areaservice;
	}

	@Autowired
	@Qualifier(YjwyEnergyDataBusinessService.ID)
	private YjwyEnergyDataBusinessService dateservice;
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
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYEnergyElectricModel[] models = service.query(query);
		YJWYEnergyElectricDao domainDao = SpringUtils.getBean(AttrResouorceDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 转向地点
	 * 
	 * @return
	 */
	@RequestMapping(value = "energy_list", method = RequestMethod.GET)
	public ModelAndView placeForward() {
		ModelAndView mv = new ModelAndView(ENERGY_LIST);
		return mv;
	}

	/**
	 * 拼接数
	 * 
	 * @param id
	 * @param type
	 * @param getsomeArea
	 * @return
	 */
	@RequestMapping(value = "query/eeltree", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryeelTree(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "type", required = false) int type,@RequestParam(value = "energy", required = false) String energy,
			@RequestParam(value = "getsomeArea", required = false) String getsomeArea) {
		YJWYUserModel userModel = UserUtil.getCurrentUser();
		if (userModel == null) {
			return new ModelAndResult(false, "未登录");
		}
		YJWYEnergyElectricModel[] models = service.queryTree(userModel.getPk_crop(), id, type,energy, getsomeArea);
		ModelAndResult mar = new ModelAndResult();
		mar.setAttribute("rows", models);
		return mar;

	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYEnergyElectricModel model) {
		// 验证编码重复
		if (!doValidateCode(model.getEq_code(), model.getEq_id())) {
			return new ModelAndResult(false, "编码已存在，请更换编码");
		}
		// 验证同级节点下名称重复
		String sql = "";
		int count;
		if(model.get("rate")==""){
			model.setRate(1);
		}
		JdbcTemplate read = dao.getReadTemplate();
		if (model.getParent_id().equals("") || model.getParent_id().equals(null)
				|| model.getParent_id().equals(model.getPk_project())) {
			sql = "select count(eq_id) from yjwy_energy_electric where pk_project='" + model.getPk_project()
					+ "' and eq_name='" + model.getEq_name() + "' and delete_flag='0'";
			count = read.queryForObject(sql, Integer.class);
		} else {
			sql = "select count(eq_id) from yjwy_energy_electric where eq_name='" + "" + model.getEq_name()
					+ "' and parent_id='" + model.getParent_id() + "' and delete_flag='0'";
			count = read.queryForObject(sql, Integer.class);
		}
		if (count == 1) {
			return new ModelAndResult(false, "该节点下一存在此名称，请更换名称！");
		}
		// 验证能耗表类型
		String sql2 = "select hydropower from yjwy_energy_electric where eq_id='" + model.getParent_id() + "'";
		if (!model.getParent_id().equals("0") || !model.getParent_id().equals(null)
				|| !model.getParent_id().equals(model.getPk_project())) {
			List<Map<String, Object>> list1 = read.queryForList(sql2);
			if (list1.size() > 0) {
				if (!list1.get(0).get("hydropower").equals(model.getHydropower())) {
					return new ModelAndResult(false, "表类型必须与上级相同，请重新选择！");
				}
			}

		}

		// 填充区域id
		String sql3 = "select  pk_area_ from yjwy_pub_project where pk_project_='"
				+ model.getStringAttribute("pk_project") + "'";
		List<Map<String, Object>> list = read.queryForList(sql3);
		Object obj = new Object();
		if (model.getParent_id().equals("0")) {
			model.setParent_id("");
		}
		obj = list.get(0).get("pk_area_");
		model.setPk_area((String) obj);
		model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		model.setPk_resource(model.getStringAttribute("fk_resource_id"));
		model.setPk_project(model.getStringAttribute("pk_project"));
		YjwyEnergyDataModel datemodel = new YjwyEnergyDataModel();
		YJWYEnergyElectricModel[] rs = service.save(new YJWYEnergyElectricModel[] { model });

		datemodel.setEq_id(rs[0].get("eq_id") + "");
		datemodel.setData(model.getStar_num());
		datemodel.setCreate_time(DateTimeUtil.getSysdateTime());
		datemodel.setCreate_user(UserUtil.getCurrentUserPk());
		datemodel.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		datemodel.setPk_project(model.getPk_project());
		dateservice.save(new YjwyEnergyDataModel[] { datemodel });

		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYEnergyElectricModel model) {
		// 验证编码重复
		if (!doValidateCode(model.getEq_code(), model.getEq_id())) {
			return new ModelAndResult(false, "编码已存在，请更换编码！");
		}
		// 验证同级节点下名称重复问题
		String sql = "";
		int count;
		JdbcTemplate read = dao.getReadTemplate();
		if (model.getParent_id().equals("") || model.getParent_id().equals(null)
				|| model.getParent_id().equals(model.getPk_project())) {
			sql = "select count(eq_id) from yjwy_energy_electric where pk_project='" + model.getPk_project()
					+ "' and eq_name='" + model.getEq_name() + "' and delete_flag='0' ";
			count = read.queryForObject(sql, Integer.class);
		} else {
			sql = "select count(eq_id) from yjwy_energy_electric where eq_name='" + "" + model.getEq_name()
					+ "' and parent_id='" + model.getParent_id() + "' and delete_flag='0'";
			count = read.queryForObject(sql, Integer.class);
		}
		if (count == 1) {
			if (model.getParent_id().equals("") || model.getParent_id().equals(null)
					|| model.getParent_id().equals(model.getPk_project())) {
				sql = "select count(eq_id) from yjwy_energy_electric where pk_project='" + model.getPk_project()
						+ "' and eq_name='" + model.getEq_name() + "' and eq_id='" + model.getEq_id()
						+ "' and delete_flag='0'";
				count = read.queryForObject(sql, Integer.class);
			} else {
				sql = "select count(eq_id) from yjwy_energy_electric where eq_name='" + "" + model.getEq_name()
						+ "' and parent_id='" + model.getParent_id() + "'  and eq_id='" + model.getEq_id()
						+ "' and delete_flag='0'";
				count = read.queryForObject(sql, Integer.class);
			}
			if (count != 1) {
				return new ModelAndResult(false, "该节点下一存在此名称，请更换名称！");
			}

		}
		// 验证能耗表类型
		String sql2 = "select hydropower from yjwy_energy_electric where eq_id='" + model.getParent_id() + "'";
		if (!model.getParent_id().equals("") || !model.getParent_id().equals(null)
				|| !model.getParent_id().equals(model.getPk_project())) {
			List<Map<String, Object>> list1 = read.queryForList(sql2);
			if (list1.size() > 0) {
				if (!list1.get(0).get("hydropower").equals(model.getHydropower())) {
					return new ModelAndResult(false, "表类型必须与上级相同，请重新选择！");
				}
			}

		}
		// 填充区域id
		String sql3 = "select  pk_area_ from yjwy_pub_project where pk_project_='"
				+ model.getStringAttribute("pk_project") + "'";
		List<Map<String, Object>> list = read.queryForList(sql3);
		Object obj = new Object();
		obj = list.get(0).get("pk_area_");
		if (model.getParent_id().equals(model.getPk_project())) {
			model.setParent_id("");
		}
		model.setPk_area((String) obj);
		model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		model.setPk_project(model.getStringAttribute("pk_project"));
		YJWYEnergyElectricModel[] rs = service.update(new YJWYEnergyElectricModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYEnergyElectricModel[] models) {
		String pid = models[0].getEq_id();
		YJWYEnergyElectricModel[] query = service.query(Query.from(YJWYEnergyElectricModel.META_ID)
				.where(Condition.eq("parent_id", pid)).and(Condition.eq("delete_flag", "0")));
		if (query.length > 0) {
			 return new ModelAndResult(false, "此表下以存在子表，不能刪除！");
		}
		for (YJWYEnergyElectricModel model : models) {
			model.setDelete_flag(1);
			model.setPk_resource(model.get("fk_resource_id") + "");
		}
		YJWYEnergyElectricModel[] rs = service.update(models);
		return new ModelAndResult(rs);
	}

	/**
	 * 判断编码是否重复
	 * 
	 * @param code
	 * @param id
	 * @return
	 */
	public boolean doValidateCode(String code, String id) {
		if (!StringUtils.isEmpty(id)) {
			YJWYEnergyElectricModel model = service
					.query(Query.from(YJWYEnergyElectricModel.META_ID).and(Condition.create("eq_id", id)))[0];
			if (code.equals(model.getEq_code())) {
				return true;
			}
		}
		YJWYEnergyElectricModel[] models = service
				.query(Query.from(YJWYEnergyElectricModel.META_ID).and(Condition.create("eq_code", code)));
		if (null != models && models.length > 0) {
			return false;
		}
		return true;
	}
}
