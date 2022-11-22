package practise.leetcode.a3_回溯;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_56_77_20210613_Combinations {

    private void backTracing(List<List<Integer>> result, List<Integer> subResult, int n , int k , int i) {
        if(subResult.size() == k) {
            result.add(new ArrayList<>(subResult));
            return;
        }

        for(int j = i + 1 ; j <= n ; j++) {
            subResult.add(j);
            backTracing(result, subResult, n, k, j);
            subResult.remove(subResult.size()-1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        backTracing(result,new ArrayList<Integer>(), n, k ,0);
        return result;
    }

}
