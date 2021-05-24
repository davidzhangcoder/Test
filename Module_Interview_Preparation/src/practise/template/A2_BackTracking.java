package practise.template;

import java.util.ArrayList;
import java.util.List;

public class A2_BackTracking {

//    回溯法（backtrack）常用于遍历列表所有子集，是 DFS 深度搜索一种，一般用于全排列，穷尽所有可能，遍历的过程实际上是一个决策树的遍历过程。时间复杂度一般 O(N!)

//    1.
//    模板:
//    result = []
//    func backtrack(选择列表,路径):
//      if 满足结束条件:
//          result.add(路径)
//          return
//      for 选择 in 选择列表:
//          做选择
//          backtrack(选择列表,路径)
//          撤销选择


//    2. https://blog.csdn.net/tangyuan_sibal/article/details/90523192
//    public List<List<Integer>> backtrack(int[] a)
//    {
//        List<List<Integer>> list = new ArrayList<>();
//        //Arrays.sort(a);//当a中存在重复值。而重复值不能使用的时候。就要进行排序。对使用过的重复值不再使用
//        backTrackTemp(list, new ArrayList<>(), a, .....)//其中的"...."表示其他限定条件(根据条件限定而存在与否)
//        return lis
//    }
//    //回溯过程
//    private static void backTrackTemp(List<List<Object>> list, Arraylist<Integer> tempList, int[] a,....)
//    {
//        //终止条件，也就是一次结果或者不符合条件
//        if(false)//false代表条件不符合
//            return false;
//        if(true)//当符合需要的结果
//            list.add(new ArrayList(tempList))//注意这里要重新创建，因为tempList是一个对象，改变的话，会改变结果值。所以重新创建
//        //对每个值进行回溯
//        for(int i = start; i < a.length; i++)
//        {
//            if(true)//存在某个限定条件的，比如出现重复值,跳过(根据条件限定而存在与否)
//                continue;
//            mask(used(i));//将i标记为已使用(根据条件限定而存在与否)
//            backTrackTemp(list, tempList, a, i+1)//此处的i+1也可以根据实际情况判断题目中的数字是否可以重复使用
//            unmask(used(i));//回溯完要记得取消掉
//            tempList.remove(tempList.size() - 1);//回溯回父节点.寻找下一个节点
//        }
//    }

    //以上2的实际使用的例子 － Permutations
//    public static List<List<Integer>> permute(int[] nums) {
//        List<List<Integer>> list = new ArrayList<>();
//        backtrack(list, new ArrayList<>(), nums,new boolean[nums.length]);//回溯法的三要素，条件值nums,存储结果数组list,临时结果(判断用的)。还有其他的额外控制条件
//        return list;
//    }
//
//    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean[] used){
//
//        if(tempList.size() == nums.length){//终止条件
//            list.add(new ArrayList<>(tempList));//这边要new的原因是因为下面的对象引用还存在。更改会改变这个list里面的值
//        } else{
//            for(int i = 0; i < nums.length; i++){//回溯过程
//                if(used[i] == true)
//                    continue;
//                else {
//                    tempList.add(nums[i]);
//                    used[i] = true;//访问过了就不要再访问
//                    backtrack(list, tempList, nums, used);
//                    used[i] = false;//一次回溯后就要将访问的重新置为未访问的
//                    tempList.remove(tempList.size() - 1);
//                }
//
//            }
//        }
//    }






    List<List<Integer>> output = new ArrayList();
    int n, k;

    public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
        // if the combination is done
        if (curr.size() == k)
            output.add(new ArrayList(curr));

        for (int i = first; i < n; ++i) {
            // add i into the current combination
            curr.add(nums[i]);
            // use next integers to complete the combination
            backtrack(i + 1, curr, nums);
            // backtrack
            curr.remove(curr.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        n = nums.length;
        for (k = 0; k < n + 1; ++k) {
            backtrack(0, new ArrayList<Integer>(), nums);
        }
        return output;
    }

}
