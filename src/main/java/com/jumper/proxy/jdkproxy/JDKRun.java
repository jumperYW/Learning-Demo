package com.jumper.proxy.jdkproxy;

import com.jumper.proxy.Coder;
import com.jumper.proxy.Person;

public class JDKRun {

    public static void main(String[] args) {
        Person person = (Person)new JDKProxy(new Coder()).getInstance();
        person.doWork();

//        //java.lang.ClassCastException: com.sun.proxy.$Proxy0 cannot be cast to com.jumper.proxy.Coder
//        Coder coder = (Coder)new JDKProxy(new Coder()).getInstance();
//        coder.doWork();
    }

}
