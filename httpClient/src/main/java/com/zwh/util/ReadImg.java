package com.zwh.util;

import com.asprise.ocr.Ocr;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ReadImg {
    public static void main(String[] args)  {
    //public static void readImg(HttpClient httpClient, String url){
        HttpClient httpClient = new HttpClient();
        int statusCode = 0;
        //获取验证码
        GetMethod getMethod = new GetMethod("http://131.100.0.194:8080/cas/captcha.jpg");
        //GetMethod getMethod = new GetMethod("http://dz.bjjtgl.gov.cn/service/checkCode.do");
        try {
            statusCode = httpClient.executeMethod(getMethod);
            Cookie[] cookies = httpClient.getState().getCookies();
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName()+"==="+cookie.getValue()+"captcha.jpg");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (statusCode != HttpStatus.SC_OK) {
            System.err.println("Method failed: " + getMethod.getStatusLine());
            return ;
        }
        String picName = "C:\\Users\\30560\\Desktop\\image\\";
        File filepic=new File(picName);
        if(!filepic.exists())
            filepic.mkdir();
        File filepicF=new File(picName+new Date().getTime() + ".jpg");
        OutputStream outStream =null;
        try {
            InputStream inputStream = getMethod.getResponseBodyAsStream();
            outStream = new FileOutputStream(filepicF);
            IOUtils.copy(inputStream, outStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(outStream!=null) {
                    outStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Ocr.setUp(); // one time setup
        Ocr ocr = new Ocr(); // create a new OCR engine
        ocr.startEngine("eng", Ocr.SPEED_FASTEST); // English
        String vcode = ocr.recognize(new File[] {filepicF},Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);
        System.out.println("Result: " + vcode);
        vcode=vcode.replace(",", "").replace("i", "1")
                .replace(" ", "").replace("'", "")
                .replace("o", "0").replace("O", "0")
                .replace("g", "6").replace("B", "8")
                .replace("s", "5").replace("z", "2")
                .replace("\n","").substring(0,4);
        System.out.println("图片文字为:" +vcode);
                // ocr more images here ...
        ocr.stopEngine();
        System.out.println("=====================================================================================================================");
        PostMethod postMethod = new PostMethod("http://131.100.0.194:8080/cas/login?service=http%3A%2F%2F131.100.0.70%2Fsym%2F&tgt=null");
        //设置请求头
        postMethod.addRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        postMethod.addRequestHeader("Connection","Keep-Alive");
        postMethod.addRequestHeader("Accept-Encoding","gzip, deflate");
        postMethod.addRequestHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0");
        postMethod.addRequestHeader("Accept-Language","zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2");
        postMethod.addRequestHeader("Referer","http://131.100.0.194:8080/cas/login?service=http%3A%2F%2F131.100.0.70%2Fsym%2F&tgt=null");
        //postMethod.addRequestHeader("Host","131.100.0.70");
        //填装表单中的参数
        Scanner sc = new Scanner(System.in);
        System.out.println("用户名：");
        String uname = sc.next();
        System.out.println("密码");
        String pwd = sc.next();
        postMethod.addParameter("username",uname);
        postMethod.addParameter("password",pwd);
        postMethod.addParameter("authcode",vcode);
        //发送请求获取响应
        try {
            if(httpClient.executeMethod(postMethod) == 200) {
                Cookie[] cookies = httpClient.getState().getCookies();
                for (Cookie cookie : cookies) {
                    System.out.println(cookie.getName()+"==="+cookie.getValue()+">>>>>>");
                }
                String body = postMethod.getResponseBodyAsString();
                System.out.println(body);
                System.out.println("获取成功");
            }
        } catch (IOException e) {
            System.out.println("获取失败");
            e.printStackTrace();
        }
    }
}
