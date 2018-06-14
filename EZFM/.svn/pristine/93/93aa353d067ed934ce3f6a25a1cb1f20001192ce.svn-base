package com.shareworx.ezfm.baseinfo.resources.action;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectBusinessService;
import com.shareworx.ezfm.baseinfo.resources.dao.AttrResouorceDao;
import com.shareworx.ezfm.baseinfo.resources.dao.YJWYResourcesDao;
import com.shareworx.ezfm.baseinfo.resources.model.AttrResouorceModel;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourceXlxsModel;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.ezfm.baseinfo.resources.service.AttrResouorceBusinessService;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesBusinessService;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 资源管理操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/resources")
public class YJWYResourcesAction {

	final static Logger log = Logger.getLogger(YJWYResourcesAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/resources";
	/**地点页面 */
	public final static String SONPAGE_TYPE = "baseinfo/type_tree";
	@Autowired
	@Qualifier(YJWYResourcesBusinessService.ID)
	private YJWYResourcesBusinessService service;

	public void setService(YJWYResourcesBusinessService service) {
		this.service = service;
	}
	@Autowired
	@Qualifier(AttrResouorceDao.ID)
	private AttrResouorceDao attdao;
	
	
	public AttrResouorceDao getAttdao() {
		return attdao;
	}


	public void setAttdao(AttrResouorceDao attdao) {
		this.attdao = attdao;
	}
	@Autowired
	@Qualifier(YJWYProjectBusinessService.ID)
	private YJWYProjectBusinessService projectservice;

	public void setProjectservice(YJWYProjectBusinessService projectservice) {
		this.projectservice = projectservice;
	}
	@Autowired
	@Qualifier(AttrResouorceBusinessService.ID)
	private AttrResouorceBusinessService rbservice;
	
	public void setRbservice(AttrResouorceBusinessService rbservice) {
		this.rbservice = rbservice;
	}
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	@Autowired
	@Qualifier(YJWYResourcesDomainService.ID)
	private YJWYResourcesDomainService yjwyResourcesDomainService;

	public void setYjwyResourcesDomainService(YJWYResourcesDomainService yjwyResourcesDomainService) {
		this.yjwyResourcesDomainService = yjwyResourcesDomainService;
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
	 * 转向地点
	 *
	 * @return
	 */
	@RequestMapping(value = "type", method = RequestMethod.GET)
	public ModelAndView SysForward() {
		ModelAndView mv = new ModelAndView(SONPAGE_TYPE);
		return mv;
	}
	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query/{id}", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult queryById(@PathVariable String id) {
        YJWYResourcesModel model = yjwyResourcesDomainService.queryById(id);
        List<Map<String, Object>> attrById = service.findAttrById(id);
        model.put("attr",attrById);
        ModelAndResult mar = new ModelAndResult(model);
        return mar;
    }

	/**
	 * 查询操作
	 *
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value = "param", required = false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYResourcesModel[] models = service.query(query);
		YJWYResourcesDao domainDao = SpringUtils.getBean(YJWYResourcesDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query/tree", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult queryTree(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "type", required = false) int type,@RequestParam(value = "getsomeArea", required = false) String getsomeArea,String keywords) {
		YJWYUserModel userModel = UserUtil.getCurrentUser();
		if(userModel==null) {
			return new ModelAndResult(false, "未登录");
		}
		YJWYResourcesModel[] models = service.queryTree(userModel.getPk_crop(), id, type,getsomeArea,keywords);
		ModelAndResult mar = new ModelAndResult();
		mar.setAttribute("rows", models);
		mar.setAttribute("total",models.length);
		return mar;

	}

	/**
	 * 新增保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYResourcesModel model) {
		
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select count(pk_resources) from yjwy_pub_resources where resources_code='"
				+ model.get("resources_code") + "'";
		int count = read.queryForObject(sql, Integer.class);
		String sql2="select count(pk_resources) from yjwy_pub_resources where  resources_name='"
				+ model.getResources_name()
				+ "'";
//		判断是否有父id
		if(model.getParent_id().equals("0")){
			sql2=sql2+" and project_id='"
					+ model.getProject_id()
					+ "'";
		}else{
			sql2=sql2+" and parent_id='"
					+ model.getParent_id()
					+ "'";
		}
		int count1=read.queryForObject(sql2, Integer.class);
		if (count > 0) {
			return new ModelAndResult(false, "此编码已存在，请查证后重试！");
		}else if(count1 > 0){
			return new ModelAndResult(false, "该资源下已存在此名称，请查证后重试！");
		}
		YJWYResourcesModel[] rs = service.save(new YJWYResourcesModel[] { model });
		//处理并保存数据
		String sql1="select attribute_code from  yjwy_attribute_name";
		List<Map<String, Object>> list1 = read.queryForList(sql1);
		String str="";
		for (Map<String, Object> map : list1) {
			str=str+map.get("attribute_code")+",";
		}
		String[] con =str.split(",");
		
		
		List<String> list = Arrays.asList(con);
		for(String name:con){
			String value = (String) JSON.toJSONString(model.getAttribute(name)).replaceAll("\"", "");
			 if(value != null && !value.equals("") && !value.equals("null")){
				AttrResouorceModel attrmodel = new AttrResouorceModel();
				attrmodel.setPk_resources(model.getPk_resources());
				attrmodel.setAttr_name(name);
				attrmodel.setAttr_value(value);
				rbservice.save(new AttrResouorceModel[]{attrmodel} );
			}
		}
		
		

		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYResourcesModel model) {
		JdbcTemplate read = dao.getReadTemplate();
		if (model.get("resources_code") != null && model.get("pk_resources") != null 
				&& !StringUtils.isEmpty(model.get("resources_code").toString())
				&& !StringUtils.isEmpty(model.get("pk_resources").toString())) {
			String sql = "select count(pk_resources) from yjwy_pub_resources where " + " resources_code='"
					+ model.get("resources_code").toString() + "'";
			int count = read.queryForObject(sql, Integer.class);
			// 修改判断Code是否重复，全库等于1
			if (count == 1) {
				sql = "select count(pk_resources) from yjwy_pub_resources where " + "pk_resources ='"
						+ model.get("pk_resources").toString() + "' " + "and resources_code='"
						+ model.get("resources_code").toString() + "'";
				count = read.queryForObject(sql, Integer.class);
				// 当全库等于1时，不是当前记录的code,提示编码重复
				if (count != 1) {
					return new ModelAndResult(false, "此编码已存在，请查证后重试！");
				}

			}
			String sql3="select count(pk_resources) from yjwy_pub_resources where  resources_name='"
					+ model.getResources_name()
					+ "'";
//			判断是否有父id
			String a=model.get("parent_id").toString();
			if(model.getParent_id().equals(model.getPk_resources())){
				sql3=sql3+" and project_id='"
						+ model.getProject_id()
						+ "'";
			}else{
				sql3=sql3+" and parent_id='"
						+ model.getParent_id()
						+ "'";
			}
			int count3=read.queryForObject(sql3, Integer.class);
			if(count3==1){
				 sql3="select count(pk_resources) from yjwy_pub_resources where  resources_name='"
						+ model.getResources_name()
						+ "' and pk_resources='"
						+ model.getPk_resources()
						+ "'";
//				判断是否有父id
				if(model.getParent_id().equals(model.getPk_resources())){
					sql3=sql3+" and project_id='"
							+ model.getProject_id()
							+ "'";
				}else{
					sql3=sql3+" and parent_id='"
							+ model.getParent_id()
							+ "'";
				}
				count3=read.queryForObject(sql3, Integer.class);
				if(count3 !=1){
					return new ModelAndResult(false,"该资源下已存在此名称，请查证后重试！");
				}
				
			}
			// 全库大于1
			if (count > 1) {
				return new ModelAndResult(false, "此编码已存在，请查证后重试！");
			}
			if(count3 > 1){
				return new ModelAndResult(false,"该资源下已存在此名称，请查证后重试！");
			}
		} else {
			return new ModelAndResult(false, "程序错误，请刷新后重试！");
		}
		YJWYResourcesModel[] rs = service.update(new YJWYResourcesModel[] { model });
		
		//删除
		String sql1="select count(1) from yjwy_attr_resource where pk_resources='"
				+model.getPk_resources()
				+ "'";
		int count = read.queryForObject(sql1, Integer.class);
		if(count>0){
			String sql="delete from yjwy_attr_resource where pk_resources='"
					+model.getPk_resources()
					+ "'";
			attdao.excuteUpdateSql(sql);
	
		}
//		添加
		String sql2="select attribute_code from  yjwy_attribute_name";
		List<Map<String, Object>> list1 = read.queryForList(sql2);
		String str="";
		for (Map<String, Object> map : list1) {
			str=str+map.get("attribute_code")+",";
		}
		String[] con =str.split(",");
		
		
		List<String> list = Arrays.asList(con);
		for(String name:con){
			String value = (String) JSON.toJSONString(model.getAttribute(name)).replaceAll("\"", "");
			 if(value != null && !value.equals("") && !value.equals("null")){
				AttrResouorceModel attrmodel = new AttrResouorceModel();
				attrmodel.setPk_resources(model.getPk_resources());
				attrmodel.setAttr_name(name);
				attrmodel.setAttr_value(value);
				rbservice.save(new AttrResouorceModel[]{attrmodel} );
			}
		}
		return new ModelAndResult(rs);
	}

	/**
	 * 删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYResourcesModel[] models) {
		YJWYResourcesModel[] rs = service.delete(models);
		JdbcTemplate read = dao.getReadTemplate();
//		删除属性表中的数据
		String sql1="select count(1) from yjwy_attr_resource where pk_resources='"
				+models[0].getPk_resources()
				+ "'";
		int count = read.queryForObject(sql1, Integer.class);
		if(count>0){
			String sql="delete from yjwy_attr_resource where pk_resources='"
					+models[0].getPk_resources()
					+ "'";
			attdao.excuteUpdateSql(sql);
	
		}
		return new ModelAndResult(rs);
	}

	/**
	 * 下载导入模板操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "imptemplete/download", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult downloadImpTemplate() {
		return ImpAndExpExcel.download("templates/templates/baseinfo/资源导入模板.xls");
	}

	/**
	 * 导入Excel
	 * @return
	 */
	@RequestMapping(value="import/excel", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult impExcel(YJWYResourceXlxsModel vo){
		String[] files = new String[]{"pk_resources","project_name",
				"resourcesType1","resourcesType2","resourcesType3","resourcesType4","resourcesType5","resourcesType6"};
		JSONArray jsonArray;
		try {
			jsonArray = ImpAndExpExcel.doImpExcel(vo.getExcle_file(), files, 2);
		} catch (IOException e) {
			e.printStackTrace();
			return new ModelAndResult(false,"导入失败");
		}
		List<YJWYResourceXlxsModel> list = JSONArray.parseArray(jsonArray.toJSONString(), YJWYResourceXlxsModel.class);
		String error="";
		//判断名称非空
		if (StringUtils.isEmpty(vo.getImport_type())) {
			error = "导入类型不能为空";
		}
		//判断编码非空
		if (StringUtils.isEmpty(vo.getProject_code())) {
			error = "项目编码不能为空";
		}
		//判断项目编码唯一性
		JdbcTemplate read = dao.getReadTemplate();
		String pk_projectSql = "select pk_project_ from yjwy_pub_project where project_code_='"+vo.getProject_code()+"'";
		List<String> listProbject = read.query(pk_projectSql, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				//报事ID
				return rs.getString("pk_project_");
			}
		});
		
		if (listProbject.size()!=1) {
			error = "项目编码错误，请输入正确项目编码";
		}
		//循环判断数据准确性，并拼接数据；
		//所属项目
		String probjectId = listProbject.get(0);
		//院区
		String resourcesType1="";
		//街道
		String resourcesType2="";
		//楼栋
		String resourcesType3="";
		//单元
		String resourcesType4="";
		//楼层
		String resourcesType5="";
		//房间
		String resourcesType6="";
		int i = 0;
		if (list.size()>0) {
			for (YJWYResourceXlxsModel importVo:list) {
				i++;
				//院区
				resourcesType1 = this.saveResources(probjectId, "resourcesType1", 
						importVo.getResourcesType1(), " ");
				//街道
				if (!StringUtils.isEmpty(resourcesType1)&&resourcesType1!="错误") {
					resourcesType2 = this.saveResources(probjectId, "resourcesType2", 
							importVo.getResourcesType2(), resourcesType1);
				}
				//楼栋
				if (!StringUtils.isEmpty(resourcesType2)&&resourcesType2!="错误") {
					resourcesType3 = this.saveResources(probjectId, "resourcesType3", 
							importVo.getResourcesType3(), resourcesType2);
				}
				//单元
				if (!StringUtils.isEmpty(resourcesType3)&&resourcesType3!="错误") {
					resourcesType4 = this.saveResources(probjectId, "resourcesType4", 
							importVo.getResourcesType4(), resourcesType3);
				}
				//楼层
				if (!StringUtils.isEmpty(resourcesType4)&&resourcesType4!="错误") {
					resourcesType5 = this.saveResources(probjectId, "resourcesType5", 
							importVo.getResourcesType5(), resourcesType4);
				}
				//房间
				if (!StringUtils.isEmpty(resourcesType5)&&resourcesType5!="错误") {
					resourcesType6 = this.saveResources(probjectId, "resourcesType6", 
							importVo.getResourcesType6(), resourcesType5);
				}
				System.out.println("成功导入"+i+"条");
			}
		}
		if (!StringUtils.isEmpty(error)) {
			return new ModelAndResult(false,error);
		}
		return new ModelAndResult(true,"导入成功");
	}

	
	/**
	 * 
	 * @param probjectId 项目ID
	 * @param resourcesType 资源类型
	 * @param resourcesTypeName 资源名称
	 * @param parent_id 父级ID
	 * @return
	 */
	public String saveResources(String probjectId,
			String resourcesType,String resourcesTypeName,String parent_id){
		//当前资源ID
		String resourcesTypeId;
		
		YJWYResourcesModel model = new YJWYResourcesModel();
		JdbcTemplate read = dao.getReadTemplate();
		//判断是否有当前资源
		String Sql = "select pk_resources from yjwy_pub_resources where"
				+ " project_id='"+probjectId+"' and resources_type='"+resourcesType+"'";
		if (StringUtils.isEmpty(resourcesTypeName)) {
			Sql +=" and (resources_name = '' or resources_name is null)";
		}else{
			Sql +=" and resources_name = '"+resourcesTypeName+"'";
		}
		
		if (StringUtils.isEmpty(parent_id)) {
			Sql +=" and (parent_id='' or parent_id is null)";
		}else{
			Sql +=" and parent_id='"+parent_id+"'";
		}
		List<String> pk_resources = read.query(Sql, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				//报事ID
				return rs.getString("pk_resources");
			}
		});
		if (pk_resources.size()>1) {
			return "错误";
		}else if(pk_resources.size()==1){
			resourcesTypeId = pk_resources.get(0);
		}else{
			//如果没有该资源，创建。
			model = new YJWYResourcesModel();
			model.setParent_id(parent_id);
			model.setProject_id(probjectId);
			model.setResources_type(resourcesType);
			model.setResources_name(resourcesTypeName);
			//model.setCreate_time(this.obtainTime());
			//model.setCreate_user(UserUtil.getCurrentUserPk());
			//model.setUpdate_time(System.currentTimeMillis()+"");
			//model.setUpdate_user(UserUtil.getCurrentUserPk());
			//model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
			YJWYResourcesModel [] LCmodel = service.save(new YJWYResourcesModel[]{model});
			resourcesTypeId = LCmodel[0].getPk_resources();
		}
		return resourcesTypeId;
	}
	
	//公共方法，获取当前时间
		public String obtainTime(){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			return df.format(new Date());
		}
}