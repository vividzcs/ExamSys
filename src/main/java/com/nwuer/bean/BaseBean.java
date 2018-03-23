package com.nwuer.bean;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBean<T> {
	private int totalCount; //���е�������
	private int nowPage; //��ʼҳ,Ĭ��Ϊ1
	private int size; //һҳ��ʾ������
	private int totalPage; //��ҳ��
	private int start; //��ʼλ��   list��ÿһҳ��ʼ���±�
	private int pageCode = 2; //�ڵ�ǰҳǰ��ʾ����ҳ��,��ǰҳ����ʾ����ҳ��
	private List<T> list;
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getPageCode() {
		return pageCode;
	}
	public void setPageCode(int pageCode) {
		this.pageCode = pageCode;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
}
