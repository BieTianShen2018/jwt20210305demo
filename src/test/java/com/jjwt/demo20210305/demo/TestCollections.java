package com.jjwt.demo20210305.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestCollections {


    @Test
    void testThreadJoin11(){

        String[] str = new String[] { "yang", "hao" };
        List list = Arrays.asList(str);
        int a=1;
    }

    @Test
    void testFanXing(){

        List<String> generics = null;
        List notGenerics = new ArrayList(10);
        notGenerics.add(new Object());
        notGenerics.add(new Integer(1));
        generics = notGenerics;
        // 此处抛出 ClassCastException 异常
        Object string = generics.get(0);
    }

    @Test
    void testFloat(){

        //结论 加减不一样  单纯比较值可以使用
        float a = 0.9f;//1.0f - 0.9f;
        float b = 0.9f;//0.9f - 0.8f;
        if (a == b) {
            System.out.println("111true");
        }
        Float x = Float.valueOf(a);
        Float y = Float.valueOf(b);
        if (x.equals(y)) {
            System.out.println("222true");
        }
        System.out.println("333:  "+(a-b));
    }


}
