package practise.leetcode.a4_二叉树;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode19_102_20210219_BinaryTreeLevelOrderTraversal {

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

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        List<Integer> subResult = new ArrayList<>();
        subResult.add(root.val);
        result.add(subResult);

        while (!q.isEmpty()) {
            Queue<TreeNode> tempq = new LinkedList<>();
            subResult = new ArrayList<>();
            while (!q.isEmpty()) {
                TreeNode node = q.poll();
                TreeNode left = node.left;
                TreeNode right = node.right;

                if (left != null) {
                    subResult.add(left.val);
                    tempq.offer(left);
                }
                if (right != null) {
                    subResult.add(right.val);
                    tempq.offer(right);
                }
            }
            if (subResult.size() != 0)
                result.add(subResult);
            q.addAll(tempq);
        }
        return result;
    }

}
