package com.eghm;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @author 殿小二
 * @date 2021/3/10
 */
public class ThreadTest {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] threads = threadMXBean.dumpAllThreads(false, false);
        for (ThreadInfo thread : threads) {
            System.out.println(thread.getThreadName() + " : " + thread.getThreadState());
        }
    }
}
