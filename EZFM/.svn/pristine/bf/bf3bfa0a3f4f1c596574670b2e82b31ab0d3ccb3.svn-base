package com.shareworx.ezfm.quality.proinspect.inspect.standard.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.dao.InspectStandardDao;
import com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * InspectStandardModel领域操作实现
 * @author dms
 * @version since Shareworx platform 3.0
 *
 */
@Service(InspectStandardDomainService.ID)
public class InspectStandardDomainServiceImpl implements InspectStandardDomainService {

	public final static String ID = "inspectStandardDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	@Autowired
	@Qualifier(InspectStandardDao.ID)
	private InspectStandardDao dao;
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardDomainService#save(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel[])
	 */
	@Override
	public List<InspectStandardModel> save(InspectStandardModel... models) throws ShareworxServiceException {
		return service.save(InspectStandardModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardDomainService#update(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel[])
	 */
	@Override
	public List<InspectStandardModel> update(InspectStandardModel... models) throws ShareworxServiceException {
		return service.update(InspectStandardModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardDomainService#update(java.util.List, com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel[])
	 */
	@Override
	public List<InspectStandardModel> update(List<String> editFields, InspectStandardModel... models) throws ShareworxServiceException {
		return service.update(InspectStandardModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardDomainService#delete(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel[])
	 */
	@Override
	public int delete(InspectStandardModel... models) throws ShareworxServiceException {
		return service.delete(InspectStandardModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(InspectStandardModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardDomainService#queryById(java.lang.String)
	 */
	@Override
	public InspectStandardModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(InspectStandardModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public InspectStandardModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<InspectStandardModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardDomainService#queryByExample(com.shareworx.ezfm.quality.proinspect.inspect.standard.model.InspectStandardModel)
	 */
	@Override
	public List<InspectStandardModel> queryByExample(InspectStandardModel model) throws ShareworxServiceException {
		return service.queryByExample(InspectStandardModel.META_ID, model);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.quality.proinspect.inspect.standard.service.InspectStandardBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public List<Map<String, Object>> queryMap(String param) throws ShareworxServiceException {
		
		String sql = getQuerySql(param);
		
		List<Map<String, Object>> listMap = dao.queryMap(sql);
		
		return listMap;
	}

	/**
	 * 
	 * @param leaveModelVo
	 * @return
	 */
	@Override
	public Long queryCount(String param) {
		
		String sql = getCountSql(param);
		
		return dao.queryCount(sql);
	}
	
	
	/**
	 * 获取查询语句
	 * @param param
	 * @return
	 */
	public String getQuerySql(String param){
		
		Query query = JSON.parseObject(param, Query.class);
		String vWhr = "where 1 = 1 ";
		String yWhr = "";
		List<Condition> conditions = query.getAndList();
		for(Condition c:conditions){
			if("inspstan_code".equalsIgnoreCase(c.getKey())){
				yWhr = yWhr+" and yqi.inspstan_code like '%"+c.getValue()+"%' ";
			}
			if("specialty".equalsIgnoreCase(c.getKey())){
				yWhr = yWhr+" and yqi.specialty = '"+c.getValue()+"' ";
			}
			if("area_id".equalsIgnoreCase(c.getKey())){
				vWhr = vWhr+" and pk_area_ = '"+c.getValue()+"' ";
			}
		}
		String baseSql = "select yqi.pk_inspstan, ypd.dict_name_ as specialty, "
				+ "yqi.inspstan_code, yqi.inspstan_name,yqi.project_category,"
				+ "yqi.inspstan_scorevalue,yqi.inspstan_category,yqi.inspstan_category_description,"
				+ "yqi.inspstan_performance_norm,yqi.inspstan_usingscope,yqi.inspstan_inpectmethod,"
				+ "yqi.inspstan_secretinquiries,yqi.inspstan_dkzg_pc,yqi.inspstan_bmjl_pc,"
				+ "yqi.inspstan_xmjl_pc,yqi.inspstan_qyzz_pc,yqi.inspstan_qyz_pc,"
				+ "yqi.inspstan_zj_pc,yqi.create_user,yqi.create_time,yqi.update_user,"
				+ "yqi.update_time,yqi.is_valid,yqi.pk_crop,yqi.fk_standardedition "
				+ "from yjwy_quality_inspectstandard yqi "
				+ "left join yjwy_pub_dict ypd on yqi.specialty = ypd.dict_code_ "
				+ "left join (select distinct(pk_standard) "
					+ "from yjwy_quality_standard_station where pk_station in( "
							+"select distinct(pk_station_) "
							+ "from view_yjwy_station "
							+ vWhr+")) yqss "
				+ "on yqi.pk_inspstan = yqss.pk_standard "
				+ "where yqi.pk_crop = '"+UserUtil.getCurrentUser().getPk_crop()+"' "
				+ yWhr//"' and (fk_standardedition = '' or fk_standardedition is null) "; 
				+ " order by yqi.create_time desc limit "+query.getStart()+","+query.getLimit();
		
		return baseSql;
		
	} 
	
	/**
	 * 查询一共多少条记录
	 * @param param
	 * @return
	 */
	public String getCountSql(String param){
		
		Query query = JSON.parseObject(param, Query.class);
		String vWhr = "where 1 = 1 ";
		String yWhr = "";
		List<Condition> conditions = query.getAndList();
		for(Condition c:conditions){
			if("inspstan_code".equalsIgnoreCase(c.getKey())){
				yWhr = yWhr+" and yqi.inspstan_code like '%"+c.getValue()+"%' ";
			}
			if("specialty".equalsIgnoreCase(c.getKey())){
				yWhr = yWhr+" and yqi.specialty = '"+c.getValue()+"' ";
			}
			if("area_id".equalsIgnoreCase(c.getKey())){
				vWhr = vWhr+" and pk_area_ = '"+c.getValue()+"' ";
			}
		}
		String baseSql = "select count(*) "
				+ "from yjwy_quality_inspectstandard yqi "
				+ "left join (select distinct(pk_standard) "
					+ "from yjwy_quality_standard_station where pk_station in( "
							+"select distinct(pk_station_) "
							+ "from view_yjwy_station "
							+ vWhr+")) yqss "
				+ "on yqi.pk_inspstan = yqss.pk_standard "
				+ "where yqi.pk_crop = '"+UserUtil.getCurrentUser().getPk_crop()+"' "
				+ yWhr;//"' and (fk_standardedition = '' or fk_standardedition is null) "; 
		
		return baseSql;
	}
}
