package com.shareworx.ezfm.worktask.areapersonnel.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.baseinfo.pub.service.IBaseInfoQueryService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.system.dict.service.YJWYDictDomainService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.areadetails.model.YJWYWorkTaskAreaDetailsModel;
import com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel;
import com.shareworx.ezfm.worktask.record.model.YJWYWorkTaskDetailsRecordModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 片区人员业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYWorkTaskAreaPersonnelBusinessService.ID)
public class YJWYWorkTaskAreaPersonnelBusinessServiceImpl implements YJWYWorkTaskAreaPersonnelBusinessService {

	@Autowired
	@Qualifier(YJWYWorkTaskAreaPersonnelDomainService.ID)
	private YJWYWorkTaskAreaPersonnelDomainService domainService;

	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;
	

	@Autowired
	@Qualifier(YJWYDictDomainService.ID)
	private YJWYDictDomainService dictService;
	
	
	@Autowired
	@Qualifier(IBaseInfoQueryService.ID)
	private IBaseInfoQueryService queryService;
	
	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	public void setDomainService(YJWYWorkTaskAreaPersonnelDomainService domainService) {
		this.domainService = domainService;
	}

	public void setUserService(YJWYUserDomainService userService) {
		this.userService = userService;
	}
	
	public void setDictService(YJWYDictDomainService dictService) {
		this.dictService = dictService;
	}
	public void setQueryService(IBaseInfoQueryService queryService) {
		this.queryService = queryService;
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YJWYWorkTaskAreaPersonnelModel[] query(Query query) throws ShareworxServiceException {
		JdbcTemplate read = dao.getReadTemplate();
		String sql = "select tab1.pk_personnel_id,tab1.user_id,tab1.major_one,tab1.major_two,tab1.major_three,tab1.major_four,tab1.major_five, "
				+" tab1.certificate,tab2.user_name_,tab2.user_code_,tab2.phone_"
				+" from (yjwy_worktask_area_personnel tab1 left join yjwy_pub_user tab2 on tab1.user_id=tab2.pk_user_) "
				+" where 1=1";
		if (query.getAndList()!=null&&query.getAndList().size()>0) {
			for (Condition cond:query.getAndList()) {
				if (cond.getKey().equals("major_one")) {
					sql +=" and major_one='"+cond.getValue()+"'";
				}
				if (cond.getKey().equals("major_two")) {
					sql +=" and major_two='"+cond.getValue()+"'";
				}
				if (cond.getKey().equals("major_three")) {
					sql +=" and major_three='"+cond.getValue()+"'";
				}
				if (cond.getKey().equals("major_four")) {
					sql +=" and major_four='"+cond.getValue()+"'";
				}
				if (cond.getKey().equals("major_five")) {
					sql +=" and major_five='"+cond.getValue()+"'";
				}
				if (cond.getKey().equals("certificate")) {
					sql +=" and certificate='"+cond.getValue()+"'";
				}
			}
		}
		sql +=" and pk_crop = '" + UserUtil.getCurrentUser().getPk_crop() + "'";
		sql +=" limit "+query.getStart()+","+query.getLimit();
		List<YJWYWorkTaskAreaPersonnelModel> list = read.query(sql, new RowMapper<YJWYWorkTaskAreaPersonnelModel>(){
			Map<String, String> dictSpecialtysMap = queryService.queryDictionaryForMap("engineer_specialtys", 1);
			Map<String, String> dictCertificateMap = queryService.queryDictionaryForMap("engineer_certificate", 1);
			@Override
			public YJWYWorkTaskAreaPersonnelModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				YJWYWorkTaskAreaPersonnelModel vo = new YJWYWorkTaskAreaPersonnelModel();
				vo.setPk_personnel_id(rs.getString("pk_personnel_id"));
				
				vo.put("major_one_name",dictSpecialtysMap.get(rs.getString("major_one")));
				vo.put("major_two_name",dictSpecialtysMap.get(rs.getString("major_two")));
				vo.put("major_three_name", dictSpecialtysMap.get(rs.getString("major_three")));
				vo.put("major_four_name", dictSpecialtysMap.get(rs.getString("major_four")));
				vo.put("major_five_name", dictSpecialtysMap.get(rs.getString("major_five")));
				vo.put("certificate_name", dictCertificateMap.get(rs.getString("certificate")));
				
				vo.put("major_one",rs.getString("major_one"));
				vo.put("major_two",rs.getString("major_two"));
				vo.put("major_three", rs.getString("major_three"));
				vo.put("major_four", rs.getString("major_four"));
				vo.put("major_five", rs.getString("major_five"));
				vo.put("certificate", rs.getString("certificate"));
				
				
				vo.put("user_phone",  rs.getString("phone_"));
				vo.put("user_code", rs.getString("user_code_"));
				vo.setUser_name(rs.getString("user_name_"));
				vo.setUser_id(rs.getString("user_id"));
				return vo;
			}		
		});
		
		String whereSql ="";
		if (list!=null&&list.size()>0) {
			whereSql =" where user_id in (";
			for (int i = 0; i < list.size(); i++) {
				whereSql += "'"+list.get(i).getUser_id()+"'";
				if (i<list.size()-1) {
					whereSql += ",";
				}
			}
			whereSql +=")";
		}
		String areaSql ="select tab1.area_name,tab2.user_id from "
				+ "yjwy_worktask_area_details tab1 left join yjwy_worktask_area_user_nexus tab2 "
				+ "on tab1.pk_area_id=tab2.area_id where tab2.user_id in "
				+ "(select user_id from yjwy_worktask_area_personnel "+whereSql+") and user_type = 2";
		List<YJWYWorkTaskAreaDetailsModel> areaNameList = read.query(areaSql, new RowMapper<YJWYWorkTaskAreaDetailsModel>(){
			@Override
			public YJWYWorkTaskAreaDetailsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				YJWYWorkTaskAreaDetailsModel vo = new YJWYWorkTaskAreaDetailsModel();
				vo.setArea_name(rs.getString("area_name"));
				vo.put("user_id", rs.getString("user_id"));
				return vo;
			}		
		});
		if (list!=null&&list.size()>0&&areaNameList!=null&&areaNameList.size()>0) {
			for (YJWYWorkTaskAreaPersonnelModel model:list) {
				String area_name ="";
				for (YJWYWorkTaskAreaDetailsModel areaModel:areaNameList) {
					if (model.get("user_id")!=null&&!StringUtils.isEmpty(model.get("user_id").toString())
							&&areaModel.get("user_id")!=null&&!StringUtils.isEmpty(areaModel.get("user_id").toString())
							&&model.get("user_id").toString().equals(areaModel.get("user_id"))){
						area_name += areaModel.getArea_name()+",";
					}
				}
				model.put("area_name", area_name);
			}
			
		}
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskAreaPersonnelModel[0];
		}
		return list.toArray(new YJWYWorkTaskAreaPersonnelModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelBusinessService#load(com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel)
	 */
	@Override
	public YJWYWorkTaskAreaPersonnelModel[] load(YJWYWorkTaskAreaPersonnelModel model) throws ShareworxServiceException {
		List<YJWYWorkTaskAreaPersonnelModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYWorkTaskAreaPersonnelModel[0];
		}
		return list.toArray(new YJWYWorkTaskAreaPersonnelModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelBusinessService#save(com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel[])
	 */
	@Override
	public YJWYWorkTaskAreaPersonnelModel[] save(YJWYWorkTaskAreaPersonnelModel[] models) throws ShareworxServiceException {
		String time = this.obtainTime();
		//注入创建人，创建时间，修改人，修改时间信息
		if (models!=null&&models.length >0) {
			for (YJWYWorkTaskAreaPersonnelModel model:models) {
				model.setCreate_time(time);
				model.setCreate_user_id(UserUtil.getCurrentUserPk());
				model.setUpdate_time(System.currentTimeMillis()+"");
				model.setUpdate_user_id(UserUtil.getCurrentUserPk());
			}
		}
		List<YJWYWorkTaskAreaPersonnelModel> list = domainService.save(models);
		return list.toArray(new YJWYWorkTaskAreaPersonnelModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelBusinessService#update(com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel[])
	 */
	@Override
	public YJWYWorkTaskAreaPersonnelModel[] update(YJWYWorkTaskAreaPersonnelModel[] models) throws ShareworxServiceException {
		YJWYWorkTaskAreaPersonnelModel [] updateModels = new YJWYWorkTaskAreaPersonnelModel[models.length];
		if (models!=null&&models.length >0) {
			YJWYWorkTaskAreaPersonnelModel updateModel;
			for (int i = 0; i < models.length; i++) {
				updateModel = domainService.queryById(models[i].getPk_personnel_id());
				if (!StringUtils.isEmpty(models[i].getMajor_one())) {
					updateModel.setMajor_one(models[i].getMajor_one());
				}
				if (!StringUtils.isEmpty(models[i].getMajor_two())) {
					updateModel.setMajor_two(models[i].getMajor_two());
				}
				if (!StringUtils.isEmpty(models[i].getMajor_three())) {
					updateModel.setMajor_three(models[i].getMajor_three());
				}
				if (!StringUtils.isEmpty(models[i].getMajor_four())) {
					updateModel.setMajor_four(models[i].getMajor_four());
				}
				if (!StringUtils.isEmpty(models[i].getMajor_five())) {
					updateModel.setMajor_five(models[i].getMajor_five());
				}
				if (!StringUtils.isEmpty(models[i].get("whether_apply_all").toString())) {
					updateModel.setWhether_apply_all(Integer.parseInt(models[i].get("whether_apply_all").toString()));
				}
				if (!StringUtils.isEmpty(models[i].getCertificate())) {
					updateModel.setCertificate(models[i].getCertificate());
				}
				if (!StringUtils.isEmpty(models[i].getRemarks())) {
					updateModel.setRemarks(models[i].getRemarks());
				}
				updateModel.setUpdate_time(System.currentTimeMillis()+"");
				updateModel.setUpdate_user_id(UserUtil.getCurrentUserPk());
				updateModel.setPk_crop(models[i].getPk_crop());
				updateModels[i] = updateModel;
			}
		}
		List<YJWYWorkTaskAreaPersonnelModel> list = domainService.update(updateModels);
		return list.toArray(new YJWYWorkTaskAreaPersonnelModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.worktask.areapersonnel.service.YJWYWorkTaskAreaPersonnelBusinessService#delete(com.shareworx.ezfm.worktask.areapersonnel.model.YJWYWorkTaskAreaPersonnelModel[])
	 */
	@Override
	public YJWYWorkTaskAreaPersonnelModel[] delete(YJWYWorkTaskAreaPersonnelModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}
	//公共方法，获取当前时间
	public String obtainTime(){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		return df.format(new Date());
	}
	public String dictName(String dictCode){
		YJWYDictModel dictModel = dictService.getDict(dictCode);
		return dictModel.getDict_name();
	}
}
