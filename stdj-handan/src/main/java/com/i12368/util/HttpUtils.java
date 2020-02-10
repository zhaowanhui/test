package com.i12368.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

//需要创建实例
@Component
public class HttpUtils {
    private static PoolingHttpClientConnectionManager cm;
    private static CloseableHttpClient httpClient;
    public HttpUtils() {
        this.cm = new PoolingHttpClientConnectionManager();
        //设置最大连接数
        this.cm.setDefaultMaxPerRoute(100);
        //设置主机最大连接数
        this.cm.setDefaultMaxPerRoute(10);
        httpClient=HttpClients.custom().setConnectionManager(cm).build();
    }

    /**
     * 根据get请求地址下载页面
     * @param url
     * @return 页面数据
     */
    public String doGetHtml(String url) {
        HttpContext httpContext = new HttpClientContext();
        //创建HttpGET请求对象，设置url地址
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(new BasicHeader("Accept-Language","zh-CN"));
        httpGet.setHeader(new BasicHeader("Accept-Encoding","gzip, deflate"));
        //设置请求信息
        httpGet.setConfig(this.getConfig());
        CloseableHttpResponse response = null;
        try {
            //使用httpClient发起请求，获取响应
            response = httpClient.execute(httpGet,httpContext);
            //解析响应，返回结果
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode==200){
                //判断响应体Entity是否为空，如果不为空就可以使用Entity
                if(response.getEntity()!=null){
                    String content = EntityUtils.toString(response.getEntity(), "utf8");
                    return content;
                }
            }else if(statusCode==301||statusCode==302){

            }
        } catch (IOException e) {
            ErrorUtil.outLog(e.getMessage()+url);
            e.printStackTrace();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            String content = this.doGetHtml(url);
            return content;
        }finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
    /**
     * 根据post请求地址下载页面
     * @param url
     * @return 页面数据
     */
    public String doPostHtml(String url,List<NameValuePair> nameValuePairs) {
        HttpContext httpContext = new HttpClientContext();
        //创建HttpPost请求对象，设置url地址
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(new BasicHeader("Accept-Language","zh-CN"));
        httpPost.setHeader(new BasicHeader("Accept-Encoding","gzip, deflate"));
        //设置请求信息
        httpPost.setConfig(this.getConfig());
        UrlEncodedFormEntity entity = null;
        CloseableHttpResponse response = null;
        try {
            entity = new UrlEncodedFormEntity(nameValuePairs, "utf-8");
            httpPost.setEntity(entity);
            //使用httpClient发起请求，获取响应
            response = httpClient.execute(httpPost,httpContext);
            //解析响应，返回结果
            if (response.getStatusLine().getStatusCode()==200){
                //判断响应体Entity是否为空，如果不为空就可以使用Entity
                if(response.getEntity()!=null){
                    String content = EntityUtils.toString(response.getEntity(), "utf8");
                    return content;
                }
            }
        } catch (IOException e) {
            ErrorUtil.outLog(e.getMessage()+url+nameValuePairs);
            e.printStackTrace();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            return this.doPostHtml(url, nameValuePairs);
        }finally {
            if (response!=null){
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "";
    }
    private RequestConfig getConfig() {
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(50000)
                .setConnectionRequestTimeout(50000)
                .setSocketTimeout(50000) // 数据传输的最长时间
                .build();
        return config;
    }

}
