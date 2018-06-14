package com.shareworx.ezfm.system.crop.pre.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.shareworx.ezfm.system.crop.file.model.CropFileModel;
import com.shareworx.ezfm.system.crop.file.service.CropFileBusinessService;
import com.shareworx.ezfm.system.crop.pre.model.PreCropModel;
import com.shareworx.ezfm.system.crop.pre.vo.PreCropVo;
import com.shareworx.ezfm.worktask.areadetails.fileUpload.FilesUpload;
import com.shareworx.platform.exception.ShareworxServiceException;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.DateTimeUtil;

/**
 * 企业信息预采集实体业务操作实现
 * @author zhentong.jia
 * @version since Shareworx platform 3.0
 *
 */
@Service(PreCropBusinessService.ID)
public class PreCropBusinessServiceImpl implements PreCropBusinessService {

	
	
	
	@Autowired
	@Qualifier(PreCropDomainService.ID)
	private PreCropDomainService domainService;
	
	public void setDomainService(PreCropDomainService domainService) {
		this.domainService = domainService;
	}
	
	@Autowired
	@Qualifier(CropFileBusinessService.ID)
	private CropFileBusinessService fileService;
	
	public void setFileService(CropFileBusinessService fileService) {
		this.fileService = fileService;
	}
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropBusinessService#query(com.shareworx.platform.persist.Query)
	 */
	@Override
	public PreCropModel[] query(Query query) throws ShareworxServiceException {
		List<PreCropModel> list = domainService.queryListByCondition(query);
		if(ArrayUtils.isEmpty(list)){
			return new PreCropModel[0];
		}
		return list.toArray(new PreCropModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropBusinessService#load(com.shareworx.ezfm.system.crop.pre.model.PreCropModel)
	 */
	@Override
	public PreCropModel[] load(PreCropModel model) throws ShareworxServiceException {
		List<PreCropModel> list = domainService.queryByExample(model);
		if(ArrayUtils.isEmpty(list)){
			return new PreCropModel[0];
		}
		return list.toArray(new PreCropModel[0]);
	}

	@Override
	public PreCropModel[] apply(PreCropVo vo) throws ShareworxServiceException {
		PreCropModel model = new PreCropModel();
		
		String time = DateTimeUtil.getSysdateTime();
		
		model.setCode(vo.getCode()); 
		model.setName(vo.getName());
		model.setAddress(vo.getAddress());
		model.setPhone(vo.getPhone());
		model.setPost_code(vo.getPost_code());
		model.setContact(vo.getContact());
		model.setEmail(vo.getEmail());
		model.setApply_time(time);
		model.setCheck_state("待审核");
		
		List<PreCropModel> list = domainService.save(model);
		//上传附件
		if (vo.getCrop_file()!=null&&!vo.getCrop_file().isEmpty()) {
			FilesUpload fileUpload = new FilesUpload();
			//保存到OSS
			String path = fileUpload.upload(vo.getCrop_file(), PreCropModel.META_ID, list.get(0).getPk_pre_crop());
			
			// 实例化文件记录实例
			CropFileModel fileModel = new CropFileModel();
			// 填充文件名
			String[] array = path.split("/");
			fileModel.setFile_name(array[array.length-1]);
			// 填充文件路径
			fileModel.setFile_path(path);
			// 填充文件类型
			fileModel.setFile_type("1");
			// 填充文件大小
			fileModel.setFile_size(vo.getCrop_file().getSize() + "");
			// 文件所属表名
			fileModel.setTable_name("yjwy_pre_crop");
			// 企业代码
			fileModel.setCrop_code(vo.getCode());
			// 上传时间
			fileModel.setCreate_time(time);
			// 所属公司
			fileModel.setPk_crop(list.get(0).getPk_pre_crop());
			// 记录ID
			fileModel.setRecord_id(list.get(0).getPk_pre_crop());
			//保存图片信息
			fileService.save(new CropFileModel[]{fileModel});
		}

		return list.toArray(new PreCropModel[0]);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropBusinessService#save(com.shareworx.ezfm.system.crop.pre.model.PreCropModel[])
	 */
	@Override
	public PreCropModel[] save(PreCropModel[] models) throws ShareworxServiceException {
		List<PreCropModel> list = domainService.save(models);
		return list.toArray(new PreCropModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropBusinessService#update(com.shareworx.ezfm.system.crop.pre.model.PreCropModel[])
	 */
	@Override
	public PreCropModel[] update(PreCropModel[] models) throws ShareworxServiceException {
		List<PreCropModel> list = domainService.update(models);
		return list.toArray(new PreCropModel[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see com.shareworx.ezfm.system.crop.pre.service.PreCropBusinessService#delete(com.shareworx.ezfm.system.crop.pre.model.PreCropModel[])
	 */
	@Override
	public PreCropModel[] delete(PreCropModel[] models) throws ShareworxServiceException {
		domainService.delete(models);
		return models;
	}


}
