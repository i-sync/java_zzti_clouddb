package com.zzti.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zzti.bean.ListResult;
import com.zzti.bean.Result;
import com.zzti.utils.SoapObjectUtils;

@Path("/class")
public class ClassResource {
	
	@GET
	@Path("/list")
	@Produces({MediaType.TEXT_XML})
	public ListResult<com.zzti.bean.Class> class_list()
	{
		return new com.zzti.dao.Class().getList();
	}
	
	
	/*
	 * add class entry
	 */
	@POST
	@Path("/add/{str}")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Result class_add(@PathParam("str") String str)
	{
		Result result = null;
		com.zzti.bean.Class data = (com.zzti.bean.Class)SoapObjectUtils.StringToObject(str);
		if(data ==null)
		{
			result = new Result();
			result.setResult(0);
			result.setMessage("参数转换为空！");;
		}
		return new com.zzti.dao.Class().add(data);
	}
	
	/*
	 * update class entry
	 */
	@POST
	@Path("/update/{str}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Result class_update(@PathParam("str") String str)
	{
		Result result = null;
		com.zzti.bean.Class data = (com.zzti.bean.Class)SoapObjectUtils.StringToObject(str);
		if(data==null)
		{
			result = new Result();
			result.setResult(0);
			result.setMessage("参数转换为空!");
			return result ;
		}
		return new com.zzti.dao.Class().update(data);
	}
	
	
}
