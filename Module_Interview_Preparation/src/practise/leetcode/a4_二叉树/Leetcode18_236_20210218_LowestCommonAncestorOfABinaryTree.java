package practise.leetcode.a4_二叉树;

import java.util.ArrayList;
import java.util.List;

public class Leetcode18_236_20210218_LowestCommonAncestorOfABinaryTree {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //solved
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if( root == null )
            return null;

        if( root == p || root == q )
            return root;

        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);

        if( left != null  && right != null )
            return root;
        if( left != null)
            return left;
        if( right != null )
            return right;
        return null;
    }

    private static boolean findPath(TreeNode root, TreeNode n, List<TreeNode> result) {
        if( root == null )
            return false;

        result.add(root);

        if( root == n ){
            return true;
        }

        boolean foundInLeft = findPath(root.left, n, result);
        if( foundInLeft )
            return true;

        boolean foundInRight = findPath(root.right, n, result);
        if (foundInRight) {
            return true;
        }

        result.remove(result.size()-1);
        return false;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pPath = new ArrayList<>();
        findPath(root, p, pPath);

        List<TreeNode> qPath = new ArrayList<>();
        findPath(root, q, qPath);

        int end = Math.min(pPath.size() , qPath.size());

        int foundIndex =-1;
        for (int i = 0; i < end; i++) {
            if( pPath.get(i) != qPath.get(i) ){
                foundIndex = i;
                break;
            }
        }
        return foundIndex!=-1?pPath.get(foundIndex-1):pPath.get(end-1);
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(1);
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(4);

        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t4.right = t5;

//        List<TreeNode> pPath = new ArrayList<>();
//        findPath(t1, t2, pPath);
//        System.out.println( pPath.toString());

        System.out.println(lowestCommonAncestor(t1,t2,t3));

    }

}
