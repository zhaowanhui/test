//1、这种方式是先把验证码的图片下载到本地、并且根据网页解析获得token值
//2、手动在控制台输入验证码
//3、因为验证码图片已经下载下来，后面就可以使用图像文字识别
package com.zwh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class httpClient实现带验证码登陆 {
    private static HttpClient httpClient = new DefaultHttpClient();

    // 登陆入口
    public static void loginDouban() {
        String login_src = "https://accounts.douban.com/login";
        // 输入用户名及密码
        String form_email = "2692613726@qq.com";
        String form_password = "0315209X";
        String captcha_solution = "";
        // 获取验证码
        String captcha_id = getImgID();
        String login = "登录";
        // 输入验证码
        System.out.println("请输入验证码：");
        BufferedReader buff = new BufferedReader(new InputStreamReader(
                System.in));
        try {
            captcha_solution = buff.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 构造参数，即模拟需要输入的参数
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        list.add(new BasicNameValuePair("form_email", form_email));
        list.add(new BasicNameValuePair("form_password", form_password));
        list.add(new BasicNameValuePair("captcha-solution", captcha_solution));
        list.add(new BasicNameValuePair("captcha-id", captcha_id));
        list.add(new BasicNameValuePair("login", login));
        HttpPost httpPost = new HttpPost(login_src);
        try {
            // 向后台请求数据,登陆网站
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // 获取验证码图片“token”
    private static String getImgID() {
        String src = "https://www.douban.com/accounts/login";
        HttpGet httpGet = new HttpGet(src);
        String url = "";
        String token="";
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity, "utf-8");
            Document doc = Jsoup.parse(content);
            url = doc.select("div[class=item item-captcha]").select("div")
                    .select("img[id=captcha_image]").attr("src");
            System.out.println(url);
            int start = url.indexOf("=");
            int end = url.indexOf("&");
            token = url.substring(start+1,end);
            // 下载验证码并存储到本地
            downImg(url);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //https://www.douban.com/misc/captcha?id=f7enPStQocewglTaSeFdgfTu:en&size=s
        return token;
    }

    private static void downImg(String src) throws IOException {
        File fileDir = new File("C:\\Users\\30560\\Desktop\\image");
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        // 图片下载保存地址
        File file = new File("C:\\Users\\30560\\Desktop\\image\\yzm.png");
        if (file.exists()) {
            file.delete();
        }
        InputStream input = null;
        FileOutputStream out = null;
        HttpGet httpGet = new HttpGet(src);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            input = entity.getContent();
            int i = -1;
            byte[] byt = new byte[1024];
            out = new FileOutputStream(file);
            while ((i = input.read(byt)) != -1) {
                out.write(byt);
            }
            System.out.println("图片下载成功！");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.close();

    }

    //登陆后，便可以输入一个或者多个url，进行请求
    public static String gethtml(String redirectLocation) {
        HttpGet httpget = new HttpGet(redirectLocation);
        // Create a response handler  
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String responseBody = "";
        try {
            responseBody = httpClient.execute(httpget, responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
            responseBody = null;
        } finally {
            httpget.abort();
//            httpClient.getConnectionManager().shutdown();  
        }
        return responseBody;
    }
    //一个实例，只请求了一个url
    public static void main(String[] args) throws ClientProtocolException, IOException {
        loginDouban();
        String redir="https://www.douban.com/people/conanemily/contacts";
//      System.out.println(gethtml(redir));
        String cc=gethtml(redir);
        System.out.println(cc);

    }

}