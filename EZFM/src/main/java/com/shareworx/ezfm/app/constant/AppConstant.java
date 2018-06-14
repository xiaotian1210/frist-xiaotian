package com.shareworx.ezfm.app.constant;

import java.text.SimpleDateFormat;

public class AppConstant {

	/**format 年月日时分秒*/
	public static SimpleDateFormat df_YMDHMS=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**format 年月日时分*/
	public static SimpleDateFormat df_YMDHM=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	/**format 年月日*/
	public static SimpleDateFormat df_YMD=new SimpleDateFormat("yyyy-MM-dd");
	
	
	/**
	 * 核查任务状态
	 * @author yuting.wang
	 *
	 */
	public final static class InspectTaskState {
		public final static String DB = "10";//待办
		public final static String DZG = "20";//待整改/问题跟踪
		public final static String COMPLETE = "30";//完成/已办
		public final static String ZCGQ = "40";//整改过期
		public final static String XJGB = "50";//休假关闭
		public final static String SDGB = "60";//手动关闭
	};
	/**
	 * 核查记录状态
	 * @author yuting.wang
	 *
	 */
	public final static class InspectRecordState {
		public final static String COMPLETE = "0";//合格完成
		public final static String PFZG = "1";//派发（整改）
		public final static String ZGWC = "2";//整改完成
		public final static String ZGQR = "3";//整改确认
	};
	
	/**
	 * 任务生成方式
	 * @author yuting.wang
	 *
	 */
	public final static class CreateTaskBy {
		public final static String SYSTEM = "0";//0:达美盛资产云后台系统生成
		public final static String MOBILE = "1";//1：手机终端核查任务
	};
	
	/**
	 * 休假操作步骤  操作  0，1，2，3已撤销 
	 * @author yuting.wang
	 *
	 */
	public final static class LeaveOperation {
		public final static String PENDING = "0";//待审批
		public final static String PASS = "1";//审批通过
		public final static String REJECT = "2";//审批拒绝
		public final static String REVOKED = "3";//已撤销 
	};
	
	
	//报事状态 
	/**未处理*/
	public static String PROBLEM_STATE_1 = "1";
	/**处理中*/
	public static String PROBLEM_STATE_2 = "2";
	/**处理完成*/
	public static String PROBLEM_STATE_3 = "3";
	/**已关闭*/
	public static String PROBLEM_STATE_4 = "4";
	/**已拒单*/
	public static String PROBLEM_STATE_5 = "5";
	/**已取消*/
	public static String PROBLEM_STATE_6 = "6";
	/**已删除*/
	public static String PROBLEM_STATE_7 = "7";
	/**待单中*/
	public static String PROBLEM_STATE_8 = "8";
	
	/**
	 * 工单状态
	 * @author yuting.wang
	 *
	 */
	public final static class WorktaskState {
		/**未派单 */
		public final static int NOTSEND= 0;
		/**待接单 */
		public final static int PENDING= 1;
		/**维修中 */
		public final static int REPAIRING= 2;
		/**完成 */
		public final static int COMPLETE= 3;
		/**已拒单 */
		public final static int REFUSED= 4;
		/**已取消 */
		public final static int CANCEL= 5;
	}
	
	/**
	 * 工单操作符
	 * @author yuting.wang
	 *
	 */
	public final static class WorktaskOperator {
		/**开单*/
		public final static int OPERATOR_1= 1;
		/**抢单 */
		public final static int OPERATOR_2= 2;
		/**接单 */
		public final static int OPERATOR_3= 3;
		/**拒单 */
		public final static int OPERATOR_4= 4;
		/**取消 */
		public final static int OPERATOR_5= 5;
		/**完成 */
		public final static int OPERATOR_6= 6;
		/**回访 */
		public final static int OPERATOR_7= 7;
		/**报修 */
		public final static int OPERATOR_8= 8;
		/**处理 */
		public final static int OPERATOR_9= 9;
		
	}
	

	
	//签到状态
	/**签退*/
	public static String SIGN_STATE_0 = "0";
	/**签到*/
	public static String SIGN_STATE_1 = "1";
	
	/**
	 * 巡检保养 任务状态 
	 * @author lingwei.li
	 *
	 */
	public final static class PatrolTaskState {
		/**未完成*/
		public final static String UNFINISH= "0";
		/**已完成*/
		public final static String FINISH = "1";
		/**已过期*/
		public final static String EXPIRE = "2";
		/**转发*/
		public final static String FORWARD = "3";
		/**保养中*/
		public final static String MAINTAIN = "4";
		/**销单*/
		public final static String REVOKE = "5";
		/**未派单*/
		public final static String UNDISPATCH = "6";// 
	};
//=============================手机推送 pm 状态码============================================	
	/**核查管理*/
	public static String MODULE_QUALITYINSPECT = "1";
	/**核查管理功能编号*/
	public final static class QualityInspectFunction {
		/**待整改*/
		public final static String FUNCTION_101 = "101"; //待整改
	}
	/**报修管理*/
	public static String MODULE_WORKTASK = "2";
	/**报修功能编号*/
	public final static class WorkTaskFunction {
		/**工单池*/
		public final static String FUNCTION_201 = "201"; //工单池
		/**待办任务*/
		public final static String FUNCTION_202 = "202"; //待办任务
		/**超时工单*/
		public final static String FUNCTION_203 = "203"; //超时工单
	}
	/**报事管理*/
	public static String MODULE_PROBLEM = "3";
	/**报修功能编号*/
	public final static class ProblemFunction {
		/**待办任务*/
		public final static String FUNCTION_301 = "301"; //待办任务
	}
	
}
