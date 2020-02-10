package com.freemark.demo.tes;

/**
 * ClassName: Str
 * Description:
 * date: 2019/9/16 14:17
 *
 * @author zhaowanhui
 * @since JDK 1.8
 */
public class Str {
    public static void main(String[] args){
        String s = new String("asdf");
        String s1 = new String("asdf");
        System.out.println(s.equals(s1));
        String s2 = "asdfg";
        System.out.println((s+"g").equals(s2));
    }

}
