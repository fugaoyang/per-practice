package com.test.proxy.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 利用newProxyInstance生成代理对象
 * 
 */
public class TestReflectProxy2 {

	public static void main(String[] args) throws Exception, SecurityException {
		// 1、创建一个InvocationHandler对象
		InvocationHandler myInvocationHandler = new MyInvocationHandler();
		// 2、利用Proxy类的静态方法newProxyInstance直接生成Person接口的代理对象
		Person person = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[] { Person.class },
				myInvocationHandler);
		// 3、调用代理对象的方法
		person.walk();
		person.sayHello("hello");

	}

}
