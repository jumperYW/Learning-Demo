package com.wayne.proxy.staticproxy;

import com.wayne.proxy.Coder;
import com.wayne.proxy.Person;

public class StaticRun {

    public static void main(String[] args) {
        Person person = new StaticProxy(new Coder());
        person.doWork();
    }
}
