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
	 * ���캯��
	 */
	public ConnectionManager() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ����һ������
	 * 
	 * @return
	 */
	public Connection getConnection() {
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
	public static void free(ResultSet rs, Statement stmt, Connection conn) {
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
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
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
