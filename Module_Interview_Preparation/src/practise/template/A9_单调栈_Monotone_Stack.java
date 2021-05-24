package practise.template;

public class A9_单调栈_Monotone_Stack {

//    https://www.shangmayuan.com/a/5681f70f3f684a5188735068.html
//    https://www.cnblogs.com/grandyang/p/8887985.html

//    适用场景
//    单调栈适合的题目是求解第一个一个大于 xxx或者第一个小于 xxx这种题目。全部当你有这种需求的时候，就应该想到单调栈。
//
//    那么为何单调栈适合求解第一个一个大于 xxx或者第一个小于 xxx这种题目

//    单调栈的一大优势就是线性的时间复杂度，所有的元素只会进栈一次，而且一旦出栈后就不会再进来了

//    这个算法的过程用一句话总结就是，若是压栈以后仍然能够保持单调性，那么直接压。不然先弹出栈的元素，直到压入以后能够保持单调性。
//    这个算法的原理用一句话总结就是，被弹出的元素都是大于当前元素的，而且因为栈是单调增的，所以在其以后小于其自己的最近的就是当前元素了

    //求第一个小于 xxx （从栈底，从小到大）
    //1 3 2 4 5
    //栈底： 1 3
    //栈底： 1 2
    //栈底： 1 2 4 5
    //［－1，2，－1，－1， －1］

    //求第一个一个大于 xxx （从栈底，从大到小）
    //栈底: 1
    //栈底: 3
    //栈底: 3 2
    //栈底: 4
    //栈底: 5
    
//    class Solution:
//    def monostoneStack(self, arr: List[int]) -> List[int]:
//    stack = []
//    ans = 定义一个长度和 arr 同样长的数组，并初始化为 -1
//    循环 i in  arr:
//            while stack and arr[i] > arr[栈顶元素]:
//              peek = 弹出栈顶元素
//              ans[peek] = i - peek
//            stack.append(i)
//    return ans

//    var monostoneStack = function (T) {
//        let stack = [];
//        let result = [];
//        for (let i = 0; i < T.length; i++) {
//            result[i] = 0;
//            while (stack.length > 0 && T[stack[stack.length - 1]] < T[i]) {
//                let peek = stack.pop();
//                result[peek] = i - peek;
//            }
//            stack.push(i);
//        }
//        return result;
//    };

}
