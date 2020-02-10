package com.zwh.test;

import java.util.TreeMap;

/**
 * @Description TODO
 * @Author zhaowanhui
 * @Date 2019/11/28 16:02
 * @ClassName Weight
 */
public class Weight {
    public static void main(String[] args){

        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("node1",1);
        treeMap.put("node2",1);
        treeMap.put("node3",3);
        treeMap.put("node4",6);
        treeMap.put("node5",4);


        treeMap.forEach((k,v)-> System.out.println(v));
    }
}
