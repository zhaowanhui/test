package com.freemark.demo.tes;

import java.util.HashMap;

public class TestHashMAP extends HashMap{
    @Override
    public Object get(Object key) {
        int i=1;
        System.out.println("saf"+i++);
        return super.get(key);
    }

    public static void main(String[] args) {
        TestHashMAP testHashMAP = new TestHashMAP();
        for (int i=0;i<10;i++) {
            testHashMAP.put("a", "a"+i);
        }
        System.out.println(testHashMAP.get("a"));
    }
}
