package com.zwh.entity;

public class Employee {
    int age;
    String name;
    double solary;

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", solary=" + solary +
                '}';
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSolary() {
        return solary;
    }

    public void setSolary(double solary) {
        this.solary = solary;
    }

    public Employee(int age, String name, double solary) {
        this.age = age;
        this.name = name;
        this.solary = solary;
    }
}
