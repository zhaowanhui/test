package com.zwh.entity;

import com.zwh.interfaces.Animal;

public class Dog implements Animal {
    int age;
    boolean sex;
    public Dog() {
        System.out.println("Ihis is a dog");
    }
}
