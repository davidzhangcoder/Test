package practise.leetcode.a3_回溯;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Leetcode_55_491_20210612_IncreasingSubsequences {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            doFindSubsequences(result, new ArrayList<>(), nums , i);
        }
        return result;
    }

    public void doFindSubsequences(List<List<Integer>> result , List<Integer> subResult , int[] nums , int i) {
        if(!subResult.isEmpty()){
            Integer e = subResult.get(subResult.size() - 1);
            if(nums[i] >= e.intValue()){
                subResult.add(nums[i]);
                if (!result.contains(subResult))
                    result.add(new ArrayList(subResult));
            }
        } else {
            subResult.add(nums[i]);
        }
        if( i+1 < nums.length) {
            for(int j = i +1 ; j < nums.length ; j++) {
                doFindSubsequences(result, subResult, nums, j);
                if (!subResult.isEmpty())
                    subResult.remove(subResult.size() - 1);
            }
        }
    }

    public List<List<Integer>> findSubsequences1(int[] nums) {
        Set<List<Integer>> result = new HashSet<>();
        doFindSubsequences1(result, new ArrayList<>(), nums , 0);
        return new ArrayList<>(result);
    }

    public void doFindSubsequences1(Set<List<Integer>> result , List<Integer> subResult , int[] nums , int i) {
            for(int j = i ; j < nums.length ; j++) {
                if(!subResult.isEmpty()){
                    Integer e = subResult.get(subResult.size() - 1);
                    if(nums[i] >= e.intValue()){
                        subResult.add(nums[j]);
                        result.add(new ArrayList(subResult));
                    }
                } else {
                    subResult.add(nums[j]);
                }

                doFindSubsequences1(result, subResult, nums, j+1);
                if (!subResult.isEmpty())
                    subResult.remove(subResult.size() - 1);
            }
    }

}
