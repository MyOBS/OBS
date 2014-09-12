package com.struggle.obs.syscom.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.struggle.obs.syscom.exception.OBSException;

public class Utils {
	/**
	 * 截取字符串的前空格<br>
	 * value==null 返回 ""<br>
	 * 2014年9月9日 上午11:18:39
	 * 
	 * @param value
	 * @return String
	 */
	public static String trim(String value) {
		if (value != null) {
			return value.trim();
		}
		return "";
	}

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
	 * 是自然数吗？ <br>
	 * value 不等于 null，且大于0时，返回true 2014年8月29日 下午11:17:34
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean IsNaturalNum(Long value) {
		return value != null && value > 0L;
	}

	/**
	 * 是自然数吗？ <br>
	 * value 不等于 null，且大于0时，返回true 2014年8月29日 下午11:17:34
	 * 
	 * @param value
	 * @return boolean
	 */
	public static boolean IsNaturalNum(Double value) {
		return value != null && value > 0;
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

	/**
	 * value==null就返回0 2014年8月30日 下午2:42:55
	 * 
	 * @param money
	 * @return Long
	 */
	public static Long switchInt(Long value) {
		return value == null ? 0L : value;
	}

	/**
	 * 验证图片大小 2014年9月5日 下午4:51:02
	 * 
	 * @param file
	 *            文件
	 * @exception OBSException
	 */
	public static void validateImageSize(File file) throws OBSException {
		if (file.length() > 204800) {
			throw new OBSException("不能大于200K");
		}
	}

	/**
	 * 验证图片格式<br>
	 * 2014年9月5日 下午4:53:36
	 * 
	 * @param imageContentType
	 *            文件类型
	 * @param ext
	 *            后缀名
	 * @exception OBSException
	 */
	public static void validateImageType(String imageContentType, String ext)
			throws OBSException {
		if (imageContentType != null) {
			List<String> arrowType = Arrays.asList("image/bmp", "image/png",
					"image/gif", "image/jpg", "image/jpeg", "image/pjpeg");
			List<String> arrowExtension = Arrays.asList("gif", "jpg", "bmp",
					"png");
			boolean y = arrowType.contains(imageContentType.toLowerCase());
			boolean x = arrowExtension.contains(ext);
			if (!(x && y)) {
				throw new OBSException("格式不正确，只允许上传gif/jpg/png/bmp格式的图片！");
			}
		}
	}

	/**
	 * 将日期增加或者减少指定的 年\月\日<br>
	 * <b>eg:</b> <br>
	 * modifyDate(new Date(), Calendar.DAY, 1) //返回 new Date() + 1 天的日期 <br>
	 * modifyDate(new Date(), Calendar.MONTH, -2) //返回 new Date() -2 个月 的日期 <br>
	 * 2014年9月11日 下午3:16:16
	 * 
	 * @param date
	 *            需要被修改的日期
	 * @param field
	 *            日历字段<br>
	 *            常用取值：
	 *            <ul>
	 *            <li>Calendar.MONTH</li>
	 *            <li>Calendar.DAY</li>
	 *            <li>Calendar.YEAR</li>
	 *            <li>...(更多用法参见Calendar类)</li>
	 *            </ul>
	 * @param amount
	 *            增加(+)\减少(-)的日期的
	 * @return Date 修改后的日期
	 */
	public static Date modifyDate(Date date, int field, int amount) {
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			cal.add(field, amount);
			return cal.getTime();
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 将日期增加或者减少指定的 年\月\日<br>
	 * <b>eg:</b> <br>
	 * modifyDate("yyyy-MM-dd", Calendar.DAY, 1) //返回 "yyyy-MM-dd" + 1 天的日期 <br>
	 * modifyDate("yyyy-MM-dd", Calendar.MONTH, -2) //返回 "yyyy-MM-dd" -2 个月 的日期 <br>
	 * 2014年9月11日 下午3:16:16
	 * 
	 * @param strDate
	 *            需要被修改的字符串型日期
	 * @param field
	 *            日历字段<br>
	 *            常用取值：
	 *            <ul>
	 *            <li>Calendar.MONTH</li>
	 *            <li>Calendar.DAY</li>
	 *            <li>Calendar.YEAR</li>
	 *            <li>...(更多用法参见Calendar类)</li>
	 *            </ul>
	 * @param amount
	 *            增加(+)\减少(-)的日期的
	 * @return Date 修改后的日期
	 */
	public static Date modifyDate(String strDate,String format, int field, int amount) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date date = sdf.parse(strDate);
			return modifyDate(date, field, amount);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据传入的金钱，算出需要的金币<br>
	 * 2014年9月12日 上午11:04:06
	 * @param money 兑换金钱
	 * @return 所属金币
	 */
	public static Long transformGold(Long money) {
		if(money == null || money == 0){
			return 0L;
		}
		return new Double(Math.floor(money/50)).longValue()+ 1;
	}
	/**
	 * 根据传入的金币，算出需要的金钱<br>
	 * 2014年9月12日 上午11:04:06
	 * @param gold 兑换金币
	 * @return 所属金钱
	 */
	public static Long transformMoney(Long gold) {
		if(gold == null || gold == 0){
			return 0L;
		}
		return new Double(Math.floor(gold*50*1.01)).longValue();
	}

	/**
	 * 将null转换为""<br>
	 * 2014年9月12日 上午11:56:04
	 * @param password
	 * @return String
	 */
	public static String switchStr(String password) {
		return password == null ? "" : password;
	}
}
