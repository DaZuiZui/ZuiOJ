package com.dazuizui.basicapi.mapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.next();
        }
        int l = scanner.nextInt();
        int r = scanner.nextInt();
        System.out.println(vowelStrings(arr,l,r));
    }

    public static int vowelStrings(String[] words, int left, int right) {
        //缓存
        Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        //结果
        int result = 0;
        //循环
        for (int i = left; i <= right; i++) {
            //当前单词
            String word = words[i];
            //如果是
            if (set.contains(word.charAt(0)) && set.contains(word.charAt(word.length() - 1))) {
                //记录
                result++;
            }
        }
        //返回结果
        return result;
    }

}
