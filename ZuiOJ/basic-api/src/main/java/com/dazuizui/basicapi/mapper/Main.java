package com.dazuizui.basicapi.mapper;

//请在此处输入代码
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] dp1 = new int[n];
        for (int i = 0; i < n; i++) {
            dp1[i] = scanner.nextInt();
        }
        int[] dp2 = new int[n];
        for (int i = 0; i < n; i++) {
            dp2[i] = scanner.nextInt();
        }
        System.out.println(temperatureTrend(dp1, dp2));
    }


    public static int temperatureTrend(int[] temperatureA, int[] temperatureB) {
        int n = temperatureA.length;
        int[] dp1 = new int[n];
        for (int i = 1; i < n; i++) {
            if (temperatureA[i] > temperatureA[i-1]){
                dp1[i] = 1;
            }else if (temperatureA[i] == temperatureA[i-1]){
                dp1[i] = 0;
            }else{
                dp1[i] = -1;
            }
        }

        int[] dp2 = new int[n];
        for (int i = 1; i < n; i++) {
            if (temperatureB[i] > temperatureB[i-1]){
                dp2[i] = 1;
            }else if (temperatureB[i] == temperatureB[i-1]){
                dp2[i] = 0;
            }else{
                dp2[i] = -1;
            }
        }

        int res = 0;
        for (int l = 1 , r = 1; l < n; ) {
            int tmp = 0;
            //相同
            while (r < n && dp1[r] == dp2[r]){
                r++;
                tmp++;
            }
            res = Math.max(res,tmp);

            while (r < n && dp1[r] != dp2[r]){
                r++;
            }

            l = r;
        }

        return res;
    }
}






