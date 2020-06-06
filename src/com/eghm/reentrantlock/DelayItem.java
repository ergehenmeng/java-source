package com.eghm.reentrantlock;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayItem implements Delayed {

    private long time;
    /**
     *
     * @param time
     */
    public DelayItem(long time) {
        this.time = time;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time;
    }

    @Override
    public int compareTo(Delayed o) {
        if (o == this){
            return 0;
        }
        if (o instanceof DelayItem) {
            DelayItem item = (DelayItem) o;
            if (item.time > time){
                return -1;
            }
        }
        return 1;
    }

    @Override
    public String toString() {
        return "DelayItem{" +
                "time=" + time +
                '}';
    }
}
