package com.shareworx.ezfm.system.user_update.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.utils.UserUtil;



@Controller
@RequestMapping("ezfm/system/user_update")
public class User_UpdateAction_Bak {
	public final static String USER_UPDATE = "system/userinfo_update";
	
	@Autowired
	@Qualifier(value=YJWYUserBusinessService.ID)
	private YJWYUserBusinessService userBusinessService;
	
	public void setUserBusinessService(YJWYUserBusinessService userBusinessService) {
		this.userBusinessService = userBusinessService;
	}
	
	
	/**
	 * 转向user/update页面
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView homeForward(){
		return new ModelAndView(USER_UPDATE);
	}
	
	
	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult query() {
		Query query = Query.from(YJWYUserModel.META_ID);
		query.and(Condition.create("pk_user_", UserUtil.getCurrentUserPk()));
		YJWYUserModel[] models = userBusinessService.query(query);	
		ModelAndResult mar = new ModelAndResult(models);
		return mar;
	}

	
	/**
	 * 修改操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YJWYUserModel[] model) {
		for (YJWYUserModel yjwyUserModel : model) {
			yjwyUserModel.setLast_modify_user(yjwyUserModel.getPk_user());
			yjwyUserModel.setLast_modify_time(DateTimeUtil.getTimestampString(new Date()));
			yjwyUserModel.setUpdate_time(System.currentTimeMillis()+"");
			Query query = Query.from(YJWYUserModel.META_ID);
			query.and(Condition.create("pk_user_", yjwyUserModel.getPk_user()));
			YJWYUserModel[] models = userBusinessService.query(query);	
			if(!models[0].getPassword().equals(yjwyUserModel.getPassword())){
				yjwyUserModel.setPassword(StringUtils.md5(yjwyUserModel.getPassword()));
			}
		}
		YJWYUserModel[] rs = userBusinessService.update(model);
		return new ModelAndResult(rs);
	}
	

}
