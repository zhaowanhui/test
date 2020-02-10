package com.freemark.demo.tes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayListTest {
    public static void main(String[] args){
        List<Object> objects = new ArrayList<>();
        objects.add(4);
        objects.add(5);
        objects.add(6);
        objects.add(7);
        Iterator<Object> iterator = objects.iterator();
        iterator.next();
        for (Object object : objects) {
            if (object.equals(4)) {
                objects.remove(object);
            }
        }
    }
}
