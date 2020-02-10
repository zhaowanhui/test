package com.zwh.dataCapture;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.net.HttpURLConnection;
import java.net.URL;

public class HTMLPageParser {
    public static void main(String[] args) throws Exception {
        //目的网页URL地址
        getURLInfo("https://blog.csdn.net/sam372648886gz/article/details/79374094", "utf-8");
    }

    public static List<Product> getURLInfo(String urlInfo, String charset) throws Exception {
        //读取目的网页URL地址，获取网页源码
        URL url = new URL(urlInfo);
        HttpURLConnection httpUrl = (HttpURLConnection) url.openConnection();
        InputStream is = httpUrl.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("F:\\资料（全部拷贝）\\CoreJava\\API\\2.html"));
        StringBuilder sb = new StringBuilder();
        String line;
        byte[] bytes;
        while ((line = br.readLine()) != null) {
            //System.out.println(line);
            bytes = line.getBytes();
            outputStream.write(bytes,0,bytes.length);
            //这里是对链接进行处理
            line = line.replaceAll("</?a[^>]*>", "");
            //这里是对样式进行处理
            line = line.replaceAll("<(\\w+)[^>]*>", "<$1>");
            System.out.println(line);
            sb.append(line);
        }
        is.close();
        br.close();
        //获得网页源码
        return getDataStructure(sb.toString().trim());
    }

    static Pattern proInfo
            = Pattern.compile("<div>(.*?)</div>", Pattern.DOTALL);

    private static List<Product> getDataStructure(String str) {
        //运用正则表达式对获取的网页源码进行数据匹配，提取我们所要的数据，在以后的过程中，我们可以采用httpclient+jsoup,
        //现在暂时运用正则表达式对数据进行抽取提取
        String[] info = str.split("</li>");
        System.out.println(info.length);
        List<Product> list = new ArrayList<Product>();
        for (String s : info) {
            Matcher m = proInfo.matcher(s);
            Product p = null;
            if (m.find()) {
                p = new Product();
                //设置产品型号
                String[] ss = m.group(1).trim().replace(" ", "").split(">");
                System.out.println(ss.length);
                p.setProStyle(ss[0]);
               /* //设置产品数量
                p.setProAmount(m.group(2).trim().replace(" ", ""));
                //设置产品报价
                p.setProPrice(m.group(4).trim().replace(" ", ""));
                //设置产品供应商
                p.setProSupplier(m.group(5).trim().replace(" ", ""));*/
                list.add(p);
            }
        }
        //这里对集合里面不是我们要提取的数据进行移除
        list.remove(0);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("产品型号:" + list.get(i).getProStyle() + ",产品数量:" + list.get(i).getProAmount()
                    + ",产品报价:" + list.get(i).getProPrice() + ",产品供应商:" + list.get(i).getProSupplier());
        }
        return list;
    }
}

class Product {
    private String proStyle;//产品型号
    private String proAmount;//产品数量
    private String proPrice;//产品报价
    private String proSupplier;//产品供应商

    public String getProStyle() {
        return proStyle;
    }

    public void setProStyle(String proStyle) {
        this.proStyle = proStyle;
    }

    public String getProSupplier() {
        return proSupplier;
    }

    public void setProSupplier(String proSupplier) {
        this.proSupplier = proSupplier;
    }

    public String getProAmount() {
        return proAmount;
    }

    public void setProAmount(String proAmount) {
        this.proAmount = proAmount;
    }

    public String getProPrice() {
        return proPrice;
    }

    public void setProPrice(String proPrice) {
        this.proPrice = proPrice;
    }

    public Product() {

    }

    @Override
    public String toString() {
        return "Product [proAmount=" + proAmount + ", proPrice=" + proPrice
                + ", proStyle=" + proStyle + ", proSupplier=" + proSupplier
                + "]";
    }

}
