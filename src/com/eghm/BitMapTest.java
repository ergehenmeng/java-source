package com.eghm;

import cn.hutool.bloomfilter.BitMapBloomFilter;

public class BitMapTest {
	public static void main(String[] args) {
		BitMapBloomFilter filter = new BitMapBloomFilter(10);
		filter.add("abc");
		filter.add("457");
		filter.add("568");
		filter.add("666");
		System.out.println(filter.contains("666"));

	}
}
