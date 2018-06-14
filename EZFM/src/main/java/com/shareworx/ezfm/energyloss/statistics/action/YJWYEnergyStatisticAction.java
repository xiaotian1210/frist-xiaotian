package com.shareworx.ezfm.energyloss.statistics.action;

import java.math.BigInteger;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import com.shareworx.ezfm.device.util.ObectUtils;
import com.shareworx.ezfm.energyloss.statistics.service.YJWYEnergyStatisticService;
import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel;
import com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ObjectUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.ezfm.device.fmdata.service.YJWYRoomBusinessService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.service.YJWYDeviceService;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.problem.statistics.service.YJWYProblemStatisticsService;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 能耗统计控制器
 *
 * @author xiaotian.luo
 */
@Controller
@RequestMapping("ezfm/energyloss/statistics")
public class YJWYEnergyStatisticAction {

    final static Logger log = Logger.getLogger(YJWYEnergyStatisticAction.class);

    /**
     * 跳转页面
     */
    public final static String PAGE_FORWARD = "ezfm/energyloss/energy_statistics";

    @Autowired
    @Qualifier(YJWYProblemStatisticsService.ID)
    private YJWYProblemStatisticsService problemStatisticsService;

    public void setProblemStatisticsService(YJWYProblemStatisticsService problemStatisticsService) {
        this.problemStatisticsService = problemStatisticsService;
    }
    @Autowired
    @Qualifier(YJWYEnergyStatisticService.ID)
    private YJWYEnergyStatisticService StatisticsService;
    @Autowired
   	@Qualifier(YJWYRoomBusinessService.ID)
   	private YJWYRoomBusinessService areaservice;
   	public void setAreaservice(YJWYRoomBusinessService areaservice) {
   		this.areaservice = areaservice;
   	}
    @Autowired
    @Qualifier(YJWYDeviceService.ID)
    private YJWYDeviceService deviceService;

    @Autowired
    @Qualifier(YJWYEnergyElectricDomainService.ID)
    YJWYEnergyElectricDomainService yjwyEnergyElectricDomainService;

    public void setDeviceService(YJWYDeviceService deviceService) {
        this.deviceService = deviceService;
    }

    /**
     * 转向列表页面
     *
     * @return
     */
    @RequestMapping(value = "index", method = RequestMethod.GET)
    public ModelAndView listForward() {
        return new ModelAndView(PAGE_FORWARD);
    }

    /**
     * 查询数据集合
     *
     * @return
     */
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndResult query(ParamEntity params) {
        // 获取参数
        Integer page = params.getPage();
        Integer rows = params.getRows();
        String pk_area = params.getPk_area();
        String pk_project = params.getPk_project();
        String time = params.getEnd_time();
        String task_type = params.getTask_type();
        StringBuilder projectSql = new StringBuilder("");
        StringBuilder areaSql = new StringBuilder("");
        StringBuilder timeSql = new StringBuilder("");
        StringBuilder typeSql = new StringBuilder("");
        if (!DeviceUtil.stringIsEmpty(pk_project) && !"default".equals(pk_project)) {
            projectSql.append(" where yjwy_pub_project.pk_project_ = '" + pk_project + "' ");
        } 
        else if (!DeviceUtil.stringIsEmpty(pk_area) && !"default".equals(pk_area)) {
            areaSql.append(" where yjwy_pub_area.pk_area_ = '" + pk_area + "' ");
        } else {
            Set<String> set = UserUtil.getUserProjectIds();
            if (!ArrayUtils.isEmpty(set)) {
                projectSql.append(" where " + DeviceUtil.getInNotInSql("yjwy_energy_electric.pk_project", QueryContents.TYPE_IN, set.toArray(new String[]{})) + " ");
            }
        }
        if (!DeviceUtil.stringIsEmpty(time) && !"default".equals(time)) {
            timeSql.append(" and year(yjwy_energy_data.create_time) =  '" + time + "'");
        }

        if (!DeviceUtil.stringIsEmpty(task_type)) {
            typeSql.append(" AND yjwy_pub_dict.dict_code_ =  '" + task_type + "' ");
        }
        // 查询语句
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT " +
                " yjwy_energy_electric.eq_id, " +
                " yjwy_pub_dict.dict_name_ AS dname, " +
                " yjwy_pub_project.project_name_ AS project, " +
                " yjwy_pub_area.area_name_ AS area, " +
                " yjwy_energy_electric.eq_code AS code, " +
                " yjwy_energy_electric.eq_name AS name, " +
                " yjwy_energy_electric.rate AS rate, " +
                " yjwy_energy_electric.pk_resource AS pk_resource, " +
                " yjwy_energy_electric.parent_id "+
                "FROM " +
                " yjwy_energy_electric " +
                "LEFT JOIN yjwy_energy_data ON yjwy_energy_electric.eq_id = yjwy_energy_data.eq_id " +
                "LEFT JOIN yjwy_pub_dict ON yjwy_pub_dict.dict_name_ = yjwy_energy_electric.hydropower " +
                "LEFT JOIN yjwy_pub_project ON yjwy_energy_electric.pk_project = yjwy_pub_project.pk_project_ " +
                "LEFT JOIN yjwy_pub_area ON yjwy_energy_electric.pk_area = yjwy_pub_area.pk_area_");

        sql.append(projectSql);
        sql.append(areaSql);
        sql.append(typeSql);
        sql.append(" and delete_flag = '0' ");
        sql.append(timeSql);
        if(params.getId() == null){
            sql.append(" and parent_id IS NULL ");
        }else {
            sql.append(" and parent_id = '"+params.getId()+"' ");
        }


        long count = problemStatisticsService.queryCount(sql.toString());
        // 分页
        if (null != page && null != rows) {
            sql.append(" limit " + (page - 1) * rows + "," + rows);
        }
        List<Map<String, Object>> models = problemStatisticsService.queryList(sql.toString());
        ModelAndResult mar = new ModelAndResult();
        mar.setTotal(count);
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        mar.setAttribute("rows", models);
        for(Map<String,Object> item:models){
            String parent_id = (String) item.get("parent_id");
            YJWYEnergyElectricModel model = new YJWYEnergyElectricModel();
            model.setParent_id((String) item.get("eq_id"));
            Query from = Query.from(YJWYEnergyElectricModel.META_ID);
            from.limit(1);
            Condition eq = Condition.eq("parent_id", (String) item.get("eq_id"));
            Condition delete_flag = Condition.neq("delete_flag", "1");
            eq.and(delete_flag);
            from.where(eq);
            List<YJWYEnergyElectricModel> yjwyEnergyElectricModels = yjwyEnergyElectricDomainService.queryListByCondition(from);
            if(yjwyEnergyElectricModels.size()>0){
                item.put("iconCls","icon-ic-ele1");
                item.put("state","closed");
            }else{
                item.put("iconCls","icon-ic-ele2");
                item.put("state","open");
            }
//            获取位置信息
            String area=areaservice.getRoomPlaceByResourceId(item.get("pk_resource")+"");
            
            item.put("install_area", area);
            String toyear="";
            String onyear="";
           
            
//            获取当前年读数
            if (!DeviceUtil.stringIsEmpty(time) && !"default".equals(time)) {
            	 int ontime= Integer.parseInt(time)-1;
            	 toyear="SELECT cast(yjwy_energy_data.data  as UNSIGNED INTEGER) as date FROM yjwy_energy_data WHERE yjwy_energy_data.eq_id =  '"+item.get("eq_id")+"' AND YEAR(yjwy_energy_data.create_time)='"+time+"' ORDER BY yjwy_energy_data.create_time DESC LIMIT 1";
            	
//               上一年读数
            	 onyear="SELECT cast(yjwy_energy_data.data  as UNSIGNED INTEGER) as date FROM yjwy_energy_data WHERE yjwy_energy_data.eq_id =  '"+item.get("eq_id")+"' AND YEAR(yjwy_energy_data.create_time)='"+ontime+"' ORDER BY yjwy_energy_data.create_time DESC LIMIT 1";
            }else{
            	 toyear="SELECT cast(yjwy_energy_data.data  as UNSIGNED INTEGER) as date FROM yjwy_energy_data WHERE yjwy_energy_data.eq_id =  '"+item.get("eq_id")+"' AND YEAR(yjwy_energy_data.create_time)=YEAR(NOW()) ORDER BY yjwy_energy_data.create_time DESC LIMIT 1";
             	
//               上一年读数
            	 onyear="SELECT cast(yjwy_energy_data.data  as UNSIGNED INTEGER) as date FROM yjwy_energy_data WHERE yjwy_energy_data.eq_id =  '"+item.get("eq_id")+"' AND YEAR(yjwy_energy_data.create_time)=year(date_sub(now(),interval 1 year)) ORDER BY yjwy_energy_data.create_time DESC LIMIT 1";
            }
            List<Map<String, Object>> ondata= StatisticsService.queryList(onyear);
            if(ondata.isEmpty()){
            	onyear="SELECT cast(yjwy_energy_electric.star_num as UNSIGNED INTEGER) as date FROM yjwy_energy_electric WHERE yjwy_energy_electric.eq_id =  '"+item.get("eq_id")+"'";
            	ondata=StatisticsService.queryList(onyear);
            }
            List<Map<String, Object>> indata= StatisticsService.queryList(toyear);
            BigInteger id=null;
            BigInteger od=null;
            int todata=0;
            int updata=0;
           if(indata.size()!=0){
        	   id=(BigInteger) indata.get(0).get("date");
           }
           if(ondata.size()!=0){
//        	   if(ondata.get(0).get("date")!=""&&ondata.get(0).get("date").equals(null)){
        		   od=(BigInteger) ondata.get(0).get("date");
//        	   }
        	  
           }
           if(id!=null){
        	   todata=id.intValue();
           }
           if(od!=null){
        	   updata=od.intValue();
           }
           Integer r = (Integer) item.get("rate");
           int rate = r.intValue();
           if(rate==0){
        	   rate=1;
           }
           
           int date=rate*(todata-updata) ;
           item.put("date", date);
        }
        return mar;
    }




    /**
     * 查询数据集合
     *
     * @return
     */
    @RequestMapping(value = "query/read", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndResult queryread(ParamEntity params) {
        // 获取参数
        Integer page = params.getPage();
        Integer rows = params.getRows();
        String pk_area = params.getPk_area();
        String pk_project = params.getPk_project();
        String time = params.getEnd_time();
        String task_type = params.getTask_type();
        StringBuilder projectSql = new StringBuilder("");
        StringBuilder areaSql = new StringBuilder("");
        StringBuilder timeSql = new StringBuilder("");
        StringBuilder typeSql = new StringBuilder("");
        if (!DeviceUtil.stringIsEmpty(pk_project) && !"default".equals(pk_project)) {
            projectSql.append(" where yjwy_pub_project.pk_project_ = '" + pk_project + "' ");
        } else if (!DeviceUtil.stringIsEmpty(pk_area) && !"default".equals(pk_area)) {
            areaSql.append(" where yjwy_pub_area.pk_area_ = '" + pk_area + "' ");
        } else {
            Set<String> set = UserUtil.getUserProjectIds();
            if (!ArrayUtils.isEmpty(set)) {
                projectSql.append(" where " + DeviceUtil.getInNotInSql("yjwy_energy_electric.pk_project", QueryContents.TYPE_IN, set.toArray(new String[]{})) + " ");
            }
        }
        if (!DeviceUtil.stringIsEmpty(time) && !"default".equals(time)) {
            timeSql.append(" and year(yjwy_energy_data.create_time) =  '" + time + "'");
        }

        if (!DeviceUtil.stringIsEmpty(task_type)) {
            typeSql.append(" AND yjwy_pub_dict.dict_code_ =  '" + task_type + "' ");
        }
        // 查询语句
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("yjwy_pub_project.project_name_ as project, ");
        sql.append("yjwy_energy_electric.version as version, ");
        sql.append("yjwy_energy_electric.surface_num as surface, ");
        sql.append("yjwy_energy_electric.rate as rate, ");
        sql.append("yjwy_energy_electric.pk_resource as pk_resource, ");
        sql.append("yjwy_energy_electric.install_time as install_time, ");
        sql.append("yjwy_energy_electric.enable_time as enable_time, ");
        sql.append("yjwy_energy_electric.expect_use_time as expect_use_time, ");
        sql.append("yjwy_energy_electric.purpose_name as purpose_name, ");
        sql.append("yjwy_energy_electric.parent_id as parent_id, ");
        sql.append("yjwy_energy_electric.purpose as purpose, ");
        sql.append("yjwy_energy_electric.eq_code as code, ");
        sql.append("yjwy_energy_electric.eq_name as name , ");
        sql.append("yjwy_pub_area.area_name_ as area, ");
        sql.append("yjwy_energy_electric.eq_id as eq_id , ");
        sql.append("yjwy_energy_electric.notice_num as notice_num , ");
        sql.append("yjwy_energy_electric.metering_range as metering_range , ");
        sql.append("yjwy_energy_electric.parent_id as parentid   ");
        sql.append("FROM ");
        sql.append("yjwy_energy_electric ");
        sql.append("left Join yjwy_pub_area ON yjwy_pub_area.pk_area_ = yjwy_energy_electric.pk_area ");
        sql.append("left Join yjwy_pub_project ON yjwy_pub_project.pk_project_ = yjwy_energy_electric.pk_project ");
        sql.append("left Join yjwy_pub_dict ON yjwy_pub_dict.dict_name_ = yjwy_energy_electric.hydropower ");
        sql.append(projectSql);
        sql.append(areaSql);
        sql.append(typeSql);
        sql.append(timeSql);
        if(params.getId() == null){
            sql.append(" and parent_id IS NULL ");
        }else {
            sql.append(" and parent_id = '"+params.getId()+"' ");
        }
        sql.append(" and delete_flag = '0' ");
        sql.append(" order by yjwy_pub_project.project_name_  desc");
        long count = problemStatisticsService.queryCount(sql.toString());
        // 分页
        if (null != page && null != rows) {
            sql.append(" limit " + (page - 1) * rows + "," + rows);
        }
        List<Map<String, Object>> models = StatisticsService.queryList(sql.toString());
        for (Map<String, Object> map : models) {
            StringBuffer ondate = new StringBuffer();
            ondate.append("SELECT cast(yjwy_energy_data.data  as UNSIGNED INTEGER) as ondate FROM yjwy_energy_data WHERE yjwy_energy_data.eq_id ='" + map.get("eq_id") + "' ORDER BY yjwy_energy_data.create_time DESC LIMIT 1, 1");
            List<Map<String, Object>> model = StatisticsService.queryList(ondate.toString());
            StringBuffer indate = new StringBuffer();
            indate.append("SELECT cast(yjwy_energy_data.data  as UNSIGNED INTEGER) as date FROM yjwy_energy_data WHERE yjwy_energy_data.eq_id ='" + map.get("eq_id") + "' ORDER BY yjwy_energy_data.create_time DESC LIMIT 1");
            List<Map<String, Object>> inmodel = StatisticsService.queryList(indate.toString());

            int data = 0;
            BigInteger in = null;
//					获取本次读数
            if(inmodel.size()!=0){
            	in=(BigInteger) inmodel.get(0).get("date");
            }
            if(in!=null){
            	data=in.intValue();
            	map.put("date", data);
            }
            
            int ondata = 0;
            BigInteger o = null;
            if (model.size() != 0) {
//						获取上次读数
                o = (BigInteger) model.get(0).get("ondate");
            }


            if (o != null) {
                ondata = o.intValue();
            }

            int lately = data - ondata;
//					获取倍率
            Integer r = (Integer) map.get("rate");
            int rate = r.intValue();
            if (rate != 0) {
                lately = rate * lately;
            }
            map.put("lately", lately);
            if (model.size() == 0) {
                map.put("ondate", "");
            } else {
                map.put("ondate", model.get(0).get("ondate"));
            }
            String sql1 = "SELECT yjwy_energy_update.id as update_id ,yjwy_energy_update.energy_update as energy_update FROM yjwy_energy_update WHERE yjwy_energy_update.eq_id =  '"
                    + map.get("eq_id")
                    + "'";
            List<Map<String, Object>> mod = problemStatisticsService.queryList(sql1.toString());

            if (mod.size() != 0) {
                map.put("energy_update", mod.get(0).get("energy_update"));
            }
			String area=areaservice.getRoomPlaceByResourceId(map.get("pk_resource")+"");
			            
			map.put("install_area", area);


            Query from = Query.from(YJWYEnergyElectricModel.META_ID);
            from.limit(1);
            Condition eq = Condition.eq("parent_id", (String) map.get("eq_id"));
            Condition delete_flag = Condition.neq("delete_flag", "1");
            eq.and(delete_flag);
            from.where(eq);
            List<YJWYEnergyElectricModel> yjwyEnergyElectricModels = yjwyEnergyElectricDomainService.queryListByCondition(from);
            if(yjwyEnergyElectricModels.size()>0){
                map.put("iconCls","icon-ic-ele1");
                map.put("state","closed");
            }else{
                map.put("iconCls","icon-ic-ele2");
                map.put("state","open");
            }

        }
        ModelAndResult mar = new ModelAndResult();
        mar.setTotal(count);

        mar.setAttribute("rows", models);
        return mar;
    }


    /**
     * 导出报表
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "export/statistics", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndResult doExpDataStatisticsByTable(@RequestParam(value = "param", required = false) String param) {
        ParamEntity params = JSONObject.parseObject(param, ParamEntity.class);
        params.setPage(null);
        params.setRows(null);
        // 获取参数
        Integer page = params.getPage();
        Integer rows = params.getRows();
        String pk_area = params.getPk_area();
        String pk_project = params.getPk_project();
        String time = params.getEnd_time();
        String task_type = params.getTask_type();
        StringBuilder projectSql = new StringBuilder("");
        StringBuilder areaSql = new StringBuilder("");
        StringBuilder timeSql = new StringBuilder("");
        StringBuilder typeSql = new StringBuilder("");
        if (!DeviceUtil.stringIsEmpty(pk_project) && !"default".equals(pk_project)) {
            projectSql.append(" where yjwy_pub_project.pk_project_ = '" + pk_project + "' ");
        } else if (!DeviceUtil.stringIsEmpty(pk_area) && !"default".equals(pk_area)) {
            areaSql.append(" where yjwy_pub_area.pk_area_ = '" + pk_area + "' ");
        } else {
            Set<String> set = UserUtil.getUserProjectIds();
            if (!ArrayUtils.isEmpty(set)) {
                projectSql.append(" where " + DeviceUtil.getInNotInSql("yjwy_energy_electric.pk_project", QueryContents.TYPE_IN, set.toArray(new String[]{})) + " ");
            }
        }
        if (!DeviceUtil.stringIsEmpty(time) && !"default".equals(time)) {
            timeSql.append(" and year(yjwy_energy_data.create_time) =  '" + time + "'");
        }

        if (!DeviceUtil.stringIsEmpty(task_type)) {
            typeSql.append(" AND yjwy_pub_dict.dict_code_ =  '" + task_type + "' ");
        }
        // 查询语句
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ");
        sql.append("max(yjwy_energy_electric.eq_id) as eq_id,");
        sql.append("max(yjwy_pub_dict.dict_name_) as dname,");
        sql.append("max(yjwy_pub_project.project_name_) as project,");
        sql.append("max(yjwy_pub_area.area_name_) as area,");
        sql.append("max(yjwy_energy_electric.eq_code) as code,");
        sql.append("max(yjwy_energy_electric.eq_name) as name,");
        sql.append("Max(cast(yjwy_energy_data.data as UNSIGNED INTEGER )) as date ");
        sql.append("FROM yjwy_energy_electric ");
        sql.append(" Left Join yjwy_pub_dict ON yjwy_pub_dict.dict_name_ = yjwy_energy_electric.hydropower");
        sql.append(" Left Join yjwy_pub_project ON yjwy_energy_electric.pk_project = yjwy_pub_project.pk_project_");
        sql.append(" Left Join yjwy_energy_data ON yjwy_energy_electric.eq_id = yjwy_energy_data.eq_id");
        sql.append(" Left Join yjwy_pub_area ON yjwy_energy_electric.pk_area = yjwy_pub_area.pk_area_");
        sql.append(projectSql);
        sql.append(areaSql);
        sql.append(typeSql);
        sql.append(timeSql);
        sql.append(" group by yjwy_energy_electric.eq_id");
        long count = problemStatisticsService.queryCount(sql.toString());
        // 分页
        if (null != page && null != rows) {
            sql.append(" limit " + (page - 1) * rows + "," + rows);
        }
        List<Map<String, Object>> models = problemStatisticsService.queryList(sql.toString());
        ModelAndResult mar = new ModelAndResult();
        mar.setTotal(count);
        mar.setAttribute("rows", models);
        String path = "";
        if (task_type.equals("shuibiao")) {
            path = "templates/templates/energyloss/水表能耗统计.xls";

        }
        if (task_type.equals("dianbiao")) {
            path = "templates/templates/energyloss/电表能耗统计.xls";
        }

        return ImpAndExpExcel.doExpExcel(models.toArray(), new String[]{"area", "project", "name", "code", "install_area", "date"}, path, 2);
    }

    /**
     * 导出报表
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "export/see", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndResult doExpDataSeeByTable(@RequestParam(value = "param", required = false) String param) {
        ParamEntity params = JSONObject.parseObject(param, ParamEntity.class);
        params.setPage(null);
        params.setRows(null);

        String task_type = params.getTask_type();
        String path = "";
        ModelAndResult mr = this.queryread(params);
        if (task_type.equals("shuibiao")) {
            path = "templates/templates/energyloss/水表信息.xls";
            //如果是下载水表模板
            if ("0".equals(params.getIsTemplate())) {
                path = "templates/templates/energyloss/水表抄表模板.xls";
                return ImpAndExpExcel.doExpExcel(mr.get("rows"), new String[]{"project", "name", "code", "install_area", "", ""}, path, 2);
            }

        }
        if (task_type.equals("dianbiao")) {
            path = "templates/templates/energyloss/电表信息.xls";
            //如果是下载水表模板
            if ("0".equals(params.getIsTemplate())) {
                path = "templates/templates/energyloss/电表抄表模板.xls";
                return ImpAndExpExcel.doExpExcel(mr.get("rows"), new String[]{"project", "name", "code", "install_area", "", ""}, path, 2);
            }
        }

        return ImpAndExpExcel.doExpExcel(mr.get("rows"), new String[]{"area", "project", "code", "name", "install_area", "version", "surface", "rate", "", "date", "", "notice_num", "metering_range", ""}, path, 2);
    }

}
