package com.shareworx.ezfm.energyloss.data.service;

import java.math.BigInteger;
import java.util.*;

import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesBusinessService;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.energyloss.data.vo.EnergyVo;
import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel;
import com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.QueryOrder;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.DateTimeUtil;

/**
 * 水表电表抄表业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(YjwyEnergyDataBusinessService.ID)
public class YjwyEnergyDataBusinessServiceImpl implements YjwyEnergyDataBusinessService {


	@Autowired
	@Qualifier(YJWYDeviceService.ID)
	private YJWYDeviceService deviceService;

	@Autowired
	@Qualifier(YjwyEnergyDataDomainService.ID)
	private YjwyEnergyDataDomainService domainService;

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	@Autowired
    @Qualifier(YJWYEnergyElectricDomainService.ID)
	YJWYEnergyElectricDomainService energyElectricDomainService;




	public void setDomainService(YjwyEnergyDataDomainService domainService) {
		this.domainService = domainService;
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public YjwyEnergyDataModel[] query(Query query) throws ShareworxServiceException {
		List<YjwyEnergyDataModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new YjwyEnergyDataModel[0];
		}
		return list.toArray(new YjwyEnergyDataModel[0]);
	}

	@Override
	public List<Map<String, Object>> queryMonthData(EnergyVo vo) {
		String sql = getSql(vo, false);
		return dao.getReadTemplate().queryForList(sql);
//		dao.getReadTemplate().queryForObject(sql,Long.class);
	}

	@Override
	public List<Map<String, Object>> queryMonthDataEach(EnergyVo vo) {
		String sql = getSqlEach(vo, false);
		return dao.getReadTemplate().queryForList(sql);

	}

	@Override
	public Long countMonthData(EnergyVo vo) {
		String sql = getSql(vo, true);
		return dao.getReadTemplate().queryForObject(sql,Long.class);
	}

	@Override
	public Long countMonthDataEach(EnergyVo vo) {
		String sql = getSqlEach(vo, true);
		return dao.getReadTemplate().queryForObject(sql,Long.class);
	}

    /**
     *
     * @param pk_project
     * @param type
     * @return
     */
    @Override
    public List<Map<String, Object>> queryMonthDetails(String pk_project, String type,String year) {

        YJWYEnergyElectricModel model = new YJWYEnergyElectricModel();
        model.setPk_project(pk_project);
        model.setHydropower(type);
        //默认parent_id == null
        //逻辑删除
        model.setDelete_flag(0);
        List<YJWYEnergyElectricModel> yjwyEnergyElectricModels = energyElectricDomainService.queryByExample(model);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> dataMap = new HashMap<>();
        for(YJWYEnergyElectricModel item:yjwyEnergyElectricModels){
            List<Map<String, Object>> monthList = queryMonthDetailsEach(item.getEq_id(), year);
            if(monthList.size() == 0){
                continue;
            }
            for(Map<String,Object> mon:monthList){
                if(!dataMap.containsKey(mon.get("mon").toString())){
                    dataMap.put(mon.get("mon").toString(),0);
                }
                Integer now = (Integer) dataMap.get(mon.get("mon").toString());
                BigInteger data = (BigInteger) mon.get("now");
                dataMap.put(mon.get("mon").toString(),now+data.intValue());
            }
        }

        Set<String> strings = dataMap.keySet();
        for(String key:strings){
            Map<String,Object> map = new HashMap<>();
            map.put("mon",key);
            map.put("now",dataMap.get(key));
            list.add(map);
        }

        return list;


    }

	@Override
	public List<Map<String, Object>> queryMonthDetailsEach(String eq_id, String year) {
    	String sql = "select DISTINCT Month(create_time) as mon ,MAX(cast(data as UNSIGNED INTEGER)) as max_data from yjwy_energy_data " +
				"where " +
				" yjwy_energy_data.eq_id = '"+eq_id+"'" +
				" AND YEAR (create_time) = '"+year+"'" +
				" and yjwy_energy_data.create_time  " +
				"in ( " +
				" select MAX(create_time) " +
				" FROM " +
				" yjwy_energy_data " +
				" WHERE yjwy_energy_data.eq_id = '"+eq_id+"' " +
				" AND YEAR (create_time) = '"+year+"' " +
				" GROUP BY " +
				" YEAR (create_time), " +
				" MONTH (create_time) " +
				") " +
				"GROUP BY create_time  " +
				"ORDER BY create_time";

        List<Map<String, Object>> list = dao.getReadTemplate().queryForList(sql);
        BigInteger last = new BigInteger("0");
        for(Map<String,Object> item:list){

            //读取抄表的值
            BigInteger value;
            if(item.get("max_data") == null){
                value = new BigInteger("0");
            }else{
                value = new  BigInteger(item.get("max_data")+"");
            }
            //获取上个月的值
            BigInteger now = value.subtract(last);
            //上个月的值
            last = value;
			YJWYEnergyElectricModel model = energyElectricDomainService.queryById(eq_id);
			now  = now.multiply(new BigInteger(model.getRate()+""));
			item.put("now",now);
        }


		return list;

	}


	/**
	 * 查项目
	 * @return
	 */
	private String getSql(EnergyVo vo,boolean count){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT yjwy_energy_electric.pk_project,MAX(yjwy_pub_area.area_name_) as area_name_,MAX(yjwy_pub_project.project_name_) as project_name_,SUM(yjwy_energy_electric.notice_num) as notice_num ");
		sb.append(" FROM yjwy_energy_electric LEFT JOIN yjwy_pub_area ON yjwy_pub_area.pk_area_ = yjwy_energy_electric.pk_area  LEFT JOIN yjwy_pub_project ON yjwy_pub_project.pk_project_ = yjwy_energy_electric.pk_project ");
		sb.append(" where parent_id is null ");
		if(vo.getPk_area() != null && !"default".equals(vo.getPk_area())){
			sb.append("and yjwy_energy_electric.pk_area = '"+vo.getPk_area()+"'");
		}
		if(vo.getPk_project() != null && !"default".equals(vo.getPk_project())){
			sb.append("and yjwy_energy_electric.pk_project ='"+vo.getPk_project()+"'");
		}

		sb.append("and yjwy_energy_electric.pk_crop = '"+ UserUtil.getCurrentUser().getPk_crop()+"'");
		sb.append("and yjwy_energy_electric.hydropower = '"+vo.getType()+"'");
		sb.append("and yjwy_energy_electric.delete_flag <> '1'");
		//默认查询当前人所属项目所有数据
		Set<String> projectIds = UserUtil.getUserProjectIds();
		if (projectIds != null && projectIds.size() > 0) {
			sb.append(" AND "+ DeviceUtil.getInNotInSql("yjwy_energy_electric.pk_project", QueryContents.TYPE_IN, projectIds.toArray(new String[] {})));
		} else {
//			whrSql += " and tab1.fk_project_id = null ";
			sb.append("AND 1=2");
		}

		sb.append(" GROUP BY yjwy_energy_electric.pk_project ");
		sb.append(" order by yjwy_energy_electric.pk_project");
		if(!count){
			sb.append(" LIMIT " + (vo.getPage() - 1) * vo.getRows() + "," + vo.getRows());
		}

		if(count){

		    sb.insert(0,"select count(*) from (");
            sb.append(") as t");

        }


		return  sb.toString();
	}

	private String getSqlEach(EnergyVo vo,boolean count){
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT yjwy_energy_electric.pk_resource , yjwy_energy_electric.hydropower,yjwy_energy_electric.eq_id,yjwy_energy_electric.pk_project,yjwy_pub_area.area_name_,yjwy_pub_project.project_name_,yjwy_energy_electric.notice_num,yjwy_energy_electric.eq_code,yjwy_energy_electric.eq_name,parent_id as is_parent ");
		sb.append(" FROM yjwy_energy_electric");
		sb.append(" LEFT JOIN yjwy_pub_area ON yjwy_pub_area.pk_area_ = yjwy_energy_electric.pk_area");
		sb.append(" LEFT JOIN yjwy_pub_project ON yjwy_pub_project.pk_project_ = yjwy_energy_electric.pk_project");
		sb.append(" WHERE yjwy_energy_electric.pk_crop = '"+ UserUtil.getCurrentUser().getPk_crop()+"'");
		if(vo.getPk_area() != null && !"default".equals(vo.getPk_area())){
			sb.append(" and yjwy_energy_electric.pk_area = '"+vo.getPk_area()+"'");
		}
		if(vo.getPk_project() != null && !"default".equals(vo.getPk_project())){
			sb.append(" and yjwy_energy_electric.pk_project ='"+vo.getPk_project()+"'");
		}
		sb.append(" and yjwy_energy_electric.hydropower = '"+vo.getType()+"'");
		sb.append(" and yjwy_energy_electric.delete_flag <> 1 ");
		//默认查询当前人所属项目所有数据
		Set<String> projectIds = UserUtil.getUserProjectIds();
		if (projectIds != null && projectIds.size() > 0) {
			sb.append(" AND "+ DeviceUtil.getInNotInSql("yjwy_energy_electric.pk_project", QueryContents.TYPE_IN, projectIds.toArray(new String[] {})));
		} else {
//			whrSql += " and tab1.fk_project_id = null ";
			sb.append("AND 1=2");
		}

		sb.append(" order by yjwy_energy_electric.pk_project");
		if(!count){
			sb.append(" LIMIT " + (vo.getPage() - 1) * vo.getRows() + "," + vo.getRows());
		}


		if(count){

			sb.insert(0,"select count(*) from (");
			sb.append(") as t");

		}
		return sb.toString();
	}


	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataBusinessService#load(com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel)
	 */
	@Override
	public YjwyEnergyDataModel[] load(YjwyEnergyDataModel model) throws ShareworxServiceException {
		List<YjwyEnergyDataModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new YjwyEnergyDataModel[0];
		}
		return list.toArray(new YjwyEnergyDataModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataBusinessService#save(com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel[])
	 */
	@Override
	public YjwyEnergyDataModel[] save(YjwyEnergyDataModel[] models) throws ShareworxServiceException {
		List<YjwyEnergyDataModel> list = domainService.save(models);
		return list.toArray(new YjwyEnergyDataModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataBusinessService#update(com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel[])
	 */
	@Override
	public YjwyEnergyDataModel[] update(YjwyEnergyDataModel[] models) throws ShareworxServiceException {
		List<YjwyEnergyDataModel> list = domainService.update(models);
		return list.toArray(new YjwyEnergyDataModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataBusinessService#delete(com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel[])
	 */
	@Override
	public YjwyEnergyDataModel[] delete(YjwyEnergyDataModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}

    /**
     *
     * @param eq_id  水表、电表
     * @param data   数据
     */
    @Override
    public void updateLastEneryData(String eq_id, String data) {

        Query query = Query.from(YjwyEnergyDataModel.META_ID);
        query.addOrder(QueryOrder.desc("create_time"));

        query.and(Condition.create("eq_id",eq_id));
        query.limit(1);
        YjwyEnergyDataModel yjwyEnergyDataModel =  domainService.queryOneByCondition(query);
        if(yjwyEnergyDataModel == null){
        	yjwyEnergyDataModel = new YjwyEnergyDataModel();
            YJWYEnergyElectricModel model = energyElectricDomainService.queryById(eq_id);
        	yjwyEnergyDataModel.setCreate_user(UserUtil.getCurrentPk());
        	yjwyEnergyDataModel.setData("0");
            yjwyEnergyDataModel.setPk_project(model.getPk_project());
            yjwyEnergyDataModel.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
            yjwyEnergyDataModel.setEq_id(eq_id);
            yjwyEnergyDataModel.setCreate_time(DateTimeUtil.getDate(new Date(),DateTimeUtil.SHORTFORMAT));
            List<YjwyEnergyDataModel> save = domainService.save(yjwyEnergyDataModel);
            yjwyEnergyDataModel = save.get(0);

        }
        yjwyEnergyDataModel.setData(data+"");
        yjwyEnergyDataModel.setCreate_time(DateTimeUtil.getSysdateTime());
        domainService.update(yjwyEnergyDataModel);
    }

}
