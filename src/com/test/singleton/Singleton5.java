package com.test.singleton;

// 懒汉式单例/延迟加载
public class Singleton5 {
	// 私有构造
	private Singleton5() {
	}

	private static Singleton5 single = null;

	// 双重检查
	public static Singleton5 getInstance() {
		try {

			if (single == null) {

				Thread.sleep(3000);
				synchronized (Singleton5.class) {
					// if (single == null) {
					single = new Singleton5();
					// }
				}
			}
			return single;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return single;
	}
}
