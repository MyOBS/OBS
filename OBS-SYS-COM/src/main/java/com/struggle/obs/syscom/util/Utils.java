package com.struggle.obs.syscom.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	/**
	 * str为null 或“” 返回true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isEmptyOrNull(String str) {
		return (str == null || "".equals(str.trim()));
	}

	/**
	 * value为null,则返回true
	 * 
	 * @param value
	 * @return
	 */
	public static boolean IsNull(Long value) {
		return value == null;
	}

	/**
	 * value为null,则返回true
	 * 
	 * @param value
	 * @return
	 */
	public static boolean IsNull(Double value) {
		return value == null;
	}

	/**
	 * 将null值转换成0 2014年8月20日 下午10:14:00
	 * 
	 * @param articleCount
	 * @return
	 */
	public static int switchInt(Integer value) {
		return value == null ? 0 : value;
	}

	/**
	 * 格式化日期date,format为null或者""时， 默认返回yyyy-MM-dd HH:mm:ss <br>
	 * 2014年8月21日 下午9:57:38
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            指定格式
	 * @return String
	 */
	public static String formatTime(Date date, String format) {
		if (format == null || "".equals(format)) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		return formatDate(date, format);
	}

	/**
	 * 格式化日期date,format为null或者""时， 默认返回yyyy-MM-dd <br>
	 * 2014年8月21日 下午9:57:34
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            指定格式
	 * @return String
	 * @see #formatDate(java.util.Date)
	 */
	public static String formatDate(Date date, String format) {
		if (format == null || "".equals(format)) {
			format = "yyyy-MM-dd";
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 格式化日期date， 返回yyyy-MM-dd <br>
	 * 2014年8月22日 上午2:05:40
	 * 
	 * @param date
	 * @return String
	 * @see #formatDate(java.util.Date, String)
	 */
	public static String formatDate(Date date) {
		return formatDate(date, null);
	}

	/**
	 * 返回系统时间的格式化字符串<br>
	 * 2014年8月22日 下午4:51:16
	 * 
	 * @return String
	 * 
	 */
	public static String formatSysDate() {
		return formatDate(new Date());
	}

}
