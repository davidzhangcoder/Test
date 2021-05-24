package practise.template;

public class A1_BinarySearch {

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

//    1.
//    # 如果 target 比 nums里所有的数都大，则最后一个数的索引 + 1 就是候选值，因此，右边界应该是数组的长度
//
//    2.
//    取中位数的时候，要避免在计算上出现整型溢出
//    int mid = (left + right) / 2; 的问题：在 left 和 right 很大的时候，left + right 会发生整型溢出，变成负数，这是一个 bug ，得改！
//
//    int mid = left + (right - left) / 2; 在 right 很大、 left 是负数且很小的时候， right - left 也有可能超过 int 类型能表示的最大值，只不过一般情况下 left 和 right 表示的是数组索引值，left 是非负数，因此 right - left 溢出的可能性很小。因此，它是正确的写法
//
//    建议使用 int mid = (left + right) >>> 1 这种写法，其实是大有含义的：
//
//    JDK8 中采用 int mid = (left + right) >>> 1 ，重点不在 + ，而在 >>> 。
//
//    我们看极端的情况，left 和 high 都是整型最大值的时候，注意，此时 32 位整型最大值它的二进制表示的最高位是 0，它们相加以后，最高位是 1 ，变成负数，但是再经过无符号右移 >>>（重点是忽略了符号位，空位都以 0 补齐），就能保证使用 + 在整型溢出了以后结果还是正确的
//    3.
//    使用 int mid = left + (right - left) / 2 ;  得到左中位数的索引；
//
//    使用 int mid = left + (right - left + 1) / 2 ;  得到右中位数的索引。
//
//    当数组的元素个数是奇数的时候，以上二者都能选到最中间的那个中位数。
//
//    4.
//    选择中位数的依据是：避免出现死循环。我们需要确保：
//            1、如果分支的逻辑，在选择左边界的时候，不能排除中位数，那么中位数就选“右中位数”，只有这样区间才会收缩，否则进入死循环；
//            2、同理，如果分支的逻辑，在选择右边界的时候，不能排除中位数，那么中位数就选“左中位数”，只有这样区间才会收缩，否则进入死循环。
    
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return nums[left] == target ? left : -1;
    }

}
