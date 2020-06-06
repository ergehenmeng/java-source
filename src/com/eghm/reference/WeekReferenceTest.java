package com.eghm.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class WeekReferenceTest {
	public static void main(String[] args) {
		Object obj = new Object();
		WeakReference<Object> sf = new WeakReference<>(obj);
		obj =null;
		System.out.println(sf.get());
		System.gc();
		try {
		    Thread.sleep(10000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		System.out.println(sf.get());
	}
}
