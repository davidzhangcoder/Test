package practise.leetcode.a3_回溯;

import java.util.ArrayList;
import java.util.List;

public class Leetcode10_17_20210213_LetterCombinationsOfAPhoneNumber {

    private static String[] v = {"","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    private static void backTracking(List<String> result,StringBuffer subResult, String digits, int s) {
        if( s == digits.length() && subResult.length() == digits.length()) {
            result.add( new String(subResult) );
            return;
        }

        for (int i = s; i < digits.length() ; i++) {
            String d = digits.substring(i, i + 1);
            int index = Integer.parseInt(d);
            for (int j = 0; j < v[index-1].length(); j++) {
                String element = v[index - 1].substring(j, j + 1);
                subResult.append(element);
                backTracking(result, subResult, digits, i+1);
                subResult.deleteCharAt(subResult.length()-1);
            }
        }
    }

    public List<String> letterCombinations(String digits) {
        if(digits==null || digits.length()==0)
            return new ArrayList<String>();
        List<String> result = new ArrayList<>();
        backTracking(result, new StringBuffer(), digits, 0);
        return result;
    }

    private static void testString(String data) {
        String newData = data.replaceAll("2","a");
        System.out.println("inner" + data + newData);
    }

    public static void main(String[] args) {
        String data = "234";

//        List<String> result = new ArrayList<>();
//        backTracking(result, new StringBuffer(), data, 0);
//
//        System.out.println(result);
        testString(data);
        System.out.println("outter" + data);
    }

}
