package com.zwh;

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.io.FileUtils;

public class Test {
    public static void main(String[] args) throws Exception {
        HttpClient client = new HttpClient();

        client.getState().setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials("admin", "123456"));

        PostMethod method = new PostMethod("http://localhost:8888/xxl-api-admin/toLogin");
        method.setRequestHeader("Content-Type", "application/soap+xml;charset=utf-8;action=\"generateLabel\"");

        File file = new File(Test.class.getResource("request2.xml").getPath());
        String content = FileUtils.readFileToString(file, "UTF-8");
        StringRequestEntity requestEntity = new StringRequestEntity(content, "text/xml", "UTF-8");
        method.setRequestEntity(requestEntity);

        int code = client.executeMethod(method);
        System.out.println(code);
        System.out.println(method.getResponseBodyAsString());
    }
}
