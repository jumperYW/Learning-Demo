package com.wayne.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapCurrentDemo {

    private static Map<String, List> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();
        Thread thread1 = new Thread(() -> {
            while(true) {
//                System.out.println("1:"+map.get("1"));
//                System.out.println("2:"+map.get("2"));
                if(map.get("1").size() > 0) {
                    break;
                }
            }
        });
        map.put("1", list);
        thread1.start();
        Thread.sleep(1000);
        list.add("1");
//        System.out.println("end");
    }

}
