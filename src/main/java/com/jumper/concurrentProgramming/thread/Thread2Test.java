package com.jumper.concurrentProgramming.thread;

import java.util.concurrent.CountDownLatch;

/**
 * 10w      1047ms
 * 100w     10679ms
 * 100w     9341ms
 * 200w     18698ms
 */
public class Thread2Test {

    private static int n = 2000000;
    private static volatile boolean flag = true;
    private static CountDownLatch countDownLatch = new CountDownLatch(2);
    private static volatile int i = 0;

    public static void main(String[] args) throws InterruptedException {

        Thread threadA = new Thread(() -> {
            while(i <= n) {
//                System.out.println("A");
                if(flag) {
                    System.out.println("threadA " + i++);
                    flag = false;
                }
            }
            countDownLatch.countDown();
        });
        Thread threadB = new Thread(() -> {
            while(i <= n) {
//                System.out.println("B");
                if(!flag) {
                    System.out.println("threadB " + i++);
                    flag = true;
                }
            }
            countDownLatch.countDown();
            countDownLatch.countDown();
        });
        long start = System.currentTimeMillis();
        threadA.start();
        threadB.start();
        countDownLatch.await();
        System.out.println("cost:"+ (System.currentTimeMillis() - start) + "ms");
    }

}
