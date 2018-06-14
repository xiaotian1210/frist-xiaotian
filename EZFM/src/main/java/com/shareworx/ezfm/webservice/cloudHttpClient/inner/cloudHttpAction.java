package com.shareworx.ezfm.webservice.cloudHttpClient.inner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.StringUtils;

@Controller
@RequestMapping("ezfm/cloudHttp")
public class cloudHttpAction {

	final static Logger logger = Logger.getLogger(cloudHttpAction.class);

	@Autowired
	@Qualifier(cloudHttpService.ID)
	private cloudHttpService service;

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;

	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}

	@RequestMapping(value = "submitEvent", method = RequestMethod.POST)
	public @ResponseBody String submitEvent(HttpServletRequest reqParam, HttpServletResponse response)
			throws Exception {
		String pk_crop = reqParam.getParameter("pk_crop");
		String request_source = reqParam.getParameter("request_source");
		String user_id = reqParam.getParameter("user_id");
		String project_id = reqParam.getParameter("project_id");
		String service_id = reqParam.getParameter("service_id");
		String record_type = reqParam.getParameter("record_type");
		String remarks = reqParam.getParameter("remarks");
		String contack = reqParam.getParameter("contack");
		String telephone = reqParam.getParameter("telephone");
		String location = reqParam.getParameter("location");
		String WorkTaskId = reqParam.getParameter("WorkTaskId");
		String yeZhuId = reqParam.getParameter("yeZhuId");

		return this.service.submitEvent(pk_crop, request_source, user_id, project_id, service_id, record_type, remarks,
				contack, telephone, location, WorkTaskId, yeZhuId);
	}

	@RequestMapping(value = "eventClassHistoryAddressInfo", method = RequestMethod.POST)
	public @ResponseBody String eventClassHistoryAddressInfo(HttpServletRequest reqParam, HttpServletResponse response)
			throws Exception {
		String user_id = reqParam.getParameter("user_id");
		String project_id = reqParam.getParameter("project_id");

		return this.service.eventClassHistoryAddressInfo(user_id, project_id);
	}

	@RequestMapping(value = "eventList", method = RequestMethod.POST)
	public @ResponseBody String eventList(HttpServletRequest reqParam, HttpServletResponse response) throws Exception {
		String user_id = reqParam.getParameter("user_id");
		String project_id = reqParam.getParameter("project_id");
		String order_type = reqParam.getParameter("order_type");
		String record_type = reqParam.getParameter("record_type");
		int page_num;
		int per_size;
		if (reqParam.getParameter("page_num") == null || reqParam.getParameter("page_num").equals("")) {
			page_num = 0;
		} else {
			page_num = Integer.parseInt(reqParam.getParameter("page_num"));
		}
		if (reqParam.getParameter("perg_size") == null || reqParam.getParameter("perg_size").equals("")) {
			per_size = 0;
		} else {
			per_size = Integer.parseInt(reqParam.getParameter("perg_size"));
		}
        
		return this.service.eventList(user_id, project_id, order_type, record_type, page_num, per_size);
	}

	// 每天11点27分50秒时推送易彩区超时工单
	@Scheduled(cron = "50 27 11 * * ?")
	public void sendTimingTask() {
		// 查询超时工单
		long currentTime = System.currentTimeMillis() - 5 * 60 * 1000;
		Date date = new Date(currentTime);
		String sql = "select ycq_worktaskid_ from yjwy_problem_details where fk_details_id in ("
				+ "select pk_details_id from yjwy_worktask_details where task_state=0 and create_time<'"
				+ DateTimeUtil.getTimestampString(date) + "' "
				+ " and fk_project_id in ( select project_id from yjwy_worktask_area_project_nexus)) and ycq_worktaskid_ != '' ";
		JdbcTemplate read = dao.getReadTemplate();
		List<String> datailsCodeList = read.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				if (!StringUtils.isEmpty(rs.getString("ycq_worktaskid_"))) {
					return rs.getString("ycq_worktaskid_");
				} else {
					return "error";
				}
			}
		});
		for (String datails : datailsCodeList) {
			if (!datails.equals("error")) {
				String Status = "timeout";
				String Property_order_id = datails;
				String pem_id = "";
				this.service.sendCloudpost(Status, Property_order_id, pem_id);
			}
		}
    }

}
