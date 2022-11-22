package practise.leetcode.a2_字符串.字符串比较;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//not finished
public class Leetcode_36_205_20210315_IsomorphicStrings {

    public boolean isIsomorphic(String s, String t){
        if( s==null && t != null )
            return false;
        if( s!=null && t == null )
            return false;
        if( s.length() != t.length() )
            return false;

        Map<Integer, List<Integer>> map = new HashMap<Integer,List<Integer>>();

        char[] chars1 = s.toCharArray();
        char[] chars2 = t.toCharArray();
        for (int i = 0; i < chars1.length; i++) {
            int n1 = Character.getNumericValue(chars1[i]);
            int n2 = Character.getNumericValue(chars2[i]);
            if (map.get(n2) == null) {
//                if(map.get(n2)!=null && map.get(n2).intValue() != n1)
//                    return false;
//                map.put(n1, n2);
//                map.put(n2, n1);
            }
            else {
                if( n1 != n2 && !map.get(n1).contains(n2) ){
                    return false;
                }
            }
        }

        return true;
    }

    //wrong
//    public boolean isIsomorphic(String s, String t) {
//        if(s.length()!=t.length())
//            return false;
//        int[] sa = new int[256];
//        for (int i = 0; i < sa.length; i++) {
//            sa[i]=0;
//        }
//        char[] chars1 = s.toCharArray();
//        char[] chars2 = t.toCharArray();
//        for (int i = 0; i < chars1.length; i++) {
//            if(chars1[i]!=chars2[i]) {
//                sa[chars1[i]]++;
//                sa[chars2[i]]++;
//            }
//            else
//                sa[chars1[i]]++;
//        }
//        for (int i = 0; i < sa.length; i++) {
//            if(sa[i]>1)
//                return false;
//        }
//        return true;
//    }

//    "AQ!@$d"
//    "A@VDS^"

//    "bbbaaaba"
//    "aaabbbba"

    //wrong
//    public boolean isIsomorphic(String s, String t) {
//        if(s.length()!=t.length())
//            return false;
//        int[] sa = new int[256];
//        int[] ta = new int[256];
//        for (int i = 0; i < sa.length; i++) {
//            sa[i]=0;
//        }
//        for (int i = 0; i < ta.length; i++) {
//            ta[i]=0;
//        }
//        for (char c : s.toCharArray()) {
//            sa[c]++;
//        }
//        for (char c : t.toCharArray()) {
//            ta[c]++;
//        }
//        Arrays.sort(sa);
//        Arrays.sort(ta);
//        for (int i = 0; i < 256; i++) {
//            if(sa[i]!=ta[i])
//                return false;
//        }
//        return true;
//    }

}
