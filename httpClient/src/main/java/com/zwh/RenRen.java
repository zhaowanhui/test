package com.zwh;

import java.util.ArrayList;
import java.util.List;


import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class RenRen {
    /*以下是模拟登陆程序*/
    /*输入你人人网的用户名及密码 ，这里输入*/
    private static String userName = "zhaowanhuiwudi@163.com";
    private static String password = "talent258";
    // 人人网登入请求url
    private static String renRenLoginURL = "http://www.renren.com/PLogin.do";
    // The HttpClient is used in one session
    private HttpResponse response;
    @SuppressWarnings("deprecation")
    //网络通信对象
    private DefaultHttpClient httpclient = new DefaultHttpClient();

    /*main方法*/
    public static void main(String[] args) {
        RenRen a = new RenRen();
        a.printText();
    }
    public void printText() {
        if (login()) { //执行登入操作
            String redirectLocation = getRedirectLocation();
            if (redirectLocation != null) {
                System.out.println(response.getStatusLine().toString());
                System.out.println(getText(redirectLocation));
            }else{//如果密码错误会返回登入页面
                System.out.println("抓取数据失败，请检查账号密码是否有效");
            }
        }
    }
    /*输入抓包的参数，即传递的参数*/
    private boolean login() {
        HttpPost httpost = new HttpPost(renRenLoginURL);
        // All the parameters post to the web site
        //建立一个NameValuePair数组，用于存储登入请求传递的参数，
        //参数怎么来的？抓包或者观察浏览器开发者模式下NetWork里点击登入url的名称找到传递的参数
        //这里传递5个参数
        List<NameValuePair> nvps  = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("domain", "renren.com"));
        nvps.add(new BasicNameValuePair("isplogin", "true"));
        nvps.add(new BasicNameValuePair("submit", "登录"));
        nvps.add(new BasicNameValuePair("username", userName));
        nvps.add(new BasicNameValuePair("password", password));


        try {
            //设置编码和参数
            httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
            //执行请求，得到响应结果
            response = httpclient.execute(httpost);
            System.out.println(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            httpost.abort();
        }
        return true;
    }

    private String getRedirectLocation() {
        /*获取响应的头 url*/
        Header locationHeader = response.getFirstHeader("Location");
        System.out.println(locationHeader);
        if (locationHeader == null) {
            return null;
        }
        return locationHeader.getValue();
    }
    /*获取html文本*/
    @SuppressWarnings("deprecation")
    private String getText(String redirectLocation) {
        HttpGet httpget = new HttpGet(redirectLocation);
        // Create a response handler
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = "";
        try {
            responseBody = httpclient.execute(httpget, responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
            responseBody = null;
        } finally {
            httpget.abort();
            httpclient.getConnectionManager().shutdown();
        }
        return responseBody;
    }
}
