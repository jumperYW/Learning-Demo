package com.wayne.concurrentProgramming.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Thread4Test {

    private static int begin = 1;
    private static int n = 100;
    private static CountDownLatch countDownLatch = new CountDownLatch(3);
    private static Lock lock = new ReentrantLock();
    private static Condition a = lock.newCondition();
    private static Condition b = lock.newCondition();
    private static Condition c = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            int count = begin;
            while(true) {
                lock.lock();
                try {
                    System.out.println("threadA-"+count);
                    count++;
                    b.signal();
                    if(count > n) {
                        break;
                    }
                    try {
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
            int count = begin;
            while(true) {
                lock.lock();
                if(count == begin) {
                    try {
                        b.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    System.out.println("threadB-"+count);
                    count++;
                    c.signal();
                    if(count > n) {
                        break;
                    }
                    try {
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
        Thread threadC = new Thread(() -> {
            int count = begin;
            while(true) {
                lock.lock();
                if(count == begin) {
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    System.out.println("threadC-"+count);
                    count++;
                    a.signal();
                    if(count > n) {
                        break;
                    }
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    lock.unlock();
                }
            }
            countDownLatch.countDown();
        }, "ThreadC");
        long start = System.currentTimeMillis();
        threadB.start();
        Thread.sleep(500);
        threadA.start();
        threadC.start();
        countDownLatch.await();
        System.out.println("cost:"+ (System.currentTimeMillis() - start) + "ms");
    }

}
