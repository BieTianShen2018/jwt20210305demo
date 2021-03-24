package com.jjwt.demo20210305.demo.controller.alibaba.examination;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

public class ThreadPoolsTools {

    public static ExecutorService createCachedThreadPool() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        return executorService;
    }

    public static ExecutorService createFixedThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        return executorService;
    }

    public static ScheduledExecutorService createScheduledThreadPool() {
        ScheduledExecutorService  executorService = Executors.newScheduledThreadPool(3);
        return executorService;
    }


    public static ExecutorService createNewAThreadPool() {

        ThreadFactory springThreadFactory = new CustomizableThreadFactory("springThread-pool-");

        ThreadFactoryBuilder guavaThreadFactory = new ThreadFactoryBuilder().setNameFormat("guava-thread-pool-");

        //springThread-pool-1  自动加数字
        ExecutorService executorService = new ThreadPoolExecutor(5, 10,
                100, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5, true),
               // Executors.defaultThreadFactory()
               // springThreadFactory
                springThreadFactory , new ThreadPoolExecutor.AbortPolicy());

        //没有线程数字
        ExecutorService  exec = new ThreadPoolExecutor(1, 10,
                100, TimeUnit.MINUTES,
                new LinkedBlockingQueue<Runnable>(10),guavaThreadFactory.build() );
        return executorService;
    }

}
