package com.shareworx.platform.metadata.service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;

import com.shareworx.platform.metadata.MetaClass;
import com.shareworx.platform.metadata.MetaField;
import com.shareworx.platform.metadata.MetadataContents;
import com.shareworx.platform.metadata.MetadataRuntimeException;
import com.shareworx.platform.metadata.TypeMapping;
import com.shareworx.platform.persist.PersistDialect;
import com.shareworx.platform.persist.PersistSupport;
import com.shareworx.platform.persist.PersistType;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.ShareworxAssert;
import com.shareworx.platform.util.StringUtils;

public class MetadataDefineServiceImpl implements MetadataDefineService {

	public final static String ID = "metadataDefineService";
	
	private Logger log = Logger.getLogger(MetadataDefineServiceImpl.class);
	
	private MetadataPersistService metadataPersistService;
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private JdbcTemplate writejt;
	
	protected JdbcTemplate getWriteTemplate(){
		if(writejt == null){
			writejt = new JdbcTemplate();
			writejt.setDataSource(dataSource);
		}
		return writejt;
	}
	
	public void setMetadataPersistService(MetadataPersistService metadataPersistService) {
		this.metadataPersistService = metadataPersistService;
	}
	
	private String getDbType() {
		return PersistSupport.getInstance().getDatabaseType(dataSource);
	}

	@Override
	public boolean createMetaClass(MetaClass metaClass) throws MetadataRuntimeException {
		if(metaClass.getType() != MetadataContents.META_TYPE_ENTITY){
			return true;
		}
		ShareworxAssert.assertNotNull(metaClass.getTable(), "表名");
		ShareworxAssert.assertNotNull(metaClass.getFields(), "字段");
		ShareworxAssert.assertNotNull(metaClass.getPrimarys(), "主键");
		List<MetaField> primaryList = metaClass.getPrimarys();
		
		String dbType = PersistSupport.getInstance().getDatabaseType(dataSource);
		StringBuffer sb = new StringBuffer();
		sb.append("create table ").append(metaClass.getTable()).append(" (").append(PersistSupport.NEXT_LINE);
		List<MetaField> fieldList = metaClass.getFields();
		for(MetaField field: fieldList){
			if(field.getPersist() == null || !field.getPersist().booleanValue()){
				continue;
			}
			//处理动态字段
			if(field.getDynamic() != null && field.getDynamic()){
				continue;
			}
			ShareworxAssert.assertNotNull(field.getColumn(), "字段名称");
			sb.append("  ").append(field.getColumn()).append(" ");
			if(!StringUtils.isEmpty(field.getDefine())){
				sb.append(field.getDefine());
				if(field.getDefine().indexOf("(") == -1){
					if(field.getType() == MetadataContents.FIELD_TYPE_DECIMAL){
						sb.append("(").append(field.getPrecision());
						sb.append(",").append(field.getScale());
						sb.append(")");
					}else if(field.getLength() > 0){
						sb.append("(").append(field.getLength()).append(")");
					}
				}
			}else{
				int type = field.getType();
				PersistType pt = PersistSupport.getInstance().getType(dbType, type);
				ShareworxAssert.doAssert(pt == null, "unsupport data type: " + type);
//				switch (type) {
//				case MetadataContents.FIELD_TYPE_CHARACTER:
//				case MetadataContents.FIELD_TYPE_STRING:
//				case MetadataContents.FIELD_TYPE_BLOB:
//					sb.append("varchar(").append(field.getLength() <= 0 ? 50 : field.getLength()).append(")");
//					break;
//				case MetadataContents.FIELD_TYPE_BOOLEAN:
//					sb.append("char(1)");
//					break;
//				case MetadataContents.FIELD_TYPE_INTEGER:
//					sb.append("int");
//					break;
//				case MetadataContents.FIELD_TYPE_DECIMAL:
//					sb.append("decimal(").append(field.getPrecision() <= 0 ? 15 : field.getPrecision()).append(",").append(field.getScale() <=0 ? 0 : field.getScale()).append(")");
//					break;
//				case MetadataContents.FIELD_TYPE_DATE:
//					sb.append("char(10)");
//					break;
//				case MetadataContents.FIELD_TYPE_DATETIME:
//					sb.append("char(19)");
//					break;
//				case MetadataContents.FIELD_TYPE_CLOB:
//					sb.append("text");
//					break;
//				default:
//					sb.append("varchar(50)");
//					break;
//				}
				if(type != MetadataContents.FIELD_TYPE_BOOLEAN && type != MetadataContents.FIELD_TYPE_DATE && type != MetadataContents.FIELD_TYPE_DATETIME) {
					if(field.getLength() != null && field.getLength() > 0) pt.setLength(field.getLength());
					if(field.getPrecision() != null && field.getPrecision() > 0) pt.setLength(field.getPrecision());
					if(field.getScale() != null && field.getScale() > 0) pt.setScale(field.getScale());
				}
				sb.append(pt.toString());
				if(field.getEmpty() == null || !field.getEmpty().booleanValue()){
					sb.append(" not null");
				}
			}
			sb.append(",").append(PersistSupport.NEXT_LINE);
		}
		
		ShareworxAssert.assertNotNull(primaryList, "主键");
		sb.append("  primary key (");
		for(int i=0;i<primaryList.size();i++){
			sb.append(primaryList.get(i).getColumn());
			if(i != primaryList.size() - 1){
				sb.append(",");
			}
		}
		sb.append(")").append(PersistSupport.NEXT_LINE);
		sb.append(")").append(PersistSupport.NEXT_LINE);
		getWriteTemplate().update(sb.toString());
		return true;
	}

	@Override
	public boolean dropMetaClass(final MetaClass metaClass) throws MetadataRuntimeException {
//		if(StringUtils.isEmpty(metaClass.getTable())){
//			String id = metaClass.getId();
//			if(StringUtils.isEmpty(id)){
//				throw new ShareworxServiceException("找不到待删除类");
//			}
//			metaClass = metadataPersistService.findClassById(id);
//		}
//		if(metaClass == null){
//			return true;
//		}
//		if(metaClass.getType() != MetadataContents.META_TYPE_ENTITY){
//			return true;
//		}
//		if(!hasPublished(metaClass.getId())){
//			return true;
//		}
		return getWriteTemplate().execute(new ConnectionCallback<Boolean>() {

			@Override
			public Boolean doInConnection(Connection conn) throws SQLException, DataAccessException {
				DatabaseMetaData dbmd = conn.getMetaData();
				ResultSet rs = dbmd.getTables(null, null, metaClass.getTable().toUpperCase(), new String[]{"TABLE"});
				if(!rs.next()){
					return true;
				}
				String sql = "drop table " + metaClass.getTable();
				getWriteTemplate().update(sql);
				return true;
			}
		});
		
	}

	@Override
	public boolean addColumn(MetaClass metaClass, MetaField... fields) throws MetadataRuntimeException {
		ShareworxAssert.assertNotNull(metaClass.getTable(), "表名");
		ShareworxAssert.assertNotNull(fields, "待增加字段");
		List<String> sqlList = new ArrayList<String>(fields.length);
		String dbType = PersistSupport.getInstance().getDatabaseType(dataSource);
		PersistDialect dialect = PersistSupport.getInstance().getDialect(dbType);
		
		for(MetaField field: fields){
			ShareworxAssert.assertNotNull(field.getColumn(), "字段");
			String[] sqls = dialect.alterAddSQL(metaClass, field);
			sqlList.addAll(Arrays.asList(sqls));
//			StringBuffer sb = new StringBuffer("alter table ");
//			sb.append(metaClass.getTable());
//			sb.append(" add ");
//			sb.append(field.getColumn());
//			sb.append(" ");
//			int type = field.getType();
//			switch (type) {
//			case MetadataContents.FIELD_TYPE_CHARACTER:
//			case MetadataContents.FIELD_TYPE_STRING:
//			case MetadataContents.FIELD_TYPE_BLOB:
//				sb.append("varchar(").append(field.getLength() <= 0 ? 50 : field.getLength()).append(")");
//				break;
//			case MetadataContents.FIELD_TYPE_BOOLEAN:
//				sb.append("char(1)");
//				break;
//			case MetadataContents.FIELD_TYPE_INTEGER:
//				sb.append("int");
//				break;
//			case MetadataContents.FIELD_TYPE_DECIMAL:
//				sb.append("decimal(").append(field.getPrecision() <= 0 ? 15 : field.getPrecision()).append(",").append(field.getScale() <=0 ? 0 : field.getScale()).append(")");
//				break;
//			case MetadataContents.FIELD_TYPE_DATE:
//				sb.append("char(10)");
//				break;
//			case MetadataContents.FIELD_TYPE_DATETIME:
//				sb.append("char(19)");
//				break;
//			case MetadataContents.FIELD_TYPE_CLOB:
//				sb.append("text");
//				break;
//			default:
//				sb.append("varchar(50)");
//				break;
//			}
//			if(field.getEmpty() == null || !field.getEmpty().booleanValue()){
//				sb.append(" not null");
//			}
//			sqlList.add(sb.toString());
		}
		getWriteTemplate().batchUpdate(sqlList.toArray(new String[0]));
		return true;
	}

	@Override
	public boolean deleteColumn(MetaClass metaClass, MetaField... fields) throws MetadataRuntimeException {
		if(metaClass.getType() != null && metaClass.getType() != MetadataContents.META_TYPE_ENTITY){
			log.warn("Unsupport metadata type for persist: " + metaClass.getName());
			return false;
		}
		ShareworxAssert.assertNotNull(metaClass.getTable(), "表名");
		ShareworxAssert.assertNotNull(fields, "待删除字段");
		
		List<String> sqlList = new ArrayList<String>(fields.length);
		for(MetaField field: fields){
			ShareworxAssert.assertNotNull(field.getColumn(), "字段");
//			StringBuffer sb = new StringBuffer("alter table " + metaClass.getTable() + " drop column " + field.getColumn());
			String[] sqls = PersistSupport.getInstance().getDialect(getDbType()).alterRemoveSQL(metaClass, field);
			sqlList.addAll(Arrays.asList(sqls));
		}
		getWriteTemplate().batchUpdate(sqlList.toArray(new String[0]));
		return true;
	}

	@Override
	public boolean updateColumn(MetaClass metaClass, Map<String, MetaField> fieldMap) throws MetadataRuntimeException {
		if(metaClass.getType() != null && metaClass.getType() != MetadataContents.META_TYPE_ENTITY){
			log.warn("Unsupport metadata type for persist: " + metaClass.getName());
			return false;
		}
		ShareworxAssert.assertNotNull(metaClass.getTable(), "表名");
		ShareworxAssert.assertNotNull(fieldMap, "待增加字段");
		List<String> sqlList = new ArrayList<String>(fieldMap.size());
		for(Entry<String, MetaField> entry: fieldMap.entrySet()){
			String oldColumnName = entry.getKey();
if("content_".equals(oldColumnName)) {
	System.out.println();
}
			MetaField field = entry.getValue();
			ShareworxAssert.assertNotNull(field.getColumn(), "字段");
			String[] sqls = PersistSupport.getInstance().getDialect(getDbType()).alterModifySQL(metaClass, oldColumnName, field);
			sqlList.addAll(Arrays.asList(sqls));
//			StringBuffer sb = new StringBuffer("alter table ");
//			sb.append(metaClass.getTable());
//			if(!oldColumnName.equals(field.getColumn())){
//				sb.append(" change ");
//				sb.append(oldColumnName).append(" ");
//			}else{
//				sb.append(" modify column ");
//			}
//			sb.append(field.getColumn());
//			sb.append(" ");
//			Integer type = field.getType();
//			if(type == null){
//				continue;
//			}
//			if(StringUtils.isEmpty(field.getDefine())){
//				switch (type) {
//				case MetadataContents.FIELD_TYPE_CHARACTER:
//				case MetadataContents.FIELD_TYPE_STRING:
//				case MetadataContents.FIELD_TYPE_BLOB:
//					sb.append("varchar(").append(field.getLength() <= 0 ? 50 : field.getLength()).append(")");
//					break;
//				case MetadataContents.FIELD_TYPE_BOOLEAN:
//					sb.append("char(1)");
//					break;
//				case MetadataContents.FIELD_TYPE_INTEGER:
//					sb.append("int");
//					break;
//				case MetadataContents.FIELD_TYPE_DECIMAL:
//					sb.append("decimal(").append(field.getPrecision() <= 0 ? 15 : field.getPrecision()).append(",").append(field.getScale() <=0 ? 0 : field.getScale()).append(")");
//					break;
//				case MetadataContents.FIELD_TYPE_DATE:
//					sb.append("char(10)");
//					break;
//				case MetadataContents.FIELD_TYPE_DATETIME:
//					sb.append("char(19)");
//					break;
//				case MetadataContents.FIELD_TYPE_CLOB:
//					sb.append("clob");
//					break;
//				default:
//					sb.append("varchar(50)");
//					break;
//				}
//			}else{
//				String define = field.getDefine().toLowerCase();
//				int length = (field.getLength() == null || field.getLength() <= 0) ? 0 : field.getLength(); 
//				sb.append(field.getDefine());
//				if(length > 0 || type == MetadataContents.FIELD_TYPE_DECIMAL){
//					if(define.indexOf("(") == -1 && define.indexOf(")") == -1){
//						if(type == MetadataContents.FIELD_TYPE_DECIMAL){
//							int precision = field.getPrecision();
//							if(precision <= 0) precision = length;
//							
//							int scale = field.getScale();
//							if(scale <= 0) scale = 2;
//							sb.append("(").append(precision).append(",").append(scale).append(")");
//						}else if(type != MetadataContents.FIELD_TYPE_CLOB){
//							sb.append("(").append(length).append(")");
//						}
//					}
//				}
//			}
//			
//			if(field.getEmpty() == null || !field.getEmpty().booleanValue()){
//				sb.append(" not null");
//			}
//			log.info("SQL: " + sb.toString());
//			sqlList.add(sb.toString());
		}
		if(!ArrayUtils.isEmpty(sqlList)){
			getWriteTemplate().batchUpdate(sqlList.toArray(new String[0]));
		}
		return true;
	}
	
	@Override
	public void createTableStructByMetaClass(final MetaClass metaClass) throws MetadataRuntimeException {
		if(metaClass.getType() != null && metaClass.getType() != MetadataContents.META_TYPE_ENTITY){
			log.warn("Unsupport metadata type for persist: " + metaClass.getName());
			return;
		}
		getWriteTemplate().execute(new ConnectionCallback<Boolean>() {

			@Override
			public Boolean doInConnection(Connection conn) throws SQLException, DataAccessException {
				dropMetaClass(metaClass);
				createMetaClass(metaClass);
				return Boolean.TRUE;
			}
		});
	}
	
	@Override
	public void updateTableStructByMetaClass(final MetaClass metaClass) throws MetadataRuntimeException {
		if(metaClass.getType() != null && metaClass.getType() != MetadataContents.META_TYPE_ENTITY){
			log.warn("Unsupport metadata type for persist: " + metaClass.getName());
			return;
		}
		
		getWriteTemplate().execute(new ConnectionCallback<Boolean>() {

			@Override
			public Boolean doInConnection(Connection conn) throws SQLException, DataAccessException {
				DatabaseMetaData dbmd = conn.getMetaData();
				try(ResultSet tableRs = dbmd.getTables(null, null, metaClass.getTable().toUpperCase(), new String[]{"TABLE"});
						ResultSet columnRs = dbmd.getColumns(null, null, metaClass.getTable().toUpperCase(), null);){
					//ResultSet keyRs = dbmd.getPrimaryKeys(null, null, metaClass.getTable());
					if(tableRs.next()){
						if(!(getDbType().equalsIgnoreCase("mysql") || getDbType().equalsIgnoreCase("oracle"))) {
							log.warn("The function of update table struct is support MySQL,oracle only now!");
							return true;
						}
						//获取字段
						Map<String, MetaField> fieldMap = new HashMap<String, MetaField>();
						while(columnRs.next()){
							MetaField field = new MetaField();
							String name = columnRs.getString("COLUMN_NAME");
							int length = columnRs.getInt("COLUMN_SIZE");
							int scale = columnRs.getInt("DECIMAL_DIGITS");
							String type = columnRs.getString("TYPE_NAME");
							field.setDefine(type);
							field.setName(name);
							field.setColumn(name);
							field.setLength(length);
							field.setPrecision(length);
							if("MEDIUMTEXT".equals(type)) {
								field.setLength(0);
								field.setPrecision(0);
							}
							field.setScale(scale);
							fieldMap.put(name, field);
						}
						doWithField(metaClass, fieldMap);
					}else{
						createMetaClass(metaClass);
					}
				}
				return Boolean.TRUE;
			}

			private void doWithField(final MetaClass metaClass, Map<String, MetaField> map) {
				List<MetaField> list = metaClass.getFields();
				for(int i=0;i<list.size();i++){
					MetaField field = list.get(i);
					if(field.getPersist() == null || !field.getPersist()){
						continue;
					}
					if(!map.containsKey(field.getColumn()) && !map.containsKey(field.getColumn().toUpperCase())){
						log.info(String.format("adding column: %s - %s", metaClass.getId(), field.getId()));
						//增加字段
						addColumn(metaClass, field);
						continue;
					}
					//修改字段
					MetaField existField = map.remove(field.getColumn());
					if(existField == null) {
						existField = map.remove(field.getColumn().toUpperCase());
					}
					if(StringUtils.isEmpty(field.getDefine())){
						String type = TypeMapping.getDatabaseType(field.getType());
						int length = field.getLength();
						int precision = field.getPrecision();
						int scale = field.getScale();
						if(field.getType().equals(MetadataContents.FIELD_TYPE_DECIMAL)){
							if(type.equalsIgnoreCase(existField.getDefine()) 
									&& precision == existField.getPrecision() 
									&& scale == existField.getScale()){
								continue;
							}
						}else{
							if(type.equalsIgnoreCase(existField.getDefine())){
								if(type.toLowerCase().indexOf("int") != -1){
									continue;
								}else if(length == existField.getLength()
									&& scale == existField.getScale()){
									continue;
								}
							}
						}
						Map<String, MetaField> updateMap = new HashMap<String, MetaField>();
						updateMap.put(field.getColumn(), existField);
						log.info(String.format("updating column: %s - %s", metaClass.getId(), field.getId()));
						updateColumn(metaClass, updateMap);
						continue;
					}else{
						String type = field.getDefine();
						int length = field.getLength();
						int scale = field.getScale();
						if(field.getDefine().indexOf("(") != -1 && field.getDefine().indexOf(")") != -1){
							type = field.getDefine().substring(0, field.getDefine().indexOf("("));
							String lengthStr = field.getDefine().substring(field.getDefine().indexOf("(") + 1, field.getDefine().indexOf(")"));
							String[] lengthArray = lengthStr.split(",");
							length = Integer.valueOf(lengthArray[0].trim());
							scale = 0;
							if(lengthArray.length > 1){
								scale = Integer.valueOf(lengthArray[1].trim());
							}
						}
						if(type.equalsIgnoreCase(existField.getDefine())){
							if(type.toLowerCase().indexOf("int") != -1){
								continue;
							}else if(type.toLowerCase().indexOf("decimal") != -1
									&&(length == existField.getLength() || field.getPrecision() == existField.getPrecision())
									&& scale == existField.getScale()){
								continue;
							}else if(length == existField.getLength()
								&& scale == existField.getScale()){
								continue;
							}
						}
						Map<String, MetaField> updateMap = new HashMap<String, MetaField>();
						updateMap.put(field.getColumn(), field);
						log.info(String.format("updating column: %s - %s", metaClass.getId(), field.getId()));
						updateColumn(metaClass, updateMap);
						continue;
					}
				}
				if(!map.isEmpty()){
					//删除多余字段
					log.info(String.format("deleting column: %s - %s", metaClass.getId(), map.values()));
					deleteColumn(metaClass, map.values().toArray(new MetaField[0]));
				}
			}
		});
	}

	@Override
	public boolean hasPublished(String classId) throws MetadataRuntimeException {
		MetaClass metaClass = metadataPersistService.findClassById(classId);
		if(metaClass == null){
			return false;
		}
		if(metaClass.getType() != MetadataContents.META_TYPE_ENTITY){
			return false;
		}
		final String tableName = metaClass.getTable();
		return getWriteTemplate().execute(new ConnectionCallback<Boolean>() {

			@Override
			public Boolean doInConnection(Connection conn) throws SQLException, DataAccessException {
				DatabaseMetaData dbmd = conn.getMetaData();
				try(ResultSet rs = dbmd.getTables(null, null, tableName, new String[]{"TABLE"});){
					if(rs.next()){
						return true;
					}
				}
				return false;
			}
		});
	}
}
