package com.test.thread;

public class Thread3 extends Thread{

	@Override
	public void run(){
		System.out.println("run方法中当前状态====" + Thread.currentThread().getState());
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
