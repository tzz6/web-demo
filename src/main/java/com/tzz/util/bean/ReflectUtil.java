package com.tzz.util.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

import com.tzz.web.domain.User;

/**
 * ReflectUtil
 *
 */
public class ReflectUtil {
	
	/** 方法--属性复制 */
	public void fieldCopy(Object source, Object target) throws Exception {
		Method[] methods = source.getClass().getDeclaredMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			System.out.println(methodName);
			if (methodName.startsWith("get")) {
				Object value = method.invoke(source, new Object[0]);
				System.out.println(value);
				String setMethodName = methodName.replaceFirst("(get)", "set");
				Method setMethod = target.getClass().getMethod(setMethodName, method.getReturnType());
				setMethod.invoke(target, value);
			}
		}
	}

	/** 属性字段名、值、数据类型 */
	public void getFields(Object object) throws Exception {
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			String classType = field.getType().toString();
			int lastIndex = classType.lastIndexOf(".");
			classType = classType.substring(lastIndex + 1);
			System.out.println("fieldName：" + field.getName() + ",type:" + classType + ",value:" + field.get(object));
		}
	}

	@Test
	public void test() throws Exception {
		User user = new User();
		user.setId(1L);
		user.setName("AAA");
		User user2 = new User();
		fieldCopy(user, user2);
		getFields(user2);
	}
}
