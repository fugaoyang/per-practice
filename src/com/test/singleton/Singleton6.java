package com.test.singleton;

// 静态代码块
public class Singleton6 {
	
	// 私有构造
	private Singleton6() {}
	
	private static Singleton6 single = null;

	// 静态代码块
	static{
		single = new Singleton6();
	}
	
	public static Singleton6 getInstance() {
		return single;
	}
}
