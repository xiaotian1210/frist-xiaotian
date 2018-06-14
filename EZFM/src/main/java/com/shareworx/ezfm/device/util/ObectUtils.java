package com.shareworx.ezfm.device.util;

import com.shareworx.platform.persist.DomainModel;
import com.shareworx.platform.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by zhi.zhang on 2017/7/11.
 */
public class ObectUtils {

    /**
     * 将数据库的数据填充到对象
     * @param dataFromYours 你的数据
     * @param dataFromDatabase 数据库的数据
     */
    public static void fillData(DomainModel dataFromYours,DomainModel dataFromDatabase){
        Iterator<String > iterator = dataFromDatabase.getAttributes().iterator();
        while(iterator.hasNext()){
            String key = iterator.next();
            String attribute = dataFromYours.getAttribute(key);
            if(StringUtils.isEmpty(attribute)){
                dataFromYours.put(key,dataFromDatabase.getAttribute(key));
            }
        }
    }

    /**
     * todo:过滤对象的属性
     * @param dataFromYours
     * @param keys
     */
    public static void filter(DomainModel dataFromYours,String... keys){
        for(String item:keys){
            dataFromYours.getAttributes().remove(item);
        }
    }
}
