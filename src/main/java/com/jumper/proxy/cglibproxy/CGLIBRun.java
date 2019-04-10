package com.jumper.proxy.cglibproxy;

import com.jumper.proxy.Coder;
import com.jumper.proxy.Person;

public class CGLIBRun {

    public static void main(String[] args) {
//        Coder coder = (Coder)new CGLIBProxy(new Coder()).getInstance();
//        coder.doWork();

        Person person = (Person)new CGLIBProxy(new Coder()).getInstance();
        person.doWork();
    }

}
