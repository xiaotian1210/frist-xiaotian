package com.shareworx.ezfm.problem.file.action;

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
import com.shareworx.ezfm.problem.file.dao.ProblemFileDao;
import com.shareworx.ezfm.problem.file.model.ProblemFileModel;
import com.shareworx.ezfm.problem.file.service.ProblemFileBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

/**
 * 报事报修文件存储操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("problem/file")
public class ProblemFileAction {

	final static Logger log = Logger.getLogger(ProblemFileAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "problem/file";
	
	@Autowired
	@Qualifier(ProblemFileBusinessService.ID)
	private ProblemFileBusinessService service;
	
	public void setService(ProblemFileBusinessService service) {
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
		ProblemFileModel[] models = service.query(query);
		ProblemFileDao domainDao = SpringUtils.getBean(ProblemFileDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody ProblemFileModel model) {
		ProblemFileModel[] rs = service.save(new ProblemFileModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody ProblemFileModel model) {
		ProblemFileModel[] rs = service.update(new ProblemFileModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody ProblemFileModel[] models) {
		ProblemFileModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}
}
