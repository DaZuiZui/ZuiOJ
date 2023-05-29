package com.dazuizui.basicapi.mapper;


import java.util.Scanner;

public class Main1 {
    static int count =0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();

        System.out.println((a+b) % 24);

    }



}
