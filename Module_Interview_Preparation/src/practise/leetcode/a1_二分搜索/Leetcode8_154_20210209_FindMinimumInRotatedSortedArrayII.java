package practise.leetcode.a1_二分搜索;

public class Leetcode8_154_20210209_FindMinimumInRotatedSortedArrayII {

    //wrong
//    private static int findP(int[] n){
//        int s= 0;
//        int e = n.length -1;
//        while( s<e ) {
//            int m = s + (e-s)/2;
//            if( n[m] == n[0] )
//                e = e - 1;
//            else if( n[m] > n[0] )
//                s = m + 1;
//            else
//                e = m;
//        }
//        return n[s+1]<n[s]?s+1:s;
//    }

    private static int findP(int[] nums) {
        int s= 0;
        int e = nums.length;
        while( s < e ) {
            int m = s + (e-s)/2;
            if(nums[m] > nums[0])
                s = m + 1;
            else if(nums[m] < nums[0])
                e = m;
            else
                 e= e -1;
        }
        return s;
    }

    public int findMin(int[] nums) {
        return  0;
    }

    public static void main(String[] args) {
//        int[] data = {1,3,5};
        int[] data = {2,2,2,0,1};

        System.out.println(findP(data));
    }
}
