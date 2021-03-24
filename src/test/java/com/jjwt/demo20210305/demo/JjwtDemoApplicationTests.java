package com.jjwt.demo20210305.demo;

import com.jjwt.demo20210305.demo.controller.alibaba.examination.ThreadPoolsTools;
import com.jjwt.demo20210305.demo.controller.alibaba.examination.UserThreadFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class JjwtDemoApplicationTests {

    @Test
    void contextLoads() {

    }

    Integer test = 1;

    @Test
    public void testThreadPools() {
        UserThreadFactory userThreadFactory = new UserThreadFactory("myDemo");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                test = test + 1;
                System.out.println("run:::" + test.toString());
            }
        };
        Thread threadOne = userThreadFactory.newThread(runnable);
        threadOne.start();
        Thread thread2 = userThreadFactory.newThread(runnable);
        thread2.start();
        Thread thread3 = userThreadFactory.newThread(runnable);
        thread3.start();
    }

    @Test
    public void testDate() {


    }

    //Cached fixed
    @Test
    public void testCachedThreadPool() {

        //创建线程池
        //ExecutorService executorService= ThreadPoolsTools.createCachedThreadPool();
        ExecutorService executorService = ThreadPoolsTools.createFixedThreadPool();

        //测试
        for (int i = 0; i < 20; i++) {
            final int index = i;
            System.out.println("loop:" + i);
            executorService.execute(() -> {
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    System.out.println("11111:"+e);
//                }
            });
        }
    }

    //sche
    @Test
    public void testScheduleThreadPool() {
        //创建线程池
        //ExecutorService executorService= ThreadPoolsTools.createCachedThreadPool();
        //ScheduledExecutorService executorService= ThreadPoolsTools.createScheduledThreadPool();
        ExecutorService executorService = ThreadPoolsTools.createNewAThreadPool();
        //测试
        for (int i = 0; i < 16; i++) {
            final int index = i;
            System.out.println("loop:" + i + "===" + Thread.currentThread().getName());
            executorService.execute(() -> {
                // 获取线程名称,默认格式:pool-1-thread-1
                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " " + index);
                // 等待2秒
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.out.println("11111:" + e);
                }
            });

//            executorService.schedule(() -> {
//                // 获取线程名称,默认格式:pool-1-thread-1
//             System.out.println("777: " + Thread.currentThread().getName() + " " + index);
//              // 等待2秒
//                // sleep(2000);
//                            }, 3, TimeUnit.SECONDS);
        }
    }


    //测试结果: 主线程执行完成 没有执行完的子线程不在继续执行.
    //所有子线程中有一个非守护线程JVM就不会退出。
    @Test
    public void testCountDownLatch() {
       // final CountDownLatch latch = new CountDownLatch(4);
        ExecutorService es =null;
        try {
             es = ThreadPoolsTools.createNewAThreadPool();
            //
            es.execute(() -> {
                try {
                    // 获取线程名称,默认格式:pool-1-thread-1
                    System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 子线程开始执行1 ");
                    // 等待2秒
                    Thread.sleep(2000);
                } catch (Exception e) {
                    System.out.println("11111:" + e);
                }
                //latch.countDown();
                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 子线程开始执行完成1 ");
                // latch.countDown();
            });
            //
            es.execute(() -> {
                try {
                    // 获取线程名称,默认格式:pool-1-thread-1
                    System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 子线程开始执行2 ");
                    // 等待2秒
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println("11111:" + e);
                }
                //latch.countDown();
                System.out.println(Instant.now() + " " + Thread.currentThread().getThreadGroup().getName() + " 子线程开始执行完成2 ");
                //latch.countDown();
            });

            es.execute(() -> {
                try {
                    // 获取线程名称,默认格式:pool-1-thread-1
                    System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 子线程开始执行3 ");
                    // 等待2秒
                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.println("11111:" + e);
                }
                //latch.countDown();
                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 子线程开始执行完成3 ");
                // latch.countDown();
            });

            es.execute(() -> {
                try {
                    // 获取线程名称,默认格式:pool-1-thread-1
                    System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 子线程开始执行4 ");
                    // 等待2秒
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("11111:" + e);
                }

                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 子线程开始执行完成4 ");
                //latch.countDown();
            });

            try {
                System.out.println("等待4个子线程执行完毕...");
                // latch.await();
                // System.out.println("4个子线程已经执行完毕");
                try {
                    // 获取线程名称,默认格式:pool-1-thread-1
                    System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 等等10秒 ");
                    // 等待2秒
                    Thread.sleep(10000);
                } catch (Exception e) {
                    System.out.println("11111:" + e);
                }
                System.out.println("继续执行主线程");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }finally {
            es.shutdown();
        }
    }


    //测试结果: 主线程执行完成 没有执行完的子线程不在继续执行.
    @Test
    public void testthreadPools222() {

        Runnable ra=new Runnable() {
            @Override
            public void run() {
                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 111kaishi");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 111jieshu");
            }
        };

        Runnable ra2=new Runnable() {
            @Override
            public void run() {
                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 222kaishi");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 222jieshu");
            }
        };

        ExecutorService es =null;
        try {
            es = ThreadPoolsTools.createNewAThreadPool();
            System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " kaishi");
            es.execute(ra);
            es.execute(ra2);
            System.out.println(Instant.now() + " " + Thread.currentThread().getName() + "jieshu ");
        }finally {
            while (true ){
                if(es.isTerminated()) {
                    System.out.println(Instant.now() + " " + Thread.currentThread().getName() + "jieshu999 ");
                    es.shutdown();
                }
            }

           // es.shutdown();
        }
    }

    //测试非线程池线程
    //new 一个 线程, start(), 主线程并不会等待这个新开线程执行完毕, 你会看到有很多演示demo都调用了一下thread.join();
    @Test
    public void testthread() throws InterruptedException {
        Runnable ra=new Runnable() {
            @Override
            public void run() {
                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 111kaishi");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 111jieshu");
            }
        };

        Runnable ra2=new Runnable() {
            @Override
            public void run() {
                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 222kaishi");
                try {
                    for (int i=0;i<20;i++){
                        System.out.println(Instant.now() + " " + Thread.currentThread().getName()+"==="+i);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " 222jieshu");
            }
        };

        Thread t=new Thread(ra2);
        Thread t2=new Thread(ra2);

        t.start();
        //t2.start();
       // t2.join(1);
       // t.join();
        System.out.println(Instant.now() + " " + Thread.currentThread().getName() + " zhu jieshu ");
       //Thread.sleep(100000);
    }
}
