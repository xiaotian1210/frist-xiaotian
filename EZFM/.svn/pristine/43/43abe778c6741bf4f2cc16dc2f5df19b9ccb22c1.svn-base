package com.shareworx.ezfm.system.crop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.MD5Utils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleModel;
import com.shareworx.ezfm.baseinfo.role.model.YJWYRoleUserModel;
import com.shareworx.ezfm.baseinfo.rolefunc.model.YJWYRoleFuncModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.system.crop.model.CropModel;
import com.shareworx.ezfm.system.function.model.YJWYFunctionModel;
import com.shareworx.ezfm.system.function.service.YJWYFunctionDomainService;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 企业管理业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(CropBusinessService.ID)
public class CropBusinessServiceImpl implements CropBusinessService {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		System.out.println(list.toArray(new String[]{}));
	}
	@Autowired
	@Qualifier(CropDomainService.ID)
	private CropDomainService domainService;
	
	public void setDomainService(CropDomainService domainService) {
		this.domainService = domainService;
	}
	
	@Autowired
	@Qualifier(YJWYFunctionDomainService.ID)
	private YJWYFunctionDomainService fservice;
	
	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userService;

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public CropModel[] query(Query query) throws ShareworxServiceException {
		List<CropModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new CropModel[0];
		}
		return list.toArray(new CropModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropBusinessService#load(com.shareworx.ezfm.system.crop.model.CropModel)
	 */
	@Override
	public CropModel[] load(CropModel model) throws ShareworxServiceException {
		List<CropModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new CropModel[0];
		}
		return list.toArray(new CropModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropBusinessService#save(com.shareworx.ezfm.system.crop.model.CropModel[])
	 */
	@Override
	public CropModel[] save(CropModel[] models) throws ShareworxServiceException {
		List<CropModel> list = domainService.save(models);
		return list.toArray(new CropModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropBusinessService#update(com.shareworx.ezfm.system.crop.model.CropModel[])
	 */
	@Override
	public CropModel[] update(CropModel[] models) throws ShareworxServiceException {
		List<CropModel> list = domainService.update(models);
		return list.toArray(new CropModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.service.CropBusinessService#delete(com.shareworx.ezfm.system.crop.model.CropModel[])
	 */
	@Override
	public CropModel[] delete(CropModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}
	
	@Override
	public YJWYUserModel[] userSave(YJWYUserModel[] models)
			throws ShareworxServiceException {
		doValidateUserCode(models[0].getUser_code(),models[0].getPk_user());
		//预置管理员
		for(YJWYUserModel model : models){
			model.setPassword(MD5Utils.getMD5String(model.getPassword()));
			model.setIs_able(true);
			model.setIs_pre(true);
			model.setCreate_user(UserUtil.getCurrentUserPk());
			model.setCreate_time(DateTimeUtil.getTimestampStr());
			model.setLast_modify_user(UserUtil.getCurrentUserPk());
			model.setLast_modify_time(DateTimeUtil.getTimestampStr());
			model.setUpdate_time(System.currentTimeMillis()+"");
		}
		
		List<YJWYUserModel> list =  domainService.userSave(models);
		String pk_crop = models[0].getPk_crop();
		String pk_user = models[0].getPk_user();
		//预置角色
		YJWYRoleModel role = new YJWYRoleModel();
		role.setPk_crop(models[0].getPk_crop());
		role.setRole_code("SuperAdmin");
		role.setRole_name("超级管理员");
		role.setIs_pre(true);
		role.setCreate_user(UserUtil.getCurrentUserPk());
		role.setCreate_time(DateTimeUtil.getTimestampStr());
		role.setLast_modify_user(UserUtil.getCurrentUserPk());
		role.setLast_modify_time(DateTimeUtil.getTimestampStr());
		role.setUpdate_time(System.currentTimeMillis()+"");
		//预置角色-人
		List<YJWYRoleModel> rl =  domainService.roleSave(new YJWYRoleModel[]{role});
		String pk_role = rl.get(0).getPk_role();
		YJWYRoleUserModel rum = new YJWYRoleUserModel();
		rum.setPk_crop(pk_crop);
		rum.setPk_role(pk_role);
		rum.setPk_user(pk_user);
		rum.setCreate_user(UserUtil.getCurrentUserPk());
		rum.setCreate_time(DateTimeUtil.getTimestampStr());
		domainService.roleUserSave(new YJWYRoleUserModel[]{rum});	
		//预置角色-功能
		Query query = Query.from(YJWYFunctionModel.META_ID);
		List<YJWYFunctionModel> fl = fservice.queryListByCondition(query);
		List<YJWYRoleFuncModel> rfml = new ArrayList<>();
		for(YJWYFunctionModel m : fl){
			YJWYRoleFuncModel rfm = new YJWYRoleFuncModel();
			rfm.setPk_crop(pk_crop);
			rfm.setPk_func(m.getId());
			rfm.setPk_role(pk_role);
			rfm.setCreate_user(UserUtil.getCurrentUserPk());
			rfm.setCreate_time(DateTimeUtil.getTimestampStr());
			rfml.add(rfm);
		}
		domainService.funcSave(rfml.toArray(new YJWYRoleFuncModel[0]));
		/*//预置角色-动作  已经作废
		Query query2 = Query.from(YJWYFunctionActionModel.META_ID);
		List<YJWYFunctionActionModel> ml = mservice.queryListByCondition(query2);
		List<YJWYRoleMenuModel> rmml = new ArrayList<YJWYRoleMenuModel>();
		for(YJWYFunctionActionModel m : ml){
			YJWYRoleMenuModel rmm = new YJWYRoleMenuModel();
			rmm.setPk_crop(pk_crop);
			rmm.setPk_func(m.getPk_func());
			rmm.setPk_role(pk_role);
			rmm.setPk_menu(m.getId());
			rmm.setIs_pre(true);
			YJWYBillModelUtils.covert(rmm);
			rmml.add(rmm);
		}
		if(rmml.size()>0){
			domainService.menuSave(rmml.toArray(new YJWYRoleMenuModel[]{}));
		}*/
		return list.toArray(new YJWYUserModel[0]);
		
	}

	@Override
	public YJWYUserModel[] userUpdate(YJWYUserModel[] models)
			throws ShareworxServiceException {
		doValidateUserCode(models[0].getUser_code(),models[0].getPk_user());
		List<YJWYUserModel> list = domainService.userUpdate(models);
		return list.toArray(new YJWYUserModel[0]);
	}
	
	/*代码写的不够严谨，弃用private void userCheck(YJWYUserModel[] models){
		List<Condition> andList = new ArrayList<>();
		Query query = new Query();
		query.getMetas().add(YJWYUserModel.META_ID);
		andList.add(Condition.create("user_code_", models[0].getUser_code()));
		query.setAndList(andList);;
		List<YJWYUserModel> l = userService.queryListByCondition(query);
		if(!ArrayUtils.isEmpty(l)){
			throw new ShareworxServiceException("该账号已存在,请换个账号试试");
		}
	}*/

	/**
	 * 判断用户账号是否重复
	 * @param code
	 * @param id
	 * @return
	 */
	public boolean doValidateUserCode(String code,String id){
		if(!StringUtils.isEmpty(id)){
			YJWYUserModel model = userService.queryById(id);
			if(code.equals(model.getUser_code())){
				return true;
			}
		}
		
		List<YJWYUserModel> models = userService.queryListByCondition(Query.from(YJWYUserModel.META_ID).and(Condition.create("user_code_", code)));
		if(null!=models && models.size()>0){
			throw new ShareworxServiceException("该账号已存在,请换个账号试试");
		}
		return true;
	}
}
