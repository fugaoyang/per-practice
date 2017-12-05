package com.test.thread;

public class Thread1 extends Thread{

	public Thread1(){
		System.out.println("构造函数---this.getName() = " + this.getName());
		System.out.println("构造函数---Thread.currentThread().getName() = " + Thread.currentThread().getName());
	}

	@Override
	public void run() {
		super.run();
		System.out.println("run方法---this == Thread.currentThread() " + (this==Thread.currentThread()) );
		System.out.println("run方法---this.getName() = " + this.getName());
		System.out.println("run方法---Thread.currentThread().getName() = " + Thread.currentThread().getName());
		for(int i=0;i<500000;i++){
			if(Thread.currentThread().isInterrupted()){
				System.out.println("i = " + i + "线程中断");
				break;
			}
			System.out.println("i = " + i);
		}
	}
	
}
