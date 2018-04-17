package com.test.thread;

public class Thread2 extends Thread{
	public Thread2(){
        System.out.println("CountOperate---begin");
        System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());//获取线程名
        System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive()); //查看线程是否存活
        System.out.println("this.getName=" + this.getName()); 
        System.out.println("this.isAlive()=" + this.isAlive());
        System.out.println("CountOperate---end ");
        System.out.println("Thread.currentThread()==this :"+ (Thread.currentThread() == this));
    }

    @Override
    public void run() {
        System.out.println("run---begin");
        System.out.println("Thread.currentThread().getName=" + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()" + Thread.currentThread().isAlive());
        System.out.println("Thread.currentThread()==this :"+ (Thread.currentThread() == this));
        System.out.println("this.getName()=" + this.getName());
        System.out.println("this.isAlive()=" + this.isAlive());
        System.out.println("run --- end");
    }

}
