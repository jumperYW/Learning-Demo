package com.jumper.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

public class MapKeyIsListDemo {

    public static void main(String[] args) {
//        List<Long> list1 = Lists.newLinkedList();
//        list1.add(1L);
//        list1.add(2L);
//        list1.add(3L);
//        List<Long> list2 = Lists.newLinkedList();
//        list2.add(3L);
//        list2.add(2L);
//        list2.add(1L);
//        Map<List<Long>, String> map = Maps.newHashMap();
//        map.put(list1, "1");
//        map.put(list2, "2");
//        System.out.println(JSON.toJSONString(map));

        Set<Long> set1 = Sets.newHashSet();
        set1.add(1L);
        set1.add(2L);
        set1.add(3L);
        Set<Long> set2 = Sets.newHashSet();
        set2.add(3L);
        set2.add(2L);
        set2.add(1L);
        Set<Long> set3 = Sets.newHashSet();
        set3.add(3L);
        set3.add(2L);
        set3.add(4L);
        Map<Set<Long>, String> map2 = Maps.newHashMap();
        map2.put(set1, "1");
        map2.put(set2, "2");
        map2.put(set3, "3");
        System.out.println(JSON.toJSONString(map2));
    }

}
