import com.mongodb.BasicDBObject;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.regex.Pattern;

public class Demo1 {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient("192.168.31.155");
        MongoDatabase spitdb = mongoClient.getDatabase("spitdb");
        MongoCollection<Document> spit = spitdb.getCollection("spit");
        FindIterable<Document> documents = spit.find();
        System.out.println("==================查所有=======================");
        documents.forEach((Block<? super Document>) document -> {
            System.out.println("内容：" + document.getString("content"));
            System.out.println("用户ID:" + document.getString("userid"));
            System.out.println("浏览量：" + document.getInteger("visits"));
        });
        System.out.println("========================条件查询================================");
        BasicDBObject basicDBObject = new BasicDBObject("userid","1013");
        spit.find(basicDBObject).forEach((Block<? super Document>) document ->{
            System.out.println("内容：" + document.getString("content"));
            System.out.println("用户ID:" + document.getString("userid"));
            System.out.println("浏览量：" + document.getInteger("visits"));
        });
        System.out.println("========================浏览量大于1000================================");
        BasicDBObject bson = new BasicDBObject("visits", new BasicDBObject("$gt", 1000));
        spit.find(bson).forEach((Block<? super Document>) document -> {
            System.out.println("内容：" + document.getString("content"));
            System.out.println("用户ID:" + document.getString("userid"));
            System.out.println("浏览量：" + document.getInteger("visits"));
        });
        System.out.println("========================浏览量大于1000小于1100================================");
        HashMap<Object, Object> map = new HashMap<>();
        map.put("$gt",1000);
        map.put("$lt", 1100);
        HashMap<Object, Object> map1 = new HashMap<>();
        map1.put("visits",map);

        BasicDBObject visits = new BasicDBObject(map1);
        spit.find(visits).forEach((Block<? super Document>) document -> {
            System.out.println("内容：" + document.getString("content"));
            System.out.println("用户ID:" + document.getString("userid"));
            System.out.println("浏览量：" + document.getInteger("visits"));
        });
        System.out.println("========================浏览量大于1000小于1100模糊查询正则表达式================================");
        Pattern pattern =  Pattern.compile("^加班.*$", Pattern.CASE_INSENSITIVE);
        map1.put("content",pattern);
        BasicDBObject visits2 = new BasicDBObject(map1);
        spit.find(visits2).forEach((Block<? super Document>) document -> {
            System.out.println("内容：" + document.getString("content"));
            System.out.println("用户ID:" + document.getString("userid"));
            System.out.println("浏览量：" + document.getInteger("visits"));
        });
    }

}
