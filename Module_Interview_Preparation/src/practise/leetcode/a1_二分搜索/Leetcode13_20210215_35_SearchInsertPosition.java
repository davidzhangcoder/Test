package practise.leetcode.a1_二分搜索;

public class Leetcode13_20210215_35_SearchInsertPosition {

    private int binarySearch(int[] nums, int target) {
        int s =0;
        int e = nums.length;
        while( s < e ) {
            int m = s + (e-s)/2;
            if( nums[m] >= target )
                e = m;
            else
                s = m + 1;

        }
        return s;
    }

    public int searchInsert(int[] nums, int target) {
        return binarySearch(nums, target);
    }

    public static void main(String[] args) {

    }
}
