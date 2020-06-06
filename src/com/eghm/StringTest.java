package com.eghm;

import java.util.ArrayList;

public class StringTest {
    public static void main(String[] args) {

        String b = "a" + new String("b");
        String intern = b.intern();
        String a = "ab";
        System.out.println(intern == a);

        ArrayList arrayList = new ArrayList();
    }
}
