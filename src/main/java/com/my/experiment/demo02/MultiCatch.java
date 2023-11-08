package com.my.experiment.demo02;

public class MultiCatch {
    Node root;

    public MultiCatch() {
        root = null;
    }

    public int sumOfLeafNodes(Node node) {
        if (node == null) {
            return 0;  // 空节点，返回0
        } else if (node.left == null && node.right == null) {
            return node.data;  // 叶子节点，返回节点值
        } else {
            // 递归计算左子树和右子树的叶子节点之和
            return sumOfLeafNodes(node.left) + sumOfLeafNodes(node.right);
        }
    }


    public static void main(String[] args) {
        MultiCatch tree = new MultiCatch();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);
        tree.root.right.left = new Node(6);
        tree.root.right.right = new Node(7);

        int sum = tree.sumOfLeafNodes(tree.root);
        System.out.println("Sum of leaf nodes: " + sum);
    }
}
