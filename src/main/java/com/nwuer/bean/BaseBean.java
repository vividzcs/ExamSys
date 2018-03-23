package com.nwuer.bean;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseBean<T> {
	private int totalCount; //所有的总条数
	private int nowPage; //开始页,默认为1
	private int size; //一页显示的条数
	private int totalPage; //总页数
	private int start; //开始位置   list中每一页开始的下标
	private int pageCode = 2; //在当前页前显示多少页码,当前页后显示多少页码
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
