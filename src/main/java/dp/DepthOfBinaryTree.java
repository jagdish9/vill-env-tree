package dp;

public class DepthOfBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        DepthOfBinaryTree depthOfBinaryTree = new DepthOfBinaryTree();

        System.out.println(depthOfBinaryTree.maxDepth(root));
    }

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;

        return Integer.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
