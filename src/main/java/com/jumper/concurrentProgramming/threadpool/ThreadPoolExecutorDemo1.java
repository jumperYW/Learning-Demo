package com.jumper.concurrentProgramming.threadpool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo1 {

	BlockingQueue workQueue = new SynchronousQueue<>();
	
	/**
	 * 属性：
	 * 1.corePoolSize:核心线程数量，一直存活，除非设置了allowCoreThreadTimeOut。
	 * 2.maximumPoolSize:最大线程数量。
	 * 3.keepAliveTime:在空闲状态下的最长等待时间；当线程数超出核心线程数量，超过该时间则回收线程，直至数量为corePoolSize。
	 * 4.unit:keepAliveTime的时间单位。
	 * 5.workQueue:等待队列。
	 * 6.allowCoreThreadTimeOut:设置了该属性，则在回收时不区分核心线程。
	 * 
	 * 方法：
	 * 1.prestartAllCoreThreads():可以在线程池创建，但还没有接收到任何任务的情况下，先行创建符合corePoolSize参数值的线程数。
	 */
	ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 5, 1, TimeUnit.MINUTES, workQueue);
	
	ExecutorService ex = Executors.newFixedThreadPool(10);
	
	public static void main(String[] args) {
		
		BlockingQueue workQueue = new SynchronousQueue<>();
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(2, 5, 1, TimeUnit.MINUTES, workQueue);
		
		ThreadPoolExecutorDemo1 demo = new ThreadPoolExecutorDemo1();
		for(int j = 0;j<100;j++){
			boolean flag = true;
			while(flag){
				try {
					tpe.execute(new Runnable() {
						@Override
						public void run() {
							System.out.println("ttt----------");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					});
					flag = false;
				} catch (RejectedExecutionException e) {
//					e.printStackTrace();
				}
			}
		}
		
	}
	
}
