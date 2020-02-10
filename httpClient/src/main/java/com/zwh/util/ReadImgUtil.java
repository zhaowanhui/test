package com.zwh.util;

import com.asprise.ocr.Ocr;

import java.io.File;

public class ReadImgUtil {
    public static String readImg(File[] file){
        Ocr.setUp(); // one time setup
        Ocr ocr = new Ocr(); // create a new OCR engine
        ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
        String vcode = ocr.recognize(file,Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
        vcode=vcode.replace(",", "").replace("i", "1")
                .replace(" ", "").replace("'", "")
                .replace("o", "0").replace("O", "0")
                .replace("g", "6").replace("B", "8")
                .replace("s", "5").replace("z", "2")
                .replace("G","0").replace("\n","").substring(0,4);
        // ocr more images here ...
        System.out.println(vcode);
        ocr.stopEngine();
        return vcode;
    }
}
