package com.dazuizui.basicapi.mapper;

import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
      //  System.out.println("Hello world");
        Scanner scanner = new Scanner(System.in);
        System.out.println(new Main4().evenOddBit(scanner.nextInt()));
    }
    public static String evenOddBit(int n) {
        String str = Integer.toBinaryString(n).toString();
        int l = str.length();
        int res1 = 0;
        int res2 = 0;
        // System.out.println(str);
        for (int i = 0; i < l; i++) {
            if (i% 2 == 0 && str.charAt(l-1-i) == '1'){
                res1++;
            }else if (i %2 != 0 && str.charAt(l-1-i) == '1'){
                res2++;
            }
        }

        return res1+" "+res2;
    }
}
