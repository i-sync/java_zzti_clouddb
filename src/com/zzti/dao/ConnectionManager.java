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
	 * ��������
	 */
	static {
		//
		readConfig();
		try {
			java.lang.Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new ExceptionInInitializerError("�������ݿ�������" + DRIVER + "�����쳣:"
					+ e.getMessage());
		}
	}

	/**
	 * ˽�й���
	 */
	private ConnectionManager() {
		// TODO Auto-generated constructor stub
		connection = newConnection();
		System.out.println("��ʼ��������ɣ�");

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
	 * ��ȡ���Ӷ���
	 * @return
	 */
	public Connection getConnection() {
		if(connection ==null)
			connection = newConnection();
		return connection;
	}
	
	/**
	 * ����һ���µ�����
	 * 
	 * @return
	 */
	private Connection newConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("��ȡ���ӷ��ʹ��󣬴�����Ϣ���£�");
			e.printStackTrace();
		}
		return conn;
	}
	
	/**
	 * �ͷ���Դ 
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
			// ��¼��־
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
	 * ��ȡ���ݿ����ӳص�������Ϣ
	 */
	private static void readConfig() {
		InputStream in = ConnectionManager.class.getClassLoader()
				.getResourceAsStream("config.properties");
		//
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e) {
			System.out.println("��ȡ�����ļ���Ϣ���ʹ��󣬴�����Ϣ���£�");
			e.printStackTrace();
		}
		//
		DRIVER = pro.getProperty("driver");
		URL = pro.getProperty("url");
		USER = pro.getProperty("username");
		PASSWORD = pro.getProperty("password");

	}
}
