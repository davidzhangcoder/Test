package practise.leetcode.a6_栈和队列;

import java.util.Stack;

public class Leetcode_27_150_20210225_EvaluateReversePolishNotation {

    static Stack<Long> operand = new Stack<Long>();

    private static Long isNumber(String s) {
        try{
            return Long.parseLong(s);
        }
        catch (Exception e){
            return null;
        }
    }

    public static int evalRPN(String[] tokens) {
        if(tokens == null)
            return 0;
        for (String token : tokens) {
            Long o = null;
            if( (o = isNumber(token) ) != null )
                operand.push( o );
            else{
                Long o2 = operand.pop();
                Long o1 = operand.pop();
                if( token.equals("+")) {
                    operand.push(o1.longValue()+o2.longValue());
                } else if(token.equals("-")){
                    operand.push(o1.longValue()-o2.longValue());
                } else if(token.equals("*")){
                    operand.push(o1.longValue()*o2.longValue());
                } else if(token.equals("/")){
                    operand.push(o1.longValue()/o2.longValue());
                }

            }
        }
        return operand.pop().intValue();
    }

    public static void main(String[] args) {
//        System.out.println(13/5);

        String[] data = {"4", "13", "5", "/", "+"};
        System.out.println(evalRPN(data));
    }

}
