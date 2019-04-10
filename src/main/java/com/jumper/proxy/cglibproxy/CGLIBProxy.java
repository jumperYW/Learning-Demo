package com.jumper.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLIBProxy implements MethodInterceptor{

    private Object o;

    public CGLIBProxy(Object o) {
        this.o = o;
    }

    public Object getInstance() {
        //cglib 中加强器，用来创建动态代理
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(o.getClass());
        // 设置回调，这里相当于是对于代理类上所有方法的调用，都会调用CallBack，而Callback则需要实行intercept()方法进行拦截
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("before ...");
        methodProxy.invokeSuper(o, objects);
        System.out.println("after ...");
        return null;
    }
}
