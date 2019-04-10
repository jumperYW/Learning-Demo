package com.jumper.proxy.staticproxy;

import com.jumper.proxy.Coder;
import com.jumper.proxy.Person;

public class StaticRun {

    public static void main(String[] args) {
        Person person = new StaticProxy(new Coder());
        person.doWork();
    }
}
