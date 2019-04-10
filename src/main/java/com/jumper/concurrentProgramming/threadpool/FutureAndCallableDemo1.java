package com.jumper.concurrentProgramming.threadpool;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class FutureAndCallableDemo1 {

    public static void main(String[] args) throws InterruptedException {
        MyCallable callable = new MyCallable(18);

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        Future<String> future = executor.submit(callable);
        System.out.println("main A " + System.currentTimeMillis() / 1000);
        try {
            System.out.println(future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("main B " + System.currentTimeMillis() / 1000);
    }

}

class MyCallable implements Callable<String> {

    private int age;

    public MyCallable(int age) {
        super();
        this.age = age;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(8000);
        return "age is " + age;
    }
}