package com.shareworx.ezfm.webservice.yjq.inner;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;


/**
 * 亿街区业务接口
 * 
 * @author jin.li
 *公司测试地址http://219.141.190.18:9090/yjwy/yjwy/webServices/yjq?wsdl
 *本机地址：http://localhost:8080/yjwy/webServices/yjq?wsdl
 *阿里云测试地址：http://123.57.30.243:8080/yjwy/webServices/yjq?wsdl
 *阿里云正式环境：http://123.57.30.221:8080/yjwy/webServices/yjq?wsdl
 */

@WebService(targetNamespace = "http://123.57.30.221:8080/yjwy/webServices/yjq?wsdl")
public interface YjqDataService {
	/**
	 * 测试方法
	 * 
	 * @param test
	 * @return
	 */
	@WebMethod
	@WebResult
	String getTest(String test);

	/**
	 *  获取用户信息
	 * @param dateTime 时间戳。格式：yyyy-MM-dd HH:mm:ss。返回此时间戳之后新增或变更的用户数据（为空则返回所有）
	 * @param user_id 用户id ；（为空判断dateTime字段，不为空返回当前ID用户信息）
	 * @return
	 */
	@WebMethod
	@WebResult
	String userInfo(String dateTime,String user_id);

	/**
	 * 获取所有区域信息
	 * 
	 * @return
	 */
	@WebMethod
	@WebResult
	String areaInfo();

	/**
	 * 获取项目信息
	 * 
	 * @param area_id
	 *            区域id（为空则返回所有项目）
	 * @param project_name
	 *            项目名称（模糊查询,可为空）
	 * @return
	 */
	String projectInfo(String area_id, String project_name);

	
	/**
	 * 业主认证房间获取楼栋
	 * 
	 * @param project_id
	 *            父类id，如果上级为项目，父类ID为项目ID
	 * @return
	 */
	String ownerResources(String parent_id);
	
	/**
	 * 获取报事报修分类及用户历史地址记录
	 * 
	 * @param user_id
	 *            用户id
	 * @param project_id
	 *            项目id
	 * @return
	 */
	String eventClassHistoryAddressInfo(String user_id, String project_id);

	/**
	 * 提交报事报修，同时更新历史地址记录
	 * @param request_source
	 *            来源
	 * @param user_id
	 *            用户id
	 * @param project_id
	 *            项目id
	 * @param service_id
	 *            服务分类id
	 * @param record_type
	 *            分类分类（1：报事报修， 2：表彰， 3：投诉）
	 * @param remarks
	 *            详情描述
	 * @param contack
	 *            联系人
	 * @param telephone
	 *            联系方式
	 * @param location
	 *            详细地址
	 * @return
	 */
	String submitEvent(String pk_crop,String request_source, String user_id, String project_id, String service_id, String record_type, String remarks, String contack, String telephone, String location);

	/**
	 * 提交报事报修附件信息
	 * @param user_id 提交用户
	 * @param pk_crop 所属公司
	 * @param order_id 订单ID
	 * @param file_path 附件路径
	 * @param file_name 附件名称
	 * @param file_size 附件大小
	 * @param file_type 附件类型（1.图片，2音频，3视频）
	 * @return
	 */
	String submitEventFile(String pk_crop,String order_id, String file_path, String file_name, 
			String file_size, String file_type);

	/**
	 *  提交报事报修评价，同时更新报事报修订单评价状态
	 * @param order_id 订单ID
	 * @param remarks 内容
	 * @param level 评价等级
	 * @return
	 */
	String submitEventEvaluation(String order_id,String remarks, String level);

	/**
	 * 提交报事报修评价附件信息
	 * @param user_id 提交用户
	 * @param pk_crop 所属公司
	 * @param order_id 订单ID
	 * @param file_path 附件路径
	 * @param file_name 附件名称
	 * @param file_size 附件大小
	 * @param file_type 附件类型（1.图片，2音频，3视频）
	 * @return
	 */
	String submitEventEvaluationFile(String pk_crop,String order_id, String file_path, String file_name, 
			String file_size, String file_type);

	/**
	 * 获取报事报修列表集合
	 * 
	 * @param user_id
	 *            用户id
	 * @param project_id
	 *            项目id
	 * @param order_type
	 *            状态分类（1：未完成，2：待评价，3：已完成）
	 * @param record_type
	 *            类型分类（1：报事报修， 2：表彰， 3：投诉，4：表彰+投诉，5：全部）
	 * @param page_num
	 *            页码
	 * @param per_size
	 *            页中条数
	 * @return
	 */
	String eventList(String user_id, String project_id, String order_type, String record_type, int page_num, int perg_size);

	/**
	 * 获取报事报修详情
	 * 
	 * @param order_id
	 *            订单id
	 * @return
	 */
	String eventDetails(String order_id);

	/**
	 * 获取报事报修评价详情
	 * 
	 * @param order_id
	 *            订单id
	 * @return
	 */
	String eventEvaluationDetails(String order_id);

	/**
	 * 获取报事报修进度详情（获取操作日志记录）
	 * 
	 * @param order_id
	 *            订单id
	 * @return
	 */
	String eventProgress(String order_id);

	/**
	 * 取消报事报修（更新报事报修状态，同时增加一条取消操作记录）
	 * 
	 * @param order_id
	 *            订单id
	 * @return
	 */
	String cancelEvent(String order_id);

}
