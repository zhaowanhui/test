package com.zwh.nameAndpwd;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 后端模拟浏览器
 */
public class PachngMain {
    public static void main(String[] args) throws Exception {
        // 1.创建一个HttpClient对象
        //HttpClient httpclient = HttpClients.createDefault();
        //for (int i=1;i<12356 ;i+=10){
            // 2.创建一个post请求对象
            //HttpGet httpost = new HttpGet("http://testadmin.i12368.com/web/transfer/transferQueryList?pageNum=" + i + "&pageSize=10&caseFlowId=&caseNum=&printStatus=&applicantName=&defendantName=&caseCause=&cognizanceTime=&caseSubtypesName=&operationName=&operationDepartment=&courtId=&departmentId=&status=6"); // 登录url
            //设置请求头
           // httpost.setHeader("Accept", "application/json, text/plain, */*");
           // httpost.setHeader("Content-Type", "application/json;charset=utf-8");
            //httpost.setHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjQ4ODMxNTcsInVzZXJuYW1lIjoidGVzdDEyMyJ9.dpOaAV3ea7VMXs0A0yqQzf7XDAfEcSWTEgt9PaR92tY");
           // //post请求准备参数
       /* List<NameValuePair> nvp = new ArrayList<>();
        nvp.add(new BasicNameValuePair("username", "test123"));
        nvp.add(new BasicNameValuePair("password", "12345678"));
        String sCharSet = "utf-8";*/
            //装载post请求参数到HttpEntity对象，对参数进行格式化
            //HttpEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nvp,sCharSet);
            //设置post请求参数
            //httpost.setEntity(urlEncodedFormEntity);
            //3.发起请求，返回相应对象
            //HttpResponse response = httpclient.execute(httpost);

            //4.获取状态码
           /* StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.toString());*/

            //5.获取响应头
           /* Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                System.out.println(header);
            }*/

            //5.得到响应内容
           /* HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
            System.out.println(result);*/
            //4、获取返回值和cookie

            //String str = EntityUtils.toString(response.getEntity()); // post请求成功后的返回值
            //String cookie = response.getLastHeader("Set-Cookie").getValue(); // 获取cookie值

            //5、新建抓取数据的请求

            //HttpPost httpost2 = new HttpPost("http://testadmin.i12368.com/web/transfer/transferQueryList?pageNum=1&pageSize=10&caseFlowId=&caseNum=&printStatus=&applicantName=&defendantName=&caseCause=&cognizanceTime=&caseSubtypesName=&operationName=&operationDepartment=&courtId=&departmentId=&status=6"); // 数据接口url

            //6、设置请求头

            //httpost2.setHeader("Cookie", cookie); // 设置之前获取到的cookie
            //httpost2.setHeader("Content-Type", "application/json;charset=UTF-8");

            //7、通过抓包，得知参数是通过form data方式传递还是通过request payload方式，form data方式就是通过键值对 NameValuePair、UrlEncodedFormEntity的方式，如果是request payload方式的话，则需要字符流StringEntity的方式了。

            // request payload
        /*StringEntity entity2 = new StringEntity(jsonString); // 一般是json字符串
        httpost2.setEntity(entity2);*/

            //8、执行请求就可以获取到我们要的数据了

        /*HttpResponse response2 = httpclient.execute(httpost2);
        String str2 = EntityUtils.toString(response2.getEntity()); */// 这里就是我们要的数据了。

            //System.out.println(str2);
            //Thread.sleep(1000);
        //}
    }
}
