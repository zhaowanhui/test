package com.zwh;

import com.asprise.ocr.Ocr;
import com.zwh.util.ReadImg;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpClientPoolTest {
    private static int authcode;
    public static void main(String[] args){
        //创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        cm.setMaxTotal(100);
        //设置每个主机最大连接数
        cm.setDefaultMaxPerRoute(10);

        doGet(cm);
        //doPost(cm);
    }

    private static void doGet(PoolingHttpClientConnectionManager cm) {
        //不是每次都创建httpClient而是从连接池中获取
        CloseableHttpClient httpClient = HttpClients.custom().build();
       // ReadImg.readImg(httpClient,"http://131.100.0.194:8080/cas/captcha.jpg");
        HttpGet httpGet = new HttpGet("http://dz.bjjtgl.gov.cn/service/checkCode.do");
        //设置请求头
       // httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
       /* httpGet.setHeader("Connection","Keep-Alive");
        httpGet.setHeader("Accept-Encoding","gzip, deflate");
        httpGet.setHeader("Referer","http://131.100.0.7/");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");*/
        //httpGet.setHeader("Host","131.100.0.194:8080");
        CloseableHttpResponse response = null;
        String jsessionid = "";
        try {
            response = httpClient.execute(httpGet);
            if(response.getStatusLine().getStatusCode()==200){
                //5.获取响应头中的JSESSIONID
                Header[] headers = response.getAllHeaders();
                for (Header header : headers) {
                    System.out.println(header.getName()+"=="+header.getValue());

                    if(header.getName().equals("Set-Cookie"))
                        jsessionid = header.getValue();
                }
               HttpEntity entity = response.getEntity();
                entity.writeTo(new FileOutputStream("C:\\Users\\30560\\Desktop\\image\\vcode.jpg"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(response!=null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(jsessionid);
    }
    private static void doPost(PoolingHttpClientConnectionManager cm) {
        //不是每次都创建httpClient而是从连接池中获取
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpPost httpPost = new HttpPost("http://131.100.0.194:8080/cas/login?service=http%3A%2F%2F131.100.0.70%2Fsym%2F&tgt=null");
        //设置请求头
        httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setHeader("Connection","Keep-Alive");
        httpPost.setHeader("Accept-Encoding","gzip, deflate");
        httpPost.setHeader("Referer","http://131.100.0.7/");
        httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0");
        httpPost.setHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        httpPost.setHeader("Host","131.100.0.194:8080");



        //声明List集合，封装表单中的参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("username","wangzhaoyang"));
        nameValuePairs.add(new BasicNameValuePair("password","123"));
        nameValuePairs.add(new BasicNameValuePair("authcode",""));

        //创建表单提交Entity对象
        UrlEncodedFormEntity encodedFormEntity = null;
        try {
            encodedFormEntity = new UrlEncodedFormEntity(nameValuePairs,"utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //设置表单Entity到Post请求中
        httpPost.setEntity(encodedFormEntity);

        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpPost);
            //4.获取状态码
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.toString());
            //5.获取响应头
            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                System.out.println(header);
            }
            if(response.getStatusLine().getStatusCode()==200){
                String content = EntityUtils.toString(response.getEntity(), "utf8");

                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
