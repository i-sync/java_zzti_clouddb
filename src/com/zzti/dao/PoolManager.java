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
	 * 返回数据库连接池中一个可用的链接
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		return ConnectionPool.getInstance().getConnection();
	}

	/**
	 * 释放资源 conn重新放回到连接池中
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
			// 记录日志
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
	 * 获取当前连接池的空闲数
	 * 
	 * @return
	 */
	public static int getFree_num() {
		return ConnectionPool.getInstance().getFree_num();
	}

	/**
	 * 获取当前正忙的连接数
	 * 
	 * @return
	 */
	public static int getAvtive_num() {
		return ConnectionPool.getInstance().getAvtive_num();
	}
}
