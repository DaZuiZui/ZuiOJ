package com.dazuizui.basicapi.mapper;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
      //  System.out.println("Hello world");
        Scanner scanner = new Scanner(System.in);
        System.out.println(sumOfNumberAndReverse(scanner.nextInt()));
    }


    public static boolean sumOfNumberAndReverse(int num) {
        for (int i = 0; i <= num ; i++) {
            String s = new StringBuilder(i+"").reverse().toString();
            if ((i + Integer.valueOf(s)) == num){
                return true;
            }
        }

        return false;
    }

}
