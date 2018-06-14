package com.shareworx.ezfm.device.fmdata.service;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.shareworx.ezfm.device.fmdata_eq.model.YJWYEqSysModel;
import com.shareworx.ezfm.util.StringUtil;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.shareworx.platform.business.BusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.ezfm.device.fmdata.dao.YJWYFmDataDao;
import com.shareworx.ezfm.device.fmdata.model.YJWYCsiModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYPmpModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYPmpsModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYSiteModel;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.system.crop.model.CropModel;
import com.shareworx.ezfm.system.crop.service.ICropService;
import com.shareworx.ezfm.webservice.CxfClientUtil;
import com.shareworx.ezfm.webservice.fm.out.IBasisDataService;

/**
 * FM数据同步业务操作实现
 * 
 * @author jin.li
 *
 */
@Service(YJWYFmDataService.ID)
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class YJWYFmDataServiceImpl implements YJWYFmDataService {

	@Autowired
	@Qualifier(YJWYFmDataDao.ID)
	private YJWYFmDataDao yjwyFmDataDao;

	public void setYjwyFmDataDao(YJWYFmDataDao yjwyFmDataDao) {
		this.yjwyFmDataDao = yjwyFmDataDao;
	}

	@Autowired
	@Qualifier(YJWYEqBusinessService.ID)
	private YJWYEqBusinessService eqService;

	@Autowired
	@Qualifier(YJWYEqDomainService.ID)
	YJWYEqDomainService yjwyEqDomainService;

	@Autowired
	@Qualifier(YJWYCsiBusinessService.ID)
	private YJWYCsiBusinessService csiService;

	@Autowired
	@Qualifier(YJWYRoomBusinessService.ID)
	private YJWYRoomBusinessService roomService;

	@Autowired
	@Qualifier(YJWYPmpBusinessService.ID)
	private YJWYPmpBusinessService pmpService;


	@Autowired
	@Qualifier(YJWYPmpsBusinessService.ID)
	private YJWYPmpsBusinessService pmpsService;



	@Autowired
	@Qualifier(YJWYSiteBusinessService.ID)
	private YJWYSiteBusinessService siteService;

	public void setSiteService(YJWYSiteBusinessService siteService) {
		this.siteService = siteService;
	}

	public void setEqService(YJWYEqBusinessService eqService) {
		this.eqService = eqService;
	}

	public void setCsiService(YJWYCsiBusinessService csiService) {
		this.csiService = csiService;
	}

	public void setRoomService(YJWYRoomBusinessService roomService) {
		this.roomService = roomService;
	}

	public void setPmpService(YJWYPmpBusinessService pmpService) {
		this.pmpService = pmpService;
	}

	public void setPmpsService(YJWYPmpsBusinessService pmpsService) {
		this.pmpsService = pmpsService;
	}

	@Autowired
	@Qualifier(ICropService.ID)
	private ICropService cropService;

	public void setCropService(ICropService cropService) {
		this.cropService = cropService;
	}

	/**
	 * 查询最大更新时间
	 */
	public String queryLastUpdateTime(String localTable) {
		if (localTable == null) {
			return null;
		}
		String sql = "select MAX(dms_update_time) from " + localTable;
		return yjwyFmDataDao.queryLastUpdateTime(sql);
	}

	/**
	 * 查询表内数据总数
	 */
	public Long queryCount(String localTable) {
		if (localTable == null) {
			return null;
		}
		String sql = "select count(*) from " + localTable;
		return yjwyFmDataDao.queryCount(sql);
	}

	/**
	 *
	 * @param list
	 * @return
	 */
	@Override
	public Long queryCountBySysId(List<YJWYEqSysModel> list) {
		Query query = new Query();
		query.addFrom(YJWYEqModel.META_ID);
		query.addSelect("count(*)");
		String[] data = new String[list.size()];
		StringBuilder sb = new StringBuilder();
		for(int i  = 0 ;i < list.size();i++){
			if(i == 0){
				sb.append("( fk_eq_sys_id = '");
			}else{
				sb.append(" or fk_eq_sys_id = '");
			}

			sb.append(list.get(i).getEq_sys_id());
			sb.append("'");
		}
		sb.append(" ) and flag = 1 ");
		String sql = query.toString()+" where "+sb.toString();

		return yjwyFmDataDao.queryCount(sql);
	}

	/**
	 * FM数据同步入口
	 * 
	 * @return
	 * @throws Exception
	 */
	public ModelAndResult synchro(String address, String crop_code) throws Exception {
		if (DeviceUtil.stringIsEmpty(address) || DeviceUtil.stringIsEmpty(crop_code)) {
			return new ModelAndResult(false, "FM系统同步地址或企业编码不能为空");
		}
		CropModel cropModel = cropService.queryForObject(crop_code);
		if (null == cropModel) {
			return new ModelAndResult(false, "企业编码不正确，请检查");
		}
		String pk_crop = cropModel.getPk_crop();
		System.out.println("【FM数据同步】-yjwy_fmdata_eq【开始】");
		this.synchroData(address, pk_crop, new YJWYEqModel(), eqService, "yjwy_fmdata_eq", null, "V_DMS_EQ", 1);
		System.out.println("【FM数据同步】-yjwy_fmdata_eq【结束】");
		System.out.println("【FM数据同步】-yjwy_fmdata_csi【开始】");
		this.synchroData(address, pk_crop, new YJWYCsiModel(), csiService, "yjwy_fmdata_csi", null, "V_DMS_CSI", 1);
		System.out.println("【FM数据同步】-yjwy_fmdata_csi【结束】");
		System.out.println("【FM数据同步】-yjwy_fmdata_room【开始】");
		this.synchroData(address, pk_crop, new YJWYRoomModel(), roomService, "yjwy_fmdata_room", null, "V_DMS_ROOM", 1);
		System.out.println("【FM数据同步】-yjwy_fmdata_room【结束】");
		System.out.println("【FM数据同步】-yjwy_fmdata_pmp【开始】");
		this.synchroData(address, pk_crop, new YJWYPmpModel(), pmpService, null, null, "pmp", 1);
		System.out.println("【FM数据同步】-yjwy_fmdata_pmp【结束】");
		System.out.println("【FM数据同步】-yjwy_fmdata_pmps【开始】");
		this.synchroData(address, pk_crop, new YJWYPmpsModel(), pmpsService, "yjwy_fmdata_pmps", null, "pmps", 1);
		System.out.println("【FM数据同步】-yjwy_fmdata_pmps【结束】");
		System.out.println("【FM数据同步】-yjwy_fmdata_site【开始】");
		this.synchroData(address, pk_crop, new YJWYSiteModel(), siteService, "yjwy_fmdata_site", null, "site", 1);
		System.out.println("【FM数据同步】-yjwy_fmdata_site【结束】");
		return new ModelAndResult();
	}

	/**
	 * 封装调用FM系统接口获取数据
	 * 
	 * @param tableName
	 * @param lastUpdateTime
	 * @param pageNum
	 * @return
	 */
	private String getFmData(String address, String tableName, String lastUpdateTime, Integer pageNum) {
		final JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setAddress(address);
		factory.setServiceClass(IBasisDataService.class);
		IBasisDataService hw = (IBasisDataService) factory.create();
		// 超时设置
		CxfClientUtil.configTimeout(hw);
		return hw.getBasisDateList(tableName, lastUpdateTime, pageNum);
	}

	/**
	 * 封装FM数据同步方法
	 * 
	 * @param object
	 * @param service
	 * @param tableName
	 * @param pageNum
	 * @throws Exception
	 */
	private void synchroData(String address, String pk_crop, Object object, BusinessService service, String localTable, String lastUpdateTime, String tableName, Integer pageNum) throws Exception {
		Class<?> clazz = object.getClass();
		if (localTable != null) {
			if (this.queryCount(localTable) == 0) {
				lastUpdateTime = "1980-01-01 00:00:00.000";
			}
			if (lastUpdateTime == null || lastUpdateTime == "") {
				lastUpdateTime = this.queryLastUpdateTime(localTable);
			}
		}
		// 获取数据集
		System.out.println("【FM数据同步】-开始读取表【" + tableName + "】数据");
		String fmData = this.getFmData(address, tableName, lastUpdateTime, pageNum);
		System.out.println("【FM数据同步】-表【" + tableName + "】数据读取完毕");
		// 判断是否有数据
		if (fmData != null && fmData != "") {
			// 字符串转集合
			JSONArray resultList = JSONArray.parseArray(fmData);
			// 声明数据对象model
			Object model = null;
			// 声明map对象，存放list遍历元素
			Map<String, Object> map = null;
			// 创建数据对象数组，用于一次性对数据库持久化操作
			List<Object> updateList = new ArrayList<>();
			List<Object> saveList = new ArrayList<>();
			// 获取实体类信息对象
			BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
			// 实体类属性集合
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			// map的键集合
			Set<String> set = null;
			// set方法
			Method setMethod = null;
			// 实体类属性所属类型class对象
			Class<?> paramTypeClazz = null;
			// map中的值
			Object value = null;
			// map值的数据类型
			String valueType = null;
			// 实体类属性数据类型
			String paramType = null;
			for (int i = 0; i < resultList.size(); i++) {
				// 获取单条记录map
				map = (Map<String, Object>) resultList.get(i);
				// 对象实例化
				model = clazz.newInstance();
				// 获取map的键集合
				set = map.keySet();
				// 循环map键并给model赋值
				for (String string : set) {
					for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
						// 获取和map键相同的属性名
						if (string.equals(propertyDescriptor.getName())) {
							// 获取set方法
							setMethod = propertyDescriptor.getWriteMethod();
							// 获取参数的class对象
							paramTypeClazz = propertyDescriptor.getPropertyType();
							// 获取map的value值
							value = map.get(string);
							if (value != null) {
								// 获取value类型
								valueType = value.getClass().getName();
							}
							// 实体类属性数据类型获取
							paramType = paramTypeClazz.getName();
							// 判断属性类型和value类型是否相同
							if (!paramType.equals(valueType) && value != null) {
								// 将value类型转为属性类型
								if ("java.lang.String".equals(paramType)) {
									value = value.toString();
								}
								if ("java.lang.Integer".equals(paramType)) {
									value = new Integer(value.toString());
								}
							}
							// 可直接赋值的参数
							setMethod.invoke(model, value);
						}
					}
				}
				// 获取主键的值
				Method getPrimaryKey = clazz.getMethod("getPrimaryKey");
				String primaryKey = (String) getPrimaryKey.invoke(model);
				Method getMethod = clazz.getMethod("get" + DeviceUtil.toUpperCaseFirstOne(primaryKey));
				Object id = getMethod.invoke(model);
				// 获取元数据名称
				Method getMetaName = clazz.getMethod("getMetaName");
				String metaName = (String) getMetaName.invoke(model);
				// 查询是否已有记录
				Query query = Query.from(metaName);
				query.where(new Condition(primaryKey, QueryContents.TYPE_EQ, id));
				Object[] objects = service.query(query);
				if (objects != null && objects.length > 0) {
					// 已存在，执行修改
					updateList.add(model);
				} else {
					// 不存在，执行保存
					saveList.add(model);
				}
			}
			this.persist(clazz, saveList, service, "save", pk_crop);
			this.persist(clazz, updateList, service, "update", pk_crop);
			pageNum++;
			this.synchroData(address, pk_crop, object, service, localTable, lastUpdateTime, tableName, pageNum);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = java.lang.Exception.class)
	private void persist(Class clazz, List<Object> list, BusinessService service, String flag, String pk_crop) {
		String clazzName = clazz.getName();
		int i = clazzName.lastIndexOf(".");
		int j = clazzName.length();
		String str = clazzName.substring(i + 1, j);
		System.out.println("【FM数据同步】-持久化" + str + "数据【开始】");
		int count = list.size();
		if (count == 0) {
			return;
		}
		if ("YJWYEqModel".equals(str)) {
			YJWYEqModel[] eqModels = new YJWYEqModel[count];
			for (int k = 0; k < count; k++) {
				eqModels[k] = (YJWYEqModel) list.get(k);
				eqModels[k].setPk_crop(pk_crop);
			}
			if ("save".equals(flag)) {
				service.save(eqModels);
			} else {
				service.update(eqModels);
			}
		} else if ("YJWYCsiModel".equals(str)) {
			YJWYCsiModel[] csiModels = new YJWYCsiModel[count];
			for (int k = 0; k < count; k++) {
				csiModels[k] = (YJWYCsiModel) list.get(k);
				csiModels[k].setPk_crop(pk_crop);
			}
			if ("save".equals(flag)) {
				service.save(csiModels);
			} else {
				service.update(csiModels);
			}
		} else if ("YJWYRoomModel".equals(str)) {
			YJWYRoomModel[] roomModels = new YJWYRoomModel[count];
			for (int k = 0; k < count; k++) {
				roomModels[k] = (YJWYRoomModel) list.get(k);
				roomModels[k].setPk_crop(pk_crop);
			}
			if ("save".equals(flag)) {
				service.save(roomModels);
			} else {
				service.update(roomModels);
			}
		} else if ("YJWYPmpModel".equals(str)) {
			YJWYPmpModel[] pmpModels = new YJWYPmpModel[count];
			for (int k = 0; k < count; k++) {
				pmpModels[k] = (YJWYPmpModel) list.get(k);
				pmpModels[k].setPk_crop(pk_crop);
			}
			if ("save".equals(flag)) {
				service.save(pmpModels);
			} else {
				service.update(pmpModels);
			}
		} else if ("YJWYPmpsModel".equals(str)) {
			YJWYPmpsModel[] pmpsModels = new YJWYPmpsModel[count];
			for (int k = 0; k < count; k++) {
				pmpsModels[k] = (YJWYPmpsModel) list.get(k);
				pmpsModels[k].setPk_crop(pk_crop);
			}
			if ("save".equals(flag)) {
				service.save(pmpsModels);
			} else {
				service.update(pmpsModels);
			}
		} else if ("YJWYSiteModel".equals(str)) {
			YJWYSiteModel[] siteModels = new YJWYSiteModel[count];
			for (int k = 0; k < count; k++) {
				siteModels[k] = (YJWYSiteModel) list.get(k);
				siteModels[k].setPk_crop(pk_crop);
			}
			if ("save".equals(flag)) {
				service.save(siteModels);
			} else {
				service.update(siteModels);
			}
		}
		System.out.println("【FM数据同步】-持久化" + str + "数据【完毕】");
	}

}
