package com.wayne.concurrentProgramming.util;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {

	private static ExecutorService service =Executors.newCachedThreadPool(); 
	
	public static void main(String[] args) {
		
		final Exchanger<String> exchanger = new Exchanger<>();

//		try {
//			exchanger.exchange("test");
//		} catch (InterruptedException e1) {
//			e1.printStackTrace();
//		}
		
		//Exchanger
		service.submit(new Runnable() {
			
			@Override
			public void run() {
				try{  
                    String data1 = "零食";  
                    System.out.println("线程"+Thread.currentThread().getName()+  
                            "正在把数据 "+data1+" 换出去");  
//                    Thread.sleep((long)Math.random()*10000);  
                    String data2 = (String)exchanger.exchange(data1);  
                    System.out.println("线程 "+Thread.currentThread().getName()+  
                            "换回的数据为 "+data2);
                }catch(Exception e){  
                    e.printStackTrace();  
                }  
			}
		});
		service.submit(new Runnable() {
			
			@Override
			public void run() {
				try{  
                    String data1 = "钱";  
                    System.out.println("线程"+Thread.currentThread().getName()+  
                            "正在把数据 "+data1+" 换出去");  
                    String data2 = (String)exchanger.exchange(data1);  
                    System.out.println("线程 "+Thread.currentThread().getName()+  
                            "换回的数据为 "+data2);  
                }catch(Exception e){  
                    e.printStackTrace();  
                }  
			}
		});
//		service.submit(new Runnable() {
//			
//			@Override
//			public void run() {
//				try{  
//                    String data1 = "面包";  
//                    System.out.println("线程"+Thread.currentThread().getName()+  
//                            "正在把数据 "+data1+" 换出去");  
//                    String data2 = (String)exchanger.exchange(data1);  
//                    System.out.println("线程 "+Thread.currentThread().getName()+  
//                            "换回的数据为 "+data2);  
//                    Thread.sleep(1000);
//                }catch(Exception e){  
//                    e.printStackTrace();  
//                }  
//			}
//		});
//		service.submit(new Runnable() {
//			
//			@Override
//			public void run() {
//				try{  
//                    String data1 = "牛奶";  
//                    System.out.println("线程"+Thread.currentThread().getName()+  
//                            "正在把数据 "+data1+" 换出去");  
//                    String data2 = (String)exchanger.exchange(data1);  
//                    System.out.println("线程 "+Thread.currentThread().getName()+  
//                            "换回的数据为 "+data2);  
//                    Thread.sleep(1000);
//                }catch(Exception e){  
//                    e.printStackTrace();  
//                }  
//			}
//		});
		
	}
	
}
