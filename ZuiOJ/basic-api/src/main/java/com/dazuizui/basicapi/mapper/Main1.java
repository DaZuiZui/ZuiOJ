package com.dazuizui.basicapi.mapper;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scanner.nextInt();
        }
        int[] ints = applyOperations(arr);
        StringBuilder stringBuilder =new StringBuilder();
        for (int anInt : ints) {
            stringBuilder.append(anInt+" ");
        }
        System.out.println(stringBuilder.toString().trim());
    }

    public static int[] applyOperations(int[] nums) {
        for (int i = 0; i < nums.length -1; i++) {
            if (nums[i] == nums[i+1]){
                nums[i] = nums[i+1]*2;
                nums[i+1]=0;
                i++;
            }else{
                continue;
            }
        }

        int[] res = new int[nums.length];
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0){
                res[index++] = nums[i];
            }
        }

        return res;
    }
}
