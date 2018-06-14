package com.shareworx.ezfm.dv.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shareworx.ezfm.baseinfo.resources.model.YJWYResourcesModel;
import com.shareworx.ezfm.baseinfo.resources.service.YJWYResourcesBusinessService;
import com.shareworx.ezfm.constants.DVConstants;
import com.shareworx.ezfm.util.StringUtil;
import com.shareworx.platform.cache.IDmsCacheManager;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.DatabaseConnections;
import com.shareworx.platform.persist.Query;

@Controller
@RequestMapping("ezfm/api/dv")
public class DVController {
	
	@Autowired
	@Qualifier("cacheManager")
	private IDmsCacheManager cacheManager;
	
	@Autowired
	@Qualifier(YJWYResourcesBusinessService.ID)
	private YJWYResourcesBusinessService resourcesBusinessService;

	/**
	 * 近七天工单状态
	 * @param project
	 * @return
	 */
	@RequestMapping(value="last7WorkTask/{project}", method=RequestMethod.GET)
	@ResponseBody
	public Object last7WorkTask(@PathVariable String project) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Map<String, String>> resultMap = cacheManager.get(DVConstants.DV_DATA_STATISTICS, DVConstants.METHOD_LAST7WORKTASK);
		Map<String, String> map = resultMap.get(project);
		if(map==null) {
			result.put("status", "success");
			result.put("data", new ArrayList<>());
			return result;
		}
		List<Map<String, String>> dataMap = new ArrayList<Map<String, String>>();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DAY_OF_YEAR, -9);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String[] statuss = new String[]{
			"未派单","待接单","维修中","完成","已拒单","已取消","已关闭"
		};
		for(int i=0;i<7;i++) {
			c.add(Calendar.DAY_OF_YEAR, 1);
			for(int j=0;j<statuss.length;j++) {
				String date = sdf.format(c.getTime());
				String status = statuss[j];
				String key = date+"|"+status;
				String count = map.get(key);
				if(count==null||"".equals(count)) {
					count = "0";
				}
				Map<String, String> data = new HashMap<String, String>();
				data.put("date", date);
				data.put("status", status);
				data.put("count", count);
				dataMap.add(data);
			}
		}
		result.put("status", "success");
		result.put("data", dataMap);
		return result;
	}
	
	/**
	 * 近一年工单完成情况
	 * @param project
	 * @return
	 */
	@RequestMapping(value="lastYearWorkTaskComplete/{project}", method=RequestMethod.GET)
	@ResponseBody
	public Object lastYearWorkTaskComplete(@PathVariable String project) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Map<String, String>> resultMap = cacheManager.get(DVConstants.DV_DATA_STATISTICS, DVConstants.METHOD_LASTYEARWORKTASKCOMPLETE);
		Map<String, String> map = resultMap.get(project);
		if(map==null) {
			result.put("status", "success");
			result.put("data", new ArrayList<>());
			return result;
		}
		List<Map<String, String>> dataMap = new ArrayList<Map<String, String>>();
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String[] types = new String[]{
			"抢单","派单"
		};
		for(int i=0;i<12;i++) {
			c.add(Calendar.MONTH, 1);
			for(int j=0;j<types.length;j++) {
				String date = sdf.format(c.getTime());
				String type = types[j];
				String key = date+"|"+type;
				String count = map.get(key);
				if(count==null||"".equals(count)) {
					count = "0";
				}
				Map<String, String> data = new HashMap<String, String>();
				data.put("date", date);
				data.put("type", type);
				data.put("count", count);
				dataMap.add(data);
			}
		}
		result.put("status", "success");
		result.put("data", dataMap);
		return result;
	}
	
	/**
	 * 查询今日报事
	 * @param project
	 * @return
	 */
	@RequestMapping(value="todayProblem/{project}", method=RequestMethod.GET)
	@ResponseBody
	public Object todayProblem(@PathVariable String project) {
		Map<String, Object> result = new HashMap<String, Object>();
		String sql = "select count(*) as count, t.state from yjwy_problem_details t "+
				" where t.create_time>=CURDATE() "+
				" and t.fk_project_id=? "+
				" group by t.state "+
				" order by t.create_time desc";
		JdbcTemplate jdbcTemplate = DatabaseConnections.getReadTemplate();
		List<Map<String, Object>> listMap = jdbcTemplate.queryForList(sql, project);
		Map<String, String> map = new HashMap<String, String>();
		for(int i=0;i<listMap.size();i++) {
			map.put(getProblemState(map.get("state")+""), map.get("count")+"");
		}
		String[] states = new String[]{
				"未处理", "处理中", "处理完成", "已回访", "已关闭"
		};
		List<Map<String, String>> dataMap = new ArrayList<Map<String, String>>();
		for(int i=0;i<states.length;i++) {
			if(map.get(states[i])!=null) {
				Map<String, String> tempMap = new HashMap<String, String>();
				tempMap.put("state", states[i]);
				tempMap.put("count", map.get(states[i]));
				dataMap.add(tempMap);
			} else {
				Map<String, String> tempMap = new HashMap<String, String>();
				tempMap.put("state", states[i]);
				tempMap.put("count", "0");
				dataMap.add(tempMap);
			}
		}
		result.put("status", "success");
		result.put("data", dataMap);
		return result;
	}
	
	private String getProblemState(String state) {
		switch(state) {
			case "1":
				return "未处理";
			case "2":
				return "处理中";
			case "3":
				return "处理完成";
			case "4":
				return "已回访";
			case "5":
				return "已关闭";
			default:
				return "";
		}
	}
	
	/**
	 * 查询当月设备完好率
	 * @param resourceid
	 * @return
	 */
	@RequestMapping(value="deviceHealthy/{resourceid}", method=RequestMethod.GET)
	@ResponseBody
	public Object deviceHealthy(@PathVariable String resourceid) {
		Map<String, Object> result = new HashMap<String, Object>();
		Calendar now = Calendar.getInstance();
		String year = now.get(Calendar.YEAR)+"";
		String month = now.get(Calendar.MONTH)+"";
		Query query = Query.from(YJWYResourcesModel.META_ID).where(Condition.eq(YJWYResourcesModel.PK_RESOURCES, resourceid));
		YJWYResourcesModel[] models = resourcesBusinessService.query(query);
		if(models==null||models.length==0) {
			result.put("status", "success");
			result.put("data", "100.0");
			return result;
		}
		List<YJWYResourcesModel> resources = resourcesBusinessService.findResoucesIncludeAllChilds(models[0].getPk_crop(), models[0].getPk_resources());
		if(resources==null||resources.size()==0) {
			result.put("status", "success");
			result.put("data", "100.0");
			return result;
		}
		String[] resourceids = new String[resources.size()];
		for(int i=0;i<resources.size();i++) {
			resourceids[i] = "'"+resources.get(i).getPk_resources()+"'";
		}
		String sql1 = "select count(*) from ( "+
						" SELECT "+
						" case when a.fk_repair_equipment is not null then a.fk_repair_equipment when b.fk_repair_equipment is not null then b.fk_repair_equipment else null end as eq "+
						" FROM yjwy_problem_details a left join yjwy_worktask_details b on  a.fk_details_id=b.pk_details_id "+
						" where not (a.fk_repair_equipment is null and  b.fk_repair_equipment is null) "+
						" and a.create_time>='"+year+"-"+month+"-01' "+
						") c,yjwy_fmdata_eq d,yjwy_fmdata_room e where c.eq=d.eq_id and d.rm_id=e.rm_id and e.fk_resource_id in("+StringUtil.join(resourceids, ",")+")";
		JdbcTemplate jdbcTemplate = DatabaseConnections.getReadTemplate();
		int count1 = jdbcTemplate.queryForObject(sql1, Integer.class);
		String sql2 = "select count(*) from yjwy_fmdata_eq d,yjwy_fmdata_room e where d.rm_id=e.rm_id and e.fk_resource_id in("+StringUtil.join(resourceids, ",")+")";
		int count2 = jdbcTemplate.queryForObject(sql2, Integer.class);	
		double precent = 0;
		if(count2==0) {
			precent = 100;
		} else {
			precent = (count2-count1)*10000/count2;
			precent = precent/100;
		}
		result.put("status", "success");
		result.put("data", precent);
		return result;
	}
}
