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
	 * ���
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
	 * ����
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
	 * ɾ��
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
	 * ��ѯ��������
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
			// �ж��Ƿ�������
			if (rs.next()) {
				data.setName(rs.getString("Name"));
				data.setVocational(rs.getString("Vocational"));

				result.setT(data);
				result.setResult(1);
			} else {
				result.setResult(0);
				result.setMessage("û���ҵ����ݣ�");
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
	 * ��ѯ��������
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
			result.setResult(1);// �ɹ�
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
