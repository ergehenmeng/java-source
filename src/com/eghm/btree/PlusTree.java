package com.eghm.btree;

import java.util.*;

/**
 * M阶的B+树定义(只限于本例):
 * 根节点关键字个数 [1,M)
 * 子节点关键字个数 [ceil(M/2) - 1,M)
 * 叶子节点关键字个数[ceil(M/2)-1,M]
 * 节点关键字-1 = 子节点个数
 *          8
 *       /     \
 *    1    8  9   15
 * @author 二哥很猛
 */
public class PlusTree<K extends Comparable<K>, V> {

    /**
     * 最小阶数
     */
    private static final int MIN = 3;

    /**
     * 根节点
     */
    private TreeNode<K, V> root;

    /**
     * 阶数 叶子节点最多包含多少元素
     */
    private int num;

    /**
     * 头节点
     */
    private TreeNode<K, V> head;

    /**
     * 树高
     */
    private int height;

    public TreeNode<K, V> getRoot() {
        return root;
    }

    public int getNum() {
        return num;
    }

    public TreeNode<K, V> getHead() {
        return head;
    }

    public int getHeight() {
        return height;
    }

    public void setRoot(TreeNode<K, V> root) {
        this.root = root;
    }

    public void setHead(TreeNode<K, V> head) {
        this.head = head;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public PlusTree(int num) {
        if (num < MIN) {
            throw new RuntimeException("阶数最小为:" + MIN);
        }
        this.num = num;
        this.root = new TreeNode<>(true, true);
        this.head = root;
    }

    /**
     * 获取元素
     * @param key key
     * @return value
     */
    public V get(K key) {
        return root.get(key);
    }

    /**
     * 添加元素,如果存在,则更新操作
     * @param key key
     * @param value value
     */
    public void put(K key, V value) {
        root.put(key, value, this);
    }

    /**
     * 移除某个元素
     * @param key key
     */
    public void remove(K key) {
    }

    /**
     * 打印所有数据
     */
    public void print(){
        root.print(0);
    }

    public static void main(String[] args) {
        PlusTree<Integer, Integer> tree = new PlusTree<>(5);
        Set<Integer> set = new HashSet<>();
        Random random = new Random();
        int[] ararys = {128, 65, 130, 195, 4, 198, 263, 135, 265, 9, 139, 204, 142, 207, 272, 80, 82, 148, 87, 24, 29, 30, 158, 97, 161, 290, 229, 293, 230, 297, 233, 44, 239, 48, 114, 52, 244, 248, 59, 123};
        for (int i = 0; i < 40; i++) {
            System.out.println("========" + ararys[i] + "=======");
            tree.put(ararys[i], ararys[i]);
//            Integer key = getKey(set, random);
//            tree.put(key, key);
            tree.print();
        }
        System.out.println(Arrays.toString(set.toArray()));
        tree.print();
    }

    private static Integer getKey(Set<Integer> set, Random random) {
        for (;;){
            int i = random.nextInt(300);
            if (set.add(i)) {
                return i;
            }
        }
    }
}
