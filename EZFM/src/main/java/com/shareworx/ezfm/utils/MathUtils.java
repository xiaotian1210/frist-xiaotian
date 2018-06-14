package com.shareworx.ezfm.utils;

import java.text.DecimalFormat;

import com.shareworx.platform.util.StringUtils;

public class MathUtils {
	/**
	 * 計算百分比%
	 * @param x
	 * @param total
	 * @return
	 */
	public static String getPercent(String a,String b){
		int x = Integer.valueOf(StringUtils.isEmpty(a)?"0":a);
		int y = Integer.valueOf(StringUtils.isEmpty(b)?"0":b);
		if(x==0 || y==0){
			return null;
		}
		
		String result="";//结果值
		double x_double=x*1.0;
		double tempresult=x_double/y;
		//NumberFormat nf   =   NumberFormat.getPercentInstance();     注释掉的也是一种方法
		//nf.setMinimumFractionDigits( 2 );        保留到小数点后几位
		DecimalFormat df1 = new DecimalFormat("0.00%");    //##.00%   百分比格式，后面不足2位的用0补齐
		//result=nf.format(tempresult);
		result= df1.format(tempresult);
		return result;
	}
	/**
	 * 除法计算，保留两位小数，不足两位用0补齐
	 * @param x
	 * @param total
	 * @return
	 */
	public static String forDivision(String a,String b){
		int x = Integer.valueOf(StringUtils.isEmpty(a)?"0":a);
		int y = Integer.valueOf(StringUtils.isEmpty(b)?"0":b);
		if(x==0 || y==0){
			return null;
		}
		String result="";//接受百分比的值
		double x_double=x*1.0;
		double tempresult=x_double/y;
		DecimalFormat df1  = new DecimalFormat("0.00");    //##.00%   百分比格式，后面不足2位的用0补齐
		result= df1.format(tempresult);
		return result;
	}
	
}
