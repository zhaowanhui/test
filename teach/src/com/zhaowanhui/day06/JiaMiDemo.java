package com.zhaowanhui.day06;

/**
 * @Description TODO 某公司采用公用电话传递数据，数据是小于8位的整数，为了确保安全，在传递过程中需要加密，
 *                  加密规则如下：
 *                      首先将数据倒序，然后将每一个数字都加5，再用和除以10的余数代替该数字，最后将第一位和
 *                      最后一位数字交换。请任意给定一个小于8位的整数，然后，把加密后的结果在控制台打印出来。
 *                      附加：
 *                      用键盘录入和方法改进
 * @Author zhaowanhui
 * @Date 2019/10/24 20:43
 * @ClassName JiaMiDemo
 */
public class JiaMiDemo {
    public static void main(String[] args){
        int number = 123456;
        int[] arr = new int[8];
        //倒序
        int index = 0;
        while (number > 0){
            arr[index] = number % 10;
            index ++;
            number /=10;
        }

       //加5除以10取余
        for (int i = 0 ; i<index;i++){
            arr[i] = (arr[i] + 5) % 10;
        }
        //首末调换
        arr[0] = arr[0]^arr[index-1];
        arr[index-1] = arr[0]^arr[index-1];
        arr[0] = arr[0]^arr[index-1];
        //最后结果
        String result = "";
        for (int i = 0 ; i<index;i++){
            result +=arr[i];
        }
        System.out.println(result);
    }
}
