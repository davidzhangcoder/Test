package practise.leetcode.a3_回溯;

import java.util.ArrayList;
import java.util.List;

public class Leetcode38_131_20210316_PalindromePartitioning {

    private static void backtracking(List<List<String>> result, List<String> temp, String s, int i) {
        if (i >= s.length())
            result.add(new ArrayList<>(temp));

        for (int k = 1; k <= s.length() - i; k++) {
            //int e = i + k > s.length() ? s.length() : i + k;
            String substring = s.substring(i, i+k);
            if (isPalindrome(substring)) {
                temp.add(substring);
                backtracking(result, temp, s, i+k);
                temp.remove(temp.size() - 1);
            }
        }

    }

    private static boolean isPalindrome(String s) {
        if (s.length() == 1)
            return true;
        int l = 0;
        int h = s.length() - 1;
        while (l < h) {
            if (s.charAt(l) != s.charAt(h))
                return false;
            l++;
            h--;
        }
        return true;
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        backtracking(result, new ArrayList<String>(), s, 0);
        return result;
    }


    //wrong
//    private static void backtracking(List<List<String>> result, List<String> temp, String s, int limit) {
//        for (int i = 0; i < s.length();) {
//            String substring = s.substring(i, i + limit>s.length()?s.length():i+limit);
//            if (isPalindrome(substring)) {
//                temp.add(substring);
//                i = i + limit;
//            }
//            else
//                return;
//        }
//        result.add(temp);
//    }

//    private static boolean isPalindrome(String s) {
//        if(s.length()==1)
//            return true;
//        int l = 0;
//        int h = s.length()-1;
//        while( l < h ){
//            if(s.charAt(l) != s.charAt(h))
//                return false;
//            l++;
//            h--;
//        }
//        return true;
//    }

//    public static List<List<String>> partition(String s) {
//        List<List<String>> result = new ArrayList<List<String>>();
//        for (int i = 1; i <= s.length(); i++) {
//            backtracking(result, new ArrayList<String>(), s, i);
//        }
//        return result;
//    }

    public static void main(String[] args) {
        //System.out.println(partition("aabbcef"));
        List<Integer> a1 = new ArrayList<Integer>();
        a1.add(1);
        a1.add(2);
        a1.add(3);

    }

}
