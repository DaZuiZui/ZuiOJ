package com.dazuizui.basicapi.mapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main1 {
    static int count =0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        dfs(n);
        System.out.println(count);
    }

    public static void dfs(int sum) {
        if (sum == 0){
            count++;
            return ;
        }
        if (sum < 0){
            return ;
        }

        for (int i = 1; i <= sum; i++) {
            dfs(sum - i);
        }
    }

}
