package com.dazuizui.basicapi.mapper;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main1 {
    static int count =0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = scanner.nextInt();
        }
        System.out.println(findSubarrays(dp));
    }

    public static boolean findSubarrays(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int n = nums.length;

        for (int i = 0; i < n-1; i++) {
            if (!set.add(nums[i] + nums[i+1])) {
                return true;
            }
        }

        return false;
    }

}
