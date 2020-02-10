package com.zwh;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zwh.util.ReadImgUtil;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class 北京市公安局 {
    //设置请求头
    private static Header[] headers = {new BasicHeader("Accept", "image/webp,*/*")
            , new BasicHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
            , new BasicHeader("Accept-Encoding", "gzip, deflate")
            , new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:68.0) Gecko/20100101 Firefox/68.0")
            , new BasicHeader("Host", "dz.bjjtgl.gov.cn")
            , new BasicHeader("Connection", "Keep-Alive")};
    private static HttpContext httpContext = new BasicHttpContext();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(new PoolingHttpClientConnectionManager()).build();
        //请求获取验证码
        String vcode = "";
        while (true) {
            HttpResponse responseCode = doGet(httpClient, "http://dz.bjjtgl.gov.cn/service/checkCode.do", headers);
            File file = new File("C:\\Users\\30560\\Desktop\\image\\vcode.jpg");
            responseCode.getEntity().writeTo(new FileOutputStream(file));//输出到本地
            vcode = ReadImgUtil.readImg(new File[]{file});//解析验证码
            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
            if (pattern.matcher(vcode).matches()) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //=====================================登陆==========================================//
        //声明List集合，封装表单中的参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("username", "zhaowh"));
        nameValuePairs.add(new BasicNameValuePair("password", "123456"));
        nameValuePairs.add(new BasicNameValuePair("code", vcode));
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairs, "GBK");
        //更改请求头
        headers[0] = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        //发起登录请求
        HttpResponse doPost = doPost(httpClient, "http://dz.bjjtgl.gov.cn/service/login.action", headers, entity);
        System.out.println(EntityUtils.toString(doPost.getEntity()));
    }

    //设置get请求
    public static HttpResponse doGet(HttpClient httpClient, String url, Header[] headers) {
        HttpGet httpGet = new HttpGet(url); //创建get请求对象
        httpGet.setConfig(getRequestConfig());
        for (Header header : headers) {
            httpGet.setHeader(header); //设置响应头
        }
        return getHttpResponse(httpClient, httpGet);
    }

    //设置post请求
    public static HttpResponse doPost(HttpClient httpClient, String url, Header[] headers, UrlEncodedFormEntity entity) {
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeaders(headers);//设置请求头
        httpPost.setEntity(entity);//设置请求体
        httpPost.setConfig(getRequestConfig());
        return getHttpResponse(httpClient, httpPost);
    }
    //发送请求，获取响应
    public static HttpResponse getHttpResponse(HttpClient httpClient, HttpRequest httpRequest) {
        try {
            HttpResponse response = httpClient.execute((HttpUriRequest) httpRequest, httpContext);
            //HttpHost targetHost = (HttpHost) httpContext.getAttribute("http.target_host");//获取主机信息
            //HttpUriRequest redirectUri = (HttpUriRequest) httpContext.getAttribute("http.request");//获取当前url
            int statusCode = response.getStatusLine().getStatusCode();
            if (response.getFirstHeader("Location") != null // 如果存在值，说明有跳转
                    && statusCode == HttpStatus.SC_MOVED_TEMPORARILY  //302 页面暂时移动到另外一个新的地址
                    || statusCode == HttpStatus.SC_MOVED_PERMANENTLY //301 页面已经永久移到另外一个新地址
                    || statusCode == HttpStatus.SC_SEE_OTHER //303 客户端请求的地址必须通过另外的URL来访问
                    || statusCode == HttpStatus.SC_TEMPORARY_REDIRECT //307 同SC_MOVED_TEMPORARILY
            ) {
                //System.out.println(response.getFirstHeader("Location"));
                String redirectURL = response.getFirstHeader("Location").getValue();
                headers[0] = new BasicHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
                return doGet(httpClient, redirectURL, headers);
            }
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //连接设置
    public static RequestConfig getRequestConfig() {
        return RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();
    }
}
























