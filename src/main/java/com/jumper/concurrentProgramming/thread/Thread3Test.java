package com.jumper.concurrentProgramming.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 10w      1381ms
 * 100w     12897ms
 * 100w     12559ms
 * 200w     24161ms
 */
public class Thread3Test {

    private static int n = 2000000;
    private static CountDownLatch countDownLatch = new CountDownLatch(2);
    private static Lock lock = new ReentrantLock();
    private static Condition a = lock.newCondition();
    private static Condition b = lock.newCondition();
    private static volatile int i = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            while(i <= n) {
                lock.lock();
                try {
//                    System.out.println("A");
                    if ((i & 1) == 0) {
                        System.out.println("threadA " + i++);
                    }
                    b.signal();
                    try {
                        if (i >= n) {
                            break;
                        }
                        a.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
            countDownLatch.countDown();
        },"threadA");
        Thread threadB = new Thread(() -> {
            while(i <= n) {
                lock.lock();
                try {
//                    System.out.println("B");
                    if ((i & 1) == 1) {
                        System.out.println("threadB " + i++);
                    }
                    a.signal();
                    try {
                        if (i >= n) {
                            break;
                        }
                        b.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
            countDownLatch.countDown();
        }, "ThreadB");
        long start = System.currentTimeMillis();
        threadA.start();
        threadB.start();
        countDownLatch.await();
        System.out.println("cost:"+ (System.currentTimeMillis() - start) + "ms");
    }

}
