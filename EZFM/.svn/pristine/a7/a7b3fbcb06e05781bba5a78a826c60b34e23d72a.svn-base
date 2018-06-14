package com.shareworx.platform.persist.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.shareworx.platform.exception.FieldValidRuntimeException;
import com.shareworx.platform.exception.ShareworxRuntimeException;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.file.model.FileModel;
import com.shareworx.platform.metadata.MetaClass;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.metadata.MetadataContents;
import com.shareworx.platform.metadata.MetadataFactory;
import com.shareworx.platform.metadata.TypeMapping;
import com.shareworx.platform.metadata.service.MetaCodeService;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Delete;
import com.shareworx.platform.persist.IDomainModel;
import com.shareworx.platform.persist.IQuery;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.Update;
import com.shareworx.platform.persist.service.MetadataResultExtractor;
import com.shareworx.platform.persist.service.MetadataResultSetExactor;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.ModelUtils;
import com.shareworx.platform.util.ShareworxAssert;
import com.shareworx.platform.util.SpringUtils;
import com.shareworx.platform.util.StringUtils;

/**
 * 对象存储实现
 * 
 * @author zhentong.jia
 * 
 */
public class ObjectPersistDaoImpl implements ObjectPersistDao {

	private MetadataFactory factory;

	private MetaCodeService metaCodeService;

	private JdbcTemplate writejt;

//	private JdbcTemplate readjt;

	public JdbcTemplate getWriteTemplate() {
		if (writejt == null) {
			writejt = DatabaseConnections.getWriteTemplate();
		}
		return writejt;
	}

	/**
	 * @Deprecated
	 */
	public JdbcTemplate getReadTemplate() {
//		if (readjt == null) {
//			readjt = DatabaseConnections.getReadTemplate();
//		}
//		return readjt;
		return getWriteTemplate();
	}

	public JdbcTemplate getWritejt() {
		return writejt;
	}

	public void setWritejt(JdbcTemplate writejt) {
		this.writejt = writejt;
	}

	public MetadataFactory getFactory() {
		if (factory == null) {
			factory = SpringUtils.getBean("metadataFactory");
		}
		return factory;
	}

	public void setMetaCodeService(MetaCodeService metaCodeService) {
		this.metaCodeService = metaCodeService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.plms.core.service.ObjectPersistService#saveModels(java.
	 * lang.String, com.shareworx.plms.core.DomainModel[])
	 */
	@Override
	public int[] saveModels(String metaName, IDomainModel[] models) throws ShareworxRuntimeException {
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		List<MetaField> fieldList = metaClass.getFields();
		List<MetaField> primaryList = metaClass.getPrimarys();

		List<Object[]> argList = new ArrayList<Object[]>();
//		List<Object[]> dynamicValList = new ArrayList<Object[]>();
		
		//处理编码自动生成
		Map<String, Boolean> genMap = new HashMap<>();
		for (MetaField field : fieldList) {
			if(!StringUtils.isEmpty(field.getSerialcode())){
				boolean haveToGenCode = false;
				if (field.getSerialmode().equals(1)) {
					haveToGenCode = true;
				}
				genMap.put(field.getSerialcode() + ":" + field.getId(), haveToGenCode);
			}
		}
		if(!ArrayUtils.isEmpty(genMap)){
			for(Entry<String, Boolean> entry: genMap.entrySet()) {
				String[] keys = entry.getKey().split(":");
				boolean force = entry.getValue();
				String serialcode = keys[0];
				String fieldCode = keys[1];
				if(serialcode.equalsIgnoreCase("uuid") || serialcode.equalsIgnoreCase("guid")){
					for(IDomainModel model: models){
						model.setAttribute(fieldCode, UUID.randomUUID().toString());
					}
				}else{
					metaCodeService.execGenerateCode(serialcode, fieldCode, force, models);
				}
			}
		}
		//编码规则处理完毕
		int i=0;
		for (IDomainModel model : models) {
			List<Object> args = new ArrayList<Object>();
			for (MetaField field : fieldList) {
				if (field.getPersist() == null || !field.getPersist()) {
					continue;
				}
				if (field.getDynamic() != null && field.getDynamic()) {
					continue;
				}
				if(field.getType() == MetadataContents.FIELD_TYPE_AGGREGATE || field.getType() == MetadataContents.FIELD_TYPE_COMPOSITE){
					continue;
				}
				Object value = model.getAttribute(field.getId());
				if (value == null || "".equals(value)) {
					value = field.getDefval();
				}
				if (primaryList.contains(field) && (value == null || value.equals(""))) {
					throw new ShareworxRuntimeException("主键值不能为空");
				}
				try {
					validateFieldValue(field, value, true);
				} catch (FieldValidRuntimeException e) {
					e.setRow(i);
					throw e;
				}
				
				Object persistVal = TypeMapping.getObjectValueByTypeWithDefault(value, field);
				args.add(persistVal);
			}
			argList.add(args.toArray());
			i++;
		}
		String sql = "insert into " + metaClass.getTable();
		StringBuffer fieldSql = new StringBuffer();
		StringBuffer valSql = new StringBuffer();
		for (MetaField field : fieldList) {
			if (field.getPersist() == null || !field.getPersist()) {
				continue;
			}
			if (field.getDynamic() != null && field.getDynamic()) {
				continue;
			}
			if(field.getType() == MetadataContents.FIELD_TYPE_AGGREGATE || field.getType() == MetadataContents.FIELD_TYPE_COMPOSITE){
				continue;
			}
			fieldSql.append(field.getColumn()).append(",");
			valSql.append("?,");
		}
		fieldSql.deleteCharAt(fieldSql.length() - 1);
		valSql.deleteCharAt(valSql.length() - 1);
		sql += "(" + fieldSql + ") values (" + valSql + ")";
		int[] rsArray = getWriteTemplate().batchUpdate(sql, argList);

		doWidthFile(metaClass, models, FileOperator.SAVE);
		return rsArray;
	}

	/**
	 * 在元数据级别验证数据输入的合法性
	 * 
	 * @param field
	 * @param value
	 * @param insert
	 */
	/*
	private void validateFieldValue(MetaField field, Object value, boolean insert) {
		// 非空校验
		if ((value == null || "".equals(value)) && !field.getEmpty().booleanValue()) {
			throw new ShareworxRuntimeException(field.getMeta() + ":" + field.getId() + " 不能为空");
		}
		if (value != null) {
			String strVal = value.toString();
			switch (field.getType()) {
			case MetadataContents.FIELD_TYPE_CHARACTER:
			case MetadataContents.FIELD_TYPE_STRING:
				// 字符串长度校验
				if (field.getLength() > 0 && value.toString().length() > field.getLength()) {
					throw new ShareworxRuntimeException(field.getName() + "长度不能超过" + field.getLength() + ", " + value);
				}
				break;
			case MetadataContents.FIELD_TYPE_DECIMAL:
				// 数值长度及精度校验
				int left = field.getPrecision() - field.getScale();
				String[] strVals = strVal.split("[.]");
				if(left == 0 && strVals[0].equals("0")){
					break;
				}
				if (strVals[0].length() > left) {
					throw new ShareworxRuntimeException(field.getName() + "整数部分长度不能超过" + left);
				}
				break;
			default:
				break;
			}
		}
	}
	*/
	
	private void validateFieldValue(MetaField field, Object value, boolean insert) {
		// 非空校验
		if ((value == null || "".equals(value)) && !field.getEmpty().booleanValue()) {
			throw new FieldValidRuntimeException(field.getName(), field.getColumn(), value, "不能为空！");
		}
		if (value != null) {
			String strVal = value.toString();
			switch (field.getType()) {
			case MetadataContents.FIELD_TYPE_CHARACTER:
			case MetadataContents.FIELD_TYPE_STRING:
				// 字符串长度校验
				if (field.getLength() > 0 && value.toString().length() > field.getLength()) {
					throw new FieldValidRuntimeException(field.getName(), field.getColumn(), value, "长度不能超过"+field.getLength());
				}
				break;
			case MetadataContents.FIELD_TYPE_DECIMAL:
				// 数值长度及精度校验
				int left = field.getPrecision() - field.getScale();
				String[] strVals = strVal.split("[.]");
				if(left == 0 && strVals[0].equals("0")){
					break;
				}
				if (strVals[0].length() > left) {
					throw new FieldValidRuntimeException(field.getName(), field.getColumn(), value, "整数部分长度不能超过"+left);
				}
				break;
			default:
				break;
			}
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.platform.persist.service.ObjectPersistService#updateModels
	 * (java.lang.String, com.shareworx.platform.persist.DomainModel[])
	 */
	@Override
	public int[] updateModels(String metaName, IDomainModel[] models) throws ShareworxRuntimeException {
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		List<MetaField> pkFieldList = metaClass.getPrimarys();
		ShareworxAssert.assertCollectionNotEmpty(pkFieldList, String.format("元数据%s未配置主键", metaName));
		int[] rs = new int[models.length];
		for (int i = 0; i < models.length; i++) {
			Map<String, Object> argMap = new HashMap<>();
			for(MetaField field: metaClass.getFields()){
				if (field.getPersist() == null || !field.getPersist()) {
					continue;
				}
				if(pkFieldList.contains(field)){
					continue;
				}
				if(field.getType() == MetadataContents.FIELD_TYPE_BLOB
						|| field.getType() == MetadataContents.FIELD_TYPE_COMPOSITE
						|| field.getType() == MetadataContents.FIELD_TYPE_AGGREGATE){
					continue;
				}
				validateFieldValue(field, models[i].getAttribute(field.getId()), false);
				argMap.put(field.getColumn(), models[i].getAttribute(field.getId()));
			}
			Update update = Update.update(metaName).set(argMap);
			for(MetaField pkField: pkFieldList){
				Object pkVal = models[i].getAttribute(pkField.getId());
				ShareworxAssert.assertNotNull(pkVal, "primary key value is required: " + pkField.getColumn());
				update.and(Condition.create(pkField.getColumn()).eq(pkVal));
			}
			rs[i] = updateModelsByCondition(update);
		}
		doWidthFile(metaClass, models, FileOperator.UPDATE);
		return rs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.shareworx.platform.persist.service.ObjectPersistService#updateModels
	 * (java.lang.String, com.shareworx.platform.persist.DomainModel[],
	 * java.lang.String[], java.lang.String[])
	 */
	@Override
	public int[] updateModels(String metaName, IDomainModel[] models, String[] include, String[] notInclude) throws ShareworxRuntimeException {
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		List<MetaField> pkFieldList = metaClass.getPrimarys();
		ShareworxAssert.assertCollectionNotEmpty(pkFieldList, String.format("元数据%s未配置主键", metaName));
		int[] rs = new int[models.length];
		List<String> includeList = Arrays.asList(include);
		List<String> notIncludeList = Arrays.asList(notInclude);
		for (int i = 0; i < models.length; i++) {
			Map<String, Object> argMap = new HashMap<String, Object>();
			for(MetaField field: metaClass.getFields()){
				if (field.getPersist() == null || !field.getPersist()) {
					continue;
				}
				String key = field.getColumn();
				if(pkFieldList.contains(field)){
					continue;
				}
				if(field.getType() == MetadataContents.FIELD_TYPE_COMPOSITE
						|| field.getType() == MetadataContents.FIELD_TYPE_AGGREGATE){
					continue;
				}
				if (!ArrayUtils.isEmpty(includeList) && !includeList.contains(key)) {
					continue;
				}
				if (!ArrayUtils.isEmpty(notIncludeList) && notIncludeList.contains(key)) {
					continue;
				}
				validateFieldValue(field, models[i].getAttribute(field.getId()), false);
				argMap.put(field.getColumn(), models[i].getAttribute(field.getId()));
			}
			
			Update update = Update.update(metaName).set(argMap);
			for(MetaField pkField: pkFieldList){
				Object pkVal = models[i].getAttribute(pkField.getId());
				ShareworxAssert.assertNotNull(pkVal, "primary key value is required: " + pkField.getColumn());
				update.and(Condition.create(pkField.getColumn()).eq(pkVal));
			}
			rs[i] = updateModelsByCondition(update);
		}
		doWidthFile(metaClass, models, FileOperator.UPDATE);
		return rs;
	}
	
	private enum FileOperator {
		SAVE, UPDATE, DELETE
	}
	
	/**
	 * 处理单据中的文件
	 * @param metaClass
	 * @param models
	 * @param isUpdate
	 */
	private <T extends IDomainModel> void doWidthFile(MetaClass metaClass, T[] models, FileOperator oper){
		List<MetaField> fieldList = metaClass.getFields();
		List<MetaField> binFieldList = new ArrayList<>();
		for(MetaField field: fieldList){
			if(field.getType() == MetadataContents.FIELD_TYPE_BLOB){
				binFieldList.add(field);
			}
		}
		if(binFieldList.isEmpty()) return;
		MetaField primaryField = metaClass.getPrimarys().get(0);
		for(T model: models){
			for(MetaField binField: binFieldList){
				if(model.getAttribute(binField.getId()) == null){
					continue;
				}
				if(oper == FileOperator.UPDATE || oper == FileOperator.DELETE){
					Delete delete = Delete.delete(FileModel.META_ID)
							.where(Condition.eq(FileModel.getColumn(FileModel.PK_BILL), model.getAttribute(primaryField.getId())))
							.and(Condition.eq(FileModel.getColumn(FileModel.BILLFIELD), binField.getId() + "@" + metaClass.getId()));
					if(oper == FileOperator.UPDATE){
						delete.and(Condition.neq(FileModel.getColumn(FileModel.PK_FILE), model.getAttribute(binField.getId())));
					}
					deleteByCondition(delete);
				}
				if(oper == FileOperator.SAVE || oper == FileOperator.UPDATE){
					Update update = Update.update(FileModel.META_ID)
							.set(FileModel.getColumn(FileModel.TMP), false)
							.set(FileModel.getColumn(FileModel.BILLFIELD), binField.getId() + "@" + metaClass.getId())
							.set(FileModel.getColumn(FileModel.PK_BILL), model.getAttribute(primaryField.getId()))
							.where(Condition.create(FileModel.getColumn(FileModel.PK_FILE), model.getAttribute(binField.getId())));
					updateModelsByCondition(update);
				}
			}
		}
	}

	@Override
	public int updateModelsByCondition(Update update) throws ShareworxRuntimeException {
		Map<String, Object> map = update.getUpdates();
		MetaClass metaClass = update.getMetaClass();
		List<MetaField> fieldList = metaClass.getFields();
		for(MetaField field: fieldList){
			if(map.containsKey(field.getColumn())){
				Object val = map.get(field.getColumn());
				Object newVal = TypeMapping.getObjectValueByTypeWithDefault(val, field);
				map.put(field.getColumn(), newVal);
			}
		}
		return getWriteTemplate().update(update.sql(), update.getParams());
	}

	@Override
	public int deleteModels(String metaName, IDomainModel[] models) throws ShareworxRuntimeException {
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		List<MetaField> pkFieldList = metaClass.getPrimarys();
		ShareworxAssert.assertCollectionNotEmpty(pkFieldList, String.format("元数据%s未设置主键", metaName));
		if(pkFieldList.size() == 1){
			String primaryKey = pkFieldList.get(0).getColumn();
			List<Object> pkList = ModelUtils.getModelPrimarys(Arrays.asList(models));
			Delete delete = Delete.delete(metaName).where(Condition.create(primaryKey).in(pkList.toArray()));
			String sql = delete.sql();
			Object[] argList = delete.getParams();
			getWriteTemplate().update(sql, argList);
			
			doWidthFile(metaClass, models, FileOperator.DELETE);
		}else{
			for(IDomainModel model: models){
				Delete delete = Delete.delete(metaName);
				for(MetaField field: pkFieldList){
					delete.and(Condition.create(field.getColumn()).eq(model.getAttribute(field.getId())));
				}
				getWriteTemplate().update(delete.sql(), delete.getParams());
			}
		}
		doWidthFile(metaClass, models, FileOperator.DELETE);
		return models.length;
	}

	@Override
	public int deleteByCondition(Delete delete) throws ShareworxRuntimeException {
		return getWriteTemplate().update(delete.sql(), delete.getParams());
	}

	@Override
	public <T extends IDomainModel> T queryById(String metaName, Serializable id) throws ShareworxRuntimeException {
		MetaClass metaClass = getFactory().getMetaClassByName(metaName);
		String column = metaClass.getPrimarys().get(0).getColumn();
		return queryOneByCondition(Query.select().addFrom(metaName).where(Condition.create(column).eq(id)));
	}

	@Override
	public <T extends IDomainModel> List<T> queryListByCondition(IQuery query) throws ShareworxRuntimeException {
		String sql = query.sql();
		List<String> metaList = query.getMetas();
		if (metaList == null || metaList.size() != 1) {
			throw new ShareworxRuntimeException("查询条件元数据必须唯一");
		}
		String metaName = metaList.get(0);
		return getReadTemplate().query(sql, new MetadataResultSetExactor<T>(metaName), query.getParams());
	}

	@Override
	public <T extends IDomainModel> T queryOneByCondition(IQuery query) throws ShareworxRuntimeException {
		String sql = query.sql();
		List<String> metaList = query.getMetas();
		if (metaList == null || metaList.size() != 1) {
			throw new ShareworxRuntimeException("查询条件元数据必须唯一");
		}
		String metaName = metaList.get(0);
		return (T) getReadTemplate().query(sql, new MetadataResultExtractor<T>(metaName), query.getParams());
	}

	@Override
	public Long countListByCondition(IQuery query) throws ShareworxRuntimeException {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(*) ");
		sb.append(query.getFromSQL()).append(" ");
		sb.append(query.getWhereSQL()).append(" ");
		sb.append(query.getGroupBySQL()).append(" ");
		return getReadTemplate().queryForObject(sb.toString(), Long.class, query.getParams());
	}

	@Override
	public <T> T queryObjectByCondition(IQuery query, ResultSetExtractor<T> extractor) {
		String sql = query.sql();
		return getReadTemplate().query(sql, extractor, query.getParams());
	}

	@Override
	public <T extends IDomainModel> List<T> queryBySql(String metaName, String sql, Object... args) throws ShareworxServiceException {
		return getReadTemplate().query(sql, new MetadataResultSetExactor<T>(metaName), args);
	}

}
