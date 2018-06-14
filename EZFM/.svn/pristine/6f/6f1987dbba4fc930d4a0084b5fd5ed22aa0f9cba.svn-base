package com.shareworx.ezfm.worktask.areadetails.fileUpload;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.shareworx.ezfm.problem.file.model.ProblemFileModel;
import com.shareworx.ezfm.utils.UserUtil;

public class FilesUpload {
	// endpoint以杭州为例，其它region请按实际情况填写
	String endpoint = "oss-cn-beijing.aliyuncs.com";
	// accessKey请登录https://ak-console.aliyun.com/#/查看
	String accessKeyId = "LTAIl2i8v7vXITug";
	String accessKeySecret = "mHJvj1ppuBnvbxGFFHi4GU04wU8VuE";
	// BucketName
	String bucketName = "ezfmcloud";
	// 文件上传路径
	String filePath = "upload/";
	// 服务器路径
	String serverPath = "http://ezfmcloud.oss-cn-beijing.aliyuncs.com/";

	/*
	 * 私有 只允许自己读写操作，其他用户没有权限
	 */
	CannedAccessControlList acl_private = CannedAccessControlList.Private;

	/*
	 * 公共读写 允许自己和其他用户读写操作
	 */
	CannedAccessControlList acl_pub_readwrite = CannedAccessControlList.PublicReadWrite;

	/*
	 * 公共读 只允许自己进行写操作，但是允许自己及其他用户进行读操作
	 */
	CannedAccessControlList acl_pub_red = CannedAccessControlList.PublicRead;

	/**
	 * 后台工单报事附件上传OSS
	 *
	 * @param file
	 *            文件对象
	 * @param tabName
	 *            关联表名称
	 * @param correlationId
	 *            关联数据ID
	 * @return
	 * @throws IOException
	 * @throws ClientException
	 * @throws OSSException
	 */
	public ProblemFileModel upload(MultipartFile file, String tabName, String record_id, String create_time,
								   String crop) {
		// 上传文件，文件名随机生成
		String fileName = file.getOriginalFilename();
		fileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf(".") - 1, fileName.length());
		// 创建文件路径
		String newPath = filePath + tabName + "/" + record_id + "/" + fileName;
		// 上传文件流
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		boolean exists = ossClient.doesBucketExist("ezfmcloud"); //指定Bucket名称
		if(exists==false){
			// key1创建名为saas01的BUCKET，权限为私有
			createBucket(ossClient, "ezfmcloud", CannedAccessControlList.PublicReadWrite);
		}
		try {
			ossClient.putObject(bucketName, newPath, file.getInputStream());
			// 关闭client
			ossClient.shutdown();
		} catch (OSSException | ClientException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 实例化文件记录实例
		ProblemFileModel fileModel = new ProblemFileModel();
		// 填充文件名
		fileModel.setFile_name(file.getName());
		// 填充文件路径
		fileModel.setFile_path(serverPath + newPath);
		// 填充文件类型
		fileModel.setFile_type("1");
		// 填充文件大小
		fileModel.setFile_size(file.getSize() + "");
		// 文件所属表名
		fileModel.setTable_name(tabName);
		// 文件记录ID
		fileModel.setRecord_id(record_id);
		// 上传人
		fileModel.setCreate_user(UserUtil.getCurrentUserPk());
		// 上传时间
		fileModel.setCreate_time(create_time);
		// 所属公司
		fileModel.setPk_crop(crop);
		return fileModel;
	}
	
	
	public String copyUpload(String tabName, String pk, String url) {
		// 上传文件，文件名随机生成
		String[] array = url.split("/");
		String fileName = array[array.length-1];
		String oldPath = url.replaceFirst(serverPath, "");
		fileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf(".") - 1, fileName.length());
		// 创建文件路径
		String newPath = filePath + tabName + "/" + pk + "/" + fileName;
		// 上传文件流
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		
		boolean exists = ossClient.doesBucketExist("ezfmcloud"); //指定Bucket名称
		if(exists==false){
			// key1创建名为saas01的BUCKET，权限为私有  
	        createBucket(ossClient, "ezfmcloud", CannedAccessControlList.PublicReadWrite);
		}
		try {
			ossClient.putObject(bucketName, newPath, ossClient.getObject(bucketName, oldPath).getObjectContent());
			// 关闭client
			ossClient.shutdown();
		} catch (OSSException | ClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 所属公司
		return  serverPath + newPath;
	}

	/**
	 * 后台工单报事附件上传OSS
	 * 
	 * @param file
	 *            文件对象
	 * @param tabName
	 *            关联表名称
	 * @param correlationId
	 *            关联数据ID
	 * @return
	 * @throws IOException
	 * @throws ClientException
	 * @throws OSSException
	 */
	public String upload(MultipartFile file, String tabName ,String pk) {
		// 上传文件，文件名随机生成
		String fileName = file.getOriginalFilename();
		fileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf(".") - 1, fileName.length());
		// 创建文件路径
		String newPath = filePath + tabName + "/" + pk + "/" + fileName;
		// 上传文件流
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		boolean exists = ossClient.doesBucketExist("ezfmcloud"); //指定Bucket名称
		if(exists==false){
			// key1创建名为saas01的BUCKET，权限为私有  
	        createBucket(ossClient, "ezfmcloud", CannedAccessControlList.PublicReadWrite);
		}
		try {
			ossClient.putObject(bucketName, newPath, file.getInputStream());
			// 关闭client
			ossClient.shutdown();
		} catch (OSSException | ClientException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return serverPath + newPath;
	}

	/**
	 * 后台工单报事附件上传OSS
	 *
	 * @param file
	 *            文件对象
	 * @param tabName
	 *            关联表名称
	 * @param correlationId
	 *            关联数据ID
	 * @return
	 * @throws IOException
	 * @throws ClientException
	 * @throws OSSException
	 */
	public ProblemFileModel upload(MultipartFile file, String tabName, String record_id, String create_time,
								   String crop,String pk_user) {


		
		// 上传文件，文件名随机生成
		String fileName = file.getOriginalFilename();

		fileName = System.currentTimeMillis() +".png";
		// 创建文件路径
		String newPath = filePath + tabName + "/" + record_id + "/" + fileName;
		// 上传文件流
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		boolean exists = ossClient.doesBucketExist("ezfmcloud"); //指定Bucket名称
		if(exists==false){
			// key1创建名为saas01的BUCKET，权限为私有
			createBucket(ossClient, "ezfmcloud", CannedAccessControlList.PublicReadWrite);
		}
		try {
			ossClient.putObject(bucketName, newPath, file.getInputStream());
			// 关闭client
			ossClient.shutdown();
		} catch (OSSException | ClientException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 实例化文件记录实例
		ProblemFileModel fileModel = new ProblemFileModel();
		// 填充文件名
		fileModel.setFile_name(file.getName());
		// 填充文件路径
		fileModel.setFile_path(serverPath + newPath);
		// 填充文件类型
		fileModel.setFile_type("1");
		// 填充文件大小
		fileModel.setFile_size(file.getSize() + "");
		// 文件所属表名
		fileModel.setTable_name(tabName);
		// 文件记录ID
		fileModel.setRecord_id(record_id);
		// 上传人
		fileModel.setCreate_user(pk_user);
		// 上传时间
		fileModel.setCreate_time(create_time);
		// 所属公司
		fileModel.setPk_crop(crop);
		return fileModel;
	}
	/**
	 * 判断文件是否为图片
	 * 
	 * @param file
	 * @return
	 */
	public boolean isImage(MultipartFile mFile) {
		CommonsMultipartFile cf = (CommonsMultipartFile) mFile;
		DiskFileItem fi = (DiskFileItem) cf.getFileItem();
		File file = fi.getStoreLocation();

		boolean flag = false;
		try {
			ImageInputStream is = ImageIO.createImageInputStream(file);
			if (null != is) {
				Iterator<ImageReader> iter = ImageIO.getImageReaders(is);
				if (!iter.hasNext()) {// 文件不是图片
					return flag;
				}
			} else {
				return flag;
			}
			is.close();
		} catch (Exception e) {
			return flag;
		}
		return flag;
	}

	public static void createBucket(OSSClient client, String bucketName, CannedAccessControlList acl) {

		/* 通过一个Bucket对象来创建 */
		CreateBucketRequest bucketObj = new CreateBucketRequest(null);// 构造函数入参为Bucket名称，可以为空
		bucketObj.setBucketName(bucketName);// 设置bucketObj名称
		bucketObj.setCannedACL(acl);// 设置bucketObj访问权限acl
		client.createBucket(bucketObj);// 创建Bucket

	}
}
