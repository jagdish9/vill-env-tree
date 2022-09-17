package dp;

import java.util.LinkedList;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
   TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
       this.right = right;
    }
}

public class BinaryTreeOrderTraversal {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.right = new TreeNode(2);
        head.right.left = new TreeNode(3);
        System.out.println(inorderTraversal(head));
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        inOrder(root, result);
        return result;
    }

    public static void inOrder(TreeNode root, List<Integer> result) {
        if (root == null)
            return;
        inOrder(root.left, result);
        result.add(root.val);
        inOrder(root.right, result);
    }
}
