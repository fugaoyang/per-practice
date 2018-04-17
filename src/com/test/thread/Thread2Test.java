package com.test.thread;

public class Thread2Test {

	public static void main(String[] args) {
		Thread2 t2 = new Thread2();
		t2.start();
		Thread t = new Thread(t2);
		System.out.println("main begin t isAlive=" + t.isAlive());
		t.setName("A");
		t.start();
		System.out.println("main end t isAlive=" + t.isAlive());

	}

}
