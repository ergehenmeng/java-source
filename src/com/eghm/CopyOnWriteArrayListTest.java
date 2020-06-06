package com.eghm;

import com.alibaba.fastjson.JSONArray;

import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> arrayList = new CopyOnWriteArrayList<>();
        arrayList.add("abc");
        arrayList.add("bcd");
        arrayList.add("efg");
        arrayList.add("hjk");
        System.out.println(JSONArray.toJSONString(arrayList));
    }
}
