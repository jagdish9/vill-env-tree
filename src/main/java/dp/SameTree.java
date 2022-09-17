package dp;

public class SameTree {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        node1.left = new TreeNode(2);
        node1.right = new TreeNode(3);

        TreeNode node2 = new TreeNode(1);
        node2.left = new TreeNode(2);
        node2.right = new TreeNode(3);

        SameTree sameTree = new SameTree();
        System.out.println(sameTree.isSameTree(node1, node2));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
        return p == null && q == null;
    }
}
