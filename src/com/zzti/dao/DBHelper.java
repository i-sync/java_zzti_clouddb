package com.zzti.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public final class DBHelper {

	public DBHelper() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��ɾ�ġ�Add��Del��Update��
	 * 
	 * @param sql
	 * @return int
	 */
	public static int executeNonQuery(String sql) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = PoolManager.getConnection();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			PoolManager.free(null, stmt, conn);
		}
		return result;
	}

	/**
	 * 
	 * ��ɾ�ġ�Add��Delete��Update��
	 * 
	 * 
	 * 
	 * @param sql
	 * 
	 * @param obj
	 * 
	 * @return int
	 */
	public static int executeNonQuery(String sql, Object... obj) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = PoolManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			result = pstmt.executeUpdate();
		} catch (SQLException err) {
			throw new RuntimeException(err);
		} finally {
			PoolManager.free(null, pstmt, conn);
		}
		return result;
	}

	/**
	 * �顾Query��
	 * 
	 * @param sql
	 * @return ResultSet
	 */
	public static ResultSet executeQuery(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = PoolManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException err) {
			err.printStackTrace();
			PoolManager.free(rs, stmt, conn);
		} finally {
			PoolManager.free(null, stmt, conn);
		}
		return rs;
	}

	/**
	 * �顾Query��
	 * 
	 * @param sql
	 * 
	 * @param obj
	 * 
	 * @return ResultSet
	 */
	public static ResultSet executeQuery(String sql, Object... obj) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = PoolManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
		} catch (SQLException err) {
			PoolManager.free(rs, pstmt, conn);
			throw new RuntimeException(err);
		} finally {
			PoolManager.free(null, pstmt, conn);
		}
		return rs;
	}

	/**
	 * 
	 * �жϼ�¼�Ƿ����
	 * 
	 * 
	 * 
	 * @param sql
	 * 
	 * @return Boolean
	 */

	public static Boolean isExist(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = PoolManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.last();
			int count = rs.getRow();
			return count > 0;
		} catch (SQLException err) {
			throw new RuntimeException(err);
		} finally {
			PoolManager.free(rs, stmt, conn);
		}
	}

	/**
	 * 
	 * �жϼ�¼�Ƿ����
	 * 
	 * 
	 * 
	 * @param sql
	 * 
	 * @return Boolean
	 */

	public static Boolean isExist(String sql, Object... obj) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = PoolManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
			rs.last();
			int count = rs.getRow();
			return count > 0;
		} catch (SQLException err) {
			throw new RuntimeException(err);
		} finally {
			PoolManager.free(rs, pstmt, conn);
		}
	}

}
