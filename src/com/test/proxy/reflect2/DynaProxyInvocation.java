package com.test.proxy.reflect2;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynaProxyInvocation implements InvocationHandler {
	/**
	 * 代理对象（实际处理业务对象）
	 */
	private Object proxy;

	/**
	 * 增强类对象(日志处理对象)
	 */
	private Object delegate;

	/**
	 * 获取一个目标对象（目标类必须实现接口,proxy是实现类）的代理对象
	 */
	public Object getProxy(Object proxy, Object delegate) {
		this.proxy = proxy;
		this.delegate = delegate;
		return Proxy.newProxyInstance(this.proxy.getClass().getClassLoader(), this.proxy.getClass().getInterfaces(),
				this);
	}

	/**
	 * 要处理的对象中的每个方法会被此方法送去JVM调用,也就是说,要处理的对象的方法只能通过此方法调用 此方法是动态的,不是手动调用的
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		try {
			// 反射得到操作者的实例
			Class clazz = this.delegate.getClass();
			// 反射得到操作者的Start方法
			Method start = clazz.getDeclaredMethod("start", new Class[] { Method.class });
			// 反射执行start方法
			start.invoke(this.delegate, new Object[] { method });
			// 通过反射执行代理对象的原本方法
			result = method.invoke(this.proxy, args);
			// 反射得到操作者的end方法
			Method end = clazz.getDeclaredMethod("end", new Class[] { Method.class });
			// 反射执行end方法
			end.invoke(this.delegate, new Object[] { method });
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}