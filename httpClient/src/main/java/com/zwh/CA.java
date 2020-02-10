package com.zwh;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CA {
    public static void main(String[] args){
        //创建客户端
        HttpClient httpClient = HttpClients.createDefault();
        //创建请求
        HttpPost httpPost = new HttpPost("http://131.16.42.135:8089/helper/interfaces/getModuleByCn6.action");
        //HttpGet httpGet = new HttpGet("http://131.100.0.194:8080/cas/js/usersigncert.js");
        //设置响应头


        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
        httpPost.setHeader("Accept","*/*");
        httpPost.setHeader("User-Agent","HebcaP11Assistor");
        httpPost.setHeader("Host","131.16.42.135:8089");
        httpPost.setHeader("Content-Length","22");
        httpPost.setHeader("Pragma","no-cache");
        //设置参数
        Scanner sc = new Scanner(System.in);
        String certcn = sc.next();
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("certcn",certcn));
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /*try {
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            HttpResponse response = httpClient.execute(httpGet);
            Document parse = Jsoup.parse(EntityUtils.toString(response.getEntity()));
            Document normalise = parse.normalise();
            System.out.println(normalise);
            System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
