package com.zwh.test;

import org.junit.Test;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description TODO
 * @Author zhaowanhui
 * @Date 2019/10/3 20:09
 * @ClassName CreateStream
 */
public class CreateStream {
    @Test
    /**
    * @Description files.line创建行数据流测式
    * @Params
    * @Return void
    * @Author zhaowanhui
    * @Date 2019/10/4 21:39
    */
    public void Filelinetest() throws IOException {
        Stream<String> stringStream = Files.lines(Paths.get("test.txt"), Charset.defaultCharset());
        stringStream.forEach(System.out::println);
    }
    @Test
    public void listSorted(){
        List<String> list = Arrays.asList("M1", "M2", "M5", "M4", "M12", "M8", "M10", "M9", "M1", "M2");
        Stream<String> stringStream = list.stream().map(s -> Integer.parseInt(s.substring(1, s.length()))).sorted().map(integer -> "M" + integer);
        List<String> collect = stringStream.collect(Collectors.toList());
    }
}
