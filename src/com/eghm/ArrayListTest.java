package com.eghm;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ArrayListTest {

    public static void main(String[] args) {

        subList();

    }


    public static void subList() {
        List<String> list = Lists.newArrayList("A","B");
        List<String> subList = list.subList(1, 2);
        for (String str : list) {
            System.out.println("主:" + str);
        }
        for (String sub : subList) {
            System.out.println("子:" + sub);
        }
        subList.add("C");
        for (String sub : subList) {
            System.out.println("子:" + sub);
        }

    }

    public static void speed() {
        List<String>  list = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            list.add("A");
        }
        long start1 = System.currentTimeMillis();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println(list.get(i));
        }
        long end1 = System.currentTimeMillis();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        long end = System.currentTimeMillis();
        list.forEach(System.out::println);
        long end2 = System.currentTimeMillis();
        for (String str : list) {
            System.out.println(str);
        }
        long end3 = System.currentTimeMillis();
        System.out.println("fori耗时:" + (end1 - start1));
        System.out.println("iterator耗时:" + (end - end1));
        System.out.println("foreach耗时:" + (end2 - end));
        System.out.println("for耗时:" + (end3 - end2));
    }
}

