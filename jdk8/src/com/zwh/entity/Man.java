package com.zwh.entity;

import java.util.Optional;

public class Man {
    String name;
    Optional<Women> wife = Optional.empty();
    public Optional<Women> getWife(){
        return wife;
    }

    public void setWife(Optional<Women> wife) {
        this.wife = wife;
    }
}
