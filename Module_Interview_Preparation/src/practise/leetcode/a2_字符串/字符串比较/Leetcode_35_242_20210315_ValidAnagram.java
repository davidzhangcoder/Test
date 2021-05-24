package practise.leetcode.a2_字符串.字符串比较;

public class Leetcode_35_242_20210315_ValidAnagram {

    public boolean isAnagram(String s, String t) {
        int[] sa = new int[26];
        int[] ta = new int[26];
        for (int i = 0; i < sa.length; i++) {
            sa[i]=0;
        }
        for (int i = 0; i < ta.length; i++) {
            ta[i]=0;
        }
        for (char c : s.toCharArray()) {
            sa[c-'a']++;
        }
        for (char c : t.toCharArray()) {
            ta[c-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if(sa[i]!=ta[i])
                return false;
        }
        return true;
    }

}
