package com.wayne.proxy.cglibproxy;

import com.wayne.proxy.Coder;
import com.wayne.proxy.Person;

public class CGLIBRun {

    public static void main(String[] args) {
//        Coder coder = (Coder)new CGLIBProxy(new Coder()).getInstance();
//        coder.doWork();

        Person person = (Person)new CGLIBProxy(new Coder()).getInstance();
        person.doWork();
    }

}
