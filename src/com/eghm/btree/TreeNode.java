package com.eghm.btree;

import java.util.ArrayList;
import java.util.List;

/**
 * B+数 节点信息 该节点可以为叶子节点,也可以为非叶子节点
 */
public class TreeNode<K extends Comparable<K>, V> {

    private boolean leaf;

    private boolean root;

    private TreeNode<K, V> pre;

    private TreeNode<K, V> next;

    private TreeNode<K, V> parent;

    /**
     * 用于存放节点数据
     */
    private List<Entry<K, V>> entries;

    /**
     * 子节点列表
     */
    private List<TreeNode<K, V>> children;

    public TreeNode(boolean leaf) {
        this.leaf = leaf;
        if (!leaf) {
            children = new ArrayList<>();
        }
        entries = new ArrayList<>();
    }

    public TreeNode(boolean leaf, boolean root) {
        this(leaf);
        this.root = root;
    }

    /**
     * 获取节点数据
     * @param key key
     * @return value
     */
    public V get(K key) {
        //当前为叶子节点
        if (leaf) {
            int low = 0;
            int high = entries.size();
            int mid;
            int comp;
            while (low <= high) {
                mid = (low + high) / 2;
                comp = entries.get(mid).getKey().compareTo(key);
                if (comp == 0) {
                    return entries.get(mid).getValue();
                } else if (comp < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return null;
        }
        //非叶子节点
        //key小于节点最左边的key,则沿左节点向下查找
        if (key.compareTo(entries.get(0).getKey()) < 0) {
            return children.get(0).get(key);
        } else if (key.compareTo(entries.get(entries.size() - 1).getKey()) >= 0) {
            //key大于等于节点最右的key(一般为等于),直接沿最后向下查找
            return children.get(entries.size() - 1).get(key);
        } else {
            //不符合,则在中间的某个节点上
            int low = 0;
            int high = entries.size() - 1;
            int mid;
            int comp;
            while (low < high) {
                mid = (low + high) / 2;
                comp = entries.get(mid).getKey().compareTo(key);
                if (comp == 0) {
                    return children.get(mid + 1).get(key);
                } else if (comp < 0) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            return children.get(low).get(key);
        }
    }

    public void put(K key, V value, PlusTree<K, V> tree) {
        //要插入的数据为叶子节点
        if (leaf) {
            //叶子节点的数据还没有上限
            if (entries.size() < tree.getNum()) {
                //直接插入
                this.put(key, value);
                return;
            }
            //节点数据满了,需要分裂为两个
            TreeNode<K, V> left = new TreeNode<>(true);
            TreeNode<K, V> right = new TreeNode<>(true);
            //双向链表关联
            if (pre != null) {
                pre.next = left;
                left.pre = pre;
            }
            if (next != null) {
                next.pre = right;
                right.next = next;
            }
            if (pre == null) {
                tree.setHead(left);
            }
            left.next = right;
            right.pre = left;
            pre = null;
            next = null;
            //将原始节点数据填充到新的left,right中
            this.copyNode(key, value, left, right, tree);
            //父节点关联关系调整
            this.adjustment(left, right, tree, true);
            return;
        }
        //在最左侧
        if (key.compareTo(entries.get(0).getKey()) < 0) {
            children.get(0).put(key, value, tree);
            return;
        }
        //在最右侧
        if (key.compareTo(entries.get(entries.size() - 1).getKey()) >= 0) {
            children.get(children.size() - 1).put(key, value, tree);
            return;
        }
        //中间
        int low = 0;
        int high = entries.size() - 1;
        int mid;
        int comp;
        while (low <= high) {
            mid = (low + high) / 2;
            comp = entries.get(mid).getKey().compareTo(key);
            if (comp == 0) {
                children.get(mid + 1).put(key, value, tree);
                break;
            } else if (comp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (low > high) {
            children.get(low).put(key, value, tree);
        }

    }


    private void split(PlusTree<K, V> tree) {
        //子节点个数已经超过上限了,需要分裂
        if (children.size() > tree.getNum()) {
            TreeNode<K, V> left = new TreeNode<>(false);
            TreeNode<K, V> right = new TreeNode<>(false);
            int leftSize = (int) Math.ceil((tree.getNum() + 1) / 2d);
            int rightSize = (tree.getNum() + 1) / 2;
            for (int i = 0; i < leftSize; i++) {
                TreeNode<K, V> child = children.get(i);
                left.children.add(child);
                child.parent = left;
            }
            for (int i = 0; i < leftSize - 1; i++) {
                left.entries.add(entries.get(i));
            }

            for (int i = 0; i < rightSize; i++) {
                TreeNode<K, V> child = children.get(leftSize + i);
                right.children.add(child);
                child.parent = right;
            }
            for (int i = 0; i < rightSize - 1; i++) {
                right.entries.add(entries.get(leftSize + i));
            }
            this.adjustment(left, right, tree, false);
        }
    }


    /**
     * 当前节点分裂为左右两个节点后的关系调整
     * @param left 分裂后的左节点
     * @param right 分裂后的右节点
     * @param tree tree
     */
    private void adjustment(TreeNode<K, V> left, TreeNode<K, V> right, PlusTree<K, V> tree, boolean leaf) {
        //当前节点有父节点,需要进行关联
        if (parent != null) {
            int index = parent.children.indexOf(this);
            //移除该节点,添加新的left,right节点
            parent.children.remove(this);
            left.parent = parent;
            right.parent = parent;
            parent.children.add(index, left);
            parent.children.add(index + 1, right);
            //只在父节点中添加关键字,不删除关键字
            parent.entries.add(index, right.entries.get(0));
            entries = null;
            children = null;
            //父节点可能关键字上限,需要调整
            parent.split(tree);
            parent = null;
        } else {
            //当前节点为根节点又同时为叶子节点,创建一个父节点(根节点)
            TreeNode<K, V> parent = new TreeNode<>(false, true);
            tree.setRoot(parent);
            tree.setHeight(tree.getHeight() + 1);
            left.parent = parent;
            right.parent = parent;
            parent.children.add(left);
            parent.children.add(right);
            //父节点添加一个关键字 一个right最小
            parent.entries.add(right.entries.get(0));
            entries = null;
            children = null;
            root = false;
        }
    }

    /**
     * 把当前节点一分为二
     *
     * @param key 待添加的key
     * @param value 待添加的value
     * @param left 分开后的左节点
     * @param right 分开后的右节点
     * @param tree tree
     */
    private void copyNode(K key, V value, TreeNode<K ,V> left, TreeNode<K ,V> right, PlusTree<K, V> tree) {
        int leftSize = (int) Math.ceil((tree.getNum() + 1) / 2d);
        //元素是否已经添加进去
        boolean inserted = false;
        for (int i = 0; i < entries.size();) {
            TreeNode<K ,V> node = ((leftSize--) > 0) ? left : right;
            //将key,value放入指定位置,前提没有插入过
            if (!inserted && entries.get(i).getKey().compareTo(key) > 0) {
                node.entries.add(new Entry<>(key, value));
                inserted = true;
                continue;
            } else {
                node.entries.add(entries.get(i));
            }
            i++;
        }
        //直接插入到最后
        if (!inserted) {
            right.entries.add(new Entry<>(key, value));
        }
    }


    /**
     * 将数据插入到当前节点 二分查找插入
     * @param key key
     * @param value value
     */
    protected void put(K key, V value) {
        int low = 0;
        int high = entries.size() - 1;
        int mid;
        int comp;
        while (low <= high) {
            mid = (low + high) / 2;
            comp = entries.get(mid).getKey().compareTo(key);
            if (comp == 0) {
                entries.get(mid).setValue(value);
                break;
            } else if (comp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (low > high) {
            entries.add(low, new Entry<>(key, value));
        }
    }

    public int contains(K key) {
        int low = 0;
        int high = entries.size() - 1;
        int mid;
        int comp;
        while (low <= high) {
            mid = (low + high) / 2;
            comp = entries.get(mid).getKey().compareTo(key);
            if (comp == 0) {
                return mid;
            } else if (comp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public void print(int index) {
        if (leaf) {
            System.out.print("第 " + (index + 1) + " 层,叶子节点,key为: ");
            for (Entry<K, V> entry : entries) {
                System.out.print(entry.getKey() + " ");
            }
            System.out.println();
        } else {
            String flag;
            if (root) {
                flag = "根节点";
            } else {
                flag = "子节点";
            }

            System.out.print("第 " + (index + 1) + " 层," + flag + ", keys: ");
            for (Entry<K, V> entry : entries) {
                System.out.print(entry.getKey() + " ");
            }
            System.out.println();
            for (TreeNode<K, V> child : children) {
                child.print(index + 1);
            }
        }
    }


    public List<TreeNode<K, V>> getChildren() {
        return children;
    }

    static class Entry<K extends Comparable<K>, V> {

        private K key;

        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

    }

}

