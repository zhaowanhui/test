package com.zwh.test;

import java.util.Scanner;

/**
 * @Description TODO
 * @Author zhaowanhui
 * @Date 2019/11/24 22:15
 * @ClassName SUM
 */
public class SUM {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int flag = 1;
        while(flag == 1){
            flag = 1;
            System.out.println("输入个数");
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0;i<n;i++){
                System.out.println("第"+(i+1)+"个数是"+arr[i]);
            }
            int sum = 0;
            System.out.println("总数是:"+sum);
            while (flag ==1){
                System.out.println("更改第几个？");
                int index = scanner.nextInt();
                System.out.println("数值是？");
                int zhi = scanner.nextInt();
                sum = sum + zhi-arr[index-1];
                arr[index-1] = zhi;
                for (int i = 0;i<n;i++){
                    System.out.println("第"+(i+1)+"个数是"+arr[i]);
                }
                System.out.println("总数是:"+sum);
                System.out.println("是否更改其他？1-是，0-否");
                flag = scanner.nextInt();
            }

            System.out.println("是否更新个数？1-是，0-否");
            flag = scanner.nextInt();
        }
    }
}
