package com.nwuer.interceptor;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Component;

import com.nwuer.entity.Student;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

@Component
public class StudentInterceptor extends MethodFilterInterceptor {

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Student student = (Student) ServletActionContext.getRequest().getSession().getAttribute("student");
		if(student != null) {
			return invocation.invoke();
		}
		return "login";
	}

}
