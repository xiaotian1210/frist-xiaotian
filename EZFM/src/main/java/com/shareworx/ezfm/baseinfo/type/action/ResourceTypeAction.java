package com.shareworx.ezfm.baseinfo.type.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.buf.UEncoder;
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
import com.shareworx.ezfm.baseinfo.resources.dao.YJWYResourceAttributePkResourceDao;
import com.shareworx.ezfm.baseinfo.resources.model.AttributeNameModel;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourceAttributePkResourceModel;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.ezfm.baseinfo.resources.service.AttributeNameDomainService;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourceAttributePkResourceBusinessService;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourceAttributePkResourceDomainService;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesBusinessService;
import com.shareworx.ezfm.baseinfo.type.dao.ResourceTypeDao;
import com.shareworx.ezfm.baseinfo.type.model.ResourcetypeModel;
import com.shareworx.ezfm.baseinfo.type.service.ResourceTypeBusinessService;
import com.shareworx.ezfm.baseinfo.type.service.ResourceTypeDomainService;
import com.shareworx.ezfm.easyui.model.ZtreeModel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;


/**
 * 资源类型操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/type")
public class ResourceTypeAction {

	Logger log = Logger.getLogger(ResourceTypeAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/type";
	
	@Autowired
	@Qualifier(ResourceTypeBusinessService.ID)
	private ResourceTypeBusinessService service;
	
	public void setService(ResourceTypeBusinessService service) {
		this.service = service;
	}
	@Autowired
	@Qualifier(YJWYResourcesBusinessService.ID)
	private YJWYResourcesBusinessService reservice;
	public void setReservice(YJWYResourcesBusinessService reservice) {
		this.reservice = reservice;
	}
	@Autowired
	@Qualifier(AttributeNameDomainService.ID)
	private AttributeNameDomainService namedomservice;
	
	public void setNamedomservice(AttributeNameDomainService namedomservice) {
		this.namedomservice = namedomservice;
	}
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	@Autowired
	@Qualifier(YJWYResourceAttributePkResourceDao.ID)
	private YJWYResourceAttributePkResourceDao raprdao;

	public void setRaprdao(YJWYResourceAttributePkResourceDao raprdao) {
		this.raprdao = raprdao;
	}
	@Autowired
	@Qualifier(YJWYResourceAttributePkResourceBusinessService.ID)
	private YJWYResourceAttributePkResourceBusinessService attributeservice;

	public void setAttributeservice(YJWYResourceAttributePkResourceBusinessService attributeservice) {
		this.attributeservice = attributeservice;
	}
	@Autowired
	@Qualifier(ResourceTypeDomainService.ID)
	private ResourceTypeDomainService domservice;
	

	public void setDomservice(ResourceTypeDomainService domservice) {
		this.domservice = domservice;
	}
	@Autowired
	@Qualifier(YJWYResourceAttributePkResourceDomainService.ID)
	private YJWYResourceAttributePkResourceDomainService attrdomainservice;
	
	public void setAttrdomainservice(YJWYResourceAttributePkResourceDomainService attrdomainservice) {
		this.attrdomainservice = attrdomainservice;
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
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param,@RequestParam(value="isWindow", required=false)String isWindow ) {
		JdbcTemplate read =dao.getReadTemplate();
		Query query = JSON.parseObject(param, Query.class);
		query.and(Condition.eq("pk_crop_", UserUtil.getCurrentUser().getPk_crop()));
		ResourcetypeModel[] models = service.query(query);
		ResourceTypeDao domainDao = SpringUtils.getBean(ResourceTypeDao.ID);
		long count = domainDao.countListByCondition(query);
		List<ZtreeModel> tree = new ArrayList<>();
		for(ResourcetypeModel m : models ){
			String sql="select t1.attribute_code from yjwy_attribute_name t1 left join yjwy_resource_attribute_pk_resource t2 "
					+ "on t1.attribute_code=t2.pk_attribute  where t2.pk_resource='"
					+ m.getType_id()
					+ "'";
			List<Map<String, Object>> list = read.queryForList(sql);
			String[] attribute_code=new String[list.size()];
			for(int i=0;i<list.size();i++){
				attribute_code[i]=(String) list.get(i).get("attribute_code");
			}
			m.put("attribute_code", attribute_code);
			ZtreeModel model = new ZtreeModel();
			model.setId(m.getType_id());
			model.setName(m.getType_name());
			model.setpId(m.getType_pid());
			model.setAttributes(m);
			tree.add(model);
			
		}
		ModelAndResult mar;
		if(isWindow != null){//处理树形状窗口
			mar = new ModelAndResult();
			mar.put("rows",tree.toArray());
		}else {
			 mar = new ModelAndResult(tree);
		}
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody ResourcetypeModel model) {
//		判断编码是否重复
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select count(type_id_) from yjwy_resource_type where type_code_='"
				+ model.getType_code()+ "' and pk_crop_='"
				+ UserUtil.getCurrentUser().getPk_crop()
				+ "'";
		int count = read.queryForObject(sql, Integer.class);
//		判断同级节点下名称唯一
		String sql1="select count(type_id_) from yjwy_resource_type where type_name_ ='"
				+ model.getType_name()+ "'"+ " and type_pid_='"+ model.getType_pid()+ "'";
		int count1=read.queryForObject(sql1, Integer.class);
		if (count == 1) {
			return new ModelAndResult(false, "此编码已存在，请查证后重试！");
		}else if(count1 >= 1){
			return new ModelAndResult(false, "该类型下一存在此类型，请查证后重试！");
		}
		model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		model.setType_time(DateTimeUtil.getSysDate());
		model.setType_upname(UserUtil.getCurrentUser().getUser_name());
		ResourcetypeModel[] rs = service.save(new ResourcetypeModel[]{model});
//		获取并处理复选框的值
		String str=JSON.toJSONString(model.getAttribute("atribute_code"));
		str=str.replaceAll("\\[", "");
		str=str.replaceAll("\\]", "");
		str=str.replaceAll("\"", "");
		String[] attribute_code =str.split(",");
		for (int i = 0; i < attribute_code.length; i++) {
			YJWYResourceAttributePkResourceModel attributemodel =new YJWYResourceAttributePkResourceModel();
			attributemodel.setPk_resource(model.getType_id());
			attributemodel.setPk_attribute(attribute_code[i]);
			attributemodel.doSave();
		}
		return new ModelAndResult(rs);
	}
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody ResourcetypeModel model) {
		
		JdbcTemplate read = dao.getReadTemplate();
		JdbcTemplate write=dao.getWriteTemplate();
		//编码不能重复
		String sql1 = "select count(type_id_) from yjwy_resource_type where type_code_='"
				+ model.getType_code()
				+ "' and pk_crop_='"
				+ UserUtil.getCurrentUser().getPk_crop()
				+ "'";
		int count = read.queryForObject(sql1, Integer.class);
		if (count == 1) {
			sql1 = "select count(type_id_) from yjwy_resource_type where type_code_='"
					+ model.getType_code()
					+ "' and pk_crop_='"
					+ UserUtil.getCurrentUser().getPk_crop()
					+ "' and type_id_ = '"
					+ model.getType_id()
					+ "'";
			count = read.queryForObject(sql1, Integer.class);
			// 等于1时，不是当前记录的code,提示编码重复
			if (count != 1) {
				return new ModelAndResult(false, "此编码已存在，请查证后重试！");
			}
		}
//		验证修改时同级节点下名称不重复
		String sql2="select count(type_id_) from yjwy_resource_type where type_name_ ='"
				+ model.getType_name()+ "'"+ " and type_pid_='"+ model.getType_pid()+ "'";
		int count2=read.queryForObject(sql2, Integer.class);
//		判断该节点下名称存在
		if(count2==1){
			sql2="select count(type_id_) from yjwy_resource_type where type_name_ ='"
					+ model.getType_name()+ "'"+ " and type_pid_='"+ model.getType_pid()+ "' and type_id_='"
							+ model.getType_id()
							+ "'";
			count2 =read.queryForObject(sql2, Integer.class);
			if(count2 != 1){
				return new ModelAndResult(false,"该类型下已存在此名称，请查证后重试");
			}
		}
		model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		model.setType_time(DateTimeUtil.getSysDate());
		model.setType_upname(UserUtil.getCurrentUser().getUser_name());
		ResourcetypeModel[] rs = service.update(new ResourcetypeModel[]{model});
//		修改资源中的资源类型
		String resql="update yjwy_pub_resources set resourcetype_name='"
				+ model.getType_name()
				+ "' where resourcetype_code='"
				+ model.getType_id()
				+ "'";
		write.update(resql);
		
//		修改关联信息时先删除已有的关联数据
		String sql ="delete from yjwy_resource_attribute_pk_resource where pk_resource = '" + model.getType_id()+"'";
		raprdao.excuteUpdateSql(sql);
//		获取并处理复选框的值
		String str=JSON.toJSONString(model.getAttribute("atribute_code"));
		str=str.replaceAll("\\[", "");
		str=str.replaceAll("\\]", "");
		str=str.replaceAll("\"", "");
		String[] attribute_code =str.split(",");
//		添加多条关联记录
		for (int i = 0; i < attribute_code.length; i++) {
			YJWYResourceAttributePkResourceModel attributemodel =new YJWYResourceAttributePkResourceModel();
			attributemodel.setPk_resource(model.getType_id());
			attributemodel.setPk_attribute(attribute_code[i]);
			attributemodel.doSave();
		}
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody ResourcetypeModel[] models) {
		//删除关联资源属性中数据
		for (ResourcetypeModel resourcetypeModel : models) {
			String sql ="delete from yjwy_resource_attribute_pk_resource where pk_resource = '" + resourcetypeModel.getType_id()+"'";
			raprdao.excuteUpdateSql(sql);
		}
		
		ResourcetypeModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
