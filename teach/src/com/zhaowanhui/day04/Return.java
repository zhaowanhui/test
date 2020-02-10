package com.zhaowanhui.day04;

/**
 * @Description TODO retrun 循环跳出练习
 * @Author zhaowanhui
 * @Date 2019/10/24 10:24
 * @ClassName Return
 */
public class Return {
    public static void main(String[] args){
           for (int i = 1;i<=10;i++){
               if (i%3==0){
                   return;
               }
               System.out.println("zhaowanhui"+i);
           }
        System.out.println("zhaowanhui");
    }
}
