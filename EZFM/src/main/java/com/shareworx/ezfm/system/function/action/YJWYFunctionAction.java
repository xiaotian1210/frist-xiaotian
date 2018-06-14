package com.shareworx.ezfm.system.function.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shareworx.ezfm.baseinfo.role.service.YJWYRoleUserDomainService;
import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel;
import com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncDomainService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.login.vo.UiFunctionVo;
import com.shareworx.ezfm.pub.easyui.tree.vo.EasyUiPlanTreeVO;
import com.shareworx.ezfm.system.function.model.YJWYFunctionModel;
import com.shareworx.ezfm.system.function.service.YJWYFunctionBusinessService;
import com.shareworx.ezfm.system.function.service.YJWYFunctionDomainService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryOrder;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.StringUtils;

/**
 * 系统功能操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/system/function")
public class YJWYFunctionAction {
	
	@Autowired
	private Environment evn;

	final static Logger log = Logger.getLogger(YJWYFunctionAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "system/function_listcard";
	
	@Autowired
	@Qualifier(YJWYFunctionBusinessService.ID)
	private YJWYFunctionBusinessService service;
	
	public void setService(YJWYFunctionBusinessService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(YJWYFunctionDomainService.ID)
	private YJWYFunctionDomainService domainService;
	
	public void setDomainService(YJWYFunctionDomainService domainService) {
		this.domainService = domainService;
	}
	
	@Autowired
	@Qualifier(YJWYRoleFuncDomainService.ID)
	private YJWYRoleFuncDomainService roleFuncDomainService;
	
	public void setRoleFuncDomainService(YJWYRoleFuncDomainService roleFuncDomainService) {
		this.roleFuncDomainService = roleFuncDomainService;
	}
	
	@Autowired
	@Qualifier(YJWYRoleUserDomainService.ID)
	private YJWYRoleUserDomainService roleUserDomainService;
	
	public void setRoleUserDomainService(YJWYRoleUserDomainService roleUserDomainService) {
		this.roleUserDomainService = roleUserDomainService;
	}
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
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
	 * 功能管理 扁平数据查询
	 * @param id
	 * @return
	 */
	@RequestMapping(value="plan/query", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult planQueryAll(@RequestParam(value="types") String types) {
		return new ModelAndResult(queryUIFunction(types));
	}
	/**
	 * 角色功能 功能授权查询操作
	 * @return
	 */
	@RequestMapping(value="role/func/query", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		List<YJWYRoleFuncModel> roleFuns = roleFuncDomainService.queryListByCondition(query);
		List<EasyUiPlanTreeVO> uifuns =  queryUIFunction("menu,function,button");
		Map<String,String> map = new HashMap<>();
		
		for(YJWYRoleFuncModel rfm : roleFuns){
			map.put(rfm.getPk_func(), rfm.getPk_func());
		}
		for(EasyUiPlanTreeVO vo : uifuns){
			if(!StringUtils.isEmpty(map.get(vo.getId()))){
				vo.setChecked(true);
			}
		}
		return new ModelAndResult(uifuns);
	}
	/**
	 * 首页功能 根据登录人查询操作
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value="loginuser/func/query", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult loginQueryForFunction() {
		YJWYUserModel loginUser = UserUtil.getCurrentUser();
		String sql = "";
		if(LoginCommons.QYUSERCODE.equals(loginUser.getUser_code())){
			sql = "select * from yjwy_pub_function where type_ in ('corp_menu','corp_function')  order by order_ ";
		}else if(LoginCommons.DMSUSERCODE.equals(loginUser.getUser_code())){
			sql = "select * from yjwy_pub_function where type_ in ('dms_menu','dms_function')  order by order_ ";
		}else{
			sql = "select * from yjwy_pub_function where id_ in (select pk_func_ from yjwy_pub_role_func where pk_crop_='"+loginUser.getPk_crop()+"' and  pk_role_ in (select pk_role_ from yjwy_pub_role_user where  pk_crop_='"+loginUser.getPk_crop()+"' and pk_user_  = '"+loginUser.getPk_user()+"') ) and type_ in('function','menu') order by order_ ";
		}
		JdbcTemplate read = dao.getReadTemplate();
		List<EasyUiPlanTreeVO> list = read.query(sql, new RowMapper<EasyUiPlanTreeVO>(){
			@Override
			public EasyUiPlanTreeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				EasyUiPlanTreeVO vo = new EasyUiPlanTreeVO();
				vo.setId(rs.getString("id_"));
				vo.setText(rs.getString("name_"));
				vo.setPid(rs.getString("pid_"));
				vo.setState("open");
				vo.setIconCls(rs.getString("icon_"));
				vo.setChecked(false);
				Map<String,String> attributes = new HashMap<>();
				attributes.put("url", rs.getString("url_"));
				attributes.put("type", rs.getString("type_"));
				attributes.put("order", rs.getString("order_"));
				vo.setAttributes(attributes);;
				return vo;
			}
		});
		if("metro".equals(evn.getProperty("homepage.style", "default"))) {
			List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
			Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();
			for(int i=0;i<list.size();i++) {
				EasyUiPlanTreeVO vo = list.get(i);
				Map<String, Object> data = new HashMap<String, Object>();
				data.put("id", vo.getId());
				data.put("name", vo.getText());
				data.put("pid", vo.getPid());
				data.put("url", ((Map<String,String> )(vo.getAttributes())).get("url"));
				data.put("type", ((Map<String,String> )(vo.getAttributes())).get("type"));
				data.put("order", ((Map<String,String> )(vo.getAttributes())).get("order"));
				map.put(vo.getId(), data);
				String pid = vo.getPid();
				if(pid==null||"".equals(pid)||"root".equals(pid)) {
					mapList.add(data);
				}
			}
			Iterator<String> it = map.keySet().iterator();
			while(it.hasNext()) {
				Map<String, Object> data = map.get(it.next());
				String pid = data.get("pid")==null?null:data.get("pid").toString();
				if(pid!=null&&!"".equals(pid)) {
					Map<String, Object>  parent = map.get(pid);
					if(parent==null) {
						continue;
					}
					List<Map<String, Object>> childs = (parent.get("childs")==null?null:(List<Map<String, Object>>)parent.get("childs"));
					if(childs==null) {
						childs = new ArrayList<Map<String, Object>>();
					}
					childs.add(data);
					parent.put("childs", childs);
				}
			}
			return new ModelAndResult(mapList);
		}
		return new ModelAndResult(list);
	}
	/**
	 * @param id
	 * @param state  open展开所有 openroot展开根 closed不展开
	 * @return
	 */
	@RequestMapping(value="all/query/{id}/{state}", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult queryAll(@PathVariable String id,@PathVariable String state) {
		Query query = Query.from(YJWYFunctionModel.META_ID);
		query.and(Condition.create("pid_", "root"));
		List<YJWYFunctionModel> funs = domainService.queryListByCondition(query);
		UiFunctionVo ui = new UiFunctionVo();
		circleQueryForEasyUi(funs,ui,state);
		return new ModelAndResult(ui.getChildren());
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYFunctionModel model) {
		if("menu".equals(model.getType()) || "function".equals(model.getType())){
			if(!doValidateCode(model.getCode(), model.getId()))
				return new ModelAndResult(false,"菜单或功能的编码已经存在，请更换其他编码");;
		}
		if(StringUtils.isEmpty(model.getPid())){
			model.setPid("root");
		}
		YJWYFunctionModel[] rs = service.save(new YJWYFunctionModel[]{model});
		if(null!=rs && rs.length>0){
			model = rs[0];
			EasyUiPlanTreeVO vo = new EasyUiPlanTreeVO();
			vo.setId(model.getId());
			vo.setText(model.getName());
			vo.setPid(model.getPid());
			//vo.setState(state);
			vo.setIconCls(model.getIcon());
			vo.setChecked(false);
			vo.setAttributes(model);
			return new ModelAndResult(new EasyUiPlanTreeVO[]{vo});
		}else{
			return new ModelAndResult(false,"保存失败");
		}
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYFunctionModel model) {
		if("menu".equals(model.getType()) || "function".equals(model.getType())){
			if(!doValidateCode(model.getCode(), model.getId()))
				return new ModelAndResult(false,"菜单或功能的编码已经存在，请更换其他编码");;
		}
		YJWYFunctionModel[] rs = service.update(new YJWYFunctionModel[]{model});
		if(null!=rs && rs.length>0){
			model = rs[0];
			EasyUiPlanTreeVO vo = new EasyUiPlanTreeVO();
			vo.setId(model.getId());
			vo.setText(model.getName());
			vo.setPid(model.getPid());
			//vo.setState(state);
			vo.setIconCls(model.getIcon());
			vo.setChecked(false);
			vo.setAttributes(model);
			return new ModelAndResult(new EasyUiPlanTreeVO[]{vo});
		}else{
			return new ModelAndResult(false,"保存失败");
		}
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYFunctionModel[] models) {
		String inSql = "";
		for(YJWYFunctionModel model : models){
			inSql=inSql+","+model.getId();
		}
		String[] inField = inSql.substring(1).split(",");
		if(null!=inField && inField.length>0){
			roleFuncDomainService.deleteByConditions(Delete.delete(YJWYRoleFuncModel.META_ID).where(Condition.in("pk_func_", inField)));
		}
		YJWYFunctionModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
	
	public boolean doValidateCode(String code,String id){
		Condition con = Condition.in("type_",new String[]{"menu","function"});
		if(!StringUtils.isEmpty(id)){
			YJWYFunctionModel model = service.query(Query.from(YJWYFunctionModel.META_ID).and(Condition.create("id_", id)))[0];
			if(code.equals(model.getCode())){
				return true;
			}
		}
		
		YJWYFunctionModel[] models = service.query(Query.from(YJWYFunctionModel.META_ID).and(con).and(Condition.create("code_", code)));
		if(null!=models && models.length>0){
			return false;
		}
		return true;
	}
	
	//查询功能数据 扁平格式
	public List<EasyUiPlanTreeVO> queryUIFunction(String types){
		Query query = Query.from(YJWYFunctionModel.META_ID);
		String[] inTypes = types.split(",");
		if(null!=inTypes && inTypes.length>0){
			query.and(Condition.in("type_", inTypes));
		}
		query.order(QueryOrder.asc("order_"));
		YJWYFunctionModel[] funs = service.query(query);
		List<EasyUiPlanTreeVO> list = new ArrayList<>();
		for(YJWYFunctionModel model : funs){
			EasyUiPlanTreeVO vo = new EasyUiPlanTreeVO();
			vo.setId(model.getId());
			vo.setText(model.getName());
			vo.setPid(model.getPid());
			if("root".equals(model.getPid())){
				vo.setState("closed");
			}else if(types.contains("button") && "function".equals(model.getType())){
				vo.setState("closed");
			}else{
				vo.setState("open");
			}
			vo.setIconCls(model.getIcon());
			vo.setChecked(false);
			vo.setAttributes(model);
			list.add(vo);
		}
		return list;
	}
	
	/**
	 * 查询root子节点的子孙节点  用于easyUi
	 * @param id
	 * @return
	 */
	public void circleQueryForEasyUi(List<YJWYFunctionModel> models,UiFunctionVo root,String state){
		if(null!=models && models.size()>0){
			for(YJWYFunctionModel fun : models){
				UiFunctionVo uiModel = new UiFunctionVo();
				Query query = Query.from(YJWYFunctionModel.META_ID);
				query.and(Condition.create("pid_", fun.getId()));
				List<YJWYFunctionModel> childs = domainService.queryListByCondition(query);
				if(null!=childs && childs.size()>0){
					circleQueryForEasyUi(childs,uiModel,state);
				}
				uiModel.setId(fun.getId());
				uiModel.setPid(fun.getPid());
				uiModel.setText(fun.getName());
				if("openroot".equals(state)){
					if("root".equals(fun.getPid())){
						uiModel.setState("open");
					}else{
						if(null==childs || childs.size()<1){
							uiModel.setState("open");
						}else{
							uiModel.setState("closed");
						}
					}
				}else{
					uiModel.setState(state);
				}
				uiModel.setIconUrl("");
				uiModel.setIconCls(fun.getIcon());
				uiModel.setUrl(fun.getUrl());
				uiModel.setFun_type("");
				uiModel.setCode(fun.getCode());
				uiModel.setMemo(fun.getMemo());
				root.getChildren().add(uiModel);
			}
		}
	}
}
