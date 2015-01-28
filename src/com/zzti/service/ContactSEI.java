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
	 * �༶���
	 * @param data
	 * @return
	 */
	public Result class_add(Class data);
	/**
	 * �༶�޸�
	 * @param data
	 * @return
	 */
	public Result class_update(Class data);
	/**
	 * �༶ɾ��
	 * @param data
	 * @return
	 */
	public Result class_delete(Class data);
	/**
	 * ��ȡ����
	 * @param data
	 * @return
	 */
	public TResult<Class> class_getModel(Class data);
	/**
	 * ��ȡ�б�
	 * @return
	 */
	public ListResult<Class> class_getList();
	/***********************Class*************************/

	/***********************Contact*************************/	
	/**
	 * �ж���ϵ���Ƿ����
	 * @param data
	 * @return
	 */
	public boolean contact_exists(Contact data);
	/**
	 * �����ϵ��
	 * @param data
	 * @return
	 */
	public Result contact_add(Contact data);
	/**
	 * ������ϵ��
	 * @param data
	 * @return
	 */
	public Result contact_update(Contact data);
	/**
	 * ɾ����ϵ��
	 * @param data
	 * @return
	 */
	public Result contact_delete(Contact data);
	/**
	 * ��ȡ��ϵ�˶���
	 * @param data
	 * @return
	 */
	public TResult<Contact> contact_getModel(Contact data);
	/**
	 * ��ȡ��ϵ���б�
	 * @param data
	 * @return
	 */
	public ListResult<Contact> contact_getList(Contact data);

	/***********************Contact*************************/
	
	/***********************android******************************/
	/**
	 * android�ͻ��˻�ȡ���еİ༶�б�
	 * @return
	 */
	public String android_class_getlist();
	/**
	 * android�ͻ��������ϵ��
	 * @param string
	 * @return
	 */
	public String android_contact_add(@WebParam(name="string") String string);
	/**
	 * android�ͻ����޸���ϵ��
	 * @param string
	 * @return
	 */
	public String android_contact_update(@WebParam(name="string") String string);
	/**
	 * android�ͻ���ɾ����ϵ��
	 * @param string
	 * @return
	 */
	public String android_contact_delete(@WebParam(name="string") String string);
	/**
	 * android�ͻ��˻�ȡ��ϵ�˶���
	 * @param string
	 * @return
	 */
	public String android_contact_getmodel(@WebParam(name="string") String string);
	/**
	 * android�ͻ��˻�ȡ��ϵ���б�
	 * @param string
	 * @return
	 */
	public String android_contact_getlist(@WebParam(name="string") String string);
	
	/***********************android******************************/
}
