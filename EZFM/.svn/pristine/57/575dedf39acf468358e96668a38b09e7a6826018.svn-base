package com.shareworx.ezfm.webservice.callcenter.inner;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSONArray;
import com.shareworx.platform.persist.Condition;
import com.shareworx.platform.persist.Query;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.ArrayUtils;
import com.shareworx.platform.util.DateTimeUtil;
import com.shareworx.platform.util.MD5Utils;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.baseinfo.user.service.YJWYUserBusinessService;
import com.shareworx.ezfm.device.util.DeviceUtil;
import com.shareworx.ezfm.login.commons.LoginCommons;
import com.shareworx.ezfm.system.crop.model.CropModel;
import com.shareworx.ezfm.system.crop.service.CropBusinessService;

/**
 * 呼叫中心业务实现
 * 
 * @author jin.li
 *
 */
public class CallCenterDataServiceImpl implements CallCenterDataService {

	final static Logger log = Logger.getLogger(CallCenterDataServiceImpl.class);

	@Autowired
	@Qualifier(YJWYUserBusinessService.ID)
	private YJWYUserBusinessService userBusinessService;

	public void setUserBusinessService(YJWYUserBusinessService userBusinessService) {
		this.userBusinessService = userBusinessService;
	}

	@Autowired
	@Qualifier(CropBusinessService.ID)
	private CropBusinessService cropBusinessService;

	public void setCropBusinessService(CropBusinessService cropBusinessService) {
		this.cropBusinessService = cropBusinessService;
	}

	@Override
	public String getTest(String test) {
		return "接口测试" + test;
	}

	@Override
	public String pushCallCenterUsersInfo(String json) {
		if (StringUtils.isEmpty(json)) {
			return this.getResultJson(2, "数据格式有误", null, null);
		}
		try {
			// 转为json集合
			JSONArray array = JSONArray.parseArray(json);
			Map<String, Object> map = null;
			// 取值
			String crop_code = null;
			List<YJWYUserModel> userList = null;
			String temp = null;
			for (int i = 0; i < array.size(); i++) {
				map = array.getJSONObject(i);
				temp = (String) map.get("crop_code");
				if (!StringUtils.isEmpty(temp)) {
					// 取出企业编码
					crop_code = temp;
					continue;
				}
				userList = JSONArray.parseArray(map.get("users").toString(), YJWYUserModel.class);
			}
			// 判断数据合法
			if (StringUtils.isEmpty(crop_code) || userList == null || userList.size() == 0) {
				return this.getResultJson(2, "数据格式有误：企业编码/用户信息不能为空", null, userList);
			}
			CropModel[] cropModels = cropBusinessService.query(Query.from(CropModel.META_ID).where(new Condition("crop_code_", QueryContents.TYPE_EQ, crop_code)));
			if (!DeviceUtil.arrayIsNotEmpty(cropModels)) {
				return this.getResultJson(2, "数据格式有误：企业编码不正确，请检查后重试", null, userList);
			}
			// 获取pk_crop
			String pk_crop = cropModels[0].getPk_crop();
			List<YJWYUserModel> userModels = new ArrayList<>();
			List<YJWYUserModel> errorModels = new ArrayList<>();
			// 用于判断导入表内是否数据重复
//			Set<String> em_codes = new HashSet<>();
//			Set<String> user_codes = new HashSet<>();
			// 设置用户model
			for (YJWYUserModel userModel : userList) {
				// 防止空白行
				if (userModel == null) {
					continue;
				}
				if (StringUtils.isEmpty(userModel.getUser_name())) {
					userModel.setAttribute("info", "数据格式有误：员工姓名不能为空，请检查");
					errorModels.add(userModel);
					continue;
//					return this.getResultJson(2, "数据格式有误：员工姓名不能为空，请检查", null);
				}
				if (StringUtils.isEmpty(userModel.getEm_code())) {
					userModel.setAttribute("info", "数据格式有误：员工工号不能为空，请检查");
					errorModels.add(userModel);
					continue;
//					return this.getResultJson(2, "数据格式有误：员工工号不能为空，请检查", null);
				}
				if (StringUtils.isEmpty(userModel.getUser_code())) {
					userModel.setAttribute("info", "数据格式有误：员工账号不能为空，请检查");
					errorModels.add(userModel);
					continue;
//					return this.getResultJson(2, "数据格式有误：员工账号不能为空，请检查", null);
				}
//				em_codes.add(userModel.getEm_code());
//				user_codes.add(userModel.getUser_code());
				if (!StringUtils.isEmpty(userModel.getPhone())) {
					if (!DeviceUtil.isMobile(userModel.getPhone())) {
						userModel.setAttribute("info", "数据格式有误：员工电话格式不正确，请检查");
						errorModels.add(userModel);
						continue;
//						return this.getResultJson(2, "数据格式有误：员工电话格式不正确，请检查", null);
					}
				}
				if (!StringUtils.isEmpty(userModel.getEmail())) {
					if (!DeviceUtil.isEmail(userModel.getEmail())) {
						userModel.setAttribute("info", "数据格式有误：员工邮箱格式不正确，请检查");
						errorModels.add(userModel);
						continue;
//						return this.getResultJson(2, "数据格式有误：员工邮箱格式不正确，请检查", null);
					}
				}
				if (!doValidateEmCode(userModel.getEm_code(), userModel.getPk_user())) {
					userModel.setAttribute("info", "数据格式有误：员工工号已存在，请更换工号");
					errorModels.add(userModel);
					continue;
//					return this.getResultJson(2, "数据格式有误：员工工号已存在，请更换工号", null);
				}
				if (!doValidateUserCode(userModel.getUser_code(), userModel.getPk_user())) {
					userModel.setAttribute("info", "数据格式有误：员工账号已存在，请更换账号");
					errorModels.add(userModel);
					continue;
//					return this.getResultJson(2, "数据格式有误：员工账号已存在，请更换账号", null);
				}
				if (LoginCommons.QYUSERCODE.equals(userModel.getUser_code())) {
					userModel.setAttribute("info", "数据格式有误：员工账号已存在，请更换账号");
					errorModels.add(userModel);
					continue;
//					return this.getResultJson(2, "数据格式有误：员工账号已存在，请更换账号", null);
				} else if (LoginCommons.DMSUSERCODE.equals(userModel.getUser_code())) {
					userModel.setAttribute("info", "数据格式有误：员工账号已存在，请更换账号");
					errorModels.add(userModel);
					continue;
//					return this.getResultJson(2, "数据格式有误：员工账号已存在，请更换账号", null);
				}
				/* model赋值 */
				userModel.setPk_crop(pk_crop);
				userModel.setPassword(MD5Utils.getMD5String("111111"));
				userModel.setIs_pre(false);
				userModel.setIs_able(true);
				userModel.setDelete_flag("0");
				userModel.setCreate_time(DateTimeUtil.getTimestampStr());
				userModel.setIs_sign("0");
				userModels.add(userModel);
			}
//			if (em_codes.size() != userList.size()) {
//				return this.getResultJson(2, "数据格式有误：请求数据中员工工号重复，请检查", null, userList);
//			}
//			if (user_codes.size() != userList.size()) {
//				return this.getResultJson(2, "数据格式有误：请求数据中员工工号重复，请检查", null, userList);
//			}
			// 持久化
			YJWYUserModel[] resultUsers = userBusinessService.save(userModels.toArray(new YJWYUserModel[] {}));
			if (!ArrayUtils.isEmpty(errorModels)) {
				return this.getResultJson(2, "部分提交成功，错误数据请查看error，错误信息查看info。", resultUsers, errorModels);
			}
			return this.getResultJson(1, "提交成功", resultUsers, errorModels);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("呼叫中心用户上传出错", e);
			return this.getResultJson(3, "服务器端出错", null, null);
		}
	}

	/**
	 * 获取json字符串
	 * 
	 * @param result
	 *            返回码标识1.提交成功;2.数据格式有误;3.服务端出错
	 * @param msg
	 *            返回结果说明
	 * @param userModels
	 *            成功推送的用户数据
	 * @return
	 */
	private String getResultJson(int result, String msg, YJWYUserModel[] userModels, List<YJWYUserModel> errorModels) {
		List<Map<String, Object>> resultList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		// 时间戳
		Long ts = new Date().getTime();
		map.put("ts", ts);
		map.put("result", result);
		map.put("msg", msg);
		if (DeviceUtil.arrayIsNotEmpty(userModels)) {
			map.put("data", userModels);
		} else {
			map.put("data", new YJWYUserModel[0]);
		}
		map.put("error", errorModels);
		resultList.add(map);
		return JSONArray.toJSONString(resultList);
	}

	/**
	 * 判断工号是否重复
	 * 
	 * @param code
	 * @param id
	 * @return
	 */
	private boolean doValidateEmCode(String code, String id) {
		if (!StringUtils.isEmpty(id)) {
			YJWYUserModel model = userBusinessService.query(Query.from(YJWYUserModel.META_ID).and(Condition.create("pk_user_", id)))[0];
			if (code.equals(model.getEm_code())) {
				return true;
			}
		}

		YJWYUserModel[] models = userBusinessService.query(Query.from(YJWYUserModel.META_ID).and(Condition.create("em_code_", code)));
		if (null != models && models.length > 0) {
			return false;
		}

		return true;
	}

	/**
	 * 判断用户账号是否重复
	 * 
	 * @param code
	 * @param id
	 * @return
	 */
	public boolean doValidateUserCode(String code, String id) {
		if (!StringUtils.isEmpty(id)) {
			YJWYUserModel model = userBusinessService.query(Query.from(YJWYUserModel.META_ID).and(Condition.create("pk_user_", id)))[0];
			if (code.equals(model.getUser_code())) {
				return true;
			}
		}

		YJWYUserModel[] models = userBusinessService.query(Query.from(YJWYUserModel.META_ID).and(Condition.create("user_code_", code)));
		if (null != models && models.length > 0) {
			return false;
		}

		return true;
	}

}
