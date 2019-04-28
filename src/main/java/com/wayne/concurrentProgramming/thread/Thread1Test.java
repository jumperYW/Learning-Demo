package com.wayne.concurrentProgramming.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 10w      1643ms
 * 100w     12705ms
 * 100w     12705ms
 * 200w     24492ms
 */
public class Thread1Test {

    private static int n = 2000000;
    private static final Object lock = new Object();
    private static CountDownLatch countDownLatch = new CountDownLatch(2);
    private static volatile int i = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            while(i <= n) {
                synchronized (lock) {
//                    System.out.println("A");
                    if((i & 1) == 1) {
                        System.out.println("threadA " + i++);
                    }
                    lock.notify();
                    try {
                        if(i >= n) {
                            break;
                        }
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            countDownLatch.countDown();
        });
        Thread threadB = new Thread(() -> {
            while(i <= n) {
                synchronized (lock) {
//                    System.out.println("B");
                    if((i & 1) == 0) {
                        System.out.println("threadB " + i++);
                    }
                    lock.notify();
                    try {
                        if(i >= n) {
                            break;
                        }
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            countDownLatch.countDown();
        });
        long start = System.currentTimeMillis();
        threadA.start();
        threadB.start();
        countDownLatch.await();
        System.out.println("cost:"+ (System.currentTimeMillis() - start) + "ms");
    }

}
