package com.zwh.jditem.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zwh.jditem.pojo.Item;
import com.zwh.jditem.service.ItemService;
import com.zwh.jditem.util.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Component
public class ItemTask {
    @Resource
    private HttpUtils httpUtils;
    @Resource
    private ItemService itemService;

    private static final ObjectMapper MAPPER = new ObjectMapper();
    //当下载任务完成后，间隔多长时间进行下一次任务。。
    @Scheduled(fixedDelay = 100*1000)
    public void itemTask() throws IOException {
        //声明需要解析的初始地址
        String url = "https://list.jd.com/list.html?cat=9987,653,655&sort=sort_rank_asc&trans=1&JL=6_0_0#J_main&page=";
        //按照页面对手机的搜索结果进行遍历解析
        for(int i=1;i<10;i++){
            String html = httpUtils.doGetHtml(url + i);
            //解析页面获取商品数据，并存储
            this.parse(html);
        }
        System.out.println("收据数据抓取完成");
    }
    //解析页面，获取商品数据并存储
    private void parse(String html) throws IOException {
        //解析html获取document对象
        Document document = Jsoup.parse(html);
        //获取spu
        Elements spuEles = document.select("div#plist > ul > li");
       for (Element spuEle : spuEles) {
            Long spu = Long.parseLong(spuEle.attr("data-spu"));
            //获取sku的信息
            Elements skuEles = spuEle.select("li.ps-item");
            for (Element skuEle : skuEles) {
                //获取sku
                long sku = Long.parseLong(skuEle.select("[data-sku]").attr("data-sku"));
                //根据sku查询数据，
                Item item =new Item();
                item.setSku(sku);
                List<Item> itemList = this.itemService.findAll(item);
                if(itemList.size()>0) {
                    // 如果商品存在就进行下一个循环
                    continue;
                }
                //设置商品的spu
                item.setSpu(spu);
                //获取商品的详情的url
                String itemUrl= "https://item.jd.com/"+sku+".html";
                item.setUrl(itemUrl);
                String picUrl = skuEle.select("img[data-sku]").first().attr("src");
                picUrl = picUrl.replace("/n9/","/n1");
                item.setPic(picUrl);
                //获取商品价格
                String priceJson = this.httpUtils.doGetHtml("");
                Double price = MAPPER.readTree(priceJson).get(0).get("p").asDouble();
                item.setPrice(price);
                //获取商品标题
                String itemInfo = this.httpUtils.doGetHtml(item.getUrl());
                String title = Jsoup.parse(itemInfo).select("div.sku-name").text();
                item.setTitle(title);
                item.setCreated(new Date());
                item.setUpdated(item.getCreated());
                this.itemService.save(item);
            }

        }
    }
}
