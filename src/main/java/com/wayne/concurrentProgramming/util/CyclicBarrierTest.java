package com.wayne.concurrentProgramming.util;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {

	public static void main(String[] args) {
		CyclicBarrier cbRef = new CyclicBarrier(5,new Runnable(){

			@Override
			public void run() {
				System.out.println("全都到了！");
			}
			
		});
		for(int i=0;i<5;i++){
			new MyThread1(cbRef).start();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			cbRef.reset();
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

class MyThread1 extends Thread {
	
	private CyclicBarrier cbRef;
	
	public MyThread1(CyclicBarrier cbRef) {
		super();
		this.cbRef = cbRef;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep((int)(Math.random() * 1000));
			cbRef.await();
			Thread.sleep((int)(Math.random() * 1000));
			System.out.println(Thread.currentThread().getName() + "到了;" + System.currentTimeMillis());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
}
