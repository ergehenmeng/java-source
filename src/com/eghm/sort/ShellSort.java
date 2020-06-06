package com.eghm.sort;

import java.util.Arrays;

/**
 * 希尔排序 是直接插入排序的增强版
 */
public class ShellSort {

	public static void main(String[] args) {
		int[] array = {3, 1, 4, 9, 2, 6, 5};
		sort(array);
		System.out.println(Arrays.toString(array));
	}

	public static void sort(int[] array) {
		int len = array.length;
		while (len > 1) {
			len = len / 2;
			for (int x = 0; x < len; x ++) {
				for (int i = x + len; i < array.length; i = i + len) {
					int temp = array[i];
					int j;
					for (j = i - len; j >= 0 && array[j] > temp; j = j - len) {
						array[j + len] = array[j];
					}
					array[j + len] = temp;
				}
			}
		}
	}
}
