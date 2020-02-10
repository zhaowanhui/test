package com.zwh.interfaces;

import com.zwh.entity.Cat;
import com.zwh.entity.Dog;


public interface Animal {
    static Animal getInstance(Class clazz) {
        if (clazz == Cat.class) {
            return new Cat();
        } else if (clazz == Dog.class) {
            return new Dog();
        }
        return null;
    }

    default void method() {
        System.out.println("this is method");
    }
}
