package com.jumper.concurrentProgramming.threadpool;

import java.util.concurrent.*;

public class ExecutorsTest {

    public static void main(String[] args) {

        /**
         *  new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
         *
         */

        Executors.newCachedThreadPool();

        /**
         *  new ThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
         *  固定大小
         */
        Executors.newFixedThreadPool(1);

        /**
         * (corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS, new DelayedWorkQueue())
         */
        Executors.newScheduledThreadPool(1);

        /**
         *  只有一个线程
         */
        Executors.newSingleThreadExecutor();

        /**
         *
         */
        Executors.newSingleThreadScheduledExecutor();

        /**
         *
         */
        Executors.newWorkStealingPool();

    }

}
