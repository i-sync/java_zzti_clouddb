package com.zzti.service;

import javax.jws.WebParam;
import javax.jws.WebService;

import com.zzti.bean.Class;
import com.zzti.bean.ListResult;
import com.zzti.bean.Result;
import com.zzti.bean.TResult;
import com.zzti.utils.SoapObjectUtils;

@WebService(targetNamespace = "http://service.zzti.com/", endpointInterface = "com.zzti.service.ContactSEI", portName = "ServicePort", serviceName = "ContactService")
public class Contact implements ContactSEI {

	/***********************Class*************************/
	/**
	 * �༶���
	 * @param data
	 * @return
	 */
	public Result class_add(Class data)
	{
		return new com.zzti.dao.Class().add(data);
	}
	/**
	 * �༶�޸�
	 * @param data
	 * @return
	 */
	public Result class_update(Class data)
	{
		return new com.zzti.dao.Class().update(data);
	}
	/**
	 * �༶ɾ��
	 * @param data
	 * @return
	 */
	public Result class_delete(Class data)
	{
		return new com.zzti.dao.Class().delete(data);
	}
	/**
	 * ��ȡ����
	 * @param data
	 * @return
	 */
	public TResult<Class> class_getModel(Class data)
	{
		return new com.zzti.dao.Class().getModel(data);
	}
	/**
	 * ��ȡ�б�
	 * @return
	 */
	public ListResult<Class> class_getList()
	{
		return new com.zzti.dao.Class().getList();
	}
	/***********************Class*************************/

	/***********************Contact*************************/	
	/**
	 * �ж���ϵ���Ƿ����
	 * @param data
	 * @return
	 */
	public boolean contact_exists(com.zzti.bean.Contact data)
	{
		return new com.zzti.dao.Contact().exists(data);
	}
	/**
	 * �����ϵ��
	 * @param data
	 * @return
	 */
	public Result contact_add(com.zzti.bean.Contact data)
	{
		return new com.zzti.dao.Contact().add(data);
	}
	/**
	 * ������ϵ��
	 * @param data
	 * @return
	 */
	public Result contact_update(com.zzti.bean.Contact data)
	{
		return new com.zzti.dao.Contact().update(data);
	}
	/**
	 * ɾ����ϵ��
	 * @param data
	 * @return
	 */
	public Result contact_delete(com.zzti.bean.Contact data)
	{
		return new com.zzti.dao.Contact().delete(data);
	}
	/**
	 * ��ȡ��ϵ�˶���
	 * @param data
	 * @return
	 */
	public TResult<com.zzti.bean.Contact> contact_getModel(com.zzti.bean.Contact data)
	{
		return new com.zzti.dao.Contact().getModel(data);
	}
	/**
	 * ��ȡ��ϵ���б�
	 * @param data
	 * @return
	 */
	public ListResult<com.zzti.bean.Contact> contact_getList(com.zzti.bean.Contact data)
	{
		return new com.zzti.dao.Contact().getList(data);
	}

	/***********************Contact*************************/
	
	
	/***********************android******************************/
	/**
	 * android�ͻ��˻�ȡ���еİ༶�б�
	 * @return
	 */
	public String android_class_getlist()
	{
		Result result = new com.zzti.dao.Class().getList();
		String string = SoapObjectUtils.ObjectToSting(result);
		return string;
	}
	/**
	 * android�ͻ��������ϵ��
	 * @param string
	 * @return
	 */
	public String android_contact_add(@WebParam(name="string") String string)
	{
		Result result =null;
		com.zzti.bean.Contact data = (com.zzti.bean.Contact)SoapObjectUtils.StringToObject(string);
		if(data==null)
		{
			result = new Result();
			result.setResult(0);
			result.setMessage("����ת��Ϊ�գ�");
			return SoapObjectUtils.ObjectToSting(result);
		}
		
		result = new com.zzti.dao.Contact().add(data);
		return SoapObjectUtils.ObjectToSting(result);
	}
	/**
	 * android�ͻ����޸���ϵ��
	 * @param string
	 * @return
	 */
	public String android_contact_update(@WebParam(name="string") String string)
	{
		Result result =null;
		com.zzti.bean.Contact data = (com.zzti.bean.Contact)SoapObjectUtils.StringToObject(string);
		if(data==null)
		{
			result = new Result();
			result.setResult(0);
			result.setMessage("����ת��Ϊ�գ�");
			return SoapObjectUtils.ObjectToSting(result);
		}
		
		result = new com.zzti.dao.Contact().update(data);
		return SoapObjectUtils.ObjectToSting(result);
	}
	/**
	 * android�ͻ���ɾ����ϵ��
	 * @param string
	 * @return
	 */
	public String android_contact_delete(@WebParam(name="string") String string)
	{
		Result result =null;
		com.zzti.bean.Contact data = (com.zzti.bean.Contact)SoapObjectUtils.StringToObject(string);
		if(data==null)
		{
			result = new Result();
			result.setResult(0);
			result.setMessage("����ת��Ϊ�գ�");
			return SoapObjectUtils.ObjectToSting(result);
		}
		
		result = new com.zzti.dao.Contact().delete(data);
		return SoapObjectUtils.ObjectToSting(result);
	}
	/**
	 * android�ͻ��˻�ȡ��ϵ�˶���
	 * @param string
	 * @return
	 */
	public String android_contact_getmodel(@WebParam(name="string") String string)
	{
		TResult<com.zzti.bean.Contact> result =null;
		com.zzti.bean.Contact data = (com.zzti.bean.Contact)SoapObjectUtils.StringToObject(string);
		if(data==null)
		{
			result = new TResult<com.zzti.bean.Contact>();
			result.setResult(0);
			result.setMessage("����ת��Ϊ�գ�");
			return SoapObjectUtils.ObjectToSting(result);
		}
		
		result = new com.zzti.dao.Contact().getModel(data);
		return SoapObjectUtils.ObjectToSting(result);
	}
	/**
	 * android�ͻ��˻�ȡ��ϵ���б�
	 * @param string
	 * @return
	 */
	public String android_contact_getlist(@WebParam(name="string") String string)
	{
		ListResult<com.zzti.bean.Contact> result =null;
		com.zzti.bean.Contact data = (com.zzti.bean.Contact)SoapObjectUtils.StringToObject(string);
		if(data==null)
		{
			result = new ListResult<com.zzti.bean.Contact>();
			result.setResult(0);
			result.setMessage("����ת��Ϊ�գ�");
			return SoapObjectUtils.ObjectToSting(result);
		}
		
		result = new com.zzti.dao.Contact().getList(data);
		return SoapObjectUtils.ObjectToSting(result);
	}
	
	/***********************android******************************/

}
