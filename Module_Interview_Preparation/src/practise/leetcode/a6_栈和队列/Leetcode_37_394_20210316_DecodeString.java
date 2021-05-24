package practise.leetcode.a6_栈和队列;

import java.util.Stack;

public class Leetcode_37_394_20210316_DecodeString {

    public static String decodeString(String s) {
        Stack<String> stack = new Stack<String>();

        StringBuffer nsb = new StringBuffer();
        StringBuffer ssb = new StringBuffer();

        for (char c : s.toCharArray()) {
            if( Character.isDigit(c) ){
                nsb.append(c);
                if(ssb.length()!=0){
                    stack.push(ssb.toString());
                    ssb = new StringBuffer();
                }
            }
            else if( c == '[' ) {
                stack.push( nsb.toString() );
                stack.push("[");
                nsb = new StringBuffer();
            }
            else if( c == ']' ) {
                String temp = stack.pop();
                while( !temp.equals("[")) {
                    ssb.insert(0,temp);
                    temp = stack.pop();
                }
                temp = stack.pop();
                StringBuffer subResult = new StringBuffer();
                for (int i = 0; i < Integer.parseInt(temp); i++) {
                    subResult.append(ssb.toString());
                }
                stack.push(subResult.toString());
                ssb = new StringBuffer();
            }
            else {
                ssb.append(c);
            }
        }
        if(ssb.length()!=0)
            stack.push(ssb.toString());
        StringBuffer result = new StringBuffer();
        while(!stack.isEmpty())
            result.insert(0,stack.pop());

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(decodeString("abc3[cd]xyz"));
    }

}
