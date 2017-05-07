package com.test.reentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/*
 * java ReentrantLock condition
 * 模拟生产者、消费者
 * 在add 和 remove中使用Condition来代替监视器锁的wait操作和唤醒操作。
       值得注意的是：  
1. 读写线程数量如果不对等，将会出现死锁。 
2. 所有的条件Condition必须使用对等的锁对象来创建lock.newCondition(); 
3. 条件必须用在lock() 和 unlock() 方法之间。 
4. 在判定条件是否满足，需要在循环中判定，未满足条件的不能离开循环体，否则数据将得不到我们想要的结果 
5. 调用await()方法进入休眠的线程可能会被中断，所以必须处理InterruptedException 异常
 */
public class ConditionTest {
	public static void main(String args[]){
		final ConditionDemo demo = new ConditionDemo();
		for(int i=0 ;i<5;i++){
			new Thread(new Runnable(){

				@Override
				public void run() {
					demo.remove();
					
				}
				
			}).start();
		}
		
		for(int i=0 ;i<5;i++){
			new Thread(new Runnable(){

				@Override
				public void run() {
					demo.add();
					
				}
				
			}).start();
		}
		
	}
}	
	 class ConditionDemo{
		private final ReentrantLock lock = new ReentrantLock();
		private Long depot = null;  //充当仓库
		private Condition addCon;
		private Condition removeCon;
		private int count = 1;
		
		public ConditionDemo(){
			this.addCon = lock.newCondition();
			this.removeCon = lock.newCondition();
		}
		
		public void add(){
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "--生产拿到lock--" );
			try{
				while(depot != null){
					addCon.await();
				}
				depot = (long) count ++;
				System.out.println(Thread.currentThread().getName() + "--设置了--" +depot);
				removeCon.signalAll();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				lock.unlock();
				System.out.println(Thread.currentThread().getName() + "--释放了生产lock--" );
			}
		}
		
		public void remove(){
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "--消费拿到lock--" );

			try {
				while(depot == null){
					removeCon.await();
				}
				System.out.println(Thread.currentThread().getName() + "--取出了--" +depot);
				depot = null;
				addCon.signalAll();
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				lock.unlock();
				System.out.println(Thread.currentThread().getName() + "--释放了消费lock--" );
			}
		}
	}

