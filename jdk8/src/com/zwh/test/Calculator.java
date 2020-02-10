package com.zwh.test;

import java.util.Scanner;

/**
 * @Description TODO
 * @Author zhaowanhui
 * @Date 2019/11/19 10:04
 * @ClassName Calculator
 */
public class Calculator {
    public static void main(String[] args) {
        System.out.println("输入利率：");
        Scanner scanner = new Scanner(System.in);
        double rate = scanner.nextDouble();
        System.out.println("输入期数：");
        int periods = scanner.nextInt();
        System.out.println("输入基数：");
        double base = scanner.nextDouble();
        double sum = base;
        for (int i = 0; i < periods; i++) {
            sum *= (1 + rate);
            sum += base;
        }
        double result = (double) Math.round(sum * 100) / 100;
        System.out.println("总数是:" + result);
    }
}
