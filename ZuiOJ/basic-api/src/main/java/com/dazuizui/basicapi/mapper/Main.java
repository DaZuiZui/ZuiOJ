package com.dazuizui.basicapi.mapper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String next = scanner.next();
        StringBuilder stringBuilder =new StringBuilder(next);
        StringBuilder reverse = stringBuilder.reverse();
        System.out.println(reverse.toString().equals(next));
    }
}


/**
  #include <iostream>
  #include <cstdio>

  using namespace std;
  int main() {
      long a,b;
      cin >> a >> b;
      cout << a+b;
      return 0;
  }


 */



