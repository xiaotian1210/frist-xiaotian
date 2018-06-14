package com.shareworx.ezfm.utils;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.shareworx.platform.util.DateTimeUtil;

public class ChargePaymentUtil {
	
	public static final int DEFAULTAUTHORIZE = 10;
	public static final BigDecimal DEFAULTBASE = new BigDecimal(100);
	
	/**
	 * 获取目前日期加amount天后的截止日期 
	 * @return deadline
	 */
	public static String getDeadlineStr(Date date , int amount){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE , amount);
		String deadline = DateTimeUtil.getDateTime(calendar.getTime());
		return deadline;
		
	}
	
	/**
	 * 获取目前日期加time毫秒后的截止日期 
	 * @return deadline
	 */
	public static String getDeadlineStr(String dateString , long time){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(DateTimeUtil.formatDate(dateString, DateTimeUtil.LONGFORMAT).getTime()+time);
		String deadline = DateTimeUtil.getDateTime(calendar.getTime());
		return deadline;
		
	}
	
	/**
	 * 获取目前日期加amount天后的截止日期 
	 * @return deadline
	 */
	public static String getDeadlineStr(int amount){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE , amount);
		String deadline = DateTimeUtil.getDateTime(calendar.getTime());
		return deadline;
		
	}
	
	/**
	 * 获取目前日期加time毫秒后的截止日期 
	 * @return deadline
	 */
	public static String getDeadlineStr(long time){
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis()+time);
		String deadline = DateTimeUtil.getDateTime(calendar.getTime());
		return deadline;
		
	}
	
	/**
	 * 获取目前日期到截止日期的时间毫秒
	 * @return time
	 */
	public static long getSurplusTime(String deadline){

		Calendar calendar = Calendar.getInstance();
		Date deadlineDate = DateTimeUtil.formatDate(deadline, DateTimeUtil.LONGFORMAT);
		calendar.setTime(deadlineDate);
		long btime = System.currentTimeMillis();
		long etime = calendar.getTimeInMillis();

		return btime>=etime? 0l : (etime - btime);
		
	}
	
	/**
	 * 获取余额
	 * @return surplusPrice
	 */
	public static BigDecimal getSurplusPrice(String deadline , BigDecimal base , int authorizeCount){
		
		return getTimeToPrice(getSurplusTime(deadline),base,authorizeCount);
		
	}
	
	/**
	 * 费用转换成时间
	 * @return PaymentTime
	 */
	public static long getPriceToTime(BigDecimal price , BigDecimal base , int authorizeCount){

		BigDecimal temp = base.multiply(new BigDecimal(authorizeCount));
		return price.divide(temp , 3 , BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(2592000000l)).longValue();
		
	}
	
	public static long getBetweenTime(String begin , String end){
		 long b = DateTimeUtil.formatDate(begin, DateTimeUtil.LONGFORMAT).getTime();
		 long e = DateTimeUtil.formatDate(end, DateTimeUtil.LONGFORMAT).getTime();
		 
		 return (e-b > 0l)?(e-b):0l;
	}
	
	/**
	 * 时间转换成费用
	 * @return PaymentTime
	 */
	public static BigDecimal getTimeToPrice(long time , BigDecimal base , int authorizeCount){

		BigDecimal price = new BigDecimal(time);
		price = price.multiply(base).multiply(new BigDecimal(authorizeCount)).divide(new BigDecimal(2592000000l), 2, BigDecimal.ROUND_HALF_UP);;
		return price;
		
	}
	
	public static BigDecimal getChargeBase(int authorizeCount){
		
		if(authorizeCount<=10)
			return DEFAULTBASE;
		
		if(authorizeCount<=100)
			return new BigDecimal(50);
		
		if(authorizeCount<=500)
			return new BigDecimal(100);
		
		return new BigDecimal(200);
		
	}

}
