package com.shareworx.ezfm.quality.file.action;

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
import com.shareworx.ezfm.quality.file.dao.QualityFileDao;
import com.shareworx.ezfm.quality.file.model.QualityFileModel;
import com.shareworx.ezfm.quality.file.service.QualityFileBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 品质核查操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("quality/file")
public class QualityFileAction {

	final static Logger log = Logger.getLogger(QualityFileAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "quality/file";
	
	@Autowired
	@Qualifier(QualityFileBusinessService.ID)
	private QualityFileBusinessService service;
	
	public void setService(QualityFileBusinessService service) {
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
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		QualityFileModel[] models = service.query(query);
		QualityFileDao domainDao = SpringUtils.getBean(QualityFileDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody QualityFileModel model) {
		QualityFileModel[] rs = service.save(new QualityFileModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody QualityFileModel model) {
		QualityFileModel[] rs = service.update(new QualityFileModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody QualityFileModel[] models) {
		QualityFileModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
