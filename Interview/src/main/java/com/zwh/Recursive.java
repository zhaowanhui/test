package com.zwh;

import java.util.Scanner;

public class Recursive {
    static long factorial = 1l;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        factorial(n);
        System.out.println(factorial);
    }
    public static void factorial(int n){
        if(n==0) return;
        factorial *=n;
        factorial(n-1);
    }

}
