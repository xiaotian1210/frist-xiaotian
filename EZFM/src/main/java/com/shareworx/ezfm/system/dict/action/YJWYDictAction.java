package com.shareworx.ezfm.system.dict.action;

import java.util.ArrayList;
import java.util.List;

import com.shareworx.ezfm.system.dict.service.YJWYDictDomainService;
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
import com.shareworx.ezfm.easyui.model.ComboBox;
import com.shareworx.ezfm.system.dict.dao.YJWYDictDao;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.system.dict.service.YJWYDictBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.SpringUtils;

/**
 * 数据字典操作控制器
 * 
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/system/dict")
public class YJWYDictAction {

	final static Logger log = Logger.getLogger(YJWYDictAction.class);

	/** 跳转页面 */
	public final static String PAGE_FORWARD = "system/dict";

	@Autowired
	@Qualifier(YJWYDictBusinessService.ID)
	private YJWYDictBusinessService service;

	@Autowired
	@Qualifier(YJWYDictDomainService.ID)
	YJWYDictDomainService dictDomainService;

	public void setService(YJWYDictBusinessService service) {
		this.service = service;
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
		YJWYDictModel[] models = service.query(query);
		YJWYDictDao domainDao = SpringUtils.getBean(YJWYDictDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}

	/**
	 * 字典公用方法
	 * 
	 * @param param
	 * @return
	 */
	@RequestMapping(value = "getDictByCode", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult getDictByCode(@RequestParam String code) {
		YJWYDictModel[] models = service.getDictByCode(code);
		return new ModelAndResult(models);
	}

	@RequestMapping(value = "dictionary/query", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult getDictQuery(@RequestParam String code) {
		YJWYDictModel[] models = service.getDictByCode(code);
		return new ModelAndResult(models);
	}

	/**
	 * 新增子级保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YJWYDictModel model) {
        YJWYDictModel dict = dictDomainService.getDict(model.getDict_code());
        if(dict != null){
            return new ModelAndResult(false,"编码重复，请查证后重试");
        }
        Query query = new Query();
        query.addFrom(YJWYDictModel.META_ID);
        query.and(Condition.create("dict_parent_",model.getDict_parent()));
        query.and(Condition.create("dict_name_",model.getDict_name()));
        YJWYDictModel[] query1 = service.query(query);
        if(query1 != null && query1.length>0){
            return new ModelAndResult(false,"名称已经存在，请查证后重试");
        }
        YJWYDictModel[] rs = service.save(new YJWYDictModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 新增保存父级操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "saveParent", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult saveParent(@RequestBody YJWYDictModel model) {
		String dict_code_ = model.getDict_code();
		Query query = Query.from(YJWYDictModel.META_ID);
		query.where(new Condition("dict_code_", QueryContents.TYPE_EQ, dict_code_));
		YJWYDictModel[] models = service.query(query);
		if (models.length > 0) {
			return new ModelAndResult(false, "字典编码已存在,请修改后重试!");
		}
		YJWYDictModel[] rs = service.saveParent(new YJWYDictModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 修改保存操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYDictModel model) {
		YJWYDictModel[] rs = service.update(new YJWYDictModel[] { model });
		return new ModelAndResult(rs);
	}

	/**
	 * 父级删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YJWYDictModel[] models) {
		YJWYDictModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}

	/**
	 * 子级删除操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "del", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult del(@RequestBody YJWYDictModel models) {
		YJWYDictModel[] rs = service.del(models);
		return new ModelAndResult(rs);
	}

	/**
	 * combobox
	 * 
	 * @return
	 */
	@RequestMapping(value = "combobox", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult combobxByDictCode(@RequestParam(value = "param", required = false) String param) {
		List<ComboBox> l = new ArrayList<>();
		ModelAndResult mar = new ModelAndResult(l);
		Query query = JSON.parseObject(param, Query.class);
		YJWYDictModel[] models = service.query(query);
		if (ArrayUtils.isEmpty(models)) {
			return mar;
		}
		// String parent = models[0].getPk_dict();
		// Query query2 =
		// Query.from(YJWYDictModel.META_ID).where(Condition.create("dict_parent_").eq(parent));
		// YJWYDictModel[] cl = service.query(query2);
		//
		for (YJWYDictModel m : models) {
			l.add(new ComboBox(m.getDict_code(), m.getDict_name()));
		}
		return mar;
	}

}
