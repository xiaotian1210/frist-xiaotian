package com.shareworx.ezfm.baseinfo.rolefunc.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArraySet;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel;
import com.shareworx.ezfm.baseinfo.rolefunc.service.YJWYRoleFuncBusinessService;
import com.shareworx.ezfm.easyui.model.ZtreeModel;
import com.shareworx.ezfm.meta.itf.YJWYBillModelUtils;
import com.shareworx.ezfm.system.function.model.YJWYFunctionModel;
import com.shareworx.ezfm.system.function.service.YJWYFunctionBusinessService;

/**
 * 基于角色的功能权限操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/baseinfo/rolefunc")
public class YJWYRoleFuncAction {

	final static Logger log = Logger.getLogger(YJWYRoleFuncAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "baseinfo/rolefunc";
	
	@Autowired
	@Qualifier(YJWYRoleFuncBusinessService.ID)
	private YJWYRoleFuncBusinessService service;
	
	@Autowired
	@Qualifier(YJWYFunctionBusinessService.ID)
	private YJWYFunctionBusinessService funcService;
	
	public void setService(YJWYRoleFuncBusinessService service) {
		this.service = service;
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
	 * 该方法已经弃用
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYRoleFuncModel[] models = service.query(query);
		Set<String> relSet = new ArraySet<>(); 
		for(YJWYRoleFuncModel m : models){
			relSet.add(m.getPk_func());
		}
		Query query2 = Query.from(YJWYFunctionModel.META_ID);
		YJWYFunctionModel[] funcs = funcService.query(query2);
		List<ZtreeModel> tl = new ArrayList<>();
		for(YJWYFunctionModel f : funcs){
			ZtreeModel zm = new ZtreeModel();
			zm.setId(f.getId());
			zm.setpId(f.getPid());
			zm.setName(f.getName());
			zm.setAttributes(f);
			if(relSet.contains(zm.getId())){
				zm.setChecked(true);
				zm.setOpen(true);
			}
			tl.add(zm);
		}
		ModelAndResult mar = new ModelAndResult(tl);	
		return mar;
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYRoleFuncModel[] model) {
		
		List<Condition> cons = new ArrayList<>();
		cons.add(new Condition("pk_crop_", QueryContents.TYPE_EQ, model[0].getPk_crop()));
		cons.add(new Condition("pk_role_", QueryContents.TYPE_EQ, model[0].getPk_role()));
		Query query = Query.from(YJWYRoleFuncModel.META_ID).and(cons.toArray(new Condition[0]));
		YJWYRoleFuncModel[] rels = service.query(query);
		if(!ArrayUtils.isEmpty(rels)){
			service.delete(rels);
		}
		for(YJWYRoleFuncModel m : model){
			YJWYBillModelUtils.covert(m);
		}
		YJWYRoleFuncModel[] rs = service.save(model);
		return new ModelAndResult(rs);
	}
	
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYRoleFuncModel model) {
		YJWYBillModelUtils.covert(model);
		YJWYRoleFuncModel[] rs = service.update(new YJWYRoleFuncModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYRoleFuncModel[] models) {
		YJWYRoleFuncModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
