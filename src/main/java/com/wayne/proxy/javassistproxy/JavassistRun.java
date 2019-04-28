package com.wayne.proxy.javassistproxy;

import com.wayne.proxy.Coder;
import com.wayne.proxy.Person;

public class JavassistRun {

    public static void main(String[] args) {
        Person person = new JavassistProxy(new Coder());
        person.doWork();
    }
}
