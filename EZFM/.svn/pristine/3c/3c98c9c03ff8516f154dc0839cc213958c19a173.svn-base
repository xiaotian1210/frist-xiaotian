package com.shareworx.ezfm.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期 工具
 * 
 * @author lingwei.li
 *
 */
public class AppDateUtils {

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate
	 *            较小的时间
	 * @param bdate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days))+1;
	}

	/**
	 * 将字符串类型的时间转换为Date类型
	 * 
	 * @param str
	 *            时间字符串
	 * @param pattern
	 *            格式
	 * @return 返回Date类型
	 */
	public static Date formatString(String str, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date time = null;
		// 需要捕获ParseException异常，如不要捕获，可以直接抛出异常，或者抛出到上层
		try {
			time = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * 获取当前系统日期
	 * 
	 * @return String 当前系统日期
	 */
	public static String getCurrentTimeEx() {
		final long now = System.currentTimeMillis();
		final Date currentTime = new Date(now);
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final String dateString = formatter.format(currentTime);
		return dateString;
	}

}
