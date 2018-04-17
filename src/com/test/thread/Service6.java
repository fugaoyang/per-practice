package com.test.thread;

public class Service6 {
	
	public void add(String lock){
		System.out.println("当前线程" + Thread.currentThread().getName() + " 进入add，--状态 =" + Thread.currentThread().getState()) ;

		synchronized(lock){
			System.out.println("当前线程" + Thread.currentThread().getName() + " 进入add synchronized块，--状态 =" + Thread.currentThread().getState()) ;
			if(ValueObject.list.size() == 0 ){
				System.out.println("当前线程" + Thread.currentThread().getName() + " 执行add list--状态 =" + Thread.currentThread().getState()) ;
				ValueObject.list.add("java");
				System.out.println("当前线程" + Thread.currentThread().getName() + " begin notifyAll--状态 =" + Thread.currentThread().getState()) ;
				lock.notifyAll();
				System.out.println("当前线程" + Thread.currentThread().getName() + " after notifyAll--状态 =" + Thread.currentThread().getState()) ;
			}
		}
		System.out.println("当前线程" + Thread.currentThread().getName() + " 退出add synchronized块--状态 =" + Thread.currentThread().getState()) ;

	}
	
	public void subtract(String lock) throws Exception{
		System.out.println("当前线程" + Thread.currentThread().getName() + " 进入subtract，--状态 =" + Thread.currentThread().getState()) ;

		synchronized(lock){
			System.out.println("当前线程" + Thread.currentThread().getName() + " 进入subtract synchronized块--状态 =" + Thread.currentThread().getState()) ;

			while(ValueObject.list.size() == 0 ){
				System.out.println("当前线程" + Thread.currentThread().getName() + " begin wait--状态 =" + Thread.currentThread().getState()) ;

				lock.wait();
				
				System.out.println("当前线程" + Thread.currentThread().getName() + " after wait--状态 =" + Thread.currentThread().getState()) ;
			}
			System.out.println("当前线程" + Thread.currentThread().getName() + " begin remove--状态 =" + Thread.currentThread().getState()) ;
			
			ValueObject.list.remove(0);
			
			System.out.println("当前线程" + Thread.currentThread().getName() + " list size = " + ValueObject.list.size() +"--状态 =" + Thread.currentThread().getState()) ;
		}
		System.out.println("当前线程" + Thread.currentThread().getName() + " 退出subtract synchronized块--状态 =" + Thread.currentThread().getState()) ;

	}

}
