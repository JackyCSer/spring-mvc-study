package com.jackycser.springmvc.proxy;

import com.alibaba.fastjson.JSON;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author Jacky Zhang
 * @since 22/02/2017
 */
public class CglibInterceptor {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(TestClass.class);
        enhancer.setCallback(new MethodInterceptorImpl());
        TestClass test = (TestClass) enhancer.create();
        test.doSome();
        test.sayHello();
    }

    static class MethodInterceptorImpl implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("intercept method: " + method + ", args: " + JSON.toJSONString(args));
            return proxy.invokeSuper(obj, args);
        }
    }
}

//创建一个类，用来做测试
class TestClass {
    public void doSome() {
        System.out.println("咿呀咿呀喂");
    }

    public void sayHello() {
        System.out.println("Hello");
    }
}
