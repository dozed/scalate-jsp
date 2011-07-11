package com.fusesource.scalate.jsp;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.jsp.JspPage;

public class ScalateJspPage implements JspPage {

	private ServletConfig config;

	public void init(ServletConfig config) throws ServletException {
		this.config = config;
	}

	public ServletConfig getServletConfig() {
		return config;
	}

	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void jspInit() {
		// TODO Auto-generated method stub
		
	}

	public void jspDestroy() {
		// TODO Auto-generated method stub
		
	}

}
