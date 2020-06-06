package com.eghm.arithmetic;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 实现 add 将猫,狗放入队列中
 * poll 依次按进队列顺序进行弹出
 * pollDog 弹出狗
 * pollCat 弹出猫
 * isEmpty 队列是否为空
 * isDogEmpty 狗是否为空
 * isCatEmpty 猫是否为空
 */
public class 第四题_猫狗队列 {

	public static void main(String[] args) {
		第四题_猫狗队列 queue = new 第四题_猫狗队列();
		queue.add(new Dog());
		queue.add(new Dog());
		queue.add(new Cat());
		queue.add(new Dog());
		queue.add(new Cat());
		queue.add(new Dog());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
		System.out.println(1 << 31);
	}

	private static final String DOG = "dog";

	private static final String CAT = "cat";

	private Queue<PetEntry> dogQueue = new LinkedList<>();

	private Queue<PetEntry> catQueue = new LinkedList<>();


	public void add(Pet pet) {
		if (DOG.equals(pet.getType())) {
			dogQueue.offer(new PetEntry(pet));
		} else {
			catQueue.offer(new PetEntry(pet));
		}
	}

	public Pet poll() {
		if (!dogQueue.isEmpty() && !catQueue.isEmpty()) {
			if (dogQueue.peek().getCount() < catQueue.peek().getCount()) {
				return dogQueue.poll().getPet();
			} else {
				return catQueue.poll().getPet();
			}
		} else if (!dogQueue.isEmpty()) {
			return dogQueue.poll().getPet();
		} else if (!catQueue.isEmpty()) {
			return catQueue.poll().getPet();
		}
		return null;
	}

	public Pet pollDog() {
		PetEntry poll = dogQueue.poll();
		return poll != null ? poll.getPet() : null;
	}

	public Pet pollCat() {
		PetEntry poll = catQueue.poll();
		return poll != null ? poll.getPet() : null;
	}

	public boolean isEmpty() {
		return catQueue.isEmpty() && dogQueue.isEmpty();
	}

	public boolean isDogEmpty() {
		return dogQueue.isEmpty();
	}

	public boolean isCatEmpty() {
		return catQueue.isEmpty();
	}

	static class Pet {

		private String type;

		public Pet(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

		@Override
		public String toString() {
			return "Pet{" +
					"type='" + type + '\'' +
					'}';
		}
	}

	static class Dog extends Pet {

		public Dog() {
			super(DOG);
		}
	}

	static class Cat extends Pet {

		public Cat() {
			super(CAT);
		}
	}

	static class PetEntry {

		private Pet pet;

		private long count;

		public PetEntry(Pet pet) {
			this.pet = pet;
			this.count = System.nanoTime();
		}

		public Pet getPet() {
			return pet;
		}

		public long getCount() {
			return count;
		}

		public String getType() {
			return pet.getType();
		}
	}
}
