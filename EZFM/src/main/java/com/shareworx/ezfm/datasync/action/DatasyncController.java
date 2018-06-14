package com.shareworx.ezfm.datasync.action;

import com.alibaba.fastjson.JSON;
import com.shareworx.admin.user.model.UserModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.project.service.YJWYProjectDomainService;
import com.shareworx.ezfm.baseinfo.resources.dao.AttrResouorceDao;
import com.shareworx.ezfm.baseinfo.resources.model.AttrResouorceModel;
import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.ezfm.baseinfo.resources.service.AttrResouorceBusinessService;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesBusinessService;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesDomainService;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserDomainService;
import com.shareworx.ezfm.datasync.vo.DeviceVo;
import com.shareworx.ezfm.datasync.vo.ProblemVo;
import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.ezfm.device.fmdata.model.YJWYRoomModel;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqBusinessService;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqService;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomDomainService;
import com.shareworx.ezfm.device.list.model.YJWYListModel;
import com.shareworx.ezfm.device.list.service.YJWYListService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.ObectUtils;
import com.shareworx.ezfm.problem.details.model.YJWYProblemDetailsModel;
import com.shareworx.ezfm.problem.details.service.YJWYProblemDetailsBusinessService;
import com.shareworx.ezfm.problem.details.vo.ProblemDetailsVo;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.system.dict.service.YJWYDictDomainService;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by zhi.zhang on 2017/7/11.
 * 数据同步
 */
@Controller
@RequestMapping("ezfm/api/datasync")
public class DatasyncController {

    @Autowired
    private Environment env;

    @Autowired
    @Qualifier(YJWYProblemDetailsBusinessService.ID)
    private YJWYProblemDetailsBusinessService detailsBusinessService;


    @Autowired
    @Qualifier(YJWYDictDomainService.ID)
    YJWYDictDomainService dictDomainService;

    @Autowired
    @Qualifier(AttrResouorceBusinessService.ID)
    private AttrResouorceBusinessService rbservice;

    @Autowired
    @Qualifier(ObjectPersistDao.ID)
    private ObjectPersistDao dao;

    @Autowired
    @Qualifier(YJWYResourcesBusinessService.ID)
    private YJWYResourcesBusinessService service;

    @Autowired
    @Qualifier(YJWYResourcesDomainService.ID)
    YJWYResourcesDomainService yjwyResourcesDomainService;

    @Autowired
    @Qualifier(YJWYUserDomainService.ID)
    YJWYUserDomainService userDomainService;

    @Autowired
    @Qualifier(YJWYProjectDomainService.ID)
    YJWYProjectDomainService projectDomainService;

    @Autowired
    @Qualifier(AttrResouorceDao.ID)
    private AttrResouorceDao attdao;

    @Autowired
    @Qualifier(YJWYRoomDomainService.ID)
    private YJWYRoomDomainService roomService;

    @Autowired
    @Qualifier(YJWYEqDomainService.ID)
    YJWYEqDomainService domainService;

    @Autowired
    @Qualifier(YJWYResourcesDomainService.ID)
    YJWYResourcesDomainService resourcesDomainService;

    @Autowired
    @Qualifier(YJWYEqService.ID)
    private YJWYEqService yjwyEqService;

    @Autowired
    @Qualifier(YJWYEqBusinessService.ID)
    private YJWYEqBusinessService yjwyeqBusinessService;


    @Autowired
    @Qualifier(YJWYListService.ID)
    private YJWYListService yjwyListService;

    @RequestMapping(value = "problem/add" ,method = RequestMethod.GET)
    @ResponseBody
    public ModelAndResult problemAdd(ProblemDetailsVo vo,String fk_repair_equipment){
        if(fk_repair_equipment!= null && !fk_repair_equipment.equals("")){
            vo.setWork_fk_repair_equipment(fk_repair_equipment);
        }
        YJWYProblemDetailsModel[] rs = detailsBusinessService.saveDetailsByEq(vo);
        if(rs.length >0){
            String problem_id = rs[0].getPk_details_id();
            ModelAndResult modelAndResult = new ModelAndResult();
            modelAndResult.put("problem_id",problem_id);
            return modelAndResult;
        }
        return new ModelAndResult(rs);
    }


    /**
     * 新增保存操作
     *
     * @return
     */
    @RequestMapping(value = "resource/add", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndResult save(ProblemVo vo) {


        YJWYResourcesModel model = new YJWYResourcesModel();

        model.setResourcetype_code(vo.getResources_type());
        model.setParent_id(vo.getParent_id());
        model.setResources_name(vo.getResources_name());
        model.setResources_code(vo.getResources_code());

        JdbcTemplate read = dao.getReadTemplate();
        String sql = "select count(pk_resources) from yjwy_pub_resources where resources_code='"
                + model.get("resources_code") + "'";
        int count = read.queryForObject(sql, Integer.class);
        String sql2="select count(pk_resources) from yjwy_pub_resources where  resources_name='"
                + model.getResources_name()
                + "'";
//		判断是否有父id
        if(model.getParent_id().equals("0")){
            sql2=sql2+" and project_id='"
                    + model.getProject_id()
                    + "'";
        }else{
            sql2=sql2+" and parent_id='"
                    + model.getParent_id()
                    + "'";
        }
        int count1=read.queryForObject(sql2, Integer.class);
        if (count > 0) {
            return new ModelAndResult(false, "此编码已存在，请查证后重试！");
        }else if(count1 > 0){
            return new ModelAndResult(false, "该资源下已存在此名称，请查证后重试！");
        }


        String userId = env.getProperty("dv.datasync.userid", "");
        YJWYUserModel yjwyUserModel = userDomainService.queryById(userId);

        if(yjwyUserModel == null){
            return new ModelAndResult(false, "没有配置用户，请查证后重试");
        }


        String project_id = env.getProperty("dv.datasync.projectid");
        YJWYProjectModel yjwyProjectModel = projectDomainService.queryById(project_id);
        if(yjwyProjectModel == null){
            return new ModelAndResult(false, "没有配置项目，请查证后重试");
        }
        model.setProject_id(project_id);
        YJWYResourcesModel[] rs = null;
        try {
            rs = service.saveByDv(new YJWYResourcesModel[] { model },yjwyUserModel.getPk_user(),yjwyUserModel.getPk_crop());
        }catch (Exception ex){
            return new ModelAndResult(false,ex.getMessage());
        }

        //处理并保存数据
        String sql1="select attribute_code from  yjwy_attribute_name";
        List<Map<String, Object>> list1 = read.queryForList(sql1);
        String str="";
        for (Map<String, Object> map : list1) {
            str=str+map.get("attribute_code")+",";
        }
        String[] con =str.split(",");


        List<String> list = Arrays.asList(con);
        for(String name:con){
            String value = (String) JSON.toJSONString(model.getAttribute(name)).replaceAll("\"", "");
            if(value != null && !value.equals("") && !value.equals("null")){
                AttrResouorceModel attrmodel = new AttrResouorceModel();
                attrmodel.setPk_resources(model.getPk_resources());
                attrmodel.setAttr_name(name);
                attrmodel.setAttr_value(value);
                rbservice.save(new AttrResouorceModel[]{attrmodel} );
            }
        }
        ModelAndResult mv = new ModelAndResult();
        mv.put("resources_id",rs[0].getPk_resources());
        return  mv;
    }


    @RequestMapping(value = "resource/update", method = RequestMethod.POST)
    @ResponseBody
    public  ModelAndResult update(ProblemVo vo) {

        YJWYResourcesModel model = new YJWYResourcesModel();
        model.setPk_resources(vo.getResources_id());
        model.setResourcetype_code(vo.getResources_type());
        model.setParent_id(vo.getParent_id());
        model.setResources_name(vo.getResources_name());
        model.setResources_code(vo.getResources_code());
        JdbcTemplate read = dao.getReadTemplate();
        if (model.get("resources_code") != null && model.get("pk_resources") != null
                && !StringUtils.isEmpty(model.get("resources_code").toString())
                && !StringUtils.isEmpty(model.get("pk_resources").toString())) {
            String sql = "select count(pk_resources) from yjwy_pub_resources where " + " resources_code='"
                    + model.get("resources_code").toString() + "'";
            int count = read.queryForObject(sql, Integer.class);
            // 修改判断Code是否重复，全库等于1
            if (count == 1) {
                sql = "select count(pk_resources) from yjwy_pub_resources where " + "pk_resources ='"
                        + model.get("pk_resources").toString() + "' " + "and resources_code='"
                        + model.get("resources_code").toString() + "'";
                count = read.queryForObject(sql, Integer.class);
                // 当全库等于1时，不是当前记录的code,提示编码重复
                if (count != 1) {
                    return new ModelAndResult(false, "此编码已存在，请查证后重试！");
                }

            }
            String sql3="select count(pk_resources) from yjwy_pub_resources where  resources_name='"
                    + model.getResources_name()
                    + "'";
//			判断是否有父id
            String a=model.get("parent_id").toString();
            if(model.getParent_id().equals(model.getPk_resources())){
                sql3=sql3+" and project_id='"
                        + model.getProject_id()
                        + "'";
            }else{
                sql3=sql3+" and parent_id='"
                        + model.getParent_id()
                        + "'";
            }
            int count3=read.queryForObject(sql3, Integer.class);
            if(count3==1){
                sql3="select count(pk_resources) from yjwy_pub_resources where  resources_name='"
                        + model.getResources_name()
                        + "' and pk_resources='"
                        + model.getPk_resources()
                        + "'";
//				判断是否有父id
                if(model.getParent_id().equals(model.getPk_resources())){
                    sql3=sql3+" and project_id='"
                            + model.getProject_id()
                            + "'";
                }else{
                    sql3=sql3+" and parent_id='"
                            + model.getParent_id()
                            + "'";
                }
                count3=read.queryForObject(sql3, Integer.class);
                if(count3 !=1){
                    return new ModelAndResult(false,"该资源下已存在此名称，请查证后重试！");
                }

            }
            // 全库大于1
            if (count > 1) {
                return new ModelAndResult(false, "此编码已存在，请查证后重试！");
            }
            if(count3 > 1){
                return new ModelAndResult(false,"该资源下已存在此名称，请查证后重试！");
            }
        } else {
            return new ModelAndResult(false, "程序错误，请刷新后重试！");
        }

        YJWYResourcesModel yjwyResourcesModel = yjwyResourcesDomainService.queryById(model.getPk_resources());
        ObectUtils.filter(model,"apartment_layout","create_time","create_user","project_id");
        ObectUtils.fillData(model,yjwyResourcesModel);
        YJWYResourcesModel[] rs = service.updateByDv(new YJWYResourcesModel[] { model });

        //删除
        String sql1="select count(1) from yjwy_attr_resource where pk_resources='"
                +model.getPk_resources()
                + "'";
        int count = read.queryForObject(sql1, Integer.class);
        if(count>0){
            String sql="delete from yjwy_attr_resource where pk_resources='"
                    +model.getPk_resources()
                    + "'";
            attdao.excuteUpdateSql(sql);

        }
//		添加
        String sql2="select attribute_code from  yjwy_attribute_name";
        List<Map<String, Object>> list1 = read.queryForList(sql2);
        String str="";
        for (Map<String, Object> map : list1) {
            str=str+map.get("attribute_code")+",";
        }
        String[] con =str.split(",");


        List<String> list = Arrays.asList(con);
        for(String name:con){
            String value = (String) JSON.toJSONString(model.getAttribute(name)).replaceAll("\"", "");
            if(value != null && !value.equals("") && !value.equals("null")){
                AttrResouorceModel attrmodel = new AttrResouorceModel();
                attrmodel.setPk_resources(model.getPk_resources());
                attrmodel.setAttr_name(name);
                attrmodel.setAttr_value(value);
                rbservice.save(new AttrResouorceModel[]{attrmodel} );
            }
        }
        ModelAndResult mv = new ModelAndResult();
        mv.put("resources_id",rs[0].getPk_resources());
        return mv;
    }



    /**
     * 删除操作
     *
     * @return
     */
    @RequestMapping(value = "resource/delete", method = RequestMethod.POST)
    public @ResponseBody ModelAndResult delete(ProblemVo vo) {
        YJWYResourcesModel[] models = new YJWYResourcesModel[1];
        models[0] = new YJWYResourcesModel();
        models[0].setPk_resources(vo.getResources_id());
        YJWYResourcesModel yjwyResourcesModel = yjwyResourcesDomainService.queryById(vo.getResources_id());
        if(yjwyResourcesModel == null){
            return new ModelAndResult(false,"资源不存在");
        }
        ObectUtils.fillData(models[0],yjwyResourcesModel);
        YJWYResourcesModel[] rs = service.delete(models);
        JdbcTemplate read = dao.getReadTemplate();
//		删除属性表中的数据
        String sql1="select count(1) from yjwy_attr_resource where pk_resources='"
                +models[0].getPk_resources()
                + "'";
        int count = read.queryForObject(sql1, Integer.class);
        if(count>0){
            String sql="delete from yjwy_attr_resource where pk_resources='"
                    +models[0].getPk_resources()
                    + "'";
            attdao.excuteUpdateSql(sql);

        }
        ModelAndResult mv = new ModelAndResult();
        mv.put("resources_id",vo.getResources_id());
        return mv;
    }



    @RequestMapping(value = "device/add", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndResult deviceAdd(DeviceVo vo){
        YJWYEqModel model = new YJWYEqModel();
        String userId = env.getProperty("dv.datasync.userid", "");
        YJWYUserModel yjwyUserModel = userDomainService.queryById(userId);

        if(yjwyUserModel == null){
            return new ModelAndResult(false, "没有配置用户，请查证后重试");
        }
        String project_id = env.getProperty("dv.datasync.projectid");
        YJWYProjectModel yjwyProjectModel = projectDomainService.queryById(project_id);
        if(yjwyProjectModel == null){
            return new ModelAndResult(false, "没有配置项目，请查证后重试");
        }
        model.setSite_id(project_id);
        model.setName(vo.getName());
        YJWYDictModel yjwyDictModel = dictDomainService.queryById(vo.getCsi_id());
        if(yjwyDictModel == null){
            return new ModelAndResult(false, "分类不存在");
        }
        model.setCsi_id(vo.getCsi_id());
        model.setUse_name(vo.getUse_name());
        model.setFm_code(vo.getFm_code());
        //资源ID
        YJWYResourcesModel yjwyResourcesModel = resourcesDomainService.queryById(vo.getRm_id());
        if(yjwyResourcesModel == null){
            return new ModelAndResult(false, "资源不存在");
        }
        model.setRm_id(vo.getRm_id());
        model.setService_dept(vo.getService_dept());
        model.setUse_dept(vo.getUse_dept());
        model.setEq_id(UUID.randomUUID().toString());
        model.setActive(1);
        model.setFlag(model.getActive());
        model.setDelete_flag(model.getDelete_flag());
        model = completeYJWYEqModel(model,yjwyUserModel.getPk_crop());
        model.setStatus("1");
        try {

                YJWYEqModel item = new YJWYEqModel();
                item.setFm_code(model.getFm_code());
                item.setPk_crop(model.getPk_crop());
                List<YJWYEqModel> yjwyEqModels1 = domainService.queryByExample(item);
                if(yjwyEqModels1.size() >0){
                    return new ModelAndResult(false,"编号重复，请查证后重试");
                }

            YJWYEqModel[] yjwyEqModels = yjwyEqService.saveModelsByDv(new YJWYEqModel[]{model});
            String id = yjwyEqModels.length>0?yjwyEqModels[0].getEq_id():"";
            ModelAndResult modelAndResult = new ModelAndResult();
            modelAndResult.put("device_id",id);
            return modelAndResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndResult(false,e.getMessage());
        }
    }
    /**
     * 完善设备的部分信息
     *
     * @param model
     * @return
     */
    public YJWYEqModel completeYJWYEqModel(YJWYEqModel model,String pk_crop) {
        model.setPk_crop(pk_crop);
        model.setList_code(model.getFm_code());
        model.setDms_update_time(DateTimeUtil.getTimestampStr());
        model.setLong_description(model.getRm_id()+"|" + model.getName());
        model.setUsual_name(model.getRm_id() + "=" + model.getUse_name());
        model.setRm_id(getRoom(
                null,
                model.getRm_id()+"", "",
                model.getSite_id(),pk_crop));
        return model;
    }

    /**
     *
     * @param id 房间ID
     * @param resource_id 资源ID
     * @param name
     * @param site_id
     * @param pk_crop
     * @return
     */
    public String getRoom(String id, String resource_id, String name, String site_id,String pk_crop){
        Query query = Query.from(YJWYRoomModel.META_ID);
        query.where(Condition.eq("site_id", site_id).and(new Condition("rm_id", QueryContents.TYPE_EQ,id).or(new Condition("fk_resource_id", QueryContents.TYPE_EQ,resource_id))));
        List<YJWYRoomModel> roomList = roomService.queryListByCondition(query);
        if(!roomList.isEmpty()){
            return roomList.get(0).getRm_id();
        }
        YJWYRoomModel model = new YJWYRoomModel();
        model.setRm_id(UUID.randomUUID().toString());
        model.setFm_code(UUID.randomUUID().toString());
        model.setName(name);
        model.setFk_resource_id(resource_id);
        model.setDms_update_time(DateTimeUtil.getTimestampStr());
        model.setPk_crop(pk_crop);
        model.setSite_id(site_id);
        model.setFlag(1);
        return roomService.save(model).get(0).getRm_id();
    }


    /**
     * 修改保存操作
     *
     * @return
     */
    @RequestMapping(value = "device/update", method = RequestMethod.POST)
    public @ResponseBody ModelAndResult update(DeviceVo vo) {
        YJWYEqModel model = new YJWYEqModel();
        model.setEq_id(vo.getDevice_id());
        String userId = env.getProperty("dv.datasync.userid", "");
        YJWYUserModel yjwyUserModel = userDomainService.queryById(userId);

        if (yjwyUserModel == null) {
            return new ModelAndResult(false, "没有配置用户，请查证后重试");
        }
        String project_id = env.getProperty("dv.datasync.projectid");
        YJWYProjectModel yjwyProjectModel = projectDomainService.queryById(project_id);
        if (yjwyProjectModel == null) {
            return new ModelAndResult(false, "没有配置项目，请查证后重试");
        }
        model.setSite_id(project_id);
        model.setName(vo.getName());
        YJWYDictModel yjwyDictModel = dictDomainService.queryById(vo.getCsi_id());
        if (yjwyDictModel == null) {
            return new ModelAndResult(false, "分类不存在");
        }
        model.setCsi_id(vo.getCsi_id());
        model.setUse_name(vo.getUse_name());
        model.setFm_code(vo.getFm_code());
        try {
            JdbcTemplate read = dao.getReadTemplate();
            String sql = "select count(eq_id) from yjwy_fmdata_eq where flag='1' and fm_code='"
                    + model.getFm_code()
                    + "' and pk_crop='"
                    + yjwyUserModel.getPk_crop()
                    + "'";
            int count = read.queryForObject(sql, Integer.class);
            if (count == 1) {
                sql = "select count(eq_id) from yjwy_fmdata_eq where flag='1' and fm_code='"
                        + model.getFm_code()
                        + "' and pk_crop='"
                        + yjwyUserModel.getPk_crop()
                        + "' and eq_id='"
                        + model.getEq_id()
                        + "'";
                count = read.queryForObject(sql, Integer.class);
                if (count != 1) {
                    return new ModelAndResult(false, "此编码已存在！");
                }
            }
            YJWYEqModel example = new YJWYEqModel();
            example.setEq_id(model.getEq_id());
            YJWYEqModel models = domainService.queryById(model.getEq_id());
            if (models == null ) {
                return new ModelAndResult(false,"设备不存在");
            }
            model = completeYJWYEqModel(model, yjwyUserModel.getPk_crop());
            YJWYEqModel[] yjwyEqModels = yjwyEqService.updateModelsByDv(new YJWYEqModel[]{model});
            String id = yjwyEqModels.length > 0 ? yjwyEqModels[0].getEq_id() : "";
            ModelAndResult modelAndResult = new ModelAndResult();
            modelAndResult.put("device_id", id);
            return modelAndResult;
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndResult(false, e.getMessage());
        }
    }


    /**
     * 删除操作
     *
     * @return
     */
    @RequestMapping(value = "device/delete", method = RequestMethod.GET)
    public @ResponseBody ModelAndResult delete(DeviceVo vo) {
        YJWYListModel[] models = new YJWYListModel[1];
        models[0] = new YJWYListModel();
        models[0].setEq_id(vo.getDevice_id());
        try {
            // 获取主键id
            for (int i = 0; i < models.length; i++) {
                // 执行删除
                YJWYEqModel item = domainService.queryById(models[i].getEq_id());
                if (item == null ) {
                    return new ModelAndResult(false,"设备不存在");
                }
                yjwyListService.deleteByCondition(models[i].getEq_id());
            }
            ModelAndResult modelAndResult = new ModelAndResult();
            modelAndResult.put("device_id",vo.getDevice_id());
            return modelAndResult;

        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndResult(false,e.getMessage());
        }
    }




}
