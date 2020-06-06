package com.eghm;

/**
 * 超大值
 */
public class MaxLong {

	public static void main(String[] args) {
		System.out.println(add("10086","101985"));
	}

	public static String add(String a, String b) {
		int max = Math.max(a.length(), b.length());
		int min = Math.min(a.length(), b.length());
		StringBuilder temp = new StringBuilder(16);
		for (int i = 0; i < max- min; i++) {
			temp.append("0");
		}
		if (a.length() == max) {
			b = temp.toString() + b;
		} else {
			a = temp.toString() + a;
		}
		int tp;
		int jw = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = max - 1; i >= 0; i--) {
			int aNum = Integer.parseInt(String.valueOf(a.charAt(i)));
			int bNum = Integer.parseInt(String.valueOf(b.charAt(i)));
			if ((aNum + bNum + jw) >= 10 && i != 0) {
				tp = (aNum + bNum + jw) - 10;
				jw = 1;
			} else {
				tp = aNum + bNum + jw;
				jw = 0;
			}
			builder.insert(0, tp);
		}
		return builder.toString();
	}
}
