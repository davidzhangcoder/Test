package practise.leetcode.a8_数组;

public class Leetcode_29_15_3Sum {

    //wrong
//    public static List<List<Integer>> threeSum(int[] nums) {
//
//        if( nums == null || nums.length < 3 )
//            return new ArrayList<>();
//
//        Set<List<Integer>> result = new HashSet<List<Integer>>();
//        Arrays.sort(nums);
//        for(int i = 0 ; i < nums.length; i++) {
//            int[] pairs = finding(nums, i, nums[i]);
//            if( pairs != null ) {
//                int[] a = new int[]{pairs[0],pairs[1],nums[i]};
//                Arrays.sort(a);
//                List<Integer> subResult = Arrays.stream(a).boxed().collect(Collectors.toList());
//                result.add(subResult);
//            }
//        }
//
//        return new ArrayList<List<Integer>>(result);
//    }
//
//    private static int[] finding(int[] nums, int i , int value) {
//        int s = 0;
//        int e = nums.length-1;
//        while( s < e ) {
//            int sum = value + nums[s] + nums[e];
//            if( sum==0 ) {
//                if( i != s && i != e )
//                    return new int[]{nums[s],nums[e]};
//                else{
//                    if( i == s )
//                        s++;
//                    else if( i == e )
//                        e--;
//                }
//            }
//            else if(sum>0){
//                if( (e = e-1) == i)
//                    e--;
//            }
//            else if(sum <0){
//                if( (s=s+1) == i )
//                    s++;
//            }
//        }
//        return null;
//    }

    public static void main(String[] args) {
//        System.out.println( threeSum(new int[]{-1,0,1,2,-1,-4}) );
    }

}
