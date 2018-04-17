package com.test.proxy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理实现
 */
public class MyInvocationHandler implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("正在执行方法：" + method.getName());
		if (args != null) {
			System.out.println("下面是执行方法时传入的实参：");
			for (Object object : args) {
				System.out.println(object);
			}
		} else {
			System.out.println("该方法调用无实参！");
		}
		return null;
	}

}
