package com.test.singleton;

// 饿汉式单例/立即加载
public class Singleton1 {
	// 私有构造
	private Singleton1() {}

	private static Singleton1 single = new Singleton1();

	public static Singleton1 getInstance() {
		return single;
	}
}
