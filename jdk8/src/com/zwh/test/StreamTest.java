package com.zwh.test;

import com.zwh.entity.Student;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "b", "c", "e", "d", "ed");
        List<String> collect = list.stream().sorted().filter(s -> s.length() == 1).collect(toList());
        collect.forEach(System.out::println);
        double a = 5.2;
        double b = 4.1;
        System.out.println(a*b);
    }


    /**
     * map映射，需求：打印年龄小于30的学生的名字
     */
    @Test
    public void testMap() {
        List<Student> list = new ArrayList<>();
       /* list.add(new Student(20, "zwh", 52.0, 2));
        list.add(new Student(22, "saf", 65.5, 2));
        list.add(new Student(30, "eff", 100.0, 2));
        list.add(new Student(34, "asd", 72.6, 1));
        list.add(new Student(26, "khg", 88.6, 2));*/
        list.stream().filter(s -> s.getAge() < 30).map(new Function<Student, String>() {
            @Override
            public String apply(Student student) {
                return student.getName();
            }
        }).collect(toList()).forEach(System.out::println);
        list.stream().filter(s -> s.getAge() < 30).map(s -> s.getName()).collect(toList()).forEach(System.out::println);
        list.stream().filter(s -> s.getAge() < 30).map(Student::getName).collect(toList()).forEach(System.out::println);
    }


    @Test
    /**
     * @Description flapMap测试，数组中元素有哪些字母组成
     * @Params null
     * @Return void
     * @Author zhaowanhui
     * @Date 2019/10/3 20:07
     */
    public void testflapMap() {
        List<String> list = Arrays.asList("ASDA", "ASDFEWRFF", "KJHY", "SHRFK");
        list.stream().flatMap(new Function<String, Stream<String>>() {
            @Override
            public Stream<String> apply(String s) {
                return Arrays.stream(s.split(""));
            }
        }).sorted().distinct().forEach(System.out::println);
        System.out.println("------------------------------------------------------");
        list.stream().map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct().collect(toList()).forEach(System.out::println);
        System.out.println("------------------------------------------------------");
        list.stream().flatMap(s -> Arrays.stream(s.split("")))
                .distinct()
                .sorted()
                .collect(toList())
                .forEach(System.out::println);
    }
    /**
     * @Description TODO paralel()测试
     * @Params
     * @Return void
     * @Author zhaowanhui
     * @Date 2019/10/7 18:08
     */
    @Test
    public void paralell() {
        LongStream parallel = LongStream.rangeClosed(1, 100);
        LongStream parallel1 = parallel.parallel();
        long sum = parallel1.sum();
        System.out.println(sum);
    }
}

class ArrMaxValue {

    public static int getMaxValue(Supplier<Integer> sup) {
        return sup.get();
    }

    public static void main(String[] args) {
        // 创建数组
        int[] arr = {100, 20, 50, 30, 99, 101, -50};
        int maxValue = getMaxValue(() -> {
            int max = arr[0];
            for (int i : arr) {
                if (i > max) {
                    max = i;
                }
            }
            return max;
        });

        System.out.println("数组中的最大值为:" + maxValue);
    }


}
