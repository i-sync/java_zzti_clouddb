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

public class Contact {

	/**
	 * �ж���ϵ�������Ƿ����
	 * 
	 * @param data
	 * @return
	 */
	public boolean exists(com.zzti.bean.Contact data) {
		boolean flag = false;
		try {
			String sql = "select 1 from Contacts where `Name`=? and ID != ?;";
			Object[] obj = new Object[] { data.getName(), data.getId() };
			flag = DBHelper.isExist(sql, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * �����ϵ��
	 * 
	 * @param data
	 * @return
	 */
	public Result add(com.zzti.bean.Contact data) {
		Result result = new Result();
		// �����ж���ϵ���Ƿ��Ѿ�����
		boolean flag = exists(data);
		if (flag) {
			result.setResult(-1);// ˵���û��Ѵ���
			result.setMessage("�û����Ѵ��ڣ�");
			return result;
		}
		try {
			String sql = "insert into Contacts(`Name`,`CID`,`Phone`,`Email`,`Living`,`Company`,`Remark`) values(?,?,?,?,?,?,?);";
			Object[] obj = new Object[] { data.getName(), data.getCid(),
					data.getPhone(), data.getEmail(), data.getLiving(),
					data.getCompany(), data.getRemark() };
			int res = DBHelper.executeNonQuery(sql, obj);
			result.setResult(res);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	/**
	 * �޸���ϵ����Ϣ
	 * 
	 * @param data
	 * @return
	 */
	public Result update(com.zzti.bean.Contact data) {
		Result result = new Result();
		// �����ж���ϵ���Ƿ��Ѿ�����
		boolean flag = exists(data);
		if (flag) {
			result.setResult(-1);// ˵���û��Ѵ���
			result.setMessage("�û����Ѵ��ڣ�");
			return result;
		}
		try {
			String sql = "update Contacts set `Name`=?,`CID`=?,`Phone`=?,`Email`=?,`Living`=?,`Company`=?,`Remark`=? where `ID`=?;";
			Object[] obj = new Object[] { data.getName(), data.getCid(),
					data.getPhone(), data.getEmail(), data.getLiving(),
					data.getCompany(), data.getRemark(), data.getId() };
			int res = DBHelper.executeNonQuery(sql, obj);
			result.setResult(res);
		} catch (Exception e) {
			e.printStackTrace();
			result.setResult(0);
			result.setMessage(e.getMessage());
		}
		return result;
	}

	/**
	 * ɾ����ϵ����Ϣ
	 * 
	 * @param data
	 * @return
	 */
	public Result delete(com.zzti.bean.Contact data) {
		Result result = new Result();
		try {
			String sql = "delete from Contacts where ID=?";
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
	public TResult<com.zzti.bean.Contact> getModel(com.zzti.bean.Contact data) {
		TResult<com.zzti.bean.Contact> result = new TResult<com.zzti.bean.Contact>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select a.*,b.Name as cName from Contacts a inner join Class b on a.CID= b.ID where a.ID=?"; // "select * from contacts where ID=?";
			Object[] obj = new Object[] { data.getId() };

			conn =ConnectionManager.getInstance().getConnection(); //PoolManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				pstmt.setObject(i + 1, obj[i]);
			}
			rs = pstmt.executeQuery();
			// �ж��Ƿ�������
			if (rs.next()) {
				data.setName(rs.getString("Name"));
				data.setCid(rs.getInt("CID"));
				data.setCname(rs.getString("cName"));
				data.setPhone(rs.getString("Phone"));
				data.setEmail(rs.getString("Email"));
				data.setLiving(rs.getString("Living"));
				data.setCompany(rs.getString("Company"));
				data.setRemark(rs.getString("Remark"));

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
			//PoolManager.free(rs, pstmt, conn);
			ConnectionManager.free(rs, pstmt);
		}

		return result;
	}

	/**
	 * ��ѯ��������
	 * 
	 * @return
	 */
	public ListResult<com.zzti.bean.Contact> getList(com.zzti.bean.Contact data) {
		ListResult<com.zzti.bean.Contact> result = new ListResult<com.zzti.bean.Contact>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			String condition = " where 1=1 ";
			String limit = "";
			if (data.getPage() != null) {
				int start = (data.getPage().getPageIndex() - 1)
						* data.getPage().getPageSize();
				int end = data.getPage().getPageIndex()
						* data.getPage().getPageSize();
				limit = " limit " + start + "," + end;
			}
			// �ж������Ƿ�Ϊ��
			if (data.getName() != null && !data.getName().equals("")) {
				condition += " and Name like '%" + data.getName() + "%' ";
			}
			if (data.getPhone() != null && !data.getPhone().equals("")) {
				condition += " and Phone like '%" + data.getPhone() + "%'";
			}
			if (data.getCid() > 0) {
				condition += " and cid=" + data.getCid();
			}

			String sql = "select a.*,b.Name as cName from (select * from Contacts "
					+ condition
					+ ") a inner join(select * from Class) b  on a.CID = b.ID order by a.ID  "
					+ limit;

			conn =ConnectionManager.getInstance().getConnection(); //PoolManager.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			List<com.zzti.bean.Contact> list = new ArrayList<com.zzti.bean.Contact>();
			// com.zzti.bean.Contacts data = null;
			while (rs.next()) {
				int id = rs.getInt("ID");
				String name = rs.getString("Name");
				int cid = rs.getInt("CID");
				String cname = rs.getString("cName");
				String phone = rs.getString("Phone");
				String email = rs.getString("Email");
				String living = rs.getString("Living");
				String company = rs.getString("Company");
				String remark = rs.getString("Remark");
				data = new com.zzti.bean.Contact(id, name, cid, cname, phone,
						email, living, company, remark);
				list.add(data);
			}
			rs.close();

			// ��ѯ����
			sql = "select count(1) as count from (select * from Contacts "
					+ condition
					+ ") a inner join(select * from Class) b  on a.CID = b.ID order by a.ID";
			rs = stmt.executeQuery(sql);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count");
			}
			// ��������
			result.setObj(count);
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
			//PoolManager.free(rs, stmt, conn);
			ConnectionManager.free(rs, stmt);
		}
		return result;
	}

}
