package com.test.thread;

import java.util.concurrent.LinkedBlockingQueue;

public class Thread4Test {

	public static void main(String[] args) throws Exception {
		
		LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();
		// 创建一个线程
		Thread thread = new Thread(() -> {
		    try {
		    	System.out.println("run方法中的状态1 =====" + Thread.currentThread().getState());
		    	System.out.println(queue.take());
		        System.out.println(queue.take());
		        System.out.println("run方法中的状态2 =====" + Thread.currentThread().getState());
		    } catch (InterruptedException e) {
		        e.printStackTrace();
		    }
		});
		// 启动线程
		thread.start();
		// 主线程挂起，保证thread线程逻辑进入并执行
		Thread.sleep(1000);
		// 主线程向队列中塞一个数据，唤醒thread线程
		System.out.println("put data ====");
		queue.put("hello world1");
		queue.put("hello world2");
		// 等待线程执行完毕
		thread.join();
		// 线程执行结束
		System.out.println("---over---");

	}

}
