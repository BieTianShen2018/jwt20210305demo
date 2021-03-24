package com.jjwt.demo20210305.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestThreadJoin {

  //  这个问题有一个关键的知识点：
   // JVM的运行机制中，所有非守护线程都执行完毕后，JVM就会自动退出，JVM的退出不关心守护线程是否结束。
   // 而 CompletableFuture 执行的时候默认使用的的守护线程池，查看源代码可以看出：
    @Test
    void testThreadJoin11(){

    }



}
