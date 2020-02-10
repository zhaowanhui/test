package com.zwh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class StoreData {
    public void store(Integer id, String content, String  title) throws Exception {
        String driverClassName = "com.mysql.jdbc.Driver";

        String url = "jdbc:mysql://localhost:3306/test?failOverReadOnly=false&loadBalanceBlacklistTimeout=5000&useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&serverTimezone=Asia/Shanghai&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true";

        String username = "root";

        String password = "root";
        // 加载驱动类
        Class.forName(driverClassName);
        Connection con = DriverManager.getConnection(url, username, password);
        // 创建sql语句模板
        String sql = "INSERT blog VALUES(?,?,?)";
        // 创建一个声明对象
        PreparedStatement pst =con.prepareStatement(sql);
        // 用循环将数据添加到sql模板中
        pst.setInt(1, id);
        pst.setString(2, content);
        //pst.set
        pst.setString(3, title);

        pst.addBatch();
        // 将sql语句发送到mysql上
        int[] res = pst.executeBatch();
        //System.out.println(res);
        pst.close();
    }
}
