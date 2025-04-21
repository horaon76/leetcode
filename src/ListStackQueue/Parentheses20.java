package ListStackQueue;

import java.util.Stack;

public class Parentheses20 {

    public static boolean isValid(String s){
        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for(char ch: s.toCharArray()){
            if(ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            }else if(
                    (ch == ')' && stack.peek() == '(')
                    || (ch == ']' && stack.peek() == '[')
                    || (ch == '}' && stack.peek() == '{')
            ){
                stack.pop();
            }else{
                break;
            }
        }
        return stack.isEmpty() ? true : false;
    }
    public static void main(String[] args) {

        String input1 = "()[]{}";
        String input2 = "(]";
        String input3 = "{[()]}";
        String input4 = "";

        System.out.println(input4 + " => " + isValid(input4));
    }
}
