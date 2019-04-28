package com.wayne.proxy.jdkproxy;

import com.wayne.proxy.Coder;
import com.wayne.proxy.Person;

public class JDKRun {

    public static void main(String[] args) {
        Person person = (Person)new JDKProxy(new Coder()).getInstance();
        person.doWork();

//        //java.lang.ClassCastException: com.sun.proxy.$Proxy0 cannot be cast to com.wayne.proxy.Coder
//        Coder coder = (Coder)new JDKProxy(new Coder()).getInstance();
//        coder.doWork();
    }

}
