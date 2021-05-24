package practise.template;

public class A7_滑动窗口 {

//    1.
//    /* 滑动窗口算法框架 */
//    void slidingWindow(string s, string t) {
//        unordered_map<char, int> need, window;
//        for (char c : t) need[c]++;
//
//        int left = 0, right = 0;
//        int valid = 0;
//        while (right < s.size()) {
//            // c 是将移入窗口的字符
//            char c = s[right];
//            // 右移窗口
//            right++;
//            // 进行窗口内数据的一系列更新
//        ...
//
//            /*** debug 输出的位置 ***/
//            printf("window: [%d, %d)\n", left, right);
//            /********************/
//
//            // 判断左侧窗口是否要收缩
//            while (window needs shrink) {
//                // d 是将移出窗口的字符
//                char d = s[left];
//                // 左移窗口
//                left++;
//                // 进行窗口内数据的一系列更新
//            ...
//            }
//        }
//    }

//    2.
//    https://lucifer.ren/blog/2020/03/16/slide-window/
//    https://zhuanlan.zhihu.com/p/69818691
//
//    思路是保证右指针每次往前移动一格，每次移动都会有新的一个元素进入窗口，这时条件可能就会发生改变，然后根据当前条件来决定左指针是否移动，以及移动多少格
//
//    public int slidingWindowTemplate(String[] a, ...) {
//        // 输入参数有效性判断
//        if (...) {
//        ...
//        }
//
//        // 申请一个散列，用于记录窗口中具体元素的个数情况
//        // 这里用数组的形式呈现，也可以考虑其他数据结构
//        int[] hash = new int[...];
//
//        // 预处理(可省), 一般情况是改变 hash
//    ...
//
//        // l 表示左指针
//        // count 记录当前的条件，具体根据题目要求来定义
//        // result 用来存放结果
//        int l = 0, count = ..., result = ...;
//        for (int r = 0; r < A.length; ++r) {
//            // 更新新元素在散列中的数量
//            hash[A[r]]--;
//
//            // 根据窗口的变更结果来改变条件值
//            if (hash[A[r]] == ...) {
//                count++;
//            }
//
//            // 如果当前条件不满足，移动左指针直至条件满足为止
//            while (count > K || ...) {
//            ...
//                if (...) {
//                    count--;
//                }
//                hash[A[l]]++;
//                l++;
//            }
//
//            // 更新结果
//            results = ...
//        }
//
//        return results;
//    }

}
