package com.zwh;

import com.zwh.util.ReadImgUtil;

import java.io.File;

public class 验证码解析 {
    public static void main(String[] args){
        System.out.println(ReadImgUtil.readImg(new File[]{new File("C:\\Users\\30560\\Desktop\\image\\vcode.jpg")}));
    }
}
