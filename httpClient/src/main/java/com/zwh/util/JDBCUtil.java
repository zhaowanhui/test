package com.zwh.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class JDBCUtil {
	//创建线程绑定对象
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();
	private static Properties p = new Properties();
	static{
		//以相对路径方式获取输入流
		InputStream is = JDBCUtil.class.getResourceAsStream("/com/baizhi/jdbc/day3/jdbc.properties");
		try {
			p.load(is);
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}//将配置文件的信息装载在Properties集合中
	}
	//获取连接
	public static Connection getConnection() throws Exception{
		//判断线程绑定对象中有没有连接
		Connection conn = tl.get();
		if(conn == null){
			Class.forName(p.getProperty("driver"));//获取配置文件中driver键对应的值
			conn = DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
			tl.set(conn);
		}
		return conn;
	}
	//关闭资源
	public static void close( Connection conn , PreparedStatement pstm ){
		if(pstm!=null) try{pstm.close();}catch(Exception e){}
		if(conn!=null) try{conn.close();tl.remove();}catch(Exception e){}
	}
	//关闭资源
	public static void close( Connection conn , PreparedStatement pstm ,ResultSet rs){
		if(rs!=null) try{rs.close();}catch(Exception e){}
		if(pstm!=null) try{pstm.close();}catch(Exception e){}
		if(conn!=null) try{conn.close();tl.remove();}catch(Exception e){}
	}
}
