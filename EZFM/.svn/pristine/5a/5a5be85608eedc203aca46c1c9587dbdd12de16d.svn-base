package com.shareworx.ezfm.device.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.shareworx.platform.exception.ShareworxRuntimeException;
import com.shareworx.platform.model.ModelAndResult;
import com.shareworx.platform.persist.DomainModel;
import com.shareworx.platform.persist.QueryContents;
import com.shareworx.platform.util.StringUtils;
import com.shareworx.ezfm.baseinfo.user.model.YJWYUserModel;
import com.shareworx.ezfm.utils.MathUtils;
import com.shareworx.ezfm.utils.UserUtil;

/**
 * 工具类
 * 
 * @author jin.li
 *
 * @param <T>
 */
public class DeviceUtil {
	public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
	public static final String YMD = "yyyy-MM-dd";

	/**
	 * 电子邮箱验证
	 * 
	 * @param email
	 * @return 验证通过返回true
	 */
	public static boolean isEmail(String email) {
		boolean flag = false;
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(check);
		Matcher matcher = pattern.matcher(email);
		flag = matcher.matches();
		return flag;
	}

	/**
	 * 手机号验证
	 * 
	 * @param str
	 * @return 验证通过返回true
	 */
	public static boolean isMobile(String mobile) {
		Pattern pattern = null;
		Matcher matcher = null;
		boolean flag = false;
		pattern = Pattern.compile("^[1][3,4,5,8][0-9]{9}$"); // 验证手机号
		matcher = pattern.matcher(mobile);
		flag = matcher.matches();
		return flag;
	}

	/**
	 * 文件下载 直接下载http网络文件，本地不缓存
	 * 
	 * @param path
	 * @param file_name
	 * @return
	 */
	public static ModelAndResult downLoad(String path, String name) {
		ServletOutputStream out = null;
		URL url = null;
		URLConnection conn = null;
		InputStream is = null;
		try {
			url = new URL(path);
			conn = url.openConnection();
			is = conn.getInputStream();
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/octet-stream; charset=UTF-8");
			response.addHeader("Content-Disposition", "attachment; filename=\"" + name + "\";");
			out = response.getOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			out.flush();
			return new ModelAndResult();
		} catch (IOException e) {
			e.printStackTrace();
			return new ModelAndResult(false, e.getLocalizedMessage());
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * map转model
	 * 
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static DomainModel map2Model(Map<String, Object> map, Class clazz) {
		DomainModel model = null;
		try {
			model = (DomainModel) clazz.newInstance();
			Set<String> keys = map.keySet();
			for (String key : keys) {
				model.put(key, map.get(key));
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return model;
	}

	/**
	 * 除法运算，保留两位小数点
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String forDivision(String a, String b) {
		return MathUtils.forDivision(a, b) == null ? "0.0" : MathUtils.forDivision(a, b);
	}

	/**
	 * 浮点型除法运算，保留两位小数点
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String doubleForDivision(String a, String b) {
		double x = Double.valueOf(StringUtils.isEmpty(a) ? "0" : a);
		double y = Double.valueOf(StringUtils.isEmpty(b) ? "0" : b);
		if (x == 0 || y == 0) {
			return "0.0";
		}
		String result = "";// 接受百分比的值
		double x_double = x * 1.0;
		double tempresult = x_double / y;
		DecimalFormat df1 = new DecimalFormat("0.00"); // ##.00%
														// 百分比格式，后面不足2位的用0补齐
		result = df1.format(tempresult);
		return result;
	}

	/**
	 * 计算百分比%
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static String getPercent(String a, String b) {
		return MathUtils.getPercent(a, b) == null ? "0.0%" : MathUtils.getPercent(a, b);
	}

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
	public static int daysBetween(Date smdate, Date bdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate, String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(string2Date(smdate, format));
		long time1 = cal.getTimeInMillis();
		cal.setTime(string2Date(bdate, format));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * date的日期之间的毫秒差
	 * 
	 * @param smdate
	 * @param bdate
	 * @return
	 */
	public static long millsBetween(Date smdate, Date bdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		return time2 - time1;
	}

	/**
	 * 字符串的日期之间的毫秒差
	 * 
	 * @param smdate
	 * @param bdate
	 * @param format
	 * @return
	 */
	public static long millsBetween(String smdate, String bdate, String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(string2Date(smdate, format));
		long time1 = cal.getTimeInMillis();
		cal.setTime(string2Date(bdate, format));
		long time2 = cal.getTimeInMillis();
		return time2 - time1;
	}

	/**
	 * 获取指定时间所在月的第一天，并将时分秒设置为00，然后格式化为指定格式
	 * 
	 * @return
	 */
	public static String CurrentMonthFirstDay(Date date, String format) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return date2String(calendar.getTime(), format);
	}

	/**
	 * 获取主键值集合
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public static String[] getPrimaryKeyValue(DomainModel[] model) {
		String[] ids = new String[0];
		Class<?> clazz = null;
		Method method = null;
		if (arrayIsNotEmpty(model)) {
			ids = new String[model.length];
			for (int i = 0; i < model.length; i++) {
				try {
					clazz = model[i].getClass();
					method = clazz.getMethod("getPrimaryKey");
					ids[i] = (String) model[i].get(method.invoke(model[i], null));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
			}
		}
		return ids;
	}

	/**
	 * 判断数组是否为空
	 * 
	 * @param array
	 * @return true不为空，false为空
	 */
	public static boolean arrayIsNotEmpty(Object[] array) {
		return null != array && array.length > 0;
	}

	/**
	 * 查询条件in和notin的 SQL语句（部分）
	 * 
	 * @return
	 */
	public static String getInNotInSql(String key, String operator, String[] value) {
		StringBuffer sb = new StringBuffer();
		if (arrayIsNotEmpty(value)) {
			sb.append(key);
			if (StringUtils.isEmpty(operator)) {
				throw new ShareworxRuntimeException("查询条件配置错误");
			}
			switch (operator) {
			case QueryContents.TYPE_IN:
				sb.append(" in (");
				break;
			case QueryContents.TYPE_NOT_IN:
				sb.append(" not in (");
				break;
			}
			int size = value.length;
			for (int i = 0; i < size; i++) {
				sb.append("'" + value[i] + "'");
				if (i != size - 1) {
					sb.append(",");
				}
			}
			sb.append(") ");
		}
		return sb.toString();
	}

	/**
	 * 设置数据修改信息
	 * 
	 * @param model
	 */
	public static void setUpdateInfo(DomainModel model) {
		Long update_time = new Date().getTime();
		model.setAttribute("update_time", update_time.toString());
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		model.setAttribute("update_user", currUser.getPk_user());
	}

	/**
	 * 设置数据新增信息
	 * 
	 * @param model
	 */
	public static void setCreateInfo(DomainModel model) {
		model.setAttribute("create_time", date2String(new Date(), "yyyy-MM-dd HH:mm:ss sss"));
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		model.setAttribute("create_user", currUser.getPk_user());
		model.setAttribute("pk_crop", currUser.getPk_crop());
		setUpdateInfo(model);
	}

	/**
	 * 设置数据新增信息
	 * 
	 * @param model
	 */
	public static void setCreateUserAndTime(DomainModel model) {
		model.setAttribute("create_time", date2String(new Date(), "yyyy-MM-dd HH:mm:ss sss"));
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		model.setAttribute("create_user", currUser.getPk_user());
		model.setAttribute("pk_crop", currUser.getPk_crop());
	}

	/**
	 * 设置数据修改时间
	 * 
	 * @param model
	 */
	public static void setUpdateTime(DomainModel model) {
		Long update_time = new Date().getTime();
		model.setAttribute("update_time", update_time.toString());
	}

	/**
	 * 设置数据新增时间
	 * 
	 * @param model
	 */
	public static void setCreateTime(DomainModel model) {
		model.setAttribute("create_time", date2String(new Date(), "yyyy-MM-dd HH:mm:ss sss"));
	}

	/**
	 * 设置数据新增和修改时间
	 * 
	 * @param model
	 */
	public static void setCreateAndUpdateTime(DomainModel model) {
		setCreateTime(model);
		setUpdateTime(model);
	}

	/**
	 * 设置数据crop信息
	 * 
	 * @param model
	 */
	public static void setPkCrop(DomainModel model) {
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		model.setAttribute("pk_crop", currUser.getPk_crop());
	}

	/**
	 * 获取当前用户的pk_crop
	 * 
	 * @return
	 */
	public static String getPkCrop() {
		YJWYUserModel currUser = UserUtil.getCurrentUser();
		return currUser.getPk_crop();
	}

	/**
	 * date转String
	 * 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	/**
	 * string转date
	 * 
	 * @param str
	 * @param format
	 * @return
	 */
	public static Date string2Date(String str, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = simpleDateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取下一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNextDate(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, num);
		return calendar.getTime();
	}

	/**
	 * 获取stirng下一天(字段转换类型)
	 * 
	 * @param date
	 * @return
	 */
	public static String getNextDate(String date, String format, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(string2Date(date, format));
		calendar.add(Calendar.DAY_OF_MONTH, num);
		return date2String(calendar.getTime(), format);
	}

	/**
	 * 首字母小写
	 * 
	 * @param s
	 * @return
	 */
	public static String toLowerCaseFirstOne(String s) {
		if (Character.isLowerCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * 首字母大写
	 * 
	 * @param s
	 * @return
	 */
	public static String toUpperCaseFirstOne(String s) {
		if (Character.isUpperCase(s.charAt(0)))
			return s;
		else
			return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
	}

	/**
	 * 判断string字符串是否为空
	 * 
	 * @param str
	 * @return true为空，false不为空
	 */
	public static boolean stringIsEmpty(String str) {
		if (null == str || "undefined".equals(str) || "null".equals(str) || "".equals(str)) {
			return true;
		}
		return false;
	}
}
