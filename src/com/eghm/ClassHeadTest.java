package com.eghm;

import org.openjdk.jol.info.ClassLayout;

public class ClassHeadTest {
    public static void main(String[] args) throws InterruptedException {
        ClassHead head = new ClassHead();
        User[] users = new User[2];
        System.out.println(ClassLayout.parseInstance(head).toPrintable());
        System.gc();
        Thread.sleep(1000);
        System.gc();
        Thread.sleep(1000);
        System.gc();
        System.out.println(ClassLayout.parseInstance(head).toPrintable());


        synchronized (head){
            // 查看对象头信息 偏向锁等
            System.out.println(ClassLayout.parseInstance(head).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(users).toPrintable());
    }

    /**
     * 对象头是对象实例创建后才会有的
     */
    public static class ClassHead {

    }
}
