package com.zwh;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpPostParamsTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().build();
        //创建post请求对象,设置uri访问路径
        HttpPost httpPost = new HttpPost("http://yun.itheima.com/search");
        //声明List集合，封装表单中的参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("keys","Java"));
        //创建表单提交Entity对象
        UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(nameValuePairs,"utf8");
        //设置表单Entity到Post请求中
        httpPost.setEntity(encodedFormEntity);
        //发起请求，获取响应
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            //获取响应体
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode()==200){
                String content = EntityUtils.toString(entity);
                System.out.println(content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
