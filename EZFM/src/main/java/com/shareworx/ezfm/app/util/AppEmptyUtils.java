package com.shareworx.ezfm.app.util;

import java.util.Collection;
import java.util.Map;
/**
 * 判空 工具 （包括list、数组、对象、String、Map）
 * @author lingwei.li
 *
 */
public class AppEmptyUtils {
	public static boolean isEmpty(Object[] array) {
		if (array == null || array.length <= 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Collection<?> array) {
		if (array == null || array.size() <= 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Map<?, ?> array) {
		if (array == null || array.size() <= 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isEmpty(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	public static boolean isEmpty(Object str){
		if(str == null ){
			return true;
		}
		return false;
	}
	public static boolean isEmpty(int[] str){
		if(str == null || str.length<=0 ){
			return true;
		}
		return false;
	}
	/**判断int 是否大于 0 */
	public static boolean isEmpty(int str){
		if(str<=0 ){
			return true;
		}
		return false;
	}
	
	/**是否签到*/
	public static boolean isSign(String isSign){
		
		if("1".equals(isSign)){
			return true;
		}
		
		return false;
	}
	
}
