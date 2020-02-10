package com.zwh;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;


public class PachngMain {
    public static void main(String[] args) throws Exception {
        //for (int j = 2000; j >0; j = j - 1) {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost("http://testadmin.i12368.com/web/user/login");
            List<NameValuePair> nvp = new ArrayList<>();
            nvp.add(new BasicNameValuePair("username", "test123"));
            nvp.add(new BasicNameValuePair("password", "12345678"));
            String sCharSet = "utf-8";
            post.setEntity(new UrlEncodedFormEntity(nvp, sCharSet));
            //post.setConfig(RequestConfig);
            HttpResponse response = client.execute(post);
      //System.out.println(response.getStatusLine().getStatusCode());
           /* if (response.getStatusLine().getStatusCode() !=200 ) {
                continue ;
            }*/
            HttpEntity entity = response.getEntity();
            System.out.println(entity.getContent().toString());
            String content = EntityUtils.toString(entity, "utf-8");
            System.out.println(content+6666);
            // 使用Jsoup解析网页
            Document doc = Jsoup.parse(content);
            /**
             * 注意cssQuery的格式  h2[class=comments-title]
             */
            Elements elements = doc.select("div[class=sku-title]");
            String title = elements.text();
            //System.out.println("title"+title);

            Elements element2 = doc.select("span");
            String article =element2.html();
           /* if (article.length()>1999){
                continue;
            }*/
            //System.out.println("article"+article);
            //new StoreData().store(j,article,title);
        //}
    }
}
