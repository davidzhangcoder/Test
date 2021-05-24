package leetcode;

public class L20201030_415_AddStrings {

    public String addStrings(String num1, String num2) {
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();

        if(chars2.length > chars1.length) {
            char[] temp = chars1;
            chars1 = chars2;
            chars2 = temp;
        }

        int carry = 0;
        int char2Position = chars2.length - 1;
        for (int i = chars1.length - 1; i >= 0; i--) {
            if(char2Position<0&&carry==0)
                break;

            int value1 = chars1[i] - '0';
            int value2 = char2Position>=0?chars2[char2Position] - '0':0;

            int sum = value1 + value2 + carry;
            carry = sum/10;
            chars1[i] = (char)(sum%10 + '0');

            char2Position--;
        }
        return String.valueOf(chars1);
    }

    public static void main(String[] args) {
//        String a = "1";
//        System.out.println( (int)(a.toCharArray()[0] - '0') + 1);

        String a = "12";
        char[] b = a.toCharArray();
        b[0] = 7+'0';
        b[1] = 5;
        System.out.println(String.valueOf(b));
    }

}
