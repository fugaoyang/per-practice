package com.test.synchroniz;

public class Service {
	private Object object1 = new Object();
	private Object object2 = new Object();
	synchronized public static void printA(){
		try{
			System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 进入方法printA");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName()  + Thread.currentThread().getId()  +"在 " + System.currentTimeMillis() + " 退出方法printA");
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	 public  void printB(){
		try{
			synchronized(object2){
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 进入方法printB");
				Thread.sleep(10000);
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 退出方法printB");
			
			}
			}catch(Exception e){
			System.out.println(e);
		}
	}

	
	 public void printC(){
		try{
			synchronized (object1){
				System.out.println("当前线程：" + Thread.currentThread().getName()+ Thread.currentThread().getId()  + "在 " + System.currentTimeMillis() + " 进入方法printC");
				Thread.sleep(3000);
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 退出方法printC");
			
			}
		}catch(Exception e){
			System.out.println(e);
		}
	}


}
