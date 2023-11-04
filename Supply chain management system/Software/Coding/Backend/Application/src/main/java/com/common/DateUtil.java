/*
 * @(#) DateUtil.java 2010/04/22
 *
 * Copyright (c) 2007, Cloudsoaring Inc. All Rights Reserved.
 * Cloudsoaring Inc. CONFIDENTIAL
 */
package com.common;

import com.common.StringUtil;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TimeZone;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class DateUtil implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String DATE_PATTERN_01 = "yyyy/MM/dd";
	public static final String DATE_PATTERN_02 = "yyyyMMdd";
	public static final String DATE_PATTERN_03 = "yyyy/MM/dd HH:mm:ss:SSS";
	public static final String DATE_PATTERN_04 = "yy/MM/dd";
	public static final String DATE_PATTERN_05 = "yyyyMMddHHmmssSSS";
	public static final String DATE_PATTERN_06 = "yyyy/MM/dd HH:mm";
	public static final String DATE_PATTERN_07 = "yyyy";
	public static final String DATE_PATTERN_08 = "yyyy/MM/dd HH:mm:ss";
	public static final String DATE_PATTERN_09 = "yyyyMMddHHmmss";
	public static final String DATE_PATTERN_10 = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN_11 = "yyMMddHHmm";
	public static final String DATE_PATTERN_12 = "yyyy-MM-dd";
	public static final String DATE_PATTERN_13 = "yyyy/MM/dd HH:mm";
	public static final String DATE_PATTERN_14 = "yyyy-MM-dd HH:mm";
	public static final String DATE_PATTERN_15 = "HH:mm:ss";
	public static final String DATE_PATTERN_16 = "yyyy-MM";
	public static final String DATE_PATTERN_17 = "MM";
	public static final String DATE_PATTERN_18 = "yyyy-MM-dd";
	public static final String DATE_PATTERN_19 = "yyyy-MM-dd HH:mm:00";
	public static final String DATE_PATTERN_20 = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_PATTERN_21 = "yyyy年MM月dd日";
	public static final String DATE_PATTERN_22 = "HH:mm";
	public static final String DATE_FORMAT_SHORT_YYYYMM = "yyyyMM";
	/**
	 * 简短日期格式:yyyyMMddHHmmss
	 */
	public static final String DATE_FORMAT_SHORT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	/**
	 * 简短日期格式:yyyyMMddHHmm
	 */
	public static final String DATE_FORMAT_SHORT_YYYYMMDDHHMM = "yyyyMMddHHmm";
	/**
	 * 简短日期格式:yyyyMMddHH
	 */
	public static final String DATE_FORMAT_SHORT_YYYYMMDDHH = "yyyyMMddHH";
	/**
	 * 简短日期格式:yyyyMMdd
	 */
	public static final String DATE_FORMAT_SHORT_YYYYMMDD = "yyyyMMdd";

	public static String EFF_OUT_DATE = "9999/12/31";
	public static String EFF_OUT_TIME = "00:00:00";

	/**
	 * 简短日期格式长度YYYYMMDDHHMMSS
	 */
	private static final int SHORT_YYYYMMDDHHMMSS_LENGTH = 14;

	/**
	 * 简短日期格式长度YYYYMMDDHHMM
	 */
	private static final int SHORT_YYYYMMDDHHMM_LENGTH = 12;

	/**
	 * 简短日期格式长度YYYYMMDDHH
	 */
	private static final int SHORT_YYYYMMDDHH_LENGTH = 10;

	private static HashSet datePatternSet;

	static {
		datePatternSet = new HashSet();
		datePatternSet.add(DATE_PATTERN_01);
		datePatternSet.add(DATE_PATTERN_02);
		datePatternSet.add(DATE_PATTERN_03);
		datePatternSet.add(DATE_PATTERN_04);
		datePatternSet.add(DATE_PATTERN_05);
		datePatternSet.add(DATE_PATTERN_06);
		datePatternSet.add(DATE_PATTERN_07);
		datePatternSet.add(DATE_PATTERN_08);
		datePatternSet.add(DATE_PATTERN_09);
		datePatternSet.add(DATE_PATTERN_10);
		datePatternSet.add(DATE_PATTERN_11);
		datePatternSet.add(DATE_PATTERN_12);
		datePatternSet.add(DATE_PATTERN_13);
		datePatternSet.add(DATE_PATTERN_14);
		datePatternSet.add(DATE_PATTERN_18);
		datePatternSet.add(DATE_PATTERN_19);
		datePatternSet.add(DATE_PATTERN_20);
		datePatternSet.add(DATE_PATTERN_21);
		datePatternSet.add(DATE_FORMAT_SHORT_YYYYMM);
		datePatternSet.add(DATE_PATTERN_22);
	}

	/**
	 *
	 * @param pDate       Date
	 * @param datePattern Date�^
	 * @return String
	 */
	public static String getDateString(Date pDate, String datePattern) {
		if (StringUtil.isEmpty(datePattern) || !datePatternSet.contains(datePattern)) {
			// throw new IllegalArgumentException("Date Pattern error!");
			return "";
		}

		if (pDate == null) {
			// throw new IllegalArgumentException("inputed Date error!");
			return "";
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

		return dateFormat.format(pDate);
	}

	/**
	 *
	 * @param str         String
	 * @param datePattern
	 * @return Date
	 */
	public static Date getDateByString(String str, String datePattern) {
		if (StringUtil.isEmpty(datePattern) || !datePatternSet.contains(datePattern)) {
			return null;
		}

		if (StringUtil.isEmpty(str)) {
			return null;
		}

		SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);

		try {
			return dateFormat.parse(str);
		} catch (ParseException pe) {
			pe.printStackTrace();
		}

		return null;
	}


	/**
	 * 日期转换字符串
	 * @param date
	 * @return
	 */
	public String dateToString(Date date){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
		String format = simpleDateFormat.format(date);
		return format;
	}

	/**
	 * 加一天
	 * @param date
	 * @return
	 */
	public Date addOneDate(Date date){
		Date temp = new Date();
		temp.setTime(date.getTime()+1000*60*60*24);
		return temp;
	}

	public static String getShortSysDateString() {
		Date date = new Date(System.currentTimeMillis());
		return DateUtil.format(date, DATE_FORMAT_SHORT_YYYYMMDD);
	}

	public static Date parse(String dateStr) {
		Date date = null;
		if (StringUtil.isNotEmpty(dateStr)) {
			// 防止将两位的月份，日期，时分秒等只用一位处理
			String[] params = StringUtil.split(dateStr, "- .:/");
			for (int i = 0; i < params.length; i++) {
				if (params[i].length() == 1 && "0123456789".indexOf(params[i]) > -1) {
					params[i] = "0" + params[i];
				}
			}
			String shortDateStr = StringUtil.join(params);
			int shortDateStrLen = shortDateStr.length();
			String formatStr = DATE_FORMAT_SHORT_YYYYMMDD;
			switch (shortDateStrLen) {
			case SHORT_YYYYMMDDHHMMSS_LENGTH:
				formatStr = DATE_FORMAT_SHORT_YYYYMMDDHHMMSS;
				break;
			case SHORT_YYYYMMDDHHMM_LENGTH:
				formatStr = DATE_FORMAT_SHORT_YYYYMMDDHHMM;
				break;
			case SHORT_YYYYMMDDHH_LENGTH:
				formatStr = DATE_FORMAT_SHORT_YYYYMMDDHH;
				break;
			case 6:
				shortDateStr += "01";
				formatStr = DATE_FORMAT_SHORT_YYYYMMDD;
				break;
			default:
				formatStr = DATE_FORMAT_SHORT_YYYYMMDD;
				break;
			}
			date = parse(shortDateStr, formatStr);
		}
		return date;
	}

	public static Date parse(String dateStr, String format) {
		Date date = null;
		if (StringUtil.isNotEmpty(dateStr)) {
			DateFormat formatter = new SimpleDateFormat(format);
			try {
				date = formatter.parse(dateStr);
			} catch (ParseException ex) {
			}
		}
		return date;
	}

	public static String format(Date date, String parttern) {
		String value = "";
		if (date != null) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat(parttern);
				value = dateFormat.format(date);
			} catch (Exception ex) {
			}
		}
		return value;
	}

	public static String changeFormatForScript(String javaformat) {
		String result = javaformat;
		if (StringUtil.isNotEmpty(javaformat)) {
			if (javaformat.indexOf("i") > -1) {
				result = javaformat;
			} else if (javaformat.indexOf("M") > -1) {
				result = StringUtil.replace(javaformat, "mm", "ii");
				result = StringUtil.replace(result, "m", "i");
			}
			result = StringUtil.replace(result, "HH", "hh");
			result = StringUtil.replace(result, "H", "h");
		}
		result = StringUtil.replaceEachRepeatedly(result, new String[] { "Y", "D" }, new String[] { "y", "d" });
		return result;
	}

	public static String formatAny(Date date, String sparttern) {
		String value = "";
		String parttern = StringUtil.replace(sparttern, "yy/mm", "yy/MM");
		parttern = StringUtil.replace(parttern, "yy-mm", "yy-MM");
		parttern = StringUtil.replace(parttern, "hh", "HH");
		parttern = StringUtil.replace(parttern, "h", "HH");
		parttern = StringUtil.replace(parttern, "ii", "mm");
		parttern = StringUtil.replace(parttern, "i", "mm");
		if (date != null) {
			try {
				SimpleDateFormat dateFormat = new SimpleDateFormat(parttern);
				value = dateFormat.format(date);
			} catch (Exception ex) {
			}
		}
		return value;
	}

	public static String changeDateFormat(String value, String format) {
		Date date = parse(value);
		return format(date, format);
	}

	/**
	 * @return Date
	 */
	public static Date addYears(Date date, int yearAmount) {
		return add(date, Calendar.YEAR, yearAmount);
	}

	/**
	 */
	public static Date getSysDate() {
		return new Date();
	}

	public static String getSysDateString() {
		Date date = new Date();
		return format(date, DATE_FORMAT_SHORT_YYYYMMDDHHMMSS);
	}

	public static String getSysDateString2() {
		Date date = new Date();
		return format(date, DATE_PATTERN_12);
	}

	public static String getSysTimeString() {
		Date date = new Date(System.currentTimeMillis());
		return format(date, DATE_PATTERN_13);
	}

	public static Date today() {
		return getDate(new Date());
	}

	public static Date getDate(Date date) {
		return parse(format(date, DATE_PATTERN_12));
	}

	public static Date tomorrow() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		return getDate(c.getTime());
	}

	/**
	 */
	public static String getYoubi(Date date) {
		String youbi = "";

		if (date == null) {
			return youbi;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("E");
		youbi = formatter.format(date);

		return youbi;
	}

	/**
	 *
	 * @param firstDate  date
	 * @param secendDate date
	 * @return Integer (firstDate-secendDate)
	 */
	public static Integer getRemainder(Date firstDate, Date secendDate) {
		if (firstDate == null || secendDate == null) {
			// throw new IllegalArgumentException("Date Pattern error!");
			return null;
		}

		Date firstYmdDate = getDateByString(getDateString(firstDate, DATE_PATTERN_01), DATE_PATTERN_01);
		Date secendYmdDate = getDateByString(getDateString(secendDate, DATE_PATTERN_01), DATE_PATTERN_01);

		long dayRemainder = (firstYmdDate.getTime() - secendYmdDate.getTime()) / (24 * 60 * 60 * 1000);

		return new Integer((int) dayRemainder);
	}

	public static Date getMiddleDate(Date firstDate, Date secendDate) {
		if (firstDate == null || secendDate == null) {
			return null;
		}
		long time = secendDate.getTime() - firstDate.getTime();
		return add(firstDate, Calendar.MILLISECOND, (int) time);
	}

	/**
	 */
	public static Date add(Date date, int field, int amount) {
		if (date == null) {
			return null;
		}

		Calendar calendar = GregorianCalendar.getInstance();
		// Calendar calendar = GregorianCalendar.getInstance(Locale.JAPAN);
		calendar.setTime(date);
		calendar.add(field, amount);

		return calendar.getTime();
	}

	/**
	 */
	public static Date addDays(Date date, int dayAmount) {
		return add(date, Calendar.DATE, dayAmount);
	}

	/**
	 */
	public static Date addMonths(Date date, int monthAmount) {
		return add(date, Calendar.MONTH, monthAmount);
	}

	/**
	 * 添加分钟
	 */
	public static Date addMinutes(Date date, int minuteAmount) {
		return add(date, Calendar.MINUTE, minuteAmount);
	}

	/**
	 * 添加小时
	 */
	public static Date addHours(Date date, int hourAmount) {
		return add(date, Calendar.HOUR, hourAmount);
	}

	public static String getCurrentYear() {
		return format(new Date(), DATE_PATTERN_07);
	}

	public static String getCurrentYearMonth() {
		return format(new Date(), DATE_PATTERN_16);
	}

	public static String getCurrentMonth() {
		return format(new Date(), DATE_PATTERN_17);
	}

	/**
	 */
	public static Date getDateByDatetime(Date date) {
		if (date == null) {
			return null;
		}

		return getDateByString(getDateString(date, DATE_PATTERN_01), DATE_PATTERN_01);
	}

	/**
	 * @return boolean
	 */
	public static boolean isValidateDate(String strDate) {
		try {
			if (!StringUtil.isNumeric(strDate)) {
				return false;
			}

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
			sdf.setLenient(false);
			sdf.parse(strDate);

			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * @return boolean
	 */
	public static boolean isValidateDateFYYYYMMDD(String strDate) {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			sdf.setLenient(false);
			sdf.parse(strDate);

			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * @return boolean
	 */
	public static boolean isValidateDateYYYYMMDD(String strDate) {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			sdf.setLenient(false);
			sdf.parse(strDate);

			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * @return boolean
	 */
	public static boolean isValidateMonth(String strDate) {
		try {
			strDate = strDate.replace("-", "").replace("/", "");
			if (strDate.length() == 6) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				sdf.setLenient(false);
				sdf.parse(strDate);
			} else if (StringUtil.isNullString(strDate)) {
				// 为空
				return false;
			}
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	public static String getYearMonth(String strDate) {
		try {
			strDate = strDate.replace("-", "").replace("/", "");
			if (strDate.length() == 6) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				sdf.setLenient(false);
				return DateUtil.getDateString(sdf.parse(strDate), "yyyyMM");
			}
			return null;
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * @return boolean
	 */
	public static boolean isValidateDate(String strDate, String dateFormat) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setLenient(false);
			sdf.parse(strDate);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 *
	 * @param date Date
	 * @return boolean boolean
	 */
	public static boolean checkDate(Date date) {
		String strDate = getDateString(date, DATE_PATTERN_02);
		Date systemDate = getDateByDatetime(getSysDate());
		String beforStrDate = getDateString(addYears(systemDate, -1), DATE_PATTERN_02);
		String afterStrDate = getDateString(addYears(systemDate, 5), DATE_PATTERN_02);
		if (strDate.compareTo(beforStrDate) < 0 || strDate.compareTo(afterStrDate) > 0) {
			return false;
		}
		return true;
	}

	/**
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		String dateStr = getDateString(date, DATE_PATTERN_02);
		String lastDay = dateStr.substring(0, 6) + String.valueOf(calendar.getActualMaximum(Calendar.DATE));
		return getDateByString(lastDay, DATE_PATTERN_02);
	}

	/**
	 */
	public static Date getMonthFirstDay(Date date) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		String dateStr = DateUtil.getDateString(date, DateUtil.DATE_PATTERN_02);
		String lastDay = dateStr.substring(0, 6) + String.valueOf(calendar.getActualMinimum(Calendar.DATE));
		return DateUtil.getDateByString(lastDay, DateUtil.DATE_PATTERN_02);
	}

	/**
	 * 得到日期所在星期的星期一
	 *
	 * @param date
	 * @return
	 */
	public static Date getMondayOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		Date result = null;
		if (weekDay == 1) {
			result = addDays(date, -6);
		} else {
			result = addDays(date, 2 - weekDay);
		}
		result = parse(getDateString(result, DATE_PATTERN_01), DATE_PATTERN_01);
		return result;
	}

	/**
	 * 得到日期所在星期的星期一
	 *
	 * @param date
	 * @return
	 */
	public static Date getNextMondayOfWeek(Date date) {
		Date result = getMondayOfWeek(date);
		return addDays(result, 7);
	}

	@SuppressWarnings("deprecation")
	public static void lastDaySecond(Date date) {
		if (date != null) {
			date.setHours(23);
			date.setMinutes(59);
			date.setSeconds(59);
		}
	}

	public static boolean isApplicationEffOut() {
//		Date today = DateUtil.getSysDate();
//		String applicationEffOutDate = "2013/08/01";
//		Date applicationEffOutDatedt = DateUtil.getDateByString(applicationEffOutDate, DateUtil.DATE_PATTERN_01);
//		if (today.after(applicationEffOutDatedt))
//		{
//			return true;
//		}
//		else
//		{
//			return false;
//		}
		return false;
	}

	/**
	 * 得到日期所在一天的开始日期
	 *
	 * @param args
	 */
	public static Date getDayStart(Date date) {
		return parse(format(date, DATE_PATTERN_01), DATE_PATTERN_01);
	}

	/**
	 * 得到日期所在一天的结束日期
	 *
	 * @param args
	 */
	public static Date getDayEnd(Date date) {
		return parse(format(date, DATE_PATTERN_02) + "235959", DATE_PATTERN_09);
	}

	/**
	 * 得到日期明日的开始日期
	 *
	 * @param args
	 */
	public static Date getTomorrowStart(Date date) {
		Date result = getDayStart(date);
		return addDays(result, 1);
	}

	/**
	 * 得到多组时间的交集长度
	 */
	public static Long getIntersectionTime(Date... args) {
		if (args == null) {
			return 0L;
		}
		// args 成对
		if (args != null && args.length % 2 != 0) {
			throw new RuntimeException("参数个数不为偶数");
		}
		// args中成对日期中的结束日期大于等于开始日期
		if (args != null) {
			for (int i = 0; i < args.length; i = i + 2) {
				if (args[i].after(args[i + 1])) {
					throw new RuntimeException("结束日期不能早于开始日期");
				}
			}
		}
		int arrayLength = args.length / 2;
		Date[] startArray = new Date[arrayLength];
		Date[] endArray = new Date[arrayLength];
		for (int i = 0; i < args.length; i = i + 2) {
			startArray[i / 2] = args[i];
		}
		for (int i = 1; i < args.length; i = i + 2) {
			endArray[i / 2] = args[i];
		}

		Long result = getMin(endArray).getTime() - getMax(startArray).getTime();
		return result < 0 ? 0 : result;
	}

	public static Date getMax(Date... dates) {
		if (dates == null || dates.length == 0) {
			return null;
		}
		Date result = dates[0];
		for (Date one : dates) {
			if (result == null) {
				result = one;
			}
			if (one != null && result.before(one)) {
				result = one;
			}
		}
		return result;
	}

	public static Date getMin(Date... dates) {
		if (dates == null || dates.length == 0) {
			return null;
		}
		Date result = dates[0];
		for (Date one : dates) {
			if (result == null) {
				result = one;
			}
			if (one != null && result.after(one)) {
				result = one;
			}
		}
		return result;
	}

	public static Date getYearLastDay(Date date) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, Calendar.DECEMBER);
		return getMonthLastDay(calendar.getTime());
	}

	public static Date getYearFirstDay(Date date) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, Calendar.JANUARY);
		return getMonthFirstDay(calendar.getTime());
	}

	public static int getDayOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getActualMaximum(Calendar.DATE);
	}

	public static int getWorkdaysInMonth(Date date) {
		int wd = 0;
		Calendar cal = Calendar.getInstance();
		cal.setTime(getMonthFirstDay(date));
		while (cal.getTime().getTime() <= (getMonthLastDay(date)).getTime()) {
			int day = cal.get(Calendar.DAY_OF_WEEK);
			if (!(day == Calendar.SUNDAY || day == Calendar.SATURDAY)) {
				wd++;
			}
			cal.add(Calendar.DATE, 1);
		}
		return wd;
	}

	public static Date changeTimeZone(Date from, TimeZone ttz) {
		return changeTimeZone(from, null, ttz);
	}

	public static Date changeTimeZone(Date from, TimeZone ftz, TimeZone ttz) {
		if (from != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTimeZone(ttz);

			// 1、取得本地时间：
			Calendar cal = null;
			if (ftz != null) {
				cal = Calendar.getInstance(ftz);
			} else {
				cal = Calendar.getInstance();
			}
			cal.setTime(from);
			// 2、取得时间偏移量：
			int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
			// 3、取得夏令时差：
			int dstOffset = cal.get(Calendar.DST_OFFSET);
			// 4、取得时间偏移量：
			int zoneOffset2 = calendar.get(Calendar.ZONE_OFFSET);
			// 5、取得夏令时差：
			int dstOffset2 = calendar.get(Calendar.DST_OFFSET);
			// 6、从本地时间里扣除这些差量，即可以取得UTC时间：
			cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset) + (zoneOffset2 + dstOffset2));
			return new Date(cal.getTimeInMillis());
		}
		return null;
	}

	/**
	 * 获取当年的第一天
	 *
	 * @param year
	 * @return
	 */
	public static Date getCurrYearFirst() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearFirst(currentYear);
	}

	/**
	 * 获取当年的最后一天
	 *
	 * @param year
	 * @return
	 */
	public static Date getCurrYearLast() {
		Calendar currCal = Calendar.getInstance();
		int currentYear = currCal.get(Calendar.YEAR);
		return getYearLast(currentYear);
	}

	/**
	 * 获取某年第一天日期
	 *
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearFirst(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		Date currYearFirst = calendar.getTime();
		return currYearFirst;
	}

	/**
	 * 获取某年最后一天日期
	 *
	 * @param year 年份
	 * @return Date
	 */
	public static Date getYearLast(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, year);
		calendar.roll(Calendar.DAY_OF_YEAR, -1);
		Date currYearLast = calendar.getTime();

		return currYearLast;
	}

	/**
	 * 当前日期与某个日期比较
	 */
	@SuppressWarnings("unused")
	public static boolean compareDate(Date date) {
		boolean result = false;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		Date beginDate = new Date();
		result = beginDate.after(date);
		return result;
	}

	/**
	 * 获取指定月的前一月（年）或后一月（年）
	 *
	 * @param dateStr
	 * @param addYear
	 * @param addMonth
	 * @param addDate
	 * @return 输入的时期格式为yyyy-MM，输出的日期格式为yyyy-MM
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static String getLastMonth(String dateStr, int addYear, int addMonth, int addDate) throws Exception {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date sourceDate = sdf.parse(dateStr);
			Calendar cal = Calendar.getInstance();
			cal.setTime(sourceDate);
			cal.add(Calendar.YEAR, addYear);
			cal.add(Calendar.MONTH, addMonth);
			cal.add(Calendar.DATE, addDate);

			SimpleDateFormat returnSdf = new SimpleDateFormat("yyyy-MM");
			String dateTmp = returnSdf.format(cal.getTime());
			Date returnDate = returnSdf.parse(dateTmp);
			return dateTmp;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * 计算相差月份
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Integer getDifMonth(Date startDate, Date endDate) {
		Calendar start = Calendar.getInstance();
		Calendar end = Calendar.getInstance();
		start.setTime(startDate);
		end.setTime(endDate);
		int result = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);
		int month = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR)) * 12;
		return Math.abs(month + result);
	}
}
