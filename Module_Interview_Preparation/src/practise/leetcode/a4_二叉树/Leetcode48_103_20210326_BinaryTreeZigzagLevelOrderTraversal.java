package practise.leetcode.a4_二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Leetcode48_103_20210326_BinaryTreeZigzagLevelOrderTraversal {

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

//    private List<Integer> doZigzagLevelOrder(int level, Deque<TreeNode> deque) {
        //        List<Integer> result = new ArrayList<Integer>();
//        if( level % 2 != 0 )
//            while( !deque.isEmpty())
//                result.add( deque.removeFirst().val );
//        else
//            while( !deque.isEmpty())
//                result.add( deque.removeFirst().val );
//        return result;
//    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        Stack<TreeNode> s = new Stack<TreeNode>();
        Stack<TreeNode> s1 = new Stack<TreeNode>();

        List<Integer> subresult = new ArrayList<Integer>();
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        int count = 1;

        if( root != null ) {
            s.push(null);
            s.push(root);
        }

        while( !s.isEmpty() ){
            if( s.peek() != null ) {
                TreeNode pop = s.pop();
                subresult.add(pop.val);

                if( count % 2 != 0 ) {
                    if (pop.left != null)
                        s1.push(pop.left);
                    if (pop.right != null)
                        s1.push(pop.right);
                } else {
                    if (pop.right != null)
                        s1.push(pop.right);
                    if (pop.left != null)
                        s1.push(pop.left);
                }
            }
            else{
                s.pop();
                result.add(subresult);
                if( !s1.isEmpty() ) {
                    s.push(null);
                    s.addAll(s1);
                    s1.clear();
                    subresult = new ArrayList<Integer>();
                    count++;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(9);
        TreeNode t3 = new TreeNode(20);

        t1.left = t2;
        t1.right = t3;

        System.out.println( zigzagLevelOrder(t1) );
    }

}
