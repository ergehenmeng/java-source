package com.eghm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RedPackage {

	public static void main(String[] args) {
		RedPackage aPackage = new RedPackage();
		for (int i = 0; i < 10; i++) {
			System.out.println(Arrays.toString(aPackage.red(100, 2, 20, 10).toArray()));
			System.out.println();
		}
	}

	public List<Integer> red(int surplus, int min, int max, int count) {
		List<Integer> list = new ArrayList<>();
		int s = surplus;
		for (int i = count; i > 0 ; i--) {
			if (surplus < 0 ){
				throw new RuntimeException();
			}
			if (i == 1) {
				list.add(s);
				break;
			}
			int c = min * i;
			if (s < c) {
				throw new RuntimeException();
			}
			int random = s - c;
			if (random == 0) {
				list.add(min);
				s = s - min;
			} else {
				int r = getRandom(random, min, max);
				list.add(min + r);
				s = s - min - r;
			}
		}
		return list;
	}

	private int getRandom(int random, int min, int max) {
		for (;;) {
			int x = ThreadLocalRandom.current().nextInt(random);
			if ((x+min) >= max) {
				continue;
			}
			System.out.print((x) + ",") ;
			return x ;
		}

	}
}
