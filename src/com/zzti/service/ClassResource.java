package com.zzti.service;
import com.zzti.bean.Class;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.zzti.bean.Result;

@Path("/class")
public class ClassResource {
	
	/*
	 * add class entry
	 */
	@POST
	@Path("/add")
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public Result add_class(Class data)
	{
		return new com.zzti.dao.Class().add(data);
	}
	
	/*
	 * update class entry
	 */
	@POST
	@Path("/update")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Result class_update(Class data)
	{
		return new com.zzti.dao.Class().update(data);
	}
	
	
}
