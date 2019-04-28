package com.wayne.concurrentProgramming.util;

import java.util.concurrent.Semaphore;

public class SemaphoreTest {

	public static void main(String[] args) throws InterruptedException {
		
		Service service = new Service();
		ThreadA a = new ThreadA(service);
		a.setName("A");
		a.start();
		ThreadB b = new ThreadB(service);
		b.setName("B");
		b.start();
		
		Thread.sleep(600);
		
		b.interrupt();
		System.out.println("main 中断了b");
		
	}
	
}

class ThreadA extends Thread{
	private Service service;
	
	public ThreadA(Service service){
		super();
		this.service = service;
	}
	
	@Override
	public void run(){
		service.testMethod();
	}
}

class ThreadB extends Thread{
	private Service service;
	
	public ThreadB(Service service){
		super();
		this.service = service;
	}
	
	@Override
	public void run(){
		service.testMethod();
	}
}

class Service {
	private Semaphore semaphore = new Semaphore(1);
	
	public void testMethod() {
//		try {
			semaphore.acquireUninterruptibly();
			System.out.println(Thread.currentThread().getName()+" begin timer=" +System.currentTimeMillis());
			for(int i=0;i<Integer.MAX_VALUE / 50;i++){
				String newString = new String();
				Math.random();
			}
			System.out.println(Thread.currentThread().getName()+" end timer=" + System.currentTimeMillis());
			semaphore.release();
//		} catch (InterruptedException e) {
//			System.out.println("线程" + Thread.currentThread().getName() + "进入了catch");
//			e.printStackTrace();
//		}
	}
}
