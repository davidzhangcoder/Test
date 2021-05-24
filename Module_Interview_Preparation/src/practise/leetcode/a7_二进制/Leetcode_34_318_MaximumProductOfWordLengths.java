package practise.leetcode.a7_二进制;

public class Leetcode_34_318_MaximumProductOfWordLengths {

    public static int maxProduct(String[] words) {
        int[] bitmap = new int[words.length];
        for (int i = 0 ; i < words.length ; i++) {
            char[] chars = words[i].toCharArray();
            for (char aChar : chars) {
                bitmap[i] = bitmap[i] | 1 << (aChar - 'a');
            }
        }

//        for (int i : bitmap) {
//            System.out.println(Integer.toBinaryString(i));
//        }

        int max = 0;

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if( (bitmap[i]&bitmap[j]) != 0)
                    max = Math.max( max , words[i].length() * words[j].length());
            }
        }
        return max;
    }

    public static void main(String[] args) {
        maxProduct(new String[]{"az", "ad"});
    }

}
