package com.eghm.arithmetic;

import java.util.Stack;

public class 第二题_两个栈实现队列 {


	static class StackQueue<T> {

		private Stack<T> pushStack;

		private Stack<T> popStack;

		public StackQueue() {
			this.pushStack = new Stack<>();
			this.popStack = new Stack<>();
		}

		public void add(T item) {
			pushStack.push(item);
		}

		public T poll() {
			if (pushStack.isEmpty() && popStack.isEmpty()) {
				return null;
			} else if (popStack.isEmpty()) {
				// 将push中所有元素压入pop中, 注意如果popStack不为空,不能做此操作
				while (!pushStack.isEmpty()) {
					popStack.push(pushStack.pop());
				}
			}
			return popStack.pop();
		}

		public T peek() {
			if (pushStack.isEmpty() && popStack.isEmpty()) {
				return null;
			} else if (popStack.isEmpty()) {
				// 将push中所有元素压入pop中, 注意如果popStack不为空,不能做此操作
				while (!pushStack.isEmpty()) {
					popStack.push(pushStack.pop());
				}
			}
			return popStack.peek();
		}
	}
}
