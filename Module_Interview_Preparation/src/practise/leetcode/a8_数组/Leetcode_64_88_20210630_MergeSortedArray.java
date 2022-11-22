package practise.leetcode.a8_数组;

public class Leetcode_64_88_20210630_MergeSortedArray {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m + n -1;
        while(m>0 && n >0){
            if(nums1[m-1]>nums2[n-1]) {
                nums1[p]=nums1[m-1];
                m--;
                p--;
            }else {
                nums1[p]=nums2[n-1];
                n--;
                p--;
            }
        }
        if(m>0){
            for (int i = m-1; i >=0 ; i--) {
                nums1[p]=nums1[i];
                p--;
            }
        } else if(n>0){
            for (int i = n-1; i >=0 ; i--) {
                nums1[p]=nums2[i];
                p--;
            }
        }
    }

}
