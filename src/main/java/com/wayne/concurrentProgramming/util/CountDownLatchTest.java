package com.wayne.concurrentProgramming.util;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
	
	public static void main(String[] args) {
//		CountDownLatch
		try {
			MyService service = new MyService();
			MyThread t = new MyThread(service);
			t.start();
			Thread.sleep(2000);
			service.downMethod();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

class MyThread extends Thread {
	
	private MyService myService;
	
	public MyThread(MyService myService) {
		super();
		this.myService = myService;
	}
	
	@Override
	public void run(){
		myService.testMethod();
	}
}

class MyService {
	
	private CountDownLatch down = new CountDownLatch(1);
	
	public void testMethod(){
		try {
			System.out.println("A");
			down.await();
			System.out.println("B");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void downMethod() {
		System.out.println("X");
		down.countDown();
	}
	
}