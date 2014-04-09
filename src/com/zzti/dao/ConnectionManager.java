package com.zzti.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionManager {

	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PASSWORD;

	private static ConnectionManager instance;
	private Connection connection;
	/**
	 * 加载驱动
	 */
	static {
		//
		readConfig();
		try {
			java.lang.Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError("加载数据库驱动：" + DRIVER + "发生异常:"
					+ e.getMessage());
		}
	}

	/**
	 * 私有构造
	 */
	private ConnectionManager() {
		// TODO Auto-generated constructor stub
		connection = newConnection();
		System.out.println("初始化连接完成！");

	}

	/**
	 * get instance
	 * 
	 * @return
	 */
	public static ConnectionManager getInstance() {
		if (instance == null) {
			synchronized (ConnectionManager.class) {
				if (instance == null)
					instance = new ConnectionManager();
			}
		}
		return instance;
	}

	/**
	 * 获取连接对象
	 * @return
	 */
	public Connection getConnection() {
		if(connection ==null)
			connection = newConnection();
		return connection;
	}
	
	/**
	 * 创建一个新的连接
	 * 
	 * @return
	 */
	private Connection newConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("获取连接发送错误，错误信息如下：");
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * 释放资源 
	 * 
	 * @param rs
	 * @param stmt
	 */
	public static void free(ResultSet rs, Statement stmt) {
		try {
			if (null != rs) {
				rs.close();
			}
		} catch (SQLException e) {
			// 记录日志
			e.printStackTrace();
		} finally {
			try {
				if (null != stmt) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} 
		}
	}

	/**
	 * 读取数据库连接池的配置信息
	 */
	private static void readConfig() {
		InputStream in = ConnectionManager.class.getClassLoader()
				.getResourceAsStream("config.properties");
		//
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e) {
			System.out.println("读取配置文件信息发送错误，错误信息如下：");
			e.printStackTrace();
		}
		//
		DRIVER = pro.getProperty("driver");
		URL = pro.getProperty("url");
		USER = pro.getProperty("username");
		PASSWORD = pro.getProperty("password");

	}
}
