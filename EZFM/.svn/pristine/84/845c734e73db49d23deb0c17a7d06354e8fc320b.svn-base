package com.shareworx.ezfm.system.pub.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.util.MD5Utils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.utils.UserUtil;
@Service(IUserPubService.ID)
public class UserPubServiceImp implements IUserPubService{
	@Autowired
	@Qualifier(YJWYUserDomainService.ID)
	private YJWYUserDomainService userservice;
	
	public void setService(YJWYUserDomainService userservice) {
		this.userservice = userservice;
	}
	/**
	 * 手机修改密码
	 * @param req
	 * @param id
	 * @param p1
	 * @param p2
	 * @return
	 */
	@Override
	public ModelAndResult alterPassword(String userId, String oldPasswordd,String newPassword){
		YJWYUserModel userModel = UserUtil.getCurrentUser();
		YJWYUserModel dataModel = userservice.queryById(userId);
		if(dataModel==null || !userId.equals(userModel.getPk_user())){
			return new ModelAndResult(false, "密码修改失败,请稍后尝试修改");
		}
		String password = dataModel.getPassword();
		if(!MD5Utils.checkPassword(oldPasswordd, password)){
			return new ModelAndResult(false, "原密码不对,请重新输入");
		}
		String md5p2 = MD5Utils.getMD5String(newPassword);
		dataModel.setPassword(md5p2);
		List<YJWYUserModel> list = userservice.update(dataModel);
		if(null==list||list.size()<1){
			return new ModelAndResult(false, "密码修改失败,请稍后尝试修改");
		}
		return new ModelAndResult(list.get(0));
	}
}
