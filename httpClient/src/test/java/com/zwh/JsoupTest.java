package com.zwh;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

public class JsoupTest {
    @Test
    public void testData() throws IOException {
        //解析url地址
        Document document = Jsoup.parse(new URL("http://www.itcast.cn"), 5000);
        System.out.println(document);
        Elements allElements = document.getAllElements();
        System.out.println(allElements);
        //根据id获取元素
        Element element = document.getElementById("qimo_badge");
        String str = "";
        //从元素中获取数据
        //1.从元素中获取id
        str = element.id();

        //2.从元素中获取className
        Set<String> strings = element.classNames();
        //3.从元素中获取属性值attr
        str = element.attr("id");
        //4.从元素中获取所有属性值attributes
        Attributes attributes = element.attributes();

        //5.从元素中获取文本内容text
       // str = element.text();
        //
        System.out.println(str);
    }
/**
 * 组合使用选择器
 *
 */
    @Test
    public void testSelector() throws IOException {
        //获取Docment
        Document document = Jsoup.parse(new File("C:\\Users\\30560\\Desktop\\test.html"),"UTF-8");
        //el#id:元素+ID，比如：li#city_bj
       //Elements element = document.select("li#city_bj");
        //el.class:元素+class，比如：h2.xqdz_tit
        //Elements element = document.select("h2.xqdz_tit");
        //el[attr]: 元素+属性名，比如：span[abc]
        //Elements element = document.select("span[abc]");
        //任意组合：比如：span[abc].s_name
        //ancestor child:查找某个元素下元素，比如：.schoolcon_right li查找"schoolcon_right"下的所有li
       // Elements element = document.select(".schoolcon_right li");
        //parent > child:查找某个父元素下的直接子元素，比如：
        //.city_con > ul >li 查找schoolcon_right第一级（直接子元素）的ul，再找所有ul下的第一级li
        //Elements element = document.select(".schoolcon_right > ul >li");
        //parent > *:查找某个父元素下所有直接子元素
        Elements element = document.select(".schoolcon_right > ul >*");
        System.out.println(element);
    }
}

















