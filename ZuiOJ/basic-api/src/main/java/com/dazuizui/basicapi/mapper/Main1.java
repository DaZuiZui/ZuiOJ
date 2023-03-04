package com.dazuizui.basicapi.mapper;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(isPalindrome(scanner.nextInt()));
    }

    public static boolean isPalindrome(int x) {
        String str = new String(String.valueOf(x));
        StringBuilder string = new StringBuilder();
        for (int i = str.length()-1; i >= 0; i--) {
            string = new StringBuilder(string + "" + str.charAt(i));
        }

        if (str.equals(string.toString())){
            return true;
        }

        return false;
    }
}
