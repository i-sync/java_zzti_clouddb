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
	 * 增删改【Add、Del、Update】
	 * 
	 * @param sql
	 * @return int
	 */
	public static int executeNonQuery(String sql) {
		int result = 0;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = new ConnectionManager().getConnection();
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			//PoolManager.free(null, stmt, conn);
			ConnectionManager.free(null, stmt,conn);
		}
		return result;
	}

	/**
	 * 
	 * 增删改【Add、Delete、Update】
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
			conn = new ConnectionManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			result = pstmt.executeUpdate();
		} catch (SQLException err) {
			throw new RuntimeException(err);
		} finally {
			//PoolManager.free(null, pstmt, conn);
			ConnectionManager.free(null, pstmt,conn);
		}
		return result;
	}

	/**
	 * 查【Query】
	 * 
	 * @param sql
	 * @return ResultSet
	 */
	public static ResultSet executeQuery(String sql) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = new ConnectionManager().getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			//PoolManager.free(null, stmt, conn);
			ConnectionManager.free(rs, stmt,conn);
		}
		return rs;
	}

	/**
	 * 查【Query】
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
			conn = new ConnectionManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
		} catch (SQLException err) {
			throw new RuntimeException(err);
		} finally {
			//PoolManager.free(null, pstmt, conn);
			ConnectionManager.free(rs, pstmt,conn);
		}
		return rs;
	}

	/**
	 * 
	 * 判断记录是否存在
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
			conn =new ConnectionManager().getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			rs.last();
			int count = rs.getRow();
			return count > 0;
		} catch (SQLException err) {
			throw new RuntimeException(err);
		} finally {
			//PoolManager.free(rs, stmt, conn);
			ConnectionManager.free(rs, stmt,conn);
		}
	}

	/**
	 * 
	 * 判断记录是否存在
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
			conn = new ConnectionManager().getConnection();
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
			//PoolManager.free(rs, pstmt, conn);
			ConnectionManager.free(rs, pstmt,conn);
		}
	}

}
