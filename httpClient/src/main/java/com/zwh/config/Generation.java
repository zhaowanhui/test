package com.zwh.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class Generation {

    public static final String DB_URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=utf8&allowMultiQueries=true";
    public static final String USER_NAME = "root";
    public static final String PASSWORD = "root";
    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String AUTHOR = "zwh";
    //包名，会按照com/example/demo这种形式生成类
    public static final String PACKAGE = "com.zwh";

    public static void generateByTables(String... tableNames) {
        String projectPath = System.getProperty("user.dir");//C:\Users\30560\IdeaProjects\test
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)
                .setAuthor(AUTHOR)
                .setOpen(false)
                .setOutputDir(projectPath + "/capture/src/main/java")// 输出目录
                .setFileOverride(true)// 是否覆盖文件
                .setBaseResultMap(true)//是否生产mapper的baseResultMap
                .setSwagger2(true)
                .setMapperName("I%sMapper");// 自定义文件命名，注意 %s 会自动填充表实体属性！
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)//数据库类型
                .setUrl(DB_URL)
                .setUsername(USER_NAME)
                .setPassword(PASSWORD)
                .setDriverName(DRIVER);
        StrategyConfig strategyConfig = new StrategyConfig();// 策略配置
        strategyConfig
                .setRestControllerStyle(true)//controller 是否支持是restController
                .setCapitalMode(true)
                .setEntityLombokModel(true)//实体增加lombot注解
                .setNaming(NamingStrategy.underline_to_camel)
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        new AutoGenerator().setGlobalConfig(config)//全局配置
                .setDataSource(dataSourceConfig)//数据源
                .setStrategy(strategyConfig)//策略配置
                .setPackageInfo(
                        new PackageConfig()//包名配置
                                .setParent(PACKAGE)
                                .setController("controller")
                                .setService("service")
                                .setServiceImpl("service.impl")
                                .setEntity("entity.po")
                                .setMapper("dao")
                                .setXml("mapper")
                ).execute();
    }
    public static void main(String[] args) {
        generateByTables(new String[]{"case_of_court"});
    }
}
