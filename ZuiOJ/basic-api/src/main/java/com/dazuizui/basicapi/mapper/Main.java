package com.dazuizui.basicapi.mapper;

//请在此处输入代码
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        int m = scanner.nextInt();
        System.out.println(check(arr, m));
    }

    public static int check(int[] arr, int m){
        int[] dp = new int[m+1];
        dp[0] = 1;
        for (int i = 0; i < arr.length ; i++) {
            for (int j = arr[i]; j <= m ; j++) {
                dp[j] += dp[j - arr[i]];
            }
        }

        return dp[m] == 0 ? -1 : dp[m];
    }
}






