package com.nwuer.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class TeacherFilter
 */
public class LoginVerifyFilter implements Filter {
	private FilterConfig filterConfig;
	private String redirectUrl;
	private Set<String> notCheckUrlList = new HashSet<String>();

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		this.notCheckUrlList.clear();
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * ��ʦû��½ֻ�ܿ�����¼ҳ��,��¼��Ҳֻ�ܿ���/teacher/*�µ�����
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//��url����س�checkSessionKey
		String url = req.getServletPath(); 
		String checkSessionKey = url.split("/")[1];
		
		//����ͼ��Session������û��Ҫ����key,���û�о�����
		Object key = req.getSession().getAttribute(checkSessionKey);
		if(key != null) {
			//˵���Ѿ���¼,�ڷ����������ҳ��
			
			if(checkSessionKey.equals("student")) { //˵����ѧ��
				if(url.startsWith("/student/")) { //˵���������ѧ������
					chain.doFilter(request, response);
					return;
				}
			}
			
			
			if(checkSessionKey.equals("teacher")) { //˵������ʦ
				if(url.startsWith("/teacher/")) { //˵�����������ʦ����
					chain.doFilter(request, response);
					return;
				}
			}
			
			if(checkSessionKey.equals("admin")) { //˵���ǹ���Ա
				if(url.startsWith("/admin/")) { //˵��������ǹ���Ա����
					chain.doFilter(request, response);
					return;
				}
			}
			
			//��¼�ĺ��û��������ҳ�治��
			//���ݵ�¼�û�������ת
			if(checkSessionKey.equals("student"))
				res.sendRedirect(req.getContextPath() + "/student/");
			else if(checkSessionKey.equals("teacher"))
				res.sendRedirect(req.getContextPath() + "/teacher/");
			else if(checkSessionKey.equals("admin"))
				res.sendRedirect(req.getContextPath() + "/admin/");
			else
				res.sendRedirect(req.getContextPath() + "/");
			
		}else {
			//û�е�¼
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO ��ʼ��
		this.filterConfig = fConfig;
		this.redirectUrl = fConfig.getInitParameter("redirectUrl");
		String notCheckUrlStr = fConfig.getInitParameter("notCheckUrlList");
		if(notCheckUrlStr != null && !notCheckUrlStr.equals("")) {
			//�ֲ���Ҫ���˵�url
			String[] urls = notCheckUrlStr.split(",");
			for(String url : urls)
				this.notCheckUrlList.add(url);
		}
	}

}
