package com.dazuizui.basicapi.mapper;

//请在此处输入代码
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(isValid(scanner.next()));
    }


        public static boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            int n = s.length();
            for (int i = 0; i < n; i++) {
                char c = s.charAt(i);
                if (c == '{') stack.push('}');
                else if (c == '(') stack.push(')');
                else if (c == '[') stack.push(']');
                else if (stack.isEmpty() || stack.pop() != c){
                    return false;
                }
            }

            return stack.isEmpty();
        }
}






