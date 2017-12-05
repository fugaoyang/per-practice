package com.test.singleton;

// 懒汉式单例/延迟加载
public class Singleton2 {
	// 私有构造
	private Singleton2() {}

	private static Singleton2 single = null;

	// 静态工厂方法
	public static Singleton2 getInstance() {
		if(single == null){
			single = new Singleton2();
		}
		return single;
	}
}
