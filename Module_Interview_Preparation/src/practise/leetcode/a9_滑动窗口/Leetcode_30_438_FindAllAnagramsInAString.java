package practise.leetcode.a9_滑动窗口;

import java.util.List;

public class Leetcode_30_438_FindAllAnagramsInAString {

    public List<Integer> findAnagrams(String s, String p) {

        int[] info = new int[26];

        char[] sArray = s.toCharArray();
        char[] pArray = p.toCharArray();

        for (char c : pArray) {
            info[c-'a']++;
        }

        int l = 0, r = 0;

        while( r < s.length()) {
            info[sArray[r]-'a']--;
            r++;

            if( r > p.length() ) {
                l++;
            }
        }

        return null;
    }

}
