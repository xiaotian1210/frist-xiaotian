package com.shareworx.ezfm.system.crop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.MD5Utils;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleUserModel;
import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.system.crop.model.CropModel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * CropModel领域操作实现
 * @author qiang.gu
 * @version since Shareworx platform 3.0
 *
 */
@Service(CropDomainService.ID)
public class CropDomainServiceImpl implements CropDomainService {

	public final static String ID = "cropDomainService";
	
	@Autowired
	@Qualifier(BaseDomainService.ID)
	private BaseDomainService service;
	
	private JdbcTemplate readjt;
	
	protected JdbcTemplate getReadTemplate() {
		if (readjt == null) {
			readjt = DatabaseConnections.getReadTemplate();
		}
		return readjt;
	}
	
	public void setService(BaseDomainService service) {
		this.service = service;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropDomainService#save(com.shareworx.ezfm.system.crop.model.CropModel[])
	 */
	@Override
	public List<CropModel> save(CropModel... models) throws ShareworxServiceException {
		return service.save(CropModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropDomainService#update(com.shareworx.ezfm.system.crop.model.CropModel[])
	 */
	@Override
	public List<CropModel> update(CropModel... models) throws ShareworxServiceException {
		return service.update(CropModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropDomainService#update(java.util.List, com.shareworx.ezfm.system.crop.model.CropModel[])
	 */
	@Override
	public List<CropModel> update(List<String> editFields, CropModel... models) throws ShareworxServiceException {
		return service.update(CropModel.META_ID, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropDomainService#delete(com.shareworx.ezfm.system.crop.model.CropModel[])
	 */
	@Override
	public int delete(CropModel... models) throws ShareworxServiceException {
		return service.delete(CropModel.META_ID, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropDomainService#deleteByIds(java.lang.String[])
	 */
	@Override
	public int deleteByIds(String[] ids) throws ShareworxServiceException {
		return service.deleteByIds(CropModel.META_ID, ids);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropDomainService#deleteByConditions(com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		return service.deleteByConditions(delete);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropDomainService#queryById(java.lang.String)
	 */
	@Override
	public CropModel queryById(String id) throws ShareworxServiceException {
		return service.queryById(CropModel.META_ID, id);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropDomainService#queryOneByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public CropModel queryOneByCondition(Query query) throws ShareworxServiceException {
		return service.queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropDomainService#queryListByCondition(com.shareworx.platform.persist.Condition)
	 */
	@Override
	public List<CropModel> queryListByCondition(Query query) throws ShareworxServiceException {
		return service.queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropDomainService#queryByExample(com.shareworx.ezfm.system.crop.model.CropModel)
	 */
	@Override
	public List<CropModel> queryByExample(CropModel model) throws ShareworxServiceException {
		return service.queryByExample(CropModel.META_ID, model);
	}
	
	
	@Override
	public List<YJWYUserModel> userSave(YJWYUserModel... models)
			throws ShareworxServiceException {
		List<YJWYUserModel> l =  service.save(YJWYUserModel.META_ID, models);
		return l;
	}

	@Override
	public List<YJWYRoleModel> roleSave(YJWYRoleModel... models)
			throws ShareworxServiceException {
		List<YJWYRoleModel> l =  service.save(YJWYRoleModel.META_ID, models);
		return l;
	}

	@Override
	public List<YJWYRoleUserModel> roleUserSave(YJWYRoleUserModel... models)
			throws ShareworxServiceException {
		List<YJWYRoleUserModel> l =  service.save(YJWYRoleUserModel.META_ID, models);
		return l;
	}

	@Override
	public List<YJWYUserModel> userUpdate(YJWYUserModel... models)
			throws ShareworxServiceException {
		for(YJWYUserModel model : models){
			YJWYUserModel newmodel = service.queryById(YJWYUserModel.META_ID, model.getPk_user());
			newmodel.setLast_modify_user(UserUtil.getCurrentUserPk());
			newmodel.setLast_modify_time(DateTimeUtil.getTimestampStr());
			newmodel.setUpdate_time(System.currentTimeMillis()+"");
			if(!model.getPassword().equals(newmodel.getPassword())){
				newmodel.setPassword(MD5Utils.getMD5String(model.getPassword()));
			}
			newmodel.setUser_name(model.getUser_name());
			newmodel.setUser_code(model.getUser_code());
			model = newmodel;
		}
		List<YJWYUserModel> l =  service.update(YJWYUserModel.META_ID, models);
		return l;
	}

	@Override
	public List<YJWYRoleFuncModel> funcSave(YJWYRoleFuncModel... models)
			throws ShareworxServiceException {
		List<YJWYRoleFuncModel> l =  service.save(YJWYRoleFuncModel.META_ID, models);
		return l;
	}
}
