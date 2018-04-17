package com.test.thread;

public class Thread6Test {

	public static void main(String[] args) throws Exception {
		final String lock = new String("");
		Service6 service = new Service6();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				service.add(lock);
			}
		};

		Thread t2 = new Thread() {
			@Override
			public void run() {
				try {
					service.subtract(lock);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		
		Thread t3 = new Thread() {
			@Override
			public void run() {
				try {
					service.subtract(lock);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		t1.setName("ADD");
		t2.setName("SUB1");
		t3.setName("SUB2");
		
		t2.start();
		t3.start();
		Thread.sleep(1000);
		
		System.out.println("线程" + t2.getName() + "--状态 =" + t2.getState()) ;
		System.out.println("线程" + t3.getName() + "--状态 =" + t3.getState()) ;
		System.out.println("线程" + t1.getName() + "--状态 =" + t1.getState()) ;

		t1.start();
		
		Thread.sleep(1000);
		
		System.out.println("线程" + t2.getName() + "--状态 =" + t2.getState()) ;
		System.out.println("线程" + t3.getName() + "--状态 =" + t3.getState()) ;
		System.out.println("线程" + t1.getName() + "--状态 =" + t1.getState()) ;
		
	}

}
