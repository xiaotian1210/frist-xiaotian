package com.shareworx.ezfm.limitcheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.dms365.ezfm.limitcheck.function.LimitCheckFunction;
import com.shareworx.ezfm.baseinfo.project.dao.YJWYProjectDao;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.user.dao.YJWYUserDao;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.device.fmdata.dao.YJWYEqDao;
import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.limitcheck.type.DeviceType;
import com.shareworx.ezfm.limitcheck.type.ProjectType;
import com.shareworx.ezfm.limitcheck.type.UserType;
import com.shareworx.ezfm.utils.VersionUtil;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;

@Component
public class PrivateLimitCheckFunction implements LimitCheckFunction {
	
	@Autowired
	@Qualifier(YJWYProjectDao.ID)
	private YJWYProjectDao projectDao;
	
	@Autowired
  	@Qualifier(YJWYEqDao.ID)
	private YJWYEqDao eqDao;
	
	@Autowired
  	@Qualifier(YJWYUserDao.ID)
	private YJWYUserDao userDao;

	@Override
	public boolean limitCheck(Class<?> type) {
		try {
			if(type==ProjectType.class) {
				String slimit = VersionUtil.getCodes()[1]; 
				int limit = Integer.parseInt(slimit);
				Query query = Query.from(YJWYProjectModel.META_ID).and(Condition.eq(YJWYProjectModel.DELETE_FLAG, "0"));
				Long count = projectDao.countListByCondition(query);
				if(count>=limit) {
					return false;
				}
				return true;
			} else if(type==DeviceType.class) {
				String slimit = VersionUtil.getCodes()[2];
				int limit = Integer.parseInt(slimit);
				Query query = Query.from(YJWYEqModel.META_ID).and(Condition.eq(YJWYEqModel.ACTIVE, "1"));
				Long count = eqDao.countListByCondition(query);
				if(count>=limit) {
					return false;
				}
				return true;
			} else if(type==UserType.class) {
				String slimit = VersionUtil.getCodes()[4];
				int limit = Integer.parseInt(slimit);
				Query query = Query.from(YJWYUserModel.META_ID).and(Condition.eq(YJWYUserModel.IS_ABLE+"_", "1"));
				Long count = userDao.countListByCondition(query);
				if(count>=limit) {
					return false;
				}
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Object errorHandler(Class<?> type) {
		if(type==ProjectType.class) {
			ModelAndResult mr = new ModelAndResult();
			mr.setSuccess(false);
			mr.setMessage("已达到项目创建的上限");
			return mr;
		} else if(type==DeviceType.class) {
			ModelAndResult mr = new ModelAndResult();
			mr.setSuccess(false);
			mr.setMessage("已达到设备创建的上限");
			return mr;
		} else if(type==UserType.class) {
			ModelAndResult mr = new ModelAndResult();
			mr.setSuccess(false);
			mr.setMessage("已达到用户创建的上限");
			return mr;
		}
		return null;
	}

}
