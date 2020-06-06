package com.eghm;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        System.out.println(66 & 32);
        //  100000 0 ~ 31
        // 1011111 64~95
        //10000000 11000000 128~159 192~223
        Map<String,Integer> map = new HashMap<>(1500000);
        for (int i = 0; i < 1000000; i++) {
            map.put("V" + i, i);
        }
        long start = System.currentTimeMillis();
        map.forEach((s, integer) -> {
            System.out.println(s + " " + integer);
        });
        long end = System.currentTimeMillis();
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        long end2 = System.currentTimeMillis();
        System.out.println("map-forEach 耗时:" + (end - start));
        System.out.println("map-entrySet for 耗时:" + (end2 - end));
    }
}
