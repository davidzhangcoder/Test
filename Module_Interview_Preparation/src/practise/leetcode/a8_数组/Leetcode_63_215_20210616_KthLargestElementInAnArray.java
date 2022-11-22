package practise.leetcode.a8_数组;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class Leetcode_63_215_20210616_KthLargestElementInAnArray {
    public static void main(String[] args) {
        int[] a = new int[]{3,2,3,1,2,4,5,5,6};
        int k = 4;
        System.out.println(findKthLargest1(a,k));
    }
    public static int findKthLargest2(int[] nums, int k){
        Set<Integer> s = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0-o1.compareTo(o2);
            }
        });
        for (int num : nums) {
            s.add(num);
        }
        if(k<=s.size())
            return s.toArray(new Integer[s.size()])[k-1];
        else
            return 0;

    }
    public static int findKthLargest1(int[] nums, int k) {
        PriorityQueue<Integer> a = new PriorityQueue(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });
        for (int num : nums) {
            a.add(num);
            if(a.size()>k)
                a.poll();
        }
        return a.toArray(new Integer[a.size()])[a.size()-k];
    }
}
