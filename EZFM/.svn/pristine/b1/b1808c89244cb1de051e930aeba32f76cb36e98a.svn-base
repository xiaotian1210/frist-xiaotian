package com.shareworx.ezfm.device.fmdata_eq.service;

import com.shareworx.ezfm.cache.annotation.DMSCacheEvict;
import com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Conditions;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 设备所属系统业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YJWYEqSysBusinessService.ID)
public class YJWYEqSysBusinessServiceImpl implements YJWYEqSysBusinessService {

	@Autowired
	@Qualifier(YJWYEqSysDomainService.ID)
	private YJWYEqSysDomainService domainService;
	
	public void setDomainService(YJWYEqSysDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override

	public YJWYEqSysModel[] query(Query query) throws ShareworxServiceException {
		List<YJWYEqSysModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYEqSysModel[0];
		}
		return list.toArray(new YJWYEqSysModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysBusinessService#load(com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel)
	 */
	@Override
	public YJWYEqSysModel[] load(YJWYEqSysModel model) throws ShareworxServiceException {
		List<YJWYEqSysModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YJWYEqSysModel[0];
		}
		return list.toArray(new YJWYEqSysModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysBusinessService#save(com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel[])
	 */
	@Override
	public YJWYEqSysModel[] save(YJWYEqSysModel[] models) throws ShareworxServiceException {
		List<YJWYEqSysModel> list = domainService.save(models);
		return list.toArray(new YJWYEqSysModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysBusinessService#update(com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel[])
	 */
	@Override
	public YJWYEqSysModel[] update(YJWYEqSysModel[] models) throws ShareworxServiceException {
		List<YJWYEqSysModel> list = domainService.update(models);
		return list.toArray(new YJWYEqSysModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.device.fmdata_eq.service.YJWYEqSysBusinessService#delete(com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel[])
	 */
	@Override
	public YJWYEqSysModel[] delete(YJWYEqSysModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

	/**
	 *
	 * @param parentId
	 * @param name
	 * @param code
	 * @param eqSysId null 新增，否则修改
	 * @return
	 */
	@Override
	public String  checkNameAndCodeUnique(String parentId, String name, String code, String eqSysId) {

		YJWYEqSysModel model = new YJWYEqSysModel();
		model.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
		model.setCode(code);
		model.setDelete_flag("0");
		//检查公司的编码是否唯一
        List<YJWYEqSysModel> yjwyEqSysModels1 = domainService.queryByExample(model);
        if(yjwyEqSysModels1.size() == 0
                ||(yjwyEqSysModels1.size() == 1  && eqSysId != null && yjwyEqSysModels1.get(0).getEq_sys_id().equals(eqSysId))){//排除自己


        }else {
           return  "编码重复，请换个编码";
        }
        model.remove("code");
        model.setParent_id(parentId);
        model.setName(name);
        //检查名称同级别是否唯一
        List<YJWYEqSysModel> yjwyEqSysModels2 = domainService.queryByExample(model);
        if(yjwyEqSysModels2.size() == 0
                ||(yjwyEqSysModels2.size() == 1  && eqSysId != null && yjwyEqSysModels2.get(0).getEq_sys_id().equals(eqSysId))){//排除自己


        }else {
            return  "同级别名称重复，请换个名称";
        }
        return null;
	}

}
