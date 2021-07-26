package com.util;

import java.lang.reflect.Field;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	//java反射机制
	//解析form表单
	public static <T> T handleRequest(HttpServletRequest request, Class<T> clazz) throws InstantiationException, IllegalAccessException {
		
		T form = clazz.newInstance();
		
		Enumeration<?> enumeration = request.getParameterNames();  //获取传递的参数
		while(enumeration.hasMoreElements()) {
			String name = enumeration.nextElement().toString(); //获取当前参数名
			String value = request.getParameter(name);  //获取当前参数值
			try {
				Field field = form.getClass().getDeclaredField(name);
				if(field != null) {
					field.setAccessible(true);
					field.set(form, value);
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return form;
	}
	
}
