package com.nwuer.interceptor;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.nwuer.entity.Admin;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@Component
public class AdminInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Admin admin = (Admin) ServletActionContext.getRequest().getSession().getAttribute("admin");
		if(admin != null) {
			return invocation.invoke();
		}
		return "login";
	}

}
