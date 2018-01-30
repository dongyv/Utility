package Util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @Description: 实体类 to Map
 */
public class BeanMapUtil {
	public static Object convertMap2Bean(Class<?> type, Map<String, Object> map)
			throws IntrospectionException, IllegalAccessException,
			InstantiationException, InvocationTargetException {
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		Object obj = type.newInstance();

		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];

			String propertyName = "";
			if (descriptor.getName().indexOf("_") != -1)
				propertyName = descriptor.getName();
			else {
				propertyName = fieldToProperty(descriptor.getName());
			}
			if (map.containsKey(propertyName)) {
				Object value = map.get(propertyName);
				Object[] args = new Object[1];
				args[0] = value;
				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

	public static Map<String, Object> bean2Map(Object bean)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		Class<? extends Object> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];

			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null)
					returnMap.put(propertyName.toUpperCase(), result);
				else {
					returnMap.put(propertyName.toUpperCase(), null);
				}
			}
		}
		return returnMap;
	}
	
	
	public static Map<String, Object> bean2Map1(Object bean)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		Class<? extends Object> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];

			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null)
					returnMap.put(propertyName.toLowerCase(), result);
				else {
					returnMap.put(propertyName.toLowerCase(), null);
				}
			}
		}
		return returnMap;
	}
	
	public static Map<String, Object> bean2Map2(Object bean)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		Class<? extends Object> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];

			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null)
					returnMap.put(propertyName, result);
				else {
					returnMap.put(propertyName, null);
				}
			}
		}
		return returnMap;
	}
	
	public static Map<String, Object> convertBean2Map(Object bean)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		Class<? extends Object> type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];

			String propertyName = "";
			if (descriptor.getName().indexOf("_") != -1)
				propertyName = descriptor.getName();
			else {
				propertyName = propertyToField(descriptor.getName());
			}
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null)
					returnMap.put(propertyName, result);
				else {
					returnMap.put(propertyName, null);
				}
			}
		}
		return returnMap;
	}
	
	public static Map<String, String> bean2MapString(Object bean)
			throws IntrospectionException, IllegalAccessException,
			InvocationTargetException {
		Class<? extends Object> type = bean.getClass();
		Map<String, String> page = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String obj1, String obj2) {
						// 生序排序
						return obj1.compareTo(obj2);
					}
				});// 使用treemap方便排序
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];

			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null)
					page.put(propertyName, result.toString());
				else {
					page.put(propertyName, null);
				}
			}
		}
		return page;
	}

	public static String propertyToField(String property) {
		if (property == null) {
			return "";
		}
		char[] chars = property.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (char c : chars) {
			if (CharUtils.isAsciiAlphaUpper(c))
				sb.append("_" + StringUtils.lowerCase(CharUtils.toString(c)));
			else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public static String fieldToProperty(String field) {
		if (field == null) {
			return "";
		}
		char[] chars = field.toCharArray();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == '_') {
				int j = i + 1;
				if (j < chars.length) {
					sb.append(StringUtils.upperCase(CharUtils
							.toString(chars[j])));
					i++;
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
