package com.eghm.arithmetic;

/**
 * @author 殿小二
 * @date 2021/3/15
 */
public class Node {

    int val;

    Node next = null;

    Node(int val) {
        this.val = val;
    }

    public static Node getInstance(int deep) {
        return getInstance(deep, 1);
    }

    public static Node getInstance(int deep, int step) {
        Node head = new Node(-1);
        Node node = head;
        int index = 0;
        while (index < deep) {
            node.next = new Node(index);
            node = node.next;
            index += step;
        }
        return head.next;
    }

    public static void println(Node node) {
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static void main(String[] args) {
        println(getInstance(5));
    }
}
