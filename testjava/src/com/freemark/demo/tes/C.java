package com.freemark.demo.tes;


import javax.servlet.http.HttpServlet;
import java.util.HashMap;

public class C extends HttpServlet {
    public static void main(String[] args){
        HashMap map = new HashMap();
        map.put("a","q");
        int i =~8;
        System.out.println(i);
    }
}
interface A{
    static void test(){}
    default void test2(){}
}
