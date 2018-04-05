package com.nwuer.interceptor;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.nwuer.entity.Teacher;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@Component
public class TeacherInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Teacher teacher = (Teacher) ServletActionContext.getRequest().getSession().getAttribute("teacher");
		if(teacher != null) {
			return invocation.invoke();
		}
		return "login";
	}

}
