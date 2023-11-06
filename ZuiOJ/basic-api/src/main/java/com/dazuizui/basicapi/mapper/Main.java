package com.dazuizui.basicapi.mapper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.next();
        StringBuilder stringBuilder = new StringBuilder(string);
        System.out.println(stringBuilder.reverse().toString());
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



