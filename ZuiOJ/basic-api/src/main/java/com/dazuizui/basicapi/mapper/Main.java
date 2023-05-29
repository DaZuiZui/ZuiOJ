package com.dazuizui.basicapi.mapper;

import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        System.out.println(numPairsDivisibleBy60(arr));
    }

    public static int numPairsDivisibleBy60(int[] time) {
        HashMap<Integer,Integer> map = new HashMap<>();
        int res =0 ;
        int n = time.length;
        // (time[i] + time[j]) % 60 == 0;
        // => time[i] % 60  + time[j] + % 60 == 0;
        for (int i = 0; i < n; i++) {
            int tmp = time[i] % 60;
            if (map.containsKey((60 - tmp)%60)) {
                res += map.get((60 - tmp)%60);
            }
            map.put(tmp,map.getOrDefault(tmp,0)+1);
        }

        return res;
    }
}






