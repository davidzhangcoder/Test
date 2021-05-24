package practise.leetcode.a3_回溯;

import java.util.ArrayList;
import java.util.List;

public class Leetcode7_46_20210208_Permutations {

    private static void backTrack(List<List<Integer>> result, List<Integer> subList , int[] nums ) {
        if( subList.size() == nums.length ) {
            result.add(new ArrayList<>(subList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if( !subList.contains(nums[i]) ) {
                subList.add( nums[i] );
                backTrack( result, subList, nums);
                subList.remove( subList.size() - 1);
            }
        }

    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack( result, new ArrayList<>(), nums );
        return result;
    }

    public static void main(String[] args) {
        int[] data = {1,2,3};
        System.out.println(permute(data));
    }

}
