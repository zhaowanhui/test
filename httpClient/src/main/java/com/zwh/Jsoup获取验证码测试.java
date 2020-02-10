package com.zwh;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Jsoup获取验证码测试 {
    public static void main(String[] args) throws IOException {
        String urlLogin = "http://newjwc.tyust.edu.cn/";
        //Connection connect = Jsoup.connect(urlLogin);
        // 伪造请求头
       // connect.header("Accept", "application/json, text/javascript, */*; q=0.01").header("Accept-Encoding",
       /*         "gzip, deflate");
        connect.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
        connect.header("Content-Length", "213").header("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");
        connect.header("Host", "newjwc.tyust.edu.cn").header("Referer", "http://newjwc.tyust.edu.cn/");
        connect.header("User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                .header("X-Requested-With", "XMLHttpRequest");

        // 请求url获取响应信息
        Connection.Response res = connect.ignoreContentType(true).method(Connection.Method.POST).execute();// 执行请求
        String body = res.body();
        //System.out.println(body);
        Document parse = Jsoup.parse(body);
        String select = parse.select("img#icode").attr("src");
        System.out.println("=================="+select);*/
        Connection connect1 = Jsoup.connect("http://newjwc.tyust.edu.cn/" + "CheckCode.aspx");
        // 伪造请求头
        connect1.header("Accept", "application/json, text/javascript, */*; q=0.01").header("Accept-Encoding",
                "gzip, deflate");
        connect1.header("Accept-Language", "zh-CN,zh;q=0.9").header("Connection", "keep-alive");
        connect1.header("Content-Length", "213").header("Content-Type",
                "application/x-www-form-urlencoded; charset=UTF-8");
        connect1.header("Host", "newjwc.tyust.edu.cn").header("Referer", "http://newjwc.tyust.edu.cn/");
        connect1.header("User-Agent",
                "Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36")
                .header("X-Requested-With", "XMLHttpRequest");
        Connection.Response res1 = connect1.ignoreContentType(true).method(Connection.Method.GET).execute();
        byte[] bytes = res1.bodyAsBytes();
        File file = new File("G:/code.jpg");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(bytes);
    }
}
