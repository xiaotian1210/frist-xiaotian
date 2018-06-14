package com.shareworx.ezfm.device.fmdata_eq.action;

import com.alibaba.fastjson.JSON;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.device.fmdata.service.YJWYFmDataService;
import com.shareworx.ezfm.device.fmdata_eq.dao.YJWYEqSysDao;
import com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel;
import com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysBusinessService;
import com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysDomainService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.easyui.model.ZtreeModel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.cache.IDmsCacheManager;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryOrder;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import org.apache.catalina.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * 设备所属系统操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/device/fmdata_eq")
public class YJWYEqSysAction {

	Logger log = Logger.getLogger(YJWYEqSysAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/device/fmdata_eq/index";
	
	@Autowired
	@Qualifier(YJWYEqSysBusinessService.ID)
	private YJWYEqSysBusinessService service;

	@Autowired
	@Qualifier(YJWYEqSysDomainService.ID)
	YJWYEqSysDomainService domainService;
	public void setService(YJWYEqSysBusinessService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(YJWYFmDataService.ID)
	YJWYFmDataService fmDataService;

	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	YJWYUserDomainService userDomainService;

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
	public @ResponseBody ModelAndResult query(@RequestParam(value = "param", required = false) String param,String isWindow) {
		Query query = JSON.parseObject(param, Query.class);
		query.addSelect("eq_sys_id,code,parent_id,create_time,sort_no,name,description,user_name,eq_class,pk_user",
				"(select count(eq_id) from yjwy_fmdata_eq " +
						"where yjwy_fmdata_eq.fk_sys_all_ids like concat('%',concat(yjwy_device_fmdata_eq_sys.eq_sys_id,'%'))" +
						"and  flag = 1 AND pk_crop = '"+UserUtil.getCurrentUser().getPk_crop()+"'"+
						") as eq_count ");

		query.and(Condition.neq("delete_flag", "1"));
		query.and(Condition.create("pk_crop", UserUtil.getCurrentUser().getPk_crop()));
		query.addOrder(QueryOrder.desc("sort_no"));



		YJWYEqSysModel[] models = service.query(query);

		for(YJWYEqSysModel item:models){
			YJWYUserModel yjwyUserModel = UserUtil.getUser(item.getPk_user());
			if(yjwyUserModel != null){
				item.setUser_name(yjwyUserModel.getUser_name());
			}else {
				item.setUser_name("");
			}

		}


//		userDomainService.queryById()

		//将所属系统转换未树型结构
		Map<String, YJWYEqSysModel> map = new HashMap<String, YJWYEqSysModel>();
		for(int i=0;i<models.length;i++) {
			YJWYEqSysModel model = models[i];
			map.put(model.getEq_sys_id(), model);
		}
		for(int i=0;i<models.length;i++) {
			YJWYEqSysModel model =  models[i];
			if(model.getParent_id()!=null&&!"".equals(model.getParent_id())) {
				YJWYEqSysModel parent = map.get(model.getParent_id());
				List<YJWYEqSysModel> childs = parent.get("childs")==null?null:(List<YJWYEqSysModel>)parent.get("childs");
				if(childs==null) {
					childs = new ArrayList<YJWYEqSysModel>();
				}
				childs.add(model);
				parent.put("childs", childs);
			}
		}
		for(int i = 0;i < models.length;i++){
			YJWYEqSysModel model =  models[i];

                List<YJWYEqSysModel> childs = new ArrayList<>();
                childs.add(model);
			    if(model.get("childs")!=null){
			        childs.addAll((List<YJWYEqSysModel>)model.get("childs"));
                }
                Long eqCount = fmDataService.queryCountBySysId(childs);
                model.setEq_count(eqCount.toString());

		}


//		List<Map<String, Object>> list = read.queryForList(sql);
		List<ZtreeModel> tree = new ArrayList<>();
		for (YJWYEqSysModel m : models) {
			ZtreeModel model = new ZtreeModel();
			model.setId(m.getEq_sys_id());
			model.setpId(m.getParent_id());
			model.setName(m.getName());
			model.setAttributes(m);
			tree.add(model);
		}
		// List<EasyUiTreeModel> tree = EasyUiTreeUtil.covertTreeModel(models);
		ModelAndResult mar;
		if(isWindow != null){//处理树形状窗口
			mar = new ModelAndResult();
			mar.put("rows",tree.toArray());
		}else {
			 mar = new ModelAndResult(tree);
		}

		return mar;
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYEqSysModel model)throws ShareworxServiceException {
		//校验编码和名称
        String b = service.checkNameAndCodeUnique(model.getParent_id(), model.getName(), model.getCode(), null);
        if(b != null){
            return new ModelAndResult(false,b);
        }
        model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		model.setPk_user(UserUtil.getCurrentUserPk());
		model.setUser_name(UserUtil.getCurrentUser().getUser_name());
		model.setCreate_time(DateTimeUtil.getTimestampString(new Date()));
		YJWYEqSysModel[] rs = service.save(new YJWYEqSysModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYEqSysModel model) {

        //校验编码和名称
        String b = service.checkNameAndCodeUnique(model.getParent_id(), model.getName(), model.getCode(),model.getEq_sys_id());
        if(b != null){
            return new ModelAndResult(false,b);
        }
		//todo:校验编码和名称
		YJWYEqSysModel tmp = domainService.queryById(model.getEq_sys_id());
		//填充字段
		Iterator<String >  iterator = model.getAttributes().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			String attribute = model.getAttribute(key);
			if(!StringUtils.isEmpty(attribute)){
					tmp.put(key,attribute);
			}

		}
		YJWYEqSysModel[] rs = service.update(new YJWYEqSysModel[]{tmp});

		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYEqSysModel[] models) {
		List<YJWYEqSysModel> list = new ArrayList<>();
		for(YJWYEqSysModel model :models){
			YJWYEqSysModel yjwyEqSysModel = domainService.queryById(model.getEq_sys_id());
			yjwyEqSysModel.setDelete_flag("1");
			list.add(yjwyEqSysModel);
		}
		YJWYEqSysModel[] tmp = new YJWYEqSysModel[list.size()];
		YJWYEqSysModel[] rs = service.update(list.toArray(tmp));
		return new ModelAndResult(rs);
	}



}
