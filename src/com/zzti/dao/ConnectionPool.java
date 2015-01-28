package com.zzti.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * 第三阶段：将配置信息移到配置文件中，较为严谨的数据库连接池
 * 
 * @author 陈浩夏
 */
public class ConnectionPool {

	//
	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	private static int INIT_CONN;
	private static int MAX_CONN;

	/**
	 * 当前数据库连接池，空闲的连接数
	 */
	private int free_num;

	/**
	 * 当前数据库连接池，正忙的连接数
	 */
	private int avtive_num;

	/**
	 * 用来存放空闲的数据库连接
	 */
	private LinkedList<Connection> freeConnections;

	private static ConnectionPool pool;
	private static Object obj = new Object();

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
	 * 构造方法 用来初始化一定数量的数据库连接
	 */
	private ConnectionPool() {
		freeConnections = new LinkedList<Connection>();
		System.out.println("pool连接池开始初始化连接了，初始化的数量是：" + INIT_CONN);
		/**
		 * 初始化一定数量的数据库连接
		 */
		for (int i = 0; i < INIT_CONN; i++) {
			Connection conn = newConnection();
			if (conn != null) {
				freeConnections.add(conn);
				free_num++;
			}
		}
		System.out.println("pool连接池初始化连接完成");
	}

	protected static ConnectionPool getInstance() {
		if (pool == null) {
			synchronized (obj) {
				if (pool == null)
					pool = new ConnectionPool();
			}
		}
		return pool;
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
	 * 获取一个空闲的连接
	 * 
	 * @return
	 */
	public synchronized Connection getConnection() {
		Connection conn = null;
		// 判断：目前连接池中是否还有空闲的连接数
		if (freeConnections != null && freeConnections.size() > 0) {
			// 获取到第一个元素，并删除
			conn = freeConnections.remove();
			// 判断：链接是否可用
			try {
				if (conn.isClosed()) {
					System.out.println("移除了一个不可用的链接");
					// 获取下一个链接
					avtive_num--;
					conn = getConnection();
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println("移除了一个不可用的链接");
				// 获取下一个链接
				avtive_num--;
				conn = getConnection();
			}
			free_num--;
			avtive_num++;
		}
		/*
		 * 判断：目前连接池中已经没有空闲的链接 当前正忙的连接数是否已经达到数据库连接池的最大值，如果没有达到最大值
		 * 1）、可以初始化一批链接，放到连接池中 2）、可以初始化一个链接，放到连接池中--
		 */
		else if (avtive_num < MAX_CONN) {
			// 获取一个新链接，存放到连接池
			conn = newConnection();
			avtive_num++;
		}
		// 当前正忙的连接数已经超过了 连接池的最大容量
		else {
			//
			System.out.println("目前已经达到最大连接数，请等待....");
		}
		return conn;
	}

	/**
	 * 释放连接，从先放回到连接池中
	 * 
	 * @param conn
	 */
	protected synchronized void freeConnection(Connection conn) {
		freeConnections.addLast(conn);
		free_num++;
		avtive_num--;
		notifyAll();
	}

	/**
	 * 获取当前连接池的空闲数
	 * 
	 * @return
	 */
	protected int getFree_num() {
		return free_num;
	}

	/**
	 * 获取当前正忙的连接数
	 * 
	 * @return
	 */
	protected int getAvtive_num() {
		return avtive_num;
	}

	/**
	 * 读取数据库连接池的配置信息
	 */
	private static void readConfig() {
		InputStream in = ConnectionPool.class.getClassLoader()
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
		INIT_CONN = Integer.parseInt(pro.getProperty("initsize"));
		MAX_CONN = Integer.parseInt(pro.getProperty("poolsize"));

	}

}
