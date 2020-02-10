package com.zwh.bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyFactory<T> {
    private static Properties p = new Properties();
    static {
        InputStream is = MyFactory.class.getResourceAsStream("/bean.properties");
        try {
            p.load(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static<T> T getInstance(String beanName){
        try {

            Class name = Class.forName(p.getProperty(beanName));
            T instance = (T) name.newInstance();
            return instance;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
