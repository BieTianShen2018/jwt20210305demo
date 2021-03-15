package com.jjwt.demo20210305.demo;

import com.jjwt.demo20210305.demo.controller.alibaba.examination.UserThreadFactory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JjwtDemoApplicationTests {

    @Test
    void contextLoads() {

    }
    Integer  test=1;
    @Test
    public void testThreadPools(){
        UserThreadFactory userThreadFactory=new UserThreadFactory("myDemo");

        Runnable runnable=new Runnable() {
            @Override
            public void run() {

                test=test+1;
                System.out.println("run:::"+ test.toString());
            }
        };
        Thread threadOne=  userThreadFactory.newThread(runnable);
        threadOne.start();
        Thread thread2=  userThreadFactory.newThread(runnable);
        thread2.start();
        Thread thread3=  userThreadFactory.newThread(runnable);
        thread3.start();
    }
    @Test
    public void testDate() {


    }


}
