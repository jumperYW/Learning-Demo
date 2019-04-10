package com.jumper.proxy.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 通过反射实现
 */
public class JDKProxy implements InvocationHandler {

    private Object obj;// 被代理人

    public JDKProxy(Object obj) {
        this.obj = obj;
    }

    public Object getInstance() {
        //与cglib的区别在于这里构建代理对象的时候需要传入被代理对象的接口对象，第二个参数。而cglib不需要被代理对象实现任何接口即可
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before ...");
        Object result = method.invoke(obj, args);
        System.out.println("after ...");
        return result;
    }
}
