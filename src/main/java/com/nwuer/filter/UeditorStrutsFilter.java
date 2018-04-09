package com.nwuer.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.dispatcher.filter.StrutsPrepareAndExecuteFilter;

public class UeditorStrutsFilter extends StrutsPrepareAndExecuteFilter {

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) arg0;
		String url = request.getRequestURI();
		String ueditorUrl = request.getContextPath()+"/style/plugins/ueditor/jsp/controller.jsp";
		if(ueditorUrl.equals(url)) 
			arg2.doFilter(arg0, arg1);
		else
			super.doFilter(arg0, arg1, arg2);
	}
	
}
