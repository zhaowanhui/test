package com.zwh;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

public class HttpGetParamsTest {
    public static void main(String[] args) throws URISyntaxException {
        //创建httpClient对象
        CloseableHttpClient httpClient = HttpClients.custom().build();
        //创建URIBuilder对象
        URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/search");
        //设置请求参数
        uriBuilder.setParameter("keys","Java");
        //创建get请求
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        //发起请求，获取响应
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
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
