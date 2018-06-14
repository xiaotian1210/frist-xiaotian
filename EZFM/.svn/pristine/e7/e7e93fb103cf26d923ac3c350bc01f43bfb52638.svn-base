package com.shareworx.ezfm.meta.itf;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.shareworx.platform.metadata.MetaDatas;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.mvc.ShareworxAuthencatinException;
import com.shareworx.platform.persist.DomainModel;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.ezfm.utils.UserUtil;

public class YJWYBillModelUtils {
	public static void covert(DomainModel model){
		Map<String, MetaField> ref = MetaDatas.getFieldsByInterface(model.getMetaName(),YJWYBillModel.META_ID);
		Set<Entry<String, MetaField>> itfSet = ref.entrySet();	
		for(Entry<String, MetaField> e:itfSet){
			if(YJWYBillModel.CREATE_TIME.equals(e.getKey())){
				if(model.getAttribute(e.getValue().getId()) == null ){
					model.setAttribute(e.getValue().getId(),DateTimeUtil.getTimestampStr());	
				}
			}else if(YJWYBillModel.LAST_MODIFY_TIME.equals(e.getKey())){
				model.setAttribute(e.getValue().getId(),DateTimeUtil.getTimestampStr());	
			}else if(YJWYBillModel.CREATE_USER.equals(e.getKey())){
				if(model.getAttribute(e.getValue().getId()) == null ){
					model.setAttribute(e.getValue().getId(),UserUtil.getCurrentPk());
				}
			}else if(YJWYBillModel.LAST_MODIFY_USER.equals(e.getKey())){
				model.setAttribute(e.getValue().getId(),UserUtil.getCurrentPk());
			}else if(YJWYBillModel.UPDATE_TIME.equals(e.getKey())){
				model.setAttribute(e.getValue().getId(),System.currentTimeMillis());	
			}else{
				throw new ShareworxAuthencatinException(MessageFormat.format("接口映射错误,请检查元数据并重新发布,元数据:{0}",model.getMetaName()));
			}
			
		}
	}
	public static void covert(DomainModel[] model){
		for(DomainModel m : model){
			covert(m);
		}
	}
}
