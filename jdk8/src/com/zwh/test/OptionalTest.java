package com.zwh.test;

import com.zwh.entity.Man;
import com.zwh.entity.Women;

import java.util.Optional;
import java.util.function.Consumer;

public class OptionalTest {
    public static void main(String[] args){
        Optional<String> o1 = Optional.ofNullable(null);
        Optional<String> o2 = Optional.ofNullable("abs");
        System.out.println(o1.orElse("s"));
        o2.ifPresent(System.out::println);
        o2.ifPresent(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("以消费");
            }
        });
        o2.ifPresent(s -> System.out.println(s.toUpperCase()));
        Man man = new Man();
        man.setWife(Optional.of(new Women()));
        man.getWife().ifPresent(Women::method);
    }
}

