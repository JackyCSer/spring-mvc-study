package com.jackycser.springmvc.proxy;

/**
 * @author Jacky Zhang
 * @since 22/02/2017
 */

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.CallbackFilter;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.cglib.proxy.NoOp;

import java.lang.reflect.Method;

class CallBackFilterTest {

    public void doOne() {
        System.out.println("doOne");
    }

    public void doTwo() {
        System.out.println("doTwo");
    }
}

public class CglibCallBackFilter {

    static class MethodInterceptorImpl implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("intercept method: " + method);
            return proxy.invokeSuper(obj, args);
        }
    }

    static class CallbackFilterImpl implements CallbackFilter {
        /**
         * 返回0代表不会进行intercept的调用
         *
         * @param method
         * @return
         */
        @Override
        public int accept(Method method) {
            return ("doTwo".equals(method.getName())) ? 0 : 1;
        }
    }

    public static void main(String[] args) {
        Callback[] callbacks =
                new Callback[]{NoOp.INSTANCE, new MethodInterceptorImpl(),};
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(CallBackFilterTest.class);
        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(new CallbackFilterImpl());
        CallBackFilterTest callBackFilterTest = (CallBackFilterTest) enhancer.create();
        callBackFilterTest.doOne();
        callBackFilterTest.doTwo();
    }
}
