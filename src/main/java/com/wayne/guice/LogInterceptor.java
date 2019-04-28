package com.wayne.guice;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;


public class LogInterceptor implements MethodInterceptor {

	private LogInterceptor() {

	}

	private static final LogInterceptor instance = new LogInterceptor();

	public static LogInterceptor getInstance() {
		return instance;
	}

	public Object invoke(MethodInvocation invocation) throws Throwable {
		Object ret = null;
		try {
			ret = invocation.proceed();
		} finally {
			Method method = invocation.getMethod();
			LogTag timecostTag = method.getAnnotation(LogTag.class);
			System.out.println("log...");
		}
		return ret;
	}

	
}
