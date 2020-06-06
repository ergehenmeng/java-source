package com.eghm;

import org.openjdk.jol.info.ClassLayout;

public class ClassHeadTest {
    public static void main(String[] args) {
        User user = new User();
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
        synchronized (user){
            System.out.println("---------");
        }
        System.out.println(ClassLayout.parseInstance(user).toPrintable());
    }
}
