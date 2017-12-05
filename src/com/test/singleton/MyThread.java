package com.test.singleton;

public class MyThread extends Thread{

	@Override
	public void run() {
		System.out.println(Singleton5.getInstance().hashCode());
	}

	
}
