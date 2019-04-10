package com.jumper.proxy.javassistproxy;

import com.jumper.proxy.Coder;
import com.jumper.proxy.Person;

public class JavassistRun {

    public static void main(String[] args) {
        Person person = new JavassistProxy(new Coder());
        person.doWork();
    }
}
