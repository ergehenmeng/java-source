package com.eghm;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.*;

public class SetRepTest {
	public static void main(String[] args) {
		Set<List<Integer>> result = new HashSet<>();
		List<Integer> set = Lists.newArrayList(1, 2,4,5,6,8);
		build(set, result);
		for (List<Integer> integers : result) {
			System.out.println(Arrays.toString(integers.toArray()));
		}
	}
	public static void build(List<Integer> list, Set<List<Integer>> result) {
		if (list.size() > 0) {
			result.add(list);
		}
		for (int i = 0; i < list.size(); i++) {
			List<Integer> set = new ArrayList<>(list);
			set.remove(i);
			build(set, result);
		}
	}
}
