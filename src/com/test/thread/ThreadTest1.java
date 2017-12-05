package com.test.thread;

public class ThreadTest1 {

	public static void main(String[] args) {
		Thread1 t1 = new Thread1();
		t1.start();
		t1.interrupt();
		//Thread.currentThread().interrupt();
		System.out.println("t1.getName() =======" + t1.getName());
		System.out.println("Thread.currentThread().getName() ======= " + Thread.currentThread().getName());
		System.out.println("t1.isInterrupted() ===== " + t1.isInterrupted());
        System.out.println("main end =======");
	}

}
