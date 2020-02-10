package com.zwh.test;

import com.zwh.entity.Dog;
import com.zwh.entity.Student;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComparatorTest {
    public static void main(String[] args) {
        Comparator<String> comparator = Comparator.naturalOrder();
        List<String> list = Arrays.asList("zhaowanhui", "likeqiang", "mayun");
        list.sort(comparator); //字母表排序
        list.forEach(new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println(o);
            }
        });
        short a = -5;
        list.sort((s1, s2) -> s1.length() - s2.length()); //长度排序
        list.forEach(System.out::println);
        System.out.println(a>>>3);
        System.out.println(-9>>2);
        Runnable aNew = Dog::new;
    }
    @Test
    public void testInstans(){
        Instant now = Instant.now();
        System.out.println(now.getEpochSecond());
    }
    @Test
    /**
    * @Description TODO 打印出每班的平均分
    * @Params
    * @Return void
    * @Author zhaowanhui
    * @Date 2019/10/10 11:21
    */
    public void average() throws IOException {
        Stream<String> stringStream = Files.lines(Paths.get("students.txt"), Charset.defaultCharset());
        Stream<Student> studentStream = stringStream.map(Student::new);
        Map<String, Double> collect = studentStream.collect(Collectors.groupingBy(Student::getClassNumber, Collectors.averagingDouble(Student::getScore)));
        collect.forEach((k,v)-> System.out.println(k+v));
    }
    /**
    * @Description TODO 打印分数最高的男生的名字
    * @Params null
    * @Return
    * @Author zhaowanhui
    * @Date 2019/10/10 12:17
    */
    @Test
    public void maxScore() throws IOException {

        Stream<String> lines = Files.lines(Paths.get("students.txt"), Charset.defaultCharset());
        Stream<Student> studentStream = lines.map(Student::new);
        Student student = studentStream.filter(Student::isSex).max(Comparator.comparing(Student::getScore)).get();
        System.out.println(student.getName());
    }
    @Test
    /**
    * @Description TODO 打印每班分数最高的前三名
    * @Params
    * @Return void
    * @Author zhaowanhui
    * @Date 2019/10/10 12:46
    */
    public void test() throws IOException {
        Stream<Student> studentStream = Files.lines(Paths.get("students.txt"), Charset.defaultCharset()).map(Student::new);
        Map<String, List<Student>> collect = studentStream.collect(Collectors.groupingBy(Student::getClassNumber));
        collect.forEach((k,v)->
            v.stream().sorted(Comparator.comparingDouble(Student::getScore).reversed()).limit(3).forEach(System.out::println)
        );
    }
}
