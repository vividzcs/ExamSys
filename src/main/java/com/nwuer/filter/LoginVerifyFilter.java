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
	 * 老师没登陆只能看到登录页面,登录后也只能看到/teacher/*下的内容
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		//从url里面截出checkSessionKey
		String url = req.getServletPath(); 
		String checkSessionKey = url.split("/")[1];
		
		//否则就检查Session里面有没有要检查的key,如果没有就拦截
		Object key = req.getSession().getAttribute(checkSessionKey);
		
		//学生页面单独考虑
		if(url.startsWith("/student/")) { //说明请求的是学生页面
			if(url.indexOf("/more/") == -1) { //说明请求的是不需要登录的页面
				chain.doFilter(request, response);
				return;
			}else { //请求的是要登录的页面
				if(key == null) { //没有登录
					res.sendRedirect(req.getContextPath() + "/student/loginStudent.jsp");
					return;
				}else{
					chain.doFilter(request, response);
					return;
				}
			}
		}
		
		
		if(key != null) {
			//说明已经登录,在分析他想进的页面
			
			if(checkSessionKey.equals("teacher")) { //说明是老师
				if(url.startsWith("/teacher/")) { //说明请求的是老师界面
					chain.doFilter(request, response);
					return;
				}
			}
			
			if(checkSessionKey.equals("admin")) { //说明是管理员
				if(url.startsWith("/admin/")) { //说明请求的是管理员界面
					chain.doFilter(request, response);
					return;
				}
			}
			
			//登录的和用户和想进的页面不符
			//根据登录用户进行跳转
			if(checkSessionKey.equals("student"))
				res.sendRedirect(req.getContextPath() + "/student/");
			else if(checkSessionKey.equals("teacher"))
				res.sendRedirect(req.getContextPath() + "/teacher/");
			else if(checkSessionKey.equals("admin"))
				res.sendRedirect(req.getContextPath() + "/admin/");
			else
				res.sendRedirect(req.getContextPath() + "/");
			
		}else {
			//没有登录
			res.sendRedirect(req.getContextPath() + "/index.jsp");
		}
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO 初始化
		this.filterConfig = fConfig;
		this.redirectUrl = fConfig.getInitParameter("redirectUrl");
		String notCheckUrlStr = fConfig.getInitParameter("notCheckUrlList");
		if(notCheckUrlStr != null && !notCheckUrlStr.equals("")) {
			//又不需要过滤的url
			String[] urls = notCheckUrlStr.split(",");
			for(String url : urls)
				this.notCheckUrlList.add(url);
		}
	}

}
