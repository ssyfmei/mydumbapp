package com.yifeng.struts2;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseActionSupport extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware,
		ModelDriven {

	protected HttpServletRequest request;

	protected HttpServletResponse response;

	protected HttpSession session;

	protected ServletContext context;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public HttpSession getSession() {
		return request.getSession();
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;

	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	public void setServletContext(ServletContext arg0) {
		this.context = arg0;
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return null;
	}
}

