package com.zwh.test;

import com.zwh.entity.Cat;
import com.zwh.entity.Dog;
import com.zwh.interfaces.Animal;

import java.util.function.BiFunction;

public class Interface {

    public static void main(String[] args) {
        Animal instance = Animal.getInstance(Cat.class);
        instance.method();
        Dog dog = new Dog();

    }
}
