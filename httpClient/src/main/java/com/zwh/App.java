package com.zwh;

import java.lang.reflect.Field;

class Test {

    private int iVal;

    private String strVal;

    public Test(int iVal, String strVal) {
        this.iVal = iVal;
        this.strVal = strVal;
    }
}

public class App {

    public static void main(String[] args) {

        // 创建一个 Test 对象
        Test t = new Test(123, "Hello");

        // 获取对象 t 的 Class 
        Class<?> tt = t.getClass();

        try {

            // 获取 Test 类的 iVal 字段 Field
            Field field = tt.getDeclaredField("iVal");

            // 设置可访问
            field.setAccessible(true);

            // 获取 iVal 的值
            int val = field.getInt(t);

            System.out.println(val);


            Field strValField = tt.getDeclaredField("strVal");
            strValField.setAccessible(true);
            String strVal = (String)strValField.get(t);
            System.out.println(strVal);

        } catch (NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}