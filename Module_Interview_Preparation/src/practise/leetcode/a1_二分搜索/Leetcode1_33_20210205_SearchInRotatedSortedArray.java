package practise.leetcode.a1_二分搜索;

public class Leetcode1_33_20210205_SearchInRotatedSortedArray {
//    private int findPilot(int[] nums, int s, int e) {
//        if( s == e )
//            return s;
//        int mid = (s+e)/2;
//        int midValue = nums[mid];
//        if (nums[s] < midValue)
//            return findPilot(nums, mid, e);
//        else
//            return findPilot(nums, s,mid);
//    }

    private int findPilot(int[] nums) {
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

    private int findTarget(int[] nums , int s, int e, int target) {
        while(s<e) {
            int mid = s + (e - s + 1)/2;
            if(nums[mid]>target)
                e = mid-1;
            else
                s = mid;
        }
        if(s==e && nums[s]== target)
            return s;
        else
            return -1;
    }

    public int search(int[] nums, int target) {
        int pilot = findPilot(nums);

        int s = 0;
        int e = pilot-1;
        int result = findTarget(nums, s, e<0?0:e, target);
        if ( result != -1 )
            return result;

        s = pilot;
        e = nums.length -1;
        return findTarget(nums, s, e, target);




//        int result = Arrays.binarySearch(nums, 0, pilot , target);
//        System.out.println("result1:"+result);
//        if ( result != -1 )
//            return result;
//        System.out.println("result2:"+Arrays.binarySearch(nums, pilot, nums.length , target));
//        return Arrays.binarySearch(nums, pilot, nums.length , target);
    }

    public static void main(String[] args) {
        Leetcode1_33_20210205_SearchInRotatedSortedArray a = new Leetcode1_33_20210205_SearchInRotatedSortedArray();
        int[] data = new int[] {3,1};
        System.out.println( a.findPilot(data));
        System.out.println( a.search(data,3));
    }
}
