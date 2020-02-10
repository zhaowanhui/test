package com.zwh;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class JsoupTest {
    public static void main(String[] args) throws IOException {
//        //解析url地址
//        Document document = Jsoup.parse(new URL("http://www.itcast.cn"), 5000);
//        //使用标签选择器
//        String title = document.getElementsByTag("title").first().text();
//        System.out.println(document);
//        Elements s_name = document.getElementsByClass("s_name");
//        System.out.println(s_name);

        String url = "http://131.100.0.194:8080/cas/login?service=http%3A%2F%2F131.100.0.70%2Fsym%2F&tgt=null";
        /* 请求执行局页面 */
        HttpClientContext httpClientContext = HttpClientContext.create();
        BasicCookieStore basicCookieStore = new BasicCookieStore();
        httpClientContext.setCookieStore(basicCookieStore);
        //设置连接配置
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(30000)
                .setSocketTimeout(30000).setConnectionRequestTimeout(30000).build();
        //创建HttpClient实例
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Accept","text/html, application/xhtml+xml, image/jxr, */*");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Accept-Language","zh-CN");
        HttpResponse response = httpClient.execute(httpGet);
        String s = EntityUtils.toString(response.getEntity());
        Document document = Jsoup.parse(s);
        String loginTicket = document.select("input#loginTicket").first().attr("value");
        System.out.println(loginTicket);

        HttpClient httpClient4 = HttpClientBuilder.create().setDefaultCookieStore(basicCookieStore).build();
        RequestConfig requestConfig4 = RequestConfig.custom().setConnectTimeout(50000)
                .setSocketTimeout(50000).setConnectionRequestTimeout(50000).build();
        String loginUrl = "http://131.100.0.194:8080/cas/login?service=http%3A%2F%2F131.100.0.70%2Fsym%2F&tgt=null";
        HttpPost httpPost4 = new HttpPost(loginUrl);
        httpPost4.setHeader("Accept","text/html, application/xhtml+xml, image/jxr, */*");
        httpPost4.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko");
        httpPost4.setHeader("Accept-Encoding", "gzip, deflate");
        httpPost4.setHeader("Accept-Language", "zh-CN,zh;q=0.9");
        httpPost4.setHeader("DNT"," 1");
        httpPost4.setHeader("Host","131.100.0.194:8080");
        httpPost4.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost4.setHeader("Connection", "Keep-Alive");
        httpPost4.setHeader("Referer","http://131.100.0.194:8080/cas/login?service=http%3A%2F%2F131.100.0.70%2Fsym%2F&tgt=null");
        httpPost4.setConfig(requestConfig4);
        List<NameValuePair> nameValuePairs4 = new LinkedList<>();
        nameValuePairs4.add(new BasicNameValuePair("loginTicket", loginTicket));
        nameValuePairs4.add(new BasicNameValuePair("lt", loginTicket));
        nameValuePairs4.add(new BasicNameValuePair("signValue", "MEQCIGHW2QnBKNOKbnQEU5r28U7S1GsPEcEga6TnVY0ZrAUWAiA8HP69QwGu52yGi7//22tZ41884ougQYnzxoo7104VqQ=="));
        nameValuePairs4.add(new BasicNameValuePair("certBase64", "MIIDUDCCAvSgAwIBAgIIdB8A5gBBqEAwDAYIKoEcz1UBg3UFADBmMQswCQYDVQQGEwJDTjEOMAwGA1UECAwFaGViZWkxFTATBgNVBAcMDHNoaWppYXpodWFuZzEOMAwGA1UECgwFaGViY2ExDjAMBgNVBAsMBWhlYmNhMRAwDgYDVQQDDAdIQlNNMkNBMB4XDTE5MDcxOTAxMTIzNloXDTIwMDcxOTAxMTIzNlowgaoxEjAQBgNVBAgMCeays+WMl+ecgTEOMAwGA1UECgwFaGViY2ExDjAMBgNVBAsMBWhlYmNhMREwDwYDVQQLDAgxNjc4NjE4MDEYMBYGA1UEKgwP546L5pyd6ZizODkyMjgxMRswGQYDVQQBDBIxMzA0MzQxOTk2MDYwNDAzN3gxFjAUBggqgRyG70oBAwwIMTY3ODYxODAxEjAQBgNVBAMMCeeOi+acnemYszBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABIdzbYiqwZCIW96LbOqjGHmx3E4Ge/jgMDtNPFP1zpJjEP3DfHiLC/6QvuW0fqvbGYxaKv/Bj9jfUhJQd5jWuOqjggFDMIIBPzAMBgNVHRMEBTADAQEAMB0GA1UdJQQWMBQGCCsGAQUFBwMCBggrBgEFBQcDBDALBgNVHQ8EBAMCAMAwEQYJYIZIAYb4QgEBBAQDAgCAMCcGCCqBHIbvSgEEBBsxQDYwMjFTRjAxMzA0MzQxOTk2MDYwNDAzN3gwHwYDVR0jBBgwFoAUejC9peH2NcKtXQ9m1XVZuj6FlR0wPQYDVR0fBDYwNDAyoDCgLoYsaHR0cDovL2NybC5oZWJjYS5jb20vY3JsZG93bmxvYWQvSEJTTTJDQS5jcmwwSAYIKwYBBQUHAQEEPDA6MDgGCCsGAQUFBzAChixodHRwOi8vY3JsLmhlYmNhLmNvbS9jcmxkb3dubG9hZC9IQlNNMkNBLmNlcjAdBgNVHQ4EFgQU+kNTJLD+iYn9z2ubvxF1zh8x6IgwDAYIKoEcz1UBg3UFAANIADBFAiEAlRGBWL/o2g9vNljtlcempbC+V+IfVsoeeFOuCiuUemcCIE8xiA+II92oAoDkzDTlZ1t3SgieULUs82RVRPL5fK1T"));
        nameValuePairs4.add(new BasicNameValuePair("execution", "e1s1"));
        nameValuePairs4.add(new BasicNameValuePair("_eventId", "submit"));
        UrlEncodedFormEntity urlEncodedFormEntity4 = new UrlEncodedFormEntity(nameValuePairs4, "UTF-8");
        httpPost4.setEntity(urlEncodedFormEntity4);
        HttpResponse response4 = httpClient4.execute(httpPost4);
        System.out.println("列表请求状态:" + response4.getStatusLine().getStatusCode());
        String caseStr = EntityUtils.toString(response4.getEntity(), "UTF-8");
        System.out.println("case:" + caseStr);
        for (Header allHeader : response4.getAllHeaders()) {
            System.out.println(allHeader.getName()+":"+allHeader.getValue());
        }

    }

}
