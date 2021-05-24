package practise.leetcode.a3_回溯;

import java.util.ArrayList;
import java.util.List;

public class Leetcode6_39_20210208_CombinationSum  {

    private static void backTrack(List<List<Integer>> result, List<Integer> subList, int[] nums, int numsStart, int target) {
        if( target == 0 ) {
            result.add(new ArrayList<>(subList));
            return;
        }

        if( target < 0)
            return;

        for (int i = numsStart; i < nums.length; i++) {
            subList.add(nums[i]);
            backTrack( result, subList, nums, i, target-nums[i]);
            subList.remove( subList.size()-1 );
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {

        List<List<Integer>> result =  new ArrayList<>();
        backTrack( result, new ArrayList<Integer>(), candidates, 0, target);

        return result;
    }


    //wrong
//    private static void backTrack(List<List<Integer>> result, List<Integer> subList, int[] nums, int numsStart, int target) {
//        System.out.println(subList);
//        if( getTotal(subList) == target )
//            result.add( new ArrayList<>(subList) );
//
//        for (int i = numsStart; i < nums.length ; i++) {
//            int temp = subList.size();
//            while(true) {
//                subList.add(nums[i]);
//                if( getTotal(subList) > target )
//                    break;
//                backTrack( result,  subList,  nums, i+1, target);
//            }
//
//            for (int j = temp; j < subList.size() ; j++) {
//                subList.remove(j);
//            }
//        }
//    }
//
//    private static int getTotal(List<Integer> subList) {
//        int total = subList.stream().collect(Collectors.summingInt(a -> a)).intValue();
////        System.out.println(total);
//        return total;
//
//    }
//
//    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
//
//        List<List<Integer>> result =  new ArrayList<>();
//        for (int i = 0; i < candidates.length; i++) {
//            backTrack( result, new ArrayList<Integer>(), candidates, i, target);
//        }
//
//        return result;
//    }

    public static void main(String[] args) {
//        int[] data = {2,3,6,7};
//        int target = 7;

        int[] data = {2,3,5};
        int target = 8;
        System.out.println( combinationSum(data, target) );
    }

}
