package com.i12368.util;

import java.io.*;
import java.time.LocalDateTime;

public class ErrorUtil {

    public static void outLog(String str){
        LocalDateTime now = LocalDateTime.now();
        //File file = new File("stdj-handan/src/main/java/com/i12368/logs/error.log");//不打包时使用
        //File file = new File("src/main/java/com/i12368/logs/error.log");//test时使用
        File file = new File("error.log"); //打jar包时使用，放在jar包同级目录
        String absolutePath = file.getAbsolutePath();
        BufferedReader br = null;
        String line = "";
        StringBuffer buffer = new StringBuffer();
        try {
            br = new BufferedReader(new FileReader(absolutePath));
            while ((line = br.readLine()) != null) {
                buffer.append(line+"\n");
            }
            buffer.append(str+now+"\n");
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
        BufferedOutputStream bos  = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(new File(absolutePath)));
            bos.write(buffer.toString().getBytes());
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
