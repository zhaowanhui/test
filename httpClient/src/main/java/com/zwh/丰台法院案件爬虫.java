package com.zwh;

import com.zwh.entity.po.CaseOfCourt;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.Date;
import java.util.Iterator;

public class 丰台法院案件爬虫 {
    public static void main(String[] args) throws IOException {
        Connection conn = null;
        PreparedStatement pstm = null;
        Class<CaseOfCourt> caseOfCourtClass = CaseOfCourt.class;
        Field[] fields = caseOfCourtClass.getDeclaredFields();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true", "root", "root");
            conn.setAutoCommit(false);
            String sql = "insert into case_of_court values (";
            for (int i = 0; i < fields.length - 3; i++) {
                if (fields.length > 1) {
                    if (i == 0) {
                        sql += "?";
                    } else if (i < fields.length - 4) {
                        sql += ",?";
                    } else {
                        sql += ",?)";
                    }
                } else {
                    sql += "?)";
                }
            }
            pstm = conn.prepareStatement(sql);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //获取上下文HttpClientContext对象,创建响应时将其设置
        HttpClientContext httpClientContext = HttpClientContext.create();
        BasicCookieStore basicCookieStore = new BasicCookieStore(); //存储了所有的cookie
        httpClientContext.setCookieStore(basicCookieStore);  //设置basicCookieStore
        System.out.println("=================================================================");
        httpClientContext.getCookieStore().getCookies().forEach(a -> System.out.println(a));
        System.out.println("=================================================================");
        //设置连接配置,请求配置使用
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000) //设置连接超时
                .setSocketTimeout(5000)  //设置建立通信超时
                .setConnectionRequestTimeout(5000)  //设置请求连接超时
                .build();
        // 1.创建一个HttpClient对象
        HttpClient httpclient = HttpClients.createDefault();
        //for (int i = 1; i < 12356/10+1; i++) {
        String url = "http://testadmin.i12368.com/web/transfer/transferQueryList?pageNum=" + 3 +
                "&pageSize=10&caseFlowId=&caseNum=&printStatus=&applicantName=&defendantName=&caseCause=&cognizanceTime=&caseSubtypesName=&operationName=&operationDepartment=&courtId=&departmentId=&status=6";
        //String url="http://131.100.0.18:6060/ajcx/artery/form/dealParse.do?action=runItemLogic;JSESSIONID=tas_ajcx1vondmzzql8e11we6vdazg7dj.tas_ajcx";
        // 2.创建一个post请求对象
        HttpGet httpGet = new HttpGet(url); // 登录url
        //设置请求头
        httpGet.setHeader("Accept", "application/json, text/plain, */*");
        httpGet.setHeader("Connection", "Keep-Alive");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate");
        httpGet.setHeader("Authorization", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NjY1Mzk1MjMsInVzZXJuYW1lIjoidGVzdDEyMyJ9.kmz-INK4K1IAEg6qaHHr56zD-MtdKT5O8vCMBb0CWBU");
        httpGet.setConfig(requestConfig);
        HttpResponse response = httpclient.execute(httpGet, httpClientContext);

        //4.获取状态码
        StatusLine statusLine = response.getStatusLine();
        System.out.println(statusLine.toString());
        //5.获取响应头
        Header[] headers = response.getAllHeaders();
        for (Header header : headers) {
            System.out.println(header);
        }
        //5.得到响应内容
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "utf-8");
        System.out.println(result);
        //处理数据
        int index = result.indexOf("[");
        int i1 = result.indexOf("]");
        String substring = result.substring(index, i1 + 1);
        JSONArray jsonArray = JSONArray.fromObject(substring);
        Iterator iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            CaseOfCourt caseOfCourt = (CaseOfCourt) JSONObject.toBean((JSONObject) next, CaseOfCourt.class);
            System.out.println(caseOfCourt);
            try {
                int position = 1;
                for (Field declaredField : fields) {
                    declaredField.setAccessible(true);//设置可访问变量值
                    //System.out.println(pstm);
                    if (declaredField.getType() == Long.class) {
                        if (declaredField.get(caseOfCourt) == null) {
                            pstm.setNull(position, Types.BIGINT); //long类型数值不能为空，否则报空指针
                        } else {
                            pstm.setLong(position, (Long) declaredField.get(caseOfCourt));
                        }
                        System.out.println(declaredField.getName() + " = " + declaredField.get(caseOfCourt) + " -> " + position);
                    } else if (declaredField.getType() == String.class) {
                        pstm.setString(position, (String) declaredField.get(caseOfCourt));
                        System.out.println(declaredField.getName() + " = " + (String) declaredField.get(caseOfCourt) + " -> " + position);
                    } else if (declaredField.getType() == Date.class) {
                        if (declaredField.get(caseOfCourt) == null) {
                            pstm.setDate(position, null);
                        } else {
                            Date date = (Date) declaredField.get(caseOfCourt);
                            pstm.setDate(position, new java.sql.Date(date.getTime()));
                        }
                        System.out.println(declaredField.getName() + " = " + (Date) declaredField.get(caseOfCourt) + " -> " + position);
                    } else if (declaredField.getType() == Integer.class) {
                        if (declaredField.get(caseOfCourt) == null) {
                            pstm.setNull(position, Types.INTEGER); //INTEGER类型数值不能为空，否则报空指针
                        } else {
                            pstm.setInt(position, (Integer) declaredField.get(caseOfCourt));
                        }
                        System.out.println(declaredField.getName() + " = " + (Integer) declaredField.get(caseOfCourt) + " -> " + position);
                    } else {
                        if (declaredField.get(caseOfCourt) == null) {
                            pstm.setNull(position, Types.BOOLEAN); //BOOLEAN类型数值不能为空，否则报空指针
                        } else {
                            pstm.setBoolean(position, (Boolean) declaredField.get(caseOfCourt));
                        }
                        System.out.println(declaredField.getName() + " = " + (Boolean) declaredField.get(caseOfCourt) + " -> " + position);
                    }
                    position++;
                    if (position == 61) break;
                }
                pstm.addBatch();//添加到批处理
            } catch (SQLException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        try {
            pstm.executeBatch();//执行批处理
            conn.commit();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
