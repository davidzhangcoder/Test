package practise.leetcode.a8_数组;

import java.util.ArrayList;
import java.util.List;

public class Leetcode_41_448_20210320_FindAllNumbersDisappearedInAnArray {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if( nums[i]-1 == i )
                nums[i]=0;
            else if( nums[i] !=0 ) {
                int j = nums[i] - 1;
                while (nums[j] != 0) {
                    int temp = nums[j];
                    nums[j] = 0;
                    j = temp - 1;
                }
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] != 0)
                result.add(i+1);
        }
        return result;
    }

}
