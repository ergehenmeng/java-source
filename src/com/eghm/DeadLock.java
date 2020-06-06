package com.eghm;

public class DeadLock implements Runnable {
	/**
	 * a
	 */
	private String x;

	/**
	 * b
	 */
	private String y;

	public DeadLock(String x, String y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void run() {
		synchronized (x) {
			System.out.println("xxxxxx");
			synchronized (y) {
				System.out.println("yyyyyyy");
			}
		}
	}

	public static void main(String[] args) {
		String a = "a";
		String b = "b";
		new Thread(new DeadLock(a,b)).start();
		new Thread(new DeadLock(b,a)).start();
	}
}
