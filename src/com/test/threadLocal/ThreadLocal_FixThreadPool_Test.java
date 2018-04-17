package com.test.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal配合线程池使用的一些坑
 * https://www.cnblogs.com/sweetchildomine/p/6575666.html <br>
 * 
 * @author fugaoyang
 *
 */
public class ThreadLocal_FixThreadPool_Test {

	static final ThreadLocal<Integer> threadParam = new ThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}

	};

	public static void main(String[] args) throws InterruptedException {
		// main线程 得到的是默认值0
		System.out.println("main > " + Thread.currentThread().getName() + " " + threadParam.get());

		ExecutorService execService =  Executors.newFixedThreadPool(10);
		
		int i = 50;
		while (i > 0) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {
					// 每个线程进入时，拿到默认值0
					//System.out.println("t1 > " + Thread.currentThread().getName() + " " + threadParam.get());
					threadParam.set(1);
					// 设置完，当前线程拿到的设置过的值
					System.out.println("t1 set1 >" + Thread.currentThread().getName() + " " + threadParam.get());
					
					// #####如果不调用remove()，将会引发t2线程拿到t1的ThreadLocal私有变量值####
					// 原因：固定线程池把线程提交到队列,线程池会根据容量开辟新的线程，当达到固定容量时，不再新建线程，而是从线程池里获取空闲的线程
					// 被重新获取的线程,仅仅是执行了run方法，从而可以直接获取原空闲线程的私有线程变量
					
					// threadParam.remove();
				}
			});
			execService.execute(t1);
			
			TimeUnit.SECONDS.sleep(1);
			
			Thread t2 = new Thread(){
				@Override
				public void run(){
					System.out.println("t2 > " + Thread.currentThread().getName() + " " + threadParam.get());
				}
			};
			
			execService.execute(t2);
			
			i--;
		}
		
	}

}
