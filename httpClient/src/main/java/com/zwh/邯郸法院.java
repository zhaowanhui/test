package com.zwh;

import com.zwh.util.ReadImgUtil;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class 邯郸法院 {
    //设置请求头
    private static Header[] headers = {new BasicHeader("Accept", "text/html, application/xhtml+xml, image/jxr, */*")
            , new BasicHeader("Host", "131.100.0.194:8080")
            , new BasicHeader("Accept-Language", "zh-CN")
            , new BasicHeader("Accept-Encoding", "gzip, deflate")
            , new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
            , new BasicHeader("Connection", "Keep-Alive")
            , new BasicHeader("DNT", "1")
            , new BasicHeader("Content-Type", "application/x-www-form-urlencoded")};
    private static HttpContext httpContext = new HttpClientContext();

    public static void main(String[] args) {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        System.out.println("======================================================获取登陆页面参数====================================================================");
        String loginTicket = "";
        String signValue = "";  //CA用
        String certBase64 = ""; //CA用
        HttpResponse response = doGet(httpClient, "http://131.100.0.194:8080/cas/login?service=http%3A%2F%2F131.100.0.70%2Fsym%2F&tgt=null", headers);
        try {
            String string = EntityUtils.toString(response.getEntity());
            Document document = Jsoup.parse(string);
            loginTicket = document.select("input#loginTicket").first().attr("value");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("======================================================请求获取验证码====================================================================");
        String vcode = "";
        while (true) {
            headers[0] = new BasicHeader("Accept", "image/png, image/svg+xml, image/jxr, image/*;q=0.8, */*;q=0.5");
            HttpResponse responseCode = doGet(httpClient, "http://131.100.0.194:8080/cas/captcha.jpg", headers);
            File file = new File("C:\\Users\\30560\\Desktop\\image\\vcode.jpg");
            HttpEntity entity = responseCode.getEntity();
            try {
                entity.writeTo(new FileOutputStream(file));//输出到本地
            } catch (IOException e) {
                e.printStackTrace();
            }
            vcode = ReadImgUtil.readImg(new File[]{file});//解析验证码
            Pattern pattern = Pattern.compile("^[0-9]{4}$");
            if (pattern.matcher(vcode).matches()) {
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("======================================================开始登陆====================================================================");
        //声明List集合，封装表单中的参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        /*nameValuePairs.add(new BasicNameValuePair("username", "wangzhaoyang"));
        nameValuePairs.add(new BasicNameValuePair("password", "123"));
        nameValuePairs.add(new BasicNameValuePair("authcode", vcode));*/
        nameValuePairs.add(new BasicNameValuePair("loginTicket", loginTicket));
        nameValuePairs.add(new BasicNameValuePair("lt", loginTicket));
        nameValuePairs.add(new BasicNameValuePair("signValue", "MEQCINsAhdHDo0T/qiwhI0GcOomYyWk64Q81KgYcQ1jLPa+VAiDaKEcjibLAzkGR3UeZYtqEBpO9isDP7/ZdDCgD9ef0nw=="));
        nameValuePairs.add(new BasicNameValuePair("certBase64", "MIIDUDCCAvSgAwIBAgIIdB8A5gBBqEAwDAYIKoEcz1UBg3UFADBmMQswCQYDVQQGEwJDTjEOMAwGA1UECAwFaGViZWkxFTATBgNVBAcMDHNoaWppYXpodWFuZzEOMAwGA1UECgwFaGViY2ExDjAMBgNVBAsMBWhlYmNhMRAwDgYDVQQDDAdIQlNNMkNBMB4XDTE5MDcxOTAxMTIzNloXDTIwMDcxOTAxMTIzNlowgaoxEjAQBgNVBAgMCeays+WMl+ecgTEOMAwGA1UECgwFaGViY2ExDjAMBgNVBAsMBWhlYmNhMREwDwYDVQQLDAgxNjc4NjE4MDEYMBYGA1UEKgwP546L5pyd6ZizODkyMjgxMRswGQYDVQQBDBIxMzA0MzQxOTk2MDYwNDAzN3gxFjAUBggqgRyG70oBAwwIMTY3ODYxODAxEjAQBgNVBAMMCeeOi+acnemYszBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABIdzbYiqwZCIW96LbOqjGHmx3E4Ge/jgMDtNPFP1zpJjEP3DfHiLC/6QvuW0fqvbGYxaKv/Bj9jfUhJQd5jWuOqjggFDMIIBPzAMBgNVHRMEBTADAQEAMB0GA1UdJQQWMBQGCCsGAQUFBwMCBggrBgEFBQcDBDALBgNVHQ8EBAMCAMAwEQYJYIZIAYb4QgEBBAQDAgCAMCcGCCqBHIbvSgEEBBsxQDYwMjFTRjAxMzA0MzQxOTk2MDYwNDAzN3gwHwYDVR0jBBgwFoAUejC9peH2NcKtXQ9m1XVZuj6FlR0wPQYDVR0fBDYwNDAyoDCgLoYsaHR0cDovL2NybC5oZWJjYS5jb20vY3JsZG93bmxvYWQvSEJTTTJDQS5jcmwwSAYIKwYBBQUHAQEEPDA6MDgGCCsGAQUFBzAChixodHRwOi8vY3JsLmhlYmNhLmNvbS9jcmxkb3dubG9hZC9IQlNNMkNBLmNlcjAdBgNVHQ4EFgQU+kNTJLD+iYn9z2ubvxF1zh8x6IgwDAYIKoEcz1UBg3UFAANIADBFAiEAlRGBWL/o2g9vNljtlcempbC+V+IfVsoeeFOuCiuUemcCIE8xiA+II92oAoDkzDTlZ1t3SgieULUs82RVRPL5fK1T"));
        nameValuePairs.add(new BasicNameValuePair("execution", "e1s1"));
        nameValuePairs.add(new BasicNameValuePair("_eventId", "submit"));
        //nameValuePairs.add(new BasicNameValuePair("submit", "登录"));
        UrlEncodedFormEntity entity = null;
        try {
            entity = new UrlEncodedFormEntity(nameValuePairs, "utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }

        //更改请求头
        headers[0] = new BasicHeader("Accept", "text/html, application/xhtml+xml, image/jxr, */*");
        //发起登录请求
        HttpResponse response2 = doPost(httpClient, "http://131.100.0.194:8080/cas/login?service=http%3A%2F%2F131.100.0.70%2Fsym%2F&tgt=null", headers, entity);
        Header[] allHeaders = response2.getAllHeaders();
        for (Header allHeader : allHeaders) {
            //System.out.println(allHeader+"------------------------------------------------------------------------------------------------");
        }
        System.out.println("-----------------------------------");
        try {
            System.out.println(EntityUtils.toString(response2.getEntity()));
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("-----------------------------------");
        System.out.println("======================================================登陆完成，开始进入主页====================================================================");
        //设置主页请求头
        headers[0] = new BasicHeader("Accept", "text/html, application/xhtml+xml, image/jxr, */*");
        headers[1] = new BasicHeader("Host", "131.100.0.70");
        doGet(httpClient, "http://131.100.0.70/sym", headers);
        System.out.println("======================================================综合查询====================================================================");
        Header[] headersZonghe = {new BasicHeader("Accept", "text/html, application/xhtml+xml, image/jxr, */*")
                , new BasicHeader("Host", "131.100.0.18:6060")
                , new BasicHeader("Accept-Language", "zh-CN")
                , new BasicHeader("Accept-Encoding", "gzip, deflate")
                , new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
                , new BasicHeader("Connection", "Keep-Alive")
                , new BasicHeader("DNT", "1")};
        doGet(httpClient, "http://131.100.0.18:6060/ajcx/", headersZonghe);
        System.out.println("======================================================请求登陆验证====================================================================");
        Header[] headersYanzheng = {new BasicHeader("Accept", "text/html, application/xhtml+xml, image/jxr, */*")
                , new BasicHeader("Host", "131.100.0.194:8080")
                , new BasicHeader("Accept-Language", "zh-CN")
                , new BasicHeader("Accept-Encoding", "gzip, deflate")
                , new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
                , new BasicHeader("Connection", "Keep-Alive")
                , new BasicHeader("DNT", "1")};
        //doGet(httpClient, "http://131.100.0.194:8080/cas/login?service=http%3A%2F%2F131.100.0.18%3A6060%2Fajcx%2F&tgt=null", headersYanzheng);
        //doGet(httpClient, "http://131.100.0.18:6060/ajcx/", headersZonghe);
        System.out.println("======================================================开始查询案件====================================================================");
        //设置查询案件请求头
        Header[] headers2 = {new BasicHeader("Accept", "*/*")
                , new BasicHeader("Host", "131.100.0.18:6060")
                , new BasicHeader("Accept-Language", "zh-cn")
                , new BasicHeader("Accept-Encoding", "gzip, deflate")
                , new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
                , new BasicHeader("Connection", "Keep-Alive")
                , new BasicHeader("DNT", "1")
                , new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                , new BasicHeader("Referer", "http://131.100.0.18:6060/ajcx/artery/form/dealParse.do?action=parseForm&formid=3b952ac46802d7aaf0bc2ad1e8b6e454&runTimeType=insert&title=%25E7%25BB%25BC%25E5%2590%2588%25E6%259F%25A5%25E8%25AF%25A2%2520")
                , new BasicHeader("Pragma", "no-cache")
                , new BasicHeader("X-Requested-With", "XMLHttpRequest")};
        //全部参数字符串解析到参数列表中
        String caseParameter = "search.CAjlbText=&search.CAjlb=&search.NSpcxText=&search.NSpcx=&search.NAjlyText=&search.NAjly=&search.CJbfyText=%E9%82%AF%E9%83%B8%E5%B8%82%E9%82%AF%E5%B1%B1%E5%8C%BA%E4%BA%BA%E6%B0%91%E6%B3%95%E9%99%A2&search.CJbfy=154&search.NBhxjy=&search.NLaayText=&search.NLaay=&search.NLaayMsText=&search.NLaayMs=&search.NLaayQbText=&search.NLaayQb=&search.NLaayOldText=&search.NLaayOld=&search.NLaayOldQbText=&search.NLaayOldQb=&search.CYsah=&search.CYsfyText=&search.CYsfy=&search.NXfbglxText=&search.NXfbglx=&search.NCbsptText=&search.NCbspt=&search.DSarqBeginText=&search.DSarqBegin=&search.DSarqEndText=&search.DSarqEnd=&search.NLarText=&search.NLar=&search.DLarqBeginText=2019-08-01&search.DLarqBegin=2019-08-01&search.DLarqEndText=&search.DLarqEnd=&search.CAhInclude=&search.CAhNotInclude=&search.NNh=&search.CFyjc=&search.CSpzhText=&search.CSpzh=&search.NPhh=&search.NPhhTo=&search.NSfszmqText=&search.NSfszmq=&search.CDsr=&search.NDsrIsJqcx=&search.NDsrlxText=&search.NDsrlx=&search.CSfzjhm=&search.DPqrqBeginText=&search.DPqrqBegin=&search.DPqrqEndText=&search.DPqrqEnd=&search.NCbrText=&search.NCbr=&search.CHytcyText=&search.CHytcy=&search.CRmpsysx=&search.NSpzText=&search.NSpz=&search.CSjyText=&search.CSjy=&search.CKtftmcText=&search.CKtftmc=&search.DKtrqBeginText=&search.DKtrqBegin=&search.DKtrqEndText=&search.DKtrqEnd=&search.CKtftmcsx=&search.NFtytText=&search.NFtyt=&search.NJafsText=&search.NJafs=&search.NJaayText=&search.NJaay=&search.NJaayMsText=&search.NJaayMs=&search.NJaayQbText=&search.NJaayQb=&search.NJaayOldText=&search.NJaayOld=&search.NJaayOldQbText=&search.NJaayOldQb=&search.NSfjaText=&search.NSfja=&search.DJarqBeginText=&search.DJarqBegin=&search.DJarqEndText=&search.DJarqEnd=&search.DTqbjrqBeginText=&search.DTqbjrqBegin=&search.DTqbjrqEndText=&search.DTqbjrqEnd=&search.CWsfzText=&search.CWsfz=&search.NSfcsxText=&search.NSfcsx=&search.NDtxpText=&search.NDtxp=&search.NAjjzjdText=&search.NAjjzjd=&search.CAjsjText=&search.CAjsj=&search.CDysjText=&search.CDysj=&search.CPcztText=&search.CPczt=&search.NSfgdText=&search.NSfgd=&search.NSfycsxText=&search.NSfycsx=&search.NYcsxsyText=&search.NYcsxsy=&search.NSfssText=&search.NSfss=&search.NSsfwText=&search.NSsfw=&search.NKsfwText=&search.NKsfw=&search.DSxrqBeginText=&search.DSxrqBegin=&search.DSxrqEndText=&search.DSxrqEnd=&search.NDyaText=&search.NDya=&search.DLatfsajrqBeginText=&search.DLatfsajrqBegin=&search.DLatfsajrqEndText=&search.DLatfsajrqEnd=&search.NSsfText=&search.NSsf=&search.NHxajajlbText=&search.NHxajajlb=&search.NHxajspcxText=&search.NHxajspcx=&search.DHxajlarqBeginText=&search.DHxajlarqBegin=&search.DHxajlarqEndText=&search.DHxajlarqEnd=&search.DHxajjarqBeginText=&search.DHxajjarqBegin=&search.DHxajjarqEndText=&search.DHxajjarqEnd=&search.NHxajjafsText=&search.NHxajjafs=&search.NSxqkText=&search.NSxqk=&CSpzh=&isclick=true&start=0&limit=20&columnConf=%7B%22columnOper%22%3Afalse%2C%22columnStringCAH%22%3Afalse%2C%22columnCodeNAjlb%22%3Afalse%2C%22columnCodeNSPCX%22%3Afalse%2C%22columnStringCDSR%22%3Afalse%2C%22columnDateDSARQ%22%3Afalse%2C%22columnOrganLAR%22%3Afalse%2C%22columnDateDLARQ%22%3Afalse%2C%22columnStringLAAY%22%3Afalse%2C%22columnOrganCBSPT%22%3Afalse%2C%22columnOrganNCBR%22%3Afalse%2C%22columnDateDTqbjrq%22%3Afalse%2C%22columnDateDJARQ%22%3Afalse%2C%22columnStringCAY%22%3Afalse%2C%22columnStringJAFS%22%3Afalse%2C%22columnCodeSFSZMQ%22%3Afalse%2C%22columnCodeNPczt%22%3Afalse%2C%22columnOrgan6aace%22%3Afalse%2C%22columnStringCYsah%22%3Afalse%2C%22columnOrganYSFY%22%3Afalse%2C%22columnCodeYSJAFS%22%3Afalse%2C%22columnDateGDRQ%22%3Afalse%2C%22columnStringGDR%22%3Afalse%2C%22columnStringSXQK%22%3Afalse%2C%22columnCodeSFCSX%22%3Afalse%2C%22columnNumericCSXTS%22%3Afalse%2C%22columnNumericNJfzje%22%3Afalse%2C%22columnStringKtdd%22%3Afalse%7D&runTimeType=insert&isBetween=&formType=1&filterColumnId=columnCodeSFCSX&pagingBarId=&isBlxt=&isNeedSpecialControl=&filterOrganType=&rightGroupKey=&formid=3b952ac46802d7aaf0bc2ad1e8b6e454&filterColumn=tfyaj.N_CSX&sortColumn=&title=%25E7%25BB%25BC%25E5%2590%2588%25E6%259F%25A5%25E8%25AF%25A2%2520&isShowTime=&filterValueType=number&limitTime=0&filterCodeType=10100001&showOrder=false&organShowType=&filterListId=listAreaResult&method=parseListData&listAreaId=listAreaResult&itemid=listAreaResult&hiddenCols=";
        String[] split = caseParameter.split("&");
        List<NameValuePair> caseParameters= new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            int separateIndex = split[i].indexOf("="); //分割下标
            String name = split[i].substring(0,separateIndex);
            String value = split[i].substring(separateIndex + 1);
            caseParameters.add(new BasicNameValuePair(name,value));
        }
        try {
            entity = new UrlEncodedFormEntity(caseParameters, "utf-8");
            HttpPost httpPost = new HttpPost("http://131.100.0.18:6060/ajcx/artery/form/dealParse.do?action=runItemLogic&method=isRightValue");
            httpPost.setConfig(getRequestConfig());
            httpPost.setHeaders(headers2);
            httpPost.setEntity(entity);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost,httpContext);
            HttpUriRequest redirectUri = (HttpUriRequest) httpContext.getAttribute("http.request");
            System.out.println(redirectUri+"---------------------------------------------------------------------+");
            System.out.println("案件列表响应："+httpResponse.getStatusLine().getStatusCode());
            System.out.println(EntityUtils.toString(httpResponse.getEntity()));
            int statusCode = httpResponse.getStatusLine().getStatusCode();
            if(httpResponse.getFirstHeader("Location")!=null && statusCode==302||statusCode==301){
                String location = httpResponse.getFirstHeader("Location").getValue();
                System.out.println(location+"-----------------------------------------------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置响应头
        Header[] headers3 = {new BasicHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                , new BasicHeader("Host", "131.100.0.70")
                , new BasicHeader("Accept-Language", "zh-CN")
                , new BasicHeader("Accept-Encoding", "gzip, deflate")
                , new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; Trident/7.0; rv:11.0) like Gecko")
                , new BasicHeader("Connection", "Keep-Alive")
                , new BasicHeader("DNT", "1")
                , new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                , new BasicHeader("Referer", "http://131.100.0.70/ajxq/ajxq.do?action=ajxq&forward=ajxq&CBh=AC5D19B33197B66AC24DE3D881C148BB&NAjlb=1&NLx=4&CAh=null&userId=263190842&corpId=154&NJbfy=154&sysName=sym&cxm=null&showWswj=1&hjajFlag=undefined&hasWsxx=undefined&pz=undefined&ajxqCode=1")
                , new BasicHeader("Pragma", "no-cache")
                , new BasicHeader("X-Requested-With", "XMLHttpRequest")};
        //设置参数
        List<NameValuePair> nameValuePairs1 = new ArrayList<>();
        nameValuePairs1.add(new BasicNameValuePair("cBhAj", "AC5D19B33197B66AC24DE3D881C148BB"));
        nameValuePairs1.add(new BasicNameValuePair("nAjlb", "1"));
        nameValuePairs1.add(new BasicNameValuePair("viewType", "4"));
        nameValuePairs1.add(new BasicNameValuePair("userId", "263190842"));

        try {
            UrlEncodedFormEntity encodedFormEntity = new UrlEncodedFormEntity(nameValuePairs1, "UTF-8");
            HttpResponse response1 = doPost(httpClient, "http://131.100.0.70/ajxq/ajxq.do?action=queryMainTable", headers3, encodedFormEntity);

            HttpEntity entity1 = response1.getEntity();

            System.out.println(EntityUtils.toString(entity1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //设置get请求
    public static HttpResponse doGet(HttpClient httpClient, String url, Header[] headers) {
        HttpGet httpGet = new HttpGet(url); //创建get请求对象
        httpGet.setConfig(getRequestConfig());
        for (Header header : headers) {
            httpGet.setHeader(header); //设置响应头
            //System.out.println(url+"===========================================================================================================================");
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
        HttpResponse response=null;
        try {
            response= httpClient.execute((HttpUriRequest) httpRequest, httpContext);
            return response;
           /* HttpHost targetHost = (HttpHost) httpContext.getAttribute("http.target_host");//获取主机信息
            System.out.println(targetHost.toString()+"================================================================================================================================");
            HttpUriRequest redirectUri = (HttpUriRequest) httpContext.getAttribute("http.request");//获取当前url
            System.out.println(redirectUri);
            int statusCode = response.getStatusLine().getStatusCode();
            if (response.getFirstHeader("Location") != null // 如果存在值，说明有跳转
                    && statusCode == HttpStatus.SC_MOVED_TEMPORARILY  //302 页面暂时移动到另外一个新的地址
                    || statusCode == HttpStatus.SC_MOVED_PERMANENTLY //301 页面已经永久移到另外一个新地址
                    || statusCode == HttpStatus.SC_SEE_OTHER //303 客户端请求的地址必须通过另外的URL来访问
                    || statusCode == HttpStatus.SC_TEMPORARY_REDIRECT //307 同SC_MOVED_TEMPORARILY
            ) {
                String redirectURL = response.getFirstHeader("Location").getValue();
                System.out.println(redirectURL+"====================================================================================================================");
                Header[] allHeaders = response.getAllHeaders();
                for (Header allHeader : allHeaders) {
                    System.out.println(allHeader+"----------------------------------------------------------------------------------------------------------------------------");
                }
                return doGet(httpClient, redirectURL, allHeaders);
            }
            return response;*/
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
