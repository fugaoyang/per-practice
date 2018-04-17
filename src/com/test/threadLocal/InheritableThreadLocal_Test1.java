package com.test.threadLocal;

/**
 * ThreadLocal,InheritableThreadLocal的区别
 * https://www.cnblogs.com/sweetchildomine/p/6575666.html <br>
 * 
 * ThreadLocal共享变量实现为线程私有变量
 * InheritableThreadLocal作为子线程时，可以继承父线程的私有变量
 * 
 * @author fugaoyang
 *
 */
public class InheritableThreadLocal_Test1 {

	static final ThreadLocal<Integer> threadParam = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}

	};

	public static void main(String[] args) {
		// main线程 得到的是默认值0
		System.out.println("main > " + Thread.currentThread().getName() + " " + threadParam.get());

		int i = 50;
		int j = 50;
		while (i > 0) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					// 每个线程进入时，拿到默认值0
					System.out.println("t1 > " + Thread.currentThread().getName() + " " + threadParam.get());
					threadParam.set(1);
					// 设置完，当前线程拿到的设置过的值
					System.out.println("t1 set1 >" + Thread.currentThread().getName() + " " + threadParam.get());
				    
					new Thread(new Runnable(){
						@Override
						public void run(){
							// 对于ThreadLocal，t1内部子线程 得到的是默认值0，而非父t1线程的值，是因为ThreadLocal共享变量实现为线程私有变量
							// 对于InheritableThreadLocal，得到的是父t1线程的值
							System.out.println("t1内部线程 > " + Thread.currentThread().getName() + " " + threadParam.get());
						}
					}).start();
				}
			});
			t1.start();
			i--;
		}
		
		while (j > 0) {
			Thread t2 = new Thread(new Runnable() {
				@Override
				public void run() {
					System.out.println("t2 > " + Thread.currentThread().getName() + " " + threadParam.get());
					//threadParam.set(1);
					//System.out.println("t2 set1 >" + Thread.currentThread().getName() + " " + threadParam.get());
				}
			});
			t2.start();
			j--;
		}

	}

}
