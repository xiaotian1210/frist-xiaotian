package com.shareworx.ezfm.webservice.cloudHttpClient.inner;


import java.io.IOException;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;



@Controller
@RequestMapping("/sqpath")
public class SqpathController {
	
	public final String PM_FORWARD = "baseinfo/pmdownload";
	
	public final String DY_FORWARD = "baseinfo/dydownload";
	
	/**
	 * 附件下载
	 *
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "YJWY_FmData.zip", method = RequestMethod.GET)
	public ResponseEntity<InputStreamResource> downloadFile(HttpServletRequest reqParam, HttpServletResponse response) throws IOException {
        String zipName = "YJWY_FmData.zip";
		String zipPath = "/var/lib/ezfm/sqliteDB/download/" + zipName;
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
	 * 转向列表页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="PMandroid")
	public ResponseEntity<InputStreamResource>  PMandroid(HttpServletRequest reqParam, HttpServletResponse response) throws IOException{
		String zipPath = "/var/lib/ezfm/appdownload/PMandroid.apk";
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
	 * 转向列表页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="PMios")
	public ResponseEntity<InputStreamResource>  PMios(HttpServletRequest reqParam, HttpServletResponse response) throws IOException{
		String zipPath = "/var/lib/ezfm/appdownload/PMios.ipa";
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
	 * 转向列表页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="DYandroid")
	public ResponseEntity<InputStreamResource>  DYandroid(HttpServletRequest reqParam, HttpServletResponse response) throws IOException{
		String zipPath = "/var/lib/ezfm/appdownload/DYandroid.apk";
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
	 * 转向列表页面
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="DYios")
	public ResponseEntity<InputStreamResource> DYios(HttpServletRequest reqParam, HttpServletResponse response) throws IOException{
		String zipPath = "/var/lib/ezfm/appdownload/DYios.ipa";
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
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="pmforword", method=RequestMethod.GET)
	public ModelAndView listForward(){
		return new ModelAndView(PM_FORWARD);
	}
	
	/**
	 * 转向列表页面
	 * @return
	 */
	@RequestMapping(value="dyforword", method=RequestMethod.GET)
	public ModelAndView listForward1(){
		return new ModelAndView(DY_FORWARD);
	}
}
