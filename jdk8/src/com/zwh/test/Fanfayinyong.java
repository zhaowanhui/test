package com.zwh.test;

import com.zwh.entity.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.ToIntFunction;

public class Fanfayinyong {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(19, "zwh", 5555.0));
        list.add(new Employee(21, "zxx", 1000.2));
        list.add(new Employee(22, "wkw", 3458.6));
        list.add(new Employee(22, "yzf", 1000.2));
        list.add(new Employee(34, "wlw", 10e2));
        list.add(new Employee(32, "dcw", 8000.0));
        list.forEach(new Consumer<Employee>() {
            @Override
            public void accept(Employee employee) {
                System.out.println(employee.getAge());
                ;
            }
        });
        list.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        list.sort((e1, e2) -> e1.getAge() - e2.getAge());
        list.forEach(System.out::println);
        System.out.println("------------------------------------");
        Comparator.comparingInt(new ToIntFunction<Employee>() {
            @Override
            public int applyAsInt(Employee value) {
                return value.getAge();
            }
        });
        Comparator<Employee> comparingDouble = Comparator.comparingDouble(s -> s.getSolary());
        Comparator<Employee> c1 = Comparator.comparingInt(Employee::getAge)
                .thenComparing(Employee::getSolary).reversed();
        list.sort(c1);
        list.forEach(System.out::println);

    }
}
