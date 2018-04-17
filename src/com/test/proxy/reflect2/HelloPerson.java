package com.test.proxy.reflect2;

public class HelloPerson implements Person {

	@Override
	public void walk() {
		System.out.println("do walk");

	}

	@Override
	public void sayHello(String words) {
		System.out.println("say hello");
	}

}
