package com.shareworx.ezfm.quality.proinspect.inspect.probtype.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.ezfm.quality.proinspect.inspect.probtype.model.ProblemTypeModel;
import com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.probtype.service.ProblemTypeDomainService;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 问题类型操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/quality/proinspect/inspect/probtype")
public class ProblemTypeAction {

	final static Logger log = Logger.getLogger(ProblemTypeAction.class);
	
	/** LIST跳转页面 */
	public final static String List_FORWARD = "ezfm/quality/proinspect/inspect/probtype/problem_types_list";
	/** FORM跳转页面 */
	public final static String Form_FORWARD = "ezfm/quality/proinspect/inspect/probtype/problem_types_form";
	
	
	@Autowired
	@Qualifier(ProblemTypeBusinessService.ID)
	private ProblemTypeBusinessService service;
	
	public void setService(ProblemTypeBusinessService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(ProblemTypeDomainService.ID)
	private ProblemTypeDomainService domaianservice;
	
	public void setDomaianservice(ProblemTypeDomainService domaianservice) {
		this.domaianservice = domaianservice;
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
	public ModelAndView listForward(HttpServletRequest request){
		return new ModelAndView(List_FORWARD);
	}
	/**
	 * 转向表单页面
	 * @return
	 */
	@RequestMapping(value="mainformpage/{id}", method=RequestMethod.GET)
	public ModelAndView formForward(HttpServletRequest request,@PathVariable String id){
		if(StringUtils.isEmpty(id) || "null".equalsIgnoreCase(id.trim())){
			return new ModelAndView(Form_FORWARD);
		}else{
			ModelAndView mv = new ModelAndView(Form_FORWARD);
			ProblemTypeModel model = domaianservice.queryById(id);
			mv.addObject("problem",model);
			return mv;
		}
	}
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		JdbcTemplate read = dao.getReadTemplate();
		String baseSql = " SELECT b.*,u.user_name_ 'create_username',u2.user_name_ 'update_username'"+
			" FROM yjwy_quality_problemtype b"+
			" LEFT JOIN yjwy_pub_user u ON b.create_user = u.pk_user_"+
			" LEFT JOIN yjwy_pub_user u2 ON b.update_user = u2.pk_user_"+
			" WHERE b.is_valid = '1' and b.pk_crop = '"+UserUtil.getCurrentUser().getPk_crop()+"' ";
		String querySql = baseSql+" ORDER BY b.update_time DESC, b.create_time DESC"+
			" LIMIT "+query.getStart()+ ","+query.getLimit();
		String countSql = "select count(*) from ("+baseSql+")c";
		List<JSONObject> list = read.query(querySql, new RowMapper<JSONObject>(){

			@Override
			public JSONObject mapRow(ResultSet rs, int rowNum) throws SQLException {
				JSONObject obj = new JSONObject();
				obj.put("pk_problem", rs.getString("pk_problem"));
				obj.put("problem_name", rs.getString("problem_name"));
				obj.put("problem_rectify_days", rs.getString("problem_rectify_days"));
				obj.put("create_user", rs.getString("create_user"));
				obj.put("create_username", rs.getString("create_username"));
				obj.put("create_time", rs.getString("create_time"));
				obj.put("update_user", rs.getString("update_user"));
				obj.put("update_username", rs.getString("update_username"));
				obj.put("update_time", DateTimeUtil.getTimestampString(new Date(Long.valueOf(rs.getString("update_time")))));
				obj.put("pk_crop", rs.getString("pk_crop"));
				obj.put("pk_crop", rs.getString("create_username"));
				obj.put("pk_crop", rs.getString("update_username"));
				return obj;
			}
			
		});
		int count = read.queryForObject(countSql, Integer.class);
		ModelAndResult mar = new ModelAndResult(list);
		mar.setTotal(count);
		return mar;
	}
	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody ProblemTypeModel model) {
		//model.setIs_valid(true);
		model.setCreate_time(DateTimeUtil.getTimestampString(new Date()));
		model.setCreate_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(new Date().getTime()+"");
		model.setUpdate_user(UserUtil.getCurrentUserPk());
		model.setIs_valid("1");
		if(StringUtils.isEmpty(model.getPk_crop())){
			model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		}
		ProblemTypeModel model1 = new ProblemTypeModel();
		model1.setProblem_name(model.getProblem_name());
		model1.setPk_crop(model.getPk_crop());
        List<ProblemTypeModel> problemTypeModels = domaianservice.queryByExample(model1);
        if(problemTypeModels.size()>0){
            return new ModelAndResult(false,"问题类型名称存在，请查证后重试");
        }

        ProblemTypeModel[] rs = service.save(new ProblemTypeModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody ProblemTypeModel model) {
		model.setUpdate_time(new Date().getTime()+"");
		model.setUpdate_user(UserUtil.getCurrentUserPk());
        ProblemTypeModel model1 = new ProblemTypeModel();
        model1.setProblem_name(model.getProblem_name());
        model1.setPk_crop(model.getPk_crop());
        List<ProblemTypeModel> problemTypeModels = domaianservice.queryByExample(model1);
        if(problemTypeModels.size()>0){
            if(problemTypeModels.size() == 1 &&!problemTypeModels.get(0).getPk_problem().equals(model1.getPk_problem())){

            }else {
                return new ModelAndResult(false,"问题类型名称存在，请查证后重试");
            }

        }
		ProblemTypeModel[] rs = service.update(new ProblemTypeModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作  假删除(数据保留，但不于显示)
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody ProblemTypeModel[] models) {
		String inSql = "";
		for(ProblemTypeModel model : models){
			inSql = inSql+","+model.getPk_problem();
		}
		models = service.query(Query.from(ProblemTypeModel.META_ID).and(Condition.in("pk_problem", inSql.split(","))));
		for(ProblemTypeModel model : models){
			model.setIs_valid("0");
		}
		ProblemTypeModel[] rs = service.update(models);
		return new ModelAndResult(rs);
	}
}
