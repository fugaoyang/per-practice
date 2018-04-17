package com.test.proxy.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 利用getProxyClass生成代理对象
 * 
 */
public class TestReflectProxy1 {

	public static void main(String[] args) throws Exception, SecurityException {
		// 1、创建一个InvocationHandler对象
		InvocationHandler myInvocationHandler = new MyInvocationHandler();
		// 2、创建Person接口的一个代理类（获取代理类的字节码）
		Class proxyClass = Proxy.getProxyClass(Person.class.getClassLoader(), new Class[] { Person.class });
		// 3、根据代理类的字节码拿到它的参数为InvocationHandler类型的构造器
		Constructor constructor = proxyClass.getConstructor(new Class[] { InvocationHandler.class });
		// 4、根据构造器创建代理对象
		Person person = (Person) constructor.newInstance(myInvocationHandler);
		// 5、调用代理对象的方法
		person.walk();
		person.sayHello("hello");

	}

}
