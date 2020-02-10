package com.i12368.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.i12368.entity.*;
import com.i12368.service.*;
import com.i12368.util.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class STDJServiceImpl implements STDJService {
    @Autowired
    private IBaseCasecauseService iBaseCasecauseService;
    @Autowired
    private IPiCaseinfoService iPiCaseinfoService;
    @Autowired
    private IPiUserinfoPhoneService iPiUserinfoPhoneService;
    @Autowired
    private IPiUserinfoAddressService iPiUserinfoAddressService;
    @Autowired
    private IPiCaseUserRelService iPiCaseUserRelService;
    @Autowired
    private IPiUserinfosService iPiUserinfosService;

    private static Map<String, Integer> CCCodes = new HashMap<>();

    public void action() {
        if (CCCodes.isEmpty()) {
            List<BaseCasecause> casecauses = iBaseCasecauseService.list();
            for (BaseCasecause casecause : casecauses) {
                CCCodes.put(casecause.getCCName(), casecause.getCCCode());
            }
        }
        //获取法院基本信息
        LoginInfoUtil loginInfoUtil = new LoginInfoUtil();
        List<LoginInfo> loginInfos = loginInfoUtil.getLoginInfos();
        //System.out.println(loginInfos);
        //按时间查询案件列表
        String startDay = TimeUtil.readTime();

        LocalDateTime startTime0 = DateUtil.dateStr2Time(startDay + " 00:00:00"); //获取开始日期
        int size = LocalDateTime.now().getDayOfYear() - startTime0.getDayOfYear(); //循环次数
        for (int day = 0; day < size; day++) {
            for (LoginInfo loginInfo : loginInfos) {
                HttpUtils httpUtils = new HttpUtils();
                List<NameValuePair> nVP1 = new ArrayList<>();
                nVP1.add(new BasicNameValuePair("j_username", loginInfo.getUsername()));
                nVP1.add(new BasicNameValuePair("j_password", loginInfo.getPassword()));
                nVP1.add(new BasicNameValuePair("dlType", "1"));
                nVP1.add(new BasicNameValuePair("sysID", "caa9b35888b5a337719baa5408c8bd3a"));
                nVP1.add(new BasicNameValuePair("fy", loginInfo.getFy()));
                nVP1.add(new BasicNameValuePair("fydm", loginInfo.getFydm()));
                httpUtils.doPostHtml("http://131.16.200.208:8081/stdj-inner/summer/common/login.do?action=login", nVP1);

                List<NameValuePair> nVP2 = new ArrayList<>();
                nVP2.add(new BasicNameValuePair("formType", "1"));
                nVP2.add(new BasicNameValuePair("formId", "f38adc26a86c9762752c76eea0700511"));
                nVP2.add(new BasicNameValuePair("nZt", "0"));
                nVP2.add(new BasicNameValuePair("rtt", "insert"));
                nVP2.add(new BasicNameValuePair("start", "0"));
                nVP2.add(new BasicNameValuePair("limit", "500"));
                nVP2.add(new BasicNameValuePair("isPageSplit", "false"));
                nVP2.add(new BasicNameValuePair("rowsPerPage", "500"));
                nVP2.add(new BasicNameValuePair("method", "parseGridData"));
                nVP2.add(new BasicNameValuePair("itemid", "jqGridb0e0c"));
                nVP2.add(new BasicNameValuePair("jqCustomFilter6bc0b", startDay));
                String postHtml2 = httpUtils.doPostHtml("http://131.16.200.208:8081/stdj-inner/artery/parse.do?action=runItemLogic", nVP2);
                Result result = null;
                Document document1 = null;
                try {
                    result = JSONObject.parseObject(postHtml2, Result.class);
                    document1 = Jsoup.parse("<table>" + result.getTbody() + "</table>");
                } catch (Exception e) {
                    ErrorUtil.outLog(e.getMessage() + "--" + startDay + "--http://131.16.200.208:8081/stdj-inner/artery/parse.do?action=runItemLogic--" + loginInfo.getUsername());
                    e.printStackTrace();
                }
                //获取列表中全部列tr
                Elements tr = document1.select("table").select("tbody").select("tr");
                /**
                 * 创建集合Map，用来记录案号和案件id,key=案号，value=案件ID
                 */
                Map<String, Integer> caseIds = new HashMap<>(); // 查询数据库获得案件id key=案号 ，value=ajid
                Map<String, String> AhMapping = new HashMap<>(); // 查询数据库获得案件id key=调解号 ，value=登字号
                Map<String, String> AhMap = new HashMap<>();//缓存详情href key=href,value=AJah
                List<PiCaseinfo> piCaseinfos = new ArrayList<>();
                if (loginInfo.getFydm() == "104") {
                    for (int i = 0; i < tr.size(); i++) {
                        Elements td = tr.get(i).select("td");
                        //查寻案件是否重复
                        String ahd = td.get(1).text().replace("（","(").replace("）",")");
                        String aht = td.get(2).text().replace("（","(").replace("）",")");
                        if (iPiCaseinfoService.getOne(new QueryWrapper<>(new PiCaseinfo(ahd, loginInfo.getCbfyid()), "Ah")) != null
                                || iPiCaseinfoService.getOne(new QueryWrapper<>(new PiCaseinfo(aht, loginInfo.getCbfyid()), "Ah")) != null) {
                            continue;
                        }
                        //如果flog=1，则说明没有民登案号，循环一次
                        int flog = 1;
                        if (td.get(1).text().contains("登")) flog = 2;
                        AhMapping.put(aht, td.get(1).text().contains("登") ? ahd : "");
                        for (int j = 0; j < flog; j++) {
                            PiCaseinfo piCaseinfo = new PiCaseinfo();
                            //1.添加案号
                            if (j == 0) {
                                piCaseinfo.setAh(aht);
                            } else {
                                piCaseinfo.setAh(ahd);
                            }
                            //2.添加案件类别id
                            switch (td.get(5).text()) {
                                case "民事":
                                    piCaseinfo.setAjlb("2");
                                    break;
                                case "刑事":
                                    piCaseinfo.setAjlb("1");
                                    break;
                                case "行政":
                                    piCaseinfo.setAjlb("6");
                                    break;
                                case "赔偿":
                                    piCaseinfo.setAjlb("6");
                                    break;
                                case "执行":
                                    piCaseinfo.setAjlb("8");
                                    break;
                                default:
                                    piCaseinfo.setAjlb(null);
                                    break;
                            }
                            //3.添加承办法院id
                            piCaseinfo.setCbfyid(loginInfo.getCbfyid());
                            //4.添加承办人名称
                            piCaseinfo.setCbrmc(td.get(8).text());
                            //5.添加案由
                            piCaseinfo.setCCCode(CCCodes.get(td.get(3).text()));
                            //6.添加立案时间
                            LocalDateTime filingTime = DateUtil.dateStr2Time(td.get(14).text() + " 08:00:00");
                            piCaseinfo.setFilingTime(filingTime);
                            //7.添加截止时间
                            String trim = Pattern.compile("[^0-9]").matcher(td.get(11).text()).replaceAll("").trim();//解析出20
                            //System.out.println(trim + ".................");
                            if (!trim.equals("")) {
                                int parseInt = Integer.parseInt(trim);
                                LocalDateTime limitTime = filingTime.plus(parseInt, ChronoUnit.DAYS);
                                piCaseinfo.setLimitTime(limitTime);
                            }
                            //8.添加案件结果
                            piCaseinfo.setRemark(td.get(12).text());

                            //封装list集合
                            piCaseinfos.add(piCaseinfo);
                            //=====================================获取详情入库========================================//
                            //获取详情的href
                            Elements elements = td.get(17).select("a");
                            String href = "";
                            for (int k = 0; k < elements.size(); k++) {
                                if (elements.get(k).text().contains("详情")) {
                                    href = elements.get(k).attr("href");
                                }
                            }
                            if (AhMap.containsKey(href)) continue; //只添加一次调解案号
                            AhMap.put(href, piCaseinfo.getAh());
                        }

                        // AhMap.forEach((k,v)-> System.out.println(k+"===="+v));
                    }
                } else if (loginInfo.getFydm() == "154") {
                    for (int i = 0; i < tr.size(); i++) {
                        Elements td = tr.get(i).select("td");
                        //查寻案件是否重复
                        String ahd = td.get(2).text().replace("（","(").replace("）",")"); // deng
                        String aht = td.get(3).text().replace("（","(").replace("）",")");
                        if (iPiCaseinfoService.getOne(new QueryWrapper<>(new PiCaseinfo(aht, loginInfo.getCbfyid()), "Ah")) != null
                                || iPiCaseinfoService.getOne(new QueryWrapper<>(new PiCaseinfo(ahd, loginInfo.getCbfyid()), "Ah")) != null) {
                            //System.out.println("案件重复-----------------------");
                            continue;
                        }
                        //如果flog=1，则说明没有民登案号，循环一次
                        int flog = 1;
                        if (td.get(2).text().contains("登")) flog = 2;
                        AhMapping.put(aht, td.get(2).text().contains("登") ? ahd : "");
                        for (int j = 0; j < flog; j++) {
                            /*for (int a = 0; a < td.size(); a++) {
                                System.out.println(a + "==" + td.get(a).text() + "...............");
                            }*/
                            PiCaseinfo piCaseinfo = new PiCaseinfo();
                            //1.添加案号
                            if (j == 0) {
                                piCaseinfo.setAh(aht);
                            } else {
                                piCaseinfo.setAh(ahd);
                            }
                            //2.添加案件类别id
                            switch (td.get(6).text()) {
                                case "民事":
                                    piCaseinfo.setAjlb("2");
                                    break;
                                case "刑事":
                                    piCaseinfo.setAjlb("1");
                                    break;
                                case "行政":
                                    piCaseinfo.setAjlb("6");
                                    break;
                                case "赔偿":
                                    piCaseinfo.setAjlb("6");
                                    break;
                                case "执行":
                                    piCaseinfo.setAjlb("8");
                                    break;
                                default:
                                    piCaseinfo.setAjlb(null);
                                    break;
                            }
                            //3.添加承办法院id
                            piCaseinfo.setCbfyid(loginInfo.getCbfyid());
                            //4.添加承办人名称
                            piCaseinfo.setCbrmc(td.get(9).text());
                            //5.添加案由
                            piCaseinfo.setCCCode(CCCodes.get(td.get(4).text()));
                            //6.添加立案时间
                            LocalDateTime filingTime = DateUtil.dateStr2Time(td.get(15).text() + " 08:00:00");
                            piCaseinfo.setFilingTime(filingTime);
                            //7.添加截止时间
                            String trim = Pattern.compile("[^0-9]").matcher(td.get(12).text()).replaceAll("").trim();//解析出20
                            //System.out.println(trim + ".................");
                            if (!trim.equals("")) {
                                int parseInt = Integer.parseInt(trim);
                                LocalDateTime limitTime = filingTime.plus(parseInt, ChronoUnit.DAYS);
                                piCaseinfo.setLimitTime(limitTime);
                            }
                            //8.添加案件结果
                            piCaseinfo.setRemark(td.get(13).text());

                            //封装list集合
                            piCaseinfos.add(piCaseinfo);
                            //=====================================获取详情入库========================================//
                            //获取详情的href
                            Elements elements = td.get(18).select("a");
                            String href = "";
                            for (int k = 0; k < elements.size(); k++) {
                                if (elements.get(k).text().contains("详情")) {
                                    href = elements.get(k).attr("href");
                                }
                            }
                            if (AhMap.containsKey(href)) continue; //只添加一次调解案号,不添加登字号
                            AhMap.put(href, piCaseinfo.getAh());
                        }
                        //AhMap.forEach((k,v)-> System.out.println(k+"===="+v));
                    }
                }
                //Thread.sleep(100000);
                iPiCaseinfoService.saveBatch(piCaseinfos);
                //根据时间查询案件id，封装在map集合,添加联系人时候，根据案号获取CASE_ID
                List<PiCaseinfo> cbfyids = iPiCaseinfoService.list(new QueryWrapper<PiCaseinfo>().eq("FilingTime", startDay + " 08:00:00"));
                for (PiCaseinfo piCaseinfo2 : cbfyids) {
                    caseIds.put(piCaseinfo2.getAh(), piCaseinfo2.getAjid());
                }
                List<PiUserinfos> piUserinfosList = new ArrayList<>();
                List<PiUserinfoPhone> piUserinfoPhoneList = new ArrayList<>();
                List<PiCaseUserRel> piCaseUserRelList = new ArrayList<>();
                List<PiUserinfoAddress> piUserinfoAddressList = new ArrayList<>();
                Set<String> hrefs = AhMap.keySet();//访问详情页面
                for (String href : hrefs) {
                    String doGetHtml = "";
                    Document document = null; //详情
                    try {
                        doGetHtml = httpUtils.doGetHtml("http://131.16.200.208:8081" + href);
                        document = Jsoup.parse(doGetHtml);
                    } catch (Exception e) {
                        //System.out.println(loginInfo.getHost() + href + "------------------------------");
                        ErrorUtil.outLog(e.getMessage() + "--" + startDay + "--" + href + "--" + loginInfo.getUsername());
                        e.printStackTrace();
                    }
                    //获取当事人信息查看的DSRtable
                    Elements DSRtable = document.select("table#jqGridd05cf-table").select("tbody").select("table");
                    int mark = 0;
                    for (Element element1 : DSRtable) {
                        //获取<a>id
                        String id = element1.select("td").select("div").attr("id");
                        //分割获取发送请求JS对象值
                        String[] strings = doGetHtml.split("Artery.cfg_" + id);
                        String substring = strings[1].substring(strings[1].indexOf("=") + 1, strings[1].lastIndexOf(";"));
                        JSONObject jsonObject = JSONObject.parseObject(substring);
                        //获取请求参数
                        JSONObject linktoEvent = (JSONObject) jsonObject.get("linktoEvent");
                        String formId = (String) linktoEvent.get("formId"); //获取formId
                        String formType = (String) linktoEvent.get("formType"); //获取formType
                        String rtt = (String) linktoEvent.get("runTimeType"); //获取rtt
                        JSONObject params = (JSONObject) linktoEvent.get("params");
                        String dsrId = (String) params.get("dsrId");//
                        String tjxxBh = (String) params.get("tjxxBh");//获取tjxxBh
                        String dsrLx = (String) params.get("dsrLx");//获取dsrLx
                        String dsrSsdw = (String) params.get("dsrSsdw");//获取dsrLx
                        String operateType = (String) params.get("operateType");//获取operateType
                        //========================================发送请求获取当事人详情页=============================================//
                        String getDSRHtml = httpUtils.doGetHtml("http://131.16.200.208:8081/stdj-inner/artery/parse.do?action=parse"+ "&formId=" +
                                formId + "&formType=" + formType + "&dsrId=" + dsrId + "&dsrLx=" + dsrLx + "&tjxxBh=" + tjxxBh + "&dsrSsdw=" +
                                dsrSsdw + "&operateType=" + operateType + "&rtt=" + rtt);
                        //排除错误页面,如果是错误页面，就从上个页面获取当事人电话
                        if (Jsoup.parse(getDSRHtml).select("title").text().contains("错误页面")) {
                            for (int j = 0; j < ((AhMapping.get(AhMap.get(href)).equals("")) ? 1 : 2); j++) {
                                try {
                                    PiUserinfos piUserinfos = new PiUserinfos();
                                    //1.添加案件id,第一次添加调解案号，第二次添加登字号
                                    if (j == 0) {
                                        piUserinfos.setAjid(caseIds.get(AhMap.get(href)));
                                        //重复案件当人不添加
                                        if (iPiUserinfosService.getOne(new QueryWrapper<>(piUserinfos, "ajid")) != null)
                                            continue;
                                    } else {
                                        //System.out.println("..............................................................................");
                                        piUserinfos.setAjid(caseIds.get(AhMapping.get(AhMap.get(href))));
                                        //重复案件当人不添加
                                        if (iPiUserinfosService.getOne(new QueryWrapper<>(piUserinfos, "ajid")) != null)
                                            break;
                                    }
                                    Elements trs = document.select("table#jqGridd05cf-table").select("tbody > tr");
                                    //System.out.println(trs.size() + "'''''''''''''''''''''''''");
                                    /*Elements element = trs.get(mark).select("td");
                                    for (Element td : element) {
                                        System.out.println(td.text());
                                    }*/
                                    //System.out.println(trs.html());
                                    //2.添加当事人类型id (实际当事人地位)
                                    if (trs.get(mark).select("td").get(3).text().contains("申") || trs.get(mark).select("td").get(3).text().contains("原")) {
                                        piUserinfos.setUserType(1);
                                    } else if (trs.get(mark).select("td").get(3).text().contains("被")) {
                                        piUserinfos.setUserType(2);
                                    } else {
                                        piUserinfos.setUserType(3);
                                    }
                                    //3.添加当事人姓名
                                    piUserinfos.setUserName(trs.get(mark).select("td").get(7).text());
                                    //4.添加单位名称
                                    piUserinfos.setDwmc(trs.get(mark).select("td").get(6).text());
                                    //System.out.println(piUserinfos.getUserName() + "...................................");
                                    //5.添加证件号码
                                    piUserinfos.setCardNum(trs.get(mark).select("td").get(8).text());
                                    //System.out.println(piUserinfos.getCardNum() + "...................................");
                                    //7.添加手机号码
                                    piUserinfos.setTelPhone(trs.get(mark).select("td").get(9).text());
                                    //System.out.println(piUserinfos.getTelPhone() + "...................................");
                                    //System.out.println(AhMap.get(href));
                                    //System.out.println(href);
                                    //8.添加flag
                                    piUserinfos.setFlag(1);
                                    //9.来源于速调对接
                                    piUserinfos.setInfosSource(3);
                                    //10.添加标记，用来筛选uid
                                    piUserinfos.setRemark(startDay + loginInfo.getUsername());
                                    //添加到list集合
                                    piUserinfosList.add(piUserinfos);
                                } catch (Exception e) {
                                    ErrorUtil.outLog(e.getMessage() + "--" + startDay + "--http://131.16.200.208:8081/stdj-inner/artery/parse.do?action=parse--" + loginInfo.getUsername());
                                    e.printStackTrace();
                                    //Thread.sleep(10000);
                                }
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            Elements DSRTr = Jsoup.parse(getDSRHtml).select("table#jqFormArea2fa98_table").select("tr");
                    /*for(int j=0;j<DSRTr.size();j++){
                        System.out.println(j+"==="+DSRTr.get(j).text()+".........................................................");
                    }*/
                            //通过href获得调解案号，再根据调解案号获得登字号,如果登字号是空，不参加循环
                            for (int j = 0; j < ((AhMapping.get(AhMap.get(href)).equals("")) ? 1 : 2); j++) {
                                try {
                                    PiUserinfos piUserinfos = new PiUserinfos();
                                    //1.添加案件id,第一次添加调解案号，第二次添加登字号
                                    if (j == 0) {
                                        piUserinfos.setAjid(caseIds.get(AhMap.get(href)));
                                        //重复案件当事人不添加
                                        if (iPiUserinfosService.getOne(new QueryWrapper<>(piUserinfos, "ajid")) != null)
                                            continue;
                                    } else {
                                        //System.out.println("..............................................................................");
                                        piUserinfos.setAjid(caseIds.get(AhMapping.get(AhMap.get(href))));
                                        //重复案件当人不添加
                                        if (iPiUserinfosService.getOne(new QueryWrapper<>(piUserinfos, "ajid")) != null)
                                            break;
                                    }
                                    if (dsrLx.equals("1")) {
                                        //5.添加证件号码
                                        piUserinfos.setCardNum(DSRTr.get(5).select("td").get(1).select("span").text().replace("* ", ""));
                                        //2.添加当事人类型id
                                        String userType = DSRTr.get(0).select("td").select("span").get(1).text();
                                        if (userType.contains("申") || userType.contains("原")) {
                                            piUserinfos.setUserType(1);
                                        } else if (userType.contains("被")) {
                                            piUserinfos.setUserType(2);
                                        } else {
                                            piUserinfos.setUserType(3);
                                        }
                                        //3.添加当事人姓名
                                        piUserinfos.setUserName(DSRTr.get(2).select("td").get(0).select("span").text().replace("* ", ""));
                                        //4.添加证件类型
                                        String cardType = DSRTr.get(5).select("td").get(0).select("span").text();
                                        if (cardType.contains("身份证")){
                                            piUserinfos.setCardType(1);
                                        }else if (cardType.contains("军官证")){
                                            piUserinfos.setCardType(2);
                                        }else if (cardType.contains("护照")){
                                            piUserinfos.setCardType(3);
                                        }else {
                                            piUserinfos.setCardType(4);
                                        }
                                        //6.添加送达地址
                                        piUserinfos.setAddress(DSRTr.get(8).select("td").get(0).select("span").text().replace("* ", ""));
                                        //7.添加手机号码
                                        piUserinfos.setTelPhone(DSRTr.get(11).select("td").get(0).select("span").text().replace("* ", ""));
                                        //System.out.println("自然人" + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                                    } else if (dsrLx.equals("2")) {
                                        //5.添加证件号码
                                        piUserinfos.setCardNum(DSRTr.get(4).select("td").get(1).select("span").text().replace("* ", ""));
                                        //2.添加当事人类型id
                                        String userType = DSRTr.get(0).select("td").select("span").get(1).text();
                                        if (userType.contains("申") || userType.contains("原")) {
                                            piUserinfos.setUserType(1);
                                        } else if (userType.contains("被")) {
                                            piUserinfos.setUserType(2);
                                        } else {
                                            piUserinfos.setUserType(3);
                                        }
                                        //3.添加当事人名称
                                        piUserinfos.setUserName(DSRTr.get(2).select("td").get(0).select("span").text().replace("* ", "") + "(" + DSRTr.get(10).select("td").get(0).select("span").text() + ")");
                                        //4.添加证件类型
                                        String cardType = DSRTr.get(5).select("td").get(0).select("span").text();
                                        if (cardType.contains("身份证")){
                                            piUserinfos.setCardType(1);
                                        }else if (cardType.contains("军官证")){
                                            piUserinfos.setCardType(2);
                                        }else if (cardType.contains("护照")){
                                            piUserinfos.setCardType(3);
                                        }else {
                                            piUserinfos.setCardType(4);
                                        }
                                        //6.添加送达地址
                                        piUserinfos.setAddress(DSRTr.get(12).select("td").get(1).select("span").text());
                                        //7.添加手机号码
                                        piUserinfos.setTelPhone(DSRTr.get(11).select("td").get(0).select("span").text().replace("* ", ""));
                                        //添加公司名称
                                        piUserinfos.setDwmc(DSRTr.get(2).select("td").get(0).select("span").text().replace("* ", ""));
                                        //System.out.println("法人" + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                                    } else if (dsrLx.equals("0")) {
                                        //2.添加当事人类型id
                                        String userType = DSRTr.get(0).select("td").select("span").get(1).text();
                                        if (userType.contains("申") || userType.contains("原")) {
                                            piUserinfos.setUserType(1);
                                        } else if (userType.contains("被")) {
                                            piUserinfos.setUserType(2);
                                        } else {
                                            piUserinfos.setUserType(3);
                                        }
                                        //3.添加当事人姓名
                                        piUserinfos.setUserName(DSRTr.get(13).select("td").get(0).select("span").text().replace("* ", ""));
                                        //4.添加证件类型
                                        String cardType = DSRTr.get(5).select("td").get(0).select("span").text();
                                        if (cardType.contains("身份证")){
                                            piUserinfos.setCardType(1);
                                        }else if (cardType.contains("军官证")){
                                            piUserinfos.setCardType(2);
                                        }else if (cardType.contains("护照")){
                                            piUserinfos.setCardType(3);
                                        }else {
                                            piUserinfos.setCardType(4);
                                        }
                                        //5.添加证件号码
                                        piUserinfos.setCardNum(DSRTr.get(15).select("td").get(0).select("span").text().replace("* ", ""));
                                        //6.添加送达地址
                                        piUserinfos.setAddress(DSRTr.get(8).select("td").get(0).select("span").text().replace("* ", ""));
                                        //7.添加手机号码
                                        piUserinfos.setTelPhone(DSRTr.get(15).select("td").get(1).select("span").text().replace("* ", ""));
                                    }
                                    //8.添加flag
                                    piUserinfos.setFlag(1);
                                    //9.来源于速调对接
                                    piUserinfos.setInfosSource(3);
                                    //10.添加标记，用来筛选uid
                                    piUserinfos.setRemark(startDay + loginInfo.getUsername());
                                    //添加到list集合
                                    piUserinfosList.add(piUserinfos);
                                } catch (Exception e) {
                                    ErrorUtil.outLog(e.getMessage() + "--" + startDay + "--http://131.16.200.208:8081/stdj-inner/artery/parse.do?action=parse--" + loginInfo.getUsername());
                                    e.printStackTrace();
                                }
                                try {
                                    Thread.sleep(1500);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        mark += 2;
                    }
                }
                //Thread.sleep(1000000);
                //System.out.println("==========================================当事人入库========================================================");
                //当事人入库，获取ID
                iPiUserinfosService.saveBatch(piUserinfosList);
                List<PiUserinfos> userinfos = iPiUserinfosService.list(new QueryWrapper<PiUserinfos>().eq("Remark", startDay + loginInfo.getUsername()));
                //String cardNum ="";
                for (PiUserinfos userinfo : userinfos) {
                    //System.out.println(userinfo+"++++++++++++");
                    PiUserinfoPhone piUserinfoPhone = new PiUserinfoPhone();
                    PiCaseUserRel piCaseUserRel = new PiCaseUserRel();
                    PiUserinfoAddress piUserinfoAddress = new PiUserinfoAddress();
                    //添加电话
                    //if (!cardNum.equals(userinfo.getCardNum())) {
                    piUserinfoPhone.setAddTime(LocalDateTime.now());
                    piUserinfoPhone.setPhone(userinfo.getTelPhone());
                    piUserinfoPhone.setInfosSource(3);
                    piUserinfoPhone.setPuid(userinfo.getPuid());
                    piUserinfoPhoneList.add(piUserinfoPhone);
                    //添加地址
                    piUserinfoAddress.setAddTime(LocalDateTime.now());
                    piUserinfoAddress.setAddress(userinfo.getAddress());
                    piUserinfoAddress.setInfosSource(3);
                    piUserinfoAddress.setPuid(userinfo.getPuid());
                    piUserinfoAddressList.add(piUserinfoAddress);
                    //}
                    //cardNum = userinfo.getCardNum();
                    //添加用户和案件关系
                    piCaseUserRel.setAddTime(LocalDateTime.now());
                    piCaseUserRel.setAjid(userinfo.getAjid());
                    piCaseUserRel.setPuid(userinfo.getPuid());
                    piCaseUserRelList.add(piCaseUserRel);
                }
                //将电话/地址/关系入库
                iPiUserinfoPhoneService.saveBatch(piUserinfoPhoneList);
                iPiUserinfoAddressService.saveBatch(piUserinfoAddressList);
                iPiCaseUserRelService.saveBatch(piCaseUserRelList);
            }
            startTime0 = startTime0.plus(1, ChronoUnit.DAYS);
            startDay = startTime0.toString().substring(0, 10);
            TimeUtil.writeTime(startDay);
        }
    }
}
