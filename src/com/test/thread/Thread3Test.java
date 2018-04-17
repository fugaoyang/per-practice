package com.test.thread;

public class Thread3Test {

	public static void main(String[] args) throws Exception {
		System.out.println("main方法中的状态1 =====" + Thread.currentThread().getState());
		Thread3 t31 = new Thread3();
		System.out.println("main方法中的状态2 =====" + t31.getState());
		t31.start();
		System.out.println("main方法中的状态3 =====" + t31.getState());
	
	}

}
