package com.shareworx.ezfm.app.sqlite.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.shareworx.platform.persist.dao.ObjectPersistDao;
import com.shareworx.ezfm.app.util.AppDateUtils;
import com.shareworx.ezfm.app.util.AppEmptyUtils;
import com.shareworx.ezfm.app.util.AppFileUtil;
import com.shareworx.ezfm.app.util.AppIdBuilder;
import com.shareworx.ezfm.app.util.AppJsonMessage;
import com.shareworx.ezfm.app.util.AppSqliteDBHelperUtils;
import com.shareworx.ezfm.app.util.SysConfigurationReadUtil;
/**
 * 通过 sqlite 下载需要的 fmdata数据 接口实现
 * @author lingwei.li
 *
 */
@Service(AppCreateDBSqliteService.ID)
public class AppCreateDBSqliteServiceImpl implements AppCreateDBSqliteService{

	@Autowired
	@Qualifier(ObjectPersistDao.ID)
	private ObjectPersistDao dao;
	
	public void setDao(ObjectPersistDao dao) {
		this.dao = dao;
	}
	
	final static Logger logger = Logger.getLogger(AppCreateDBSqliteServiceImpl.class);
	static String SourcePath = "";
	static String TargetPath = "";
	static String DownloadPath = "";
	static String DBName = "";
	
	static {
		try {
			SourcePath = SysConfigurationReadUtil.getInstance().getConfigItem(
					"SourcePath");
			TargetPath = SysConfigurationReadUtil.getInstance().getConfigItem(
					"TargetPath");
			DownloadPath = SysConfigurationReadUtil.getInstance().getConfigItem(
					"DownloadPath");
			DBName = SysConfigurationReadUtil.getInstance().getConfigItem(
					"DBName");
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据 app_fmdata_record 表中的最新 
	 * 			createtime 查询之后的数据
	 */
	@Override
	public JSONObject updateDataDB() throws Exception {
		logger.info("appCreateDBSqliteService/updateDataDB");
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		//检测更新的版本
		String checksql = "select createtime, path "
				+ "from app_fmdata_record "
				+ "where createtime = "
				+ "(select MAX(createtime) from app_fmdata_record)";
		List<Map<String, Object>> fmdataDBMap = readJdbcTemplate.queryForList(checksql);
		
		if (AppEmptyUtils.isEmpty(fmdataDBMap)) {
			logger.info("fmdataDBMap is null");
			return AppJsonMessage.toJsonMsgTrue(fmdataDBMap);
		}
		return AppJsonMessage.toJsonMsgTrue(fmdataDBMap.get(0));
	}
	

	/**
	 * 根据 createtime 获取新增的数据进行 update sqliteDB 文件
	 */
	@Override
	public void createSqliteDB() throws Exception {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		//获取系统当前时间
		String nowDate = AppDateUtils.getCurrentTimeEx();
		String new_createtime = "0000-00-00 00:00:00";
		
		//检测更新的版本
		String checksql = "select createtime, path "
				+ "from app_fmdata_record "
				+ "where createtime = "
				+ "(select MAX(createtime) from app_fmdata_record)";
		List<Map<String, Object>> fmdataDBMap = readJdbcTemplate.queryForList(checksql);
		try {
			if (AppEmptyUtils.isEmpty(fmdataDBMap)) {
				//第一次打包
				clearDB();   // 清空数据
			}else {
				//不是第一次打包，获取更新的时间
				new_createtime = fmdataDBMap.get(0).get("createtime").toString();
			}
			
			Boolean isNewAdd_pmp = yjwy_fmdata_pmps(new_createtime); // 生成yjwy_fmdata_pmps 表
			Boolean isNewAdd_room = yjwy_fmdata_room(new_createtime); // 生成yjwy_fmdata_room 表
			Boolean isNewAdd_eq = yjwy_fmdata_eq(new_createtime);   // 生成 yjwy_fmdata_eq 表	
			
			//判断 和上次的 db 文件比较是否有新增 
			if (isNewAdd_pmp || isNewAdd_room || isNewAdd_eq) {
				
				//要打包的源文件
				String resourcesPath =  SourcePath;
				//打好包需要放到的路径
				String targetPath = TargetPath;
				
				String zipName = DBName+ ".zip";
				String downloadpath = DownloadPath + "/" + zipName;
				
				//先将打好包需要放到的路径 清空
				AppFileUtil.deleteDir(new File(targetPath));
				
				//再打包文件
				AppFileUtil.compressedFile(resourcesPath, targetPath);
				
				//新增app_fmdata_record 记录表
				String newFmSql = "insert into app_fmdata_record "
						+ "(id, name, path, createtime) "
						+ "value ('"+AppIdBuilder.createRandomId()+"', "
								+ "'"+zipName+"', '"+downloadpath+"', "
										+ "'"+nowDate+"')"; 
				readJdbcTemplate.update(newFmSql);
				logger.error(nowDate+"生成离线db成功！");
			}
		} catch (Exception e) {
			logger.error("生成离线db报错：" + e.getMessage());
		}
		
	}

	
	/**
	 * 清空数据库
	 * @throws Exception
	 */
	private void clearDB() throws Exception {
		
		AppSqliteDBHelperUtils.clearnDB();
			
	}
	
	/**
	 * 生成 yjwy_fmdata_pmps 表
	 * @param new_createtime
	 * @return  Boolean 是否有新增数据
	 * @throws Exception
	 */
	private Boolean yjwy_fmdata_pmps(String new_createtime) throws Exception {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		/**是否有新增*/
		Boolean isNewAdd = true;
		try {
			String sql="select pmp_id, pmps_id, instructions, "
					+ "detail_instructions "
					+ "from yjwy_fmdata_pmps "
					+ "where dms_update_time > '"+new_createtime+"'";
			List<Map<String, Object>> list = readJdbcTemplate.queryForList(sql);
			
			if (!AppEmptyUtils.isEmpty(list)) {
				AppSqliteDBHelperUtils.insert("yjwy_fmdata_pmps", list);
				logger.info("新增 yjwy_fmdata_pmps 成功");
			}else{
				isNewAdd = false;
				logger.info("没有新增 yjwy_fmdata_pmps 数据");
			}
			
		} catch (Exception e) {
			logger.info("生成 yjwy_fmdata_pmps 失败，请联系管理员！");
			e.printStackTrace();
		}
		return isNewAdd;
	}
	
	/**
	 * 生成 yjwy_fmdata_room 表
	 * @throws Exception
	 */
	private Boolean yjwy_fmdata_room(String new_createtime) throws Exception {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		/**是否有新增*/
		Boolean isNewAdd = true;
		try {
			String sql="select rm_id, fm_code, name, alias "
					+ "from yjwy_fmdata_room "
					+ "where dms_update_time >'"+new_createtime+"'";
			List<Map<String, Object>> list = readJdbcTemplate.queryForList(sql);
			
			if (!AppEmptyUtils.isEmpty(list)) {
				AppSqliteDBHelperUtils.insert("yjwy_fmdata_room", list);
				logger.info("生成 yjwy_fmdata_room 成功");
			}else{
				isNewAdd = false;
				logger.info("没有新增 yjwy_fmdata_room 数据");
			}
			
		} catch (Exception e) {
			logger.info("生成 yjwy_fmdata_room 失败，请联系管理员！");
			e.printStackTrace();
		}
		return isNewAdd;
	}
	
	/**
	 * 生成 yjwy_fmdata_eq 表
	 * @throws Exception
	 */
	private Boolean yjwy_fmdata_eq(String new_createtime) throws Exception {
		JdbcTemplate readJdbcTemplate = dao.getReadTemplate();
		/**是否有新增*/
		Boolean isNewAdd = true;
		try {
			String sql="select eq_id, name, fm_code, "
					+ "eq_description, long_description, "
					+ "usual_name, brand, power, factory, "
					+ "model, rm_id "
					+ "from yjwy_fmdata_eq "
					+ "where dms_update_time >'"+new_createtime+"'";
			List<Map<String, Object>> list = readJdbcTemplate.queryForList(sql);
			if (!AppEmptyUtils.isEmpty(list)) {
				AppSqliteDBHelperUtils.insert("yjwy_fmdata_eq", list);
				logger.info("新增 yjwy_fmdata_eq 成功");
			}else{
				logger.info("没有新增 yjwy_fmdata_eq ");
				isNewAdd = false;
			}
		} catch (Exception e) {
			logger.info("生成 yjwy_fmdata_eq 失败，请联系管理员！");
			e.printStackTrace();
		}
		return isNewAdd;
	}

}
