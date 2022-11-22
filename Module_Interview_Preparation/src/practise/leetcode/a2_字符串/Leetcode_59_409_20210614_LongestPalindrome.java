package practise.leetcode.a2_字符串;

public class Leetcode_59_409_20210614_LongestPalindrome {

    public int longestPalindrome(String s) {
        int[] a = new int['z'-'A'+1];
        for (int i = 0; i < s.length(); i++) {
            char index = s.charAt(i);
            a[index - 'A']++;
        }

        int result = 0;
        boolean foundOdd = false;
        for (int i = 0; i < a.length; i++) {
            result = result + (a[i]/2)*2;
            if(!foundOdd && a[i]%2!=0) {
                result++;
                foundOdd=true;
            }
        }
        return result;
    }

}
