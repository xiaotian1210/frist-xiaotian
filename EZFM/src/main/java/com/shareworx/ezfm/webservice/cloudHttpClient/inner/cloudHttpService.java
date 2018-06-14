package com.shareworx.ezfm.webservice.cloudHttpClient.inner;

public interface cloudHttpService {
	
	String ID="cloudHttpService";
	
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
	String submitEvent(String pk_crop,String request_source, String user_id, String project_id, String service_id, String record_type, String remarks, String contack, String telephone, String location,String WorkTaskId,String yeZhuId);

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
	String eventList(String user_id, String project_id, String order_type, String record_type, int page_num, int per_size);
	
	/**
	 * send post to 易彩区后台工单状态
	 * @param param
	 * @param Property_order_id
	 * @param pem_id
	 * @return
	 */
	void sendCloudpost(String Status,String Property_order_id,String pem_id);
		
}
