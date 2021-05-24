package practise.leetcode.a1_二分搜索;

public class Leetcode9_34_20210209_34_FindFirstAndLastPositionOfElementInSortedArray {

    private static int findL(int[] nums, int target) {
        int s = 0;
        int e = nums.length-1;
        while( s < e )
        {
            int m = s + (e-s)/2;
            if(nums[m]<target)
                s = m +1;
            else
                e =m;
        }
        return nums.length!=0&&nums[s]==target?s:-1;
    }

    private static int findR(int[] nums, int target) {
        int s = 0;
        int e = nums.length-1;
        while( s < e )
        {
            int m = s + (e-s+1)/2;
            if(nums[m]>target)
                e = m -1;
            else
                s =m;
        }
        return nums.length!=0&&nums[e]==target?e:-1;
    }

    public int[] searchRange(int[] nums, int target) {
        return new int[]{findL(nums,target),findR(nums,target)};
    }

    public static void main(String[] args) {
        int data[] = {5,7,7,8,8,10};
        int target = 8;

//        int data[] = {5,7,7,8,8,10};
//        int target = 6;

//        int data[] = {};
//        int target = 0;

        System.out.println(findL(data,target));
        System.out.println(findR(data,target));
    }

}
