package com.eghm.thread;

import cn.hutool.core.date.DateUtil;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author 殿小二
 * @date 2021/5/12
 */
public class CompletableFutureTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            String s = DateUtil.formatDateTime(new Date());
            System.out.println("开始时间: " + s);
            return s;
        }).thenApply(aLong -> {
            sleep(2);
            String millis = DateUtil.formatDateTime(new Date());
            System.out.println("二次时间:" + millis);
            return millis;
            // 该并行计算与最开始的任务一起执行
        }).thenCombineAsync(CompletableFuture.supplyAsync(() -> {
            sleep(5);
            String millis = DateUtil.formatDateTime(new Date());
            System.out.println("并行时间:" + millis);
            return millis;
        }), (aLong, aLong2) -> {
            System.out.println("aLong, aLong2 " + aLong + " " + aLong2);
            return aLong + " " +  aLong2;
        });

        String time = future.get();
        System.out.println("time = "+time);
    }

    public static void sleep(long sleep) {
        try {
            TimeUnit.SECONDS.sleep(sleep);
        } catch (InterruptedException e) {
        }
    }
}
