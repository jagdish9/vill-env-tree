package dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PreOrderTraversal {

    public static void main(String[] args) {
        TreeNode head = new TreeNode(1);
        head.right = new TreeNode(2);
        head.right.left = new TreeNode(3);
        head.right.right = new TreeNode(4);
        preOrder(head);
        inOrder(head);
        postOrder(head);
    }

    private static void inOrder(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode curr = head;

        while(!stack.isEmpty() || curr != null) {
          if(curr != null) {
              stack.push(curr);
              curr = curr.left;
          }
          else{
              curr = stack.pop();
              result.add(curr.val);
              curr = curr.right;
          }
        }

        System.out.println(result);
    }

    public static void preOrder(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        TreeNode curr = head;
        stack.push(curr);

        while(!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            result.add(temp.val);
            if(temp.right != null) {
                stack.push(temp.right);
            }
            if(temp.left != null) {
                stack.push(temp.left);
            }
        }

        System.out.println(result);
    }

    public static void postOrder(TreeNode head) {
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> result = new Stack<>();
        TreeNode curr = head;
        stack.push(curr);

        while(!stack.isEmpty()) {
            TreeNode temp = stack.pop();
            result.add(temp.val);
            if(temp.left != null) {
                stack.push(temp.left);
            }
            if(temp.right != null) {
                stack.push(temp.right);
            }
        }

        while(!result.isEmpty()) {
            System.out.print(result.pop() + " ");
        }
    }

}
