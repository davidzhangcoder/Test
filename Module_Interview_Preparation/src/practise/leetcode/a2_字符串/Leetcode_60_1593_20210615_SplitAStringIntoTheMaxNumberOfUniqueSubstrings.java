package practise.leetcode.a2_字符串;

import java.util.HashSet;
import java.util.Set;

public class Leetcode_60_1593_20210615_SplitAStringIntoTheMaxNumberOfUniqueSubstrings {

    public int maxUniqueSplit(String s) {
        Set<String> set = new HashSet<>();
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < s.length(); i++) {
            sb.append(String.valueOf(s.charAt(i)));
            String cs = sb.toString();
            if(!set.contains(cs)) {
                set.add(cs);
                sb=new StringBuffer();
            }
        }
        return set.size();
    }

}
