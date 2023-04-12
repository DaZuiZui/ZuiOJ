package com.dazuizui.basicapi.mapper;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main1 {
    static int count =0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(maximum69Number(scanner.nextInt()));

    }


        public static int maximum69Number (int num) {
            String numstr=String.valueOf(num);//将给定数字转换成字符串，便于操作
            char[] nums=numstr.toCharArray();//将字符串转换成字符数组
            for(int i=0;i<numstr.length();i++){
                //要想得到最大的，必然是先将高位6变成9，所以从前往后遍历，遇到6，直接翻转
                if(nums[i]=='6'){
                    nums[i]='9';
                    break;
                }
            }
            String res="";
            for(int i=0;i<numstr.length();i++){
                res+=nums[i];
            }
            return Integer.parseInt(res);
        }



}
