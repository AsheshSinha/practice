package com.prac.basics.stack;

import java.util.*;

public class StringBalanceProblem {
    public static void main(String[] argh) {
        Scanner sc = new Scanner(System.in);
        Stack<Character> stack = new Stack<>();
        boolean isBalanced = true;
        while (sc.hasNext()) {
            String input = sc.next();
            isBalanced = true;
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                switch (c) {
                    case '{':
                        stack.push(c);
                        break;
                    case '[':
                        stack.push(c);
                        break;
                    case '(':
                        stack.push(c);
                        break;
                    case '}':
                        isBalanced = isBalanced && !stack.isEmpty() && '{' == stack.pop();
                        break;
                    case ']':
                        isBalanced = isBalanced && !stack.isEmpty() && '[' == stack.pop();
                        break;
                    case ')':
                        isBalanced = isBalanced && !stack.isEmpty() && '(' == stack.pop();
                        break;
                    default:
                        break;
                }
            }
            System.out.println(stack.isEmpty() && isBalanced);
            while (!stack.isEmpty()){
                stack.pop();
            }
        }
        sc.close();

    }
}
