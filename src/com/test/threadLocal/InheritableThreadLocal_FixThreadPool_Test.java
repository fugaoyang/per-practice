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
public class InheritableThreadLocal_FixThreadPool_Test {

	static final ThreadLocal<Integer> threadParam = new InheritableThreadLocal<Integer>() {
		@Override
		protected Integer initialValue() {
			return 0;
		}

	};

	public static void main(String[] args) throws InterruptedException {
		// main线程 得到的是默认值0
		System.out.println("main > " + Thread.currentThread().getName() + " " + threadParam.get());

		ExecutorService execService =  Executors.newFixedThreadPool(4);
		
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
					
					// threadParam.remove();
					
					Thread innerA = new Thread(new Runnable(){
						@Override
						public void run(){
							System.out.println("innerA > " + Thread.currentThread().getName() + " " + threadParam.get());

						}
					});
					
					execService.execute(innerA);
				}
			});
			execService.execute(t1);
			
			//TimeUnit.SECONDS.sleep(1);
			
			Thread t2 = new Thread(){
				@Override
				public void run(){
					threadParam.set(2);
					System.out.println("t2 > " + Thread.currentThread().getName() + " " + threadParam.get());
					//threadParam.remove();
				}
			};
			
			execService.execute(t2);
			
			i--;
		}
		
	}

}
