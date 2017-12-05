package com.test.deadLock;

public class DeadLockTest {

	public static String A = "source-A";
	public static String B = "source-B";

	public static void main(String[] args) {
		Thread a = new Thread(new Lock1(), "thread-lock1");
		Thread b = new Thread(new Lock2(), "thread-lock2");
		a.start();
		b.start();
	}
}
class Lock1 implements Runnable {
	@Override
	public void run() {
		try {
			System.out.println("----" + Thread.currentThread().getName() + "--start running");
			while (true) {
				synchronized (DeadLockTest.A) {
					System.out.println("----" + Thread.currentThread().getName() + "--lock A");
					Thread.sleep(2000);
					synchronized (DeadLockTest.B) {
						System.out.println("----" + Thread.currentThread().getName() + "--lock B");
					}
				}
			}} catch (Exception e) {
			e.printStackTrace();
		}}
}
class Lock2 implements Runnable {
	@Override
	public void run() {
		try {
			System.out.println("----" + Thread.currentThread().getName() + "--start running");
			while (true) {
				synchronized (DeadLockTest.B) {
					System.out.println("----" + Thread.currentThread().getName() + "--lock B");
					Thread.sleep(2000);
					synchronized (DeadLockTest.A) {
						System.out.println("----" + Thread.currentThread().getName() + "--lock A");
					}
				}}} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
