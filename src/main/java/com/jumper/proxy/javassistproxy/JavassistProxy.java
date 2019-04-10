package com.jumper.proxy.javassistproxy;

import com.jumper.proxy.Person;

public class JavassistProxy implements Person{

    private Person person;

    public JavassistProxy(Person person) {
        this.person = person;
    }

    @Override
    public void doWork() {
        System.out.println("before ...");
        person.doWork();
        System.out.println("after ...");
    }
}
