package com.dazuizui.basicapi.mapper;


import java.util.Scanner;

public class Main1 {
    static int count =0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        System.out.println(climbStairs(a));

    }

    public static int climbStairs(int n) {
        if(n == 1) return 1;

        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;

        for(int i = 2 ; i <= n ; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];

    }



}
