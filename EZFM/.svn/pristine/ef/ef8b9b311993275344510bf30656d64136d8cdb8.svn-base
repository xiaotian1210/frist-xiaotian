package com.shareworx.ezfm.device.patrol.record.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.ezfm.baseinfo.project.model.YJWYProjectModel;
import com.shareworx.ezfm.device.patrol.record.model.YJWYTaskFileModel;
import com.shareworx.ezfm.device.patrol.record.service.YJWYRecordService;
import com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileBusinessService;
import com.shareworx.ezfm.device.patrol.record.service.YJWYTaskFileService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.device.util.vo.ParamEntity;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 巡检维保记录表操作控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/patrol/record")
public class YJWYRecordAction {

	final static Logger log = Logger.getLogger(YJWYRecordAction.class);

	/** 跳转页面 */
	public final static String CHECK = "ezfm/device/patrol/record/check_record_listcard";
	public final static String MAINT = "ezfm/device/patrol/record/maint_record_listcard";
	public final static String FILE = "ezfm/device/patrol/record/pic_list";

	@Autowired
	@Qualifier(YJWYRecordService.ID)
	private YJWYRecordService recordService;

	public void setRecordService(YJWYRecordService recordService) {
		this.recordService = recordService;
	}

	@Autowired
	@Qualifier(YJWYTaskFileBusinessService.ID)
	private YJWYTaskFileBusinessService taskFileBusinessService;

	public void setService(YJWYTaskFileBusinessService taskFileBusinessService) {
		this.taskFileBusinessService = taskFileBusinessService;
	}

	@Autowired
	@Qualifier(YJWYTaskFileService.ID)
	private YJWYTaskFileService taskFileService;

	public void setService(YJWYTaskFileService taskFileService) {
		this.taskFileService = taskFileService;
	}

	/**
	 * 转向巡检任务列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "checkindex", method = RequestMethod.GET)
	public ModelAndView checkListForward() {
		return new ModelAndView(CHECK);
	}

	/**
	 * 转向维保任务列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "maintindex", method = RequestMethod.GET)
	public ModelAndView maintListForward() {
		return new ModelAndView(MAINT);
	}

	/**
	 * 转向查看附件页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "fileindex", method = RequestMethod.GET)
	public ModelAndView fileForward() {
		return new ModelAndView(FILE);
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(ParamEntity params) {
		YJWYProjectModel[] projects = null;
		Set<String> projectids = new HashSet<String>();
		if(params.getPk_area()==null||"default".equals(params.getPk_area())){
			projects = UserUtil.getUserProject();
		}else{
			if(params.getPk_project()==null||"default".equals(params.getPk_project())){
				projects=UserUtil.getUserProject(params.getPk_area());
			}else{
				projectids.add(params.getPk_project());				
			}
		}
		if(projects != null && projects.length>0){
			for(int i=0;i<projects.length;i++){
				projectids.add(projects[i].getPk_project());
			}
		}
		params.setProject_ids(projectids);
		List<Map<String, Object>> list = recordService.queryRecordMap2(params);
		long count = recordService.queryRecordCount(params);
		ModelAndResult mar = new ModelAndResult();
		mar.setTotal(count);
		mar.setAttribute("rows", list);
		return mar;
	}

	/**
	 * 附件下载
	 *
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadFile(ParamEntity params) throws IOException {
		String task_id = params.getTask_id();
		// 获取附件file对象集合
		List<File> fileList = this.getFiles(task_id);
		String zipName = null;
		String zipPath = null;
		// 压缩文件名称、路径
		zipName = task_id + ".zip";
		zipPath = "E:/ezfm/temp/" + zipName;
		this.getZipFile(zipName, zipPath, fileList);
		// 输出文件到前台
		FileSystemResource file = new FileSystemResource(zipPath);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
		headers.add("Content-Disposition", String.format("attachment;filename=\"%s\"", file.getFilename()));
		headers.add("Pragma", "no-cache");
		headers.add("Expires", "0");
		return ResponseEntity.ok().headers(headers).contentLength(file.contentLength()).contentType(MediaType.parseMediaType("application/octet-stream")).body(new InputStreamResource(file.getInputStream()));
	}

	/**
	 * 获取附件file对象集合
	 * 
	 * @param task_id
	 * @return
	 */
	private List<File> getFiles(String task_id) {
		// 附件model集合
		YJWYTaskFileModel[] fileModels = null;
		List<File> fileList = new ArrayList<>();
		if (null != task_id) {
			Query query = Query.from(YJWYTaskFileModel.META_ID);
			query.where(new Condition("task_id", QueryContents.TYPE_EQ, task_id));
			query.and(new Condition("pk_crop", QueryContents.TYPE_EQ, DeviceUtil.getPkCrop()));
			fileModels = taskFileBusinessService.query(query);
			String file_path = null;
			String file_name = null;
			String pathTemp = null;
			for (YJWYTaskFileModel yjwyTaskFileModel : fileModels) {
				file_path = yjwyTaskFileModel.getFile_path();
				file_name = yjwyTaskFileModel.getFile_name();
				if (null != file_path && null != file_name && !"".equals(file_path) && !"".equals(file_name)) {
					pathTemp = "D:/work-yy/EZFM/src/main/resources/resources/" + file_path + "/" + file_name;
					fileList.add(new File(pathTemp));
				}
			}
		}
		return fileList;
	}

	/**
	 * 根据附件file生成临时zip压缩文件
	 * 
	 * @param zipName
	 * @param zipPath
	 * @param fileList
	 * @throws IOException
	 */
	private void getZipFile(String zipName, String zipPath, List<File> fileList) throws IOException {
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath));
		// 遍历文件
		FileInputStream fis = null;
		byte[] bytes = new byte[1024];
		for (File file : fileList) {
			fis = new FileInputStream(file);
			zos.putNextEntry(new ZipEntry(System.currentTimeMillis() + "@" + file.getName()));
			int len;
			// 读入需要下载的文件的内容，打包到zip文件
			while ((len = fis.read(bytes)) > 0) {
				zos.write(bytes, 0, len);
			}
			fis.close();
			zos.closeEntry();
		}
		zos.close();
	}

}
