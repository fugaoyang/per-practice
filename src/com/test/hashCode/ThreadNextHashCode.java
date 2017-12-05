package com.test.hashCode;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadNextHashCode {

	private static final int HASH_INCREMENT = 0x61c88647;

	private static final int INITIAL_CAPACITY = 16;

	private static AtomicInteger nextHashCode = new AtomicInteger();

	private static int nextHashCode() {
		return nextHashCode.getAndAdd(HASH_INCREMENT);
	}
	
	public static void print(){
		int threadLocalHashCode = nextHashCode();
		System.out.println(threadLocalHashCode);
		
		int i = threadLocalHashCode & (INITIAL_CAPACITY - 1);
		System.out.println(i);
	}

	// private final int threadLocalHashCode = nextHashCode();

	public static void main(String[] args) {
		
		int threadLocalHashCode = nextHashCode();
		System.out.println(threadLocalHashCode);
		
		int i = threadLocalHashCode & (INITIAL_CAPACITY - 1);
		System.out.println(i);
		
	}

}
