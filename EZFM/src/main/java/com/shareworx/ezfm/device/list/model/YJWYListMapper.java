package com.shareworx.ezfm.device.list.model;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * 设备列表自定义实体类Mapper
 * 
 * @author jin.li
 *
 */
public class YJWYListMapper implements RowMapper<YJWYListModel> {

	public YJWYListModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		YJWYListModel model = new YJWYListModel();
		try {
			// 获取实体类信息对象
			BeanInfo beanInfo = Introspector.getBeanInfo(model.getClass());
			// 实体类属性集合
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			// 属性名
			String paramName = null;
			// set方法
			Method setMethod = null;
			// rs的value
			Object value = null;
			// value的类型
			String valueType = null;
			String paramType = null;
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				paramName = propertyDescriptor.getName();
				if ("class".equals(paramName)) {
					continue;
				}
				paramType = propertyDescriptor.getPropertyType().getName();
				setMethod = propertyDescriptor.getWriteMethod();
				value = rs.getObject(paramName);
				if (value != null) {
					valueType = value.getClass().getName();
					value = value.toString();
				}
				setMethod.invoke(model, value);
			}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return model;
	}

}
