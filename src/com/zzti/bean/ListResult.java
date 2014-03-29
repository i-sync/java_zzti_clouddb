package com.zzti.bean;

import java.io.Serializable;
import java.util.List;


public class ListResult<T> extends Result implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9120922723371978006L;
	/**
	 * ���ؽ������
	 */
	private List<T> list;
	public List<T> getList()
	{
		return this.list;
	}
	public void setList(List<T> list)
	{
		this.list= list;
	}
}
