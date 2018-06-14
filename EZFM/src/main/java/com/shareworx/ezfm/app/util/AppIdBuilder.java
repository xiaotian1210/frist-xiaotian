package com.shareworx.ezfm.app.util;

import java.util.Date;
import java.util.UUID;
/**
 * 
 * @author lingwei.li
 * id 生成规则
 */
public class AppIdBuilder {
	public static String createRandomId() {
		long timestamp = new Date().getTime();
		StringBuffer id = new StringBuffer(timestamp+"");
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		int random = (int)(Math.random()*10);
		uuid = uuid.substring(random, random+7);
		
		char[] chars = uuid.toCharArray();
		for(char ch : chars){
			int index = (int)(Math.random()*10);
			id.insert(index, ch);
		}
		return id.toString();
	}
	
	public static String createTimestamepId() {
		long timestamp = new Date().getTime();
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		int random = (int)(Math.random()*10);
		uuid = uuid.substring(random, random+7);
		return timestamp+uuid;
	}
	public static void main(String[] args) {
		String id1 = createRandomId();
		System.out.println(id1);
		String id2 = createTimestamepId();
		System.out.println(id2);
	}
}
