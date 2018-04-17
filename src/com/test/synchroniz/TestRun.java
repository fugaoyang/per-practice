package com.test.synchroniz;

/**
 * 0.synchronized同步方法、synchronized静态同步方法分别是用到的是实例锁，类锁，一个线程获取到synchronized同步方法的锁时，
 * 另一线程依然可以进入synchronized静态同步方法（实例锁，类锁两者不同，相互不影响
 * ）
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

	public static void main(String[] args) throws Exception {
		System.out.println("当前线程：" + Thread.currentThread().getName() + " main函数");
		Service service = new Service();
		Thread threadA = new Thread("A"){
			@Override
			public void run(){
				service.printA();
			}
		};
		
		Thread threadB = new Thread("B"){
			@Override
			public void run(){
				service.printB();
			}
		};
		
		Thread threadC = new Thread("C"){
			@Override
			public void run(){
				service.printC();
			}
		};
		
		Thread threadD = new Thread("D"){
			@Override
			public void run(){
				service.printD();
			}
		};
		
		Thread threadE = new Thread("E"){
			@Override
			public void run(){
				service.printE();
			}
		};
		
		Thread threadF = new Thread("F"){
			@Override
			public void run(){
				service.printF();
			}
		};
		
		Thread threadG = new Thread("G"){
			@Override
			public void run(){
				service.printG();
			}
		};
		
		threadA.start();
		//threadB.start();
		//threadC.start();
		//threadD.start();
		//threadE.start();
		threadF.start();
		threadG.start();
		
		threadA.join();
		threadF.join();
		threadG.join();
	}
}
