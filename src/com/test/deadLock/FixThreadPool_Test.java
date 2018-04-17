package com.test.deadLock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * 线程池同时执行线程和线程的子线程会导致死锁吗？<br>
 * 解释：对于newFixedThreadPool，看源码是LinkedBlockingQueue无界队列，当程序执行时，不管是线程，子线程都放入线程池，
 * 当到达最大可用时则放入队列排队等候，因为放的这个动作是不会阻塞，所以不会产生死锁。
 * 
 * 对于我们的系统，线程池是固定容量，放入线程这个动作(执行方法体)是同步的，当可用线程不够时，这个同步的动作会抛异常，导致死锁。
 * 比如5个容量的TTTTT，当容量满时，其中一个T比如发短信要执行这个同步的方法
 * 
 * @author fugaoyang
 *
 */
public class FixThreadPool_Test {

	public static void main(String[] args) throws InterruptedException {
		// main线程 得到的是默认值0
		System.out.println("main > " + Thread.currentThread().getName());

		//ExecutorService execService = Executors.newFixedThreadPool(4);
		ExecutorService execService = new ThreadPoolExecutor(4, 4,
                 0L, TimeUnit.MILLISECONDS,
                 new LinkedBlockingQueue<Runnable>(50));

		ThreadPoolExecutor pool = (ThreadPoolExecutor) execService;

		int i = 50;
		while (i > 0) {
			Thread t1 = new Thread(new Runnable() {
				@Override
				public void run() {

					try {
						System.out.println("t1> " + Thread.currentThread().getName());

						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					Thread innerA = new Thread(new Runnable() {
						@Override
						public void run() {
							 try {
							 TimeUnit.SECONDS.sleep(3);
							 } catch (InterruptedException e) {
							 e.printStackTrace();
							 }
							System.out.println("innerA > " + Thread.currentThread().getName());

						}
					});

					execService.execute(innerA);
				}
			});
			execService.execute(t1);
			System.out.println("t1 exec > " + Thread.currentThread().getName());

			System.out.println("Core threads: " + pool.getCorePoolSize()); // 核心4
			System.out.println("Largest executions: " + pool.getLargestPoolSize());  // 1-4
			System.out.println("Maximum allowed threads: " + pool.getMaximumPoolSize()); // 核心4
			System.out.println("Current threads in pool: " + pool.getPoolSize()); // 1-4
			System.out.println("Currently executing threads: " + pool.getActiveCount()); // 1-4
			System.out.println("Total number of threads(ever scheduled): " + pool.getTaskCount()); // 50

			// TimeUnit.SECONDS.sleep(1);
			//
			// Thread t2 = new Thread(){
			// @Override
			// public void run(){
			// threadParam.set(2);
			// System.out.println("t2 > " + Thread.currentThread().getName());
			// }
			// };
			//
			// execService.execute(t2);

			i--;
		}

	}
	

}
