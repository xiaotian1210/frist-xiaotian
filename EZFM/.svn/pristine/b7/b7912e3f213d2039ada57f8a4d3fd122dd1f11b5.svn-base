package com.shareworx.ezfm.easyui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaDatas;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.persist.DomainModel;
import com.shareworx.platform.util.ArrayUtils;

public class EasyUiTreeUtil {


	public static List<EasyUiTreeModel> covertTreeModel(DomainModel[] array)
			throws ShareworxServiceException {
		if(!ArrayUtils.isEmpty(array)){
			Map<String, MetaField> ref = MetaDatas.getFieldsByInterface(array[0].getMetaName(),EasyUiTreeModel.META_ID);
			Set<Entry<String, MetaField>> itfSet = ref.entrySet();
			Map<String,EasyUiTreeModel> mt = new HashMap<>();
			for(DomainModel m : array){
				EasyUiTreeModel model = new EasyUiTreeModel();
				for(Entry<String, MetaField> e : itfSet){
					model.setAttribute(e.getKey(), m.get(e.getValue().getId()));
				}
				model.setAttributes(m);
				mt.put(model.getId(), model);
			}
			Set<Entry<String, EasyUiTreeModel>> mop = mt.entrySet();
			List<EasyUiTreeModel> root = new ArrayList<>();
			for(Entry<String, EasyUiTreeModel> entry : mop){
				EasyUiTreeModel mo = entry.getValue();
				String parent = mo.getParent();	
				if(StringUtils.isEmpty(parent) || "root".equalsIgnoreCase(parent) || null == mt.get(parent)){// null == mt.get(parent)垃圾数据或数据错误导致
					root.add(mo);
					continue;
				}
				List<EasyUiTreeModel> children = mt.get(parent).getChildren();
				if(ArrayUtils.isEmpty(children)){
					children = new ArrayList<>();
					children.add(mo);
					mt.get(parent).setChildren(children);
					continue;
				}
				mt.get(parent).getChildren().add(mo);
			}
			
			return root;
		}
		return new ArrayList<>();
	}
}
