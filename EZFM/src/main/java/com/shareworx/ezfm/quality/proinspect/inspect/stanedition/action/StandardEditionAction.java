package com.shareworx.ezfm.quality.proinspect.inspect.stanedition.action;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.alibaba.fastjson.JSON;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryOrder;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.dao.StandardEditionDao;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.EditionStationModel;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.model.StandardEditionModel;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.EditionStationBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionBusinessService;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.service.StandardEditionDomainService;
import com.shareworx.ezfm.quality.proinspect.inspect.stanedition.vo.StationStanQueryVo;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 标准版本操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/quality/proinspect/inspect/stanedition")
public class StandardEditionAction {

	final static Logger log = Logger.getLogger(StandardEditionAction.class);
	
	/** LIST跳转页面 */
	public final static String List_FORWARD = "ezfm/quality/proinspect/inspect/stanedition/edition_list";
	/** FORM跳转页面 */
	public final static String Form_FORWARD = "ezfm/quality/proinspect/inspect/stanedition/edition_form";
	/** SONPAGE跳转页面 */
	public final static String SONPAGE_FORWARD = "ezfm/quality/proinspect/inspect/stanedition/edition_son1_page";
	/** SON1SONPAGE跳转页面 */
	public final static String SON1SONPAGE_FORWARD = "ezfm/quality/proinspect/inspect/stanedition/edition_son1_sonpage";
	@Autowired
	@Qualifier(StandardEditionDomainService.ID)
	private StandardEditionDomainService domainservice;
	
	public void setDomainservice(StandardEditionDomainService domainservice) {
		this.domainservice = domainservice;
	}
	
	@Autowired
	@Qualifier(StandardEditionBusinessService.ID)
	private StandardEditionBusinessService service;
	
	public void setService(StandardEditionBusinessService service) {
		this.service = service;
	}
	
	@Autowired
	@Qualifier(InspectStandardBusinessService.ID)
	private InspectStandardBusinessService standardService;
	
	public void setStandardService(InspectStandardBusinessService standardService) {
		this.standardService = standardService;
	}
	
	@Autowired
	@Qualifier(EditionStationBusinessService.ID)
	private EditionStationBusinessService esService;
	
	public void setService(EditionStationBusinessService esService) {
		this.esService = esService;
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
			StandardEditionModel model = domainservice.queryById(id);
			mv.addObject("mainmodel",model);
			return mv;
		}
	}
	
	/**
	 * 转向SONPAGE
	 * @return
	 */
	@RequestMapping(value="sonpage/{id}", method=RequestMethod.GET)
	public ModelAndView sonpageForward(HttpServletRequest request,@PathVariable String id){
		ModelAndView mv = new ModelAndView(SONPAGE_FORWARD);
		mv.addObject("mainmodel_id",id);
		return mv;
	}
	
	/**
	 * 转向SON1SONPAGE
	 * @return
	 */
	@RequestMapping(value="son1/sonpage/{id}", method=RequestMethod.GET)
	public ModelAndView son1SonpageForward(HttpServletRequest request,@PathVariable String id){
		ModelAndView mv = new ModelAndView(SON1SONPAGE_FORWARD);
		mv.addObject("mainmodel_id",id);
		return mv;
	}
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		query.and(Condition.create("pk_crop", UserUtil.getCurrentUser().getPk_crop()));
		query.and(Condition.create("is_valid", "1"));
		query.addOrder(QueryOrder.desc("create_time"));
		StandardEditionModel[] models = service.query(query);
		StandardEditionDao domainDao = SpringUtils.getBean(StandardEditionDao.ID);
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
	public @ResponseBody ModelAndResult save(@RequestBody StandardEditionModel model) {
		model.setIs_valid("1");
		model.setCreate_time(DateTimeUtil.getTimestampString(new Date()));
		model.setCreate_user(UserUtil.getCurrentUserPk());
		model.setUpdate_time(new Date().getTime()+"");
		model.setUpdate_user(UserUtil.getCurrentUserPk());
		StandardEditionModel[] rs = service.save(new StandardEditionModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody StandardEditionModel model) {
		model.setUpdate_time(new Date().getTime()+"");
		model.setUpdate_user(UserUtil.getCurrentUserPk());
		StandardEditionModel[] rs = service.update(new StandardEditionModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody StandardEditionModel[] models) {
		/*for(StandardEditionModel model:models){
			Query query = Query.from(InspectStandardModel.META_ID);
			query.and(Condition.create("fk_standardedition",model.getPk_edition()));
			InspectStandardModel[] standards = standardService.query(query);
			if(standards.length>0){
				return new ModelAndResult(false, "标准版本下含有核查标准，不可删除");
			}
		}*/
		String inSql = "";
		for(StandardEditionModel model : models){
			inSql = inSql+","+model.getPk_edition();
		}
		inSql=inSql.substring(1);
		InspectStandardModel[] standards = standardService.query(Query.from(InspectStandardModel.META_ID).and(Condition.in("fk_standardedition", inSql.split(","))));
		if(null!=standards && standards.length>0){
			return new ModelAndResult(false, "标准版本下含有核查标准，不可删除");
		}
		EditionStationModel[] esms = esService.query(Query.from(EditionStationModel.META_ID).and(Condition.in("pk_edition", inSql.split(","))));
		if(null!=esms && esms.length>0){
			return new ModelAndResult(false, "标准版本已被岗位关联，请先移除岗位关联。");
		}
		models = service.query(Query.from(StandardEditionModel.META_ID).and(Condition.in("pk_edition", inSql.split(","))));
		for (StandardEditionModel model : models) {
			model.setIs_valid("0");
			model.setUpdate_time(new Date().getTime()+"");
			model.setUpdate_user(UserUtil.getCurrentUserPk());
		}
		StandardEditionModel[] rs = service.update(models);
		return new ModelAndResult(rs);
	}
	
	@Autowired
	@Qualifier(YJWYAreaBusinessService.ID)
	private YJWYAreaBusinessService areaservice;
	
	public void setService(YJWYAreaBusinessService areaservice) {
		this.areaservice = areaservice;
	}
	
	/**
	 * 通过StationId查询其关联的标准版本
	 * 通过StationId查询其没有关联的标准版本
	 * @return
	 */
	@RequestMapping(value="station/edition/query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult queryEditionByStationId(@RequestBody StationStanQueryVo query){
		JdbcTemplate read = dao.getReadTemplate();
		String notInSql = " select * from yjwy_quality_standardedition e where is_valid='1' and pk_edition not in(select pk_edition from yjwy_quality_edition_station e_s where e_s.pk_crop = '"+UserUtil.getCurrentUser().getPk_crop()+"' and e_s.pk_station = '"+query.getPk_station()+"') ";
		String inSql = " select * from yjwy_quality_standardedition e where is_valid='1' and pk_edition in(select pk_edition from yjwy_quality_edition_station e_s where e_s.pk_crop = '"+UserUtil.getCurrentUser().getPk_crop()+"' and e_s.pk_station = '"+query.getPk_station()+"') ";
		String sql;
		if("in".equalsIgnoreCase(query.getFlag())){
			sql=inSql;
		}else{
			String stationId = query.getPk_station();
			String areaSql = " select org_area_ from(select * from yjwy_pub_station  where pk_station_ = '"+stationId+"')s"+
					" left join yjwy_pub_org o on s.pk_org_ = o.pk_org_";
			String areaId = read.queryForObject(areaSql, String.class);
			if(StringUtils.isEmpty(areaId)){
				notInSql = notInSql+"and (area_ids = '' or area_ids is null) ";
			}else{
				notInSql = notInSql+"and (area_ids = '' or area_ids is null or area_ids like '%"+areaId+"%') ";
			}
			sql = notInSql;
		}
		if(!StringUtils.isEmpty(query.getEdition_name())){
			sql = sql + " and edition_name like '%"+query.getEdition_name()+"%' ";
		}
		String countSql = "select count(*) from ("+sql+")c";
		int count = read.queryForObject(countSql, Integer.class);
		sql = sql+" order by e.create_time desc limit "+query.getStart()+","+query.getLimit();
		List<StandardEditionModel> list = read.query(sql, new RowMapper<StandardEditionModel>(){
			@Override
			public StandardEditionModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				StandardEditionModel edition = new StandardEditionModel();
				edition.setPk_edition(rs.getString("pk_edition"));
				edition.setEdition_name(rs.getString("edition_name"));
				edition.setArea_ids(rs.getString("area_ids"));
				edition.setCreate_time(rs.getString("create_time"));
				return edition;
			}});
		ModelAndResult mar = new ModelAndResult(list.toArray(new StandardEditionModel[]{}));
		mar.setTotal(count);
		return mar;
	}
}
