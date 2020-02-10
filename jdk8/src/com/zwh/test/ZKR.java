package com.zwh.test;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * @Description TODO 中科软上机测试
 * @Author zhaowanhui
 * @Date 2019/10/14
 * @ClassName ZKR
 */
public class ZKR {
    public static void main(String[] args) {
        Random random = new Random();

//        System.out.println((random.nextLong()+"").substring());
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        test2(n);
//        Random r = new Random();
//        long num = Math.abs(r.nextLong() % 100000000000000L);
//        String s = String.valueOf(num);
//
//        System.out.println(s);

    }

    /**
     * @Description TODO 输出两个集合中的交集
     * @Params null
     * @Return
     * @Author zhaowanhui
     * @Date 2019/10/14 13:50
     */
    @Test
    public void test1() {
        List<Integer> integers1 = Arrays.asList(12, 1, 9, 62, 11, 8, 7, 21, 15, 16);
        List<Integer> integers2 = Arrays.asList(19, 1, 10, 62, 13, 8, 42, 21, 15, 67);
        integers1.forEach(i -> {
            if (integers2.contains(i)) {
                System.out.print(i + "   ");
            }
        });

    }

    /**
     * @Description TODO 生成一个指定位数不能重复的数字随机数
     * @Params n 需要生成的位数，1-10位
     * @Return
     * @Author zhaowanhui
     * @Date 2019/10/14 13:59
     */
    public static void test2(int n) {
        Random random = new Random();
        ArrayList<Integer> ints = new ArrayList<>();
        while (true) {
            int i1 = random.nextInt(6);
            if (!ints.contains(i1)) {
                ints.add(i1);
            }
            if (ints.size() == n) break;
        }
        ints.forEach(System.out::print);
        System.out.println();
    }

    /**
     * @Description TODO 读取指定文件夹下的所有文件夹及文件，递归查询
     * （1）在控制台打印出所有目录名和文件名
     * （2）按目录结构打印所有目录名和文件名
     * （3）打印出所有文件中的内容
     * 以G://Go为例
     * 思路：查出文件夹下所有文件，如果存在文件夹继续向里查找文件
     * @Params null
     * @Return
     * @Author zhaowanhui
     * @Date 2019/10/14 14:00
     */
    @Test
    public void test3() {
        searchDirectorys("G://Go", 0);
    }

    /**
     * @Description TODO （1）按结构在控制台打印出所有目录名和文件名
     * @Params name 文件夹路径 n 输入0
     * @Return
     * @Author zhaowanhui
     * @Date 2019/10/14 15:09
     */
    public void searchDirectorys(String name, int n) {
        File file = new File(name);
        File[] files = file.listFiles();
        ArrayList<String> diractorys = new ArrayList<>();
        String str = n == 0 ? "" : "|-";
        for (int i = 0; i < n; i++) {
            str = "   " + str;
        }
        for (File file1 : files) {
            if (file1.isDirectory()) {
                diractorys.add(file1.getName());
                System.out.println(str + "文件夹：" + file1.getName());
                searchDirectorys(file1.getPath(), n + 1);
            } else {
                System.out.println(str + "*文件名：" + file1.getName());
                //readFile(file1.getPath());
            }
        }
        if (diractorys.isEmpty()) return;
    }

    /**
     * @Description TODO 读取文件中所有内容
     * @Params path 文件路径
     * @Return
     * @Author zhaowanhui
     * @Date 2019/10/14 15:13
     */
    public void readFile(String path) {
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        BufferedReader br = null;
        String line = "";
        StringBuffer buffer = new StringBuffer();
        try {
            br = new BufferedReader(new FileReader(absolutePath));
            while ((line = br.readLine()) != null) {
                buffer.append(line + "\r\n");
            }
            System.out.println(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
