package com.test.proxy.reflect2;

/**
 * 利用getProxyClass生成代理对象
 * 
 */
public class TestReflectProxy1 {
	public static void main(String[] args) throws Exception, SecurityException {
		Person hello = (Person) new DynaProxyInvocation().getProxy(new HelloPerson(), new LogOperation());
		hello.walk();
		hello.sayHello("Double J");
	}

}
