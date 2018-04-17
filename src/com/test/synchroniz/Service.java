package com.test.synchroniz;

public class Service {
	
	public Service(){
		System.out.println("当前线程：" + Thread.currentThread().getName() + " 构造方法");
	}
	
	static{
		System.out.println("当前线程：" + Thread.currentThread().getName() + " 静态代码块");
	}
	
	private Object object1 = new Object();
	private Object object2 = new Object();
	
	synchronized public static void printA(){
		try{
			System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 进入方法A");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName()  + Thread.currentThread().getId()  +"在 " + System.currentTimeMillis() + " 退出方法A");
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	synchronized public void printB(){
		try{
			System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 进入方法B");
			Thread.sleep(3000);
			System.out.println("当前线程：" + Thread.currentThread().getName()  + Thread.currentThread().getId()  +"在 " + System.currentTimeMillis() + " 退出方法B");
		}catch(Exception e){
			System.out.println(e);
		}
	}
	
	 public void printC(){
		 System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 进入方法C");
		try{
			synchronized(object1){
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + "进入方法C--synchronized{X}");
				Thread.sleep(3000);
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 退出方法C-synchronized{X}");
			}
			System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 退出方法C");
			}catch(Exception e){
			System.out.println(e);
		}
	}

	
	 public void printD(){
		 System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 进入方法D");
		try{
			synchronized(object2){
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + "进入方法D--synchronized{X}");
				Thread.sleep(3000);
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 退出方法D-synchronized{X}");
			}
			System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 退出方法D");
			}catch(Exception e){
			System.out.println(e);
		}
	}
	 
	 public void printE(){
		 System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 进入方法E");
		try{
			synchronized(this){
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 进入方法E--synchronized{this}");
				Thread.sleep(3000);
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 退出方法E-synchronized{this}");
			}
			System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 退出方法E");
			}catch(Exception e){
			System.out.println(e);
		}
	}
	 
	 public static void printF(){
		 System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 进入方法E");
		try{
			synchronized(Service.class){
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 进入方法F--synchronized{class}");
				Thread.sleep(3000);
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 退出方法F-synchronized{class}");
			}
			System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 退出方法F");
			}catch(Exception e){
			System.out.println(e);
		}
	}
	 
	public void printG(){
		 System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 进入方法G");
		try{
			synchronized(Service.class){
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 进入方法G--synchronized{class}");
				Thread.sleep(3000);
				System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 退出方法G-synchronized{class}");
			}
			System.out.println("当前线程：" + Thread.currentThread().getName() + Thread.currentThread().getId() +"在 " + System.currentTimeMillis() + " 退出方法G");
			}catch(Exception e){
			System.out.println(e);
		}
	}



}
