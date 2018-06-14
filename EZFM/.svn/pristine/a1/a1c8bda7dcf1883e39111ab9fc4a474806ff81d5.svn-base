package com.shareworx.ezfm.worktask.areadetails.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaBusinessService;
import com.shareworx.ezfm.baseinfo.area.service.YJWYAreaDomainService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel;
import com.shareworx.ezfm.worktask.details.model.YJWYWorkTaskDetailsModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 片区详情业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskAreaDetailsBusinessService.ID)
public class YJWYWorkTaskAreaDetailsBusinessServiceImpl implements YJWYWorkTaskAreaDetailsBusinessService {

	@Autowired
	@Qualifier(YJWYWorkTaskAreaDetailsDomainService.ID)
	private YJWYWorkTaskAreaDetailsDomainService domainService;

	public void setDomainService(YJWYWorkTaskAreaDetailsDomainService domainService) {
		this.domainService = domainService;
	}

	
	@Autowired
	@Qualifier(YJWYAreaDomainService.ID)
	private YJWYAreaDomainService areaService;

	public void setAreaService(YJWYAreaDomainService areaService) {
		this.areaService = areaService;
	}
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskAreaDetailsModel[] query(Query query) throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.pk_area_id,tab1.area_name,tab1.fk_region_id,tab2.area_name_ "
				+ "from yjwy_worktask_area_details tab1 "
				+ "left join yjwy_pub_area tab2 on tab1.fk_region_id=tab2.pk_area_ "
		        + "where 1=1";
		if (query.getAndList()!=null&&query.getAndList().size()>0) {
			for (Condition cond:query.getAndList()) {
				if (cond.getKey().equals("fk_region_id")) {
					sql+= " and tab2.pk_area_='"+cond.getValue()+"'";
				}
			}
		}
		sql +=" and pk_crop = '" + UserUtil.getCurrentUser().getPk_crop() + "'";
		sql +=" limit "+query.getStart()+","+query.getLimit();
		List<YJWYWorkTaskAreaDetailsModel> list = read.query(sql, new RowMapper<YJWYWorkTaskAreaDetailsModel>(){
			@Override
			public YJWYWorkTaskAreaDetailsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYWorkTaskAreaDetailsModel model = new YJWYWorkTaskAreaDetailsModel();
				model.setPk_area_id(rs.getString("pk_area_id"));
				model.setArea_name(rs.getString("area_name"));
				model.setFk_region_id(rs.getString("fk_region_id"));
				model.put("fk_region_name", rs.getString("area_name_"));
				return model;
			}
		});
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskAreaDetailsModel[0];
		}
		return list.toArray(new YJWYWorkTaskAreaDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsBusinessService#load(com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel)
	 */
	@Override
	public YJWYWorkTaskAreaDetailsModel[] load(YJWYWorkTaskAreaDetailsModel model) throws ShareworxServiceException {
		List<YJWYWorkTaskAreaDetailsModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskAreaDetailsModel[0];
		}
		return list.toArray(new YJWYWorkTaskAreaDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsBusinessService#save(com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel[])
	 */
	@Override
	public YJWYWorkTaskAreaDetailsModel[] save(YJWYWorkTaskAreaDetailsModel[] models) throws ShareworxServiceException {
		String time = obtainTime();
		for (YJWYWorkTaskAreaDetailsModel model:models) {
			model.setCreate_time(time);
			model.setCreate_user_id(UserUtil.getCurrentUserPk());
		}
		List<YJWYWorkTaskAreaDetailsModel> list = domainService.save(models);
		return list.toArray(new YJWYWorkTaskAreaDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsBusinessService#update(com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel[])
	 */
	@Override
	public YJWYWorkTaskAreaDetailsModel[] update(YJWYWorkTaskAreaDetailsModel[] models) throws ShareworxServiceException {
		YJWYWorkTaskAreaDetailsModel[] updateModels = new YJWYWorkTaskAreaDetailsModel[models.length];
		if (models!=null&&models.length >0) {
			YJWYWorkTaskAreaDetailsModel updateModel;
			for (int i = 0; i < models.length; i++) {
				updateModel = domainService.queryById(models[i].getPk_area_id());
				if (!StringUtils.isEmpty(models[i].getArea_name())) {
					updateModel.setArea_name(models[i].getArea_name());
				}
				if (!StringUtils.isEmpty(models[i].getFk_region_id())) {
					updateModel.setFk_region_id(models[i].getFk_region_id());
				}
				updateModel.setUpdate_time(System.currentTimeMillis()+"");
				updateModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
				updateModel.setPk_crop(models[i].getPk_crop());
				updateModels[0] = updateModel;
			}
		}
		
		List<YJWYWorkTaskAreaDetailsModel> list = domainService.update(updateModels);
		return list.toArray(new YJWYWorkTaskAreaDetailsModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areadetails.service.YJWYWorkTaskAreaDetailsBusinessService#delete(com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel[])
	 */
	@Override
	public YJWYWorkTaskAreaDetailsModel[] delete(YJWYWorkTaskAreaDetailsModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}
	//公共方法，获取当前时间
	public String obtainTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
}
