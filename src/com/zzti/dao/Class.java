package com.zzti.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.zzti.bean.ListResult;
import com.zzti.bean.Result;
import com.zzti.bean.TResult;

public class Class {

	/**
	 * 添加
	 * 
	 * @param data
	 * @return
	 */
	public Result add(com.zzti.bean.Class data) {
		Result result = new Result();
		try {
			String sql = "insert into Class(Name,Vocational) values(?,?);";
			Object[] objs = new Object[] { data.getName(), data.getVocational() };
			int res = DBHelper.executeNonQuery(sql, objs);
			result.setResult(res);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	/**
	 * 更新
	 * 
	 * @param data
	 * @return
	 */
	public Result update(com.zzti.bean.Class data) {
		Result result = new Result();
		try {
			String sql = "update Class set Name=?,Vocational=? where ID=?";
			Object[] objs = new Object[] { data.getName(),
					data.getVocational(), data.getId() };
			int res = DBHelper.executeNonQuery(sql, objs);
			result.setResult(res);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	/**
	 * 删除
	 * 
	 * @param data
	 * @return
	 */
	public Result delete(com.zzti.bean.Class data) {
		Result result = new Result();
		try {
			String sql = "delete from Class where ID=?";
			Object[] objs = new Object[] { data.getId() };
			int res = DBHelper.executeNonQuery(sql, objs);
			result.setResult(res);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	/**
	 * 查询单个对象
	 * 
	 * @param data
	 * @return
	 */
	public TResult<com.zzti.bean.Class> getModel(com.zzti.bean.Class data) {
		TResult<com.zzti.bean.Class> result = new TResult<com.zzti.bean.Class>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select * from Class where ID=?";
			Object[] objs = new Object[] { data.getId() };

			conn = new ConnectionManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < objs.length; i++) {
				pstmt.setObject(i + 1, objs[i]);
			}
			rs = pstmt.executeQuery();
			// 判断是否有数据
			if (rs.next()) {
				data.setName(rs.getString("Name"));
				data.setVocational(rs.getString("Vocational"));

				result.setT(data);
				result.setResult(1);
			} else {
				result.setResult(0);
				result.setMessage("没有找到数据！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		} finally {
			// PoolManager.free(rs, pstmt, conn);
			ConnectionManager.free(rs, pstmt, conn);
		}

		return result;
	}

	/**
	 * 查询所有数据
	 * 
	 * @return
	 */
	public ListResult<com.zzti.bean.Class> getList() {
		ListResult<com.zzti.bean.Class> result = new ListResult<com.zzti.bean.Class>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String sql = "select * from Class";
			conn = new ConnectionManager().getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<com.zzti.bean.Class> list = new ArrayList<com.zzti.bean.Class>();
			com.zzti.bean.Class data = null;
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				String vocational = rs.getString("Vocational");
				data = new com.zzti.bean.Class(id, name, vocational);
				list.add(data);
			}
			result.setResult(1);// 成功
			result.setList(list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		} finally {
			// PoolManager.free(rs, stmt, conn);
			ConnectionManager.free(rs, stmt, conn);
		}
		return result;
	}
}
