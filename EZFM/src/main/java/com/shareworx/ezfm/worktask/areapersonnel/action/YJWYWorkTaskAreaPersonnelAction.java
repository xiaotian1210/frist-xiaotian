package com.shareworx.ezfm.worktask.areapersonnel.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserRelationService;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.system.dict.service.YJWYDictBusinessService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.areapersonnel.dao.YJWYWorkTaskAreaPersonnelDao;
import com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel;
import com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelBusinessService;
import com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelDomainService;
import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 片区人员操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/worktask/areapersonnel")
public class YJWYWorkTaskAreaPersonnelAction {

	final static Logger log = Logger.getLogger(YJWYWorkTaskAreaPersonnelAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/workTask/areaPersonnel/area_personnel_listcard";
	
	@Autowired
	@Qualifier(YJWYWorkTaskAreaPersonnelBusinessService.ID)
	private YJWYWorkTaskAreaPersonnelBusinessService service;
	
	@Autowired
	@Qualifier(YJWYWorkTaskAreaPersonnelDomainService.ID)
	private YJWYWorkTaskAreaPersonnelDomainService domainService;
	
	@Autowired
	@Qualifier(YJWYDictBusinessService.ID)
	private YJWYDictBusinessService dictService;
	
	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;
	
	public void setDictService(YJWYDictBusinessService dictService) {
		this.dictService = dictService;
	}
	public void setDomainService(YJWYWorkTaskAreaPersonnelDomainService domainService) {
		this.domainService = domainService;
	}
	public void setService(YJWYWorkTaskAreaPersonnelBusinessService service) {
		this.service = service;
	}
	public void setUserService(YJWYUserDomainService userService) {
		this.userService = userService;
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
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YJWYWorkTaskAreaPersonnelModel[] models = service.query(query);
		YJWYWorkTaskAreaPersonnelDao domainDao = SpringUtils.getBean(YJWYWorkTaskAreaPersonnelDao.ID);
		query.and(Condition.create("pk_crop", UserUtil.getCurrentUser().getPk_crop()));
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", models);
		return mar;
	}
	
	
	/**
	 * 查询服务专业字典
	 * @return
	 */
	
	@RequestMapping(value="queryDict", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryDict(@RequestParam(value="dictCode", required=false) String dictCode,@RequestParam(value="code", required=false) String code) {
		YJWYDictModel[] models = dictService.getDictByCode(dictCode);
		int len = models.length;
		Object[] modelsRes = new Object[len];
		for (int i = 0; i < len; i++) {
			if (models[i].getDict_code().equals(code)) {
				models[i].put("selected", true);
			}
			modelsRes[i] = models[i];
		}
		ModelAndResult mr = new ModelAndResult(modelsRes);
		return mr;
	}
	
	/**
	 * 查询用户
	 * @return
	 */
	
	@RequestMapping(value="queryUser")
	public @ResponseBody List<Map<String,Object>> queryUser(@RequestParam(value="userData", required=false) String userData) {
		if(userData != null){
			try {
				userData = URLDecoder.decode(userData, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		Query query =  Query.from(YJWYUserModel.META_ID);
		//根据填写信息，查询用户
		if (!StringUtils.isEmpty(userData)) {
			query.and(Condition.like("user_name_", userData+"%"));
		}
		query.and(Condition.create("pk_crop_",UserUtil.getCurrentUser().getPk_crop()));
		query.limit(8);
		List<YJWYUserModel> models = userService.queryListByCondition(query);
		int len = models.size();
		List<Map<String,Object>> data = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			Map<String,Object> map = new HashMap<>();
			map.put("id",models.get(i).getPk_user());
			map.put("text",models.get(i).getUser_name());
			data.add(map);
//			modelsRes[i] = models.get(i);
		}
		return data;
//		ModelAndResult mr = new ModelAndResult(data);
//		return mr;
	}
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYWorkTaskAreaPersonnelModel model) {
		YJWYWorkTaskAreaPersonnelModel rc = domainService.queryOneByCondition(Query.from(YJWYWorkTaskAreaPersonnelModel.META_ID).and(Condition.create("user_id", model.getUser_id())));
		if(rc!=null)
			return new ModelAndResult(false,"该用户已添加！");
		YJWYWorkTaskAreaPersonnelModel[] rs = service.save(new YJWYWorkTaskAreaPersonnelModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYWorkTaskAreaPersonnelModel model) {
		YJWYWorkTaskAreaPersonnelModel[] rs = service.update(new YJWYWorkTaskAreaPersonnelModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYWorkTaskAreaPersonnelModel[] models) {
		YJWYWorkTaskAreaPersonnelModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
