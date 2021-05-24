package practise.leetcode.a9_滑动窗口;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_45_76_20210321_MinimumWindowSubstring {

    public String minWindow(String s, String t) {
        int left = 0, right = 0;
        Map<Character,Integer> tm = new HashMap<Character,Integer>();
        Map<Character,Integer> cm = new HashMap<Character,Integer>();
        for( char tc : t.toCharArray())
            tm.put(tc, tm.getOrDefault(tc,0)+1);
        while( left < s.length() ){
            char rightc = s.charAt(right);
            cm.put(rightc, tm.getOrDefault(rightc,0)+1);
            while( isValid(tm, cm) ){
                left++;
                char leftc = s.charAt(left);
                cm.put(leftc, cm.get(leftc)-1);
            }
            right++;
        }
        return "";
    }

    private boolean isValid(Map<Character, Integer> tm, Map<Character, Integer> cm) {
        return false;
    }

}
