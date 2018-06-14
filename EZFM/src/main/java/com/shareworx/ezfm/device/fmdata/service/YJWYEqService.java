package com.shareworx.ezfm.device.fmdata.service;

import java.util.List;

import com.shareworx.ezfm.device.fmdata.model.YJWYEqModel;
import com.shareworx.platform.exception.ShareworxServiceException;

/**
 * 设备信息操作接口
 * 
 * @author jin.li
 *
 */
public interface YJWYEqService {
	String ID = "yJWYEqService";

	/**
	 * 根据机房id查询设备eq
	 * 
	 * @param pk_project
	 * @param eq_name
	 * @return
	 */
	List<YJWYEqModel> queryEqModels(String rm_id);

	int[] saveModels(YJWYEqModel[] yjwyEqModels);

	YJWYEqModel[]  saveModelsByDv(YJWYEqModel[] yjwyEqModels);
	/**
	 * 修改设备设施基本信息
	 * 
	 * @param models
	 * @return
	 * @throws ShareworxServiceException
	 */
	int[] updateModels(YJWYEqModel[] models) throws ShareworxServiceException;

	YJWYEqModel[] updateModelsByDv(YJWYEqModel[] models) throws ShareworxServiceException;

}
