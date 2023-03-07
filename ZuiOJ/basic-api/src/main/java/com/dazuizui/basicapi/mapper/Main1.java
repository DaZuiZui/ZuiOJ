package com.dazuizui.basicapi.mapper;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        System.out.println(passThePillow(n, m));
    }


    public static int passThePillow(int n, int time) {
            boolean b = true;
            int res = 1;

            for (int i = 1; i <= time; i++) {
                if (res  == n){
                    b = false;
                }else if (res == 1){
                    b = true;
                }
                if (b){
                    res++;
                }else{
                    res--;
                }
            }
            return res;
        }

    }
