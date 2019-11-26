package com.design;


import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationContainer;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.*;


@SpringBootTest
class ComDesignCodeApplicationTests {

    class  TraHandler implements InvocationHandler{
        private Object target;
        public TraHandler(Object t){
            target = t;
        }
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName());
            System.out.println(args);
            return method.invoke(target, args);
        }
    }


    @Test
    void contextLoads() {

        Object[] elements = new Object[100];
        for (int i = 0; i < elements.length; i++) {
            Object element = elements[i];
            Integer value = i+1;
            InvocationHandler handler = new TraHandler(value);
        }
    }

    @Test
    void testMap() {
        Map<String, Object> map = new HashMap<>();

        map.put("121", "asas");

        System.out.println(map.get("xx"));
    }

}
