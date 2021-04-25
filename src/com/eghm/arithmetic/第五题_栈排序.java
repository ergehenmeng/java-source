package com.eghm.arithmetic;

import java.util.Arrays;
import java.util.Stack;

/**
 * 不能使用额外的数据结构
 * 栈顶-栈底 大到小排序  重点
 */
public class 第五题_栈排序 {


	public static void main(String[] args) {
		Stack<Integer> stack = new Stack<>();
		stack.push(2);
		stack.push(3);
		stack.push(5);
		stack.push(1);
		stack.push(4);
		stack.push(9);
		sort(stack);
		System.out.println(Arrays.toString(stack.toArray()));
	}

	public static void sort(Stack<Integer> stack) {
		// 正序排列
		Stack<Integer> help = new Stack<>();
		while (!stack.isEmpty()) {
			Integer pop = stack.pop();
			// 对比, 大值放在stack(正序) 小的放在help中
			while (!help.isEmpty() && help.peek() > pop) {
				stack.push(help.pop());
			}
			// 尽可能将小的元素入栈
			help.push(pop);
		}
		// 倒叙
		while (!help.isEmpty()) {
			stack.push(help.pop());
		}
	}
}
