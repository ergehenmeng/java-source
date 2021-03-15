package com.eghm;

/**
 * 字节码文件中初始化方法有两种，非静态资源初始化的<init>和静态资源初始化的<clinit>，
 * 类构造器方法<clinit>()不同于类的构造器，这些方法都是字节码文件中只能给JVM识别的特殊方法。
 */
public class Calc implements Super{

    private int age = 12;

    private String name = "name";

    public static void main(String[] args) {
        System.out.println(Calc.class.getClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(new Calc().age);
        // System.out.println(16 & 12);
    }
}

interface Super {
    default void say() {
        System.out.println(
                "say"
        );
    }
}
