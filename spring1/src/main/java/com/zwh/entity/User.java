package com.zwh.entity;

public class User
{
    private String username = "zhangs";
    private int age = 5;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
