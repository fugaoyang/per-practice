package com.test.singleton;

// 懒汉式单例/延迟加载
public class Singleton4 {
	// 私有构造
	private Singleton4() {}

	private static Singleton4 single = null;

	// 双重检查
	public static Singleton4 getInstance() {
		if (single == null) {
			synchronized (Singleton4.class) {
				if (single == null) {
					single = new Singleton4();
				}
			}
		}
		return single;
	}
}
