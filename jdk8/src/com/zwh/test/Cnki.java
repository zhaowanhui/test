package com.zwh.test;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Description TODO
 * @Author zhaowanhui
 * @Date 2019/11/21 9:12
 * @ClassName Cnki
 */
public class Cnki {
    private static ArrayList<Integer> arr = new ArrayList<>(); //检查
    private static ArrayList<Integer> arrs = new ArrayList<>(); //存储

    public static void method(Integer a) throws InterruptedException {

        if (arr.contains(a)) {
            System.out.println("重复添加.........................................................");
            return;
        }
        arr.add(a);
        Thread.sleep(100);

        if (arrs.contains(a)) {
            System.out.println("库中已有");
            return;
        }
        arrs.add(a);
        arr.remove(arr.indexOf(a));

    }

    public static void main(String[] args) throws InterruptedException {

            new Thread(() -> {
                Random random = new Random();
                int i = random.nextInt(10);
                System.out.println(i);
                try {
                    method(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            new Thread(() -> {
                Random random = new Random();
                int i = random.nextInt(10);
                System.out.println(i);
                try {
                    method(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            Thread.sleep(1000);
            System.out.println(arrs);



    }
}
