package com.test.singleton;

// 静态内部类
public class Singleton7 {
	// 私有构造
	private Singleton7() {}

	// 静态内部类
	private static class InnerObject{
		private static Singleton7 single = new Singleton7();
	}
	
	public static Singleton7 getInstance() {
		return InnerObject.single;
	}
}
