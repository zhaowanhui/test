package com.freemark.demo.tes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class LocalDateTest {
    public static void main(String[] args){
        LocalDate now = LocalDate.now();
        LocalDateTime now1 = LocalDateTime.now();
        System.out.println(now1);
        System.out.println(now.plus(0, ChronoUnit.DAYS));
    }
}
