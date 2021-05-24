package practise.leetcode.a1_二分搜索;

public class Leetcode3_153_20210206_FindMinimumInRotatedSortedArray {

    private static int findP(int[] nums) {
        int s = 0;
        int e = nums.length - 1;
        while(s < e) {
           int mid = s + (e-s)/2;
           if( nums[mid] >= nums[0] )
               s=mid+1;
           else
               e=mid;
        }
        return s;
    }

    private static int findPilot(int[] nums) {
        int s = 0;
        int e = nums.length -1;
        while ( s < e ) {
            int mid = s + (e - s + 1)/2;
            if( nums[mid]> nums[0] )
                s = mid;
            else
                e = mid - 1;
        }
        return s;
    }


    public int findMin(int[] nums) {
        int p = findP(nums);
        return Math.min(nums[0],nums[p]);
    }

    public static void main(String[] args) {
//        int[] data = {4,5,6,7,0,1,2};
        int[] data = {2,1};
        System.out.println(findP(data));
        System.out.println(findPilot(data));
    }

}
