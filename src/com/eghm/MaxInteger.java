package com.eghm;


import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;
import io.netty.util.internal.MathUtil;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 殿小二
 * @date 2021/3/8
 */
public class MaxInteger {

    private final FastThreadLocal<MaxInteger> threadLocal = new FastThreadLocal<>();

    private FastThreadLocal<MaxLong> maxLocal = new FastThreadLocal<>();

    private static final int max = new AtomicInteger().getAndIncrement();

    private MaxInteger[] elements;

    private int count;

    public MaxInteger() {
        elements = new MaxInteger[MathUtil.safeFindNextPositivePowerOfTwo(16)];
        for (int i = 0; i < elements.length; ++i) {
            // Size of 16 should be good enough for the majority of all users as an initial capacity.
            elements[i] = new MaxInteger(i);
        }
        count = elements.length;
    }

    public MaxInteger(int x) {
    }

    public static void main(String[] args) {

        new FastThreadLocalThread(() -> {
            MaxInteger maxInteger = new MaxInteger();
            System.out.println(maxInteger.getMax());

        }).start();
        new FastThreadLocalThread(() -> {
            MaxInteger maxInteger = new MaxInteger();
            System.out.println(maxInteger.getMax());
            maxInteger.threadLocal.set(new MaxInteger());
        }).start();
    }

    private int getMax () {
        return max;
    }
}
