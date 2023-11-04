package com.fabric.utils;


public class StringUtil {


	private static int generateCount = 0;

	/**
	 * 生成一个唯一字符串
	 */
	public static synchronized String getUniqueString() {
		if (generateCount > 99999) {
			generateCount = 0;
		}
		String uniqueNumber = Long.toString(System.currentTimeMillis()) + Integer.toString(generateCount);
		generateCount++;
		return uniqueNumber;
	}


	public static String blankToNull(String src) {
		if (null == src || src.trim().equals("")) {
			return null;
		} else {
			return src;
		}
	}

	/**
	 * @param src
	 * @param trimStr
	 * @return emptyString
	 */
	public static String trimStr(String src, String trimStr) {
		if (src == null) {
			return "";
		}
		if (trimStr == null) {
			return nvlTrim(src);
		}
		return nvlTrim(src).replaceAll(trimStr, "");
	}


	/**
	 * 
	 */
	public static String getPriceStr(String price) {
		StringBuffer sb = new StringBuffer().append("\\");
		int pos = 0;
		for (int i = price.length() / 3; i > 0; i--) {
			String str = price.substring(pos, price.length() - i * 3);
			if (!"".equals(str)) {
				sb.append(str).append(",");
			}
			pos = price.length() - i * 3;
		}
		sb.append(price.substring(pos, price.length()));
		if (sb.length() == 1) {
			sb.append("0");
		}
		return sb.toString();
	}

	/**
	 * NULL
	 * @return boolean true: NULL, false: NULL
	 */
	public static boolean isNullString(String str) {
		return (str == null || trim(str).equals(""));
	}

	/**
	 * 
	 */
	public static boolean isNotNullString(String str) {
		return (!isNullString(str));
	}

	/**
	 * 
	 */
	public static boolean isSpace(char c) {
		return (c == ' ' || c == '\t' || c == ' ');
	}

	/**
	 *
	 */
	public static String nvl(String sIn) {
		return (sIn == null) ? "" : sIn;
	}

	/**
	 *
	 */
	public static String nvl(Object sIn) {
		return (sIn == null) ? "" : sIn.toString();
	}

	/**
	 *
	 */
	public static String nvlBlank(String sIn) {
		String value = nvl(sIn);

		if (value.trim().equals("")) {
			return " ";
		} else {
			return value;
		}
	}

	/**
	 *
	 */
	public static String nvlTrim(String sIn) {
		return nvl(sIn).trim();
	}

	/**
	 * 
	 */
	public static String replaceStr(String src, String sFnd, String sRep) {
		if (src == null || sFnd == null || sRep == null) {
			return src;
		}
		StringBuffer sTemp = new StringBuffer();
		int endIndex = 0;
		int beginIndex = 0;
		do {
			endIndex = src.indexOf(sFnd, beginIndex);
			if (endIndex >= 0) { // mean found it.
				sTemp.append(src.substring(beginIndex, endIndex));
				sTemp.append(sRep);

				beginIndex = endIndex + sFnd.length();
			} else if (beginIndex >= 0) {
				sTemp.append(src.substring(beginIndex));
				break;
			}
		} while (endIndex >= 0);

		return sTemp.toString();
	}



	/**
	 * 
	 */
	public static String trim(String s) {
		if (s == null) {
			return null;
		}

		int begin;
		int end;

		for (begin = 0; (begin < s.length()) && isSpace(s.charAt(begin));) {
			begin++;
		}

		for (end = s.length() - 1; (end >= 0) && isSpace(s.charAt(end));) {
			end--;
		}

		if (end < begin) {
			return "";
		}

		return s.substring(begin, end + 1);
	}



}
