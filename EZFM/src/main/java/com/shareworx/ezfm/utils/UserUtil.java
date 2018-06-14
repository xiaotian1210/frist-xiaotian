package com.shareworx.ezfm.utils;

import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.platform.mvc.ThreadLocalContextHolder;
import com.shareworx.platform.util.SpringUtils;

import java.util.HashSet;
import java.util.Set;

public class UserUtil {
	
	public static final String ROOT = "root";
	public static final String SYS = "sys";
	
	public static boolean isAdmin() {
		return ROOT.equals(getCurrentUser().getPk_user());
	}
	
	public static YJWYUserModel getCurrentUser() {
		YJWYUserModel ob = (YJWYUserModel) ThreadLocalContextHolder.getContext().getSession().getUserObject();
		return ob;
		//return (YJWYUserModel) ThreadLocalContextHolder.getContext().getSession().getUserObject();
	}
	
	/**
	 * 获取用户和管理员主键
	 * @return
	 */
	public static String getCurrentPk() {
		YJWYUserModel model = getCurrentUser();
		return model.getPk_user();
	}
	
	/**
	 * 获取用户主键
	 * @return
	 */
	public static String getCurrentUserPk() {
		return getCurrentUser().getPk_user();
	}

	public static YJWYUserModel getUser(String pk_user) {
		YJWYUserDomainService service = SpringUtils.getBean(YJWYUserDomainService.ID);
		return service.queryById(pk_user);
	}
	
	/**
	 * 获取当前登录用户能看到的区域
	 * @return
	 */
	public static YJWYAreaModel[] getUserArea() {
		YJWYUserBusinessService service = SpringUtils.getBean(YJWYUserBusinessService.ID);
		return service.getUserArea(getCurrentUserPk());
	}
	
	/**
	 * 获取当前登录用户能看到的项目
	 * @return
	 */
	public static YJWYProjectModel[] getUserProject() {
		YJWYUserBusinessService service = SpringUtils.getBean(YJWYUserBusinessService.ID);
		return service.getUserProject(getCurrentUserPk());
	}


	/**
	 * 获取当前登录用户能看到的项目ID
	 * @return
	 */
	public static Set<String> getUserProjectIds() {
        YJWYProjectModel[] userProject = getUserProject();
        Set<String> set = new HashSet<>();
        for(YJWYProjectModel item: userProject){
            set.add(item.getPk_project());
        }
        return set;
	}

	/**
	 * 获取当前登录用户能看到的项目，指定区域id
	 * @param areaid
	 * @return
	 */
	public static YJWYProjectModel[] getUserProject(String areaid) {
		YJWYUserBusinessService service = SpringUtils.getBean(YJWYUserBusinessService.ID);
		return service.getUserProject(getCurrentUserPk(), areaid);
	}
}
