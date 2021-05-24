package practise.leetcode.a2_字符串;

public class Leetcode4_28_20210207_ImplementStrStr {

    public int strStr(String haystack, String needle) {
        if ("".equals(needle))
            return 0;
        if ("".equals(haystack) || needle.length() > haystack.length())
            return -1;
        for (int i = 0; i <= haystack.length()-needle.length(); i++) {
            boolean found = true;
            for (int j = 0; j < needle.length(); j++) {
                if( haystack.charAt(i+j) != needle.charAt(j) ) {
                    found=false;
                    break;
                }
            }
            if(found)
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
