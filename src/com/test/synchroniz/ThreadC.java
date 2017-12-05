package com.test.synchroniz;

public class ThreadC extends Thread{
	private Service service;
	
	public ThreadC(Service service){
		this.service = service;
	}
	
	@Override
	public void run(){
		service.printC();
	}


}
