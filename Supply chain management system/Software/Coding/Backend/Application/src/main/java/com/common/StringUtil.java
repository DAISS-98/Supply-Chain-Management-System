package com.common;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.BeanUtils;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.*;

public class StringUtil extends org.apache.commons.lang.StringUtils {


	/**
	 * 传统方式，非常简单直接通过InetAddress获取，但不准确获取的为虚拟ip
	 */
	public String getIp() throws Exception {
		Map map = new HashMap();
		InetAddress localHost = InetAddress.getLocalHost();
		String hostAddress = localHost.getHostAddress();
		return hostAddress;
	}

	public static Long getId(String type){
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddHHmmss");
		String format = simpleDateFormat.format(date);
		String id = type+format+(int)(Math.random()*100)+(int)(Math.random()*100);
		Long onlyId = Long.valueOf(id);
		return onlyId;
	}

	public static String getType(Long id){
		String s = String.valueOf(id);
		String substring = s.substring(0, 1);
		return substring;
	}



	public static final String getOrElse(Object value, String defaultValue){
		if( value != null && isNotEmpty(value.toString())){
			return value.toString();
		} else if(isNotEmpty(defaultValue)){
			return defaultValue;
		}
		return "";
	}

	public static final String getOrElseTrim(Object value, String defaultValue){
		if( value != null && isNotEmpty(value.toString())){
			return value.toString().trim();
		} else if(StringUtil.isNotEmpty(defaultValue)){
			return defaultValue.trim();
		}
		return "";
	}

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
	public static final String getOrElseTrim(Object value){
		return getOrElseTrim(value, "");
	}

	public static final String[] splitTwo(String old, String splitor){
		String[] result = new String[2];
		if(isNotEmpty(old)){
			int index = old.indexOf(splitor);
			if(index > -1){
				result[0] = old.substring(0, index);
				result[1] = old.substring(index + splitor.length());
			}else{
				result[0] = old;
				result[1] = "";
			}
		}
		return result;
	}

	public static final String getOrElse(Object value){
		return getOrElse(value, EMPTY);
	}


	public static String replaceRepeat(String value, String searchString, String replacement){
		String result = value;
		if(isNotEmpty(value)){
			do{
				result = replace(result, searchString, replacement);
			}while(!result.equals(replace(result, searchString, replacement)));
		}
		return result;
	}

	public static boolean isAbsolutePath(String path){
		return isNotEmpty(path)&&path.indexOf(":") > -1;
	}

	public static Boolean toBoolean(String value){
		boolean result = false;
		if(isNotEmpty(value) && ("1".equals(value)
				|| "true".equalsIgnoreCase(value)
				|| "是".equalsIgnoreCase(value)
				|| "ok".equalsIgnoreCase(value)
				|| "yes".equalsIgnoreCase(value)
				|| "y".equalsIgnoreCase(value)
				|| "on".equalsIgnoreCase(value))){
			result = true;
		}
		return result;
	}

	public static Integer toInteger(String value){
		int i = 0;
		try{
			i = Integer.parseInt(getOrElse(value).trim());
		}catch(Exception ex){}
		return i;
	}

	public static BigDecimal toBigDecimal(String value){
		BigDecimal i = null;
		try{
			i = new BigDecimal(getOrElse(value).trim());
		}catch(Exception ex){}
		return i;
	}

	public static Long toLong(String value){
		long i = 0;
		try{
			i = Long.parseLong(getOrElse(value).trim());
		}catch(Exception ex){}
		return i;
	}
	/**
	 * NULL
	 *
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

	//对象转map
	public static Map<String,Object> objectToMap(Object object){
		return JSON.parseObject(JSON.toJSONString(object), Map.class);
	}
	//map转对象
	public static <T> T mapToObject(Map map,Class<T> clazz){
		return JSON.parseObject(JSON.toJSONString(map),clazz);
	}

	/*list解析
	 *  {
	 * 		xxx属性
	 * 		List list;
	 * }
	 */
	public static  List analysis(List list){
		List listArr = new ArrayList();
		if(list != null && list.size()>0){
			for (int i = 0;i<list.size();i++){
				Map<String,Object> map = JSON.parseObject(JSON.toJSONString(list.get(i)),Map.class);
				Object obj = map.get("list");
				map.remove("list");
				if(obj instanceof  List){
					List tempArr = (List) obj;
					if (tempArr != null && tempArr.size()>0){
						for(int j = 0;j<tempArr.size();j++){
							Map map1 = JSON.parseObject(JSON.toJSONString(tempArr.get(j)), Map.class);
							if(map1 != null){
								map1.remove("list");
								map.putAll(map1);
							}
						}
					}
				}
				listArr.add(map);
			}
		}
		return  listArr;
	}


}
