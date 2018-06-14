package com.shareworx.platform.persist.service;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.shareworx.platform.api.model.ReferenceModel;
import com.shareworx.platform.api.model.TreeModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.metadata.MetaClass;
import com.shareworx.platform.metadata.MetaClassRelation;
import com.shareworx.platform.metadata.MetaDatas;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.metadata.MetadataContents;
import com.shareworx.platform.metadata.MetadataFactory;
import com.shareworx.platform.metadata.service.MetaCodeService;
import com.shareworx.platform.metadata.service.MetaCodeServiceImpl;
import com.shareworx.platform.model.PagedResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.DomainModel;
import com.shareworx.platform.persist.IDomainModel;
import com.shareworx.platform.persist.IQuery;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.persist.dao.ObjectPersistDaoImpl;
import com.shareworx.platform.persist.object.SimpleQuery;
import com.shareworx.platform.persist.service.BaseDomainService;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.ModelUtils;
import com.shareworx.platform.util.ShareworxAssert;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 领域模型持久化操作默认实现(基于元数据)
 * @author zhentong.jia
 *
 */
@SuppressWarnings("unchecked")
public class BaseDomainServiceImpl implements BaseDomainService {
	
	private ObjectPersistDao objectPersistDao;
	
	private MetadataFactory factory;
	
	private MetaCodeService metaCodeService;

	public void setMetaCodeService(MetaCodeService metaCodeService) {
		this.metaCodeService = metaCodeService;
	}
	
	public void setObjectPersistDao(ObjectPersistDao objectPersistService) {
		this.objectPersistDao = objectPersistService;
	}
	
	public MetadataFactory getFactory() {
		if(factory == null){
			factory = SpringUtils.getBean("metadataFactory");
		}
		return factory;
	}
	
	public MetaCodeService getMetaCodeService() {
		if(metaCodeService == null){
			metaCodeService = SpringUtils.getBean(MetaCodeServiceImpl.ID);
		}
		return metaCodeService;
	}
	
	public ObjectPersistDao getObjectPersistDao() {
		if(objectPersistDao == null){
			objectPersistDao = SpringUtils.getBean(ObjectPersistDaoImpl.ID);
		}
		return objectPersistDao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#save(java.lang.String, com.shareworx.platform.persist.DomainModel[])
	 */
	@Override
	public <T extends IDomainModel> List<T> save(String metaName, T... models) throws ShareworxServiceException {
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		
		//处理属性结构
		doWidthTreeModel(metaName, models);
		getObjectPersistDao().saveModels(metaName, models);
		
		MetaField primaryField = metaClass.getPrimarys().get(0);
		List<MetaClassRelation> relationList = metaClass.getRelations();
		Map<String, List<IDomainModel>> childModelMap = new HashMap<>();
		for(T model: models){
			if(!ArrayUtils.isEmpty(relationList)){
				for(MetaClassRelation relation: relationList){
					if(relation.getRelation() != MetadataContents.RELATION_COMPOSITATION){
						continue;
					}
					IDomainModel[] childArray = castChildModel(model, relation, primaryField);
					if(ArrayUtils.isEmpty(childArray)){
						continue;
					}
					if(!childModelMap.containsKey(relation.getMetato())){
						childModelMap.put(relation.getMetato(), new ArrayList<IDomainModel>());
					}
					childModelMap.get(relation.getMetato()).addAll(Arrays.asList(childArray));
				}
			}
		}
		if(!childModelMap.isEmpty()){
			for(Entry<String, List<IDomainModel>> entry: childModelMap.entrySet()){
				String childMetaName = entry.getKey();
				List<IDomainModel> childModels = entry.getValue();
				save(childMetaName, childModels.toArray(new DomainModel[0]));
			}
		}
		return Arrays.asList(models);
	}
	
	/**
	 * 子表模型转换
	 * @param model
	 * @param relation
	 * @return
	 */
	private <T extends IDomainModel> IDomainModel[] castChildModel(T model, MetaClassRelation relation, MetaField primaryField) {
		List<DomainModel> childModels = model.getChildAttributes(relation.getFieldfrom());
		if(childModels == null) {
			return new IDomainModel[0];
		}
		IDomainModel[] childArray = new IDomainModel[childModels.size()];
		for(int i=0;i<childModels.size();i++){
			if(childModels.get(i) == null){
				continue;
			}
			DomainModel childModel = null;
			if(childModels.get(i) instanceof DomainModel){
				childModel = childModels.get(i);
			}else if(childModels.get(i) instanceof Map){
				childModel = new DomainModel((Map<String, Object>)childModels.get(i));
				childModel.setMetaName(relation.getMetato());
			}else{
				throw new ShareworxServiceException("未知对象类型：" + childModels.get(i).getClass());
			}
			if(primaryField != null) {
				childModel.setAttribute(relation.getFieldto(), model.getAttribute(primaryField.getId()));
			}
			childArray[i] = childModel;
		}
		return childArray;
	}
	
	private <T extends IDomainModel> void doWidthTreeModel(String metaName, T[] models) {
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		Map<String, MetaField> map = MetaDatas.getFieldsByInterface(metaName, MetadataContents.INF_TREE);
		if(ArrayUtils.isEmpty(map) || ArrayUtils.isEmpty(models)) {
			return;
		}
		if(!map.containsKey(TreeModel.TREECODE) || !map.containsKey(TreeModel.PK_PARENT)){
			return;
		}
		MetaField treeCodeField = map.get(TreeModel.TREECODE);
		MetaField parentField = map.get(TreeModel.PK_PARENT);
		
		Map<String, List<T>> nodeMap = new HashMap<>();
		for(T model: models) {
			String pk_parent = model.getAttribute(parentField.getId());
			if(!nodeMap.containsKey(pk_parent)){
				nodeMap.put(pk_parent, new ArrayList<T>());
			}
			nodeMap.get(pk_parent).add(model);
		}
		for(Entry<String, List<T>> entry: nodeMap.entrySet()){
			String pk_parent = entry.getKey();
			T parentModel = getObjectPersistDao().queryById(metaName, pk_parent);
			String parentCode = "";
			if(parentModel != null && !StringUtils.isEmpty((String) parentModel.getAttribute(treeCodeField.getId()))){
				parentCode = parentModel.getAttribute(treeCodeField.getId());
			}
			String sql = "select max(" + treeCodeField.getColumn() + ") from " + metaClass.getTable();
			if(!StringUtils.isEmpty(pk_parent)){
				sql += " where " + parentField.getColumn() + "='" + pk_parent + "'";
			}else{
				sql += " where " + parentField.getColumn() + "='" + pk_parent + "'";
				sql += " or " + parentField.getColumn() + "='root'";
				sql += " or " + parentField.getColumn() + " is null";
			}
			String maxCode = getObjectPersistDao().queryObjectByCondition(new SimpleQuery(sql), new ResultSetExtractor<String>() {

				@Override
				public String extractData(ResultSet rs) throws SQLException, DataAccessException {
					if(rs.next()){
						return rs.getString(1);
					}
					return null;
				}
			});
			int index = 0;
			if(!StringUtils.isEmpty(maxCode)){
					String numCode = maxCode.substring(parentCode.length() + 1);
					index = Integer.parseInt(numCode, 36);
			}
			List<T> modelList = entry.getValue();
			for(T model: modelList) {
				String code = parentCode + "." + Integer.toString((++index), 36); 
				model.setAttribute(treeCodeField.getId(), code);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#update(java.lang.String, com.shareworx.platform.persist.DomainModel[])
	 */
	@Override
	public <T extends IDomainModel> List<T> update(String metaName, T... models) throws ShareworxServiceException {
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		
		List<String> editFields = new ArrayList<>();
		List<MetaField> fieldList = metaClass.getFields();
		for(MetaField field: fieldList){
			editFields.add(field.getColumn());
		}
		return update(metaName, editFields, models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#update(java.lang.String, java.util.List, com.shareworx.platform.persist.DomainModel[])
	 */
	@Override
	public <T extends IDomainModel> List<T> update(String metaName, List<String> editFields, T... models) throws ShareworxServiceException {
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		List<MetaField> pkFieldList = metaClass.getPrimarys();
		ShareworxAssert.assertCollectionNotEmpty(pkFieldList, String.format("元数据%s未配置主键", metaName));
		MetaField primaryField = pkFieldList.get(0);
		List<MetaClassRelation> relationList = metaClass.getRelations();
		for(T model: models){
			getObjectPersistDao().updateModels(metaName, new IDomainModel[]{model}, editFields.toArray(new String[0]), new String[]{});
			if(!ArrayUtils.isEmpty(relationList) && pkFieldList.size() == 1){
				for(MetaClassRelation relation: relationList){
					if(relation.getRelation() != MetadataContents.RELATION_COMPOSITATION){
						continue;
					}
					//删除子表所有数据
					MetaClass childMetaClass = getFactory().getMetaClassByName(relation.getMetato());
					List<MetaField> childFields = childMetaClass.getFields();
					String childfk = relation.getFieldto();
					String childFkColumn = null;
					for(MetaField childField: childFields){
						if(childfk.equals(childField.getId())){
							childFkColumn = childField.getColumn();
							break;
						}
					}
					Delete delete = Delete.delete(relation.getMetato()).where(Condition.create(childFkColumn).eq(model.getAttribute(primaryField.getId())));
//					getObjectPersistDao().deleteByCondition(delete);
					deleteByConditions(delete);
					
					//子表数据为空时不处理
					List<DomainModel> childModels = model.getChildAttributes(relation.getFieldfrom());
					if(ArrayUtils.isEmpty(childModels)){
						continue;
					}
					
					//新增保存子表数据
					IDomainModel[] childArray = castChildModel(model, relation, primaryField);
					if(ArrayUtils.isEmpty(childArray)){
						continue;
					}
//					getObjectPersistDao().saveModels(relation.getMetato(), childArray);
					save(relation.getMetato(), childArray);
				}
			}
		}
		return Arrays.asList(models);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#delete(java.lang.String, com.shareworx.platform.persist.DomainModel[])
	 */
	@Override
	public <T extends IDomainModel> int delete(String metaName, T... models) throws ShareworxServiceException {
		checkDelete(metaName, models);
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		List<MetaClassRelation> relationList = metaClass.getRelations();

		List<MetaField> pkFieldList = MetaDatas.getPrimaryFields(metaName);
		ShareworxAssert.assertCollectionNotEmpty(pkFieldList, String.format("元数据%s未配置主键", metaName));
		if(MetaDatas.hasRealize(metaName, "inf_version")) {
			List<String> pkList = ModelUtils.getModelFieldValues(Arrays.asList(models), pkFieldList.get(0).getId());
			Delete delete = Delete.delete("pub_version").where(Condition.eq("pk_meta_", metaName)).and(Condition.in("pk_bill_", pkList.toArray()));
			deleteByConditions(delete);
		}
		if(!ArrayUtils.isEmpty(relationList) && pkFieldList.size() == 1){
			List<String> pkList = new ArrayList<>();
			MetaField pkField  = pkFieldList.get(0);
			for(T model: models){
				String pk = (String)model.getAttribute(pkField.getId());
				if(StringUtils.isEmpty(pk)){
					//加载对象
				}
				pkList.add(pk);
				if(pkFieldList.size() > 1){
					continue;
				}
				for(MetaClassRelation relation: relationList){
					if(relation.getRelation() == MetadataContents.RELATION_COMPOSITATION){
//						DomainModel[] childArray = castChildModel(model, relation, null);
//						if(ArrayUtils.isEmpty(childArray)){
//							continue;
//						}
//						getObjectPersistDao().deleteModels(relation.getMetato(), childArray);
						MetaField field = MetaDatas.getFieldById(relation.getMetato(), relation.getFieldto());
						Delete delete = Delete.delete(relation.getMetato()).where(Condition.create(field.getColumn()).eq(pk));
						deleteByConditions(delete);
					}
				}
			}
			/*
			 * 当对象为元数据实现树状模型接口时，连带删除其子节点
			 */
			boolean isTreeModel = MetaDatas.hasRealize(metaName, MetadataContents.INF_TREE);
			if(isTreeModel){
				Map<String, MetaField> infFieldMap = MetaDatas.getFieldsByInterface(metaName, MetadataContents.INF_TREE);
				MetaField parentField = infFieldMap.get(TreeModel.PK_PARENT);
				if(parentField != null){
					Query query = Query.from(metaName).where(Condition.create(parentField.getColumn()).in(pkList.toArray()));
					List<T> subList = queryListByCondition(query);
					if(!ArrayUtils.isEmpty(subList)){
						T[] tArray = (T[]) Array.newInstance(subList.get(0).getClass(), subList.size());
						int index = 0;
						for(T t:subList){
							tArray[index++] = t;
						}
						delete(metaName, tArray);
					}
				}
			}
		}
		return getObjectPersistDao().deleteModels(metaName, models);
	}
	
	/**
	 * 验证对象是否可以被删除
	 * @param metaName
	 * @param models
	 */
	public <T extends IDomainModel> void checkDelete(String metaName, T... models){
		List<MetaClassRelation> relationList = getObjectPersistDao().queryObjectByCondition(Query.from(MetadataContents.METADATA_CLASSRELATION).where(Condition.create("relation_").eq(MetadataContents.RELATION_ASSOCIATION)).and(Condition.create("metato_").eq(metaName)), new ResultSetExtractor<List<MetaClassRelation>>() {

			@Override
			public List<MetaClassRelation> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<MetaClassRelation> list = new ArrayList<>();
				while(rs.next()){
					MetaClassRelation model = new MetaClassRelation();
					model.setMetafrom(rs.getString("metafrom_"));
					model.setMetato(rs.getString("metato_"));
					model.setFieldfrom(rs.getString("fieldfrom_"));
					model.setFieldto(rs.getString("fieldto_"));
					model.setRelation(rs.getInt("relation_"));
					list.add(model);
				}
				return list;
			}
		});
		if(!ArrayUtils.isEmpty(relationList)){
			List<Object> pkList = ModelUtils.getModelPrimarys(Arrays.asList(models));
			for(MetaClassRelation relation: relationList){
				MetaField field = MetaDatas.getFieldById(relation.getMetafrom(), relation.getFieldfrom());
				Query query = Query.from(relation.getMetafrom()).where(Condition.create(field.getColumn()).in(pkList.toArray()));
				long count = getObjectPersistDao().countListByCondition(query);
				if(count > 0){
					MetaClass metaClass = getFactory().getMetaClassByName(relation.getMetafrom());
					throw new ShareworxServiceException("当前数据已被[" + metaClass.getName() + "]引用");
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#deleteByIds(java.lang.String, java.lang.String[])
	 */
	@Override
	public int deleteByIds(String metaName, String[] ids) throws ShareworxServiceException {
		List<DomainModel> list = ModelUtils.toModelList(metaName,Arrays.asList(ids), true);
		if(ArrayUtils.isEmpty(list)){
			return 0;
		}
		return delete(metaName, list.toArray(new DomainModel[0]));
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#deleteByConditions(java.lang.String, com.shareworx.platform.persist.Condition[])
	 */
	@Override
	public int deleteByConditions(Delete delete) throws ShareworxServiceException {
		Query query = delete.toQuery();
		List<DomainModel> list = queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return 0;
		}
		String metaName  = delete.getMetas().get(0);
		
		return delete(metaName, list.toArray(new DomainModel[0]));
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#queryById(java.lang.String, java.io.Serializable)
	 */
	@Override
	public <T extends IDomainModel> T queryById(String metaName, Serializable id) throws ShareworxServiceException {
		return queryById(metaName, new Serializable[]{id});
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#queryById(java.lang.String, java.io.Serializable[])
	 */
	@Override
	public <T extends IDomainModel> T queryById(String metaName, Serializable[] ids) throws ShareworxServiceException {
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		List<MetaField> pkFieldList = metaClass.getPrimarys();
		ShareworxAssert.assertCollectionNotEmpty(pkFieldList, String.format("元数据%s未配置主键", metaName));
		ShareworxAssert.doAssert(ArrayUtils.isEmpty(ids) || ids.length != pkFieldList.size(), "主键参数个数不正确，应为" + pkFieldList.size());
		Query query = Query.from(metaName);
		int index = 0;
		for(MetaField pkField: pkFieldList){
			query.and(Condition.create(pkField.getColumn()).eq(ids[index++]));
		}
		return queryOneByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#queryByExample(java.lang.String, com.shareworx.platform.persist.DomainModel)
	 */
	@Override
	public <T extends IDomainModel> List<T> queryByExample(String metaName, T model) throws ShareworxServiceException {
		if(model == null){
			return queryListByCondition(Query.from(metaName));
		}
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		Query query = Query.from(metaName);
		for(MetaField field: metaClass.getFields()){
			Object value = model.getAttribute(field.getId());
			if(value == null){
				continue;
			}
			if(field.getType() == MetadataContents.FIELD_TYPE_BLOB || field.getType() == MetadataContents.FIELD_TYPE_AGGREGATE || field.getType() == MetadataContents.FIELD_TYPE_COMPOSITE){
				continue;
			}
			Condition condition = new Condition(field.getColumn(), QueryContents.TYPE_EQ, value);
			query.and(condition);
		}
		return queryListByCondition(query);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#queryOneByCondition(java.lang.String, com.shareworx.platform.persist.Condition)
	 */
	@Override
	public <T extends IDomainModel> T queryOneByCondition(IQuery query) throws ShareworxServiceException {
		String metaName = query.getMetas().get(0);
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		T model = getObjectPersistDao().queryOneByCondition(query);
		if(model == null){
			return null;
		}
		List<MetaField> pkFieldList = metaClass.getPrimarys();
		if(!ArrayUtils.isEmpty(pkFieldList) && pkFieldList.size() == 1){
			queryRelations(metaClass, Arrays.asList(model));
		}
		return model;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#queryListByCondition(java.lang.String, com.shareworx.platform.persist.Condition)
	 */
	@Override
	public <T extends IDomainModel> List<T> queryListByCondition(IQuery query) throws ShareworxServiceException {
		String metaName = query.getMetas().get(0);
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		List<T> modelList = getObjectPersistDao().queryListByCondition(query);
		List<MetaField> pkFieldList = metaClass.getPrimarys();
		if(!ArrayUtils.isEmpty(pkFieldList) && pkFieldList.size() == 1){
			queryRelations(metaClass, modelList);
		}
		return modelList;
	}

	private <T extends IDomainModel> void queryRelations(MetaClass metaClass, List<T> modelList) {
		List<MetaField> pkFieldList = metaClass.getPrimarys();
		ShareworxAssert.assertCollectionNotEmpty(pkFieldList, String.format("元数据%s未配置主键", metaClass.getId()));
		ShareworxAssert.doAssert(pkFieldList.size() > 1, "此操作不支持复合主键");
		
		MetaField primaryField = pkFieldList.get(0);
		if(!ArrayUtils.isEmpty(modelList)){
			Map<Object, T> map = new HashMap<>();
			for(T model: modelList){
				map.put(model.getAttribute(primaryField.getId()), model);
			}
			List<MetaClassRelation> relations = new ArrayList<>();
			List<MetaClassRelation> metaRelations = metaClass.getRelations();
			if(!ArrayUtils.isEmpty(metaRelations)){
				relations.addAll(metaRelations);
			}
			List<MetaField> fieldList = metaClass.getFields();
			for(MetaField field: fieldList){
				if(MetadataContents.FIELD_TYPE_BLOB == field.getType()){
					MetaClassRelation relation = new MetaClassRelation();
					relation.setMetafrom(metaClass.getId());
					relation.setMetato("pubfile");
					relation.setRelation(MetadataContents.RELATION_ASSOCIATION);
					relation.setFieldfrom(field.getId());
					relation.setFieldto("pk_file");
					relations.add(relation);
				}
			}
			if(!ArrayUtils.isEmpty(relations)) {
				for(MetaClassRelation relation: relations){
					if(relation.getRelation() == MetadataContents.RELATION_COMPOSITATION || relation.getRelation() == MetadataContents.RELATION_AGGREGATION){
						String childMetaName = relation.getMetato();
						String childfk = relation.getFieldto();
						MetaClass childClass = getFactory().getMetaClassByName(childMetaName);
						List<MetaField> childFields = childClass.getFields();
						String childFkColumn = null;
						for(MetaField childField: childFields){
							if(childfk.equals(childField.getId())){
								childFkColumn = childField.getColumn();
								break;
							}
						}
						List<Object> keyList = ModelUtils.getModelFieldValues(modelList, primaryField.getId());
						Query childQuery = Query.from(childMetaName).where(Condition.create(childFkColumn).in(keyList.toArray()));
						List<DomainModel> childList = queryListByCondition(childQuery);//getObjectPersistDao().queryListByCondition(childQuery);
						if(ArrayUtils.isEmpty(childList)){
							continue;
						}
						for(DomainModel childModel: childList){
							Object key = childModel.getAttribute(childfk);
							if(map.containsKey(key)){
								map.get(key).addArrayAttribute(relation.getFieldfrom(), childModel);
							}
						}
					}else if(relation.getRelation() == MetadataContents.RELATION_ASSOCIATION){
						loadReference(relation, modelList);
					}
				}
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#queryListBySql(java.lang.String, java.lang.String, java.lang.Object[])
	 */
	@Override
	public <T extends IDomainModel> List<T> queryListBySql(String metaName, String conditionSQL, Object... args) throws ShareworxServiceException {
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		String sql = "select * from " + metaClass.getTable() + " where " + conditionSQL;
		List<T> modelList = getObjectPersistDao().queryBySql(metaName, sql, args);
		if(!ArrayUtils.isEmpty(modelList)){
			List<MetaField> pkFieldList = metaClass.getPrimarys();
			if(!ArrayUtils.isEmpty(pkFieldList) && pkFieldList.size() == 1){
				queryRelations(metaClass, modelList);
			}
		}
		return modelList;
	}
	
	/**
	 * 装载参照模型数据
	 * @param relation
	 * @param modelList
	 */
	private <T extends IDomainModel> void loadReference(MetaClassRelation relation, List<T> modelList){
		MetaClass metaClass = MetaDatas.getFactory().getMetaClassByName(relation.getMetato());
		if(metaClass.getType() == MetadataContents.META_TYPE_ENTITY){
			Set<Object> set = new HashSet<>();
			for(IDomainModel model: modelList){
				set.add(model.getAttribute(relation.getFieldfrom()));
			}
			Map<Object, DomainModel> referMap = queryByReference(relation, set);
			if(!ArrayUtils.isEmpty(referMap)){
				Map<String, MetaField> fieldMap = MetaDatas.getFieldsByInterface(relation.getMetato(), MetadataContents.INF_REFERENCE);
				MetaField keyField = fieldMap.get(ReferenceModel.REFKEY);
				MetaField valueField = fieldMap.get(ReferenceModel.REFVALUE);
				for(IDomainModel model: modelList){
//					model.setAttribute("refer_" + relation.getFieldfrom(), referMap.get(model.getAttribute(relation.getFieldfrom())));
					DomainModel referModel = referMap.get(model.getAttribute(relation.getFieldfrom()));
					if(referModel != null){
						if(keyField != null){
							referModel.setAttribute(DomainModel.$_REFERENCE_KEY, referModel.getAttribute(keyField.getId()));
						}
						if(valueField != null){
							referModel.setAttribute(DomainModel.$_REFERENCE_VALUE, referModel.getAttribute(valueField.getId()));
						}
						model.setReferValue(relation.getFieldfrom(), referModel);
					}
				}
			}
		}else if(metaClass.getType() == MetadataContents.META_TYPE_ENUMERATION) {
			Map<Serializable, String> enumMap = MetaDatas.getEnums(relation.getMetato());
			if(!ArrayUtils.isEmpty(enumMap)){
				for(T model: modelList) {
					Serializable enumVal = model.getAttribute(relation.getFieldfrom());
					if(enumVal == null || "".equals(enumVal)){
						continue;
					}
					if(enumMap.containsKey(enumVal)){
						Map<String, Object> conts = new HashMap<>(2);
						conts.put(DomainModel.$_REFERENCE_KEY, enumVal);
						conts.put(DomainModel.$_REFERENCE_VALUE, enumMap.get(enumVal));
						model.setReferValue(relation.getFieldfrom(), conts);
					}
				}
			}
		}
	}

	/**
	 * 查询参照信息
	 * @param meta
	 * @param ids
	 * @return
	 */
	private <T extends IDomainModel> Map<Object, T> queryByReference(MetaClassRelation metaRelation, Collection<Object> ids) {
		MetaField field = MetaDatas.getFieldById(metaRelation.getMetato(), metaRelation.getFieldto());
		Query query = Query.from(metaRelation.getMetato()).where(Condition.create(field.getColumn()).in(ids.toArray(new Object[0])));
		List<T> list = objectPersistDao.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)) return null;
		Map<Object, T> map = new HashMap<>();
		for(T model: list){
			map.put(model.getAttribute(field.getId()), model);
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.platform.persist.service.BaseDomainService#queryListByPage(com.shareworx.platform.persist.IQuery, int, int)
	 */
	@Override
	public <T extends IDomainModel> PagedResult<T> queryListByPage(IQuery query, int start, int limit) throws ShareworxServiceException {
		long size = getObjectPersistDao().countListByCondition(query);
		if(limit <= 0){
			limit = 20;
		}
		if(start < 0){
			start = 0;
		}else if(start >= size) {
			start -= limit;
		}
		query.setStart(start);
		query.setLimit(limit);
		List<T> list = queryListByCondition(query);
		PagedResult<T> result = new PagedResult<>();
		result.setData(list);
		result.setTotal(size);
		result.setPage(start / limit + 1);
		return result;
	}
}
