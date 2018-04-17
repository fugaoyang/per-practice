package com.test.thread;

import java.util.concurrent.LinkedBlockingQueue;

public class Thread5Test {

	public static void main(String[] args) throws Exception {
		final Object lock = new Object();
		Thread t1 = new Thread() {
			@Override
			public void run() {

				int i = 0;

				while (true) {
					synchronized (lock) {
						System.out.println("当前同步块--" + Thread.currentThread().getName() + "--进入");
						try {
							lock.wait();
						} catch (InterruptedException e) {
						}
						System.out.println("当前同步块--" + Thread.currentThread().getName() + "--退出");
					}
				}
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {

				while (true) {
					synchronized (lock) {
						System.out.println("当前同步块--" + Thread.currentThread().getName() + "--进入");
						lock.notifyAll();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						System.out.println("当前同步块--" + Thread.currentThread().getName() + "--退出");

					}

				}
			}
		};

		t1.setName("A");
		t2.setName("B");

		t1.start();
		t2.start();
	}

}
