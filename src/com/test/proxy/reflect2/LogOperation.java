package com.test.proxy.reflect2;

import java.lang.reflect.Method;

public class LogOperation {

	public static void start(Method method) {
		// Logger.logging(Level.DEBUGE, method.getName() + " Method start .");
		System.out.println(method.getName() + "--- Method start");
	}

	public static void end(Method method) {
		// Logger.logging(Level.DEBUGE, method.getName() + " Method end .");
		System.out.println(method.getName() + "--- Method end");
	}

}
