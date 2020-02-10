package com.i12368.util;

import java.io.*;

/**
 * @decription 用于读写开始时间
 */
public class TimeUtil {
    /**
     * 从日志中获取开始时间
     * @return
     */
    public static String readTime(){
        //File file = new File("stdj-handan/src/main/java/com/i12368/logs/records.txt");//不打包时使用
        //File file = new File("src/main/java/com/i12368/logs/records.txt");//test时使用
        File file = new File("records.txt");//打jar包时使用，放在jar包同级目录
        String absolutePath = file.getAbsolutePath();
        BufferedReader br = null;
        String line="";
        StringBuffer buffer = new StringBuffer();
        try {
            br=new BufferedReader(new FileReader(absolutePath));
            while((line=br.readLine())!=null){
                buffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer.toString();
    }

    /**
     * 把下一次开始时间写入日志
     * @param time
     */
    public static void writeTime(String time){
        //File file = new File("stdj-handan/src/main/java/com/i12368/logs/records.txt");//不打包时使用
        //File file = new File("src/main/java/com/i12368/logs/records.txt");//test时使用
        File file = new File("records.txt");//打jar包时使用，放在jar包同级目录
        String absolutePath = file.getAbsolutePath();
        BufferedOutputStream bos  = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(new File(absolutePath)));
            bos.write(time.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bos != null){
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
