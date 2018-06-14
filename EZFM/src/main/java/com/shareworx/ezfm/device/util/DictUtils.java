package com.shareworx.ezfm.device.util;

import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.system.dict.service.YJWYDictBusinessService;
import com.shareworx.ezfm.system.dict.service.YJWYDictDomainService;
import com.shareworx.platform.util.SpringUtils;

/**
 * Created by zhi.zhang on 2017/7/13.
 */
public class DictUtils {
    /**
     *
     * @param pk_dict 字典ID拿本身对象
     * @return
     */
    public static  YJWYDictModel queryById(String pk_dict){
//        CacheConstants
        YJWYDictDomainService bean = SpringUtils.getBean(YJWYDictDomainService.ID);
        return  bean.queryById(pk_dict);
    }

    /**
     * 字段code拿本身对象
     * @param code
     * @return
     */
    public static YJWYDictModel getDictByCode(String code){
        YJWYDictDomainService bean = SpringUtils.getBean(YJWYDictDomainService.ID);
        return bean.getDict(code);
    }


    /**
     * 字段code拿子对象集合
     * @param code
     * @return
     */
    public static YJWYDictModel[] getChildDictByCode(String code){
        YJWYDictBusinessService bean = SpringUtils.getBean(YJWYDictBusinessService.ID);
        return bean.getDictByCode(code);
    }


    /**
     * 根据父类的code和本类的name获取对象
     * @param code
     * @param name
     * @return
     */
    public static YJWYDictModel getDictByNameAndParentCode(String parentCode,String name) {
        YJWYDictBusinessService bean = SpringUtils.getBean(YJWYDictBusinessService.ID);
        YJWYDictModel[] dictByNameAndParentCode = bean.getDictByNameAndParentCode(parentCode, name);
        if(dictByNameAndParentCode != null&& dictByNameAndParentCode.length>0){
            return dictByNameAndParentCode[0];
        }else{
            return null;
        }
    }


}
