package com.zwh.fastsort;

public class Test {
    public static void soonSort(int[] a,int low,int hight){
        //开始第一步是从右找---第一回和找到比基准小的，交换，找不到就左移
        //接下来左侧查找---找到比基准大的，交换，找不到就右移
        //知道i》j结束循环
        //----
        //接下来分组进行查找，递归
        int i, j, index;
        if (low > hight) {
            return;
        }
        i = low;
        j = hight;
        index = a[i]; // 用子表的第一个记录做基准
        while(i<j){
            while(i<j && a[j]>=index)
                j--;
            if(i<j)a[i++] = a[j];
            while(i<j&&a[i] <= index)
                i++;
            if(i<j)a[j--] = a[i];
        }
        a[i] = index;
        soonSort(a, low, i - 1); // 对低子表进行递归排序
        soonSort(a, i + 1, hight); // 对高子表进行递归排序
        for (int i1 : a) {

            System.out.print(i1);
        }
    }
    public static void main(String[] args){
        int [] a = {2,5,18,14,5,6,3};
        soonSort(a,2,a.length-1);
    }
}
