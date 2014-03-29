package com.zzti.dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class PoolManager {
	//
	private PoolManager() {
	}

	/**
	 * �������ݿ����ӳ���һ�����õ�����
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		return ConnectionPool.getInstance().getConnection();
	}

	/**
	 * �ͷ���Դ conn���·Żص����ӳ���
	 * 
	 * @param rs
	 * @param stmt
	 * @param conn
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
				if (null != conn) {
					ConnectionPool.getInstance().freeConnection(conn);
				}
			}
		}
	}

	/**
	 * ��ȡ��ǰ���ӳصĿ�����
	 * 
	 * @return
	 */
	public static int getFree_num() {
		return ConnectionPool.getInstance().getFree_num();
	}

	/**
	 * ��ȡ��ǰ��æ��������
	 * 
	 * @return
	 */
	public static int getAvtive_num() {
		return ConnectionPool.getInstance().getAvtive_num();
	}
}
