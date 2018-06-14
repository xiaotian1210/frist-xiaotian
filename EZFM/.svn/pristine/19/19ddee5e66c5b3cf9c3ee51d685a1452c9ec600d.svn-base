package com.shareworx.ezfm.energyloss.data.action;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shareworx.ezfm.baseinfo.area.model.YJWYAreaModel;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesBusinessService;
import com.shareworx.ezfm.device.fmdata.service.YJWYEqDomainService;
import com.shareworx.ezfm.device.util.DictUtils;
import com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataDomainService;
import com.shareworx.ezfm.energyloss.data.vo.EnergyVo;
import com.shareworx.ezfm.energyloss.tabledefinition.model.YJWYEnergyElectricModel;
import com.shareworx.ezfm.energyloss.tabledefinition.service.YJWYEnergyElectricDomainService;
import com.shareworx.ezfm.system.dict.model.YJWYDictModel;
import com.shareworx.ezfm.utils.ImpAndExpExcel;
import com.shareworx.ezfm.utils.UserUtil;
import com.shareworx.ezfm.worktask.repairclass.model.YJWYWorkTaskRepairClassModel;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.shareworx.ezfm.energyloss.data.dao.YjwyEnergyDataDao;
import com.shareworx.ezfm.energyloss.data.model.YjwyEnergyDataModel;
import com.shareworx.ezfm.energyloss.data.service.YjwyEnergyDataBusinessService;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.SpringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 水表电表抄表操作控制器
 * @author zhentong.jia
 *
 */
@Controller
@RequestMapping("ezfm/energyloss/data")
public class YjwyEnergyDataAction {

	Logger log = Logger.getLogger(YjwyEnergyDataAction.class);
	
	/** 跳转页面 */
	public final static String PAGE_FORWARD = "ezfm/energyloss/data/index";
	/**
	 *
	 */
		public final static String PAGE_EACH = "ezfm/energyloss/data/each";

	@Autowired
	@Qualifier(YjwyEnergyDataBusinessService.ID)
	private YjwyEnergyDataBusinessService service;
	@Autowired
	@Qualifier(YJWYEnergyElectricDomainService.ID)
	YJWYEnergyElectricDomainService energyElectricDomainService;

	@Autowired
	@Qualifier(YjwyEnergyDataDomainService.ID)
	YjwyEnergyDataDomainService energyDataDomainService;


	@Autowired
	@Qualifier(YJWYResourcesBusinessService.ID)
	YJWYResourcesBusinessService resourcesBusinessService;

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	
	public void setService(YjwyEnergyDataBusinessService service) {
		this.service = service;
	}

	/**
	 * 按月查看用电、用水转向列表页面
	 * @return
	 */
	@RequestMapping(value="each", method=RequestMethod.GET)
	public ModelAndView eachForward(){
		return new ModelAndView(PAGE_EACH);
	}

	/**
	 * 按月查看项目用电、用水转向列表页面
	 * @return
	 */
	@RequestMapping(value="index", method=RequestMethod.GET)
	public ModelAndView listForward(){
		return new ModelAndView(PAGE_FORWARD);
	}


	/**
	 * 查询操作
	 * @return
	 */
	@RequestMapping(value="query", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestParam(value="param", required=false) String param) {
		Query query = JSON.parseObject(param, Query.class);
		YjwyEnergyDataModel[] models = service.query(query);
		YjwyEnergyDataDao domainDao = SpringUtils.getBean(YjwyEnergyDataDao.ID);
		long count = domainDao.countListByCondition(query);
		ModelAndResult mar = new ModelAndResult(models);
		mar.setTotal(count);
		return mar;
	}


	@RequestMapping(value="query/month", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult queryMonth( @RequestParam String param) {


		EnergyVo vo  = JSON.parseObject(param,EnergyVo.class);
		YJWYDictModel dictByCode = DictUtils.getDictByCode(vo.getType());
		vo.setType(dictByCode.getDict_name());
        List<Map<String, Object>> maps = service.queryMonthData(vo);
        for(Map<String,Object> map:maps){
            String pk_project = (String) map.get("pk_project");
            if(vo.getYear() == null){
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
            	vo.setYear(year+"");
			}


            List<Map<String, Object>> list = service.queryMonthDetails(pk_project, vo.getType(),vo.getYear());
            BigDecimal d = (BigDecimal) map.get("notice_num");
            Integer notice_num = d.intValue();

            for(Map<String,Object> item:list){
                //获取月份
                Integer mon = Integer.parseInt(item.get("mon")+"");
                Integer now = (Integer) item.get("now");
                String status ;
                if(now> notice_num){
                    status="异常";
                }else{
                    status ="正常";
                }
                String m = now+"|"+notice_num+"|"+status;
                map.put("mon"+mon,m);
            }
        }
		if("1".equals(vo.getExport())){

        	 String path ;
        	 if("0".equals(vo.getType())){
				  path = "templates/templates/energyloss/按月查看项目用水量.xls";
			 }else {
				  path = "templates/templates/energyloss/按月查看项目用电量.xls";
			 }
			return ImpAndExpExcel.doExpExcel(maps.toArray(),
					new String[] { "area_name_", "project_name_" ,"mon1","mon2","mon3","mon4","mon5","mon6","mon7","mon8","mon9","mon10","mon11","mon12"},
					path, 2);
		}
        Long count = service.countMonthData(vo);
        ModelAndResult mar = new ModelAndResult(maps);
		mar.setTotal(count);

//		service.updateLastEneryData("00000020170719003S2S",401);


		return mar;
	}


	@RequestMapping(value="query/month/each", method=RequestMethod.GET)
	public @ResponseBody ModelAndResult queryMonthEach( @RequestParam String param) {


		EnergyVo vo  = JSON.parseObject(param,EnergyVo.class);
		YJWYDictModel dictByCode = DictUtils.getDictByCode(vo.getType());
		vo.setType(dictByCode.getDict_name());
		List<Map<String, Object>> maps = service.queryMonthDataEach(vo);
		for(Map<String,Object> map:maps){
			String eq_id = (String) map.get("eq_id");
			if(vo.getYear() == null){
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				vo.setYear(year+"");
			}
			List<Map<String, Object>> list = service.queryMonthDetailsEach(eq_id,vo.getYear());
			String pk_resource = (String) map.get("pk_resource");
			if(pk_resource != null){
                String allParent = resourcesBusinessService.findAllParent(pk_resource);
                map.put("allParent",allParent);
            }

            String is_parent = (String) map.get("is_parent");
			if(is_parent == null){
			    map.put("is_parent","分"+dictByCode.getDict_name());
            }else{
                map.put("is_parent","总"+dictByCode.getDict_name());
            }
            //row 变成column
			for(Map<String,Object> item:list){
				//获取月份
				Integer mon = Integer.parseInt(item.get("mon")+"");
				map.put("mon"+mon,item.get("now"));
			}
		}
		if("1".equals(vo.getExport())){

			String path ;
			if("0".equals(vo.getType())){
				path = "templates/templates/energyloss/按月查看水表用水量.xls";
			}else {
				path = "templates/templates/energyloss/按月查看电表用电量.xls";
			}
			return ImpAndExpExcel.doExpExcel(maps.toArray(),
					new String[] { "area_name_", "project_name_" ,"eq_code","eq_name","is_parent","allParent","mon1","mon2","mon3","mon4","mon5","mon6","mon7","mon8","mon9","mon10","mon11","mon12"},
					path, 2);
		}
		Long count = service.countMonthDataEach(vo);
		ModelAndResult mar = new ModelAndResult(maps);
		mar.setTotal(count);



		return mar;
	}


	
	/**
	 * 新增保存操作
	 * @return
	 */
	@RequestMapping(value="save", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult save(@RequestBody YjwyEnergyDataModel model) {
        YJWYEnergyElectricModel yjwyEnergyElectricModel = energyElectricDomainService.queryById(model.getEq_id());
        model.setPk_project( yjwyEnergyElectricModel.getPk_project());
		YjwyEnergyDataModel[] rs = service.save(new YjwyEnergyDataModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 修改保存操作
	 * @return
	 */
	@RequestMapping(value="update", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult update(@RequestBody YjwyEnergyDataModel model) {
		YjwyEnergyDataModel[] rs = service.update(new YjwyEnergyDataModel[]{model});
		return new ModelAndResult(rs);
	}
	
	/**
	 * 删除操作
	 * @return
	 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public @ResponseBody ModelAndResult delete(@RequestBody YjwyEnergyDataModel[] models) {
		YjwyEnergyDataModel[] rs = service.delete(models);
		return new ModelAndResult(rs);
	}


    /**
     * 导入Excel
     *
     * @return
     */
    @RequestMapping(value = "import/excel", method = RequestMethod.POST)
    public @ResponseBody ModelAndResult impExcel(HttpServletRequest request,
                                                 @RequestParam("excleFile") MultipartFile file) {
        String[] files = new String[] { "project_name", "eq_name", "eq_code",  "position",
				"create_time","data"};
        JSONArray jsonArray;
        try {
            jsonArray = ImpAndExpExcel.doImpExcel(file, files, 2);
        } catch (IOException e) {
            e.printStackTrace();
            return new ModelAndResult(false, "导入失败");
        }
        List<YjwyEnergyDataModel> list = new ArrayList<>();
        for(int i = 0 ;i<jsonArray.size();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            String create_time = jsonObject.getString("create_time");
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Integer data1 = jsonObject.getInteger("data");
            if(data1 == null ||data1<0){
                return new ModelAndResult(false, "导入失败 第 "+(i+1)+" 数据有误");
            }
            Date parse = null;
            try {
                 parse = format.parse(create_time);
            } catch (ParseException e) {
                return new ModelAndResult(false, "导入失败 第 "+(i+1)+" 日期格式有误");
            }
            YJWYEnergyElectricModel model = new YJWYEnergyElectricModel();
            String eq_code = jsonObject.getString("eq_code");
            model.setEq_code(eq_code);
            List<YJWYEnergyElectricModel> yjwyEnergyElectricModels = energyElectricDomainService.queryByExample(model);
            if(yjwyEnergyElectricModels.size() != 1){
                return new ModelAndResult(false, "导入失败 第 "+(i+1)+" 设备不存在");
            }

            YjwyEnergyDataModel data = new YjwyEnergyDataModel();
			YJWYEnergyElectricModel model1 = yjwyEnergyElectricModels.get(0);
			data.setEq_id(model1.getEq_id());
            data.setData(data1+"");
            data.setPk_project(model1.getPk_project());
            data.setPk_crop(UserUtil.getCurrentUser().getPk_crop());
            data.setCreate_time(DateTimeUtil.getDate(parse,"yyyy-MM-dd HH:mm:ss"));
            data.setCreate_user(UserUtil.getCurrentPk());
            list.add(data);

        }

        for(YjwyEnergyDataModel item:list){
            energyDataDomainService.save(item);
        }
//        return new ModelAndResult(false, "导入失败");
        return new ModelAndResult(true, "导入成功");
    }
}
