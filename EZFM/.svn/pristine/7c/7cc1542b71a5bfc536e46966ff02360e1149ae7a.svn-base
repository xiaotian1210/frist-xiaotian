package com.shareworx.ezfm.files.action;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.files.service.YJWYFileService;
import com.shareworx.ezfm.files.vo.YJWYFileVo;

/**
 * 附件操作控制器
 * 
 * @author jin.li
 *
 */
@Controller
@RequestMapping("ezfm/file")
public class YJWYFileAction {

	final static Logger log = Logger.getLogger(YJWYFileAction.class);

	/** 跳转页面 */
	public final static String FILE = "ezfm/files/pic_list";

	@Autowired
	@Qualifier(YJWYFileService.ID)
	private YJWYFileService fileService;

	public void setFileService(YJWYFileService fileService) {
		this.fileService = fileService;
	}

	/**
	 * 转向列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView listForward() {
		return new ModelAndView(FILE);
	}

	/**
	 * 查询操作
	 * 
	 * @return
	 */
	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody ModelAndResult query(@RequestBody YJWYFileVo vo) {
		// 条件获取
		String query_table = vo.getQuery_table();
		String table_name = vo.getTable_name();
		String record_id = vo.getRecord_id();
		String flag = vo.getFlag();
		ModelAndResult mar = new ModelAndResult();
		List<Map<String, Object>> list = null;
		// 条件判断
		if (null != query_table && null != table_name && null != record_id) {
			// 拼接附件查询sql
			StringBuilder sql = new StringBuilder();
			if ("leave".equals(flag)) {
				sql.append("select create_time_ as create_time, create_user_ as create_user, " + "file_id_ as file_id, file_name_ as file_name, file_path_ as file_path, " + "file_size_ as file_size, file_type_ as file_type, pk_crop_ as pk_crop, " + "record_id_ as record_id,  table_name_ as table_name from ");
				sql.append(query_table + " ");
				sql.append("where table_name_ = '" + table_name + "' ");
				sql.append("and record_id_ = '" + record_id + "' ");
			} else {
				sql.append("select * from ");
				sql.append(query_table.toString() + " ");
				sql.append("where table_name = '" + table_name + "' ");
				sql.append("and record_id = '" + record_id + "' ");
			}
			list = fileService.queryList(sql.toString());
		}
		mar.setAttribute("rows", list);
		return mar;
	}

	/**
	 * 附件下载
	 * 
	 * @param file_id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "download", method = RequestMethod.GET)
	public @ResponseBody ModelAndResult downloadFile(YJWYFileVo vo) throws IOException {
		String file_id = vo.getFile_id();
		String query_table = vo.getQuery_table();
		String flag = vo.getFlag();
		if (DeviceUtil.stringIsEmpty(file_id) || DeviceUtil.stringIsEmpty(query_table)) {
			return new ModelAndResult(false, "下载失败，文件未找到！");
		}
		// 查询附件model
		StringBuilder sql = new StringBuilder();
		if ("leave".equals(flag)) {
			sql.append("select create_time_ as create_time, create_user_ as create_user, " + "file_id_ as file_id, file_name_ as file_name, file_path_ as file_path, " + "file_size_ as file_size, file_type_ as file_type, pk_crop_ as pk_crop, " + "record_id_ as record_id,  table_name_ as table_name from ");
			sql.append(query_table + " ");
			sql.append("where file_id_='" + file_id + "' ");
		} else {
			sql.append("select * from ");
			sql.append(query_table + " ");
			sql.append("where file_id='" + file_id + "' ");
		}
		List<Map<String, Object>> list = fileService.queryList(sql.toString());
		String path = list.get(0).get("file_path").toString();
		String file_name = path.substring(path.lastIndexOf("/") + 1);
		return this.downLoad(path, file_name);
	}

	/**
	 * 下载
	 * 
	 * @param path
	 * @param name
	 * @return
	 */
	private ModelAndResult downLoad(String path, String name) {
		ServletOutputStream out = null;
		URL url = null;
		URLConnection conn = null;
		InputStream is = null;
		try {
			url = new URL(path);
			conn = url.openConnection();
			is = conn.getInputStream();
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + name + "\";");
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
			return new ModelAndResult();
		} catch (IOException e) {
			e.printStackTrace();
			return new ModelAndResult(false, e.getLocalizedMessage());
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
