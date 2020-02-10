package com.zwh.entity;

import java.util.ArrayList;
import java.util.List;

public class Student {
    int age;
    String name;
    double score;
    String className;
    boolean sex;
    public Student() {

    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Student(String string) {
        String[] split = string.split(",");
        this.age = Integer.parseInt(split[0]);
        this.name = split[1];
        this.score = Double.parseDouble(split[2]);
        this.className = split[3];
        this.sex = split[4].equals("男");
    }


    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", score=" + score +
                ", classNumber=" + className +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public String getClassNumber() {
        return className;
    }

    public void setClassNumber(String className) {
        this.className = className;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student(int age, String name, double score, String classNumber,boolean sex) {
        this.age = age;
        this.name = name;
        this.score = score;
        this.className = classNumber;
        this.sex  = sex;
    }
}
