package com.zzti.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

/**
 * �����׶Σ���������Ϣ�Ƶ������ļ��У���Ϊ�Ͻ������ݿ����ӳ�
 * 
 * @author �º���
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
	 * ��ǰ���ݿ����ӳأ����е�������
	 */
	private int free_num;

	/**
	 * ��ǰ���ݿ����ӳأ���æ��������
	 */
	private int avtive_num;

	/**
	 * ������ſ��е����ݿ�����
	 */
	private LinkedList<Connection> freeConnections;

	private static ConnectionPool pool;
	private static Object obj = new Object();

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
	 * ���췽�� ������ʼ��һ�����������ݿ�����
	 */
	private ConnectionPool() {
		freeConnections = new LinkedList<Connection>();
		System.out.println("pool���ӳؿ�ʼ��ʼ�������ˣ���ʼ���������ǣ�" + INIT_CONN);
		/**
		 * ��ʼ��һ�����������ݿ�����
		 */
		for (int i = 0; i < INIT_CONN; i++) {
			Connection conn = newConnection();
			if (conn != null) {
				freeConnections.add(conn);
				free_num++;
			}
		}
		System.out.println("pool���ӳس�ʼ���������");
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
	 * ��ȡһ�����е�����
	 * 
	 * @return
	 */
	public synchronized Connection getConnection() {
		Connection conn = null;
		// �жϣ�Ŀǰ���ӳ����Ƿ��п��е�������
		if (freeConnections != null && freeConnections.size() > 0) {
			// ��ȡ����һ��Ԫ�أ���ɾ��
			conn = freeConnections.remove();
			// �жϣ������Ƿ����
			try {
				if (conn.isClosed()) {
					System.out.println("�Ƴ���һ�������õ�����");
					// ��ȡ��һ������
					avtive_num--;
					conn = getConnection();
				}
			} catch (SQLException e) {
				// e.printStackTrace();
				System.out.println("�Ƴ���һ�������õ�����");
				// ��ȡ��һ������
				avtive_num--;
				conn = getConnection();
			}
			free_num--;
			avtive_num++;
		}
		/*
		 * �жϣ�Ŀǰ���ӳ����Ѿ�û�п��е����� ��ǰ��æ���������Ƿ��Ѿ��ﵽ���ݿ����ӳص����ֵ�����û�дﵽ���ֵ
		 * 1�������Գ�ʼ��һ�����ӣ��ŵ����ӳ��� 2�������Գ�ʼ��һ�����ӣ��ŵ����ӳ���--
		 */
		else if (avtive_num < MAX_CONN) {
			// ��ȡһ�������ӣ���ŵ����ӳ�
			conn = newConnection();
			avtive_num++;
		}
		// ��ǰ��æ���������Ѿ������� ���ӳص��������
		else {
			//
			System.out.println("Ŀǰ�Ѿ��ﵽ�������������ȴ�....");
		}
		return conn;
	}

	/**
	 * �ͷ����ӣ����ȷŻص����ӳ���
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
	 * ��ȡ��ǰ���ӳصĿ�����
	 * 
	 * @return
	 */
	protected int getFree_num() {
		return free_num;
	}

	/**
	 * ��ȡ��ǰ��æ��������
	 * 
	 * @return
	 */
	protected int getAvtive_num() {
		return avtive_num;
	}

	/**
	 * ��ȡ���ݿ����ӳص�������Ϣ
	 */
	private static void readConfig() {
		InputStream in = ConnectionPool.class.getClassLoader()
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
		INIT_CONN = Integer.parseInt(pro.getProperty("initsize"));
		MAX_CONN = Integer.parseInt(pro.getProperty("poolsize"));

	}

}
