package com.dazuizui.basicapi.mapper;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long a = scanner.nextLong();
        long b = scanner.nextLong();
        System.out.println(a+b);
    }

    public static int alternateDigitSum(int n) {
        int sum = 0;
        boolean b = true;
        String str = n+"";
        for (int i = 0; i < str.length(); i++) {
            if(b){
                sum +=(str.charAt(i)-'0');
            }else{
                sum-=(str.charAt(i)-'0');
            }
            b= !b;

        }

        return sum;
    }
}
