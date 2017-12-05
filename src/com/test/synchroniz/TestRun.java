package com.test.synchroniz;

/**
 * 1.synchronized同步方法，synchronized(this)都是对象锁，对于其他线程调用synchronized同步方法，synchronized(this)呈阻塞状态 </br>
 * 2.同一时间同一线程只有一个线程获取对象锁执行 </br>
 * 
 * 1.synchronized(非this)对象锁，对于非this如果是同一对象，两个线程同时只有一个可以获取该锁 </br>
 * 2.对象锁（synchronized同步方法 或 synchronized(this)）、synchronized(非this)对象锁 两个线程同时执行，都可获得各自的锁 </br>
 *
 * 1.synchronized修饰static方法与synchronized(X.class)作用一样
 * 
 * @author fugaoyang
 *
 */
public class TestRun {

	public static void main(String[] args) {
		Service service = new Service();
//		ThreadA threadA = new ThreadA(service);
//		threadA.setName("A");
//		threadA.start();
		
		ThreadB threadB = new ThreadB(service);
		threadB.setName("B");
		threadB.start();
		
		ThreadC threadC = new ThreadC(service);
		threadC.setName("C");
		threadC.start();
	}

}
