package practise.leetcode.a3_回溯;

import java.util.ArrayList;
import java.util.LinkedList;

public class Leetcode5_78_20210207_Subsets {

    //wrong
//    public static List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> resultList = new LinkedList<List<Integer>>();
//        resultList.add(new ArrayList<>());
//        for (int i = 1; i <= nums.length; i++) {
//            int p = 0;
//            while( p < nums.length ){
//                if( p + i <= nums.length ) {
//                    int[] element = Arrays.copyOfRange(nums, p, p + i);
////                    System.out.println(element);
//                    List<Integer> collect = Arrays.stream(element).boxed().collect(Collectors.toList());
//                    resultList.add(collect);
//                }
//                p = p + i;
//            }
//        }
//        return resultList;
//    }
    
    public static void main(String[] args) {
        int data[] = {1,2,3};

        ArrayList a = new ArrayList<>();
        LinkedList b = new LinkedList<>();

//        System.out.println(subsets(data));
    }

}
