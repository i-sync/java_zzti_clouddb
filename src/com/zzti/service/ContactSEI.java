package com.zzti.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.zzti.bean.Class;
import com.zzti.bean.Contact;
import com.zzti.bean.ListResult;
import com.zzti.bean.Result;
import com.zzti.bean.TResult;

@WebService(name="ContactSEI",targetNamespace="http://service.zzti.com/")
public interface ContactSEI {
	/***********************Class*************************/
	/**
	 * 班级添加
	 * @param data
	 * @return
	 */
	public Result class_add(Class data);
	/**
	 * 班级修改
	 * @param data
	 * @return
	 */
	public Result class_update(Class data);
	/**
	 * 班级删除
	 * @param data
	 * @return
	 */
	public Result class_delete(Class data);
	/**
	 * 获取对象
	 * @param data
	 * @return
	 */
	public TResult<Class> class_getModel(Class data);
	/**
	 * 获取列表
	 * @return
	 */
	public ListResult<Class> class_getList();
	/***********************Class*************************/

	/***********************Contact*************************/	
	/**
	 * 判断联系人是否存在
	 * @param data
	 * @return
	 */
	public boolean contact_exists(Contact data);
	/**
	 * 添加联系人
	 * @param data
	 * @return
	 */
	public Result contact_add(Contact data);
	/**
	 * 个性联系人
	 * @param data
	 * @return
	 */
	public Result contact_update(Contact data);
	/**
	 * 删除联系人
	 * @param data
	 * @return
	 */
	public Result contact_delete(Contact data);
	/**
	 * 获取联系人对象
	 * @param data
	 * @return
	 */
	public TResult<Contact> contact_getModel(Contact data);
	/**
	 * 获取联系人列表
	 * @param data
	 * @return
	 */
	public ListResult<Contact> contact_getList(Contact data);

	/***********************Contact*************************/
	
	/***********************android******************************/
	/**
	 * android客户端获取所有的班级列表
	 * @return
	 */
	public String android_class_getlist();
	/**
	 * android客户端添加联系人
	 * @param string
	 * @return
	 */
	public String android_contact_add(@WebParam(name="string") String string);
	/**
	 * android客户端修改联系人
	 * @param string
	 * @return
	 */
	public String android_contact_update(@WebParam(name="string") String string);
	/**
	 * android客户端删除联系人
	 * @param string
	 * @return
	 */
	public String android_contact_delete(@WebParam(name="string") String string);
	/**
	 * android客户端获取联系人对象
	 * @param string
	 * @return
	 */
	public String android_contact_getmodel(@WebParam(name="string") String string);
	/**
	 * android客户端获取联系人列表
	 * @param string
	 * @return
	 */
	public String android_contact_getlist(@WebParam(name="string") String string);
	
	/***********************android******************************/
}
