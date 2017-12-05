package com.test.singleton;

// 懒汉式单例/延迟加载
public class Singleton3 {
	// 私有构造
	private Singleton3() {}

	private static Singleton3 single = null;

    public static Singleton3 getInstance() {
		
		// 等同于 synchronized public static Singleton3 getInstance()
    	synchronized(Singleton3.class){
    		if(single == null){
    			single = new Singleton3();
    		}
    	}
		return single;
	}
}
